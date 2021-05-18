package com.projetointegrador.model;

import java.util.Comparator;

/**
 * @author RafaelRodrigues1
 */
public class ComparatorLivros implements Comparator<Livro> {

    @Override
    public int compare(Livro o1, Livro o2) {
        return o1.getCodigo().compareTo(o2.getCodigo());
    }
    
}
