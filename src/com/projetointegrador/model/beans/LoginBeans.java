package com.projetointegrador.model.beans;

import com.projetointegrador.controller.LoginController;
import com.projetointegrador.model.dao.UsuarioDao;
import com.projetointegrador.views.MainView;
import com.projetointegrador.model.entities.Usuario;

/**
 * @author RafaelRodrigues1
 */
public class LoginBeans {
    
    private final LoginController loginController;
    private final UsuarioDao usuarioDao;
    
    public LoginBeans(LoginController loginController) {
        this.loginController = loginController;
        usuarioDao = new UsuarioDao(this);
    }
    
    public Boolean entrar(Usuario usuario){
        if(usuarioDao.autenticaUsuario(usuario)){
            AtividadesDiariasBeans.startAtividades();
            return true;
        }
        return false;
    }
}
