/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Employee;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
            em.persist(new Employee("Asger", "Ny Ellebjerg", 250));
            em.persist(new Employee("Andreas", "Skovlunde", 100));
            em.persist(new Employee("Martin", "Hashvangen", 420));
            em.persist(new Employee("William", "Vesterbro", 200));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    

    /**
     * Test of getEmployeeById method, of class Facade.
     */
    @Test
    public void testGetEmployeeById() {
        System.out.println("getEmployeeById");
        Employee result = f.getEmployeeById(1L);
        assertNotNull(result);
        assertEquals("Andreas", result.getName());
    }

    /**
     * Test of getEmployeeByName method, of class Facade.
     */
    @Test
    public void testGetEmployeeByName() {
        System.out.println("getEmployeeByName");
        List<Employee> result = f.getEmployeeByName("Martin");
        assertNotNull(result);
        assertEquals("Martin", result.get(0).getName());
    }

    /**
     * Test of getAllEmployees method, of class Facade.
     */
    @Test
    public void testGetAllEmployees() {
        System.out.println("getAllEmployees");
        List<Employee> result = f.getAllEmployees();
        assertNotNull(result);
        assertEquals(4, result.size());
    }

    /**
     * Test of getEmployeesWithHighestSalary method, of class Facade.
     */
    @Test
    public void testGetEmployeesWithHighestSalary() {
        System.out.println("getEmployeesWithHighestSalary");
        List<Employee> result = f.getEmployeesWithHighestSalary();
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    /**
     * Test of createEmployee method, of class Facade.
     */
    @Test
    public void testCreateEmployee() {
        System.out.println("createEmployee");
        String name = "test";
        String address = "test street";
        int salary = 300;
        Employee result = f.createEmployee(name, address, salary);
        assertEquals("test", result.getName());
        EntityManager em1 = emf.createEntityManager();
        try {
            em1.getTransaction().begin();
            em1.remove(em1.find(Employee.class, 5L));
            em1.getTransaction().commit();
        } finally {
            em1.close();
        }
    }
    
}
