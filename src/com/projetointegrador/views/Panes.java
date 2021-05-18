package com.projetointegrador.views;

import javax.swing.JOptionPane;

/**
 * @author RafaelRodrigues1
 */
public class Panes {
    
    public static void mostraMsg(String string){
        JOptionPane.showMessageDialog(null, string, "Info", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static Integer confirma(String string){
        Integer resp = JOptionPane.showConfirmDialog(null, string, "Confirmação", JOptionPane.YES_NO_OPTION);
        return resp;
    }
}
