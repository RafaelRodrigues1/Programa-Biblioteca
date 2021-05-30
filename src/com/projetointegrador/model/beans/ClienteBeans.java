package com.projetointegrador.model.beans;

import com.projetointegrador.controller.ClienteController;
import com.projetointegrador.model.entities.Cliente;
import com.projetointegrador.printservices.ClientePrint;
import java.time.LocalDate;
import java.util.List;
import java.time.format.DateTimeFormatter;
import com.projetointegrador.model.dao.CrudDao;
import com.projetointegrador.model.dao.DaoFactory;

/**
 *
 * @author RafaelRodrigues1
 */
public class ClienteBeans {
    
    private final ClienteController clienteController;
    private final CrudDao clienteDao;
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    public ClienteBeans(ClienteController clienteController) {
        this.clienteController = clienteController;
        clienteDao = DaoFactory.getClienteDao();
    }
    
    public Boolean cadastrar(String nome, String dataNascimento, String email, String cpf, 
            String endereco, String telefone) throws Exception{
        LocalDate data = LocalDate.parse(dataNascimento, dtf);
        Cliente cliente = new Cliente(nome, data, email, cpf, endereco, telefone);
        return clienteDao.cadastrar(cliente);
    }
    
    public Boolean apagaCliente(Integer id){
        return clienteDao.apagar(id);
    } 
    
    public Boolean alteraCliente(Integer id, String email, String nomeAtt, String dataNascimentoAtt, 
            String enderecoAtt, String telefoneAtt){
        LocalDate dataNasc = LocalDate.parse(dataNascimentoAtt, dtf);
        Cliente cliente = new Cliente(id, nomeAtt, dataNasc, email, "", enderecoAtt, telefoneAtt);
        return clienteDao.alterar(cliente);
    }
    
    public final List<Cliente> pesquisaCliente(String nomePesquisa){
        List<Cliente> listaFiltrada = clienteDao.pesquisar(nomePesquisa);
        listaFiltrada.sort(Cliente::compareTo);
        return listaFiltrada;
    }
    
    public final List<Cliente> listarCliente(){
        List<Cliente> listaCliente = clienteDao.listar();
        listaCliente.sort(Cliente::compareTo);
        return listaCliente;
    }
    
    public Cliente selecionaCliente(Integer id){
        Cliente cliente = (Cliente) clienteDao.buscarPorId(id);
        return cliente;
    }
    
    public void imprimeRelatorioCliente(){
        ClientePrint print = new ClientePrint(listarCliente());
        print.run();
    }
}
