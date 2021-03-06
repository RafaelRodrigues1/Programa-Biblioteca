package com.projetointegrador.model.dao;

import com.projetointegrador.database.connection.DBConnection;
import com.projetointegrador.model.beans.LongDate;
import com.projetointegrador.model.entities.Cliente;
import com.projetointegrador.model.entities.Emprestimo;
import com.projetointegrador.model.entities.Livro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

/**
 * @author RafaelRodrigues1
 */
public class EmprestimoDao {

    private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement prepStatement = null;
    private ResultSet resultSet = null;

    public List<Emprestimo> listarGeral() {
        try {
            connection = DBConnection.getConnection();
            prepStatement = connection.prepareCall("SELECT e.*, c.nome nome_cliente, c.email, c.quantidade_livros,"
                    + " l.titulo titulo_livro "
                    + "FROM emprestimo e JOIN (cliente c, livro l) "
                    + "ON c.id=e.id_cliente and l.codigo=e.codigo_livro;");
            return funcaoLista(prepStatement);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        } finally {
            DBConnection.closeConnection(resultSet, statement, connection);
        }
    }
    
    public List<Emprestimo> listarAbertos() {
        try {
            connection = DBConnection.getConnection();
            prepStatement = connection.prepareCall("SELECT e.*, c.nome nome_cliente, c.email, c.quantidade_livros,"
                    + " l.titulo titulo_livro "
                    + "FROM emprestimo e JOIN (cliente c, livro l) "
                    + "ON c.id=e.id_cliente and l.codigo=e.codigo_livro WHERE e.aberto = true;");
            return funcaoLista(prepStatement);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        } finally {
            DBConnection.closeConnection(resultSet, statement, connection);
        }
    }
    
    public List<Emprestimo> pesquisaGeral(String pesquisa) {
        try {
            connection = DBConnection.getConnection();
            prepStatement = connection.prepareCall("SELECT e.*, c.nome nome_cliente, c.email, c.quantidade_livros,"
                    + " l.titulo titulo_livro "
                    + "FROM emprestimo e JOIN (cliente c, livro l) "
                    + "ON c.id=e.id_cliente and l.codigo=e.codigo_livro "
                    + "WHERE c.nome LIKE ? or l.titulo LIKE ? ;");
            prepStatement.setString(1, "%"+pesquisa+"%");
            prepStatement.setString(2, "%"+pesquisa+"%");
            return funcaoLista(prepStatement);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
            return null;
        } finally {
            DBConnection.closeConnection(resultSet, statement, connection);
        }
    }
    
    public List<Emprestimo> pesquisaAbertos(String pesquisa) {
        try {
            connection = DBConnection.getConnection();
            prepStatement = connection.prepareCall("SELECT e.*, c.nome nome_cliente, c.email, c.quantidade_livros,"
                    + " l.titulo titulo_livro "
                    + "FROM emprestimo e JOIN (cliente c, livro l) "
                    + "ON c.id=e.id_cliente and l.codigo=e.codigo_livro "
                    + "WHERE (c.nome LIKE ? or l.titulo LIKE ?) AND e.aberto = true ;");
            prepStatement.setString(1, "%"+pesquisa+"%");
            prepStatement.setString(2, "%"+pesquisa+"%");
            return funcaoLista(prepStatement);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        } finally {
            DBConnection.closeConnection(resultSet, statement, connection);
        }
    }

    public Boolean efetuaEmprestimo(Emprestimo emprestimo) {
        try {
            connection = DBConnection.getConnection();
            prepStatement = connection.prepareStatement("INSERT INTO emprestimo "
                    + "(id_cliente, codigo_livro, data_emprestimo, data_prazo_entrega) VALUES "
                    + "(? , ? , ? , ?)");
            prepStatement.setInt(1, emprestimo.getCliente().getId());
            prepStatement.setInt(2, emprestimo.getLivro().getCodigo());
            prepStatement.setDate(3, new Date(LongDate.getLongDate(emprestimo.getDataEmprestimo())));
            prepStatement.setDate(4, new Date(LongDate.getLongDate(emprestimo.getDataPrazoEntrega())));
            int rowsAffected = prepStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            DBConnection.closeConnection(prepStatement, connection);
        }
    }
    
    public Emprestimo buscarPorId(Integer id){
        try{
            connection = DBConnection.getConnection();
            prepStatement = connection.prepareStatement("SELECT e.*, c.nome nome_cliente, c.email, c.quantidade_livros, "
                    + "l.titulo titulo_livro "
                     +"FROM emprestimo e JOIN (cliente c, livro l)"
                     +"ON c.id=e.id_cliente and l.codigo=e.codigo_livro WHERE e.codigo = ? ;");
            prepStatement.setInt(1, id);
            resultSet = prepStatement.executeQuery();
            resultSet.next();
            return instanciaTipo(resultSet);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        } finally {
            DBConnection.closeConnection(resultSet, prepStatement, connection);
        }
    }
    
    public Boolean fechaEmprestimo(Integer id){
        try{
            long dataEntrega = LongDate.getLongDate(LocalDate.now());
            connection = DBConnection.getConnection();
            prepStatement = connection.prepareStatement("UPDATE emprestimo "
                    + "SET aberto = false , data_entrega = ? "
                    + "WHERE codigo = ? LIMIT 1 ");
            prepStatement.setDate(1, new Date(dataEntrega));
            prepStatement.setInt(2, id);
            int rowsAffected = prepStatement.executeUpdate();
            return rowsAffected > 0;
        }catch(SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            DBConnection.closeConnection(resultSet, prepStatement, connection);
        }
    }

    private List<Emprestimo> funcaoLista(PreparedStatement prepStatement) {
        try {
            List<Emprestimo> listaEmprestimos = new ArrayList<>();
            resultSet = prepStatement.executeQuery();
            while (resultSet.next()) {
                Emprestimo emprestimo = instanciaTipo(resultSet);
                listaEmprestimos.add(emprestimo);
            }
            return listaEmprestimos;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    private Emprestimo instanciaTipo(ResultSet resultSet) throws SQLException {
        Cliente cliente = new Cliente(resultSet.getInt("id_cliente"), resultSet.getString("nome_cliente"), 
                resultSet.getString("email"), resultSet.getInt("quantidade_livros"));
        Livro livro = new Livro(resultSet.getString("titulo_livro"), resultSet.getInt("codigo_livro"));
        Emprestimo emprestimo = new Emprestimo(
                resultSet.getInt("codigo"), cliente, livro,
                resultSet.getDate("data_emprestimo").toLocalDate(),
                resultSet.getDate("data_prazo_entrega").toLocalDate());
        return emprestimo;
    }
}
