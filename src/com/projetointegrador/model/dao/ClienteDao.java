package com.projetointegrador.model.dao;

import com.projetointegrador.model.beans.ClienteBeans;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author RafaelRodrigues1
 */
public class ClienteDao {
    
    private final String path = "DataBase\\Clientes.txt";
    private final File file = new File(path);
    ClienteBeans clienteBeans;

    public ClienteDao(ClienteBeans clienteBeans) {
        this.clienteBeans = clienteBeans;
    }
    
    public List<String> listarCliente(){
        List<String> listaCliente = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String linha;
            while((linha = br.readLine())!=null){
                listaCliente.add(linha);
            }
            return listaCliente;
        }catch(Exception ex){
            return null;
        }
    }
    
    public Boolean cadastraCliente(String cadastroCliente){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))){
            bw.write(cadastroCliente);
            bw.newLine();
            return true;
        }catch(Exception ex){
            return false;
        }
    }
    
    public Boolean atualizaBDLivro(List<String> listaCliente){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))){
            for (String linha : listaCliente) {
                bw.write(linha);
                bw.newLine();
            }
            return true;
        }catch(Exception ex){
            return false;
        }
    }
}
