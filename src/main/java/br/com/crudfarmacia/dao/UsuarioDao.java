package br.com.crudfarmacia.dao;

import java.util.List;

import javax.swing.text.html.parser.Entity;

import org.hibernate.query.TypedParameterValue;

import br.com.crudfarmacia.model.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class UsuarioDao implements IDao<Usuario>{
    private final EntityManager manager;

    public UsuarioDao(EntityManager manager){
        this.manager = manager;
    }

    @Override
    public void inserir(Usuario objeto) throws Exception {
        this.manager.persist(objeto);
        this.manager.getTransaction().begin();
        this.manager.getTransaction().commit();
    }

    @Override
    public void alterar(Usuario objeto) throws Exception {

        objeto = this.buscar(objeto.getId());
        if (objeto != null) {
            this.manager.getTransaction().begin();
            objeto.setCpf(objeto.getCpf());
            objeto.setSenha(objeto.getSenha());
            this.manager.merge(objeto);
            this.manager.getTransaction().commit();
        } else {
            throw new Exception("Usuário não encontrado para atualizar!");
        }
    }

    @Override
    public Usuario buscar(long id) throws Exception {
        return this.manager.find(Usuario.class, id);
    }

    @Override
    public void excluir(long id) throws Exception {
        Usuario user = buscar(id);
        this.manager.remove(user);
        this.manager.getTransaction().begin();
        this.manager.getTransaction().commit();
    }

    @Override
    public List<Usuario> listar() throws Exception {
        TypedQuery<Usuario> query = this.manager.createQuery("select m from Medicamento m order by m.nome", Usuario.class)
        return query.getResultList();
    }
    
}
