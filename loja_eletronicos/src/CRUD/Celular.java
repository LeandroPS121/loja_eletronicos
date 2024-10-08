/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package CRUD;

import CONEXAO_BANCO.Banco_dados;
import MODULO_INICIAL.Home;
import java.awt.Component;
import java.sql.ResultSet;
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
public class Celular extends javax.swing.JDialog {

    /**
     * Creates new form produto
     */
    public Celular(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setResizable(false);
        setLocationRelativeTo(null);
        pesquisa_celular();
        initTableListener();
    }
    Banco_dados bd = new Banco_dados();
    private void cadastro_celular(){
        if(bd.getConnection()){
            try{
                String query = "insert celular"
                        +"(modelo_celular,estado_celular,memoria_ram_celular,processador_celular,armazenamento_celular,bateria_celular,preco_celular)"
                        +"values(?,?,?,?,?,?,?)";
                PreparedStatement smtp = bd.conexao.prepareStatement(query);
                
                String estado_cel = jCBestado_celular.getSelectedItem().toString();
                String memoria_ram_cel = jCBmemoria_ram_celular.getSelectedItem().toString();
                String armazenamento_cel = jCBarmazenamento_celular.getSelectedItem().toString();
                
                smtp.setString(1, jTmodelo_celular.getText());
                smtp.setString(2, estado_cel);
                smtp.setString(3, memoria_ram_cel);
                smtp.setString(4, jTprocessador_celular.getText());
                smtp.setString(5, armazenamento_cel);
                smtp.setString(6, jTbateria_celular.getText());
                smtp.setString(7, jTPreco_celular.getText());
                smtp.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Dados Gravados!");
                    smtp.close();
                    bd.conexao.close();
                }catch(SQLException erro){
                    JOptionPane.showMessageDialog(null,"Erro de gravação!" + erro.toString());
            }
        }
    }
    private void pesquisa_celular(){
        if (bd.getConnection()) {
            try{
                String query = "select * from celular where modelo_celular like ?";
                PreparedStatement smtp = bd.conexao.prepareStatement(query);
                smtp.setString(1,"%"+jTPesquisa.getText()+"%");
                ResultSet rs = smtp.executeQuery();
                DefaultTableModel tabela = (DefaultTableModel) jTtabela_pesquisa.getModel();
                tabela.setNumRows(0);
                while(rs.next()){
                    tabela.addRow(new Object[]{
                        rs.getString("id_celular"),
                        rs.getString("modelo_celular"),
                        rs.getString("estado_celular"),
                        rs.getString("memoria_ram_celular"),
                        rs.getString("processador_celular"),
                        rs.getString("armazenamento_celular"),
                        rs.getString("bateria_celular"),
                        rs.getString("preco_celular")
                    });
                }
            }catch(SQLException e){
                System.out.println("Erro ao pesquisar"+e.toString());
            }
        }
    }
    private void alterar_celular(){
        if (bd.getConnection()) {
            try{
               String query = "Update celular set modelo_celular =?, estado_celular =?, memoria_ram_celular =?, processador_celular =?, armazenamento_celular =?, bateria_celular =?, preco_celular=? where id_celular = ?";
               PreparedStatement alterar = bd.conexao.prepareStatement(query);
               alterar.setString(1,jTmodelo_celular.getText());
               alterar.setString(2,jCBestado_celular.getSelectedItem().toString());
               alterar.setString(3,jCBmemoria_ram_celular.getSelectedItem().toString());
               alterar.setString(4,jTprocessador_celular.getText());
               alterar.setString(5,jCBarmazenamento_celular.getSelectedItem().toString());
               alterar.setString(6,jTbateria_celular.getText());
               alterar.setString(7, jTPreco_celular.getText());
               alterar.setString(8,jLID.getText());
               alterar.executeUpdate();
               JOptionPane.showMessageDialog(null,"Dados alterados!");
                    alterar.close();
                    bd.conexao.close();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null,"Erro de SQL!" + e.toString());
            }
        }
    }
    private void excluir_celular(){
        if(bd.getConnection()){
            try{
                String query = "delete from celular where id_celular =?";
                PreparedStatement excluir = bd.conexao.prepareStatement(query);
                String index = (String)jTtabela_pesquisa.getModel().getValueAt(jTtabela_pesquisa.getSelectedRow(), 0);
                excluir.setString(1,index);
                
                int escolha = JOptionPane.showConfirmDialog(null,"Deseja excluir?","Confirmacao",JOptionPane.YES_NO_OPTION);
                if (escolha == JOptionPane.YES_OPTION) {
                    int resultado = excluir.executeUpdate();
                    if (resultado>0) {
                        JOptionPane.showMessageDialog(null,"Celular excluido com sucesso");
                    }else{
                        JOptionPane.showMessageDialog(null,"Nao foi possivel excluir o celular");
                    }
                    excluir.close();
                    bd.conexao.close();
                }
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Falha ao excluir o celular"+e.toString());
            }
        }
    }
    private static void limparcampos(JPanel jPanel){
        Component[] componentes = jPanel.getComponents();
        for(Component componente : componentes){
            if(componente instanceof JTextField){
                JTextField camposTF = (JTextField)componente;
                camposTF.setText("");
            }
        }
    }
    private void limpartabela(){
        DefaultTableModel table = (DefaultTableModel)jTtabela_pesquisa.getModel();
        table.setNumRows(0);
    }
    private void initTableListener() {
        jTtabela_pesquisa.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int selectedRow = jTtabela_pesquisa.getSelectedRow();
                if (selectedRow != -1) {
                    jLID.setText(jTtabela_pesquisa.getValueAt(selectedRow, 0).toString());
                    jTmodelo_celular.setText(jTtabela_pesquisa.getValueAt(selectedRow, 1).toString());
                    jCBestado_celular.setSelectedItem(jTtabela_pesquisa.getValueAt(selectedRow, 2).toString());
                    jCBmemoria_ram_celular.setSelectedItem(jTtabela_pesquisa.getValueAt(selectedRow, 3).toString());
                    jTprocessador_celular.setText(jTtabela_pesquisa.getValueAt(selectedRow, 4).toString());
                    jCBarmazenamento_celular.setSelectedItem(jTtabela_pesquisa.getValueAt(selectedRow, 5).toString());
                    jTbateria_celular.setText(jTtabela_pesquisa.getValueAt(selectedRow, 6).toString());
                    jTPreco_celular.setText(jTtabela_pesquisa.getValueAt(selectedRow, 7).toString());
                }
            }
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

        jPanel1 = new javax.swing.JPanel();
        jLID = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPinfo_celular = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTmodelo_celular = new javax.swing.JTextField();
        jTprocessador_celular = new javax.swing.JTextField();
        jTPreco_celular = new javax.swing.JTextField();
        jBlimpar = new javax.swing.JButton();
        jBexcluir = new javax.swing.JButton();
        jBAlterar = new javax.swing.JButton();
        jBcadastrar = new javax.swing.JButton();
        jCBestado_celular = new javax.swing.JComboBox<>();
        jCBmemoria_ram_celular = new javax.swing.JComboBox<>();
        jCBarmazenamento_celular = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTbateria_celular = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jPtabela_celular = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTPesquisa = new javax.swing.JTextField();
        jBPesquisar = new javax.swing.JButton();
        jBLimpar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTtabela_pesquisa = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jBcancelar = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1200, 720));
        setMinimumSize(new java.awt.Dimension(1200, 720));
        setPreferredSize(new java.awt.Dimension(1200, 720));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 0, 82));
        jPanel1.setMaximumSize(new java.awt.Dimension(1000, 720));
        jPanel1.setMinimumSize(new java.awt.Dimension(1000, 720));
        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 720));

        jPanel2.setPreferredSize(new java.awt.Dimension(1000, 546));

        jPinfo_celular.setBackground(new java.awt.Color(0, 51, 102));

        jLabel1.setFont(new java.awt.Font("Bosch Sans", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Model:");

        jLabel3.setFont(new java.awt.Font("Bosch Sans", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Condition:");

        jLabel5.setFont(new java.awt.Font("Bosch Sans", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Internal Storage:");

        jLabel6.setFont(new java.awt.Font("Bosch Sans", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Battery:");

        jLabel7.setFont(new java.awt.Font("Bosch Sans", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Price: R$");

        jLabel8.setFont(new java.awt.Font("Bosch Sans", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Processor");

        jTPreco_celular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTPreco_celularActionPerformed(evt);
            }
        });

        jBlimpar.setFont(new java.awt.Font("Bosch Office Sans", 1, 12)); // NOI18N
        jBlimpar.setText("Clean");
        jBlimpar.setIconTextGap(-60);
        jBlimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBlimparActionPerformed(evt);
            }
        });

        jBexcluir.setFont(new java.awt.Font("Bosch Office Sans", 1, 12)); // NOI18N
        jBexcluir.setText("Delete");
        jBexcluir.setIconTextGap(-60);
        jBexcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBexcluirActionPerformed(evt);
            }
        });

        jBAlterar.setFont(new java.awt.Font("Bosch Office Sans", 1, 12)); // NOI18N
        jBAlterar.setText("Edit");
        jBAlterar.setIconTextGap(-60);
        jBAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAlterarActionPerformed(evt);
            }
        });

        jBcadastrar.setFont(new java.awt.Font("Bosch Office Sans", 1, 12)); // NOI18N
        jBcadastrar.setText("Register");
        jBcadastrar.setIconTextGap(-80);
        jBcadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBcadastrarActionPerformed(evt);
            }
        });

        jCBestado_celular.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "New", "like new" }));

        jCBmemoria_ram_celular.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2 GB", "3 GB", "4 GB", "6 GB", "8 GB", "12 GB" }));

        jCBarmazenamento_celular.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "32 GB", "64 GB", "128 GB", "256 GB", "512 GB" }));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("mA");

        jLabel11.setFont(new java.awt.Font("Bosch Sans", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("RAM Memory:");

        javax.swing.GroupLayout jPinfo_celularLayout = new javax.swing.GroupLayout(jPinfo_celular);
        jPinfo_celular.setLayout(jPinfo_celularLayout);
        jPinfo_celularLayout.setHorizontalGroup(
            jPinfo_celularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPinfo_celularLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPinfo_celularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPinfo_celularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPinfo_celularLayout.createSequentialGroup()
                            .addComponent(jBexcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jBlimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPinfo_celularLayout.createSequentialGroup()
                            .addComponent(jBcadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jBAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPinfo_celularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPinfo_celularLayout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(18, 18, 18)
                            .addComponent(jTmodelo_celular, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPinfo_celularLayout.createSequentialGroup()
                            .addGroup(jPinfo_celularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5)
                                .addGroup(jPinfo_celularLayout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTbateria_celular, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPinfo_celularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPinfo_celularLayout.createSequentialGroup()
                                    .addGap(16, 16, 16)
                                    .addComponent(jCBarmazenamento_celular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPinfo_celularLayout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel4))))
                        .addGroup(jPinfo_celularLayout.createSequentialGroup()
                            .addComponent(jLabel8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTprocessador_celular, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPinfo_celularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPinfo_celularLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTPreco_celular))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPinfo_celularLayout.createSequentialGroup()
                                .addGroup(jPinfo_celularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(jPinfo_celularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jCBestado_celular, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jCBmemoria_ram_celular, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPinfo_celularLayout.setVerticalGroup(
            jPinfo_celularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPinfo_celularLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPinfo_celularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTmodelo_celular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPinfo_celularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jCBestado_celular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPinfo_celularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCBmemoria_ram_celular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(jPinfo_celularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTPreco_celular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPinfo_celularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTprocessador_celular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPinfo_celularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jCBarmazenamento_celular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPinfo_celularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4)
                    .addComponent(jTbateria_celular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPinfo_celularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBcadastrar)
                    .addComponent(jBAlterar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPinfo_celularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBexcluir)
                    .addComponent(jBlimpar))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(140, 158, 177));

        jPtabela_celular.setBackground(new java.awt.Color(0, 51, 102));

        jLabel9.setFont(new java.awt.Font("Bosch Office Sans", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Model:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Registers");

        jTPesquisa.setFont(new java.awt.Font("Bosch Office Sans", 1, 12)); // NOI18N

        jBPesquisar.setFont(new java.awt.Font("Bosch Office Sans", 1, 12)); // NOI18N
        jBPesquisar.setText("Search");
        jBPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBPesquisarActionPerformed(evt);
            }
        });

        jBLimpar.setFont(new java.awt.Font("Bosch Office Sans", 1, 12)); // NOI18N
        jBLimpar.setText("Clean");
        jBLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBLimparActionPerformed(evt);
            }
        });

        jTtabela_pesquisa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Code", "Model", "Condition", "RAM Memory", "Processor", "Storage", "Battery", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTtabela_pesquisa);

        javax.swing.GroupLayout jPtabela_celularLayout = new javax.swing.GroupLayout(jPtabela_celular);
        jPtabela_celular.setLayout(jPtabela_celularLayout);
        jPtabela_celularLayout.setHorizontalGroup(
            jPtabela_celularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPtabela_celularLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPtabela_celularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPtabela_celularLayout.createSequentialGroup()
                        .addGroup(jPtabela_celularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addGroup(jPtabela_celularLayout.createSequentialGroup()
                                .addComponent(jTPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jBPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jBLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 219, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPtabela_celularLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(328, 328, 328))
        );
        jPtabela_celularLayout.setVerticalGroup(
            jPtabela_celularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPtabela_celularLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPtabela_celularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBPesquisar)
                    .addComponent(jBLimpar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPtabela_celular, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(jPtabela_celular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPinfo_celular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1025, 1025, 1025))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPinfo_celular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(83, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
        );

        jPanel3.setBackground(new java.awt.Color(0, 51, 102));

        jLabel2.setFont(new java.awt.Font("Bosch Sans", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Cell Phone");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        jBcancelar.setBackground(new java.awt.Color(140, 158, 177));
        jBcancelar.setFont(new java.awt.Font("Bosch Office Sans", 0, 18)); // NOI18N
        jBcancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back-icon.png"))); // NOI18N
        jBcancelar.setIconTextGap(-70);
        jBcancelar.setInheritsPopupMenu(true);
        jBcancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBcancelarActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Bosch Sans Black", 0, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("BACK");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLID, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(460, 460, 460))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jBcancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)))
                .addContainerGap(8, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLID, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(14, 14, 14)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jBcancelar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBcadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBcadastrarActionPerformed
        cadastro_celular();
        limpartabela();
        pesquisa_celular();
        limparcampos(jPinfo_celular);
    }//GEN-LAST:event_jBcadastrarActionPerformed

    private void jBAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAlterarActionPerformed
        alterar_celular();
        limparcampos(jPinfo_celular);
        pesquisa_celular();
    }//GEN-LAST:event_jBAlterarActionPerformed

    private void jBexcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBexcluirActionPerformed
        excluir_celular();
        limparcampos(jPinfo_celular);
        pesquisa_celular();
    }//GEN-LAST:event_jBexcluirActionPerformed

    private void jBlimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBlimparActionPerformed
        limparcampos(jPinfo_celular);
    }//GEN-LAST:event_jBlimparActionPerformed

    private void jBPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBPesquisarActionPerformed
        pesquisa_celular();
    }//GEN-LAST:event_jBPesquisarActionPerformed

    private void jBLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBLimparActionPerformed
        limpartabela();
    }//GEN-LAST:event_jBLimparActionPerformed

    private void jBcancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBcancelarActionPerformed
        Home h = new Home();
        this.dispose();
        h.setVisible(true);
    }//GEN-LAST:event_jBcancelarActionPerformed

    private void jTPreco_celularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTPreco_celularActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTPreco_celularActionPerformed

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
            java.util.logging.Logger.getLogger(Celular.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Celular.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Celular.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Celular.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Celular dialog = new Celular(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jBLimpar;
    private javax.swing.JButton jBPesquisar;
    private javax.swing.JButton jBcadastrar;
    private javax.swing.JButton jBcancelar;
    private javax.swing.JButton jBexcluir;
    private javax.swing.JButton jBlimpar;
    private javax.swing.JComboBox<String> jCBarmazenamento_celular;
    private javax.swing.JComboBox<String> jCBestado_celular;
    private javax.swing.JComboBox<String> jCBmemoria_ram_celular;
    private javax.swing.JLabel jLID;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPinfo_celular;
    private javax.swing.JPanel jPtabela_celular;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTPesquisa;
    private javax.swing.JTextField jTPreco_celular;
    private javax.swing.JTextField jTbateria_celular;
    private javax.swing.JTextField jTmodelo_celular;
    private javax.swing.JTextField jTprocessador_celular;
    private javax.swing.JTable jTtabela_pesquisa;
    // End of variables declaration//GEN-END:variables
}
