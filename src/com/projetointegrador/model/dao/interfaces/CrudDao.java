package com.projetointegrador.model.dao.interfaces;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author RafaelRodrigues1
 */
public interface CrudDao<T> {
    
    List<T> listar();
    Boolean cadastrar(T t);
    Boolean alterar(T t);
    Boolean apagar(Integer t);
    List<T> pesquisar(String pesquisa);
    List<T> funcaoLista(PreparedStatement prepStatement)throws SQLException;
    T instanciaTipo(ResultSet resultSet)throws SQLException;
    T buscarPorId(Integer busca);
    List<T> listarEmprestimo();
    List<T> pesquisarEmprestimo(String pesquisa);
}
