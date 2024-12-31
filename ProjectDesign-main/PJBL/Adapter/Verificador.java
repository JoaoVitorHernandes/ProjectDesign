package PJBL.Adapter;
import PJBL.Usuarios.Usuario;

import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;


public class Verificador {
    public static boolean verificarCPF(List<Usuario> usuarios, String cpf) {
        if (cpf == null || cpf.isBlank()) {
            return false;
        }
        // Remove as pontuações do CPF e verifica se todos os caracteres restantes são dígitos
        cpf = cpf.replaceAll("\\D", "");
        if (cpf.length() != 11 || !cpf.matches("\\d{11}")) {
            return false;
        }
        // Verifica se o CPF já está em uso
        for (Usuario usuario : usuarios) {
            // Remove as pontuações do Cpf já cadastrado
            String cpfUsuario = usuario.getCpf().replaceAll("\\D", "");
            if (cpfUsuario.equals(cpf)) {
                return false; // O CPF fornecido já está em uso
            }
        }
        // Calcula e verifica os dígitos verificadores do CPF
        int digito1 = Character.getNumericValue(cpf.charAt(9));
        int digito2 = Character.getNumericValue(cpf.charAt(10));
        int soma, resto, resultado;

        soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }
        resto = soma % 11;
        if (resto < 2) {
            resultado = 0;
        } else {
            resultado = 11 - resto;
        }
        if (resultado != digito1) {
            return false;
        }

        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }
        resto = soma % 11;
        if (resto < 2) {
            resultado = 0;
        } else {
            resultado = 11 - resto;
        }
        return resultado == digito2;
    }

    public static boolean verificaCPF(String cpf) {
        if (cpf == null || cpf.isBlank()) {
            return false;
        }
        // Remove as pontuações do CPF e verifica se todos os caracteres restantes são dígitos
        cpf = cpf.replaceAll("\\D", "");
        if (cpf.length() != 11 || !cpf.matches("\\d{11}")) {
            return false;
        }

        // Calcula e verifica os dígitos verificadores do CPF
        int digito1 = Character.getNumericValue(cpf.charAt(9));
        int digito2 = Character.getNumericValue(cpf.charAt(10));
        int soma, resto, resultado;

        soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }
        resto = soma % 11;
        if (resto < 2) {
            resultado = 0;
        } else {
            resultado = 11 - resto;
        }
        if (resultado != digito1) {
            return false;
        }

        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }
        resto = soma % 11;
        if (resto < 2) {
            resultado = 0;
        } else {
            resultado = 11 - resto;
        }
        return resultado == digito2;
    }


    public static boolean verificarTelefone(String telefone) {
        if (telefone == null || telefone.isBlank()) {
            return false;
        }
        List<String> DDD = new ArrayList<>(List.of(
                "11", "12", "13", "14", "15", "16", "17", "18", "19",
                "21", "22", "24", "27", "28", "31", "32", "33", "34", "35", "37", "38", "41",
                "42", "43", "44", "45", "46", "47", "48", "49", "51", "53", "54", "55", "61",
                "62", "63", "64", "65", "66", "67", "68", "69", "71", "73", "74", "75", "77",
                "79", "81", "82", "83", "84", "85", "86", "87", "88", "89", "91", "92", "93",
                "94", "95", "96", "97", "98", "99"
        ));
        telefone = telefone.replaceAll("\\D", "");
        if (telefone.length() != 11 || !telefone.matches("\\d{11}")) {
            return false;
        } else if (Character.getNumericValue(telefone.charAt(2)) != 9) {
            return false;
        } else return DDD.contains(telefone.substring(0, 2));
    }

    public static boolean verificarEmail(List<Usuario> usuarios, String email) {
        // Verifica se o e-mail corresponde à expressão regular
        String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        if (!Pattern.matches(EMAIL_REGEX, email)) {
            return false; // O formato do e-mail não é válido
        }
        // Verifica se o e-mail já está em uso
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email)) {
                return false; // O e-mail já está em uso
            }
        }
        return true; // O e-mail é válido e não está em uso
    }



}
