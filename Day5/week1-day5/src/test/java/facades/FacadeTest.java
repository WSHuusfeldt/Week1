/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dto.CustomerDTO;
import entities.BankCustomer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author willi
 */
public class FacadeTest {
    
    private static EntityManagerFactory emf;
    private static Facade f;
    
    public FacadeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        emf = Persistence.createEntityManagerFactory("putest");
        EntityManager em = emf.createEntityManager();
        f = Facade.getFacade(emf);
        
        try {
            em.getTransaction().begin();
            em.persist(new BankCustomer("Test", "A", "123", 200.0, 10, "Testing A"));
            em.persist(new BankCustomer("Tester", "B", "1234", 300.0, 20, "Testing B"));
            em.persist(new BankCustomer("Testers", "C", "12345", 1250.0, 20, "Testing C"));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    /**
     * Test of getCustomerByID method, of class Facade.
     */
    @Test
    public void testGetCustomerByID() {
        System.out.println("getCustomerByID");
        CustomerDTO result = f.getCustomerByID(1);
        assertNotNull(result);
        assertEquals("Testers C", result.getFullName());
    }

    /**
     * Test of getCustomerByName method, of class Facade.
     */
//    @Test
//    public void testGetCustomerByName() {
//        System.out.println("getCustomerByName");
//        List<CustomerDTO> result = f.getCustomerByName("Tester B");
//        assertNotNull(result);
//        assertEquals("Tester A", result.get(0).getFullName());
//    }

    /**
     * Test of addCustomer method, of class Facade.
     */
    @Test
    public void testAddCustomer() {
        System.out.println("addCustomer");
        BankCustomer cust = new BankCustomer("testingTrue", "b", "c", 10.0, 10, "new");
        BankCustomer result = f.addCustomer(cust);
        assertEquals("testingTrue", result.getFirstName());
        EntityManager em1 = emf.createEntityManager();
        try {
            em1.getTransaction().begin();
            em1.remove(em1.find(BankCustomer.class, 4L));
            em1.getTransaction().commit();
        } finally {
            em1.close();
        }
    
    }

    /**
     * Test of getAllBankCustomers method, of class Facade.
     */
    @Test
    public void testGetAllBankCustomers() {
        System.out.println("getAllBankCustomers");
        List<BankCustomer> result = f.getAllBankCustomers();
        assertEquals(3, result.size());
    }
    
}
