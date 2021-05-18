package com.projetointegrador.beans;

import com.projetointegrador.controller.ClienteController;
import com.projetointegrador.dao.ClienteDao;
import com.projetointegrador.model.Cliente;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author RafaelRodrigues1
 */
public class ClienteBeans {
    
    private ClienteController clienteController;
    private ClienteDao clienteDao;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
    public ClienteBeans(ClienteController clienteController) {
        this.clienteController = clienteController;
        clienteDao = new ClienteDao(this);
    }
    
    public Boolean cadastrar(String nome, String dataNascimento, String email, String cpf, 
            String endereco, String telefone){
        try{
            List<Cliente> listaCliente = listarCliente();
            Date data = sdf.parse(dataNascimento);
            Cliente cliente = new Cliente(nome, data, email, cpf, endereco, telefone, 0);
            if(!listaCliente.contains(cliente)){
                String cadastroCliente = cliente.getNome() + ";" + dataNascimento + ";" + cliente.getEmail() + ";" +
                    cliente.getCpf() + ";" + cliente.getEndereco() + ";" + cliente.getTelefone() + ";" + 
                        cliente.getNumeroLivros().toString(); 
                return clienteDao.cadastraCliente(cadastroCliente);
            }else{
                return false;
            }
        }catch(ParseException ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    
    
    
    
    public final List<Cliente> listarCliente(){
        try{
            List<String> listaStr = clienteDao.listarCliente();
            List<Cliente> listaClienteMetodo = new ArrayList<>();
            for(String linha : listaStr){
                String[] linhaVect = linha.split(";");
                Date dataNasc = sdf.parse(linhaVect[1]);
                Integer numLivros = Integer.parseInt(linhaVect[6]);
                listaClienteMetodo.add(new Cliente(linhaVect[0], dataNasc, linhaVect[2], linhaVect[3],
                        linhaVect[4], linhaVect[5], numLivros));            
            }
            listaClienteMetodo.sort(Cliente::compareTo);
            return listaClienteMetodo;
        }catch(ParseException ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    public Cliente selecionaCliente(String nome, String data, String email){
        try{    
            Date dataNasc = sdf.parse(data);
            for(Cliente cliente : listarCliente()){
                if(cliente.getEmail().equals(email) && cliente.getNome().equals(nome) 
                        && cliente.getDataNascimento().equals(dataNasc)){
                    return cliente;
                }
            }
        }catch(ParseException ex){
            return null;
        }
        return null;
    }
}
