package facades;

import dto.CustomerDTO;
import entities.BankCustomer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class Facade {

    private static Facade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private Facade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static Facade getFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new Facade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public CustomerDTO getCustomerByID(long id) {
        EntityManager em = emf.createEntityManager();
        try {
           return new CustomerDTO(em.find(BankCustomer.class, id));
        } finally {
            em.close();
        }
    }
    
    public List<CustomerDTO> getCustomerByName(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<CustomerDTO> query = 
                    em.createQuery("SELECT c FROM CustomerDTO c WHERE c.fullName = :name",CustomerDTO.class).setParameter("name", name);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public BankCustomer addCustomer(BankCustomer cust) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(cust);
            em.getTransaction().commit();
            return cust;
        } finally {
            em.close();
        }
    }
    
    public List<BankCustomer> getAllBankCustomers() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery query =
                    em.createQuery("SELECT b FROM BankCustomer b", BankCustomer.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public static void main(String[] args) {
        Facade f = new Facade();
        System.out.println(f.getAllBankCustomers());
    }
}
