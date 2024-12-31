package PJBL.GUI;

import PJBL.Produtos.Aviao;
import PJBL.Produtos.Carro;
import PJBL.Produtos.Embarcacao;
import PJBL.Produtos.Veiculo;
import PJBL.Sistemas.LoginException;
import PJBL.Sistemas.SistemaProduto;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CreateProduto extends JFrame {
    private JComboBox<String> tipoVeiculo;
    private JPanel cardPanel;
    private JPanel carroPanel;
    private JPanel aviaoPanel;
    private JPanel embarcacaoPanel;

    public CreateProduto() {
        setTitle("Criar Produto");
        setSize(500, 760);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        tipoVeiculo = new JComboBox<>(new String[]{"Escolha um tipo de veículo", "Carro", "Avião", "Embarcação"});
        tipoVeiculo.addActionListener(e -> showCard((String) tipoVeiculo.getSelectedItem()));

        cardPanel = new JPanel(new CardLayout());
        JPanel emptyPanel = new JPanel();
        carroPanel = createCarroPanel();
        aviaoPanel = createAviaoPanel();
        embarcacaoPanel = createEmbarcacaoPanel();

        cardPanel.add(emptyPanel, "Empty");
        cardPanel.add(carroPanel, "Carro");
        cardPanel.add(aviaoPanel, "Avião");
        cardPanel.add(embarcacaoPanel, "Embarcação");

        add(tipoVeiculo, BorderLayout.NORTH);
        add(cardPanel, BorderLayout.CENTER);
        tipoVeiculo.addActionListener(e -> {
            String selectedOption = (String) tipoVeiculo.getSelectedItem();
            if (!selectedOption.equals("Escolha um tipo de veículo")) {
                tipoVeiculo.setEnabled(false);
                showCard(selectedOption);
            }
        });
    }

    private void showCard(String card) {
        CardLayout cl = (CardLayout) (cardPanel.getLayout());
        switch (card) {
            case "Escolha um tipo de veículo":
                cl.show(cardPanel, "Empty");
                break;
            case "Carro":
                cl.show(cardPanel, "Carro");
                break;
            case "Avião":
                cl.show(cardPanel, "Avião");
                break;
            case "Embarcação":
                cl.show(cardPanel, "Embarcação");
                break;
        }
    }

    private JPanel createCarroPanel() {
        List<Veiculo> carros = new ArrayList<>();
        JPanel panel = new JPanel(null);

        // Campos de entrada para Carro
        String[] labels = {"Tipo da Carroceria:", "Número de Assentos:", "Número de Portas:", "Capacidade do Porta-malas:",
                           "Tipo do Motor:", "Potência do Motor:", "Tipo do Combustível:", "Tipo do Câmbio:",
                           "Marca do Veículo:", "Modelo do Veículo:", "Ano de fabricação:", "Quilometragem do Veículo:",
                           "Cor do Veículo:", "Capacidade de Passageiros:", "Número de Rodas:",
                           "Preço:", "Estoque:"};
        int y = 20;
        for (int i = 0; i < labels.length; i++) {
            // Adiciona o rótulo
            JLabel label = new JLabel(labels[i]);
            label.setBounds(10, y, 200, 25);
            panel.add(label);
            y += 35;
        }

        // Adiciona os campos de entrada
        JTextField tipoCarroceriaField = new JTextField(20);
        tipoCarroceriaField.setBounds(220, 20, 250, 25);
        panel.add(tipoCarroceriaField);

        JTextField numAssentosField = new JTextField(20);
        numAssentosField.setBounds(220, 55, 250, 25);
        panel.add(numAssentosField);

        JTextField numPortasField = new JTextField(20);
        numPortasField.setBounds(220, 90, 250, 25);
        panel.add(numPortasField);

        JTextField capacidadePortaMalasField = new JTextField(20);
        capacidadePortaMalasField.setBounds(220, 125, 250, 25);
        panel.add(capacidadePortaMalasField);

        JTextField tipoMotorField = new JTextField(20);
        tipoMotorField.setBounds(220, 160, 250, 25);
        panel.add(tipoMotorField);

        JTextField potenciaMotorField = new JTextField(20);
        potenciaMotorField.setBounds(220, 195, 250, 25);
        panel.add(potenciaMotorField);

        JTextField tipoCombustivelField = new JTextField(20);
        tipoCombustivelField.setBounds(220, 230, 250, 25);
        panel.add(tipoCombustivelField);

        JTextField tipoCambioField = new JTextField(20);
        tipoCambioField.setBounds(220, 265, 250, 25);
        panel.add(tipoCambioField);

        JTextField marcaField = new JTextField(20);
        marcaField.setBounds(220, 300, 250, 25);
        panel.add(marcaField);

        JTextField modeloField = new JTextField(20);
        modeloField.setBounds(220, 335, 250, 25);
        panel.add(modeloField);

        JTextField anoField = new JTextField(20);
        anoField.setBounds(220, 370, 250, 25);
        panel.add(anoField);

        JTextField quilometragemField = new JTextField(20);
        quilometragemField.setBounds(220, 405, 250, 25);
        panel.add(quilometragemField);

        JTextField corField = new JTextField(20);
        corField.setBounds(220, 440, 250, 25);
        panel.add(corField);

        JTextField capacidadeDePassageiroField = new JTextField(20);
        capacidadeDePassageiroField.setBounds(220, 475, 250, 25);
        panel.add(capacidadeDePassageiroField);

        JTextField numRodasField = new JTextField(20);
        numRodasField.setBounds(220, 510, 250, 25);
        panel.add(numRodasField);

        JTextField precoField = new JTextField(20);
        precoField.setBounds(220, 545, 250, 25);
        panel.add(precoField);

        JTextField quantidadeField = new JTextField(20);
        quantidadeField.setBounds(220, 580, 250, 25);
        panel.add(quantidadeField);

        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Dados do Carro"));

        JButton cancelButton = new JButton("Cancelar");
        cancelButton.setBackground(new Color(255, 99, 99));
        cancelButton.setBounds(20, 620, 200, 50);
        cancelButton.addActionListener(e -> dispose());
        panel.add(cancelButton);

        JButton registerButton = new JButton("Cadastrar");
        registerButton.setBackground(new Color(0, 222, 110));
        registerButton.setBounds(250, 620, 200, 50);
        registerButton.addActionListener(e -> {
            JTextField[] fields = {tipoCarroceriaField, numAssentosField, numPortasField, capacidadePortaMalasField,
                    tipoMotorField, potenciaMotorField, tipoCombustivelField, tipoCambioField,
                    marcaField, modeloField, anoField, quilometragemField, corField,
                    capacidadeDePassageiroField, numRodasField, precoField, quantidadeField};

            for (JTextField field : fields) {
                if (field.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            if (!anoField.getText().matches("\\d+") || !quilometragemField.getText().matches("\\d+") ||
                    !capacidadeDePassageiroField.getText().matches("\\d+") || !precoField.getText().matches("\\d+") ||
                    !quantidadeField.getText().matches("\\d+") || !numRodasField.getText().matches("\\d+") ||
                    !numAssentosField.getText().matches("\\d+") || !numPortasField.getText().matches("\\d+") ||
                    !capacidadePortaMalasField.getText().matches("\\d+") || !potenciaMotorField.getText().matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "Por favor, insira apenas números nos campos apropriados.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int codVeiculo = 0;
            String marca = marcaField.getText();
            String modelo = modeloField.getText();
            int ano = Integer.parseInt(anoField.getText());
            int quilometragem = Integer.parseInt(quilometragemField.getText());
            String cor = corField.getText();
            int capacidadeDePassageiro = Integer.parseInt(capacidadeDePassageiroField.getText());
            double preco = Double.parseDouble(precoField.getText());
            int quantidade = Integer.parseInt(quantidadeField.getText());
            int numRodas = Integer.parseInt(numRodasField.getText());
            String carroceria = tipoCarroceriaField.getText();
            int numAssentos = Integer.parseInt(numAssentosField.getText());
            int portas = Integer.parseInt(numPortasField.getText());
            int capacidadePortaMalas = Integer.parseInt(capacidadePortaMalasField.getText());
            String tipoMotor = tipoMotorField.getText();
            int potenciaMotor = Integer.parseInt(potenciaMotorField.getText());
            String tipoCombustivel = tipoCombustivelField.getText();
            String tipoCambio = tipoCambioField.getText();

            Veiculo veiculo = new Carro(codVeiculo, marca, modelo, ano, quilometragem, cor,
                    capacidadeDePassageiro, preco, quantidade, numRodas, carroceria, numAssentos, portas,
                    capacidadePortaMalas, tipoMotor, potenciaMotor, tipoCombustivel, tipoCambio);
            carros.add(veiculo);

            String text = String.join(",", String.valueOf(codVeiculo), "Carro", marca, modelo,
                    String.valueOf(ano), String.valueOf(quilometragem), cor, String.valueOf(capacidadeDePassageiro),
                    String.valueOf(preco), String.valueOf(quantidade), String.valueOf(numRodas), carroceria,
                    String.valueOf(numAssentos), String.valueOf(portas), String.valueOf(capacidadePortaMalas),
                    tipoMotor, String.valueOf(potenciaMotor), tipoCombustivel, tipoCambio);
            try {
                SistemaProduto.cadastrar(text);
                JOptionPane.showMessageDialog(this, "Carro cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } catch (LoginException ex) {
                throw new RuntimeException(ex);
            }
        });
        panel.add(registerButton);

        return panel;
    }

    private JPanel createAviaoPanel() {
        List<Veiculo> avioes = new ArrayList<>();
        JPanel panel = new JPanel(null);

        // Campos de entrada para Carro
        String[] labels = {"Tipo da aeronave:", "Marca da aeronave:", "Modelo da aeronave:", "Ano de fabricação:",
                "Quilometragem rodada:", "Cor da aeronave:", "Tipo de propulsão:", "Tipo do avião:",
                "Alcance de voo (em km):", "Capacidade de passageiros:", "Número de motores:", "Preço da aeronave:",
                "Estoque:"};
        int y = 20;
        for (int i = 0; i < labels.length; i++) {
            // Adiciona o rótulo
            JLabel label = new JLabel(labels[i]);
            label.setBounds(10, y, 200, 25);
            panel.add(label);
            y += 35;
        }

        // Adiciona os campos de entrada
        JTextField tipoAeronaveField = new JTextField(20);
        tipoAeronaveField.setBounds(220, 20, 250, 25);
        panel.add(tipoAeronaveField);

        JTextField marcaAeronaveField = new JTextField(20);
        marcaAeronaveField.setBounds(220, 55, 250, 25);
        panel.add(marcaAeronaveField);

        JTextField modeloAeronaveField = new JTextField(20);
        modeloAeronaveField.setBounds(220, 90, 250, 25);
        panel.add(modeloAeronaveField);

        JTextField anoAeronaveField = new JTextField(20);
        anoAeronaveField.setBounds(220, 125, 250, 25);
        panel.add(anoAeronaveField);

        JTextField quilometragemAeronaveField = new JTextField(20);
        quilometragemAeronaveField.setBounds(220, 160, 250, 25);
        panel.add(quilometragemAeronaveField);

        JTextField corAeronaveField = new JTextField(20);
        corAeronaveField.setBounds(220, 195, 250, 25);
        panel.add(corAeronaveField);

        JTextField tipoPropulsaoField = new JTextField(20);
        tipoPropulsaoField.setBounds(220, 230, 250, 25);
        panel.add(tipoPropulsaoField);

        JTextField tipoAviaoField = new JTextField(20);
        tipoAviaoField.setBounds(220, 265, 250, 25);
        panel.add(tipoAviaoField);

        JTextField alcanceVooField = new JTextField(20);
        alcanceVooField.setBounds(220, 300, 250, 25);
        panel.add(alcanceVooField);

        JTextField capacidadePassageirosField = new JTextField(20);
        capacidadePassageirosField.setBounds(220, 335, 250, 25);
        panel.add(capacidadePassageirosField);

        JTextField numMotoresField = new JTextField(20);
        numMotoresField.setBounds(220, 370, 250, 25);
        panel.add(numMotoresField);

        JTextField precoAeronaveField = new JTextField(20);
        precoAeronaveField.setBounds(220, 405, 250, 25);
        panel.add(precoAeronaveField);

        JTextField quantidadeAeronaveField = new JTextField(20);
        quantidadeAeronaveField.setBounds(220, 440, 250, 25);
        panel.add(quantidadeAeronaveField);

        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Dados do Avião"));

        JButton cancelButton = new JButton("Cancelar");
        cancelButton.setBackground(new Color(255, 99, 99));
        cancelButton.setBounds(20, 620, 200, 50);
        cancelButton.addActionListener(e -> dispose());
        panel.add(cancelButton);

        JButton registerButton = new JButton("Cadastrar");
        registerButton.setBackground(new Color(0, 222, 110));
        registerButton.setBounds(250, 620, 200, 50);
        registerButton.addActionListener(e -> {
            JTextField[] fields = {tipoAeronaveField, marcaAeronaveField, modeloAeronaveField, anoAeronaveField,
                    quilometragemAeronaveField, corAeronaveField, tipoPropulsaoField, tipoAviaoField,
                    alcanceVooField, capacidadePassageirosField, numMotoresField, precoAeronaveField, quantidadeAeronaveField};

            for (JTextField field : fields) {
                if (field.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            // Campos que devem conter apenas números
            JTextField[] numericFields = {anoAeronaveField, quilometragemAeronaveField, alcanceVooField, capacidadePassageirosField, numMotoresField, quantidadeAeronaveField};
            for (JTextField field : numericFields) {
                if (!field.getText().matches("\\d+")) {
                    JOptionPane.showMessageDialog(this, "Por favor, insira apenas números nos campos de ano, quilometragem, alcance de voo, capacidade de passageiros, número de motores e quantidade.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            int codVeiculo = 0;
            String tipo = tipoAeronaveField.getText();
            String marca = marcaAeronaveField.getText();
            String modelo = modeloAeronaveField.getText();
            int ano = Integer.parseInt(anoAeronaveField.getText());
            int quilometragem = Integer.parseInt(quilometragemAeronaveField.getText());
            String cor = corAeronaveField.getText();
            String tipoPropulsao = tipoPropulsaoField.getText();
            String tipoDeAviao = tipoAviaoField.getText();
            int alcanceDeVoo = Integer.parseInt(alcanceVooField.getText());
            int capacidadeDePassageiro = Integer.parseInt(capacidadePassageirosField.getText());
            int numMotores = Integer.parseInt(numMotoresField.getText());
            double preco = Double.parseDouble(precoAeronaveField.getText());
            int quantidade = Integer.parseInt(quantidadeAeronaveField.getText());

            Veiculo veiculo = new Aviao(codVeiculo, tipo, marca, modelo, ano, quilometragem, cor,
                    capacidadeDePassageiro, preco, quantidade, tipoPropulsao, numMotores, tipoDeAviao, alcanceDeVoo);
            avioes.add(veiculo);

            String text = String.join(",", String.valueOf(codVeiculo), "Avião", marca, modelo,
                    String.valueOf(ano), String.valueOf(quilometragem), cor, String.valueOf(capacidadeDePassageiro),
                    String.valueOf(preco), String.valueOf(quantidade), String.valueOf(numMotores));
            try {
                SistemaProduto.cadastrar(text);
                JOptionPane.showMessageDialog(this, "Avião cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } catch (LoginException ex) {
                throw new RuntimeException(ex);
            }
        });
        panel.add(registerButton);
        return panel;
    }

    private JPanel createEmbarcacaoPanel() {
        List<Veiculo> embarcacoes = new ArrayList<>();
        JPanel panel = new JPanel(null);

        // Campos de entrada para Carro
        String[] labels = {"Tipo da embarcação:", "Marca da embarcação:", "Modelo da embarcação:", "Ano de fabricação:",
                "Quilometragem navegada:", "Cor da embarcação:", "Capacidade de passageiros:", "Tipo de propulsão:",
                "Alcance de navegação (em km):", "Preço da embarcação:", "Estoque:"};
        int y = 20;
        for (int i = 0; i < labels.length; i++) {
            // Adiciona o rótulo
            JLabel label = new JLabel(labels[i]);
            label.setBounds(10, y, 200, 25);
            panel.add(label);
            y += 35;
        }

        // Adiciona os campos de entrada para Embarcação
        JTextField tipoEmbarcacaoField = new JTextField(20);
        tipoEmbarcacaoField.setBounds(220, 20, 250, 25);
        panel.add(tipoEmbarcacaoField);

        JTextField marcaEmbarcacaoField = new JTextField(20);
        marcaEmbarcacaoField.setBounds(220, 55, 250, 25);
        panel.add(marcaEmbarcacaoField);

        JTextField modeloEmbarcacaoField = new JTextField(20);
        modeloEmbarcacaoField.setBounds(220, 90, 250, 25);
        panel.add(modeloEmbarcacaoField);

        JTextField anoEmbarcacaoField = new JTextField(20);
        anoEmbarcacaoField.setBounds(220, 125, 250, 25);
        panel.add(anoEmbarcacaoField);

        JTextField quilometragemEmbarcacaoField = new JTextField(20);
        quilometragemEmbarcacaoField.setBounds(220, 160, 250, 25);
        panel.add(quilometragemEmbarcacaoField);

        JTextField corEmbarcacaoField = new JTextField(20);
        corEmbarcacaoField.setBounds(220, 195, 250, 25);
        panel.add(corEmbarcacaoField);

        JTextField capacidadePassageirosField = new JTextField(20);
        capacidadePassageirosField.setBounds(220, 230, 250, 25);
        panel.add(capacidadePassageirosField);

        JTextField tipoPropulsaoField = new JTextField(20);
        tipoPropulsaoField.setBounds(220, 265, 250, 25);
        panel.add(tipoPropulsaoField);

        JTextField alcanceNavegacaoField = new JTextField(20);
        alcanceNavegacaoField.setBounds(220, 300, 250, 25);
        panel.add(alcanceNavegacaoField);

        JTextField precoEmbarcacaoField = new JTextField(20);
        precoEmbarcacaoField.setBounds(220, 335, 250, 25);
        panel.add(precoEmbarcacaoField);

        JTextField quantidadeEmbarcacaoField = new JTextField(20);
        quantidadeEmbarcacaoField.setBounds(220, 370, 250, 25);
        panel.add(quantidadeEmbarcacaoField);

        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Dados da Embarcação"));

        JButton cancelButton = new JButton("Cancelar");
        cancelButton.setBackground(new Color(255, 99, 99));
        cancelButton.setBounds(20, 620, 200, 50);
        cancelButton.addActionListener(e -> dispose());
        panel.add(cancelButton);

        JButton registerButton = new JButton("Cadastrar");
        registerButton.setBackground(new Color(0, 222, 110));
        registerButton.setBounds(250, 620, 200, 50);
        registerButton.addActionListener(e -> {
            JTextField[] fields = {tipoEmbarcacaoField, marcaEmbarcacaoField, modeloEmbarcacaoField, anoEmbarcacaoField,
                    quilometragemEmbarcacaoField, corEmbarcacaoField, capacidadePassageirosField, tipoPropulsaoField,
                    alcanceNavegacaoField, precoEmbarcacaoField, quantidadeEmbarcacaoField};

            for (JTextField field : fields) {
                if (field.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            // Campos que devem conter apenas números
            JTextField[] numericFields = {anoEmbarcacaoField, quilometragemEmbarcacaoField, capacidadePassageirosField, alcanceNavegacaoField, quantidadeEmbarcacaoField};

            for (JTextField field : numericFields) {
                if (!field.getText().matches("\\d+")) {
                    JOptionPane.showMessageDialog(this, "Por favor, insira apenas números nos campos de ano, quilometragem, capacidade de passageiros, alcance de navegação e quantidade.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }


            int codVeiculo = 0;
            String tipo = tipoEmbarcacaoField.getText();
            String marca = marcaEmbarcacaoField.getText();
            String modelo = modeloEmbarcacaoField.getText();
            int ano = Integer.parseInt(anoEmbarcacaoField.getText());
            int quilometragem = Integer.parseInt(quilometragemEmbarcacaoField.getText());
            String cor = corEmbarcacaoField.getText();
            int capacidadeDePassageiro = Integer.parseInt(capacidadePassageirosField.getText());
            String tipoPropulsao = tipoPropulsaoField.getText();
            int alcanceNavegacao = Integer.parseInt(alcanceNavegacaoField.getText());
            double preco = Double.parseDouble(precoEmbarcacaoField.getText());
            int quantidade = Integer.parseInt(quantidadeEmbarcacaoField.getText());

            Veiculo veiculo = new Embarcacao(codVeiculo, tipo, marca, modelo, ano, quilometragem, cor,
                    capacidadeDePassageiro, preco, quantidade, tipoPropulsao, alcanceNavegacao);
            embarcacoes.add(veiculo);

            String text = String.join(",", String.valueOf(codVeiculo), "Embarcação", marca, modelo,
                    String.valueOf(ano), String.valueOf(quilometragem), cor, String.valueOf(capacidadeDePassageiro),
                    String.valueOf(preco), String.valueOf(quantidade), tipoPropulsao);
            try {
                SistemaProduto.cadastrar(text);
                JOptionPane.showMessageDialog(this, "Embarcação cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } catch (LoginException ex) {
                throw new RuntimeException(ex);
            }
        });
        panel.add(registerButton);
        return panel;
    }
}