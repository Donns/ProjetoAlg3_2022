package victor_entidades;

import dao.FinanceiroDAO;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 *
 * @author Victor
 */
public class FinanceiroJPanel extends javax.swing.JPanel {
    private FinanceiroDAO dao = DAOFactory.createFinanDAO();

    /**
     * Creates new form FinanceiroJPanel
     */
    public FinanceiroJPanel() {
        initComponents();
        jTextFieldPFun.setText((dao.despesa_fun())+"");
        jTextFieldPFor.setText((dao.despesa_forn())+"");
        jTextFieldCaixa.setText(dao.caixa()+"");
        dao.iniciar();
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
        jTextFieldPFun = new javax.swing.JTextField();
        jTextFieldPFor = new javax.swing.JTextField();
        jTextFieldCaixa = new javax.swing.JTextField();
        jButtonP1 = new javax.swing.JButton();
        jButtonP2 = new javax.swing.JButton();
        jTextFieldData = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Financeiro"));

        jLabel1.setText("Pagto. Funcionários");

        jLabel2.setText("Pagto. Fornecedoras");

        jLabel3.setText("Dinheiro em Caixa");

        jTextFieldPFun.setEditable(false);

        jTextFieldPFor.setEditable(false);

        jTextFieldCaixa.setEditable(false);

        jButtonP1.setText("Pagar");
        jButtonP1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonP1ActionPerformed(evt);
            }
        });

        jButtonP2.setText("Pagar");
        jButtonP2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonP2ActionPerformed(evt);
            }
        });

        jTextFieldData.setEditable(false);

        jLabel5.setText("Próxima data para pagamento:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldPFor, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldCaixa)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldPFun)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonP2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonP1)
                        .addGap(64, 64, 64)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldData, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(255, 255, 255))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldPFun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonP1)
                    .addComponent(jTextFieldData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldPFor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonP2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(486, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonP1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonP1ActionPerformed
        // TODO add your handling code here:
        //funcionario
        jTextFieldCaixa.setText(dao.atualizaCaixaFun()+"");
        jTextFieldPFun.setText((dao.despesa_fun())+"");
        LocalDate hoje = LocalDate.now().plusMonths(1);
        String formatada = hoje.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
        jTextFieldData.setText(formatada);
        
    }//GEN-LAST:event_jButtonP1ActionPerformed

    private void jButtonP2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonP2ActionPerformed
        // TODO add your handling code here:
        //fornecedora
        jTextFieldCaixa.setText(dao.atualizaCaixaFor()+"");
        jTextFieldPFor.setText((dao.despesa_forn())+"");
    }//GEN-LAST:event_jButtonP2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonP1;
    private javax.swing.JButton jButtonP2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField jTextFieldCaixa;
    private javax.swing.JTextField jTextFieldData;
    private javax.swing.JTextField jTextFieldPFor;
    private javax.swing.JTextField jTextFieldPFun;
    // End of variables declaration//GEN-END:variables
}