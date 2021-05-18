package com.projetointegrador.beans;

import com.projetointegrador.controller.LoginController;
import com.projetointegrador.dao.UsuarioDao;
import com.projetointegrador.views.MainView;
import com.projetointegrador.model.Usuario;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.BiPredicate;

/**
 * @author RafaelRodrigues1
 */
public class LoginBeans {
    
    
    private LoginController loginController;
    private MainView mainView;
    private UsuarioDao usuarioDao;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    
    public LoginBeans(LoginController loginController) {
        this.loginController = loginController;
    }
    
    public Usuario entrar(String login, String senha){
        try{
            BiPredicate<String, String> bp = (a, b) -> a.equals(b);//@FunctionalInterface, expressão lambda
            usuarioDao = new UsuarioDao(this);
            List<String> lista = usuarioDao.listarUsuario();
            List<Usuario> listaUsuario = new ArrayList<>();
            if(!lista.isEmpty()){
                for(String linha: lista){
                    String[] linhaVect = linha.split(";");
                    Date data = sdf.parse(linhaVect[3]);
                    listaUsuario.add(new Usuario(linhaVect[0], linhaVect[1], linhaVect[2], data));
                }
                for(Usuario usuario : listaUsuario){
                    if(usuario.getLogin().hashCode()==login.hashCode() && 
                            usuario.getSenha().hashCode()==senha.hashCode()){
                        if(bp.test(usuario.getLogin(), login) && bp.test(usuario.getSenha(), senha)){
                            return usuario;     //Usando a interface funcional
                        }
                    }
                }
                throw new Exception();
            }else{
                throw new Exception();
            }
        }catch(ParseException ex){
            return null;
        }catch(Exception ex){
            return null;
        } 
    }
}
