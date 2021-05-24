package com.projetointegrador.model.beans;

import com.projetointegrador.controller.ClienteController;
import com.projetointegrador.model.dao.ClienteDao;
import com.projetointegrador.model.entities.Cliente;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author RafaelRodrigues1
 */
public class ClienteBeans {
    
    private final ClienteController clienteController;
    private final ClienteDao clienteDao;
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    public ClienteBeans(ClienteController clienteController) {
        this.clienteController = clienteController;
        clienteDao = new ClienteDao(this);
    }
    
    public Boolean cadastrar(String nome, String dataNascimento, String email, String cpf, 
            String endereco, String telefone) throws Exception{
        try{
            List<Cliente> listaCliente = listarCliente();
            LocalDate data = LocalDate.parse(dataNascimento, dtf);
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
            String data = dtf.format(cliente.getDataNascimento());
            listaStr.add(cliente.getNome() + ";" + data + ";" + cliente.getEmail() + ";" +
                cliente.getCpf() + ";" + cliente.getEndereco() + ";" + cliente.getTelefone() + ";" + 
                cliente.getNumeroLivros().toString());
        });
        return clienteDao.atualizaBDLivro(listaStr);
    } 
    
    public Boolean alteraCliente(String nome, String email, String nomeAtt, String dataNascimentoAtt, String emailAtt, 
            String enderecoAtt, String telefoneAtt){
        LocalDate dataNasc = LocalDate.parse(dataNascimentoAtt, dtf);
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
            String data = dtf.format(cliente.getDataNascimento());
            listaStr.add(cliente.getNome() + ";" + data + ";" + cliente.getEmail() + ";" +
                    cliente.getCpf() + ";" + cliente.getEndereco() + ";" + cliente.getTelefone() + ";" + 
                    cliente.getNumeroLivros().toString());
        });
        return clienteDao.atualizaBDLivro(listaStr);
    }
    
    public final List<Cliente> pesquisaCliente(String nomePesquisa){
        List<Cliente> listaCliente = listarCliente();
        List<Cliente> listaFiltrada= listaCliente.stream()
                        .filter(cliente -> cliente.getNome().toUpperCase().contains(nomePesquisa.toUpperCase()))
                        .collect(Collectors.toList());
        listaFiltrada.sort(Cliente::compareTo);
        return listaFiltrada;
    }
    
    public final List<Cliente> listarCliente(){
        List<String> listaStr = clienteDao.listarCliente();
        List<Cliente> listaClienteMetodo = new ArrayList<>();
        for(String linha : listaStr){
            String[] linhaVect = linha.split(";");
            LocalDate dataNasc = LocalDate.parse(linhaVect[1], dtf);
            Integer numLivros = Integer.parseInt(linhaVect[6]);
            listaClienteMetodo.add(new Cliente(linhaVect[0], dataNasc, linhaVect[2], linhaVect[3],
                    linhaVect[4], linhaVect[5], numLivros));
        }
        listaClienteMetodo.sort(Cliente::compareTo);
        return listaClienteMetodo;
    }
    
    public Cliente selecionaCliente(String nome, String data, String email){
        LocalDate dataNasc = LocalDate.parse(data, dtf);
        for(Cliente cliente : listarCliente()){
            if(cliente.getEmail().equals(email) && cliente.getNome().equals(nome)
                    && cliente.getDataNascimento().equals(dataNasc)){
                return cliente;
            }
        }
        return null;
    }
}
