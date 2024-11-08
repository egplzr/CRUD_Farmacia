package br.com.crudfarmacia.dao;

import br.com.crudfarmacia.model.Medicamento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class FarmaciaDao implements IDao<Medicamento>{
    private final EntityManager manager;
    public FarmaciaDao(EntityManager manager){
        this.manager = manager;
    }

    @Override
    public void inserir(Medicamento objeto) throws Exception {
        this.manager.persist(objeto);
        this.manager.getTransaction().begin();
        this.manager.getTransaction().commit();
    }

    @Override
    public void alterar(Medicamento objeto) throws Exception {
        this.manager.merge(objeto);
        this.manager.getTransaction().begin();
        this.manager.getTransaction().commit();
    }

    @Override
    public Medicamento buscar(long id) throws Exception {
        return this.manager.find(Medicamento.class, id);
    }

    @Override
    public void excluir(long id) throws Exception {
        Medicamento medicamento = buscar(id);
        this.manager.remove(medicamento);
        this.manager.getTransaction().begin();
        this.manager.getTransaction().commit();
    }

    @Override
    public List<Medicamento> listar() throws Exception {
        TypedQuery<Medicamento> query = this.manager.createQuery("select m from Medicamento m order by m.nome", Medicamento.class);
        return query.getResultList();
    }
}
