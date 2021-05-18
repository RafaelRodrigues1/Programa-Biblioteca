package com.projetointegrador.model;

/**
 * @author RafaelRodrigues1
 */
public enum Genero {
    
    ROMANCE("Romance"), DRAMA("Drama"), NOVELA("Novela"), CONTO("Conto"), 
    CRÔNICA("Crônica"), ENSAIO("Ensaio"), POESIA("Poesia"), CARTA("Carta"), 
    BIOGRAFIA("Biografia"), FICCÃO("Ficcão"), MEMÓRIAS("Memórias"), AVENTURA("Aventura"),
    LITERATURA_FANTÁSTICA("Literatura fantástica"), LITERATURA_INFANTIL("Literatura infantil"), 
    LITERATURA_NACIONAL("Literatura nacional"), TERROR("Terror"), QUADRINHOS("Quadrinhos"), 
    MATERIAL_ACADÊMICO("Material acadêmico"), FILOSOFIA("Filosofia"), CLÁSSICO("Clássico");
    
    final String genero;

    private Genero(String genero) {
        this.genero = genero;
    }

    public String getGenero() {
        return genero;
    }
}
