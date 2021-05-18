package com.projetointegrador.dao;

import com.projetointegrador.beans.UsuarioBeans;
import com.projetointegrador.beans.LoginBeans;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author RafaelRodrigues1
 */
public class UsuarioDao {
    
    private final String path = "C:\\Users\\RAFAEL\\Documents\\NetBeansProjects\\AppProjetoBiblioteca\\DataBase\\Usuarios.txt";
    private LoginBeans loginBeans;
    private UsuarioBeans cadastroUsuarioBeans;
    private final File file = new File(path);

    public UsuarioDao(LoginBeans loginBeans) {
        this.loginBeans = loginBeans;
    }

    public UsuarioDao(UsuarioBeans cadastroUsuarioBeans) {
        this.cadastroUsuarioBeans = cadastroUsuarioBeans;
    }
    
    public List<String> listarUsuario(){
        List<String> listaStr = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String linha;
            while((linha = br.readLine()) != null){
                listaStr.add(linha);               
            }
            return listaStr;
        }catch(IOException ex){
            return null;
        }
    }
    
    public Boolean cadastrar(String cadastroUsuario){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))){
            bw.write(cadastroUsuario);
            bw.newLine();
            return true;
        }catch (IOException ex) {
            return false;
        }
    }
    
}
