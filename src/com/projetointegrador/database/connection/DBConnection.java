package com.projetointegrador.database.connection;

import java.sql.Connection;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 * @author RafaelRodrigues1
 */
public class DBConnection {
    
    
    public static Connection getConnection(){
        try{
            Properties prop = getProperties();
            String url = getProperties().getProperty("dburl");
            Connection conn = DriverManager.getConnection(url, prop);
            return conn;
        }catch(SQLException ex){
            throw new DBException(ex.getMessage());
        }
    }
    
    public static void closeConnection(Connection con){
        try{    
            if(con != null){
                con.close();
            }
        }catch(SQLException ex){
            throw new DBException(ex.getMessage());
        }
    }
    
    public static void closeConnection(Statement sta, Connection con){
        try{    
            if(sta != null){
                sta.close();
            }
        }catch(SQLException ex){
            throw new DBException(ex.getMessage());
        }
        closeConnection(con);
    }
    
    public static void closeConnection(ResultSet res, Statement sta, Connection con){
        try{    
            if(res != null){
                res.close();
            }
        }catch(SQLException ex){
            throw new DBException(ex.getMessage());
        }
        closeConnection(sta, con);
    }
    
    private static Properties getProperties(){
        try(FileInputStream fis = new FileInputStream("src\\com\\projetointegrador\\database\\connection\\DBConnection.properties")){
            Properties prop = new Properties();
            prop.load(fis);
            return prop;
        }catch(IOException ex){
            throw new DBException(ex.getMessage());
        }
    }
}
