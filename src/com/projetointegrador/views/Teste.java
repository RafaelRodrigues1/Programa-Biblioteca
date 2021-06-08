package com.projetointegrador.views;

import com.projetointegrador.model.dao.EmprestimoDao;
import com.projetointegrador.model.entities.Cliente;

/**
 * @author RafaelRodrigues1
 */
public class Teste {
    
    public static void main(String[] args) {
        
        EmprestimoDao dao = new EmprestimoDao();
        dao.verificaQuantidadeLivrosCliente(new Cliente(6, ""));
    }
}
