package br.com.crudfarmacia.dao;

import br.com.crudfarmacia.model.Medicamento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
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

        objeto = this.buscar(objeto.getId());
        if (objeto != null) {
            this.manager.getTransaction().begin();
            objeto.setNome(objeto.getNome());
            objeto.setLaboratorio(objeto.getLaboratorio());
            objeto.setCategoria(objeto.getCategoria());
            objeto.setPrincipioAtivo(objeto.getPrincipioAtivo());
            this.manager.merge(objeto);
            this.manager.getTransaction().commit();
        } else {
            throw new Exception("Medicamento não encontrado para atualizar!");
        }
    }

    @Override
    public Medicamento buscar(long id) throws Exception {
        return this.manager.find(Medicamento.class, id);
    }

    public Medicamento buscar(String nome) throws Exception {
        List<Medicamento> ms = new ArrayList<>();
        ms = this.listar();

        for(Medicamento m : ms){
            if(m.getNome().equals(nome)){
                return m;
            }
        }

        return null;
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