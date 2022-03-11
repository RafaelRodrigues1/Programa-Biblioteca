package com.projetointegrador.model.beans;

import com.projetointegrador.model.dao.AtividadesDiariasDao;
import com.projetointegrador.model.dao.ClienteDao;
import com.projetointegrador.model.dao.EmprestimoDao;
import com.projetointegrador.model.dao.interfaces.ClienteDaoInterface;
import com.projetointegrador.model.entities.Cliente;
import com.projetointegrador.model.entities.Emprestimo;
import com.projetointegrador.model.services.emailservice.EmailService;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author RafaelRodrigues1
 */
public class AtividadesDiariasBeans extends Thread{
    
    private static EmprestimoDao emprestimoDao;
    private static ClienteDaoInterface clienteDao;
    private static List<Emprestimo> listEmprestimo;

    @Override
    public void run(){
        if(AtividadesDiariasDao.insereDia()){  
            emprestimoDao = new EmprestimoDao();
            clienteDao = new ClienteDao();
            listEmprestimo = emprestimoDao.listarAbertos();
            desautorizaAtrasadosAuto();
            enviaEmails();
        }
    }
    
    private static void desautorizaAtrasadosAuto(){
        Set<Cliente> setClientes = clienteDao.verificaAtrasos();
        setClientes.stream().forEach(clienteDao::desautorizaCliente);
    }
    
    private static void enviaEmails(){
        List<Cliente> listAtrasados = listaAtrasados();
        List<Cliente> listAvisoDias = listaAvisoDias();
        List<Cliente> listAvisoHoje = listaAvisoHoje();
        
        if(!listAtrasados.isEmpty()){
            listAtrasados.forEach((Cliente cliente) -> {
                EmailService email = new EmailService("Entrega de livro em atraso", 
                        "Prezado(a) " + cliente.getNome() + ",\nVerificamos um atraso na entrega "
            + "de livro(s) em seu nome.\nPedimos a você que nos procure para a melhor solução da situação \ne para "
            + "que você volte, o mais rápido possível, a aproveitar dos nossos serviços.\n\n"
            + "Atenciosamente,\nEquipe BiblioSoft.", cliente.getEmail());
                email.start();
            });
        }
        
        if(!listAvisoDias.isEmpty()){
            listAvisoDias.forEach((Cliente cliente) -> {
                EmailService email = new EmailService("Prazo de entrega vence amanhã!", 
                        "Prezado(a) " + cliente.getNome() + ",\nO prazo de entrega do livro "
            + "vence amanhã.\nEsteja sempre em dia com a nossa biblioteca para poder aproveitar dos nossos serviços sempre."
            + "\n\nAtenciosamente,\nEquipe BiblioSoft.", cliente.getEmail());
                email.enviaEmail();
            });
        }
        
        if(!listAvisoHoje.isEmpty()){
            listAvisoHoje.forEach((Cliente cliente) -> {
                EmailService email = new EmailService("Prazo de entrega vence hoje!", 
                    "Prezado(a) " + cliente.getNome() + ",\nO prazo de entrega do livro "
            + "vence hoje.\nEsteja sempre em dia com a nossa biblioteca para poder aproveitar dos nossos serviços sempre."
            + "\n\nAtenciosamente,\nEquipe BiblioSoft.", cliente.getEmail());
                email.enviaEmail();
            });
        }
    }
    
    private static List<Cliente> listaAtrasados(){
        return listEmprestimo
                .stream()
                .filter(emprestimo -> {
                    long dias = ChronoUnit.DAYS.between(emprestimo.getDataPrazoEntrega(), LocalDate.now());
                    return dias == 1 || dias == 3 || dias == 5 || dias == 10 || dias == 20;
                })
                .map(Emprestimo::getCliente)
                .collect(Collectors.toList());
    }
    
    private static List<Cliente> listaAvisoDias(){
        return listEmprestimo
                .stream()
                .filter(emprestimo -> {
                    long dias = ChronoUnit.DAYS.between(emprestimo.getDataPrazoEntrega(), LocalDate.now());
                    return dias < 0 && dias >= -2;
                })
                .map(Emprestimo::getCliente)
                .collect(Collectors.toList());
    }
    
    private static List<Cliente> listaAvisoHoje(){
        return listEmprestimo
                .stream()
                .filter(emprestimo -> {
                    long dias = ChronoUnit.DAYS.between(emprestimo.getDataPrazoEntrega(), LocalDate.now());
                    return dias == 0;
                })
                .map(Emprestimo::getCliente)
                .collect(Collectors.toList()); 
    }
}
