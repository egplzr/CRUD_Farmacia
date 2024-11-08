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
        List<Farmaco> lista = new ArrayList<>();
        lista.add(new Farmaco("RemedioTeste", "100mg"));
        lista.add(new Farmaco("RemedioTeste2", "200mg"));
        lista.add(new Farmaco("RemedioTeste3", "300mg"));






        Medicamento medicamentoTeste = new Medicamento("medicamentoTeste", "SenacLab", lista, Categoria.CAPSULA);
        for(Farmaco f : lista){
            f.setMedicamento(medicamentoTeste);
        }
        farmaciaDao.inserir(medicamentoTeste);
    }
}
