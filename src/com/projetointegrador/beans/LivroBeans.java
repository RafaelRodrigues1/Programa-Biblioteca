package com.projetointegrador.beans;

import com.projetointegrador.controller.LivroController;
import com.projetointegrador.dao.LivroDao;
import com.projetointegrador.model.Genero;
import com.projetointegrador.model.Livro;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author RafaelRodrigues1
 */
public class LivroBeans {
    
    
    private LivroDao livroDao;
    private LivroController livroController;


    public LivroBeans(LivroController livroController) {
        this.livroController = livroController;
        livroDao = new LivroDao(this);
    }
    
    public Boolean cadastrar(String titulo, String autor, String genero, String editora, 
        Boolean alugavel, String edicao, String anotacoes){
        Genero gender = Genero.valueOf(genero.toUpperCase().replace(" ", "_"));
        Livro livro = new Livro(titulo, autor, gender, editora, alugavel);
        if(!edicao.isBlank()){
            livro.setEdicao(edicao);
        }
        if(!anotacoes.isBlank()){
            livro.setAnotacoes(anotacoes);
        }
        if(!listarLivros().contains(livro)){
        String livroStr = livro.getTitulo() + ";" + livro.getAutor() + ";" + livro.getGenero() + ";" +  
                livro.getEditora() + ";" + livro.getEdicao() + ";" + livro.getAnotacoes() + ";" + 
                livro.getAlugavel() + ";" + livro.getAlugado() + ";" + 
                livro.getCodigo().toString();
            return livroDao.cadastraLivro(livroStr);
        }else{
            return false;           
        }       
    }
    
    public Boolean apagaLivro(String cod){
        List<String> listaStr = new ArrayList<>();
        List<Livro> listaLivro = listarLivros();
        listaLivro.sort((a, b) -> a.getCodigo().compareTo(b.getCodigo()));
        listaLivro.removeIf(livro -> livro.getCodigo().toString().equals(cod));
        listaLivro.forEach(livro -> {
            listaStr.add(livro.getTitulo() + ";" + livro.getAutor() + ";" + livro.getGenero() + ";" + 
                    livro.getEditora() + ";" + livro.getEdicao() + ";" + livro.getAnotacoes() + ";" + 
                    livro.getAlugavel() + ";" + livro.getAlugado() + ";" + livro.getCodigo().toString());
        });
        return livroDao.atualizaBDLivro(listaStr);
    }
    
    public Boolean alteraLivro(String cod, String tituloAtt, String autorAtt, String generoAtt, String editoraAtt, 
            Boolean alugavelAtt, String edicaoAtt, String anotacoesAtt){
        List<String> listaStr = new ArrayList<>();
        List<Livro> listaLivro = listarLivros();
        Genero gender = Genero.valueOf(generoAtt.toUpperCase().replace(" ", "_"));
        listaLivro.sort((a, b) -> a.getCodigo().compareTo(b.getCodigo()));//Expressão lambda
        listaLivro.forEach(livro -> {
            if(livro.getCodigo().toString().equals(cod)){
                livro.setTitulo(tituloAtt);
                livro.setAutor(autorAtt);
                livro.setGenero(gender);
                livro.setEditora(editoraAtt);
                livro.setAlugavel(alugavelAtt);
                livro.setEdicao(edicaoAtt);
                livro.setAnotacoes(anotacoesAtt);
            }
        }); 
        listaLivro.forEach(livro -> {
            listaStr.add(livro.getTitulo() + ";" + livro.getAutor() + ";" + livro.getGenero() + ";" + 
                    livro.getEditora() + ";" + livro.getEdicao() + ";" + livro.getAnotacoes() + ";" + 
                    livro.getAlugavel() + ";" + livro.getAlugado() + ";" + livro.getCodigo().toString());
        });
        return livroDao.atualizaBDLivro(listaStr);
    }
    
    public List<Livro> pesquisaLivro(String pesquisa){
        //List<Livro> listaFiltrada = new ArrayList<>();
        List<Livro> listaLivro = listarLivros();
        List<Livro> listaFiltrada = listaLivro.stream()
                        .filter((livro) -> (livro.getTitulo().toUpperCase().contains(pesquisa.toUpperCase()) || 
                                    livro.getAutor().toUpperCase().contains(pesquisa.toUpperCase()) || 
                                    livro.getEditora().toUpperCase().contains(pesquisa.toUpperCase()) || 
                                    livro.getGenero().getGenero().toUpperCase().contains(pesquisa.toUpperCase())))
                        .collect(Collectors.toList());
                //forEachOrdered(livro -> {
               // listaFiltrada.add(livro);
          //  });
        Collections.sort(listaFiltrada);
        return listaFiltrada;
    }
    
    public final List<Livro> listarLivros(){
        List<String> listaStr = livroDao.listarLivros();
        List<Livro> listaLivroMetodo = new ArrayList<>();
        for(String linha : listaStr){
            String[] linhaVect = linha.split(";");
            Boolean alugav=false, alugad=false;
            if(linhaVect[6].equals("Sim")){
                alugav=true;
            }
            if(linhaVect[7].equals("Sim")){
                alugad=true;
            }
            Integer cod = Integer.parseInt(linhaVect[8]);
            listaLivroMetodo.add(new Livro(linhaVect[0], linhaVect[1], Genero.valueOf(linhaVect[2]), linhaVect[3], linhaVect[4], 
                    linhaVect[5], alugav, alugad, cod));
        }
        Collections.sort(listaLivroMetodo);
        return listaLivroMetodo;
    }
    
    public Livro selecionaLivro(String cod){
        for(Livro livro : listarLivros()){
            if(livro.getCodigo().toString().equals(cod)){
                return livro;
            }
        }
        return null;
    }    
}
