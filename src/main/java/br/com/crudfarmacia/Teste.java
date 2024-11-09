package br.com.crudfarmacia;

import javax.swing.JOptionPane;

import br.com.crudfarmacia.model.Medicamento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Teste {

    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("farmaciaPU");
    private static EntityManager entityManager = entityManagerFactory.createEntityManager();

    public static void main(String[] args) {
        try {
            entityManager.getTransaction().begin();
            Medicamento dipirona = entityManager.find(Medicamento.class, 1);

            if (dipirona != null) {
                System.out.println("Medicamento: " + dipirona.getNome());
            } else {
                System.out.println("Medicamento não encontrado.");
            }
            
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO: " + e.getMessage(), "ERRO", 0);
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
