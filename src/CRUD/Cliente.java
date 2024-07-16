/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package CRUD;

import CONEXAO_BANCO.Banco_dados;
import MODULO_INICIAL.Home;
import java.awt.Component;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author sil9jvl
 */
public class Cliente extends javax.swing.JDialog {

    /**
     * Creates new form Cliente
     */
    public Cliente(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    Banco_dados bd = new Banco_dados();
    private void cadastro_cliente(){
        if(bd.getConnection()){
            try{
                String query = "insert cliente"
                        +"(nome_cliente,cpf_cliente,cep_cliente,email_cliente)"
                        +"values(?,?,?,?)";
                PreparedStatement stmt = bd.conexao.prepareStatement(query);
                
                stmt.setString(1, jTnome_cliente.getText());
                stmt.setString(2, jTcpf_cliente.getText());
                stmt.setString(3, jTcep_cliente.getText());
                stmt.setString(4, jTemail_cliente.getText());
                stmt.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Dados Gravados!");
                    stmt.close();
                    bd.conexao.close();
                }catch(SQLException erro){
                    JOptionPane.showMessageDialog(null,"Erro de gravação!" + erro.toString());
            }
        }
    }
    private void alterar_cliente(){
        if (bd.getConnection()) {
            try{
               String query = "Update clinete set nome_cliente =?, cpf_cliente =?, cep_cliente =?, email_cliente =? where idcliente = ?";
               PreparedStatement alterar = bd.conexao.prepareStatement(query);
               alterar.setString(1,jTnome_cliente.getText());
               alterar.setString(2,jTcpf_cliente.getText());
               alterar.setString(3,jTcep_cliente.getText());
               alterar.setString(4,jTemail_cliente.getText());
               alterar.executeUpdate();
               JOptionPane.showMessageDialog(null,"Dados alterados!");
                    alterar.close();
                    bd.conexao.close();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null,"Erro de SQL!" + e.toString());
            }
        }
    }
    private static void limparcampos(JPanel jPanel){
        Component[] componentes = jPanel.getComponents();
        for(Component componente : componentes){
             if(componente instanceof JTextField camposTF){
                camposTF.setText("");
            }
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPinfo_cliente = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTnome_cliente = new javax.swing.JTextField();
        jTcep_cliente = new javax.swing.JTextField();
        jTemail_cliente = new javax.swing.JTextField();
        jTcpf_cliente = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jBcadastrar = new javax.swing.JButton();
        jBAlterar = new javax.swing.JButton();
        jBcancelar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(157, 0, 0));
        jPanel1.setMaximumSize(new java.awt.Dimension(1000, 720));
        jPanel1.setMinimumSize(new java.awt.Dimension(1000, 720));
        jPanel1.setVerifyInputWhenFocusTarget(false);

        jPanel2.setBackground(new java.awt.Color(102, 0, 0));

        jPinfo_cliente.setBackground(new java.awt.Color(204, 204, 204));

        jLabel4.setText("Cep:");

        jLabel5.setText("Email:");

        jLabel2.setText("Nome:");

        jLabel3.setText("CPF:");

        jBcadastrar.setFont(new java.awt.Font("Bosch Office Sans", 1, 12)); // NOI18N
        jBcadastrar.setText("Cadastrar");
        jBcadastrar.setIconTextGap(-80);
        jBcadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBcadastrarActionPerformed(evt);
            }
        });

        jBAlterar.setFont(new java.awt.Font("Bosch Office Sans", 1, 12)); // NOI18N
        jBAlterar.setText("Alterar");
        jBAlterar.setIconTextGap(-60);
        jBAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAlterarActionPerformed(evt);
            }
        });

        jBcancelar.setFont(new java.awt.Font("Bosch Office Sans", 1, 12)); // NOI18N
        jBcancelar.setText("Cancelar");
        jBcancelar.setIconTextGap(-70);
        jBcancelar.setInheritsPopupMenu(true);
        jBcancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBcancelarActionPerformed(evt);
            }
        });

        jLabel1.setText("Cliente");

        javax.swing.GroupLayout jPinfo_clienteLayout = new javax.swing.GroupLayout(jPinfo_cliente);
        jPinfo_cliente.setLayout(jPinfo_clienteLayout);
        jPinfo_clienteLayout.setHorizontalGroup(
            jPinfo_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPinfo_clienteLayout.createSequentialGroup()
                .addGap(15, 110, Short.MAX_VALUE)
                .addGroup(jPinfo_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPinfo_clienteLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTnome_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4)
                    .addGroup(jPinfo_clienteLayout.createSequentialGroup()
                        .addGroup(jPinfo_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addGroup(jPinfo_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPinfo_clienteLayout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addGroup(jPinfo_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTcep_cliente)
                                    .addComponent(jTcpf_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPinfo_clienteLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jTemail_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPinfo_clienteLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jBcadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBcancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(63, 63, 63))
            .addGroup(jPinfo_clienteLayout.createSequentialGroup()
                .addGap(296, 296, 296)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPinfo_clienteLayout.setVerticalGroup(
            jPinfo_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPinfo_clienteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(8, 8, 8)
                .addGroup(jPinfo_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTnome_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPinfo_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTcpf_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPinfo_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTcep_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPinfo_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTemail_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 113, Short.MAX_VALUE)
                .addGroup(jPinfo_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBcadastrar)
                    .addComponent(jBAlterar)
                    .addComponent(jBcancelar))
                .addGap(36, 36, 36))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(143, 143, 143)
                .addComponent(jPinfo_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(144, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jPinfo_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(129, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(162, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBcadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBcadastrarActionPerformed
        cadastro_cliente();
        limparcampos(jPinfo_cliente);
    }//GEN-LAST:event_jBcadastrarActionPerformed

    private void jBAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAlterarActionPerformed
        alterar_cliente();
        limparcampos(jPinfo_cliente);
    }//GEN-LAST:event_jBAlterarActionPerformed

    private void jBcancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBcancelarActionPerformed
        Home h = new Home();
        this.dispose();
        h.setVisible(true);
    }//GEN-LAST:event_jBcancelarActionPerformed

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
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Cliente dialog = new Cliente(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBAlterar;
    private javax.swing.JButton jBcadastrar;
    private javax.swing.JButton jBcancelar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPinfo_cliente;
    private javax.swing.JTextField jTcep_cliente;
    private javax.swing.JTextField jTcpf_cliente;
    private javax.swing.JTextField jTemail_cliente;
    private javax.swing.JTextField jTnome_cliente;
    // End of variables declaration//GEN-END:variables
}
