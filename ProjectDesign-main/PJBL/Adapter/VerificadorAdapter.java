package PJBL.Adapter;

import PJBL.Usuarios.Usuario;

import java.util.List;

public interface VerificadorAdapter {
    boolean verificar(List<Usuario> usuarios, String identificador);
}