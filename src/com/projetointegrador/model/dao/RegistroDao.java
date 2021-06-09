package com.projetointegrador.model.dao;

import com.projetointegrador.database.connection.DBConnection;
import com.projetointegrador.model.beans.LongDate;
import com.projetointegrador.model.entities.Registro;
import com.projetointegrador.model.entities.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author RafaelRodrigues1
 */
public abstract class RegistroDao {

    private static Connection connection = null;
    private static Statement statement = null;
    private static PreparedStatement prepStatement = null;
    private static ResultSet resultSet = null;

    public static Boolean cadastroRegistro(Registro registro) {
        try {
            long dataHora = LongDate.getLongDate(registro.getDataHora());
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

    public static List<Registro> listar() {
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
