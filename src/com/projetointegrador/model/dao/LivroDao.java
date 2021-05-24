package com.projetointegrador.model.dao;

import com.projetointegrador.model.beans.LivroBeans;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author RafaelRodrigues1
 */
public class LivroDao {
    
    private final String path = "DataBase\\Livros.txt";
    private final File file = new File(path);
    LivroBeans livroBeans;

    public LivroDao(LivroBeans livroBeans) {
        this.livroBeans = livroBeans;
    }
    
    public List<String> listarLivros(){
        List<String> listaLivro = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String linha;
            while((linha=br.readLine())!=null){
                listaLivro.add(linha);
            }
            return listaLivro;
        }catch(IOException ex){
            return null;
        }
    }
    
    public Boolean cadastraLivro(String cadastroLivro){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))){
            bw.write(cadastroLivro);
            bw.newLine();
            return true;
        }catch(IOException ex){
            return false;
        }
    }
    
    public Boolean atualizaBDLivro(List<String> listaLivro){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))){
            for(String linha : listaLivro){
                bw.write(linha);
                bw.newLine();                
            }
            return true;
        }catch(IOException ex){
            return false;
        }
    }
}
