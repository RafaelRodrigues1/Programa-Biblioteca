package com.projetointegrador.model.dao.interfaces;

/**
 * @author RafaelRodrigues1
 */
public interface LivroDaoInterface<T> extends CrudDao<T>{
    
    Boolean emprestaLivro(T t);
    Boolean devolveLivro(T t);
}
