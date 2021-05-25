package com.projetointegrador.model.beans;

import com.projetointegrador.controller.LoginController;
import com.projetointegrador.model.dao.UsuarioDao;
import com.projetointegrador.views.MainView;
import com.projetointegrador.model.entities.Usuario;
import java.util.List;

/**
 * @author RafaelRodrigues1
 */
public class LoginBeans {
    
    
    private LoginController loginController;
    private MainView mainView;
    private UsuarioDao usuarioDao;

    
    public LoginBeans(LoginController loginController) {
        this.loginController = loginController;
        usuarioDao = new UsuarioDao(this);
    }
    
    public Boolean entrar(String login, String senha){
        try{
            Usuario usuario = new Usuario(login, senha);
            return usuarioDao.autenticaUsuario(usuario);
        }catch(Exception ex){
            return false; 
        }
    }
}
