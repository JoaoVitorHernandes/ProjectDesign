package PJBL;

import PJBL.GUI.LoginScreen;
import PJBL.Observer.Subject;
import PJBL.Produtos.Veiculo;
import PJBL.Singleton.ProdutoDBsingleton;
import PJBL.Sistemas.LoginException;
import PJBL.Strategy.*;
import PJBL.Usuarios.Administrador;
import PJBL.Usuarios.Funcionario;
import PJBL.Usuarios.Usuario;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static PJBL.Sistemas.SistemaDeLogin.autenticar;
import static PJBL.Sistemas.SistemaDeLogin.carregarUsuarios;

public class Main {
    public static void main(String[] args) throws LoginException {
        List<Usuario> usuarios = carregarUsuarios();
        List<String> produtos = ProdutoDBsingleton.getInstance().carregarProdutos();

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                try {
                    System.out.println("--- BEM-VINDO ---");
                    System.out.println("1. Login");
                    System.out.println("2. Interface");
                    System.out.println("3. Sair");
                    System.out.print("--> ");

                    int opcao = Integer.parseInt(scanner.nextLine());
                    if (opcao == 1) {
                        login(scanner, usuarios);
                        break;
                    } else if (opcao == 2) {
                        SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                new LoginScreen(usuarios, produtos);
                            }
                        });
                    } else if (opcao == 3) {
                        break;
                    } else {
                        System.out.println("Opção inválida.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida! Por favor, digite um número válido.");
                }
            }
        }

    }

    public static void login(Scanner scanner, List<Usuario> usuarios) throws LoginException {
        while (true) {
            try {
                System.out.println("--- FAÇA SEU LOGIN ---");
                System.out.print("Digite seu email: ");
                String email = scanner.nextLine();
                System.out.print("Digite sua senha: ");
                String senha = scanner.nextLine();

                Usuario usuarioLogado = autenticar(email, senha, usuarios);

                if (usuarioLogado != null) {
                    System.out.println("Login bem-sucedido! Bem-vindo, " + usuarioLogado.getNome() + ".");
                    menu(scanner, usuarioLogado, usuarios);
                    break;
                } else {
                    System.out.println("Email ou senha incorretos.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Por favor, digite um número válido.");
            }
        }

    }

    public static void menu(Scanner scanner, Usuario usuarioLogado, List<Usuario> usuarios) throws LoginException {
        List<Veiculo> carros = new ArrayList<>();
        List<Veiculo> avioes = new ArrayList<>();
        List<Veiculo> embarcacoes = new ArrayList<>();
        List<String> produtos = ProdutoDBsingleton.getInstance().carregarProdutos();
        Subject subject = new Subject();
        for (Usuario usuario : usuarios) {
            if (usuario instanceof Administrador) {
                subject.adicionarObservador((Administrador) usuario);
            }
        }

        while (true) {
            try {
                if (usuarioLogado instanceof Administrador admin) {
                    System.out.println("--- MENU ADMINISTRADOR ---");
                    System.out.println("1. Criar novo usuário");
                    System.out.println("2. Editar usuário");
                    System.out.println("3. Deletar usuário");
                    System.out.println("4. Listar usuários");
                    System.out.println("5. Cadastrar Produto");
                    System.out.println("6. Carregar Produtos");
                    System.out.println("7. Editar Produtos");
                    System.out.println("8. Excluir Produtos");
                    System.out.println("9. Realizar Pagamento");
                    System.out.println("0. Sair");
                    System.out.print("--> ");

                    int escolha = Integer.parseInt(scanner.nextLine());

                    if (escolha == 1) {
                        System.out.println("-- Cadastro de Usuário --");
                        admin.criarNovoUsuario(scanner, usuarios, subject);
                    } else if (escolha == 2) {
                        System.out.println("-- Edição de Usuário --");
                        admin.editarUsuario(scanner, usuarios);
                    } else if (escolha == 3) {
                        System.out.println("-- Exclusão de Usuário --");
                        admin.deletarUsuario(scanner, usuarios, usuarioLogado, subject);
                    } else if (escolha == 4) {
                        System.out.println("-- Listagem de Usuários --");
                        admin.listarUsuarios(usuarios);
                    } else if (escolha == 5) {
                        System.out.println("-- Cadastro de Veículos --");
                        System.out.println("1. Veículo Terrestre");
                        System.out.println("2. Veículo Aquático");
                        System.out.println("3. Veículo Aéreo");

                        int tipoVeiculo = Integer.parseInt(scanner.nextLine());

                        if (tipoVeiculo == 1) {
                            admin.criarNovoProduto(scanner, 1, carros, new ArrayList<>(), new ArrayList<>());
                        } else if (tipoVeiculo == 2) {
                            admin.criarNovoProduto(scanner, 2, new ArrayList<>(), new ArrayList<>(),
                                    embarcacoes);
                        } else if (tipoVeiculo == 3) {
                            admin.criarNovoProduto(scanner, 3, new ArrayList<>(), avioes, new ArrayList<>());
                        } else {
                            System.out.println("Opção inválida.");
                        }
                    } else if (escolha == 6) {
                        System.out.println("-- Produtos Carregados --");
                        produtos = ProdutoDBsingleton.getInstance().carregarProdutos();
                        for (String produto : produtos) {
                            System.out.println(produto);
                        }
                    } else if (escolha == 7) {
                        ProdutoDBsingleton.getInstance().editarProduto(scanner, produtos);
                    } else if (escolha == 8) {
                        ProdutoDBsingleton.getInstance().excluirProduto(scanner, produtos);
                    } else if (escolha == 9) {
                        admin.comprarProduto(scanner);
                    } else if (escolha == 0) {
                        break;
                    } else {
                        System.out.println("Opção inválida.");
                    }
                } else if (usuarioLogado instanceof Funcionario funcio) {
                    System.out.println("--- MENU FUNCIONARIO ---");
                    System.out.println("1. Cadastrar Produto");
                    System.out.println("2. Carregar Produtos");
                    System.out.println("3. Editar Produtos");
                    System.out.println("4. Excluir Produtos");
                    System.out.println("5. Listar Funcionarios");
                    System.out.println("6. Realizar Pagamento");
                    System.out.println("0. Sair");
                    System.out.print("--> ");

                    int escolha = Integer.parseInt(scanner.nextLine());

                    if (escolha == 1) {
                        System.out.println("-- Cadastro de Veículos --");
                        System.out.println("1. Veículo Terrestre");
                        System.out.println("2. Veículo Aquático");
                        System.out.println("3. Veículo Aéreo");

                        int tipoVeiculo = Integer.parseInt(scanner.nextLine());

                        if (tipoVeiculo == 1) {
                            funcio.criarNovoProduto(scanner, 1, carros, new ArrayList<>(), new ArrayList<>());
                        } else if (tipoVeiculo == 2) {
                            funcio.criarNovoProduto(scanner, 2, new ArrayList<>(), new ArrayList<>(),
                                    embarcacoes);
                        } else if (tipoVeiculo == 3) {
                            funcio.criarNovoProduto(scanner, 3, new ArrayList<>(), avioes, new ArrayList<>());
                        } else {
                            System.out.println("Opção inválida.");
                        }
                    } else if (escolha == 2) {
                        System.out.println("-- Produtos Carregados --");
                        produtos = ProdutoDBsingleton.getInstance().carregarProdutos();
                        for (String produto : produtos) {
                            System.out.println(produto);
                        }
                    } else if (escolha == 3) {
                        ProdutoDBsingleton.getInstance().editarProduto(scanner, produtos);
                    } else if (escolha == 4) {
                        ProdutoDBsingleton.getInstance().excluirProduto(scanner, produtos);
                    } else if (escolha == 5) {
                        System.out.println("-- Listagem de Funcionários --");
                        funcio.listarUsuarios(usuarios);
                    } else if (escolha == 6) {
                        funcio.comprarProduto(scanner);
                    } else if (escolha == 0) {
                        break;
                    } else {
                        System.out.println("Opção inválida.");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Por favor, digite um número válido.");
            } catch (LoginException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
