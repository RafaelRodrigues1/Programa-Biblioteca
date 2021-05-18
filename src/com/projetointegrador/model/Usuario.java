package com.projetointegrador.model;

import java.util.Date;
import java.util.Objects;

/**
 * @author RafaelRodrigues1
 */
public class Usuario extends Pessoa {
    
    private String login;
    private String senha;

    public Usuario(String login, String senha, String nome, Date dataNascimento) {
        super(nome, dataNascimento);
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.login);
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
        final Usuario other = (Usuario) obj;
        return Objects.equals(this.login, other.login);
    }
    
    @Override
    public int compareTo(Pessoa o) {
        return this.getNome().compareTo(o.getNome());
    }
    
    
}
