package com.projetointegrador.model.beans;

import com.projetointegrador.controller.EmprestimoController;
import com.projetointegrador.model.dao.ClienteDao;
import com.projetointegrador.model.dao.interfaces.ClienteDaoInterface;
import com.projetointegrador.model.dao.EmprestimoDao;
import com.projetointegrador.model.dao.LivroDao;
import com.projetointegrador.model.dao.interfaces.LivroDaoInterface;
import com.projetointegrador.model.dao.RegistroDao;
import com.projetointegrador.model.entities.Cliente;
import com.projetointegrador.model.entities.Emprestimo;
import com.projetointegrador.model.entities.Livro;
import com.projetointegrador.model.entities.Registro;
import com.projetointegrador.model.services.emailservice.EmailService;
import com.projetointegrador.views.Panes;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;

/**
 * @author RafaelRodrigues1
 */
public class EmprestimoBeans {
    
    private final Double MULTA_DIA = 5d;
    private final EmprestimoController emprestimoController;
    private final EmprestimoDao emprestimoDao;
    private final ClienteDaoInterface<Cliente> clienteDao;
    private final LivroDaoInterface<Livro> livroDao;
    

    public EmprestimoBeans(EmprestimoController emprestimoController) {
        this.emprestimoController = emprestimoController;
        emprestimoDao = new EmprestimoDao();
        clienteDao = new ClienteDao();
        livroDao = new LivroDao();
    }
    
    public Boolean efetuaEmprestimo(Integer idCliente, String nomeCliente, Integer quantidadeEmprestimos,
            Integer codigoLivro, String titulo){
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
        Emprestimo emprestimo = new Emprestimo(new Cliente(idCliente, nomeCliente, quantidadeEmprestimos), 
                new Livro(titulo, codigoLivro), LocalDate.now());
        if(emprestimoDao.efetuaEmprestimo(emprestimo)){
            if(livroDao.emprestaLivro(emprestimo.getLivro()) && 
                    clienteDao.tomaLivroEmprestado(emprestimo.getCliente())){
                    Cliente cliente = clienteDao.buscarPorId(idCliente);
                    EmailService email = new EmailService("Empréstimo de livro efetuado", 
                            "Sr. " + cliente.getNome() + ",\nEmpréstimo do livro " + titulo + 
                            " efetuado com sucesso!\nData de entrega: " + sdf.format(emprestimo.getDataPrazoEntrega())
                                + "\n\nAtenciosamente,\nEquipe BiblioSoft.", cliente.getEmail());
                    email.start();
                    RegistroDao.cadastroRegistro(new Registro(emprestimoController.getUsuario(), 
                    "Empréstimo do livro: " + emprestimo.getLivro().getTitulo()
                            + " para o Cliente: " + emprestimo.getCliente().getNome()));
                if(clienteDao.buscarPorId(emprestimo.getCliente().getId()).getNumeroLivros() >= 3){
                    return clienteDao.desautorizaCliente(emprestimo.getCliente());
                }
                return true;
            }
        }
        return false;
    }
    
    public Boolean devolveLivro(Integer id){
        Emprestimo emprestimo = emprestimoDao.buscarPorId(id);
        if(calculaMulta(emprestimo)){
            if(emprestimoDao.fechaEmprestimo(id)){
                if(livroDao.devolveLivro(emprestimo.getLivro()) &&
                        clienteDao.devolveLivro(emprestimo.getCliente())){
                    RegistroDao.cadastroRegistro(new Registro(emprestimoController.getUsuario(), 
                    "Devolução do livro: " + emprestimo.getLivro().getTitulo()
                            + " do Cliente: " + emprestimo.getCliente().getNome()));
                    if(clienteDao.buscarPorId(emprestimo.getCliente().getId()).getNumeroLivros() < 3 
                            && verificaClienteAtrasado(emprestimo.getCliente())){
                        return clienteDao.autorizaCliente(emprestimo.getCliente());
                    }
                }
                return true;
            }
        }
        return false;
    }
    
    private Boolean verificaClienteAtrasado(Cliente cliente){
        Set<Cliente> setCliente = clienteDao.verificaAtrasos();
        return !setCliente.contains(cliente);
    }
    
    public List<Emprestimo> pesquisaGeral(String pesquisa){
        return emprestimoDao.pesquisaGeral(pesquisa);
    }
    
    public List<Emprestimo> pesquisaAbertos(String pesquisa){
        return emprestimoDao.pesquisaAbertos(pesquisa);
    }
    
    public List<Livro> pesquisaLivro(String pesquisa){
        return livroDao.pesquisarEmprestimo(pesquisa);
    }
    
    public List<Cliente> pesquisaCliente(String nome){
        return clienteDao.pesquisarEmprestimo(nome);
    }
    
    public List<Emprestimo> listarAbertos(){
        return emprestimoDao.listarAbertos();
    }
    
    public List<Emprestimo> listarTodos(){
        return emprestimoDao.listarGeral();
    }
    
    public List<Livro> listarLivro(){
        return livroDao.listarEmprestimo();
    }
    
    public List<Cliente> listarCliente(){
        return clienteDao.listarEmprestimo();
    }
    
    private Boolean calculaMulta(Emprestimo emprestimo){
        LocalDate dataEntrega = LocalDate.now();
        long dias = ChronoUnit.DAYS.between(emprestimo.getDataPrazoEntrega(), dataEntrega);
        if(dias>0){
            return Panes.confirma("Multa de R$" + String.format("%.2f", MULTA_DIA*dias) + "\n"
                    + "Por " + dias + " dias de atraso") != 1 ;
        }
        return true;
    }
}
