package br.com.crudfarmacia.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EMfactory {
    private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("farmaciaPU");
    private static EntityManager manager;

    public static EntityManager getEntityManager(){
        if(manager == null) {
            manager = factory.createEntityManager();
        }
        return manager;
    }

}