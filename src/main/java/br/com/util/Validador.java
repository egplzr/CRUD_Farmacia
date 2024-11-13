package br.com.util;

public class Validador {
    private static int[] numValidar;

    public static boolean validarCpf(String txt) {
        formatar(txt);

        if (numValidar.length != 11) {
            return false;
        }

        boolean digitosIguais = true;
        for (int i = 1; i < numValidar.length; i++) {
            if (numValidar[i] != numValidar[0]) {
                digitosIguais = false;
                break;
            }
        }
        if (digitosIguais) {
            return false;
        }

        int soma = 0;
        int[] pesos = {10, 9, 8, 7, 6, 5, 4, 3, 2};

        for (int i = 0; i < pesos.length; i++) {
            soma += numValidar[i] * pesos[i];
        }

        int resto = soma % 11;
        int primeiroDigitoVerificador = (resto < 2) ? 0 : 11 - resto;

        if (numValidar[9] != primeiroDigitoVerificador) {
            return false;
        }

        soma = 0;
        pesos = new int[]{11, 10, 9, 8, 7, 6, 5, 4, 3, 2};

        for (int i = 0; i < pesos.length; i++) {
            soma += numValidar[i] * pesos[i];
        }

        resto = soma % 11;
        int segundoDigitoVerificador = (resto < 2) ? 0 : 11 - resto;

        return numValidar[10] == segundoDigitoVerificador;
    }

    private static void formatar(String txt) {
        numValidar = new int[11];
        StringBuilder sb = new StringBuilder();

        for (char c : txt.toCharArray()) {
            if (Character.isDigit(c)) {
                sb.append(c);
            }
        }

        if (sb.length() != 11) {
            numValidar = new int[0];
            return;
        }

        for (int i = 0; i < 11; i++) {
            numValidar[i] = Character.getNumericValue(sb.charAt(i));
        }
    }
}
