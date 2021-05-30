package com.projetointegrador.model.beans;

import com.projetointegrador.controller.LivroController;
import com.projetointegrador.model.dao.CrudDao;
import com.projetointegrador.model.dao.DaoFactory;
import com.projetointegrador.model.dao.LivroDao;
import com.projetointegrador.model.entities.Genero;
import com.projetointegrador.model.entities.Livro;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author RafaelRodrigues1
 */
public class LivroBeans {
    
    
    private CrudDao livroDao;
    private LivroController livroController;


    public LivroBeans(LivroController livroController) {
        this.livroController = livroController;
        livroDao = DaoFactory.getLivroDao();
    }
    
    public Boolean cadastrar(String titulo, String autor, String genero, String editora, 
        Boolean alugavel, Boolean restricao, String edicao, String anotacoes){
        Genero gender = Genero.valueOf(genero.toUpperCase().replace(" ", "_"));
        Livro livro = new Livro(titulo, autor, gender, editora, edicao, anotacoes, alugavel, restricao);
        return livroDao.cadastrar(livro);
    }
    
    public Boolean alteraLivro(Integer cod, String tituloAtt, String autorAtt, String generoAtt, 
            String editoraAtt, String edicaoAtt, String anotacoesAtt, Boolean alugavelAtt, Boolean restricao){
        Genero gender = Genero.valueOf(generoAtt.toUpperCase().replace(" ", "_"));
        Livro livro = new Livro(tituloAtt, autorAtt, gender, editoraAtt, edicaoAtt, 
                anotacoesAtt, alugavelAtt, restricao);
        livro.setCodigo(cod);
        return livroDao.alterar(livro);
    }
    
    public Boolean apagaLivro(Integer cod){
        return livroDao.apagar(cod);
    }

    public List<Livro> pesquisaLivro(String pesquisa){
        List<Livro> listaFiltrada = livroDao.pesquisar(pesquisa);
        return listaFiltrada;
    }
    
    public final List<Livro> listarLivros(){
        return livroDao.listar();
    }
    
    public Livro selecionaLivro(Integer cod){
        for(Livro livro : listarLivros()){
            if(livro.getCodigo().equals(cod)){
                return livro;
            }
        }
        return null;
    }    
}
