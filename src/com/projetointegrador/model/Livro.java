package com.projetointegrador.model;

import java.util.Objects;

/**
 * @author RafaelRodrigues1
 */
public class Livro implements Comparable<Livro> {
    
    private String titulo;
    private String autor;
    private Genero genero;
    private String editora;
    private String edicao;
    private String anotacoes;   
    private Boolean alugavel;
    private Boolean alugado;

    private final Integer codigo;
    private static Integer codigos=1000;

        //Construtor para dados vindos do banco

    public Livro(String titulo, String autor, Genero genero, String editora, String edicao, 
            String anotacoes, Boolean alugavel, Boolean alugado, Integer codigo) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.editora = editora;
        this.edicao = edicao;
        this.anotacoes = anotacoes;
        this.alugavel = alugavel;
        this.alugado = alugado;
        this.codigo = codigo;
        codigos=codigo;
    }
    
    
        //Construtor para cadastro
    public Livro(String titulo, String autor, Genero genero, String editora, Boolean alugavel) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.editora = editora; 
        codigos++;
        this.codigo = codigos;
        this.alugavel = alugavel; 
        if(alugavel){
            this.alugado = true;
        }else{
            this.alugado = false;
        }
    } 
    
    @Override
    public int compareTo(Livro o) {
        return this.getTitulo().compareTo(o.getTitulo());
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
        final Livro other = (Livro) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getEdicao() {
        if(edicao == null){
            return "";
        }
        return edicao;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }

    public String getAnotacoes() {
        if(anotacoes == null){
            return "";
        }
        return anotacoes;
    }

    public void setAnotacoes(String anotacoes) {
        this.anotacoes = anotacoes;
    }

    public String getAlugavel() {
        if(alugavel){
            return "Sim";
        }else{
            return "Não";
        }
    }

    public void setAlugavel(Boolean alugavel) {
        if(!alugavel){
            this.alugado=false;
        }
        this.alugavel = alugavel;
    }

    public Integer getCodigo() {
        return codigo;
    }  

    public String getAlugado() {
        if(alugado){
            return "Sim";
        }else{
            return "Não";
        }
    }

    public void setAlugado(Boolean alugado) {
        this.alugado = alugado;
    }
}
