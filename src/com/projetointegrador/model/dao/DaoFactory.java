package com.projetointegrador.model.dao;

/**
 * @author RafaelRodrigues1
 */
public class DaoFactory {
    
    public static CrudDao getClienteDao(){
        return new ClienteDao();
    }
    
    public static CrudDao getLivroDao(){
        return new LivroDao();
    }
}
