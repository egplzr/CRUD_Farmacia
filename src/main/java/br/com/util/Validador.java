package br.com.util;

public class Validador {
    
    public boolean String validarCpf(String txt){
        formatar(txt);

        //Validando primeiro digito verificador
        int[] pesos = {10,9,8,7,6,5,4,3,2};

        for (int i = 0; i < pesos.length; i++) {
            finalNum += numValidar[i] * pesos[i];
        }

        int rest = finalNum %11;

        if (rest < 2) {
            if (!(numValidar[9] == 0)){
                return true;
            }
        } else if (!(numValidar[9] == 11 -rest)){
            return false;
        }

        //Validando segundo digito verificador;
        pesos = new int[]{11,10,9,8,7,6,5,4,3,2};
        finalNum = 0;

        for (int i = 0; i < pesos.length; i++) {
            finalNum += numValidar[i] * pesos[i];
        }

        rest = finalNum %11;

        if (rest < 2) {
            if (!(numValidar[10] == 0)){
                return true;
            }
        } else if (!(numValidar[10] == 11 -rest)){
            return false;
        }

        return true;
    }

    private static void formatar(String txt){
        int[] numValidar = new int[txt.length()];

        StringBuilder sb = new StringBuilder();
        for(char c : txt.toCharArray()) {
            if(Character.isDigit(c)){
                sb.append(c);
            }
        }

        for (int i = 0; i < sb.toString().length(); i++) {
            numValidar[i] = Character.getNumericValue(sb.toString().charAt(i));
        }

    }
}
