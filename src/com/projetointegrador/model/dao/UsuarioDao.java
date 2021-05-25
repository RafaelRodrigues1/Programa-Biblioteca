package com.projetointegrador.model.dao;

import com.projetointegrador.database.connection.DBConnection;
import com.projetointegrador.model.beans.UsuarioBeans;
import com.projetointegrador.model.beans.LoginBeans;
import com.projetointegrador.model.entities.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.time.ZoneId;
import java.util.List;
import java.util.ArrayList;

/**
 * @author RafaelRodrigues1
 */
public class UsuarioDao {
    
    private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement prepStatement = null;
    private ResultSet resultSet = null;
    private LoginBeans loginBeans;
    private UsuarioBeans cadastroUsuarioBeans;

    public UsuarioDao(LoginBeans loginBeans) {
        this.loginBeans = loginBeans;
    }

    public UsuarioDao(UsuarioBeans cadastroUsuarioBeans) {
        this.cadastroUsuarioBeans = cadastroUsuarioBeans;
    }
    
    public Boolean autenticaUsuario(Usuario usuario){
        try{
            connection = DBConnection.getConnection();
            prepStatement = connection.prepareStatement("SELECT * FROM usuario WHERE usuario = ? and senha = ? ;");
            prepStatement.setString(1, usuario.getLogin());
            prepStatement.setString(2, usuario.getSenha());
            prepStatement.execute();
            resultSet = prepStatement.getResultSet();
            return resultSet.next();
        }catch(SQLException ex){
            return false;
        }finally{
            DBConnection.closeConnection(resultSet, prepStatement, connection);
        }
    }
    
    public List<Usuario> listarUsuario(){
        try{
            List<Usuario> listaUsuario = new ArrayList<>();
            connection = DBConnection.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM usuario;");
            while(resultSet.next()){
                listaUsuario.add(new Usuario(resultSet.getString("usuario"), 
                        resultSet.getString("senha"), 
                        resultSet.getString("nome"), 
                        resultSet.getDate("data_nascimento").toLocalDate()));
            }
            return listaUsuario;
        }catch(SQLException ex){
            return null;
        }finally{
            DBConnection.closeConnection(resultSet, statement, connection);
        }
    }
    
    public Boolean cadastrar(Usuario usuario){
        try{
            long data = usuario.getDataNascimento().atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
            connection = DBConnection.getConnection();
            prepStatement = connection.prepareCall("INSERT INTO usuario "
                    + "(usuario, senha, nome, data_nascimento)"
                    + "values"
                    + "(?, ?, ?, ?);");
            prepStatement.setString(1, usuario.getLogin());
            prepStatement.setString(2, usuario.getSenha());
            prepStatement.setString(3, usuario.getNome());
            prepStatement.setDate(4, new Date(data));
            int rows = prepStatement.executeUpdate();
            return rows>0;
        }catch (SQLException ex) {
            return false;
        }finally{
            DBConnection.closeConnection(prepStatement, connection);
        }
    }
    
}
