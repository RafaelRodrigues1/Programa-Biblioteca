package com.projetointegrador.model.beans;

import com.projetointegrador.controller.UsuarioController;
import com.projetointegrador.model.dao.UsuarioDao;
import com.projetointegrador.model.entities.Usuario;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


/**
 * @author RafaelRodrigues1
 */
public class UsuarioBeans {
    
    
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
    private final UsuarioDao usuarioDao;
    private final UsuarioController usuarioController;

    public UsuarioBeans(UsuarioController usuarioController) {
        this.usuarioController = usuarioController;
        usuarioDao = new UsuarioDao(this);
    }
    
    public Boolean cadastrar(String login, String senha, String nome, String data){        
        LocalDate dataNascimento = LocalDate.parse(data, dtf);
        Usuario usuario = new Usuario(login, senha, nome, dataNascimento);
        return usuarioDao.cadastrar(usuario);
    }
    
    public Boolean verificaUsuario(String login){
        List<Usuario> listaUsuario = usuarioDao.listarUsuario();
        return !listaUsuario
                        .stream()
                        .anyMatch(usuario -> usuario.getLogin().toUpperCase().equals(login.toUpperCase()));
    }
}
