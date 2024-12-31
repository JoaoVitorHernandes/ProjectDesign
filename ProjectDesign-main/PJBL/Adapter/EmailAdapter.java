
package PJBL.Adapter;

import PJBL.Usuarios.Usuario;

import java.util.List;

public class EmailAdapter implements VerificadorAdapter {

    @Override
    public boolean verificar(List<Usuario> usuarios, String email) {
        return Verificador.verificarEmail(usuarios, email);
    }
}
