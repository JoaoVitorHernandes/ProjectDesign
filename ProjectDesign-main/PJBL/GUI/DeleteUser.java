package PJBL.GUI;

import PJBL.Usuarios.Usuario;
import PJBL.Sistemas.SistemaDeLogin;

import javax.swing.text.MaskFormatter;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.ParseException;
import java.util.List;

public class DeleteUser {
    private JFrame frame;
    private JTextField cpfField;
    private JButton deleteButton;
    private JButton cancelButton;
    private List<Usuario> usuarios;
    private Usuario usuarioLogado;

    public DeleteUser(List<Usuario> usuarios, Usuario usuarioLogado) {
        this.usuarios = usuarios;
        this.usuarioLogado = usuarioLogado;
        frame = new JFrame("Excluir Usuário");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(350, 200);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel cpfLabel = new JLabel("Digite o CPF do usuário a ser excluído:");
        cpfLabel.setBounds(10, 20, 280, 25);
        panel.add(cpfLabel);

        try {
            MaskFormatter cpfMask = new MaskFormatter("###.###.###-##");
            cpfMask.setPlaceholderCharacter('_');
            cpfField = new JFormattedTextField(cpfMask);
        } catch (ParseException e) {
            cpfField = new JTextField(20);
        }
        cpfField.setBounds(10, 50, 110, 25);

        cpfField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String cpf = cpfField.getText();
                cpf = cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
                cpfField.setText(cpf);
            }
        });
        panel.add(cpfField);

        deleteButton = new JButton("Excluir");
        deleteButton.setBounds(140, 100, 120, 30);
        deleteButton.setBackground(new Color(255, 99, 99));
        deleteButton.addActionListener(e -> deleteUser());
        panel.add(deleteButton);

        cancelButton = new JButton("Cancelar");
        cancelButton.setBounds(10, 100, 120, 30);
        cancelButton.addActionListener(e -> frame.dispose());
        panel.add(cancelButton);
    }

    private void deleteUser() {
        String cpf = cpfField.getText();
        if (cpf.equals(usuarioLogado.getCpf())) {
            JOptionPane.showMessageDialog(frame, "Não é possível apagar a conta em utilização.");
            return;
        }

        Usuario usuarioEncontrado = null;
        for (Usuario usuario : usuarios) {
            if (usuario.getCpf().equals(cpf)) {
                usuarioEncontrado = usuario;
                break;
            }
        }

        if (usuarioEncontrado != null) {
            int dialogResult = JOptionPane.showConfirmDialog(frame, "Tem certeza que deseja excluir o usuário " + usuarioEncontrado.getNome() + "?", "Confirmação de exclusão", JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION) {
                try {
                    SistemaDeLogin.removerUsuario(cpf);
                    usuarios.remove(usuarioEncontrado);
                    JOptionPane.showMessageDialog(frame, "Usuário removido com sucesso.");
                    frame.dispose();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(frame, "Erro ao remover usuário.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Usuário não encontrado.");
        }
    }
}