package PJBL.GUI;

import PJBL.Usuarios.Usuario;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MenuInterface {
    private JFrame frame;
    private Usuario usuarioLogado;
    private List<Usuario> usuarios;
    private List<String> produtos;

    public MenuInterface(Usuario usuarioLogado, List<Usuario> usuarios, List<String> produtos) {
        this.usuarioLogado = usuarioLogado;
        this.usuarios = usuarios;
        this.produtos = produtos;

        frame = new JFrame("Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBackground(Color.decode("#1e1e1e"));
        frame.add(panel);
        placeComponents(panel);

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(new GridLayout(0, 3, 20, 20));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JButton createUserButton = createButton("Criar novo usuário");
        createUserButton.setBackground(new Color(201, 207, 228));
        createUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateUser(usuarios);
            }
        });
        panel.add(createUserButton);

        JButton editUserButton = createButton("Editar usuário");
        editUserButton.setBackground(new Color(201, 207, 228));
        editUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EditUser(usuarios);
            }
        });
        panel.add(editUserButton);

        JButton deleteUserButton = createButton("Deletar usuário");
        deleteUserButton.setBackground(new Color(201, 207, 228));
        deleteUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DeleteUser(usuarios, usuarioLogado);
            }
        });
        panel.add(deleteUserButton);

        JButton listUserButton = createButton("Listar todos os usuários");
        listUserButton.setBackground(new Color(201, 207, 228));
        listUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ListUser(usuarios);
            }
        });
        panel.add(listUserButton);

        JButton registerProductButton = createButton("Cadastrar Produto");
        registerProductButton.setBackground(new Color(192, 232, 186));
        registerProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateProduto createProduto = new CreateProduto();
                createProduto.setVisible(true);
            }
        });
        panel.add(registerProductButton);

        JButton loadProductsButton = createButton("Carregar Produtos");
        loadProductsButton.setBackground(new Color(192, 232, 186));
        loadProductsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ListProduto();
            }
        });
        panel.add(loadProductsButton);

        JButton editProductsButton = createButton("Editar Produtos");
        editProductsButton.setBackground(new Color(192, 232, 186));
        editProductsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EditProduto();
            }
        });
        panel.add(editProductsButton);

        JButton deleteProductsButton = createButton("Excluir Produtos");
        deleteProductsButton.setBackground(new Color(192, 232, 186));
        deleteProductsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DeleteProduto();
            }
        });
        panel.add(deleteProductsButton);

        JButton exitButton = createButton("Sair");
        exitButton.setBackground(new Color(236, 203, 203));
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        panel.add(exitButton);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Consolas", Font.PLAIN, 30));
        button.setBackground(new Color(217, 217, 217));
        return button;
    }
}