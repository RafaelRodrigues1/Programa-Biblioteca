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

    public Cliente(String nome, LocalDate dataNascimento, String email, String cpf, String endereco, String telefone, Integer numeroLivros) {
        super(nome, dataNascimento);
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.cpf = cpf;
        this.numeroLivros = numeroLivros;
    }

    public Cliente(String nome, LocalDate dataNascimento, String email, String cpf, String endereco, String telefone) {
        super(nome, dataNascimento);
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.cpf = cpf;
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

    @Override
    public String toString() {
        return "Cliente{" + "telefone=" + telefone + ", email=" + email + ", cpf=" + cpf + '}';
    }
    
    
}
