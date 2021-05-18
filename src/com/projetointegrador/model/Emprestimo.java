package com.projetointegrador.model;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author RafaelRodrigues1
 */
public final class Emprestimo {
    
    
    private static final Map<Cliente, List<Livro>> emprestimoMap = new TreeMap<>();

    public Emprestimo(Cliente cliente, Livro livro) {
        emprestaLivro(cliente, livro);
    }
    
    public static void emprestaLivro(Cliente cliente, Livro livro){
        emprestimoMap.get(cliente).add(livro);   
    }
    
    public static void devolveLivro(Cliente cliente, Livro livro){
        emprestimoMap.get(cliente).remove(livro);
    }

    public static Map<Cliente, List<Livro>> getEmprestimoMap() {
        return emprestimoMap;
    }       
}
