package com.projetointegrador.controller;

import com.projetointegrador.model.dao.RegistroDao;
import com.projetointegrador.model.entities.Registro;
import com.projetointegrador.model.entities.Usuario;
import com.projetointegrador.printservices.AtividadePrint;
import com.projetointegrador.views.AtividadeView;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.time.format.DateTimeFormatter;

/**
 * @author RafaelRodrigues1
 */
public class AtividadeController {
    
    private AtividadeView atividadeView;
    private Usuario usuario;
    private RegistroDao registroDao;

    //"Beans" não implementado por quantidade baixa de funcionalidade
    public AtividadeController(AtividadeView atividadeView) {
        this.atividadeView = atividadeView;
        registroDao = new RegistroDao();
        preencheTabela();
    }
    
    private void preencheTabela(){
        DateTimeFormatter dataHoraForm = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        List<Registro> listaRegistro = registroDao.listar();
        DefaultTableModel tableModel = (DefaultTableModel) atividadeView.getjTableAtividades().getModel();
        tableModel.setNumRows(0);
        listaRegistro.forEach(registro -> {
                String dataHora = dataHoraForm.format(registro.getDataHora());
                tableModel.addRow(new Object[]{
                    registro.getUsuario().getLogin(), registro.getDescricao(), dataHora
                });
        });
    }
    
    public void imprimeRelatorioAtividade(){
        AtividadePrint print = new AtividadePrint(registroDao.listar());
        print.run();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
