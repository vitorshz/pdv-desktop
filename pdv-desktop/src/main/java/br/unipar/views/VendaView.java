package br.unipar.views;
import br.unipar.models.Cliente;
import br.unipar.models.ItemVenda;
import br.unipar.models.Produto;
import br.unipar.models.Venda;
import br.unipar.retrofit.RetrofitConfig;
import br.unipar.services.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class VendaView extends JFrame {
    private JTable clientesTable;
    private JTable produtosTable;
    private JTable itensVendaTable;
    private JTextField observacoesField;
    private JTextField quantidadeField;
    private JButton adicionarItemButton;
    private JButton realizarVendaButton;
    private JButton atualizarClientesButton;
    private JButton atualizarProdutosButton;
    private ApiService apiService;
    private List<Cliente> clientes;
    private List<Produto> produtos;
    private DefaultTableModel itensVendaModel;

    public VendaView() {
        apiService = RetrofitConfig.getApiService();
        setTitle("Ponto de Venda");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Painel de Clientes
        JPanel clientesPanel = new JPanel(new BorderLayout());
        clientesPanel.setBorder(BorderFactory.createTitledBorder("Clientes"));
        clientesTable = new JTable();
        clientesPanel.add(new JScrollPane(clientesTable), BorderLayout.CENTER);

        atualizarClientesButton = new JButton("Atualizar Clientes", new ImageIcon("path_to_refresh_icon"));
        atualizarClientesButton.addActionListener(e -> listarClientes());
        clientesPanel.add(atualizarClientesButton, BorderLayout.SOUTH);

        // Painel de Produtos
        JPanel produtosPanel = new JPanel(new BorderLayout());
        produtosPanel.setBorder(BorderFactory.createTitledBorder("Produtos"));
        produtosTable = new JTable();
        produtosPanel.add(new JScrollPane(produtosTable), BorderLayout.CENTER);

        atualizarProdutosButton = new JButton("Atualizar Produtos", new ImageIcon("path_to_refresh_icon"));
        atualizarProdutosButton.addActionListener(e -> listarProdutos());
        produtosPanel.add(atualizarProdutosButton, BorderLayout.SOUTH);

        // Painel de Venda
        JPanel vendaPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        vendaPanel.setBorder(BorderFactory.createTitledBorder("Detalhes da Venda"));

        vendaPanel.add(new JLabel("Observações:"));
        observacoesField = new JTextField();
        vendaPanel.add(observacoesField);

        vendaPanel.add(new JLabel("Quantidade:"));
        quantidadeField = new JTextField();
        vendaPanel.add(quantidadeField);

        adicionarItemButton = new JButton("Adicionar Item", new ImageIcon("path_to_plus_icon"));
        vendaPanel.add(adicionarItemButton);

        realizarVendaButton = new JButton("Realizar Venda", new ImageIcon("path_to_cart_icon"));
        vendaPanel.add(realizarVendaButton);

        // Tabela de Itens da Venda
        itensVendaModel = new DefaultTableModel(new Object[]{"Produto", "Quantidade", "Valor Unitário", "Valor Total"}, 0);
        itensVendaTable = new JTable(itensVendaModel);
        JPanel itensVendaPanel = new JPanel(new BorderLayout());
        itensVendaPanel.setBorder(BorderFactory.createTitledBorder("Itens da Venda"));
        itensVendaPanel.add(new JScrollPane(itensVendaTable), BorderLayout.CENTER);

        // Painel Principal
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, clientesPanel, produtosPanel);
        splitPane.setDividerLocation(600);

        add(splitPane, BorderLayout.NORTH);
        add(vendaPanel, BorderLayout.CENTER);
        add(itensVendaPanel, BorderLayout.SOUTH);

        // Carregar dados dos clientes e produtos
        listarClientes();
        listarProdutos();

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
    }

    private void listarClientes() {
        apiService.listarClientes().enqueue(new Callback<List<Cliente>>() {
            @Override
            public void onResponse(Call<List<Cliente>> call, Response<List<Cliente>> response) {
                if (response.isSuccessful()) {
                    clientes = response.body();
                    atualizarTabelaClientes(clientes);
                }
            }

            @Override
            public void onFailure(Call<List<Cliente>> call, Throwable t) {
                JOptionPane.showMessageDialog(VendaView.this, "Erro ao listar clientes.");
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
                }
            }

            @Override
            public void onFailure(Call<List<Produto>> call, Throwable t) {
                JOptionPane.showMessageDialog(VendaView.this, "Erro ao listar produtos.");
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
        venda.setData(LocalDate.now());
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
                } else {
                    JOptionPane.showMessageDialog(VendaView.this, "Erro ao criar venda.");
                }
            }

            @Override
            public void onFailure(Call<Venda> call, Throwable t) {
                JOptionPane.showMessageDialog(VendaView.this, "Erro ao criar venda.");
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VendaView vendaView = new VendaView();
            vendaView.setVisible(true);
        });
    }
}

