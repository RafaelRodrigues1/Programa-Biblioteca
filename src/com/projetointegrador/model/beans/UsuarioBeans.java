package com.projetointegrador.model.beans;

import com.projetointegrador.controller.UsuarioController;
import com.projetointegrador.model.dao.UsuarioDao;
import com.projetointegrador.model.entities.Usuario;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * @author RafaelRodrigues1
 */
public class UsuarioBeans {
    
    
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
    private UsuarioDao usuarioDao;
    private UsuarioController usuarioController;

    public UsuarioBeans(UsuarioController usuarioController) {
        this.usuarioController = usuarioController;
        usuarioDao = new UsuarioDao(this);
    }
    
    public Boolean cadastrar(String login, String senha, String nome, String data){        
        LocalDate dataNascimento = LocalDate.parse(data, dtf);
        Usuario usuario = new Usuario(login, senha, nome, dataNascimento);
        return usuarioDao.cadastrar(usuario);
    }
}
