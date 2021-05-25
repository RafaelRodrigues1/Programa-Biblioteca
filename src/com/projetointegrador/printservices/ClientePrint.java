package com.projetointegrador.printservices;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.projetointegrador.database.connection.DBException;
import com.projetointegrador.model.entities.Cliente;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author RafaelRodrigues1
 */
public final class ClientePrint implements PrintService {
    
    private List<Cliente> clienteList;
    private Document documento;
    private PdfPTable tabela;

    public ClientePrint(List<Cliente> clienteLista) {
        clienteList = clienteLista;
        this.documento = new Document();
        tabela = new PdfPTable(3);
    }
    
    @Override
    public void run() {
        try{
            PdfWriter.getInstance(documento, new FileOutputStream("RelatorioClientes.pdf"));
            this.documento.open();
            cabecalho();
            corpo();
            this.documento.add(tabela);
            imprimir();
        }catch(FileNotFoundException | DocumentException ex){
            System.out.println(ex);
            throw new DBException("Erro ao gerar relatório");
        }
    }

    @Override
    public void cabecalho() throws DocumentException {
        Paragraph titulo = new Paragraph();
        titulo.setAlignment(Element.ALIGN_CENTER);
        titulo.add(new Chunk("Relatório  -  Clientes", new Font(Font.FontFamily.HELVETICA, 20f)));
        this.documento.add(titulo);
        this.documento.add(new Paragraph(" "));
        this.documento.add(new Paragraph(" "));
        PdfPCell cel1 = new PdfPCell(new Paragraph("NOME", new Font(Font.FontFamily.COURIER, 13f)));
        PdfPCell cel2 = new PdfPCell(new Paragraph("CPF", new Font(Font.FontFamily.COURIER, 13f)));
        PdfPCell cel3 = new PdfPCell(new Paragraph("E-MAIL", new Font(Font.FontFamily.COURIER, 13f)));
        this.tabela.addCell(cel1);
        this.tabela.addCell(cel2);
        this.tabela.addCell(cel3);
    }

    @Override
    public void corpo() throws DocumentException {
        this.clienteList.forEach(cliente -> {
            PdfPCell cel1 = new PdfPCell(new Paragraph(cliente.getNome(), new Font(Font.FontFamily.TIMES_ROMAN, 11f)));
            PdfPCell cel2 = new PdfPCell(new Paragraph(cliente.getCpf(), new Font(Font.FontFamily.TIMES_ROMAN, 12f)));
            PdfPCell cel3 = new PdfPCell(new Paragraph(cliente.getEmail(), new Font(Font.FontFamily.TIMES_ROMAN, 12f)));
            this.tabela.addCell(cel1);
            this.tabela.addCell(cel2);
            this.tabela.addCell(cel3);
        });
    }

    @Override
    public void imprimir() {
        if(this.documento.isOpen()){
            this.documento.close();
            try {
                Desktop.getDesktop().open(new File("RelatorioClientes.pdf"));
            } catch (IOException ex) {
                System.out.println(ex);
                throw new DBException("Erro ao gerar relatório");
            }
        }
    }

    
}
