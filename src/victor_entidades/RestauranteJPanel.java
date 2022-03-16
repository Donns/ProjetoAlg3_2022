package victor_entidades;

import Entidades.Restaurante;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import dao.RestauranteDAO;

/**
 *
 * @author Victor
 */
public class RestauranteJPanel extends javax.swing.JPanel {

    private RestauranteDAO dao = DAOFactory.createRestDAO();

    /**
     * Creates new form RestauranteJPanel
     */
    public RestauranteJPanel() {
        initComponents();
        atualizaTabela();
    }

    private void atualizaTabela() {
        List<Restaurante> lista = dao.listar();
        DefaultTableModel dfm = (DefaultTableModel) jTableTabela.getModel();
        int linhas = jTableTabela.getRowCount();
        for (int i = 0; i < linhas; i++) {
            dfm.removeRow(0);
        }
        for (Restaurante restaurante : lista) {
            Object[] linha = new Object[]{restaurante.getId_rest(), restaurante.getNome(), restaurante.getTel_rest()};
            dfm.addRow(linha);
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

        jLabelId = new javax.swing.JLabel();
        jLabelNome = new javax.swing.JLabel();
        jLabelTelefone = new javax.swing.JLabel();
        jTextFieldId = new javax.swing.JTextField();
        jTextFieldNome = new javax.swing.JTextField();
        jTextFieldTelefone = new javax.swing.JTextField();
        jButtonSalvar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableTabela = new javax.swing.JTable();
        jButtonEditar = new javax.swing.JButton();
        Excluir = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Restaurante"));

        jLabelId.setText("Id");

        jLabelNome.setText("Nome");

        jLabelTelefone.setText("Telefone");

        jTextFieldId.setEditable(false);
        jTextFieldId.setEnabled(false);

        jButtonSalvar.setText("Salvar");
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarActionPerformed(evt);
            }
        });

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        jTableTabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id", "Nome", "Telefone"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableTabela);

        jButtonEditar.setText("Editar");
        jButtonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarActionPerformed(evt);
            }
        });

        Excluir.setText("Excluir");
        Excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabelTelefone)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelNome)
                            .addComponent(jLabelId))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldNome)
                            .addComponent(jTextFieldId))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonEditar)
                    .addComponent(Excluir))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonCancelar)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 946, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(346, 346, 346))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelId)
                    .addComponent(jTextFieldId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNome)
                    .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTelefone)
                    .addComponent(jTextFieldTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSalvar)
                    .addComponent(jButtonCancelar))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jButtonEditar)
                        .addGap(18, 18, 18)
                        .addComponent(Excluir)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE)
                        .addContainerGap())))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        // TODO add your handling code here:
        jTextFieldId.setText("");
        jTextFieldNome.setText("");
        jTextFieldTelefone.setText("");
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed
        // TODO add your handling code here:
        Restaurante novo = new Restaurante();
        novo.setNome(jTextFieldNome.getText());
        novo.setTel_rest(jTextFieldTelefone.getText());

        try {
            int id = Integer.parseInt(jTextFieldId.getText());
            novo.setId_rest(id);
            dao.editar(novo);
        } catch (NumberFormatException ex) {
            dao.editar(novo);
        }

        JOptionPane.showMessageDialog(jButtonSalvar, "Salvo com sucesso!");
        atualizaTabela();
        jButtonCancelarActionPerformed(evt);
    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarActionPerformed
        // TODO add your handling code here:
        
        if (jTableTabela.getSelectedRowCount() == 1) {
            int linha = jTableTabela.getSelectedRow();
            jTextFieldId.setText(jTableTabela.getValueAt(linha, 0) + "");
            jTextFieldNome.setText(jTableTabela.getValueAt(linha, 1) + "");
            jTextFieldTelefone.setText(jTableTabela.getValueAt(linha, 2) + "");
        } else {
            JOptionPane.showMessageDialog(jTableTabela, "Selecione somente 1 linha.");
        }
    }//GEN-LAST:event_jButtonEditarActionPerformed

    private void ExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExcluirActionPerformed
        // TODO add your handling code here:
        if (jTableTabela.getSelectedRowCount() >= 1) {
            int[] linhas = jTableTabela.getSelectedRows();
            for (int i = linhas.length - 1; i >= 0; i--) {
                int id = Integer.parseInt(jTableTabela.getValueAt(linhas[i], 0) + "");
                dao.remover(id);
            }
            JOptionPane.showMessageDialog(jTableTabela, "Removido com sucesso!");
            atualizaTabela();
        } else {
            JOptionPane.showMessageDialog(jTableTabela, "Selecione ao menos 1 linha.");
        }
    }//GEN-LAST:event_ExcluirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Excluir;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JLabel jLabelId;
    private javax.swing.JLabel jLabelNome;
    private javax.swing.JLabel jLabelTelefone;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableTabela;
    private javax.swing.JTextField jTextFieldId;
    private javax.swing.JTextField jTextFieldNome;
    private javax.swing.JTextField jTextFieldTelefone;
    // End of variables declaration//GEN-END:variables
}
