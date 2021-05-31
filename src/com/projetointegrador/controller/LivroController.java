package com.projetointegrador.controller;

import com.projetointegrador.model.beans.LivroBeans;
import com.projetointegrador.model.entities.Livro;
import com.projetointegrador.model.entities.Usuario;
import com.projetointegrador.views.LivroView;
import com.projetointegrador.views.MainView;
import com.projetointegrador.views.Panes;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 * @author RafaelRodrigues1
 */
public class LivroController {
    
    private final LivroView livroView;
    private final LivroBeans livroBeans;
    private Usuario usuario;

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
                        livroView.getjCheckAlugavel().isSelected(), livroView.getjCheckRestricaoEtaria().isSelected(), 
                        livroView.getjTextEdicao().getText(),
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
        try{
            if(livroView.getjTableLivros().getSelectedRow()>=0){
                Integer row = livroView.getjTableLivros().getSelectedRow();
                Integer cod = (int)livroView.getjTableLivros().getValueAt(row, 0);
                String titulo = livroView.getjTableLivros().getValueAt(row, 1).toString();
                if(Panes.confirma("Tem certeza que deseja apagar o livro:\n" + 
                        livroView.getjTableLivros().getValueAt(row, 1).toString())!=1){
                    if(livroBeans.apagaLivro(cod, titulo)){
                        Panes.mostraMsg("Livro Apagado");
                        tabelaDefault();
                    }else{
                        throw new Exception("Erro ao apagar");
                    }
                }else{            
                }
            }else{
                throw new Exception("Selecione um livro para apagar");
            }
        }catch(Exception ex){
            Panes.mostraMsg(ex.getMessage());
        }
    }
    
    public void alteraLivro(){
        try{    
            if(livroView.getjTableLivros().getSelectedRow()>=0){
                Integer row = livroView.getjTableLivros().getSelectedRow();
                Integer cod = (int)livroView.getjTableLivros().getValueAt(row, 0);
                String titulo = livroView.getjTableLivros().getValueAt(row, 1).toString();
                if(Panes.confirma("Tem certeza que deseja alterar o livro:\n" + 
                        livroView.getjTableLivros().getValueAt(row, 1).toString() + "?")!=1){
                    if(livroBeans.alteraLivro(cod, titulo, livroView.getjTextTitulo().getText(), 
                            livroView.getjTextAutor().getText(), 
                            livroView.getjComboGenero().getSelectedItem().toString(), 
                            livroView.getjTextEditora().getText(), livroView.getjTextEdicao().getText(), 
                            livroView.getjTextAnotacoes().getText(), livroView.getjCheckAlugavel().isSelected(), 
                            livroView.getjCheckRestricaoEtaria().isSelected())){
                        Panes.mostraMsg("Dados do livro alterados");
                        tabelaDefault();
                    }else{
                        throw new Exception("Erro ao alterar");
                    }
                }else{           
                }
            }else{
                throw new Exception("Selecione um livro para alterar");
            }
        }catch(Exception ex){
            Panes.mostraMsg(ex.getMessage());
        }
    }
    
    public void voltar(){
        livroView.setVisible(false);
        MainView mainView = new MainView(usuario);
        mainView.setVisible(true);
    }
    
    public void pesquisaTabela(){
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
            String alugavel = livro.getAlugavel() ? "Sim": "Não";
            String disponivel = livro.getDisponivel() ? "Sim": "Não";
            tableModel.addRow(new Object[]{livro.getCodigo(), livro.getTitulo(), livro.getAutor(),
                livro.getEditora(), livro.getEdicao(), livro.getGenero().getGenero(), 
                alugavel, livro.getAnotacoes(), disponivel});
        });
    }
    
    public void selecionaLivro(){
        Integer row = livroView.getjTableLivros().getSelectedRow();
        Integer cod = (int)livroView.getjTableLivros().getValueAt(row, 0);
        Livro livroSelecionado = livroBeans.selecionaLivro(cod);
        livroView.getjTextTitulo().setText(livroSelecionado.getTitulo());
        livroView.getjTextAutor().setText(livroSelecionado.getAutor());
        livroView.getjTextEditora().setText(livroSelecionado.getEditora());
        livroView.getjTextEdicao().setText(livroSelecionado.getEdicao());
        livroView.getjTextAnotacoes().setText(livroSelecionado.getAnotacoes());
        livroView.getjComboGenero().setSelectedItem(livroSelecionado.getGenero().getGenero());
        livroView.getjCheckAlugavel().setSelected(livroSelecionado.getAlugavel());
        livroView.getjCheckRestricaoEtaria().setSelected(livroSelecionado.getRestricaoEtaria());
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}