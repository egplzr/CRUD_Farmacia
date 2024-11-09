package br.com.crudfarmacia.tests;

import br.com.crudfarmacia.dao.EMfactory;
import br.com.crudfarmacia.dao.FarmaciaDao;
import br.com.crudfarmacia.model.Medicamento;

import java.util.List;

public class BuscarTeste {
    public static void main(String[] args) throws Exception {
        FarmaciaDao farmaciaDao = new FarmaciaDao(EMfactory.getEntityManager());
        Medicamento medicamentoBuscado = farmaciaDao.buscar(6);
        System.out.println(medicamentoBuscado);

        System.out.println("Listando todos os medicamentos no banco:\n");
        List<Medicamento> listarMedicamentos = farmaciaDao.listar();
        for (Medicamento m : listarMedicamentos) {
            System.out.println(m);
        }
    }
}
