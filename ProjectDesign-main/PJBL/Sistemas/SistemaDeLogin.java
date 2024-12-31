package PJBL.Sistemas;

import PJBL.Usuarios.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SistemaDeLogin {

    //Atributo com o nome do arquivo
    private static final String FILE_PATH = "src/PJBL/";
    private static final String FILE_NAME = FILE_PATH + "usuarios.txt";

    // Carregar usuários do arquivo
    public static List<Usuario> carregarUsuarios() throws LoginException {
        List<Usuario> usuarios = new ArrayList<>();
        File file = new File(FILE_NAME);
        // Verifica se o arquivo existe, se não, cria um arquivo vazio
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("Arquivo de usuários criado com sucesso.");
                } else {
                    throw new LoginException("Erro ao criar o arquivo de usuários: motivo desconhecido.");
                }
            } catch (IOException e) {
                throw new LoginException("Erro ao criar o arquivo de usuários: " + e.getMessage());
            }
        }
        //Abre o arquivo e le linha por linha
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                try {
                    // Converte a String lida em um Usuario
                    Usuario usuario = Usuario.fromFileString(linha);
                    // Se usuario for válido, adiciona na lista
                    usuarios.add(usuario);
                } catch (LoginException e) {
                    throw new LoginException("Erro ao processar linha: " + linha + ". Detalhes: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            throw new LoginException("Erro ao carregar usuários: " + e.getMessage());
        }
        return usuarios;
    }

    // Salvar usuário no arquivo
    public static void salvarUsuario(Usuario usuario) throws LoginException{
        //Abre o arquivo em modo de adição e inicia a escrita
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            //Adiciona mais uma linha no arquivo
            bw.newLine();
            //Transforma o objeto Usuario em uma string
            bw.write(usuario.toFileString());
        } catch (IOException e) {
            throw new LoginException("Erro ao salvar usuário: " + e.getMessage());
        }
    }

    public static void atualizarUsuario(Usuario usuario, String cpfFormatted) throws LoginException {
        List<String> linhas = new ArrayList<>();

        // Leitura do arquivo
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                Usuario usuarioLinha = Usuario.fromFileString(linha);
                if (usuarioLinha.getCpf().equals(cpfFormatted)) {
                    // Substitui o usuário antigo pelo novo usuário editado
                    linhas.add(usuario.toFileString());
                } else {
                    // Adiciona as linhas que não foram modificadas
                    linhas.add(linha);
                }
            }
        } catch (IOException e) {
            throw new LoginException("Erro ao ler arquivo: " + e.getMessage());
        }

        // Escrita do arquivo
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            int size = linhas.size();
            for (int i = 0; i < size; i++) {
                bw.write(linhas.get(i));
                if (i < size - 1) {
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            throw new LoginException("Erro ao escrever arquivo: " + e.getMessage());
        }
    }


    public static void removerUsuario(String cpfFormatted) throws LoginException {
        List<String> linhas = new ArrayList<>();

        // Leitura do arquivo
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                Usuario usuarioLinha = Usuario.fromFileString(linha);
                if (!usuarioLinha.getCpf().equals(cpfFormatted)) {
                    // Adiciona as linhas que não correspondem ao usuário a ser removido
                    linhas.add(linha);
                }
            }
        } catch (IOException e) {
            throw new LoginException("Erro ao ler arquivo: " + e.getMessage());
        }

        // Escrita do arquivo
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            int size = linhas.size();
            for (int i = 0; i < size; i++) {
                bw.write(linhas.get(i));
                if (i < size - 1) {
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            throw new LoginException("Erro ao escrever arquivo: " + e.getMessage());
        }
    }


    // Autenticar usuário
    public static Usuario autenticar(String email, String senha, List<Usuario> usuarios) {
        //Intera por cada usuario
        for (Usuario usuario : usuarios) {
            //Se o dados baterem, ele autentica
            if (usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)) {
                return usuario;
            }
        }
        return null;
    }
}
