package br.com.crudfarmacia.tests;

import br.com.crudfarmacia.dao.EMfactory;
import br.com.crudfarmacia.dao.FarmaciaDao;
import br.com.crudfarmacia.model.Medicamento;

public class AlterarTeste {
    public static void main(String[] args) throws Exception {
        FarmaciaDao farmaciaDao = new FarmaciaDao(EMfactory.getEntityManager());
        Medicamento medicamentoAlterado = farmaciaDao.buscar(2);

        System.out.println(medicamentoAlterado);

        medicamentoAlterado.setNome("Pararararar");
        farmaciaDao.alterar(medicamentoAlterado);

        System.out.println(medicamentoAlterado);
    }
}
