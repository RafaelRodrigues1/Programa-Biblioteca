package com.projetointegrador.model.dao;

import com.projetointegrador.database.connection.DBConnection;
import com.projetointegrador.database.connection.DBException;
import com.projetointegrador.model.entities.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

/**
 * @author RafaelRodrigues1
 */
public class ClienteDao implements CrudDao<Cliente> {
    
    private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement prepStatement = null;
    private ResultSet resultSet = null;
    
    @Override
    public List<Cliente> listar() {
        try{
            connection = DBConnection.getConnection();
            prepStatement = connection.prepareStatement("SELECT * FROM cliente ;"); // ORDER BY nome;");
            return funcaoLista(prepStatement);
        }catch(SQLException ex){
            return null;
        }finally{
            DBConnection.closeConnection(resultSet, prepStatement, connection);
        }
    }
    
    @Override
    public Boolean cadastrar(Cliente cliente) {
        try{
            long data = cliente.getDataNascimento()
                    .atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
            connection = DBConnection.getConnection();
            prepStatement = connection.prepareStatement(""
                    + "INSERT INTO cliente(nome, data_nascimento, email, cpf, endereco, telefone)"
                    + "VALUES"
                    + "(?, ?, ?, ?, ?, ?) ;");
            prepStatement.setString(1, cliente.getNome());
            prepStatement.setDate(2, new Date(data));
            prepStatement.setString(3, cliente.getEmail());
            prepStatement.setString(4, cliente.getCpf());
            prepStatement.setString(5, cliente.getEndereco());
            prepStatement.setString(6, cliente.getTelefone());
            int rowsAffected = prepStatement.executeUpdate();
            return rowsAffected > 0;
        }catch(SQLException ex){
            throw new DBException("Cliente já consta no cadastro");
        }finally{
            DBConnection.closeConnection(prepStatement, connection);
        }
    }
    
     @Override
    public Boolean alterar(Cliente cliente) {
        try{
            long data = cliente.getDataNascimento()
                    .atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
            connection = DBConnection.getConnection();
            prepStatement = connection.prepareStatement("UPDATE cliente SET nome = ? , email = ? ,"
                    + "data_nascimento = ? , endereco = ? , telefone = ? WHERE id = ? LIMIT 1;");
            prepStatement.setString(1, cliente.getNome());
            prepStatement.setString(2, cliente.getEmail());
            prepStatement.setDate(3, new Date(data));
            prepStatement.setString(4, cliente.getEndereco());
            prepStatement.setString(5, cliente.getTelefone());
            prepStatement.setInt(6, cliente.getId());
            int rowsAffected = prepStatement.executeUpdate();
            return rowsAffected > 0;
        }catch(SQLException ex){
            return false;
        }finally{
            DBConnection.closeConnection(resultSet, prepStatement, connection);
        }
    }

    @Override
    public Boolean apagar(Integer id) {
        try{
            connection = DBConnection.getConnection();
            prepStatement = connection.prepareStatement(""
                    + "DELETE FROM cliente WHERE id = ? LIMIT 1;");
            prepStatement.setInt(1, id);
            int rowsAffected = prepStatement.executeUpdate();
            return rowsAffected > 0; 
        }catch(SQLException ex){
            return false;
        }finally{
            DBConnection.closeConnection(resultSet, prepStatement, connection);
        }
    }
    
    @Override
    public List<Cliente> pesquisar(String pesquisa) {
        try{
            connection = DBConnection.getConnection();
            prepStatement = connection.prepareStatement("SELECT * FROM cliente WHERE nome LIKE ? ;");
            prepStatement.setString(1, "%"+pesquisa+"%");
            return funcaoLista(prepStatement);
        }catch(SQLException ex){
            return null;
        }finally{
            DBConnection.closeConnection(resultSet, prepStatement, connection);
        }
    }
    
    @Override
    public List<Cliente> funcaoLista(PreparedStatement prepStatement){
        try{
            List<Cliente> listaClientes = new ArrayList<>();
            resultSet = prepStatement.executeQuery();
            while(resultSet.next()){
                    Cliente cliente = instanciaTipo(resultSet);
                    listaClientes.add(cliente);
                }
                return listaClientes;
        }catch(SQLException ex){
            return null;
        }
    }
    
    @Override
    public Cliente instanciaTipo(ResultSet resultSet) throws SQLException{
        Cliente cliente = new Cliente(resultSet.getInt("id"), resultSet.getString("nome"), 
                            resultSet.getDate("data_nascimento").toLocalDate(), 
                            resultSet.getString("email"), resultSet.getString("cpf"), 
                            resultSet.getString("endereco"), resultSet.getString("telefone"), 
                            resultSet.getInt("quantidade_livros"), resultSet.getBoolean("liberado"));
        return cliente;
    }
    
    @Override
    public Cliente buscarPorId(Integer id){
        try{
            Cliente cliente = null;
            connection = DBConnection.getConnection();
            prepStatement = connection.prepareStatement("SELECT * FROM cliente WHERE id = ? ;");
            prepStatement.setInt(1, id);
            resultSet = prepStatement.executeQuery();
            if(resultSet.next()){
                cliente = instanciaTipo(resultSet);
            }
            return cliente;
        }catch(SQLException ex){
            return null;
        }finally{
            DBConnection.closeConnection(resultSet, prepStatement, connection);
        }
    }
}
