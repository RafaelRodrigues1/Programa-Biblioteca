package com.projetointegrador.views;

import com.projetointegrador.controller.LivroController;
import com.projetointegrador.model.entities.Genero;
import com.projetointegrador.model.entities.Usuario;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;

/**
 * @author RafaelRodrigues1
 */
public class LivroView extends javax.swing.JFrame {
    
    private final LivroController livroController;
    private Usuario usuario;
    
    public LivroView() {
        initComponents();
        listaGenero();
        setLocationRelativeTo(null);       
        livroController = new LivroController(this);
        livroController.tabelaDefault();
    }

    public final void listaGenero(){
        List<String> listaGenero = new ArrayList<>();
        for(Genero genero : Genero.values()){
            listaGenero.add(genero.getGenero());
        }
        Collections.sort(listaGenero);
        listaGenero.forEach(genero -> {
            jComboGenero.addItem(genero);
        });
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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jCheckAlugavel = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAnotacoes = new javax.swing.JTextPane();
        jTextTitulo = new javax.swing.JTextField();
        jTextAutor = new javax.swing.JTextField();
        jTextEditora = new javax.swing.JTextField();
        jTextEdicao = new javax.swing.JTextField();
        jComboGenero = new javax.swing.JComboBox<>();
        jButtonCadastrar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableLivros = new javax.swing.JTable();
        jButtonVoltar = new javax.swing.JButton();
        jButtonAlterar = new javax.swing.JButton();
        jButtonApagar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextPesquisa = new javax.swing.JTextField();
        jCheckRestricaoEtaria = new javax.swing.JCheckBox();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("T??tulo:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, -1, -1));

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Autor:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, -1, -1));

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("G??nero:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 90, -1, -1));

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Editora:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, -1, -1));

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Edi????o:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, -1, -1));

        jLabel10.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Anota????es:");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 190, -1, -1));

        jCheckAlugavel.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jCheckAlugavel.setForeground(new java.awt.Color(0, 0, 0));
        jCheckAlugavel.setText("Alug??vel:");
        jCheckAlugavel.setToolTipText("");
        jCheckAlugavel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jCheckAlugavel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        getContentPane().add(jCheckAlugavel, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 140, 119, -1));

        jTextAnotacoes.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jScrollPane1.setViewportView(jTextAnotacoes);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 190, 240, 76));

        jTextTitulo.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        getContentPane().add(jTextTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, 280, -1));

        jTextAutor.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        getContentPane().add(jTextAutor, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, 280, -1));

        jTextEditora.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        getContentPane().add(jTextEditora, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 190, 230, -1));

        jTextEdicao.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        getContentPane().add(jTextEdicao, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 240, 140, -1));

        jComboGenero.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jComboGenero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{}));
        getContentPane().add(jComboGenero, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 90, 158, -1));

        jButtonCadastrar.setFont(new java.awt.Font("Quicksand", 1, 17)); // NOI18N
        jButtonCadastrar.setForeground(new java.awt.Color(0, 0, 0));
        jButtonCadastrar.setText("Cadastrar");
        jButtonCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCadastrarActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonCadastrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 600, 120, -1));

        jTableLivros.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTableLivros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "C??digo", "T??tulo", "Autor", "Editora", "Edi????o", "G??nero", "Alug??vel", "Anota????es", "Dispon??vel"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableLivros.setToolTipText("");
        jTableLivros.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTableLivros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableLivrosMouseClicked(evt);
            }
        });
        jTableLivros.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTableLivrosKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(jTableLivros);
        if (jTableLivros.getColumnModel().getColumnCount() > 0) {
            jTableLivros.getColumnModel().getColumn(0).setResizable(false);
            jTableLivros.getColumnModel().getColumn(0).setPreferredWidth(30);
            jTableLivros.getColumnModel().getColumn(1).setPreferredWidth(140);
            jTableLivros.getColumnModel().getColumn(4).setPreferredWidth(40);
            jTableLivros.getColumnModel().getColumn(6).setPreferredWidth(40);
            jTableLivros.getColumnModel().getColumn(7).setPreferredWidth(60);
            jTableLivros.getColumnModel().getColumn(8).setResizable(false);
            jTableLivros.getColumnModel().getColumn(8).setPreferredWidth(55);
        }

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 786, 270));

        jButtonVoltar.setFont(new java.awt.Font("Quicksand", 1, 17)); // NOI18N
        jButtonVoltar.setForeground(new java.awt.Color(0, 0, 0));
        jButtonVoltar.setText("Voltar");
        jButtonVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVoltarActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonVoltar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 110, -1));

        jButtonAlterar.setFont(new java.awt.Font("Quicksand", 1, 17)); // NOI18N
        jButtonAlterar.setForeground(new java.awt.Color(0, 0, 0));
        jButtonAlterar.setText("Alterar");
        jButtonAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlterarActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonAlterar, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 600, 120, -1));

        jButtonApagar.setFont(new java.awt.Font("Quicksand", 1, 17)); // NOI18N
        jButtonApagar.setForeground(new java.awt.Color(0, 0, 0));
        jButtonApagar.setText("Apagar");
        jButtonApagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonApagarActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonApagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 600, 120, -1));

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Pesquisa:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 30, -1, -1));

        jTextPesquisa.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTextPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextPesquisaKeyReleased(evt);
            }
        });
        getContentPane().add(jTextPesquisa, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 30, 260, -1));

        jCheckRestricaoEtaria.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jCheckRestricaoEtaria.setForeground(new java.awt.Color(0, 0, 0));
        jCheckRestricaoEtaria.setText("Restri????o et??ria");
        jCheckRestricaoEtaria.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jCheckRestricaoEtaria.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        getContentPane().add(jCheckRestricaoEtaria, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 140, -1, -1));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/projetointegrador/Imagens/painel livros.png"))); // NOI18N
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 830, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/projetointegrador/Imagens/fundoLivros.png"))); // NOI18N
        jLabel7.setText("jLabel7");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 850, 650));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCadastrarActionPerformed
        livroController.cadastrar();
    }//GEN-LAST:event_jButtonCadastrarActionPerformed

    private void jButtonVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVoltarActionPerformed
        livroController.voltar();
    }//GEN-LAST:event_jButtonVoltarActionPerformed

    private void jTableLivrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableLivrosMouseClicked
        livroController.selecionaLivro();
    }//GEN-LAST:event_jTableLivrosMouseClicked

    private void jButtonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarActionPerformed
        livroController.alteraLivro();
    }//GEN-LAST:event_jButtonAlterarActionPerformed

    private void jButtonApagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonApagarActionPerformed
        livroController.apagaLivro();
    }//GEN-LAST:event_jButtonApagarActionPerformed

    private void jTableLivrosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableLivrosKeyReleased
       // livroController.selecionaLivro();
    }//GEN-LAST:event_jTableLivrosKeyReleased

    private void jTextPesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextPesquisaKeyReleased
        livroController.pesquisaTabela();
    }//GEN-LAST:event_jTextPesquisaKeyReleased

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LivroView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new LivroView().setVisible(true);
        });
    }

    public JCheckBox getjCheckRestricaoEtaria() {
        return jCheckRestricaoEtaria;
    }
    
    public JCheckBox getjCheckAlugavel() {
        return jCheckAlugavel;
    }

    public JComboBox<String> getjComboGenero() {
        return jComboGenero;
    }

    public JTable getjTableLivros() {
        return jTableLivros;
    }

    public JTextPane getjTextAnotacoes() {
        return jTextAnotacoes;
    }

    public JTextField getjTextAutor() {
        return jTextAutor;
    }

    public JTextField getjTextEdicao() {
        return jTextEdicao;
    }

    public JTextField getjTextEditora() {
        return jTextEditora;
    }

    public JTextField getjTextTitulo() {
        return jTextTitulo;
    }

    public JTextField getjTextPesquisa() {
        return jTextPesquisa;
    }

    public final Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        livroController.setUsuario(usuario);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAlterar;
    private javax.swing.JButton jButtonApagar;
    private javax.swing.JButton jButtonCadastrar;
    private javax.swing.JButton jButtonVoltar;
    private javax.swing.JCheckBox jCheckAlugavel;
    private javax.swing.JCheckBox jCheckRestricaoEtaria;
    private javax.swing.JComboBox<String> jComboGenero;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableLivros;
    private javax.swing.JTextPane jTextAnotacoes;
    private javax.swing.JTextField jTextAutor;
    private javax.swing.JTextField jTextEdicao;
    private javax.swing.JTextField jTextEditora;
    private javax.swing.JTextField jTextPesquisa;
    private javax.swing.JTextField jTextTitulo;
    // End of variables declaration//GEN-END:variables
}
