package com.projetointegrador.controller;

import com.projetointegrador.beans.ClienteBeans;
import com.projetointegrador.views.ClienteView;
import com.projetointegrador.views.Panes;

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
                && !clienteView.getjTextEmail().getText().isBlank() && !clienteView.getjFormattedTextCPF().getText().isBlank()
                    && !clienteView.getjTextEndereco().getText().isBlank() && !clienteView.getjFormattedTextTelefone().getText().isBlank()){
                if(clienteBeans.cadastrar(clienteView.getjTextNome().getText(), clienteView.getjFormattedTextData().getText(), 
                        clienteView.getjTextEmail().getText(), clienteView.getjFormattedTextCPF().getText(), 
                        clienteView.getjTextEndereco().getText(), clienteView.getjTextEndereco().getText())){
                    Panes.mostraMsg("Cliente cadastrado!");
                }else{
                    throw new Exception("Erro ao cadastrar cliente");
                }
            }else{
                throw new Exception("Preencha os campos obrigatórios");
            }
        }catch(Exception ex){
            Panes.mostraMsg(ex.getMessage());
        }
    }
}
