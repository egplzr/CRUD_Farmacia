package br.com.crudfarmacia.tests;

import br.com.crudfarmacia.dao.EMfactory;
import br.com.crudfarmacia.dao.FarmaciaDao;
import br.com.crudfarmacia.model.Categoria;
import br.com.crudfarmacia.model.Farmaco;
import br.com.crudfarmacia.model.Medicamento;

public class InserirTeste {
    public static void main(String[] args) throws Exception {
        FarmaciaDao farmaciaDao = new FarmaciaDao(EMfactory.getEntityManager());
        Medicamento medicamento = new Medicamento("Resfenol", "SenacLab", Categoria.CAPSULA);

        Farmaco farmaco1 = new Farmaco("Paracetamol", "200mg", medicamento);
        Farmaco farmaco2 = new Farmaco("Resfriadonol", "150mg", medicamento);

        medicamento.adicionarFarmaco(farmaco1);
        medicamento.adicionarFarmaco(farmaco2);
        farmaciaDao.inserir(medicamento);
    }
}
