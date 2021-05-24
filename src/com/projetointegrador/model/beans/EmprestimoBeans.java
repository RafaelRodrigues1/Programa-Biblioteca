package com.projetointegrador.model.beans;

import com.projetointegrador.controller.EmprestimoController;
import com.projetointegrador.model.dao.EmprestimoDao;
import com.projetointegrador.model.entities.Emprestimo;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * @author RafaelRodrigues1
 */
public class EmprestimoBeans {
    
    private final Double MULTA_DIA =5d;
    private EmprestimoController emprestimoController;
    private EmprestimoDao emprestimoDao;

    public EmprestimoBeans(EmprestimoController emprestimoController) {
        this.emprestimoController = emprestimoController;
        emprestimoDao = new EmprestimoDao(this);
    }
    
    
    
    
    public Double calculaMulta(Emprestimo emprestimo){   //Deve estar no EmprestimoBeans????
//        Date dataAtual = new Date();
//        Long diferenca = dataAtual.getTime() - getDataEntrega().getTime();
//        Long dias = TimeUnit.DAYS.convert(diferenca, TimeUnit.MILLISECONDS);
        LocalDate dataEntrega = LocalDate.now();
        System.out.println(dataEntrega);
        //Period periodo = Period.between(getDataEntrega(), dataAtual);
        //int dias = periodo.getDays();
        long dias = ChronoUnit.DAYS.between(emprestimo.getDataPrazoEntrega(), dataEntrega);
        if(dias>0){
            return MULTA_DIA*dias*emprestimo.getListaLivro().size();
        }
        return 0d;
    }
}
