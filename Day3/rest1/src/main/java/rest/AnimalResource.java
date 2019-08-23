/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import entities.Animal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author willi
 */
@Path("animal")
public class AnimalResource {
    private List<Animal> animals = new ArrayList();
    {
    animals.add(new Animal("Duck", 2017, "Quack"));
    animals.add(new Animal("Tigress", 2008, "miaw"));
    animals.add(new Animal("Monkey", 2011, "UUUUUUAHAHAHAHAHAH"));
    animals.add(new Animal("Shark", 1961, "*BLOP*"));
    }

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AnimalResource
     */
    public AnimalResource() {
    }

    /**
     * Retrieves representation of an instance of rest.AnimalResource
     * @return an instance of java.lang.String
     */
    @GET
    //@Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        return "Hello from my first web service";
    }
    
    @GET
    @Path("/random")
    @Produces(MediaType.APPLICATION_JSON)
    public String getRandomAnimal() {
        Random random = new Random();
        Animal ranAnimal = animals.get(random.nextInt(animals.size()));
        return new Gson().toJson(ranAnimal);
    }

}
