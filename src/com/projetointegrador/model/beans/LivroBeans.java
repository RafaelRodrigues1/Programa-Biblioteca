package com.projetointegrador.model.beans;

import com.projetointegrador.controller.LivroController;
import com.projetointegrador.model.dao.interfaces.CrudDao;
import com.projetointegrador.model.dao.DaoFactory;
import com.projetointegrador.model.dao.RegistroDao;
import com.projetointegrador.model.entities.Genero;
import com.projetointegrador.model.entities.Livro;
import com.projetointegrador.model.entities.Registro;
import java.util.List;

/**
 * @author RafaelRodrigues1
 */
public class LivroBeans {
    
    
    private final CrudDao livroDao;
    private final LivroController livroController;
    private final RegistroDao registroDao;


    public LivroBeans(LivroController livroController) {
        this.livroController = livroController;
        livroDao = DaoFactory.getLivroDao();
        registroDao = new RegistroDao();
    }
    
    public Boolean cadastrar(String titulo, String autor, String genero, String editora, 
        Boolean alugavel, Boolean restricao, String edicao, String anotacoes){
        Genero gender = Genero.valueOf(genero.toUpperCase().replace(" ", "_"));
        Livro livro = new Livro(titulo, autor, gender, editora, edicao, anotacoes, alugavel, restricao);
        if(livroDao.cadastrar(livro)){
            Registro registro = new Registro(livroController.getUsuario(), 
                    "Cadastro do livro " + titulo + " no sistema");
            registroDao.cadastroRegistro(registro);
            return true;
        }
        return false;
    }
    
    public Boolean alteraLivro(Integer cod, String titulo, String tituloAtt, String autorAtt, String generoAtt, 
            String editoraAtt, String edicaoAtt, String anotacoesAtt, Boolean alugavelAtt, Boolean restricao){
        Genero gender = Genero.valueOf(generoAtt.toUpperCase().replace(" ", "_"));
        Livro livro = new Livro(tituloAtt, autorAtt, gender, editoraAtt, edicaoAtt, 
                anotacoesAtt, alugavelAtt, restricao);
        livro.setCodigo(cod);
        if(livroDao.alterar(livro)){
            Registro registro = new Registro(livroController.getUsuario(), 
                    "Alteração dos dados do livro " + titulo + " Código: " + cod);
            registroDao.cadastroRegistro(registro);
            return true;
        }
        return false;
    }
    
    public Boolean apagaLivro(Integer cod, String titulo){
        if(livroDao.apagar(cod)){
            Registro registro = new Registro(livroController.getUsuario(), 
                    "Apagamento dos dados do livro " + titulo + " Código: " + cod);
            registroDao.cadastroRegistro(registro);
            return true;
        }
        return false;
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
