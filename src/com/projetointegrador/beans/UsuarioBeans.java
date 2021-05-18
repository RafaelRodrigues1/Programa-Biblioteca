package com.projetointegrador.beans;

import com.projetointegrador.controller.UsuarioController;
import com.projetointegrador.dao.UsuarioDao;
import java.text.SimpleDateFormat;
import java.util.List;
import com.projetointegrador.model.Usuario;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author RafaelRodrigues1
 */
public class UsuarioBeans {
    
    
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");  
    private UsuarioDao usuarioDao;
    private UsuarioController usuarioController;

    public UsuarioBeans(UsuarioController usuarioController) {
        this.usuarioController = usuarioController;
    }
    
    public Boolean cadastrar(String login, String senha, String nome, String data){
        try {
            usuarioDao = new UsuarioDao(this);
            Date dataNascimento;
            dataNascimento = sdf.parse(data);
            Usuario usuario = new Usuario(login, senha, nome, dataNascimento);           
            List<String> listaStr = usuarioDao.listarUsuario();
            List<Usuario> listaUsuario = new ArrayList<>();
            for(String str : listaStr){
                String[] linhaVect = str.split(";");
                Date dataNasc = sdf.parse(linhaVect[3]);
                listaUsuario.add(new Usuario(linhaVect[0], linhaVect[1], linhaVect[2], dataNasc));               
            }
            if(!listaUsuario.contains(usuario)){
                String cadastroUsuario = login +";"+ senha +";"+ nome +";"+ data;
                return usuarioDao.cadastrar(cadastroUsuario);
            }else{
                return false;
            }
        }catch(ParseException ex) {
            Logger.getLogger(UsuarioBeans.class.getName()).log(Level.SEVERE, null, ex);       
            return false;
        }
    }
}
