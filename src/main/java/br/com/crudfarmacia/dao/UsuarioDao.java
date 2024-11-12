package br.com.crudfarmacia.dao;

import java.util.List;

import javax.swing.text.html.parser.Entity;

import org.hibernate.query.TypedParameterValue;

import br.com.crudfarmacia.model.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.var;

public class UsuarioDao{
    private final EntityManager manager;

    public UsuarioDao(EntityManager manager){
        this.manager = manager;
    }

    public void inserir(Usuario objeto) throws Exception {
        this.manager.persist(objeto);
        this.manager.getTransaction().begin();
        this.manager.getTransaction().commit();
    }

    public void alterar(Usuario objeto) throws Exception {

        objeto = this.buscar(objeto.getCpf());
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

    
    public Usuario buscar(String cpf) throws Exception {
        return this.manager.find(Usuario.class, cpf);
    }

    
    public void excluir(String cpf) throws Exception {
        Usuario user = buscar(cpf);
        this.manager.remove(user);
        this.manager.getTransaction().begin();
        this.manager.getTransaction().commit();
    }


    public List<Usuario> listar() throws Exception {
        this.manager.getTransaction().begin();
        TypedQuery<Usuario> query = this.manager.createQuery("select m from Usuario m order by m.cpf", Usuario.class);
        this.manager.getTransaction().commit();
        return query.getResultList();
    }
    
}
