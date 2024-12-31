package PJBL.GUI;

import PJBL.Sistemas.LoginException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListProduto {
    private JFrame frame;
    private JTabbedPane tabbedPane;
    private List<String> produtos;
    private static final String FILE_PATH = "src/PJBL/";
    private static final String FILE_NAME = FILE_PATH + "produtos.txt";
    private List<String> terrestres = new ArrayList<>();
    private List<String> aquaticos = new ArrayList<>();
    private List<String> aereos = new ArrayList<>();

    public ListProduto() {
        try {
            this.produtos = carregarProdutos();
            if (produtos.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Não há nenhum produto cadastrado.");
                return; // Não continue se não houver produtos
            } else {
                for (String produto : produtos) {
                    String[] partes = produto.split(",");
                    if (partes.length > 1) {
                        switch (partes[1]) {
                            case "Carro":
                                terrestres.add(produto);
                                break;
                            case "Embarcação":
                                aquaticos.add(produto);
                                break;
                            case "Avião":
                                aereos.add(produto);
                                break;
                            default:
                                System.out.println("Tipo desconhecido: " + partes[1]);
                                break;
                        }
                    }
                }
            }
        } catch (LoginException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar produtos: " + e.getMessage());
            return; // Não continue se houver um erro ao carregar os produtos
        }

        frame = new JFrame("Listar Produtos");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        tabbedPane = new JTabbedPane();

        // Cria tabelas para cada tipo de produto
        createTable("Terrestres", terrestres);
        createTable("Aquáticos", aquaticos);
        createTable("Aéreos", aereos);

        frame.add(tabbedPane, BorderLayout.CENTER);

        JButton backButton = new JButton("Voltar");
        backButton.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        backButton.setPreferredSize(new Dimension(100, 30));
        backButton.addActionListener(e -> frame.dispose());
        frame.add(backButton, BorderLayout.NORTH);

        frame.setVisible(true);
    }

    private void createTable(String tipo, List<String> produtosTipo) {
        if (produtosTipo.isEmpty()) {
            return;
        }

        // Verifica o número de campos em um produto
        int numCampos = produtosTipo.get(0).split(",").length;

        // Cria os nomes das colunas com base no número de campos
        String[] columnNames = new String[numCampos];
        if (tipo.equals("Terrestres")) {
            columnNames = new String[]{"id", "Tipo", "Marca", "Modelo", "Ano", "P. Motor", "Cor", "Cap. Passageiros", "Preço", "Estoque", "Nº rodas", "Carroceria", "Assentos", "N° portas", "Cap. Porta-malas", "Tipo motor", "P. Motor", "Combustível", "Câmbio"};
        } else if (tipo.equals("Aquáticos")) {
            columnNames = new String[]{"id", "Tipo", "Marca", "Modelo", "Ano", "Km navegados", "Cor", "Cap. Passageiros", "Preço", "Estoque", "Propulsão"};
        } else if (tipo.equals("Aéreos")) {
            columnNames = new String[]{"id", "Tipo", "Marca", "Modelo", "Ano", "Km rodados", "Cor", "Passageiros", "Preço", "Estoque", "N° motores"};
        }

        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (String produto : produtosTipo) {
            String[] partes = produto.split(",");
            if (partes.length > 1) {
                Object[] o = new Object[partes.length];
                for (int i = 0; i < partes.length; i++) {
                    o[i] = partes[i];
                }
                model.addRow(o);
            }
        }

        JTable table = new JTable(model);
        table.setFillsViewportHeight(true);

        // Increase row height
        table.setRowHeight(25);

        // Increase font size
        Font font = table.getFont();
        Font newFont = font.deriveFont(font.getStyle(), 15);
        table.setFont(newFont);

        JScrollPane scrollPane = new JScrollPane(table);

        // Create a panel to hold the table and add a border to it
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        tablePanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        tabbedPane.addTab(tipo, tablePanel);
    }

    public static List<String> carregarProdutos() throws LoginException {
        List<String> produtos = new ArrayList<>();
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("Arquivo de produtos criado com sucesso.");
                } else {
                    throw new LoginException("Erro ao criar o arquivo de produtos: motivo desconhecido.");
                }
            } catch (IOException e) {
                throw new LoginException("Erro ao criar o arquivo de produtos: " + e.getMessage());
            }
        }

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                produtos.add(linha);
            }
        } catch (IOException e) {
            throw new LoginException("Erro ao carregar produtos: " + e.getMessage());
        }
        return produtos;
    }
}
