package com.projetointegrador.model.services.printservices;

import com.itextpdf.text.DocumentException;

/**
 * @author RafaelRodrigues1
 */
public interface PrintService {
    
    public void run();
    public void cabecalho()throws DocumentException;
    public void corpo()throws DocumentException;
    public void imprimir();
    //public void rodape();
}
