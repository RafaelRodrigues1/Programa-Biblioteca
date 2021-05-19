package com.projetointegrador.controller;

import com.projetointegrador.beans.ClienteBeans;
import com.projetointegrador.model.Cliente;
import com.projetointegrador.views.ClienteView;
import com.projetointegrador.views.Panes;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.table.DefaultTableModel;

/**
 * @author RafaelRodrigues1
 */
public class ClienteController {
    
    
    private ClienteView clienteView;
    private ClienteBeans clienteBeans;
    
    
    public ClienteController(ClienteView clienteView) {
        this.clienteView = clienteView;
        clienteBeans = new ClienteBeans(this);
    }
    
    public void cadastrar(){
        try{
            if(!clienteView.getjTextNome().getText().isBlank() && !clienteView.getjFormattedTextData().getText().isBlank()
                && !clienteView.getjTextEmail().getText().isBlank() && !clienteView.getjTextEndereco().getText().isBlank() 
                    && !clienteView.getjFormattedTextTelefone().getText().isBlank()){
                if(verificaPadrao()){
                    if(clienteBeans.cadastrar(clienteView.getjTextNome().getText(), clienteView.getjFormattedTextData().getText(), 
                            clienteView.getjTextEmail().getText(), clienteView.getjFormattedTextCPF().getText(), 
                            clienteView.getjTextEndereco().getText(), clienteView.getjFormattedTextTelefone().getText())){
                        Panes.mostraMsg("Cliente cadastrado!");
                        esvaziaCampos();
                        List<Cliente> listaCliente = clienteBeans.listarCliente();
                        preencheTabela(listaCliente);
                    }else{
                        throw new Exception("Erro ao cadastrar cliente");
                    }
                }else{
                    throw new Exception("Dados em formato inválido");
                }
            }else{
                throw new Exception("Preencha os campos obrigatórios");
            }
        }catch(Exception ex){
            Panes.mostraMsg(ex.getMessage());
        }
    }
    
    public void alteraCliente(){
        try{
            if(clienteView.getjTableClientes().getSelectedRow()>=0){
                Integer row = clienteView.getjTableClientes().getSelectedRow();
                String nome = clienteView.getjTableClientes().getValueAt(row, 0).toString();
                String email = clienteView.getjTableClientes().getValueAt(row, 2).toString();
                if(Panes.confirma("Certeza que deseja alterar o cliente: " + nome + "?")!=1){
                    if(verificaPadrao("Cpf não altera")){   
                        if(clienteBeans.alteraCliente(nome, email, clienteView.getjTextNome().getText(), 
                                clienteView.getjFormattedTextData().getText(), clienteView.getjTextEmail().getText(),
                                clienteView.getjTextEndereco().getText(), clienteView.getjFormattedTextTelefone().getText())){
                        Panes.mostraMsg("Dados do cliente alterados");
                        tabelaDefault();
                        }else{
                            throw new Exception("Erro ao alterar");
                        }
                    }else{
                        throw new Exception("Dados em formato inválido");
                    }
                }else{           
                }
            }else{
                throw new Exception("Selecione um cliente");
            }
        }catch(Exception ex){
            Panes.mostraMsg(ex.getMessage());
        }
    }
    
    public Boolean verificaPadrao(){
        Pattern email = Pattern.compile("\\S+@{1}[a-z A-Z]+(.com|.com.br)");
        Matcher matchEmail = email.matcher(clienteView.getjTextEmail().getText());
        Pattern cpf = Pattern.compile("\\d{3}.\\d{3}.\\d{3}.\\d{2}");
        Matcher matchCpf = cpf.matcher(clienteView.getjFormattedTextCPF().getText());
        Pattern data = Pattern.compile("\\d{2}/\\d{2}/\\d{4}");
        Matcher matchData = data.matcher(clienteView.getjFormattedTextData().getText());
        Pattern telefone = Pattern.compile("\\(\\d{2}\\)\\d{1} \\d{4}-\\d{4}");
        Matcher matchTelefone = telefone.matcher(clienteView.getjFormattedTextTelefone().getText());
        return matchEmail.find() && matchCpf.find() && matchData.find() && matchTelefone.find();
    }
    
    public Boolean verificaPadrao(String cpf){
        Pattern email = Pattern.compile("\\S+@{1}[a-z A-Z]+(.com|.com.br)");
        Matcher matchEmail = email.matcher(clienteView.getjTextEmail().getText());
        Pattern data = Pattern.compile("\\d{2}/\\d{2}/\\d{4}");
        Matcher matchData = data.matcher(clienteView.getjFormattedTextData().getText());
        Pattern telefone = Pattern.compile("\\(\\d{2}\\)\\d{1} \\d{4}-\\d{4}");
        Matcher matchTelefone = telefone.matcher(clienteView.getjFormattedTextTelefone().getText());
        return matchEmail.find() && matchData.find() && matchTelefone.find();
    }
    
    public void voltar(){
        clienteView.setVisible(false);
    }
    
    public void pesquisaCliente(){
        List<Cliente> listaCliente = clienteBeans.pesquisaCliente(clienteView.getjTextPesquisaNome().getText());
        preencheTabela(listaCliente);
    }
    
    public void tabelaDefault(){
        List<Cliente> listaCliente = clienteBeans.listarCliente();
        preencheTabela(listaCliente);
    }
    
    public void preencheTabela(List<Cliente> listaCliente){
        DefaultTableModel tableModel = (DefaultTableModel) clienteView.getjTableClientes().getModel();
        tableModel.setNumRows(0);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");        
        listaCliente.forEach(cliente ->{
            String data = sdf.format(cliente.getDataNascimento());
            tableModel.addRow(new Object[]{cliente.getNome(), data, cliente.getEmail(), 
                cliente.getTelefone(), cliente.getNumeroLivros()});
            });
    }
    
    public void esvaziaCampos(){
        clienteView.getjTextNome().setText("");
        clienteView.getjFormattedTextData().setText("");
        clienteView.getjFormattedTextCPF().setText("");
        clienteView.getjTextEmail().setText("");
        clienteView.getjFormattedTextTelefone().setText("");
        clienteView.getjTextEndereco().setText("");
    }
    
    public void selecionaCliente(){
        Integer row = clienteView.getjTableClientes().getSelectedRow();
        String nome = clienteView.getjTableClientes().getValueAt(row, 0).toString();
        String data = clienteView.getjTableClientes().getValueAt(row, 1).toString();
        String email = clienteView.getjTableClientes().getValueAt(row, 2).toString();
        Cliente cliente = clienteBeans.selecionaCliente(nome, data, email);
        String cpf = cliente.getCpf().substring(0, 3).concat("******").concat(cliente.getCpf().substring(12, 14));
        clienteView.getjTextNome().setText(cliente.getNome());
        clienteView.getjFormattedTextData().setText(data);
        clienteView.getjTextEmail().setText(cliente.getEmail());
        clienteView.getjFormattedTextTelefone().setText(cliente.getTelefone());
        clienteView.getjTextEndereco().setText(cliente.getEndereco());
        clienteView.getjFormattedTextCPF().setText(cpf);
    }
    
    public void emailToLowCase(){
        clienteView.getjTextEmail().setText(clienteView.getjTextEmail().getText().toLowerCase());
    }
}
