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
            List<Usuario> listaUsuario = usuarioDao.listarUsuario();
            if(!listaUsuario.isEmpty()){
                return listaUsuario
                        .stream()
                        .anyMatch(usuario -> usuario.getLogin().equals(login) && usuario.getSenha().equals(senha));
            }else{
                throw new Exception();
            }
        }catch(Exception ex){
            return false; 
        }
    }
}
