package com.projetointegrador.controller;

import com.projetointegrador.beans.LivroBeans;
import com.projetointegrador.model.Livro;
import com.projetointegrador.views.LivroView;
import com.projetointegrador.views.MainView;
import com.projetointegrador.views.Panes;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 * @author RafaelRodrigues1
 */
public class LivroController {
    
    private LivroView livroView;
    private LivroBeans livroBeans;
    private MainView mainView;

    public LivroController(LivroView livroView) {
        this.livroView = livroView;
        livroBeans = new LivroBeans(this);
    }
    
    public void cadastrar(){
        try{
            if(!livroView.getjTextTitulo().getText().isBlank() && !livroView.getjTextAutor().getText().isBlank() &&
                    !livroView.getjComboGenero().getSelectedItem().toString().isBlank() &&
                    !livroView.getjTextEditora().getText().isBlank()){
                if(livroBeans.cadastrar(livroView.getjTextTitulo().getText(), livroView.getjTextAutor().getText()
                        , livroView.getjComboGenero().getSelectedItem().toString(), livroView.getjTextEditora().getText(),
                        livroView.getjCheckAlugavel().isSelected(), livroView.getjTextEdicao().getText(),
                        livroView.getjTextAnotacoes().getText())){
                    Panes.mostraMsg("Livro cadastrado!");
                    List<Livro> lista = livroBeans.listarLivros();
                    preencheTabela(lista);
                }else{
                    throw new Exception("Erro ao cadastrar livro");
                }
            }else{
                throw new Exception("Preencha os campos obrigatórios");
            }
        }catch(Exception ex){
            Panes.mostraMsg(ex.getMessage());
        }       
    }
    
    public void apagaLivro(){
        Integer row = livroView.getjTableLivros().getSelectedRow();
        String cod = livroView.getjTableLivros().getValueAt(row, 0).toString();
        if(Panes.confirma("Tem certeza que deseja apagar o livro:\n" + 
                livroView.getjTableLivros().getValueAt(row, 1).toString())!=1){
            if(livroBeans.apagaLivro(cod)){
                Panes.mostraMsg("Livro Apagado");
                tabelaDefault();
            }else{
                Panes.mostraMsg("Erro ao apagar");
            }
        }else{            
        }
    }
    
    public void alteraLivro(){
        Integer row = livroView.getjTableLivros().getSelectedRow();
        String cod = livroView.getjTableLivros().getValueAt(row, 0).toString();
        if(Panes.confirma("Tem certeza que deseja alterar o livro:\n" + 
                livroView.getjTableLivros().getValueAt(row, 1).toString())!=1){
            if(livroBeans.alteraLivro(cod, livroView.getjTextTitulo().getText(), livroView.getjTextAutor().getText()
                            , livroView.getjComboGenero().getSelectedItem().toString(), livroView.getjTextEditora().getText(),
                            livroView.getjCheckAlugavel().isSelected(), livroView.getjTextEdicao().getText(),
                            livroView.getjTextAnotacoes().getText())){
                Panes.mostraMsg("Livro alterado");
                tabelaDefault();
            }else{
                Panes.mostraMsg("Erro ao alterar");
            }
        }else{           
        }
    }
    
    public void voltar(){
        mainView = new MainView();
        mainView.setVisible(true);
        livroView.setVisible(false);
    }
    
    public void atualizaTabela(){
        List<Livro> lista = livroBeans.pesquisaLivro(livroView.getjTextPesquisa().getText());
        preencheTabela(lista);
    }
    
    public void tabelaDefault(){
        List<Livro> lista = livroBeans.listarLivros();
        preencheTabela(lista);
    }
    
    public void preencheTabela(List<Livro> lista){        
        DefaultTableModel tableModel = (DefaultTableModel) livroView.getjTableLivros().getModel();
        tableModel.setNumRows(0);
        lista.forEach(livro -> {
            tableModel.addRow(new Object[]{livro.getCodigo(), livro.getTitulo(), livro.getAutor(),
                livro.getEditora(), livro.getEdicao(), livro.getGenero().getGenero(), 
                livro.getAlugavel(), livro.getAnotacoes(), livro.getAlugado()});
        });
    }
    
    public void selecionaLivro(){
        Integer row = livroView.getjTableLivros().getSelectedRow();
        String cod = livroView.getjTableLivros().getValueAt(row, 0).toString();
        Livro livroSelecionado = livroBeans.selecionaLivro(cod);
        livroView.getjTextTitulo().setText(livroSelecionado.getTitulo());
        livroView.getjTextAutor().setText(livroSelecionado.getAutor());
        livroView.getjTextEditora().setText(livroSelecionado.getEditora());
        livroView.getjTextEdicao().setText(livroSelecionado.getEdicao());
        livroView.getjTextAnotacoes().setText(livroSelecionado.getAnotacoes());
        livroView.getjComboGenero().setSelectedItem(livroSelecionado.getGenero().getGenero());
        if(livroSelecionado.getAlugavel().equals("Sim")){
            livroView.getjCheckAlugavel().setSelected(true);
        }else{
        livroView.getjCheckAlugavel().setSelected(false);
        }
    }
    
    

}
