
package PJBL.Adapter;

import PJBL.Usuarios.Usuario;

import java.util.List;

public class TelefoneAdapter implements VerificadorAdapter {
    @Override
    public boolean verificar(List<Usuario> usuarios, String telefone) {
        return Verificador.verificarTelefone(telefone);
    }
}
