package br.unipar.views;
import br.unipar.models.Cliente;
import br.unipar.models.ItemVenda;
import br.unipar.models.Produto;
import br.unipar.models.Venda;
import br.unipar.services.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class VendaView extends JFrame {
    private JTable clientesTable;
    private JTable produtosTable;
    private JTable itensVendaTable;
    private JTextField observacoesField;
    private JTextField quantidadeField;
    private JTextField totalField;
    private JTextField produtoField;
    private JTextField precoField;
    private JTextField descricaoField;
    private JButton adicionarItemButton;
    private JButton realizarVendaButton;
    private JButton atualizarClientesButton;
    private JButton atualizarProdutosButton;
    private ApiService apiService;
    private List<Cliente> clientes;
    private List<Produto> produtos;
    private DefaultTableModel itensVendaModel;
    private JLabel totalVendaLabel;

    public VendaView() {
        setTitle("Ponto de Venda");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Painel de Controle Superior
        JPanel controlPanel = new JPanel(new GridLayout(1, 3, 10, 10));


        // Painel de Clientes e Vendedor
        JPanel clienteVendedorPanel = new JPanel(new GridLayout(1, 2, 10, 10));

        // Tamanho máximo da tabela de clientes
        int maxHeight = 100;

// Painel de Clientes
        JPanel clientePanel = new JPanel(new BorderLayout());
        clientePanel.setBorder(BorderFactory.createTitledBorder("Clientes"));
        clientePanel.add(new JLabel("Clientes"), BorderLayout.NORTH);
        clientesTable = new JTable();
        clientesTable.setPreferredScrollableViewportSize(new Dimension(clientesTable.getPreferredSize().width, maxHeight)); // Define a altura máxima da tabela de clientes
        clientePanel.add(new JScrollPane(clientesTable), BorderLayout.CENTER);
        clientePanel.setPreferredSize(new Dimension(300, 100)); // Definindo um tamanho menor
        clienteVendedorPanel.add(clientePanel);


// Painel de Produtos
        JPanel produtosPanel = new JPanel(new BorderLayout());
        produtosPanel.setBorder(BorderFactory.createTitledBorder("Produtos"));
        produtosPanel.add(new JLabel("Produtos"), BorderLayout.NORTH);
        produtosTable = new JTable();
        produtosTable.setPreferredScrollableViewportSize(new Dimension(produtosTable.getPreferredSize().width, maxHeight)); // Define a altura máxima da tabela de produtos
        produtosPanel.add(new JScrollPane(produtosTable), BorderLayout.CENTER); // Alterado de 'vendedorPanel' para 'produtoPanel'
        produtosPanel.setPreferredSize(new Dimension(300, 100)); // Definindo um tamanho menor
        clienteVendedorPanel.add(produtosPanel);



// Painel de Itens da Venda
        itensVendaModel = new DefaultTableModel(new Object[]{"Produto", "Quantidade", "Valor Unitário", "Valor Total"}, 0);
        itensVendaTable = new JTable(itensVendaModel);
        JPanel iitensVendaPanel = new JPanel(new BorderLayout());
        iitensVendaPanel.setBorder(BorderFactory.createTitledBorder("Itens de Venda"));
        iitensVendaPanel.add(new JScrollPane(itensVendaTable), BorderLayout.CENTER);
        iitensVendaPanel.setPreferredSize(new Dimension(600, 200)); // Definindo um tamanho para o painel de itens de venda



        // Painel de Total da Venda
        JPanel totalVendaPanel = new JPanel(new BorderLayout());
        totalVendaPanel.setBorder(BorderFactory.createTitledBorder("Total Venda R$"));
        totalVendaLabel = new JLabel("0,00", SwingConstants.CENTER);
        totalVendaLabel.setFont(new Font("Arial", Font.BOLD, 24));
        totalVendaPanel.add(totalVendaLabel, BorderLayout.CENTER);

        // Painel de Produto
        JPanel produtoPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        produtoPanel.setBorder(BorderFactory.createTitledBorder("Produto"));

        // Define o tamanho máximo para os campos de texto
        int maxTextFieldWidth = 100; // Escolha o valor máximo que deseja para a largura dos campos de texto

        produtoPanel.add(new JLabel("Produto:"));
        produtoField = new JTextField();
        produtoPanel.add(produtoField);

        produtoPanel.add(new JLabel("Qtde:"));
        quantidadeField = new JTextField();
        produtoPanel.add(quantidadeField);

        produtoPanel.add(new JLabel("Preço:"));
        precoField = new JTextField();
        produtoPanel.add(precoField);

        produtoPanel.add(new JLabel("Desc:"));
        descricaoField = new JTextField();
        produtoPanel.add(descricaoField);

        // Define o tamanho máximo para os campos de texto de produto
        produtoField.setMaximumSize(new Dimension(maxTextFieldWidth, produtoField.getPreferredSize().height));
        quantidadeField.setMaximumSize(new Dimension(maxTextFieldWidth, quantidadeField.getPreferredSize().height));
        precoField.setMaximumSize(new Dimension(maxTextFieldWidth, 100));
        descricaoField.setMaximumSize(new Dimension(maxTextFieldWidth, descricaoField.getPreferredSize().height));

        // Painel de Observações
        JPanel observacoesPanel = new JPanel(new BorderLayout());
        observacoesPanel.setBorder(BorderFactory.createTitledBorder("Observações"));
        observacoesField = new JTextField();
        observacoesPanel.add(observacoesField, BorderLayout.CENTER);

        // Painel de Itens da Venda
        itensVendaModel = new DefaultTableModel(new Object[]{"Produto", "Quantidade", "Valor Unitário", "Valor Total"}, 0);
        itensVendaTable = new JTable(itensVendaModel);
        JPanel itensVendaPanel = new JPanel(new BorderLayout());
        itensVendaPanel.setBorder(BorderFactory.createTitledBorder("Itens / Qtde"));
        itensVendaPanel.add(new JScrollPane(itensVendaTable), BorderLayout.CENTER);

        // Painel de Ações
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        adicionarItemButton = new JButton("Adicionar Item", new ImageIcon("path_to_add_icon"));
        actionPanel.add(adicionarItemButton);
        realizarVendaButton = new JButton("Realizar Venda", new ImageIcon("path_to_checkout_icon"));
        actionPanel.add(realizarVendaButton);

        // Adicionando painéis ao frame principal
        add(controlPanel, BorderLayout.NORTH);
        add(clienteVendedorPanel, BorderLayout.WEST);
        add(totalVendaPanel, BorderLayout.EAST);
        add(produtoPanel, BorderLayout.CENTER);
        add(observacoesPanel, BorderLayout.SOUTH);
        add(itensVendaPanel, BorderLayout.SOUTH);
        add(actionPanel, BorderLayout.SOUTH);

//        // Carregar dados dos clientes e produtos
//        listarClientes();
//        listarProdutos();

        // Configurar ações dos botões
        adicionarItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarItemVenda();
            }
        });

        realizarVendaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarVenda();
            }
        });

//        // Atualizar automaticamente os clientes e produtos a cada 5 minutos
//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                listarClientes();
//                listarProdutos();
//            }
//        }, 0, 5 * 60 * 1000); // Atualiza a cada 5 minutos (5 * 60 * 1000 ms)

    }

    private void listarClientes() {
        apiService.listarClientes().enqueue(new Callback<List<Cliente>>() {
            @Override
            public void onResponse(Call<List<Cliente>> call, Response<List<Cliente>> response) {
                if (response.isSuccessful()) {
                    clientes = response.body();
                    atualizarTabelaClientes(clientes);
                    registrarLog("Obtenção de clientes", "Sucesso");
                }
            }

            @Override
            public void onFailure(Call<List<Cliente>> call, Throwable t) {
                JOptionPane.showMessageDialog(VendaView.this, "Erro ao listar clientes.");
                registrarLog("Obtenção de clientes", "Falha");
            }
        });
    }

    private void listarProdutos() {
        apiService.listarProdutos().enqueue(new Callback<List<Produto>>() {
            @Override
            public void onResponse(Call<List<Produto>> call, Response<List<Produto>> response) {
                if (response.isSuccessful()) {
                    produtos = response.body();
                    atualizarTabelaProdutos(produtos);
                    registrarLog("Obtenção de produtos", "Sucesso");
                }
            }

            @Override
            public void onFailure(Call<List<Produto>> call, Throwable t) {
                JOptionPane.showMessageDialog(VendaView.this, "Erro ao listar produtos.");
                registrarLog("Obtenção de produtos", "Falha");
            }
        });
    }


    private void atualizarTabelaClientes(List<Cliente> clientes) {
        String[] colunas = {"ID", "Nome", "Telefone", "Email"};
        Object[][] dados = new Object[clientes.size()][4];
        for (int i = 0; i < clientes.size(); i++) {
            Cliente cliente = clientes.get(i);
            dados[i][0] = cliente.getId();
            dados[i][1] = cliente.getNome();
            dados[i][2] = cliente.getTelefone();
            dados[i][3] = cliente.getEmail();
        }
        clientesTable.setModel(new DefaultTableModel(dados, colunas));
    }

    private void atualizarTabelaProdutos(List<Produto> produtos) {
        String[] colunas = {"ID", "Descrição", "Valor", "Categoria"};
        Object[][] dados = new Object[produtos.size()][4];
        for (int i = 0; i < produtos.size(); i++) {
            Produto produto = produtos.get(i);
            dados[i][0] = produto.getId();
            dados[i][1] = produto.getDescricao();
            dados[i][2] = produto.getValor();
            dados[i][3] = produto.getCategoria();
        }
        produtosTable.setModel(new DefaultTableModel(dados, colunas));
    }

    private void adicionarItemVenda() {
        int produtoIndex = produtosTable.getSelectedRow();

        if (produtoIndex == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um produto.");
            return;
        }

        Produto produto = produtos.get(produtoIndex);

        int quantidade;
        try {
            quantidade = Integer.parseInt(quantidadeField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Quantidade inválida.");
            return;
        }

        BigDecimal valorTotal = produto.getValor().multiply(new BigDecimal(quantidade));

        // Adicionar o item na tabela de itens da venda
        itensVendaModel.addRow(new Object[]{produto.getDescricao(), quantidade, produto.getValor(), valorTotal});
    }

    private void realizarVenda() {
        int clienteIndex = clientesTable.getSelectedRow();

        if (clienteIndex == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um cliente.");
            return;
        }

        Cliente cliente = clientes.get(clienteIndex);
        String observacoes = observacoesField.getText();

        BigDecimal totalVenda = BigDecimal.ZERO;
        for (int i = 0; i < itensVendaModel.getRowCount(); i++) {
            totalVenda = totalVenda.add((BigDecimal) itensVendaModel.getValueAt(i, 3));
        }

        Venda venda = new Venda();
        venda.setObservacoes(observacoes);
        venda.setData(LocalDateTime.now());
        venda.setTotal(totalVenda);
        venda.setClienteId(cliente.getId());

        apiService.criarVenda(venda).enqueue(new Callback<Venda>() {
            @Override
            public void onResponse(Call<Venda> call, Response<Venda> response) {
                if (response.isSuccessful()) {
                    Venda vendaCriada = response.body();
                    for (int i = 0; i < itensVendaModel.getRowCount(); i++) {
                        adicionarItemVenda(vendaCriada, i);
                    }
                    JOptionPane.showMessageDialog(VendaView.this, "Venda realizada com sucesso!");
                    registrarLog("Inserção de venda", "Sucesso");
                } else {
                    JOptionPane.showMessageDialog(VendaView.this, "Erro ao criar venda.");
                    registrarLog("Inserção de venda", "Falha");
                }
            }

            @Override
            public void onFailure(Call<Venda> call, Throwable t) {
                JOptionPane.showMessageDialog(VendaView.this, "Erro ao criar venda.");
                registrarLog("Inserção de venda", "Falha");
            }
        });
    }


    private void adicionarItemVenda(Venda venda, int rowIndex) {
        Produto produto = produtos.stream()
                .filter(p -> p.getDescricao().equals(itensVendaModel.getValueAt(rowIndex, 0)))
                .findFirst().orElse(null);

        if (produto != null) {
            ItemVenda itemVenda = new ItemVenda();
            itemVenda.setVendaId(venda.getId());
            itemVenda.setProdutoId(produto.getId());
            itemVenda.setQuantidade((Integer) itensVendaModel.getValueAt(rowIndex, 1));
            itemVenda.setValorUnitario((BigDecimal) itensVendaModel.getValueAt(rowIndex, 2));
            itemVenda.setValorTotal((BigDecimal) itensVendaModel.getValueAt(rowIndex, 3));

            apiService.adicionarItemVenda(itemVenda).enqueue(new Callback<ItemVenda>() {
                @Override
                public void onResponse(Call<ItemVenda> call, Response<ItemVenda> response) {
                    if (!response.isSuccessful()) {
                        JOptionPane.showMessageDialog(VendaView.this, "Erro ao adicionar item de venda.");
                    }
                }

                @Override
                public void onFailure(Call<ItemVenda> call, Throwable t) {
                    JOptionPane.showMessageDialog(VendaView.this, "Erro ao adicionar item de venda.");
                }
            });
        }
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VendaView vendaView = new VendaView();
            vendaView.setVisible(true);
        });
    }
}

