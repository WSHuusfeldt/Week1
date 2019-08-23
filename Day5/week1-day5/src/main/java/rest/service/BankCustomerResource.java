package rest.service;

import com.google.gson.Gson;
import dto.CustomerDTO;
import entities.BankCustomer;
import facades.Facade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("bankcustomer")
public class BankCustomerResource {

    EntityManagerFactory emf; 
    Facade fc =  Facade.getFacade(emf);

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"succes\"}";
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(BankCustomer entity) {
        throw new UnsupportedOperationException();
    }
    
    @GET
    @Path("/all")
    @Consumes({MediaType.APPLICATION_JSON})
    public String getAll(){
        emf = Persistence.createEntityManagerFactory("pu");
        Facade fc = Facade.getFacade(emf);
        List<BankCustomer> emp = fc.getAllBankCustomers();
        List<CustomerDTO> dto = new ArrayList();
        for(BankCustomer b : emp){
            dto.add(new CustomerDTO(b));
        }
        return new Gson().toJson(dto);
    }
    
    @GET
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public String getByID(@PathParam("id") int id) {
        emf = Persistence.createEntityManagerFactory("pu");
        Facade fc = Facade.getFacade(emf);
        CustomerDTO c = fc.getCustomerByID(id);
        List<CustomerDTO> dto = new ArrayList();
        dto.add(c);
        return new Gson().toJson(dto);
    }
    
    
}
