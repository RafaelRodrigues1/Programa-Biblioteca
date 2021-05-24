package com.projetointegrador.model.dao;

import com.database.connection.DBConnection;
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
    
    private Connection con = null;
    private Statement sta = null;
    private PreparedStatement pst = null;
    private ResultSet res = null;
    private LoginBeans loginBeans;
    private UsuarioBeans cadastroUsuarioBeans;

    public UsuarioDao(LoginBeans loginBeans) {
        this.loginBeans = loginBeans;
    }

    public UsuarioDao(UsuarioBeans cadastroUsuarioBeans) {
        this.cadastroUsuarioBeans = cadastroUsuarioBeans;
    }
    
    public List<Usuario> listarUsuario(){
        try{
            List<Usuario> listaUsuario = new ArrayList<>();
            con = DBConnection.getConnection();
            sta = con.createStatement();
            res = sta.executeQuery("SELECT * FROM usuario;");
            while(res.next()){
                listaUsuario.add(new Usuario(res.getString("usuario"), 
                        res.getString("senha"), 
                        res.getString("nome"), 
                        res.getDate("data_nascimento").toLocalDate()));
            }
            return listaUsuario;
        }catch(SQLException ex){
            return null;
        }finally{
            DBConnection.closeConnection(res, sta, con);
        }
    }
    
    public Boolean cadastrar(Usuario usuario){
        try{
            long data = usuario.getDataNascimento().atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
            con = DBConnection.getConnection();
            pst = con.prepareCall("INSERT INTO usuario "
                    + "(usuario, senha, nome, data_nascimento)"
                    + "values"
                    + "(?, ?, ?, ?);");
            pst.setString(1, usuario.getLogin());
            pst.setString(2, usuario.getSenha());
            pst.setString(3, usuario.getNome());
            pst.setDate(4, new Date(data));
            int rows = pst.executeUpdate();
            return rows>0;
        }catch (SQLException ex) {
            return false;
        }finally{
            DBConnection.closeConnection(pst, con);
        }
    }
    
}
