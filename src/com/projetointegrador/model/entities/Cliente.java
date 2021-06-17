package com.projetointegrador.model.entities;

import java.time.LocalDate;
import java.util.Objects;

/**
 * @author RafaelRodrigues1
 */
public class Cliente extends Pessoa  {
    
    private String endereco;
    private String telefone;
    private String email;
    private String cpf;
    private Integer numeroLivros;
    private Integer id;
    private Boolean liberado;

    public Cliente(Integer id, String nome, LocalDate dataNascimento, String email, 
            String cpf, String endereco, String telefone, Integer numeroLivros, Boolean liberado) {
        super(nome, dataNascimento);
        this.id = id;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.cpf = cpf;
        this.numeroLivros = numeroLivros;
        this.liberado = liberado;
    }
        //Construtor para cadastro
    public Cliente(String nome, LocalDate dataNascimento, String email, String cpf, String endereco, String telefone) {
        super(nome, dataNascimento);
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.cpf = cpf;
    }
        //Construtor para alteração
    public Cliente(Integer id, String nome, LocalDate dataNascimento, String email, String cpf, String endereco, String telefone) {
        super(nome, dataNascimento);
        this.id = id;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.cpf = cpf;
    }
        //Construtor para tela de Empréstimo
    public Cliente(Integer id, String nome, Integer numeroLivros) {
        super(nome);
        this.id = id;
        this.numeroLivros = numeroLivros;
    }
    
    public Cliente(Integer id, String nome, String email, Integer numeroLivros) {
        super(nome);
        this.id = id;
        this.email = email;
        this.numeroLivros = numeroLivros;
    }

        //Construtor para Empréstimo
    public Cliente(Integer id, String nome) {
        super(nome);
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
    
    @Override
    public int compareTo(Pessoa o) {
        return this.getNome().compareTo(o.getNome());
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Cliente other = (Cliente) obj;
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.cpf, other.cpf)) {
            return false;
        }
        return true;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public Integer getNumeroLivros() {
        return numeroLivros;
    }

    public Boolean getLiberado() {
        return liberado;
    }

    public void setLiberado(Boolean liberado) {
        this.liberado = liberado;
    }
    
    @Override
    public String toString() {
        return "Cliente: " + super.getNome() + ", email=" + email + ", cpf=" + cpf + '}';
    }
    
    
}
