package com.projetointegrador.controller;

import com.projetointegrador.model.beans.LoginBeans;
import com.projetointegrador.model.entities.Usuario;
import com.projetointegrador.views.UsuarioView;
import com.projetointegrador.views.LoginView;
import com.projetointegrador.views.MainView;
import com.projetointegrador.views.Panes;

/**
 * @author RafaelRodrigues1
 */
public class LoginController {
    
    private LoginView loginView;
    private LoginBeans loginBeans;
    private UsuarioView cadastroUsuarioView;

    public LoginController(LoginView loginView) {
        this.loginView = loginView;
        loginBeans = new LoginBeans(this);
    }
    
    public void entrar(){
        try{    
            String senha = getSenha();
            String login = loginView.getjTextLogin().getText();
            if(!senha.isBlank() && !login.isBlank()){                
                if(loginBeans.entrar(login, senha)){
                    MainView mainView = new MainView();
                    mainView.setVisible(true);
                    mainView.getMainController().setUsuario(login);
                    loginView.setVisible(false);
                }else{
                    throw new Exception("Usuário ou senha inválidos");
                }
            }else{
                throw new Exception("Campos em branco");
            }
        }catch(Exception ex){
            Panes.mostraMsg(ex.getMessage());
        }
    }
    
    public void cadastrar(){        
        try{   
            if(loginView.getjTextLogin().getText().equals("admin") && getSenha().equals("123admin456")){
                cadastroUsuarioView = new UsuarioView();
                cadastroUsuarioView.setVisible(true);
            }else{
                throw new Exception("Permissão negada");
            }
        }catch(Exception ex){
            Panes.mostraMsg(ex.getMessage());
        }    
    }
    
    public String getSenha(){
        String senha = "";
        char[] senhaVector = loginView.getjPasswordSenha().getPassword();
            for(char a : senhaVector){
                senha += a;
            }
        return senha;
    }
}
