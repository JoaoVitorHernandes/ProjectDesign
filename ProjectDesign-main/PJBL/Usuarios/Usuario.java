package PJBL.Usuarios;

import PJBL.Produtos.Aviao;
import PJBL.Produtos.Carro;
import PJBL.Produtos.Embarcacao;
import PJBL.Produtos.Veiculo;
import PJBL.Sistemas.LoginException;
import PJBL.Sistemas.SistemaProduto;
import PJBL.Strategy.*;

import java.util.*;

public abstract class Usuario {
    private int idUsuario;
    private String nome;
    private String tipo;
    private String cpf;
    private String email;
    private String senha;
    private String telefone;
    private String endereco;

    // Variáveis de contador para os códigos dos produtos
    private static int proximoCodigoCarro = 1;
    private static int proximoCodigoAviao = 1;
    private static int proximoCodigoEmbarcacao = 1;

    public Usuario(int idUsuario, String nome, String tipo, String cpf, String email, String senha, String telefone,
                   String endereco) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.tipo = tipo;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public abstract void listarUsuarios(List<Usuario> usuarios);

    public abstract void exibirInfo(Usuario usuario);

    // Método para formatar os dados do usuário como uma linha de texto separada por virgula
    public String toFileString() {
        return String.join(",", String.valueOf(idUsuario), nome, tipo, cpf, email, senha, telefone,
                endereco);
    }

    // Método para criar um usuário a partir de uma linha de texto
    // Recebe dados de um usuário delimitados por vírgula
    public static Usuario fromFileString(String fileString) throws LoginException {
        // Verifica se a string de entrada é nula ou vazia
        if (fileString == null || fileString.isEmpty()) {
            throw new LoginException("A string de entrada está nula ou vazia.");
        }
        // Separa cada parte dos dados e coloca num array
        String[] parts = fileString.split(",");
        // Verifica se todas as partes necessárias estão presentes
        if (parts.length != 8) {
            throw new LoginException("A string de entrada não contém todas as partes necessárias.");
        }
        try {
            int id = Integer.parseInt(parts[0]);
            String nome = parts[1].trim();
            String tipo = parts[2].trim();
            String cpf = parts[3].trim();
            String email = parts[4].trim();
            String senha = parts[5].trim();
            String telefone = parts[6].trim();
            String endereco = parts[7].trim();
            // Verifica se todos os campos obrigatórios estão presentes e não vazios
            if (nome.isEmpty() || tipo.isEmpty() || cpf.isEmpty() || email.isEmpty() ||
                    senha.isEmpty() || telefone.isEmpty() || endereco.isEmpty()) {
                throw new LoginException("Um ou mais campos obrigatórios estão vazios.");
            }
            // Retorna um objeto dependendo do tipo
            if (tipo.equals("Administrador")) {
                return new Administrador(id, nome, cpf, email, senha, telefone, endereco);
            } else if (tipo.equals("Funcionario")) {
                return new Funcionario(id, nome, cpf, email, senha, telefone, endereco);
            } else {
                throw new LoginException("Tipo de usuário inválido.");
            }
        } catch (NumberFormatException e) {
            // Se a conversão do id falhar, lança LoginException
            throw new LoginException("Falha na conversão do ID: " + e.getMessage());
        } catch (Exception e) {
            // Captura qualquer outra exceção que possa ocorrer e lança LoginException
            throw new LoginException("Erro desconhecido: " + e.getMessage());
        }
    }

    public void criarNovoProduto(Scanner scanner, int tipoVeiculo, List<Veiculo> carro, List<Veiculo> aviao, List<Veiculo> embarcacao)
            throws LoginException {
        if (tipoVeiculo == 1) {
            System.out.println("Criar Veículo Terrestre (Carro)");

            int codVeiculo = proximoCodigoCarro++;
            System.out.println("O id do carro é " + codVeiculo);
            System.out.println("--Carro--");

            String carroceria = "";
            while (carroceria.isEmpty()) {
                System.out.print("Digite o tipo da carroceria: ");
                carroceria = scanner.nextLine();
                if (carroceria.isEmpty()) {
                    System.out.println("Tipo da carroceria não pode ser vazio. Tente novamente.");
                }
            }

            int numAssentos = 0;
            while (numAssentos <= 0) {
                System.out.print("Digite o número de assentos: ");
                try {
                    numAssentos = Integer.parseInt(scanner.nextLine());
                    if (numAssentos <= 0) {
                        System.out.println("Número de assentos deve ser maior que zero. Tente novamente.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Número de assentos inválido. Tente novamente.");
                }
            }

            int portas = 0;
            while (portas <= 0) {
                System.out.print("Digite o número de portas: ");
                try {
                    portas = Integer.parseInt(scanner.nextLine());
                    if (portas <= 0) {
                        System.out.println("Número de portas deve ser maior que zero. Tente novamente.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Número de portas inválido. Tente novamente.");
                }
            }

            int capacidadePortaMalas = 0;
            while (capacidadePortaMalas <= 0) {
                System.out.print("Digite a capacidade do porta-malas: ");
                try {
                    capacidadePortaMalas = Integer.parseInt(scanner.nextLine());
                    if (capacidadePortaMalas <= 0) {
                        System.out.println("Capacidade do porta-malas deve ser maior que zero. Tente novamente.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Capacidade do porta-malas inválida. Tente novamente.");
                }
            }

            String tipoMotor = "";
            while (tipoMotor.isEmpty()) {
                System.out.print("Digite o tipo do motor: ");
                tipoMotor = scanner.nextLine();
                if (tipoMotor.isEmpty()) {
                    System.out.println("Tipo do motor não pode ser vazio. Tente novamente.");
                }
            }

            int potenciaMotor = 0;
            while (potenciaMotor <= 0) {
                System.out.print("Digite a potência do motor: ");
                try {
                    potenciaMotor = Integer.parseInt(scanner.nextLine());
                    if (potenciaMotor <= 0) {
                        System.out.println("Potência do motor deve ser maior que zero. Tente novamente.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Potência do motor inválida. Tente novamente.");
                }
            }

            String tipoCombustivel = "";
            while (tipoCombustivel.isEmpty()) {
                System.out.print("Digite o tipo do combustível: ");
                tipoCombustivel = scanner.nextLine();
                if (tipoCombustivel.isEmpty()) {
                    System.out.println("Tipo do combustível não pode ser vazio. Tente novamente.");
                }
            }

            String tipoCambio = "";
            while (tipoCambio.isEmpty()) {
                System.out.print("Digite o tipo do câmbio: ");
                tipoCambio = scanner.nextLine();
                if (tipoCambio.isEmpty()) {
                    System.out.println("Tipo do câmbio não pode ser vazio. Tente novamente.");
                }
            }

            String marca = "";
            while (marca.isEmpty()) {
                System.out.print("Digite a marca do Veículo: ");
                marca = scanner.nextLine();
                if (marca.isEmpty()) {
                    System.out.println("Marca do Veículo não pode ser vazio. Tente novamente.");
                }
            }

            String modelo = "";
            while (modelo.isEmpty()) {
                System.out.print("Digite o modelo Veículo: ");
                modelo = scanner.nextLine();
                if (modelo.isEmpty()) {
                    System.out.println("Modelo do Veículo não pode ser vazio. Tente novamente.");
                }
            }

            int ano = 0;
            while (ano <= 0) {
                System.out.print("Digite o ano do Veículo: ");
                try {
                    ano = Integer.parseInt(scanner.nextLine());
                    if (ano <= 0) {
                        System.out.println("Ano do Veículo deve ser maior que zero. Tente novamente.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Ano do Veículo inválido. Tente novamente.");
                }
            }

            int quilometragem = 0;
            while (quilometragem <= 0) {
                System.out.print("Digite a quilometragem do Veículo: ");
                try {
                    quilometragem = Integer.parseInt(scanner.nextLine());
                    if (quilometragem < 0) {
                        System.out.println("Quilometragem do Veículo não pode ser negativa. Tente novamente.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Quilometragem do Veículo inválida. Tente novamente.");
                }
            }

            String cor = "";
            while (cor.isEmpty()) {
                System.out.print("Digite a cor do Veículo: ");
                cor = scanner.nextLine();
                if (cor.isEmpty()) {
                    System.out.println("Cor do Veículo não pode ser vazia. Tente novamente.");
                }
            }

            int capacidadeDePassageiro = 0;
            while (capacidadeDePassageiro <= 0) {
                System.out.print("Digite a capacidade de passageiros do Veículo: ");
                try {
                    capacidadeDePassageiro = Integer.parseInt(scanner.nextLine());
                    if (capacidadeDePassageiro <= 0) {
                        System.out.println("Capacidade de passageiros deve ser maior que zero. Tente novamente.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Capacidade de passageiros inválida. Tente novamente.");
                }
            }

            int numRodas = 0;
            while (numRodas <= 0) {
                System.out.print("Digite o número de rodas do Veículo: ");
                try {
                    numRodas = Integer.parseInt(scanner.nextLine());
                    if (numRodas <= 0) {
                        System.out.println("Número de rodas deve ser maior que zero. Tente novamente.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Número de rodas inválido. Tente novamente.");
                }
            }

            float preco = 0;
            while (preco <= 0) {
                System.out.print("Digite o preço do Veículo: ");
                try {
                    preco = Float.parseFloat(scanner.nextLine());
                    if (preco <= 0) {
                        System.out.println("Preço do Veículo deve ser maior que zero. Tente novamente.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Preço do Veículo inválido. Tente novamente.");
                }
            }

            int quantidade = 0;
            while (quantidade <= 0) {
                System.out.print("Digite a quantidade desse Veículo: ");
                try {
                    quantidade = Integer.parseInt(scanner.nextLine());
                    if (quantidade <= 0) {
                        System.out.println("Quantidade deve ser maior que zero. Tente novamente.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Quantidade inválida. Tente novamente.");
                }
            }

            Veiculo veiculo = new Carro(codVeiculo, marca, modelo, ano, quilometragem, cor,
                    capacidadeDePassageiro, preco, quantidade, numRodas, carroceria, numAssentos, portas,
                    capacidadePortaMalas, tipoMotor, potenciaMotor, tipoCombustivel, tipoCambio);
            carro.add(veiculo);
            veiculo.displayInfo();

            String text = String.join(",", String.valueOf(codVeiculo), "Carro", marca, modelo,
                    String.valueOf(ano), String.valueOf(quilometragem), cor, String.valueOf(capacidadeDePassageiro),
                    String.valueOf(preco), String.valueOf(quantidade), String.valueOf(numRodas), carroceria,
                    String.valueOf(numAssentos), String.valueOf(portas), String.valueOf(capacidadePortaMalas),
                    tipoMotor, String.valueOf(potenciaMotor), tipoCombustivel, tipoCambio);
            SistemaProduto.cadastrar(text);

        } else if (tipoVeiculo == 3) {
            System.out.println("Criar Veículo Aéreo (Avião)");

            int codVeiculo = proximoCodigoAviao++;
            System.out.println("O id do avião é " + codVeiculo);
            System.out.println("--Avião--");

            String tipo = "";
            while (tipo.isEmpty()) {
                System.out.print("Digite o tipo da aeronave: ");
                tipo = scanner.nextLine();
                if (tipo.isEmpty()) {
                    System.out.println("Tipo da aeronave não pode ser vazio. Tente novamente.");
                }
            }

            String marca = "";
            while (marca.isEmpty()) {
                System.out.print("Digite a marca da aeronave: ");
                marca = scanner.nextLine();
                if (marca.isEmpty()) {
                    System.out.println("Marca da aeronave não pode ser vazio. Tente novamente.");
                }
            }

            String modelo = "";
            while (modelo.isEmpty()) {
                System.out.print("Digite o modelo da aeronave: ");
                modelo = scanner.nextLine();
                if (modelo.isEmpty()) {
                    System.out.println("Modelo da aeronave não pode ser vazio. Tente novamente.");
                }
            }

            int ano = 0;
            while (ano <= 0) {
                System.out.print("Digite o ano de fabricação da aeronave: ");
                try {
                    ano = Integer.parseInt(scanner.nextLine());
                    if (ano <= 0) {
                        System.out.println("Ano de fabricação deve ser maior que zero. Tente novamente.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Ano de fabricação inválido. Tente novamente.");
                }
            }

            int quilometragem = 0;
            while (quilometragem <= 0) {
                System.out.print("Digite a quilometragem da aeronave: ");
                try {
                    quilometragem = Integer.parseInt(scanner.nextLine());
                    if (quilometragem < 0) {
                        System.out.println("Quilometragem da aeronave não pode ser negativa. Tente novamente.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Quilometragem da aeronave inválida. Tente novamente.");
                }
            }

            String cor = "";
            while (cor.isEmpty()) {
                System.out.print("Digite a cor da aeronave: ");
                cor = scanner.nextLine();
                if (cor.isEmpty()) {
                    System.out.println("Cor da aeronave não pode ser vazia. Tente novamente.");
                }
            }

            String tipoPropulsao = "";
            while (tipoPropulsao.isEmpty()) {
                System.out.print("Digite o tipo de propulsão da aeronave: ");
                tipoPropulsao = scanner.nextLine().trim();
                if (tipoPropulsao.isEmpty()) {
                    System.out.println("Tipo de propulsão não pode ser vazio. Tente novamente.");
                }
            }

            // Coleta do tipo de avião
            String tipoDeAviao = "";
            while (tipoDeAviao.isEmpty()) {
                System.out.print("Digite o tipo de avião: ");
                tipoDeAviao = scanner.nextLine().trim();
                if (tipoDeAviao.isEmpty()) {
                    System.out.println("Tipo de avião não pode ser vazio. Tente novamente.");
                }
            }

            // Coleta do alcance de voo
            double alcanceDeVoo = 0;
            while (alcanceDeVoo <= 0) {
                System.out.print("Digite o alcance de voo da aeronave (em km): ");
                try {
                    alcanceDeVoo = Double.parseDouble(scanner.nextLine());
                    if (alcanceDeVoo <= 0) {
                        System.out.println("Alcance de voo deve ser maior que zero. Tente novamente.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Valor inválido. Digite apenas números positivos.");
                }
            }


            int capacidadeDePassageiro = 0;
            while (capacidadeDePassageiro <= 0) {
                System.out.print("Digite a capacidade de passageiros da aeronave: ");
                try {
                    capacidadeDePassageiro = Integer.parseInt(scanner.nextLine());
                    if (capacidadeDePassageiro <= 0) {
                        System.out.println("Capacidade de passageiros deve ser maior que zero. Tente novamente.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Capacidade de passageiros inválida. Tente novamente.");
                }
            }

            int numMotores = 0;
            while (numMotores <= 0) {
                System.out.print("Digite o número de motores da aeronave: ");
                try {
                    numMotores = Integer.parseInt(scanner.nextLine());
                    if (numMotores <= 0) {
                        System.out.println("Número de motores deve ser maior que zero. Tente novamente.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Número de motores inválido. Tente novamente.");
                }
            }

            float preco = 0;
            while (preco <= 0) {
                System.out.print("Digite o preço da aeronave: ");
                try {
                    preco = Float.parseFloat(scanner.nextLine());
                    if (preco <= 0) {
                        System.out.println("Preço da aeronave deve ser maior que zero. Tente novamente.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Preço da aeronave inválido. Tente novamente.");
                }
            }

            int quantidade = 0;
            while (quantidade <= 0) {
                System.out.print("Digite a quantidade dessa aeronave: ");
                try {
                    quantidade = Integer.parseInt(scanner.nextLine());
                    if (quantidade <= 0) {
                        System.out.println("Quantidade deve ser maior que zero. Tente novamente.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Quantidade inválida. Tente novamente.");
                }
            }

            Veiculo veiculo = new Aviao(codVeiculo, tipo, marca, modelo, ano, quilometragem, cor,
                    capacidadeDePassageiro, preco, quantidade, tipoPropulsao, numMotores, tipoDeAviao, alcanceDeVoo);
            aviao.add(veiculo);
            veiculo.displayInfo();

            String text = String.join(",", String.valueOf(codVeiculo), "Avião", marca, modelo,
                    String.valueOf(ano), String.valueOf(quilometragem), cor, String.valueOf(capacidadeDePassageiro),
                    String.valueOf(preco), String.valueOf(quantidade), String.valueOf(numMotores));
            SistemaProduto.cadastrar(text);

        } else if (tipoVeiculo == 2) {
            System.out.println("Criar Embarcação");

            int codVeiculo = proximoCodigoEmbarcacao++;
            System.out.println("O id da embarcação é " + codVeiculo);
            System.out.println("--Embarcação--");

            String tipo = "";
            while (tipo.isEmpty()) {
                System.out.print("Digite o tipo da embarcação: ");
                tipo = scanner.nextLine().trim();
                if (tipo.isEmpty()) {
                    System.out.println("Tipo da embarcação não pode ser vazio. Tente novamente.");
                }
            }

            String marca = "";
            while (marca.isEmpty()) {
                System.out.print("Digite a marca da embarcação: ");
                marca = scanner.nextLine().trim();
                if (marca.isEmpty()) {
                    System.out.println("Marca da embarcação não pode ser vazio. Tente novamente.");
                }
            }

            String modelo = "";
            while (modelo.isEmpty()) {
                System.out.print("Digite o modelo da embarcação: ");
                modelo = scanner.nextLine().trim();
                if (modelo.isEmpty()) {
                    System.out.println("Modelo da embarcação não pode ser vazio. Tente novamente.");
                }
            }

            int ano = 0;
            while (ano <= 0) {
                System.out.print("Digite o ano da embarcação: ");
                try {
                    ano = Integer.parseInt(scanner.nextLine());
                    if (ano <= 0) {
                        System.out.println("Ano da embarcação deve ser maior que zero. Tente novamente.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Ano da embarcação inválido. Tente novamente.");
                }
            }

            int quilometragem = 0;
            while (quilometragem <= 0) {
                System.out.print("Digite a quilometragem da embarcação: ");
                try {
                    quilometragem = Integer.parseInt(scanner.nextLine());
                    if (quilometragem < 0) {
                        System.out.println("Quilometragem da embarcação não pode ser negativa. Tente novamente.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Quilometragem da embarcação inválida. Tente novamente.");
                }
            }

            String cor = "";
            while (cor.isEmpty()) {
                System.out.print("Digite a cor da embarcação: ");
                cor = scanner.nextLine().trim();
                if (cor.isEmpty()) {
                    System.out.println("Cor da embarcação não pode ser vazia. Tente novamente.");
                }
            }

            int capacidadeDePassageiro = 0;
            while (capacidadeDePassageiro <= 0) {
                System.out.print("Digite a capacidade de passageiros da embarcação: ");
                try {
                    capacidadeDePassageiro = Integer.parseInt(scanner.nextLine());
                    if (capacidadeDePassageiro <= 0) {
                        System.out.println("Capacidade de passageiros da embarcação deve ser maior que zero. Tente novamente.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Capacidade de passageiros da embarcação inválida. Tente novamente.");
                }
            }

            String tipoPropulsao = "";
            while (tipoPropulsao.isEmpty()) {
                System.out.print("Digite o tipo de propulsão da embarcação: ");
                tipoPropulsao = scanner.nextLine().trim();
                if (tipoPropulsao.isEmpty()) {
                    System.out.println("Tipo de propulsão da embarcação não pode ser vazio. Tente novamente.");
                }
            }

            double alcanceNavegacao = 0;
            while (alcanceNavegacao <= 0) {
                System.out.print("Digite o alcance de navegação da embarcação (em km): ");
                try {
                    alcanceNavegacao = Double.parseDouble(scanner.nextLine());
                    if (alcanceNavegacao <= 0) {
                        System.out.println("Alcance de navegação da embarcação deve ser maior que zero. Tente novamente.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Alcance de navegação da embarcação inválido. Tente novamente.");
                }
            }

            double preco = 0;
            while (preco <= 0) {
                System.out.print("Digite o preço da embarcação: ");
                try {
                    preco = Double.parseDouble(scanner.nextLine());
                    if (preco <= 0) {
                        System.out.println("Preço da embarcação deve ser maior que zero. Tente novamente.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Preço da embarcação inválido. Tente novamente.");
                }
            }

            int quantidade = 0;
            while (quantidade <= 0) {
                System.out.print("Digite a quantidade dessa embarcação: ");
                try {
                    quantidade = Integer.parseInt(scanner.nextLine());
                    if (quantidade <= 0) {
                        System.out.println("Quantidade de embarcações deve ser maior que zero. Tente novamente.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Quantidade de embarcações inválida. Tente novamente.");
                }
            }

            Veiculo veiculo = new Embarcacao(codVeiculo, tipo, marca, modelo, ano, quilometragem, cor,
                    capacidadeDePassageiro, preco, quantidade, tipoPropulsao, alcanceNavegacao);
            embarcacao.add(veiculo);
            veiculo.displayInfo();

            String text = String.join(",", String.valueOf(codVeiculo), "Embarcação", marca, modelo,
                    String.valueOf(ano), String.valueOf(quilometragem), cor, String.valueOf(capacidadeDePassageiro),
                    String.valueOf(preco), String.valueOf(quantidade), tipoPropulsao);
            SistemaProduto.cadastrar(text);
        }
    }

    public void comprarProduto(Scanner scanner) {
        System.out.println("-- Realizar Pagamento --");

        System.out.print("Informe o valor da compra: R$");
        double valorCompra = Double.parseDouble(scanner.nextLine());

        System.out.println("Escolha a forma de pagamento:");
        System.out.println("1. Cartão de Crédito");
        System.out.println("2. Cartão de Débito");
        System.out.println("3. Dinheiro");
        System.out.println("4. Pix");

        int formaPagamento = Integer.parseInt(scanner.nextLine());

        PagamentoStrategy estrategiaPagamento;

        switch (formaPagamento) {
            case 1:
                estrategiaPagamento = new PagamentoCartaoCredito();
                break;
            case 2:
                estrategiaPagamento = new PagamentoCartaoDebito();
                break;
            case 3:
                estrategiaPagamento = new PagamentoDinheiro();
                break;
            case 4:
                estrategiaPagamento = new PagamentoPix();
                break;
            default:
                System.out.println("Opção inválida! Pagamento cancelado.");
                return;
        }

        estrategiaPagamento.pagar(valorCompra);
    }


    @Override
    public String toString() {
        return "ID: " + idUsuario + ", Nome: " + nome + ", Tipo: " + tipo + ", CPF: " + cpf + ", Email: " + email +
                ", Telefone: " + telefone + ", Endereço: " + endereco;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
