package PJBL.GUI;

import PJBL.Usuarios.Administrador;
import PJBL.Sistemas.SistemaDeLogin;
import PJBL.Usuarios.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LoginScreen {
    private JFrame frame;
    private JTextField emailField;
    private JPasswordField passwordField;
    private List<Usuario> usuarios;
    private List<String> produtos;

    public LoginScreen(List<Usuario> usuarios, List<String> produtos) {
        setUIFont(new javax.swing.plaf.FontUIResource("Consolas",Font.PLAIN,12));

        this.usuarios = usuarios;
        this.produtos = produtos;

        frame = new JFrame("Login");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }

    public static void setUIFont (javax.swing.plaf.FontUIResource f){
        java.util.Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof javax.swing.plaf.FontUIResource)
                UIManager.put(key, f);
        }
    }

    private void placeComponents(JPanel panel) {

        panel.setLayout(null);

        JLabel userLabel = new JLabel("Email:");
        userLabel.setBounds(10, 20, 50, 25);
        panel.add(userLabel);

        emailField = new JTextField(20);
        emailField.setBounds(100, 20, 270, 25);
        panel.add(emailField);

        JLabel passwordLabel = new JLabel("Senha:");
        passwordLabel.setBounds(10, 50, 50, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(100, 50, 270, 25);
        panel.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(30, 120, 330, 25);
        panel.add(loginButton);

        ActionListener loginAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                Usuario usuarioLogado = SistemaDeLogin.autenticar(email, password, usuarios);

                if (usuarioLogado != null) {
                    if (usuarioLogado instanceof Administrador admin){
                        frame.dispose();
                        new MenuInterface(usuarioLogado, usuarios, produtos);
                    } else {
                        frame.dispose();
                        new MenuInterfaceFuncio(usuarioLogado, usuarios);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Email ou senha incorretos.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        };

        loginButton.addActionListener(loginAction);
        passwordField.addActionListener(loginAction);
    }
}