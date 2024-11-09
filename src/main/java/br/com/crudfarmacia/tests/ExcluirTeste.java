package br.com.crudfarmacia.tests;

import br.com.crudfarmacia.dao.EMfactory;
import br.com.crudfarmacia.dao.FarmaciaDao;
import br.com.crudfarmacia.model.Medicamento;

import java.util.List;


public class ExcluirTeste {
    public static void main(String[] args) throws Exception {
        FarmaciaDao farmaciaDao = new FarmaciaDao(EMfactory.getEntityManager());
        farmaciaDao.excluir(2);

        List<Medicamento> listarMedicamentos = farmaciaDao.listar();
        for (Medicamento m : listarMedicamentos) {
            System.out.println(m);
        }
    }
}
