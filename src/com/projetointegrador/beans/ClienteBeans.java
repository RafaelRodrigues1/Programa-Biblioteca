package com.projetointegrador.beans;

import com.projetointegrador.controller.ClienteController;
import com.projetointegrador.dao.ClienteDao;
import com.projetointegrador.model.Cliente;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author RafaelRodrigues1
 */
public class ClienteBeans {
    
    private final ClienteController clienteController;
    private final ClienteDao clienteDao;
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
    public ClienteBeans(ClienteController clienteController) {
        this.clienteController = clienteController;
        clienteDao = new ClienteDao(this);
    }
    
    public Boolean cadastrar(String nome, String dataNascimento, String email, String cpf, 
            String endereco, String telefone) throws Exception{
        try{
            List<Cliente> listaCliente = listarCliente();
            Date data = sdf.parse(dataNascimento);
            Cliente cliente = new Cliente(nome, data, email, cpf, endereco, telefone, 0);
            if (listaCliente.stream().anyMatch(clientes -> (clientes.getCpf().equals(cliente.getCpf()) || 
                    clientes.getEmail().equals(cliente.getEmail())))) {
                throw new Exception("Cliente já consta no cadastro");
            }
            String cadastroCliente = cliente.getNome() + ";" + dataNascimento + ";" + cliente.getEmail() + ";" +
                    cliente.getCpf() + ";" + cliente.getEndereco() + ";" + cliente.getTelefone() + ";" + 
                        cliente.getNumeroLivros().toString(); 
                return clienteDao.cadastraCliente(cadastroCliente);
        }catch(ParseException ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public Boolean apagaCliente(String nome, String email){
        List<String> listaStr = new ArrayList<>();
        List<Cliente> listaCliente = listarCliente();
        listaCliente.removeIf(cliente -> cliente.getNome().equals(nome) && cliente.getEmail().equals(email));
        listaCliente.forEach(cliente -> {
            String data = sdf.format(cliente.getDataNascimento());
            listaStr.add(cliente.getNome() + ";" + data + ";" + cliente.getEmail() + ";" +
                cliente.getCpf() + ";" + cliente.getEndereco() + ";" + cliente.getTelefone() + ";" + 
                cliente.getNumeroLivros().toString());
        });
        return clienteDao.atualizaBDLivro(listaStr);
    } 
    
    public Boolean alteraCliente(String nome, String email, String nomeAtt, String dataNascimentoAtt, String emailAtt, 
            String enderecoAtt, String telefoneAtt){
        try{  
            Date dataNasc = sdf.parse(dataNascimentoAtt);
            List<String> listaStr = new ArrayList<>();
            List<Cliente> listaCliente = listarCliente();
            listaCliente.forEach(cliente ->{
                if(cliente.getNome().equals(nome) && cliente.getEmail().equals(email)){
                    cliente.setNome(nomeAtt);
                    cliente.setDataNascimento(dataNasc);                   
                    cliente.setEmail(emailAtt);
                    cliente.setEndereco(enderecoAtt);
                    cliente.setTelefone(telefoneAtt);
                }
            });
            listaCliente.forEach(cliente -> {
                String data = sdf.format(cliente.getDataNascimento());
                listaStr.add(cliente.getNome() + ";" + data + ";" + cliente.getEmail() + ";" +
                    cliente.getCpf() + ";" + cliente.getEndereco() + ";" + cliente.getTelefone() + ";" + 
                    cliente.getNumeroLivros().toString());
            });
            return clienteDao.atualizaBDLivro(listaStr);
        }catch(ParseException ex){
            return false;
        }
    }
    
    public final List<Cliente> pesquisaCliente(String nomePesquisa){
        //List<Cliente> listaFiltrada = new ArrayList<>();
        List<Cliente> listaCliente = listarCliente();
        List<Cliente> listaFiltrada= listaCliente.stream()
                        .filter(cliente -> cliente.getNome().toUpperCase().contains(nomePesquisa.toUpperCase()))
                        .collect(Collectors.toList());
        listaFiltrada.sort(Cliente::compareTo);
        return listaFiltrada;
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
