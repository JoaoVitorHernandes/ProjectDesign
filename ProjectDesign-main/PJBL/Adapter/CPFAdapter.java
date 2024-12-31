package PJBL.Adapter;

import PJBL.Usuarios.Usuario;

import java.util.List;

public class CPFAdapter implements VerificadorAdapter {

    @Override
    public boolean verificar(List<Usuario> usuarios, String cpf) {
        // Verifica se a lista de usuários é nula ou vazia
        if (usuarios == null || usuarios.isEmpty()) {
            // Valida apenas o formato do CPF
            return Verificador.verificaCPF(cpf);
        } else {
            // Valida o CPF levando em consideração a lista de usuários
            return Verificador.verificarCPF(usuarios, cpf);
        }
    }
}
