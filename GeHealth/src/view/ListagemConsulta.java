/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import funcao.ConsultaDao;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import model.Usuario;

/**
 *
 * @author Marcos
 */
public class ListagemConsulta extends javax.swing.JFrame {

    ConsultaDao consultadao = new ConsultaDao();

    private void preencherTabela() {
        if (consultadao.conectar()) {
            DefaultTableModel modelo = consultadao.montarTabela();
            tblConsultas.setModel(modelo);
            tblConsultas.setRowSorter(new TableRowSorter<>(modelo));
            consultadao.desconectar();
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao conectar com o banco!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public ListagemConsulta(Usuario u) {
        initComponents();
        preencherTabela();
        if (u.getNivel().equalsIgnoreCase("Administrador")) {
            btnExcluir.setEnabled(true);
            btnAdicionar.setEnabled(true);
        } else if (u.getNivel().equalsIgnoreCase("Terapeuta")) {
            btnExcluir.setEnabled(false);
            btnAdicionar.setEnabled(true);
        } else {
            btnExcluir.setEnabled(false);
            btnAdicionar.setEnabled(false);
        }
    }

    public ListagemConsulta() {
        initComponents();
        preencherTabela();
    }

    ConsultaDao lc = new ConsultaDao();

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        pnlGeral = new javax.swing.JPanel();
        pnlSuperior = new javax.swing.JPanel();
        lblGestaoConsulta = new javax.swing.JLabel();
        pnlInferior = new javax.swing.JPanel();
        btnAdicionar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        lblConsultasCadastradas = new javax.swing.JLabel();
        pnlCentro = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblConsultas = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestão de Consulta");
        setName("ListagemConsulta"); // NOI18N

        pnlGeral.setBackground(new java.awt.Color(217, 217, 217));

        pnlSuperior.setBackground(new java.awt.Color(77, 128, 133));

        lblGestaoConsulta.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblGestaoConsulta.setText("Gestão de Consulta");

        javax.swing.GroupLayout pnlSuperiorLayout = new javax.swing.GroupLayout(pnlSuperior);
        pnlSuperior.setLayout(pnlSuperiorLayout);
        pnlSuperiorLayout.setHorizontalGroup(
            pnlSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSuperiorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblGestaoConsulta)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlSuperiorLayout.setVerticalGroup(
            pnlSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSuperiorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblGestaoConsulta)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pnlInferior.setBackground(new java.awt.Color(217, 217, 217));
        pnlInferior.setLayout(new java.awt.GridBagLayout());

        btnAdicionar.setBackground(new java.awt.Color(77, 128, 133));
        btnAdicionar.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnAdicionar.setText("Adicionar");
        btnAdicionar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 52;
        gridBagConstraints.ipady = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 6, 6, 35);
        pnlInferior.add(btnAdicionar, gridBagConstraints);

        btnExcluir.setBackground(new java.awt.Color(123, 128, 124));
        btnExcluir.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 73;
        gridBagConstraints.ipady = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 519, 6, 0);
        pnlInferior.add(btnExcluir, gridBagConstraints);

        lblConsultasCadastradas.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblConsultasCadastradas.setText("Consultas Cadastradas");

        pnlCentro.setBackground(new java.awt.Color(217, 217, 217));
        pnlCentro.setLayout(new java.awt.GridLayout(1, 0));

        tblConsultas.setShowGrid(true);
        jScrollPane1.setViewportView(tblConsultas);

        pnlCentro.add(jScrollPane1);

        javax.swing.GroupLayout pnlGeralLayout = new javax.swing.GroupLayout(pnlGeral);
        pnlGeral.setLayout(pnlGeralLayout);
        pnlGeralLayout.setHorizontalGroup(
            pnlGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlSuperior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlInferior, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlGeralLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblConsultasCadastradas)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(pnlCentro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlGeralLayout.setVerticalGroup(
            pnlGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGeralLayout.createSequentialGroup()
                .addComponent(pnlSuperior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblConsultasCadastradas)
                .addGap(18, 18, 18)
                .addComponent(pnlCentro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlInferior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlGeral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlGeral, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        CadastroConsulta cc = new CadastroConsulta();
        cc.setConsultaDao(consultadao);// Passa a instância para CadastroConsulta
        cc.setVisible(true);

        // Atualiza a tabela quando a TelaCadastroConsulta for fechada.
        cc.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                preencherTabela(); // Atualiza a tabela
            }
        });
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed

        int linhaSelecionada = tblConsultas.getSelectedRow();

        if (linhaSelecionada >= 0) {
            int confirmacao = javax.swing.JOptionPane.showConfirmDialog(this,
                    "Deseja realmente realizar esta exclusão?",
                    "Confirmação de exclusão",
                    javax.swing.JOptionPane.YES_NO_OPTION);

            if (confirmacao == javax.swing.JOptionPane.YES_OPTION) {
                // Pegando o ID da linha selecionada
                int idConsulta = (int) tblConsultas.getValueAt(linhaSelecionada, 0);

                consultadao.conectar();
                boolean sucesso = consultadao.excluir(idConsulta);
                consultadao.desconectar();

                if (sucesso) {
                    javax.swing.JOptionPane.showMessageDialog(this, "Exclusão realizada com sucesso!");
                    preencherTabela(); // Atualizar a tabela
                } else {
                    javax.swing.JOptionPane.showMessageDialog(this, "Erro ao realizar exclusão.");
                }
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Selecione uma consulta para excluir.");
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

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
            java.util.logging.Logger.getLogger(ListagemConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListagemConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListagemConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListagemConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListagemConsulta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblConsultasCadastradas;
    private javax.swing.JLabel lblGestaoConsulta;
    private javax.swing.JPanel pnlCentro;
    private javax.swing.JPanel pnlGeral;
    private javax.swing.JPanel pnlInferior;
    private javax.swing.JPanel pnlSuperior;
    private javax.swing.JTable tblConsultas;
    // End of variables declaration//GEN-END:variables
}
