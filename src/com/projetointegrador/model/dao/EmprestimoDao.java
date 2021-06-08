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

    public List<Emprestimo> listar() {
        try {
            connection = DBConnection.getConnection();
            prepStatement = connection.prepareCall("SELECT e.*, c.nome nome_cliente, c.quantidade_livros,"
                    + " l.titulo titulo_livro "
                    + "FROM emprestimo e JOIN (cliente c, livro l) "
                    + "ON c.id=e.id_cliente and l.codigo=e.codigo_livro WHERE aberto = true;");
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
            prepStatement = connection.prepareStatement("SELECT e.*, c.nome nome_cliente, c.quantidade_livros, "
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
    
    /**
     * 
     * @param cliente
     * @return true se o cliente estiver com 3 ou mais livros
     */  //NÃO USADO
    public Boolean verificaQuantidadeLivrosCliente(Cliente cliente) { 
        try {
            connection = DBConnection.getConnection();
            prepStatement = connection.prepareStatement("SELECT * from emprestimo "
                    + "WHERE id_cliente = ? AND aberto = true ;");
            prepStatement.setInt(1, cliente.getId());
            resultSet = prepStatement.executeQuery();
            int rowsAffected = 0;
            while (resultSet.next()) {
                rowsAffected++;
            }
            return rowsAffected >= 3; //Quantidade máxima de livros
        } catch (SQLException ex) {
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
                resultSet.getInt("quantidade_livros"));
        Livro livro = new Livro(resultSet.getString("titulo_livro"), resultSet.getInt("codigo_livro"));
        Emprestimo emprestimo = new Emprestimo(
                resultSet.getInt("codigo"), cliente, livro,
                resultSet.getDate("data_emprestimo").toLocalDate(),
                resultSet.getDate("data_prazo_entrega").toLocalDate());
        return emprestimo;
    }
}
