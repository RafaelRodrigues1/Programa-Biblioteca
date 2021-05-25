package com.projetointegrador.model.dao;

import java.util.List;

/**
 * @author RafaelRodrigues1
 */
public interface Crud<T> {
    
    public List<T> listar();
    public Boolean cadastrar(T t);
    public Boolean alterar(T t);
    public Boolean apagar(T t);
    public List<T> pesquisar(String pesquisa);
}
