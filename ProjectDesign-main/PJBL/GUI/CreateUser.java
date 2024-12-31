package PJBL.GUI;

import PJBL.Sistemas.LoginException;
import PJBL.Sistemas.SistemaDeLogin;
import PJBL.Usuarios.Administrador;
import PJBL.Usuarios.Funcionario;
import PJBL.Usuarios.Usuario;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.ParseException;
import java.util.List;

public class CreateUser {
    private JFrame frame;
    private JTextField nomeField;
    private JTextField cpfField;
    private JTextField emailField;
    private JPasswordField senhaField;
    private JTextField telefoneField;
    private JTextField enderecoField;
    private JRadioButton adminButton;
    private JRadioButton funcionarioButton;
    private JButton submitButton;
    private JButton cancelButton;
    private List<Usuario> usuarios;

    public CreateUser(List<Usuario> usuarios) {
        this.usuarios = usuarios;
        frame = new JFrame("Criar Novo Usuário");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(430, 370);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel, usuarios);

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    private void placeComponents(JPanel panel, List<Usuario> usuarios) {
        panel.setLayout(null);

        JLabel nomeLabel = new JLabel("Nome:");
        nomeLabel.setBounds(20, 20, 80, 25);
        panel.add(nomeLabel);
        nomeField = new JTextField(20);
        nomeField.setBounds(110, 20, 280, 25);
        panel.add(nomeField);

        JLabel cpfLabel = new JLabel("CPF:");
        cpfLabel.setBounds(20, 50, 80, 25);
        panel.add(cpfLabel);
        try {
            MaskFormatter cpfMask = new MaskFormatter("###.###.###-##");
            cpfMask.setPlaceholderCharacter('_');
            cpfField = new JFormattedTextField(cpfMask);
            cpfField.setBounds(110, 50, 100, 25);
            panel.add(cpfField);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cpfField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String cpf = cpfField.getText().replaceAll("\\D", "");
                if (cpf.length() == 11) {
                    String cpfFormatted = cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
                    Usuario existingUser = findUserByCpf(cpfFormatted);
                    if (existingUser != null) {
                        JOptionPane.showMessageDialog(frame, "CPF já cadastrado. Por favor, insira um CPF diferente.");
                        cpfField.setText("");
                        cpfField.requestFocus();
                    } else {
                        cpfField.setText(cpfFormatted);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "CPF inválido. Por favor, insira um CPF com 11 dígitos.");
                    cpfField.setText("");
                    cpfField.requestFocus();
                }
            }
        });

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(20, 80, 80, 25);
        panel.add(emailLabel);
        emailField = new JTextField(20);
        emailField.setBounds(110, 80, 280, 25);
        panel.add(emailField);
        emailField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String email = emailField.getText();
                Usuario existingUser = findUserByEmail(email);
                if (existingUser != null) {
                    JOptionPane.showMessageDialog(frame, "Email já cadastrado. Por favor, insira um email diferente.");
                    emailField.setText("");
                    emailField.requestFocus();
                }
            }
        });

        JLabel senhaLabel = new JLabel("Senha:");
        senhaLabel.setBounds(20, 110, 80, 25);
        panel.add(senhaLabel);
        senhaField = new JPasswordField(20);
        senhaField.setBounds(110, 110, 280, 25);
        panel.add(senhaField);

        JLabel telefoneLabel = new JLabel("Telefone:");
        telefoneLabel.setBounds(20, 140, 80, 25);
        panel.add(telefoneLabel);
        try {
            MaskFormatter telefoneMask = new MaskFormatter("(##) #####-####");
            telefoneMask.setPlaceholderCharacter('_');
            telefoneField = new JFormattedTextField(telefoneMask);
            telefoneField.setBounds(110, 140, 110, 25);
            panel.add(telefoneField);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        telefoneField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String telefone = telefoneField.getText().replaceAll("\\D", "");
                if (telefone.length() == 11) {
                    String telefoneFormatted = telefone.replaceAll("(\\d{2})(\\d{5})(\\d{4})", "($1) $2-$3");
                    telefoneField.setText(telefoneFormatted);
                } else {
                    JOptionPane.showMessageDialog(frame, "Telefone inválido. Por favor, insira um telefone com 11 dígitos.");
                    telefoneField.setText("");
                    telefoneField.requestFocus();
                }
            }
        });

        JLabel enderecoLabel = new JLabel("Endereço:");
        enderecoLabel.setBounds(20, 170, 80, 25);
        panel.add(enderecoLabel);
        enderecoField = new JTextField(20);
        enderecoField.setBounds(110, 170, 280, 25);
        panel.add(enderecoField);

        JLabel tipoLabel = new JLabel("Tipo:");
        tipoLabel.setBounds(20, 200, 80, 25);
        panel.add(tipoLabel);

        JPanel radioPanel = new JPanel();
        radioPanel.setBounds(20, 200, 380, 35);
        adminButton = new JRadioButton("Administrador");
        funcionarioButton = new JRadioButton("Funcionario");
        ButtonGroup group = new ButtonGroup();
        group.add(adminButton);
        group.add(funcionarioButton);
        radioPanel.add(adminButton);
        radioPanel.add(funcionarioButton);
        panel.add(radioPanel);

        submitButton = new JButton("Cadastrar");
        submitButton.setBounds(20, 260, 160, 50);
        submitButton.setBackground(new Color(0, 222, 110));
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText();
                String cpf = cpfField.getText();
                String email = emailField.getText();
                String senha = new String(senhaField.getPassword());
                String telefone = telefoneField.getText();
                String endereco = enderecoField.getText();
                String tipo = adminButton.isSelected() ? "Administrador" : "Funcionario";

                // Validação dos campos
                if (nome.isEmpty() || cpf.isEmpty() || email.isEmpty() || senha.isEmpty() || telefone.isEmpty() || endereco.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Todos os campos devem ser preenchidos.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Usuario novoUsuario;
                if (tipo.equals("Administrador")) {
                    novoUsuario = new Administrador(usuarios.size() + 1, nome, cpf, email, senha, telefone, endereco);
                } else {
                    novoUsuario = new Funcionario(usuarios.size() + 1, nome, cpf, email, senha, telefone, endereco);
                }

                usuarios.add(novoUsuario);
                try {
                    SistemaDeLogin.salvarUsuario(novoUsuario);
                    JOptionPane.showMessageDialog(frame, "Novo usuário criado com sucesso.");
                    frame.dispose();
                } catch (LoginException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        panel.add(submitButton);

        cancelButton = new JButton("Cancelar");
        cancelButton.setBounds(220, 260, 160, 50);
        cancelButton.setBackground(new Color(255, 99, 99));
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        panel.add(cancelButton);
    }

    private Usuario findUserByCpf(String cpf) {
        for (Usuario usuario : usuarios) {
            if (usuario.getCpf().equals(cpf)) {
                return usuario;
            }
        }
        return null;
    }

    private Usuario findUserByEmail(String email) {
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email)) {
                return usuario;
            }
        }
        return null;
    }
}

