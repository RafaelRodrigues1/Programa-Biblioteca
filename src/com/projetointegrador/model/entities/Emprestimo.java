package com.projetointegrador.model.entities;

import java.time.LocalDate;
import java.util.Objects;

/**
 * @author RafaelRodrigues1
 */
public class Emprestimo {
    
    private Integer codigo;
    private Cliente cliente;
    private Livro livro;
    private LocalDate dataEmprestimo;
    private LocalDate dataPrazoEntrega;
    private final Long PRAZO = 5l; //Prazo de 5 dias para devolução do livro
    
    //Momento do empréstimo                                
    public Emprestimo(Cliente cliente, Livro livro, LocalDate dataEmprestimo) {
        this.cliente = cliente;
        this.livro = livro;
        this.dataEmprestimo = dataEmprestimo;
        this.dataPrazoEntrega = calculaPrazo(dataEmprestimo);
    }
    
    //Dados vindos da DB
    public Emprestimo(Integer codigo, Cliente cliente, Livro livro, 
            LocalDate dataEmprestimo, LocalDate dataPrazoEntrega){
        this.codigo = codigo;
        this.cliente = cliente;
        this.livro = livro;
        this.dataEmprestimo = dataEmprestimo;
        this.dataPrazoEntrega = dataPrazoEntrega; //será a data atual no Beans
    }
    
    //Prazo de 5 dias para devolução do livro
    public final LocalDate calculaPrazo(LocalDate dataEmprestimo){
        LocalDate dataPrazoEntrega = dataEmprestimo.plusDays(PRAZO);
        if(dataPrazoEntrega.getDayOfWeek().getValue()==6){   //6=SÁBADO
            dataPrazoEntrega = dataPrazoEntrega.plusDays(2);
        }
        if(dataPrazoEntrega.getDayOfWeek().getValue()==7){   //7=DOMINGO
            dataPrazoEntrega = dataPrazoEntrega.plusDays(1);
        }
        return dataPrazoEntrega;
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

    public Livro getLivro() {
        return livro;
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