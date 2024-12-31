package PJBL.GUI;

import PJBL.Sistemas.LoginException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DeleteProduto extends JFrame {
    private JTextField idField;
    private JButton excluirButton;
    private JLabel messageLabel;
    private List<String> produtos;
    private static final String FILE_PATH = "src/PJBL/";
    private static final String FILE_NAME = FILE_PATH + "produtos.txt";

    public DeleteProduto() {
        try {
            this.produtos = carregarProdutos();
        } catch (LoginException e) {
            e.printStackTrace();
        }
        setLayout(null);

        JLabel idLabel = new JLabel("ID do Produto:");
        idLabel.setBounds(20, 20, 150, 30);
        add(idLabel);

        idField = new JTextField(20);
        idField.setBounds(20, 50, 150, 30);
        idField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirButton.doClick();
            }
        });
        add(idField);


        excluirButton = new JButton("Excluir Produto");
        excluirButton.setBackground(new Color(255, 99, 99));
        excluirButton.setBounds(20, 100, 200, 30);
        add(excluirButton);

        messageLabel = new JLabel("");
        add(messageLabel);

        excluirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int produtoIndex = Integer.parseInt(idField.getText()) - 1;

                    if (produtoIndex >= 0 && produtoIndex < produtos.size()) {
                        int dialogResult = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir o produto?", "Aviso", JOptionPane.YES_NO_OPTION);
                        if(dialogResult == JOptionPane.YES_OPTION){
                            produtos.remove(produtoIndex);
                            salvarProdutos(produtos);
                            renumerarProdutos(produtos);
                            messageLabel.setText("Produto excluído com sucesso.");
                            dispose();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Produto não encontrado.");
                    }
                } catch (Exception ex) {
                    messageLabel.setText("Erro ao excluir produto: " + ex.getMessage());
                }
            }
        });

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Excluir Produto");
        setSize(350, 200);
        setLocationRelativeTo(null);
        setVisible(true);
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

    private static void renumerarProdutos(List<String> produtos) throws LoginException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (int i = 0; i < produtos.size(); i++) {
                String[] partes = produtos.get(i).split(",", 2); // Divide o ID do produto do restante da string
                partes[0] = Integer.toString(i + 1); // Renomeia o ID do produto
                bw.write(String.join(",", partes)); // Reescreve a linha com o novo ID
                bw.newLine();
            }
        } catch (IOException e) {
            throw new LoginException("Erro ao renumerar produtos: " + e.getMessage());
        }
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
}