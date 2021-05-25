package com.projetointegrador.model.beans;

import com.projetointegrador.controller.ClienteController;
import com.projetointegrador.model.dao.ClienteDao;
import com.projetointegrador.model.entities.Cliente;
import com.projetointegrador.printservices.ClientePrint;
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
        LocalDate data = LocalDate.parse(dataNascimento, dtf);
        Cliente cliente = new Cliente(nome, data, email, cpf, endereco, telefone, 0);
        return clienteDao.cadastrar(cliente);
    }
    
    public Boolean apagaCliente(String nome, String email){
        Cliente cliente = new Cliente(nome, LocalDate.now(), email, "", "", "");
        return clienteDao.apagar(cliente);
    } 
    
    public Boolean alteraCliente(String email, String nomeAtt, String dataNascimentoAtt, 
            String enderecoAtt, String telefoneAtt){
        LocalDate dataNasc = LocalDate.parse(dataNascimentoAtt, dtf);
        Cliente cliente = new Cliente(nomeAtt, dataNasc, email, "", enderecoAtt, telefoneAtt);
        return clienteDao.alterar(cliente);
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
        List<Cliente> listaCliente = clienteDao.listar();
        listaCliente.sort(Cliente::compareTo);
        return listaCliente;
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
    
    public void imprimeRelatorioCliente(){
        ClientePrint print = new ClientePrint(listarCliente());
        print.run();
    }
}
