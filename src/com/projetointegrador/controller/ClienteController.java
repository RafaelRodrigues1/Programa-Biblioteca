package com.projetointegrador.controller;

import com.projetointegrador.model.beans.ClienteBeans;
import com.projetointegrador.model.entities.Cliente;
import com.projetointegrador.model.entities.Usuario;
import com.projetointegrador.views.ClienteView;
import com.projetointegrador.views.MainView;
import com.projetointegrador.views.Panes;
import java.awt.Color;
import java.text.ParseException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.table.DefaultTableModel;
import java.time.format.DateTimeFormatter;
import javax.swing.text.MaskFormatter;

/**
 * @author RafaelRodrigues1
 */
public class ClienteController {
    
    
    private final ClienteView clienteView;
    private final ClienteBeans clienteBeans;
    private Usuario usuario;
    
    public ClienteController(ClienteView clienteView) {
        this.clienteView = clienteView;
        clienteBeans = new ClienteBeans(this);
    }
    
    public void cadastrar(){
        try{
            if(!clienteView.getjTextNome().getText().isBlank() && !clienteView.getjFormattedTextData().getText().isBlank()
                && !clienteView.getjTextEmail().getText().isBlank() && !clienteView.getjTextEndereco().getText().isBlank() 
                    && !clienteView.getjFormattedTextTelefone().getText().isBlank()){
                if(verificaPadrao("\\d{3}.\\d{3}.\\d{3}.\\d{2}")){
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
    
    public void apagaCliente(){
        try{
            if(clienteView.getjTableClientes().getSelectedRow()>=0){
                Integer row = clienteView.getjTableClientes().getSelectedRow();
                Integer id = (int) clienteView.getjTableClientes().getValueAt(row, 0);
                String nome = clienteView.getjTableClientes().getValueAt(row, 1).toString();
                if(Panes.confirma("Deseja apagar os dados do cliente: " + nome + "?")!=1){
                    if(clienteBeans.apagaCliente(id, nome)){
                        Panes.mostraMsg("Dados do cliente apagados");
                        tabelaDefault();
                    }else{
                        throw new Exception("Erro ao apagar");
                    }
                }else{                    
                }
            }else{
                throw new Exception("Selecione um cliente para apagar");
            }
        }catch(Exception ex){
            Panes.mostraMsg(ex.getMessage());
        }
    }
    
    public void alteraCliente(){
        try{
            if(clienteView.getjTableClientes().getSelectedRow()>=0){
                Integer row = clienteView.getjTableClientes().getSelectedRow();
                Integer id = (int) clienteView.getjTableClientes().getValueAt(row, 0);
                String nome = clienteView.getjTableClientes().getValueAt(row, 1).toString();
                if(Panes.confirma("Deseja alterar os dados do cliente: " + nome + "?")!=1){
                    if(verificaPadrao("\\S*")){  
                        if(clienteBeans.alteraCliente(nome, id, clienteView.getjTextEmail().getText(), 
                                clienteView.getjTextNome().getText(), 
                                clienteView.getjFormattedTextData().getText(),
                                clienteView.getjTextEndereco().getText(), 
                                clienteView.getjFormattedTextTelefone().getText())){
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
                throw new Exception("Selecione um cliente para alterar");
            }
        }catch(Exception ex){
            Panes.mostraMsg(ex.getMessage());
        }
    }
    
    public Boolean verificaPadrao(String cpfPattern){
        Pattern email = Pattern.compile("\\S+@{1}[a-z A-Z]+(.com|.com.br)");
        Matcher matchEmail = email.matcher(clienteView.getjTextEmail().getText());
        Pattern cpf = Pattern.compile(cpfPattern);
        Matcher matchCpf = cpf.matcher(clienteView.getjFormattedTextCPF().getText());
        Pattern data = Pattern.compile("\\d{2}/\\d{2}/\\d{4}");
        Matcher matchData = data.matcher(clienteView.getjFormattedTextData().getText());
        Pattern telefone = Pattern.compile("\\(\\d{2}\\)\\d{1} \\d{4}-\\d{4}");
        Matcher matchTelefone = telefone.matcher(clienteView.getjFormattedTextTelefone().getText());
        return matchEmail.find() && matchCpf.find() && matchData.find() && matchTelefone.find();
    }
    
//    public Boolean verificaPadrao(String cpf){
//        Pattern email = Pattern.compile("\\S+@{1}[a-z A-Z]+(.com|.com.br)");
//        Matcher matchEmail = email.matcher(clienteView.getjTextEmail().getText());
//        Pattern data = Pattern.compile("\\d{2}/\\d{2}/\\d{4}");
//        Matcher matchData = data.matcher(clienteView.getjFormattedTextData().getText());
//        Pattern telefone = Pattern.compile("\\(\\d{2}\\)\\d{1} \\d{4}-\\d{4}");
//        Matcher matchTelefone = telefone.matcher(clienteView.getjFormattedTextTelefone().getText());
//        return matchEmail.find() && matchData.find() && matchTelefone.find();
//    }
    
    public void voltar(){
        clienteView.setVisible(false);
        MainView mainView = new MainView(usuario);
        mainView.setVisible(true);
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
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
        listaCliente.forEach(cliente ->{
            String data = sdf.format(cliente.getDataNascimento());
            tableModel.addRow(new Object[]{cliente.getId(), cliente.getNome(), data, cliente.getEmail(), 
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
        Integer id = Integer.parseInt(clienteView.getjTableClientes().getValueAt(row, 0).toString());
        String data = clienteView.getjTableClientes().getValueAt(row, 2).toString();
        Cliente cliente = clienteBeans.selecionaCliente(id);
        
                //Muda cor de seleção
        if(!cliente.getLiberado()){
            clienteView.getjTableClientes().setSelectionBackground(Color.RED.darker());
            clienteView.getjTableClientes().setSelectionForeground(Color.WHITE);
        }else{
            clienteView.getjTableClientes().setSelectionBackground(Color.BLUE.darker());
            clienteView.getjTableClientes().setSelectionForeground(Color.WHITE);
        }
        
        String cpf = cliente.getCpf().substring(0, 3).concat("******").concat(cliente.getCpf().substring(12, 14));
        clienteView.getjTextNome().setText(cliente.getNome());
        clienteView.getjFormattedTextData().setText(data);
        clienteView.getjTextEmail().setText(cliente.getEmail());
        clienteView.getjFormattedTextTelefone().setText(cliente.getTelefone());
        clienteView.getjTextEndereco().setText(cliente.getEndereco());
        clienteView.getjFormattedTextCPF().setText(cpf);
    }
    
    public void formataDataCpfTelefone(){
        try {
            MaskFormatter maskCPF = new MaskFormatter("###.***.***-##");
            MaskFormatter maskData = new MaskFormatter("##/##/####");
            MaskFormatter maskTelefone = new MaskFormatter("(##)# ####-####");
            maskCPF.install(clienteView.getjFormattedTextCPF());
            maskData.install(clienteView.getjFormattedTextData());
            maskTelefone.install(clienteView.getjFormattedTextTelefone());
        } catch (ParseException ex) {
            Panes.mostraMsg("Formato de data ou telefone inválido");
        }
    }
    
    public void formataEmail(){
        clienteView.getjTextEmail().setText(clienteView.getjTextEmail().getText().toLowerCase().trim());
    }
    
    public void imprimeRelatorioCliente(){
        clienteBeans.imprimeRelatorioCliente();
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }
}
