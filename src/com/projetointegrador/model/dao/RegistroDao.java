package com.projetointegrador.model.dao;

import com.projetointegrador.database.connection.DBConnection;
import com.projetointegrador.database.connection.DBException;
import com.projetointegrador.model.entities.Registro;
import com.projetointegrador.model.entities.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

/**
 * @author RafaelRodrigues1
 */
public class RegistroDao {

    private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement prepStatement = null;
    private ResultSet resultSet = null;

    public Boolean cadastroRegistro(Registro registro) {
        try {
            long dataHora = registro.getDataHora().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
            connection = DBConnection.getConnection();
            prepStatement = connection.prepareStatement("INSERT INTO registro VALUES (? , ? , ?)");
            prepStatement.setString(1, registro.getUsuario().getLogin());
            prepStatement.setString(2, registro.getDescricao());
            prepStatement.setTimestamp(3, new Timestamp(dataHora));
            int rowsAffected = prepStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            return false;
        } finally {
            DBConnection.closeConnection(prepStatement, connection);
        }
    }
    public static void main(String[] args) {
        
    }

    public List<Registro> listar() {
        try {
            List<Registro> listaRegistro = new ArrayList<>();
            connection = DBConnection.getConnection();
            prepStatement = connection.prepareStatement(""
                    + "SELECT registro.*, usuario.senha FROM registro JOIN usuario "
                    + "WHERE registro.usuario = usuario.usuario ORDER BY registro.data;");
            resultSet = prepStatement.executeQuery();
            while (resultSet.next()) {
                Usuario usuario = new Usuario(resultSet.getString("usuario"), resultSet.getString("senha"));
                listaRegistro.add(new Registro(usuario,
                        resultSet.getString("descricao"), resultSet.getTimestamp("data").toLocalDateTime()));
            }
            return listaRegistro;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        } finally {
            DBConnection.closeConnection(resultSet, prepStatement, connection);
        }
    }
}
