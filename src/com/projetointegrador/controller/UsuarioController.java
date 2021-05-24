package com.projetointegrador.controller;

import com.projetointegrador.model.beans.UsuarioBeans;
import com.projetointegrador.views.UsuarioView;
import com.projetointegrador.views.LoginView;
import com.projetointegrador.views.Panes;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static javax.swing.text.html.HTML.Tag.P;

/**
 * @author RafaelRodrigues1
 */
public class UsuarioController {
    
    private UsuarioView usuarioView;
    private UsuarioBeans usuarioBeans;
    private LoginView loginView = new LoginView();;

    public UsuarioController(UsuarioView usuarioview) {
        this.usuarioView = usuarioview;
    }
    
    public void cadastrar(){
        try{
            List<String> listaSenhas = getSenhas(); 
            Pattern data = Pattern.compile("\\d{2}/\\d{2}/\\d{4}");
            Matcher matchData = data.matcher(usuarioView.getjFormattedTextData().getText());
            if(matchData.find()){
                if(!usuarioView.getjTextNome().getText().isBlank() && 
                        !usuarioView.getjFormattedTextData().getText().isBlank() &&
                        !usuarioView.getjTextLogin().getText().isBlank() &&
                        !listaSenhas.get(0).isBlank() && !listaSenhas.get(0).isBlank()){
                    if(verificaSenhas()){
                        usuarioBeans = new UsuarioBeans(this);
                        if(usuarioBeans.cadastrar(usuarioView.getjTextLogin().getText().trim(), listaSenhas.get(0),
                                usuarioView.getjTextNome().getText(), 
                                usuarioView.getjFormattedTextData().getText())){
                            Panes.mostraMsg("Usuário "+ usuarioView.getjTextLogin().getText() +" cadastrado com sucesso");
                            usuarioView.setVisible(false);
                            loginView.setVisible(true);
                        }else{
                            usuarioView.getjLabelAvisoUsuario().setText("*Usuário indisponível");
                        }
                    }else{
                        throw new Exception("Campos de senha não coindidem");
                    }
                }else{
                    throw new Exception("Todos os campos são obrigatórios");
                } 
            }else{
                throw new Exception("Data inválida");
            }
        }catch(Exception ex){
            Panes.mostraMsg(ex.getMessage());
        }
    }
    
    public void campoMostraSenha(){
        if(usuarioView.getjCheckBoxMostrarSenha().isSelected()){
            usuarioView.getjPasswordSenha().setEchoChar((char)0);
            usuarioView.getjPasswordRepeteSenha().setEchoChar((char)0);
        }else{
            usuarioView.getjPasswordSenha().setEchoChar('*');
            usuarioView.getjPasswordRepeteSenha().setEchoChar('*');
        }
    }
    
    public Boolean verificaSenhas(){
        List<String> listaSenhas = getSenhas();
        if(listaSenhas.get(0).equals(listaSenhas.get(1))){
            usuarioView.getjLabelConfirmaSenhas().setForeground(Color.BLUE);
            usuarioView.getjLabelConfirmaSenhas().setText("*Senhas coincidem");
            return true;
        }else{
            usuarioView.getjLabelConfirmaSenhas().setForeground(Color.RED);
            usuarioView.getjLabelConfirmaSenhas().setText("*Senhas não coincidem");
            return false;
        }
    }
    
    public void apagaEspacoLogin(){
        usuarioView.getjTextLogin().setText(usuarioView.getjTextLogin().getText().trim());
    }
    
    private List<String> getSenhas(){
        List<String> listaSenhas = new ArrayList<>();
        String senha = "";
        String repeteSenha = "";
        char[] senhaVector = usuarioView.getjPasswordSenha().getPassword();
        char[] repeteSenhaVector = usuarioView.getjPasswordRepeteSenha().getPassword();
        for(char a : senhaVector){
            senha += a;
        } 
        for(char a : repeteSenhaVector){
            repeteSenha += a;
        } 
        listaSenhas.add(senha);
        listaSenhas.add(repeteSenha);
        return listaSenhas;
    }
}
