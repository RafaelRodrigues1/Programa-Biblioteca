package com.projetointegrador.controller;

import com.projetointegrador.model.beans.EmprestimoBeans;
import com.projetointegrador.views.EmprestimoView;
import com.projetointegrador.views.MainView;

/**
 * @author RafaelRodrigues1
 */
public class EmprestimoController {
    
    private EmprestimoView emprestimoView;
    private EmprestimoBeans emprestimoBeans;
    private MainView mainView;

    public EmprestimoController(EmprestimoView emprestimoView) {
        this.emprestimoView = emprestimoView;
        emprestimoBeans = new EmprestimoBeans(this);
    }
    
    
    
}
