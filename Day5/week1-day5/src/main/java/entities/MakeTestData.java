/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author willi
 */
public class MakeTestData {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        
        try {
            em.getTransaction().begin();
            em.persist(new BankCustomer("Lars", "Pedersen", "Threefiddy", 2000.0, 10, "Super rich"));
            em.persist(new BankCustomer("Kim", "Kost", "123", 165.0, 2, "Knap så rig"));
            em.persist(new BankCustomer("Brian", "Larsen", "666", 250.0, 3, "Medium rig"));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        
    }
    
}
