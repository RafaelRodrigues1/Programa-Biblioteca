package com.projetointegrador.controller;

import com.projetointegrador.views.MainView;
import com.projetointegrador.model.Usuario;
import com.projetointegrador.views.ClienteView;
import com.projetointegrador.views.LivroView;
import com.projetointegrador.views.LoginView;
/**
 * @author RafaelRodrigues1
 */
public class MainController {
    
    
    private MainView mainView;
    private Usuario usuario;
    private LoginView loginView;
    private LivroView livroView;
    private ClienteView clienteView;

    public MainController(MainView mainView) {
        this.mainView = mainView;
    }
    
    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
        mainView.getjLabelNomeUsuario().setText("Usuário: " + usuario.getLogin());
    }
    
    public void logOut(){
        loginView = new LoginView();
        loginView.setVisible(true);
        mainView.setVisible(false);
    }
    
    public void abrirLivros(){
        livroView = new LivroView();
        livroView.setVisible(true);
    }
    
    public void abrirClientes(){
        clienteView = new ClienteView();
        clienteView.setVisible(true);
    }
}
