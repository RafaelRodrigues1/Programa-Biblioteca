/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projetointegrador.views;
import com.projetointegrador.controller.MainController;
import java.awt.Cursor;
import javax.swing.JLabel;
/**
 *
 * @author RafaelRodrigues1
 */
public class MainView extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    private MainController mainController;
    public MainView() {        
        mainController = new MainController(this);
        initComponents();
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelNomeUsuario = new javax.swing.JLabel();
        jLabelLogOut = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuClientes = new javax.swing.JMenu();
        jMenuLivros = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelNomeUsuario.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabelNomeUsuario.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelNomeUsuario.setToolTipText("");
        jLabelNomeUsuario.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        getContentPane().add(jLabelNomeUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 10, 235, 19));

        jLabelLogOut.setFont(new java.awt.Font("Microsoft Tai Le", 1, 12)); // NOI18N
        jLabelLogOut.setForeground(new java.awt.Color(0, 0, 0));
        jLabelLogOut.setText("LogOut");
        jLabelLogOut.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jLabelLogOutMouseMoved(evt);
            }
        });
        jLabelLogOut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLogOutMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelLogOutMouseExited(evt);
            }
        });
        getContentPane().add(jLabelLogOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 30, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/projetointegrador/Imagens/fundo para logout e usuario.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, -20, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/projetointegrador/Imagens/mainNotebook.jpg.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -90, 970, 670));

        jMenuClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/projetointegrador/Imagens/Clientes1.png"))); // NOI18N
        jMenuClientes.setText("Clientes");
        jMenuClientes.setAutoscrolls(true);
        jMenuClientes.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jMenuClientes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jMenuClientes.setIconTextGap(6);
        jMenuClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuClientesMouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenuClientes);

        jMenuLivros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/projetointegrador/Imagens/livros.png"))); // NOI18N
        jMenuLivros.setText(" Livros");
        jMenuLivros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuLivrosMouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenuLivros);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/projetointegrador/Imagens/Empréstimo.png"))); // NOI18N
        jMenu3.setText("Empréstimos");
        jMenuBar1.add(jMenu3);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/projetointegrador/Imagens/Atividades.png"))); // NOI18N
        jMenu4.setText("Atividades");
        jMenuBar1.add(jMenu4);

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/projetointegrador/Imagens/DB.png"))); // NOI18N
        jMenu5.setText("Livros DataBase");
        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabelLogOutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelLogOutMouseClicked
        mainController.logOut();
    }//GEN-LAST:event_jLabelLogOutMouseClicked

    private void jLabelLogOutMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelLogOutMouseMoved
        Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
        this.setCursor(cursor);
    }//GEN-LAST:event_jLabelLogOutMouseMoved

    private void jLabelLogOutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelLogOutMouseExited
        Cursor cursor = new Cursor(Cursor.DEFAULT_CURSOR);
        this.setCursor(cursor);
    }//GEN-LAST:event_jLabelLogOutMouseExited

    private void jMenuLivrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuLivrosMouseClicked
        mainController.abrirLivros();
    }//GEN-LAST:event_jMenuLivrosMouseClicked

    private void jMenuClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuClientesMouseClicked
        mainController.abrirClientes();
    }//GEN-LAST:event_jMenuClientesMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainView().setVisible(true);
            }
        });
    }

    public JLabel getjLabelNomeUsuario() {
        return jLabelNomeUsuario;
    }

    public MainController getMainController() {
        return mainController;
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelLogOut;
    private javax.swing.JLabel jLabelNomeUsuario;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuClientes;
    private javax.swing.JMenu jMenuLivros;
    // End of variables declaration//GEN-END:variables
}
