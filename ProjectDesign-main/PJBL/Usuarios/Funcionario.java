package PJBL.Usuarios;

import java.util.List;

public class Funcionario extends Usuario {
    public Funcionario(int idUsuario, String nome, String cpf, String email, String senha, String telefone, String endereco) {
        super(idUsuario, nome, "Funcionario", cpf, email, senha, telefone, endereco);
    }

    @Override
    public void listarUsuarios(List<Usuario> usuarios) {
        System.out.println("Funcionários:");
        for (Usuario usuario : usuarios) {
            if (usuario.getTipo().equals("Funcionario")) {
                exibirInfo(usuario);
                System.out.println();
            }
        }
    }

    public void exibirInfo(Usuario usuario) {
        System.out.println("Nome: " + usuario.getNome());
        System.out.println("Email: " + usuario.getEmail());
        System.out.println("Telefone: " + usuario.getTelefone());
        System.out.println("Endereço: " + usuario.getEndereco());
    }
}
