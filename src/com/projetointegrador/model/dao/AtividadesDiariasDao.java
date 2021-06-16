package com.projetointegrador.model.dao;

import com.projetointegrador.database.connection.DBConnection;
import com.projetointegrador.model.beans.LongDate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Date;

/**
 * @author RafaelRodrigues1
 */
public class AtividadesDiariasDao {
    
    private static Connection connection = null;
    private static PreparedStatement prepStatement = null;
    
    public static Boolean insereDia(){
        try{
            long data = LongDate.getLongDate(LocalDate.now());
            connection = DBConnection.getConnection();
            prepStatement = connection.prepareStatement(""
                    + "INSERT INTO atividades_diarias(data) VALUES (?);");
            prepStatement.setDate(1, new Date(data));
            int rowsAffected = prepStatement.executeUpdate();
            System.out.println(rowsAffected);
            return rowsAffected == 1;
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            return false;
        }finally{
            DBConnection.closeConnection(prepStatement, connection);
        }
    }
}
