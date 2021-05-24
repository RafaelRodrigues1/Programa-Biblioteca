package com.projetointegrador.model.dao;

import com.projetointegrador.model.beans.EmprestimoBeans;
import java.io.File;

/**
 * @author RafaelRodrigues1
 */
public class EmprestimoDao {
    
    private final String path = "DataBase\\Emprestimos.txt";
    private final File file = new File(path);
    private EmprestimoBeans emprestimoBeans;

    public EmprestimoDao(EmprestimoBeans emprestimoBeans) {
        this.emprestimoBeans = emprestimoBeans;
    }
    
    
}
