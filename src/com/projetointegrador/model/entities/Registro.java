package com.projetointegrador.model.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author RafaelRodrigues1
 */
public class Registro {
    
    private Usuario usuario;
    private String descricao;
    private LocalDateTime dataHora;

    //Construtor para cadastro
    public Registro(Usuario usuario, String descricao) {
        this.usuario = usuario;
        this.descricao = descricao;
        this.dataHora = LocalDateTime.now();
    }
    //Construtor para dados vindos do BD
    public Registro(Usuario usuario, String descricao, LocalDateTime dataHora) {
        this.usuario = usuario;
        this.descricao = descricao;
        this.dataHora = dataHora;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}
