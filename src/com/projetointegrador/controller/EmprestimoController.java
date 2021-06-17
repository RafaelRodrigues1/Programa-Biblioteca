package com.projetointegrador.controller;

import com.projetointegrador.database.connection.DBException;
import com.projetointegrador.model.beans.EmprestimoBeans;
import com.projetointegrador.model.entities.Cliente;
import com.projetointegrador.model.entities.Emprestimo;
import com.projetointegrador.model.entities.Livro;
import com.projetointegrador.model.entities.Usuario;
import com.projetointegrador.views.EmprestimoView;
import com.projetointegrador.views.MainView;
import com.projetointegrador.views.Panes;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 * @author RafaelRodrigues1
 */
public class EmprestimoController {
    
    private EmprestimoView emprestimoView;
    private EmprestimoBeans emprestimoBeans;
    private MainView mainView;
    private Usuario usuario;

    public EmprestimoController(EmprestimoView emprestimoView) {
        this.emprestimoView = emprestimoView;
        emprestimoBeans = new EmprestimoBeans(this);
    }
    
    public void efetuaEmprestimo(){
        try{
            if(emprestimoView.getjTableClientes().getSelectedRow()>=0 &&
                    emprestimoView.getjTableLivros().getSelectedRow()>=0){
                int rowCliente = emprestimoView.getjTableClientes().getSelectedRow();
                int rowLivro = emprestimoView.getjTableLivros().getSelectedRow();
                Integer idCliente = (int) emprestimoView.getjTableClientes().getValueAt(rowCliente, 0);
                String nomeCliente = emprestimoView.getjTableClientes().getValueAt(rowCliente, 1).toString();
                String maiorIdade = emprestimoView.getjTableClientes().getValueAt(rowCliente, 2).toString();
                int quantidadeEmprestimos = (int) emprestimoView.getjTableClientes().getValueAt(rowCliente, 3);
                int codLivro = (int) emprestimoView.getjTableLivros().getValueAt(rowLivro, 0);
                String tituloLivro = emprestimoView.getjTableLivros().getValueAt(rowLivro, 1).toString();
                String restricaoEtaria = emprestimoView.getjTableLivros().getValueAt(rowLivro, 2).toString();
                if(!(restricaoEtaria.equals("Sim") && maiorIdade.equals("Não"))){
                    if(Panes.confirma("Deseja emprestar o livro: " + tituloLivro + 
                            "\nPara o cliente: " + nomeCliente + "?")!=1){
                        if(emprestimoBeans.efetuaEmprestimo(idCliente, nomeCliente, quantidadeEmprestimos,
                                codLivro, tituloLivro)){
                            Panes.mostraMsg("Empréstimo efetuado com sucesso!");
                            telaDefault();
                        }
                    }else{
                        throw new DBException("Empréstimo cancelado");
                    }
                }else{
                    throw new DBException("Livro restrito para maiores de idade");
                }
            }else{
                throw new DBException("Selecione um cliente e um livro");
            }
        }catch(DBException ex){
            Panes.mostraMsg(ex.getMessage());
        }
    }
    
    public void devolveLivro(){
        try{
            if(!emprestimoView.getjCheckBoxMostrarTodos().isSelected()){
                if(emprestimoView.getjTableEmprestimos().getSelectedRow()>=0){
                int row = emprestimoView.getjTableEmprestimos().getSelectedRow();
                int codigo = (int) emprestimoView.getjTableEmprestimos().getValueAt(row, 0);
                    if(emprestimoBeans.devolveLivro(codigo)){
                        Panes.mostraMsg("Livro devolvido com sucesso!");
                        telaDefault();
                    }else{
                        throw new DBException("Devolução cancelada");
                    }
                }else{
                    throw new DBException("Selecione um registro na tabela de empréstimos");
                }
            }else{
                throw new DBException("Desmarque a opção \"mostrar todos\"");
            }
        }catch(DBException ex){
            Panes.mostraMsg(ex.getMessage());
        }
    }
    
    public void telaDefault(){
        tabelaEmprestimoDefault();
        tabelaLivroDefault();
        tabelaClienteDefault();
    }
    
    private void tabelaEmprestimoDefault(){
        List<Emprestimo> listaEmprestimo = emprestimoBeans.listarAbertos();
        preencheTabelaEmprestimo(listaEmprestimo);
    }
    
    public void tabelaEmprestimo(){
        if(emprestimoView.getjTextPesquisaEmprestimo().getText().isBlank()){
            if(emprestimoView.getjCheckBoxMostrarTodos().isSelected()){
                List<Emprestimo> listaEmprestimo = emprestimoBeans.listarTodos();
                preencheTabelaEmprestimo(listaEmprestimo);
            }else{
                tabelaEmprestimoDefault();
            }
        }else{
            pesquisaEmprestimo();
        }
    }
    
    private void tabelaLivroDefault(){
        List<Livro> listaLivro = emprestimoBeans.listarLivro();
        preencheTabelaLivro(listaLivro);
    }
    
    private void tabelaClienteDefault(){
        List<Cliente> listaCliente = emprestimoBeans.listarCliente();
        preencheTabelaCliente(listaCliente);
    }
    
    public void pesquisaEmprestimo(){
        if(emprestimoView.getjTextPesquisaEmprestimo().getText().isBlank()){
            tabelaEmprestimo();
        }else{
            if(emprestimoView.getjCheckBoxMostrarTodos().isSelected()){
                List<Emprestimo> listaEmprestimo = emprestimoBeans
                        .pesquisaGeral(emprestimoView.getjTextPesquisaEmprestimo().getText());
                preencheTabelaEmprestimo(listaEmprestimo);
            }else{
                List<Emprestimo> listaEmprestimo = emprestimoBeans
                        .pesquisaAbertos(emprestimoView.getjTextPesquisaEmprestimo().getText());
                preencheTabelaEmprestimo(listaEmprestimo);
            }
        }
    }
    
    public void pesquisaLivro(){
        if(emprestimoView.getjTextPesquisaLivro().getText().isBlank()){
            tabelaLivroDefault();
        }else{
            List<Livro> listaLivro = emprestimoBeans
                    .pesquisaLivro(emprestimoView.getjTextPesquisaLivro().getText());
            preencheTabelaLivro(listaLivro);
        }
    }
    
    public void pesquisaCliente(){
        if(emprestimoView.getjTextPesquisaCliente().getText().isBlank()){
            tabelaClienteDefault();
        }else{
            List<Cliente> listaCliente = emprestimoBeans
                    .pesquisaCliente(emprestimoView.getjTextPesquisaCliente().getText());
            preencheTabelaCliente(listaCliente);
        }    
    }
    
    private void preencheTabelaEmprestimo(List<Emprestimo> listaEmprestimo){
        listaEmprestimo.sort((e1, e2) -> e1.getCodigo().compareTo(e2.getCodigo()));
        DefaultTableModel tableModel = (DefaultTableModel) emprestimoView.getjTableEmprestimos().getModel();
        tableModel.setNumRows(0);
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
        listaEmprestimo.forEach(emprestimo -> {
            String dataEmprestimo = sdf.format(emprestimo.getDataEmprestimo());
            String dataPrazo = sdf.format(emprestimo.getDataPrazoEntrega());
            tableModel.addRow(new Object[]{emprestimo.getCodigo(), emprestimo.getCliente().getNome(), 
                emprestimo.getCliente().getId(), emprestimo.getLivro().getTitulo(), emprestimo.getLivro().getCodigo(), 
                dataEmprestimo, dataPrazo});
        });
    }
    
    private void preencheTabelaLivro(List<Livro> listaLivro){
        DefaultTableModel tableModel = (DefaultTableModel) emprestimoView.getjTableLivros().getModel();
        tableModel.setNumRows(0);
        listaLivro.forEach(livro -> {
            String restricaoEtaria = "Não";
            if(livro.getRestricaoEtaria()){
                restricaoEtaria = "Sim";
            }
            tableModel.addRow(new Object[]{livro.getCodigo(), livro.getTitulo(), restricaoEtaria});
        });
    }
    
    private void preencheTabelaCliente(List<Cliente> listaCliente){
        LocalDate dataHoje = LocalDate.now();
        DefaultTableModel tableModel = (DefaultTableModel) emprestimoView.getjTableClientes().getModel();
        tableModel.setNumRows(0);
        listaCliente.forEach(cliente -> {
            String maiorIdade = "Sim";
            long idade = ChronoUnit.YEARS.between(cliente.getDataNascimento(), dataHoje);
            if(idade<18){
                maiorIdade = "Não";
            }
            tableModel.addRow(new Object[]{cliente.getId(), cliente.getNome(), maiorIdade, cliente.getNumeroLivros()});
        });
    }
    
    public void voltar(){
        emprestimoView.setVisible(false);
        mainView = new MainView(usuario);
        mainView.setVisible(true);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
