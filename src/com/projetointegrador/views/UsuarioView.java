/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projetointegrador.views;

import com.projetointegrador.controller.UsuarioController;
import java.text.ParseException;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author RafaelRodrigues1
 */
public final class UsuarioView extends javax.swing.JFrame {

    /**
     * Creates new form CadastroUsuario
     */
    private final UsuarioController usuarioController;
    public UsuarioView() {
        initComponents();
        formataData();
        setLocationRelativeTo(null);
        usuarioController = new UsuarioController(this);
    }
    
    public void formataData(){
        try {
            MaskFormatter mask = new MaskFormatter("##/##/####");
            mask.install(jFormattedTextData);
        } catch (ParseException ex) {
            Panes.mostraMsg("Formato de data inválido");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextNome = new javax.swing.JTextField();
        jTextLogin = new javax.swing.JTextField();
        jPasswordSenha = new javax.swing.JPasswordField();
        jPasswordRepeteSenha = new javax.swing.JPasswordField();
        jFormattedTextData = new javax.swing.JFormattedTextField();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jCheckBoxMostrarSenha = new javax.swing.JCheckBox();
        jLabelConfirmaSenhas = new javax.swing.JLabel();
        jLabelAvisoUsuario = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel1.setText("Nome completo:");

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel2.setText("Data de nascimento(dd/mm/yyyy):");

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel3.setText("Login:");

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel4.setText("Senha:");

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel5.setText("Insira a senha novamente:");

        jTextNome.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTextNome.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jTextLogin.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTextLogin.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextLoginKeyReleased(evt);
            }
        });

        jPasswordSenha.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jPasswordSenha.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPasswordSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jPasswordSenhaKeyReleased(evt);
            }
        });

        jPasswordRepeteSenha.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jPasswordRepeteSenha.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPasswordRepeteSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jPasswordRepeteSenhaKeyReleased(evt);
            }
        });

        jFormattedTextData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT))));
        jFormattedTextData.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jFormattedTextData.setText("    /      /          ");
        jFormattedTextData.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        jButton1.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jButton1.setText("Cadastrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel6.setText("Cadastro de usuário");

        jCheckBoxMostrarSenha.setText("Mostrar senha");
        jCheckBoxMostrarSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMostrarSenhaActionPerformed(evt);
            }
        });

        jLabelConfirmaSenhas.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jLabelAvisoUsuario.setBackground(new java.awt.Color(255, 51, 51));
        jLabelAvisoUsuario.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        jLabelAvisoUsuario.setForeground(new java.awt.Color(255, 0, 0));
        jLabelAvisoUsuario.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addComponent(jLabel6))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel1)
                        .addGap(6, 6, 6)
                        .addComponent(jTextNome, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel2)
                        .addGap(107, 107, 107)
                        .addComponent(jFormattedTextData, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel3)
                        .addGap(227, 227, 227)
                        .addComponent(jTextLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel5)
                        .addGap(99, 99, 99)
                        .addComponent(jPasswordRepeteSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(344, 344, 344)
                        .addComponent(jCheckBoxMostrarSenha))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(314, 314, 314)
                        .addComponent(jLabelConfirmaSenhas, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(190, 190, 190)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel4)
                        .addGap(221, 221, 221)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelAvisoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPasswordSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel1))
                    .addComponent(jTextNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jFormattedTextData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel3))
                    .addComponent(jTextLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addComponent(jLabelAvisoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel4))
                    .addComponent(jPasswordSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel5))
                    .addComponent(jPasswordRepeteSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addComponent(jCheckBoxMostrarSenha)
                .addGap(6, 6, 6)
                .addComponent(jLabelConfirmaSenhas, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jButton1)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBoxMostrarSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMostrarSenhaActionPerformed
        usuarioController.campoMostraSenha();
    }//GEN-LAST:event_jCheckBoxMostrarSenhaActionPerformed

    private void jPasswordRepeteSenhaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordRepeteSenhaKeyReleased
        usuarioController.verificaSenhas();
    }//GEN-LAST:event_jPasswordRepeteSenhaKeyReleased

    private void jPasswordSenhaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordSenhaKeyReleased
        usuarioController.verificaSenhas();
    }//GEN-LAST:event_jPasswordSenhaKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        usuarioController.cadastrar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextLoginKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextLoginKeyReleased
        usuarioController.apagaEspacoLogin();
        usuarioController.verificaUsuario();
    }//GEN-LAST:event_jTextLoginKeyReleased

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
            java.util.logging.Logger.getLogger(UsuarioView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UsuarioView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UsuarioView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UsuarioView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UsuarioView().setVisible(true);
            }
        });
    }

    public JLabel getjLabelConfirmaSenhas() {
        return jLabelConfirmaSenhas;
    }

    public JCheckBox getjCheckBoxMostrarSenha() {
        return jCheckBoxMostrarSenha;
    }

    public void setjCheckBoxMostrarSenha(JCheckBox jCheckBoxMostrarSenha) {
        this.jCheckBoxMostrarSenha = jCheckBoxMostrarSenha;
    }

    public JFormattedTextField getjFormattedTextData() {
        return jFormattedTextData;
    }

    public void setjFormattedTextData(JFormattedTextField jFormattedTextData) {
        this.jFormattedTextData = jFormattedTextData;
    }

    public JPasswordField getjPasswordRepeteSenha() {
        return jPasswordRepeteSenha;
    }

    public void setjPasswordRepeteSenha(JPasswordField jPasswordRepeteSenha) {
        this.jPasswordRepeteSenha = jPasswordRepeteSenha;
    }

    public JPasswordField getjPasswordSenha() {
        return jPasswordSenha;
    }

    public void setjPasswordSenha(JPasswordField jPasswordSenha) {
        this.jPasswordSenha = jPasswordSenha;
    }

    public JTextField getjTextLogin() {
        return jTextLogin;
    }

    public void setjTextLogin(JTextField jTextLogin) {
        this.jTextLogin = jTextLogin;
    }

    public JTextField getjTextNome() {
        return jTextNome;
    }

    public void setjTextNome(JTextField jTextNome) {
        this.jTextNome = jTextNome;
    }

    public JLabel getjLabelAvisoUsuario() {
        return jLabelAvisoUsuario;
    }
    
    


 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBoxMostrarSenha;
    private javax.swing.JFormattedTextField jFormattedTextData;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelAvisoUsuario;
    private javax.swing.JLabel jLabelConfirmaSenhas;
    private javax.swing.JPasswordField jPasswordRepeteSenha;
    private javax.swing.JPasswordField jPasswordSenha;
    private javax.swing.JTextField jTextLogin;
    private javax.swing.JTextField jTextNome;
    // End of variables declaration//GEN-END:variables
}
