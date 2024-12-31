package PJBL.GUI;

import PJBL.Sistemas.LoginException;
import PJBL.Sistemas.SistemaProduto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class EditProduto extends JFrame {
    private static final String FILE_PATH = "src/PJBL/";
    private static final String FILE_NAME = FILE_PATH + "produtos.txt";
    private List<String> produtos;
    private JTextField idField;
    private JPanel panelTerrestre;
    private JPanel panelAquatico;
    private JPanel panelAereo;

    public EditProduto() {
        try {
            produtos = SistemaProduto.carregarProdutos();
        } catch (LoginException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar produtos: " + e.getMessage());
        }
        setLayout(null);
        setSize(500, 760);
        setTitle("Editar Produto");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        JLabel idLabel = new JLabel("ID do Produto:");
        idLabel.setBounds(10, 10, 100, 30);
        add(idLabel);

        idField = new JTextField();
        idField.setBounds(120, 10, 200, 30);

        JButton searchButton = new JButton("Pesquisar");
        searchButton.setBounds(330, 10, 100, 30);
        add(searchButton);
        add(idField);idField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchButton.doClick();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String produto = findProductById(id);
                if (produto != null) {
                    clearPanel();
                    String[] partes = produto.split(",");
                    switch (partes[1]) {
                        case "Carro":
                            setupPanelTerrestre(produto);
                            break;
                        case "Embarcação":
                            setupPanelAquatico(produto);
                            break;
                        case "Avião":
                            setupPanelAereo(produto);
                            break;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Produto não encontrado!");
                }
            }
        });
    }

    private String findProductById(String id) {
        for (String produto : produtos) {
            String[] partes = produto.split(",");
            if (partes[0].equals(id)) {
                return produto;
            }
        }
        return null;
    }

    private void setupPanelTerrestre(String produto) {
        String[] partes = produto.split(",");
        String[] labels = {"nada", "codVeiculo", "Marca", "Modelo", "Ano", "Quilometragem", "Cor", "Capacidade de passageiros",
                "Preço", "Estoque", "Qtd Rodas", "Tipo de carroceria", "Qtd de assentos", "Portas", "Capacidade Porta Malas",
                "Tipo do Motor", "Potencia Motor", "Tipo Combustivel", "Tipo cambio"};

        panelTerrestre = new JPanel();
        panelTerrestre.setBounds(10, 40, 480, 760);
        panelTerrestre.setLayout(null);

        int y = 10;
        for (int i = 2; i < labels.length; i++) {
            JLabel label = new JLabel(labels[i] + ":");
            label.setBounds(10, y, 180, 30);
            panelTerrestre.add(label);

            JTextField field = new JTextField(partes[i]);
            field.setBounds(190, y, 260, 30);
            if (!labels[i].equals("Preço") && !labels[i].equals("Estoque")) {
                field.setEditable(false);
            }
            panelTerrestre.add(field);

            y += 35;
        }

        JButton cancelButton = new JButton("Cancelar");
        cancelButton.setBackground(new Color(255, 99, 99));
        cancelButton.setBounds(10, 620, 150, 50);
        panelTerrestre.add(cancelButton);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JButton editarButton = new JButton("Editar");
        editarButton.setBackground(new Color(0, 222, 110));
        editarButton.setBounds(220, 620, 200, 50);
        panelTerrestre.add(editarButton);
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtém os valores dos campos de texto 7 e 8
                String novoPreco = ((JTextField) panelTerrestre.getComponent(15)).getText();
                String novoEstoque = ((JTextField) panelTerrestre.getComponent(17)).getText();

                // Atualiza o produto na lista de produtos
                String produto = produtos.get(Integer.parseInt(idField.getText()) - 1);
                String[] partes = produto.split(",");
                partes[8] = novoPreco;
                partes[10] = novoEstoque;
                produtos.set(Integer.parseInt(idField.getText()) - 1, String.join(",", partes));

                // Salva a lista de produtos atualizada
                try {
                    salvarProdutos(produtos);
                    JOptionPane.showMessageDialog(null, "Produto editado com sucesso");
                    dispose();
                } catch (LoginException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao salvar produto: " + ex.getMessage());
                }
            }
        });
        getContentPane().add(panelTerrestre);
        getContentPane().revalidate();
        getContentPane().repaint();
    }

    private void setupPanelAquatico(String produto) {
        String[] partes = produto.split(",");
        String[] labels = {"codVeiculo", "Tipo", "Marca", "Modelo", "Ano", "Quilometragem", "Cor", "Capacidade de passageiros",
                "Preço", "Estoque", "Tipo de propulsão", "Alcance de navegação"};

        panelAquatico = new JPanel();
        panelAquatico.setBounds(10, 40, 480, 760);
        panelAquatico.setLayout(null);

        int y = 10;
        for (int i = 0; i < labels.length-1; i++) {
            JLabel label = new JLabel(labels[i] + ":");
            label.setBounds(10, y, 180, 30);
            panelAquatico.add(label);

            String fieldValue = (i < partes.length) ? partes[i] : "";
            JTextField field = new JTextField(fieldValue);
            field.setBounds(190, y, 260, 30);
            if (!labels[i].equals("Preço") && !labels[i].equals("Estoque")) {
                field.setEditable(false);
            }
            panelAquatico.add(field);

            y += 35;
        }

        JButton cancelButton = new JButton("Cancelar");
        cancelButton.setBackground(new Color(255, 99, 99));
        cancelButton.setBounds(10, 620, 150, 50);
        panelAquatico.add(cancelButton);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JButton editarButton = new JButton("Editar");
        editarButton.setBackground(new Color(0, 222, 110));
        editarButton.setBounds(220, 620, 200, 50);
        panelAquatico.add(editarButton);
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtém os valores dos campos de texto 7 e 8
                String novoPreco = ((JTextField) panelAquatico.getComponent(17)).getText();
                String novoEstoque = ((JTextField) panelAquatico.getComponent(19)).getText();

                // Atualiza o produto na lista de produtos
                String produto = produtos.get(Integer.parseInt(idField.getText()) - 1);
                String[] partes = produto.split(",");
                partes[8] = novoPreco;
                partes[9] = novoEstoque;
                produtos.set(Integer.parseInt(idField.getText()) - 1, String.join(",", partes));

                // Salva a lista de produtos atualizada
                try {
                    salvarProdutos(produtos);
                    JOptionPane.showMessageDialog(null, "Produto editado com sucesso");
                    dispose();
                } catch (LoginException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao salvar produto: " + ex.getMessage());
                }
            }
        });
        getContentPane().add(panelAquatico);
        getContentPane().revalidate();
        getContentPane().repaint();
    }

    private void setupPanelAereo(String produto) {
        String[] partes = produto.split(",");
        String[] labels = {"codVeiculo", "Tipo", "Marca", "Modelo", "Ano", "Quilometragem", "Cor", "Capacidade de passageiros",
                "Preço", "Estoque", "Tipo de propulsão", "Número de motores", "Tipo de avião", "Alcance de voo"};

        panelAereo = new JPanel();
        panelAereo.setBounds(10, 40, 480, 760);
        panelAereo.setLayout(null);

        int y = 10;
        for (int i = 0; i < labels.length; i++) {
            JLabel label = new JLabel(labels[i] + ":");
            label.setBounds(10, y, 180, 30);
            panelAereo.add(label);

            String fieldValue = (i < partes.length) ? partes[i] : "";
            JTextField field = new JTextField(fieldValue);
            field.setBounds(190, y, 260, 30);
            if (!labels[i].equals("Preço") && !labels[i].equals("Estoque")) {
                field.setEditable(false);
            }
            panelAereo.add(field);

            y += 35;
        }

        JButton cancelButton = new JButton("Cancelar");
        cancelButton.setBackground(new Color(255, 99, 99));
        cancelButton.setBounds(10, 620, 150, 50);
        panelAereo.add(cancelButton);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JButton editarButton = new JButton("Editar");
        editarButton.setBackground(new Color(0, 222, 110));
        editarButton.setBounds(220, 620, 200, 50);
        panelAereo.add(editarButton);
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtém os valores dos campos de texto 7 e 8
                String novoPreco = ((JTextField) panelAereo.getComponent(17)).getText();
                String novoEstoque = ((JTextField) panelAereo.getComponent(19)).getText();

                // Atualiza o produto na lista de produtos
                String produto = produtos.get(Integer.parseInt(idField.getText()) - 1);
                String[] partes = produto.split(",");
                partes[8] = novoPreco;
                partes[9] = novoEstoque;
                produtos.set(Integer.parseInt(idField.getText()) - 1, String.join(",", partes));

                // Salva a lista de produtos atualizada
                try {
                    salvarProdutos(produtos);
                    JOptionPane.showMessageDialog(null, "Produto editado com sucesso");
                    dispose();
                } catch (LoginException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao salvar produto: " + ex.getMessage());
                }
            }
        });
        getContentPane().add(panelAereo);
        getContentPane().revalidate();
        getContentPane().repaint();
    }

    private static void salvarProdutos(List<String> produtos) throws LoginException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (String produto : produtos) {
                bw.write(produto);
                bw.newLine();
            }
        } catch (IOException e) {
            throw new LoginException("Erro ao salvar produtos: " + e.getMessage());
        }
    }

    private void clearPanel() {
        for (Component component : getContentPane().getComponents()) {
            if (component instanceof JPanel) {
                getContentPane().remove(component);
            }
        }
        getContentPane().repaint();
        getContentPane().revalidate();
    }
}