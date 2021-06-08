package com.projetointegrador.model.dao.interfaces;

/**
 * @author RafaelRodrigues1
 */
public interface ClienteDaoInterface<T> extends CrudDao<T> {
    
    Boolean desautorizaCliente(T t);
    Boolean autorizaCliente(T t);
    Boolean tomaLivroEmprestado(T t);
    Boolean devolveLivro(T t);
    Integer numeroLivros(T t);
}
