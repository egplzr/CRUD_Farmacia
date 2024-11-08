package br.com.crudfarmacia;

import br.com.crudfarmacia.dao.EMfactory;
import br.com.crudfarmacia.dao.FarmaciaDao;
import br.com.crudfarmacia.model.Categoria;
import br.com.crudfarmacia.model.Farmaco;
import br.com.crudfarmacia.model.Medicamento;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;

public class Teste {
    private static FarmaciaDao farmaciaDao = new FarmaciaDao(EMfactory.getEntityManager());

    public static void main(String[] args) throws Exception {
        Medicamento medicamento = new Medicamento("Paracetamol", "SenacLab", Categoria.COMPRIMIDO);

        Farmaco farmaco1 = new Farmaco("Paracetamol", "500mg", medicamento);
        Farmaco farmaco2 = new Farmaco("Cafeína", "50mg", medicamento);

        medicamento.adicionarFarmaco(farmaco1);
        medicamento.adicionarFarmaco(farmaco2);

        farmaciaDao.inserir(medicamento);
        farmaciaDao.buscar(1);
    }
}
