package com.projetointegrador.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

/**
 * @author RafaelRodrigues1
 */
public class Emprestimo {
    
    private Integer codigo;
    private Cliente cliente;
    private List<Livro> listaLivro;
    private LocalDate dataEmprestimo;
    private LocalDate dataPrazoEntrega;
    private final Long PRAZO = 5l; //Prazo de 5 dias para devolução do livro
    private final Double MULTA_DIA =5d;
    
    //Momento do empréstimo                                
    public Emprestimo(Cliente cliente, List<Livro> listaLivro, LocalDate dataEmprestimo) {
        this.cliente = cliente;
        this.listaLivro = listaLivro;
        this.dataEmprestimo = dataEmprestimo;
        this.dataPrazoEntrega = calculaPrazo(dataEmprestimo);
    }
    
    //Dados vindos da DB
    public Emprestimo(Integer codigo, Cliente cliente, List<Livro> listaLivro, 
            LocalDate dataEmprestimo, LocalDate dataPrazoEntrega){
        this.codigo = codigo;
        this.cliente = cliente;
        this.listaLivro = listaLivro;
        this.dataEmprestimo = dataEmprestimo;
        this.dataPrazoEntrega = dataPrazoEntrega; //será a data atual no Beans
    }
    
    //Prazo de 5 dias para devolução do livro
    public final LocalDate calculaPrazo(LocalDate dataEmprestimo){
        LocalDate dataPrazoEntrega = dataEmprestimo.plusDays(PRAZO);
        System.out.println(dataPrazoEntrega);
        if(dataPrazoEntrega.getDayOfWeek().getValue()==6){   //6=SÁBADO
            dataPrazoEntrega = dataPrazoEntrega.plusDays(2);
        }
        if(dataPrazoEntrega.getDayOfWeek().getValue()==7){   //7=DOMINGO
            dataPrazoEntrega = dataPrazoEntrega.plusDays(1);
        }
        System.out.println(dataPrazoEntrega);
        return dataPrazoEntrega;
    }
        
    public Double calculaMulta(){   //Deve estar no EmprestimoBeans????
//        Date dataAtual = new Date();
//        Long diferenca = dataAtual.getTime() - getDataEntrega().getTime();
//        Long dias = TimeUnit.DAYS.convert(diferenca, TimeUnit.MILLISECONDS);
        LocalDate dataEntrega = LocalDate.now();
        System.out.println(dataEntrega);
        //Period periodo = Period.between(getDataEntrega(), dataAtual);
        //int dias = periodo.getDays();
        long dias = ChronoUnit.DAYS.between(getDataPrazoEntrega(), dataEntrega);
        if(dias>0){
            return MULTA_DIA*dias*getListaLivro().size();
        }
        return 0d;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Livro> getListaLivro() {
        return listaLivro;
    }

    public void setListaLivro(List<Livro> listaLivro) {
        this.listaLivro = listaLivro;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataPrazoEntrega() {
        return dataPrazoEntrega;
    }

    public void setDataPrazoEntrega(LocalDate dataPrazoEntrega) {
        this.dataPrazoEntrega = dataPrazoEntrega;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.codigo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Emprestimo other = (Emprestimo) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }
}