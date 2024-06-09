/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.unipar.views;

import br.unipar.models.Cliente;
import br.unipar.retrofit.RetrofitConfig;
import br.unipar.services.ApiService;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *
 * @author lucia
 */
public class ClientePanel extends javax.swing.JFrame {
    
    private ApiService apiService;
    private List<Cliente> clientes;
    private VendaFrame vendaFrame;

    /**
     * Creates new form ClientePanel
     */
    public ClientePanel(VendaFrame vendaFrame) {
        initComponents();
        this.vendaFrame = vendaFrame;
        apiService = RetrofitConfig.getApiService();
        clientes = new ArrayList<>();
        
        listarClientes();
        refreshListas();

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
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        clienteTableModel = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Selecione o Cliente");

        clienteTableModel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Nome", "Telefone", "Email"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        clienteTableModel.setColumnSelectionAllowed(true);
        clienteTableModel.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(clienteTableModel);
        clienteTableModel.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        if (clienteTableModel.getColumnModel().getColumnCount() > 0) {
            clienteTableModel.getColumnModel().getColumn(0).setResizable(false);
            clienteTableModel.getColumnModel().getColumn(1).setResizable(false);
            clienteTableModel.getColumnModel().getColumn(2).setResizable(false);
            clienteTableModel.getColumnModel().getColumn(3).setResizable(false);
        }

        jButton1.setText("Selecionar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(7, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int clienteIndex = clienteTableModel.getSelectedRow();

        if (clienteIndex == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um cliente.");
            return;
        }

        Cliente cliente = clientes.get(clienteIndex);
        vendaFrame.setClienteFields(cliente);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(ClientePanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientePanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientePanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientePanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                VendaFrame vendaFrame = new VendaFrame();
                vendaFrame.setVisible(true);
                new ClientePanel(vendaFrame).setVisible(true);
            }
        });
    }
    private void listarClientes() {
        ApiService apiService = RetrofitConfig.getApiService();
        apiService.listarClientes().enqueue(new Callback<List<Cliente>>() {
            @Override
            public void onResponse(Call<List<Cliente>> call, Response<List<Cliente>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    clientes = response.body();

                    SwingUtilities.invokeLater(() -> {
                        DefaultTableModel defaultTableModel = new DefaultTableModel();
                        defaultTableModel.addColumn("ID");
                        defaultTableModel.addColumn("Nome");
                        defaultTableModel.addColumn("email");
                        defaultTableModel.addColumn("Telefone");

                        for (Cliente c : clientes) {
                            defaultTableModel.addRow(new Object[]{
                               c.getId(), c.getNome(), c.getEmail(), c.getTelefone()
                            });
                        }

                        clienteTableModel.setModel(defaultTableModel);
                    });

                    registrarLog("Obtenção dos clientes", "Sucesso");
                } else {
                    JOptionPane.showMessageDialog(ClientePanel.this, "Failed to load clients.", "Error", JOptionPane.ERROR_MESSAGE);
                    registrarLog("Obtenção dos clientes", "Falha");
                }
            }

            @Override
            public void onFailure(Call<List<Cliente>> call, Throwable t) {
                JOptionPane.showMessageDialog(ClientePanel.this, "Error: " + t.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                registrarLog("Obtenção dos clientes", "Falha");
            }
        });
    }

    private void registrarLog(String operacao, String status) {
        String logFilePath = "log.txt";
        try (PrintWriter writer = new PrintWriter(new FileWriter(logFilePath, true))) {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String dataHora = now.format(formatter);
            writer.println(dataHora + " - Operação: " + operacao + " - Status: " + status);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Cliente getSelectedCliente() {
        int selectedRow = clienteTableModel.getSelectedRow();
        if (selectedRow != -1) {

            Cliente cliente = new Cliente();
            Object idValue = clienteTableModel.getValueAt(selectedRow, 0);
            if (idValue instanceof Long) {
                cliente.setId((Long) idValue);
            } else if (idValue instanceof String) {
                // Tratar o caso em que o valor é uma String e tentar converter para Long
                try {
                    cliente.setId(Long.parseLong((String) idValue));
                } catch (NumberFormatException e) {
                    // Lidar com o caso em que não é possível converter para Long
                    e.printStackTrace();
                    return null; // Ou outra ação apropriada, como lançar uma exceção
                }
            } else {
                // Lidar com o caso em que o valor não é nem Long nem String
                return null; // Ou outra ação apropriada, como lançar uma exceção
            }

            cliente.setNome((String) clienteTableModel.getValueAt(selectedRow, 1));
            cliente.setEmail((String) clienteTableModel.getValueAt(selectedRow, 2));
            cliente.setTelefone((String) clienteTableModel.getValueAt(selectedRow, 3));
            return cliente;
        }
        return null;
    }
    public javax.swing.JTable getClientesTable() {
        return clienteTableModel;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable clienteTableModel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    private void refreshListas() {
        // Atualizar automaticamente os clientes a cada 5 minutos
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                listarClientes();

            }
        }, 0, 5 * 60 * 1000); // Atualiza a cada 5 minutos (5 * 60 * 1000 ms)    }
    }
}
