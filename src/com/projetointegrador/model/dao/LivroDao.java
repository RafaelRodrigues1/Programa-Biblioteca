package com.projetointegrador.model.dao;

import com.projetointegrador.model.dao.interfaces.LivroDaoInterface;
import com.projetointegrador.database.connection.DBConnection;
import com.projetointegrador.database.connection.DBException;
import com.projetointegrador.model.entities.Genero;
import com.projetointegrador.model.entities.Livro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author RafaelRodrigues1
 */
public class LivroDao implements LivroDaoInterface<Livro> {

    private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement prepStatement = null;
    private ResultSet resultSet = null;

    @Override
    public List<Livro> listar() {
        try {
            connection = DBConnection.getConnection();
            prepStatement = connection.prepareStatement("SELECT * FROM livro ORDER BY titulo;");
            return funcaoLista(prepStatement);
        } catch (SQLException ex) {
            return null;
        } finally {
            DBConnection.closeConnection(resultSet, prepStatement, connection);
        }
    }

    @Override
    public Boolean cadastrar(Livro livro) {
        try {
            connection = DBConnection.getConnection();
            prepStatement = connection.prepareStatement(""
                    + "INSERT INTO livro "
                    + "(titulo , autor , genero, editora ,edicao, anotacoes, alugavel ,disponivel, restricao_etaria) "
                    + "values ( ? , ? , ? , ? , ? , ? , ? , ? , ? );");
            prepStatement.setString(1, livro.getTitulo());
            prepStatement.setString(2, livro.getAutor());
            prepStatement.setString(3, livro.getGenero().getGenero());
            prepStatement.setString(4, livro.getEditora());
            prepStatement.setString(5, livro.getEdicao());
            prepStatement.setString(6, livro.getAnotacoes());
            prepStatement.setBoolean(7, livro.getAlugavel());
            prepStatement.setBoolean(8, livro.getDisponivel());
            prepStatement.setBoolean(9, livro.getRestricaoEtaria());
            int rowsAffected = prepStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            throw new DBException("Erro ao cadastrar livro");
        } finally {
            DBConnection.closeConnection(prepStatement, connection);
        }
    }

    @Override
    public Boolean alterar(Livro livro) {
        try{
            connection = DBConnection.getConnection();
            prepStatement = connection.prepareStatement(""
                    + "UPDATE livro SET titulo = ? , autor = ? , genero = ? , editora = ? , edicao = ? , "
                    + "anotacoes = ? , alugavel = ? , disponivel = ? , restricao_etaria = ? "
                    + "WHERE codigo = ? LIMIT 1;");
            prepStatement.setString(1, livro.getTitulo());
            prepStatement.setString(2, livro.getAutor());
            prepStatement.setString(3, livro.getGenero().getGenero());
            prepStatement.setString(4, livro.getEditora());
            prepStatement.setString(5, livro.getEdicao());
            prepStatement.setString(6, livro.getAnotacoes());
            prepStatement.setBoolean(7, livro.getAlugavel());
            prepStatement.setBoolean(8, livro.getDisponivel());
            prepStatement.setBoolean(9, livro.getRestricaoEtaria());
            prepStatement.setInt(10, livro.getCodigo());
            int rowsAffected = prepStatement.executeUpdate();
            return rowsAffected > 0;
        }catch(SQLException ex) {
            throw new DBException("Erro ao alterar livro");
        } finally {
            DBConnection.closeConnection(prepStatement, connection);
        }
    }

    @Override
    public Boolean apagar(Integer codigo) {
        try{
            connection = DBConnection.getConnection();
            prepStatement = connection.prepareStatement(""
                    + "DELETE FROM livro WHERE codigo = ? LIMIT 1;");
            prepStatement.setInt(1, codigo);
            int rowsAffected = prepStatement.executeUpdate();
            return rowsAffected > 0;
        }catch(SQLException ex) {
            throw new DBException("Erro ao apagar livro");
        } finally {
            DBConnection.closeConnection(prepStatement, connection);
        }
    }

    @Override
    public List<Livro> pesquisar(String pesquisa) {
        try{
            connection = DBConnection.getConnection();
            prepStatement = connection.prepareStatement("SELECT * FROM livro WHERE "
                    + "titulo LIKE ? OR autor LIKE ? OR editora LIKE ? OR genero LIKE ? OR codigo LIKE ? "
                    + "ORDER BY titulo ;");
            prepStatement.setString(1, "%"+pesquisa+"%");
            prepStatement.setString(2, "%"+pesquisa+"%");
            prepStatement.setString(3, "%"+pesquisa+"%");
            prepStatement.setString(4, "%"+pesquisa+"%");
            prepStatement.setString(5, "%"+pesquisa+"%");
            return funcaoLista(prepStatement);
        }catch(SQLException ex){
            return null;
        }finally{
            DBConnection.closeConnection(resultSet, prepStatement, connection);
        }
    }

    @Override
    public List<Livro> funcaoLista(PreparedStatement prepStatement) {
        try {
            List<Livro> listaLivros = new ArrayList<>();
            resultSet = prepStatement.executeQuery();
            while (resultSet.next()) {
                Livro livro = instanciaTipo(resultSet);
                listaLivros.add(livro);
            }
            return listaLivros;
        } catch (SQLException ex) {
            return null;
        }
    }

    @Override
    public Livro instanciaTipo(ResultSet resultSet) throws SQLException {
        Livro livro = new Livro(resultSet.getInt("codigo"), resultSet.getString("titulo"), 
                resultSet.getString("autor"), 
                Genero.valueOf(resultSet.getString("genero").toUpperCase().replace(" ", "_")),
                resultSet.getString("editora"), resultSet.getString("edicao"), 
                resultSet.getString("anotacoes"), resultSet.getBoolean("alugavel"), 
                resultSet.getBoolean("disponivel"), resultSet.getBoolean("restricao_etaria"));
        return livro;
    }

    @Override
    public Livro buscarPorId(Integer codigo) {
        try{ 
            Livro livro = null;
            connection = DBConnection.getConnection();
            prepStatement = connection.prepareStatement("SELECT * FROM livro WHERE codigo = ? ;");
            prepStatement.setInt(1, codigo);
            resultSet = prepStatement.executeQuery();
            if(resultSet.next()){
                livro = instanciaTipo(resultSet);
            }
            return livro;
        }catch(SQLException ex){
            return null;
        }finally{
            DBConnection.closeConnection(resultSet, prepStatement, connection);
        }
    }
    
    @Override
    public List<Livro> listarEmprestimo(){
        try {
            connection = DBConnection.getConnection();
            prepStatement = connection.prepareStatement("SELECT * FROM livro "
                    + "WHERE alugavel = true AND disponivel = true "
                    + "ORDER BY titulo;");
            return funcaoLista(prepStatement);
        } catch (SQLException ex) {
            return null;
        } finally {
            DBConnection.closeConnection(resultSet, prepStatement, connection);
        }
    }
    
    @Override
    public Boolean emprestaLivro(Livro livro){
        try{
            connection = DBConnection.getConnection();
            prepStatement = connection.prepareStatement("UPDATE livro "
                    + "SET disponivel = false WHERE codigo = ? and titulo = ? LIMIT 1;");
            prepStatement.setInt(1, livro.getCodigo());
            prepStatement.setString(2, livro.getTitulo());
            int rowsAffected = prepStatement.executeUpdate();
            return rowsAffected > 0;
        }catch(SQLException ex){
            ex.printStackTrace();
            return false;
        }finally{
            DBConnection.closeConnection(prepStatement, connection);
        }
    }

    @Override
    public Boolean devolveLivro(Livro livro) {
        try{
            connection = DBConnection.getConnection();
            prepStatement = connection.prepareStatement("UPDATE livro "
                    + "SET disponivel = true WHERE codigo = ? and titulo = ? LIMIT 1;");
            prepStatement.setInt(1, livro.getCodigo());
            prepStatement.setString(2, livro.getTitulo());
            int rowsAffected = prepStatement.executeUpdate();
            return rowsAffected > 0;
        }catch(SQLException ex){
            ex.printStackTrace();
            return false;
        }finally{
            DBConnection.closeConnection(prepStatement, connection);
        }
    }

    @Override
    public List<Livro> pesquisarEmprestimo(String pesquisa) {
        try{
            connection = DBConnection.getConnection();
            prepStatement = connection.prepareStatement("SELECT * FROM livro WHERE "
                    + "titulo LIKE ? AND alugavel = true AND disponivel = true "
                    + "ORDER BY titulo ;");
            prepStatement.setString(1, "%"+pesquisa+"%");
            return funcaoLista(prepStatement);
        }catch(SQLException ex){
            return null;
        }finally{
            DBConnection.closeConnection(resultSet, prepStatement, connection);
        }
    }
    
    
}
