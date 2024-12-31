package PJBL.GUI;

import PJBL.Usuarios.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListUser {
    private JFrame frame;
    private JTable table;
    private List<Usuario> usuarios;

    public ListUser(List<Usuario> usuarios) {
        this.usuarios = usuarios;
        frame = new JFrame("Listar Usuários");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        String[] columnNames = {"Nome", "CPF", "Email", "Telefone", "Endereço", "Tipo"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Usuario usuario : usuarios) {
            Object[] o = new Object[6];
            o[0] = usuario.getNome();
            o[1] = usuario.getCpf();
            o[2] = usuario.getEmail();
            o[3] = usuario.getTelefone();
            o[4] = usuario.getEndereco();
            o[5] = usuario.getTipo();
            model.addRow(o);
        }

        table = new JTable(model);
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

        frame.add(tablePanel, BorderLayout.CENTER);

        JButton backButton = new JButton("Voltar");
        backButton.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        backButton.setPreferredSize(new Dimension(100, 30));
        backButton.addActionListener(e -> frame.dispose());
        frame.add(backButton, BorderLayout.NORTH);

        frame.setVisible(true);
    }
}