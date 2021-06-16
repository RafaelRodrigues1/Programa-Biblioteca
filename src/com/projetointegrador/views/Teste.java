package com.projetointegrador.views;

import com.projetointegrador.model.dao.EmprestimoDao;
import com.projetointegrador.model.entities.Cliente;
import com.projetointegrador.model.entities.Emprestimo;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author RafaelRodrigues1
 */
public class Teste {
    
    public static void main(String[] args) {
        
//        EmprestimoDao emprestimoDao = new EmprestimoDao();
//        List<Emprestimo> listEmprestimo = emprestimoDao.listarAbertos();
//        List<Cliente> listAtrasados = listEmprestimo
//                .stream()
//                .filter(emprestimo -> {
//                    long dias = ChronoUnit.DAYS.between(emprestimo.getDataPrazoEntrega(), LocalDate.now());
//                    return dias > 0;
//                })
//                .map(Emprestimo::getCliente)
//                .collect(Collectors.toList());
//        
//        List<Cliente> listAvisoDias = listEmprestimo
//                .stream()
//                .filter(emprestimo -> {
//                    long dias = ChronoUnit.DAYS.between(emprestimo.getDataPrazoEntrega(), LocalDate.now());
//                    return dias < 0 && dias >= -2;
//                })
//                .map(Emprestimo::getCliente)
//                .collect(Collectors.toList());
//        
//        List<Cliente> listAvisoHoje = listEmprestimo
//                .stream()
//                .filter(emprestimo -> {
//                    long dias = ChronoUnit.DAYS.between(emprestimo.getDataPrazoEntrega(), LocalDate.now());
//                    return dias == 0;
//                })
//                .map(Emprestimo::getCliente)
//                .collect(Collectors.toList());        
//        
//        System.out.println("Atrasados:"); 
//        listAtrasados.forEach(cliente -> System.out.println(cliente.getNome()));
//        System.out.println("");
//        System.out.println("dias:"); 
//        listAvisoDias.forEach(cliente -> System.out.println(cliente.getNome()));
//        System.out.println("");
//        System.out.println("hj:"); 
//        listAvisoHoje.forEach(cliente -> System.out.println(cliente.getNome()));
    }
}
