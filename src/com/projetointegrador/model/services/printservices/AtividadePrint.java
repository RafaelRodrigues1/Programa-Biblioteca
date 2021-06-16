package com.projetointegrador.model.services.printservices;

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
import com.projetointegrador.model.entities.Registro;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author RafaelRodrigues1
 */
public class AtividadePrint implements PrintService{
    
    private List<Registro> registroList;
    private Document documento;
    private PdfPTable tabela;
    private DateTimeFormatter dataHoraForm = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    
    public AtividadePrint(List<Registro> registroLista) {
        registroList = registroLista;
        this.documento = new Document();
        tabela = new PdfPTable(3);
    }

    @Override
    public void run() {
        try{
            PdfWriter.getInstance(documento, new FileOutputStream("RelatorioAtividades.pdf"));
            this.documento.open();
            cabecalho();
            corpo();
            this.documento.add(tabela);
            imprimir();
        }catch(FileNotFoundException | DocumentException ex){
            System.out.println(ex);
            this.documento.close();
            throw new DBException("Erro ao gerar relatório");
        }
    }

    @Override
    public void cabecalho() throws DocumentException {
        Paragraph titulo = new Paragraph();
        titulo.setAlignment(Element.ALIGN_CENTER);
        titulo.add(new Chunk("Relatório  -  Atividades", new Font(Font.FontFamily.HELVETICA, 20f)));
        this.documento.add(titulo);
        this.documento.add(new Paragraph(" "));
        this.documento.add(new Paragraph(" "));
        PdfPCell cel1 = new PdfPCell(new Paragraph("USUÁRIO", new Font(Font.FontFamily.COURIER, 13f)));
        PdfPCell cel2 = new PdfPCell(new Paragraph("ATIVIDADE", new Font(Font.FontFamily.COURIER, 13f)));
        PdfPCell cel3 = new PdfPCell(new Paragraph("DATA E HORA", new Font(Font.FontFamily.COURIER, 13f)));
        this.tabela.addCell(cel1);
        this.tabela.addCell(cel2);
        this.tabela.addCell(cel3);
    }

    @Override
    public void corpo() throws DocumentException {
        this.registroList.forEach(registro -> {
            PdfPCell cel1 = new PdfPCell(new Paragraph(registro.getUsuario().getLogin(), new Font(Font.FontFamily.TIMES_ROMAN, 11f)));
            PdfPCell cel2 = new PdfPCell(new Paragraph(registro.getDescricao(), new Font(Font.FontFamily.TIMES_ROMAN, 12f)));
            PdfPCell cel3 = new PdfPCell(new Paragraph(dataHoraForm.format(registro.getDataHora()), new Font(Font.FontFamily.TIMES_ROMAN, 12f)));
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
                Desktop.getDesktop().open(new File("RelatorioAtividades.pdf"));
            } catch (IOException ex) {
                System.out.println(ex);
                throw new DBException("Erro ao gerar relatório");
            }
        }
    }
}
