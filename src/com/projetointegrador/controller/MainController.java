package com.projetointegrador.controller;

import com.projetointegrador.views.MainView;
import com.projetointegrador.model.entities.Usuario;
import com.projetointegrador.views.AtividadeView;
import com.projetointegrador.views.ClienteView;
import com.projetointegrador.views.EmprestimoView;
import com.projetointegrador.views.LivroView;
import com.projetointegrador.views.LoginView;
import com.projetointegrador.views.Panes;
/**
 * @author RafaelRodrigues1
 */
public class MainController {
    
    private static final Usuario admin = new Usuario("admin", "123admin456");
    private final MainView mainView;
    private Usuario usuario;
    private LoginView loginView;
    private LivroView livroView;
    private ClienteView clienteView;
    private AtividadeView atividadeView;
    private EmprestimoView emprestimoView;

    public MainController(MainView mainView, Usuario usuario) {
        this.mainView = mainView;
        this.usuario = usuario;
        usuarioTela();
    }
    
    private void usuarioTela(){ 
        mainView.getjLabelNomeUsuario().setText("Usuário: " + usuario.getLogin());
    }
    
    public void logOut(){
        loginView = new LoginView();
        loginView.setVisible(true);
        mainView.setVisible(false);
    }
    
    public void abrirLivros(){
        livroView = new LivroView();
        livroView.setUsuario(usuario);
        livroView.setVisible(true);
        mainView.setVisible(false);
    }
    
    public void abrirClientes(){
        clienteView = new ClienteView();
        clienteView.setUsuario(usuario);
        clienteView.setVisible(true);
        mainView.setVisible(false);
    }
    
    public void abrirEmprestimos(){
        emprestimoView = new EmprestimoView();
        emprestimoView.setUsuario(usuario);
        emprestimoView.setVisible(true);
        mainView.setVisible(false);
    }
    
    public void abrirAtividades(){
        if(usuario.equals(admin)){
            atividadeView = new AtividadeView();
            atividadeView.setUsuario(usuario);
            atividadeView.setVisible(true);
        }else{
            Panes.mostraMsg("Você não possui autorização para acessar as atividades");
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
