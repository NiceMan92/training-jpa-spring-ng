package fr.formation.bibliotheque.inventaire.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/hellors")
public class HelloRest {
	
	public HelloRest() {
		System.out.println("HelloREst - Instanciation");

	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)	
	public String sayHello(){
		return "Bonjour tout le monde";
	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)	
	public String sayHelloHTML(){
		return "<h1>Bonjour tout le monde comment ça va bien ? </h1>";
	}
	
	@GET
	@Path("/{prenom}")
	@Produces(MediaType.TEXT_HTML)	
	public String sayHelloPrenomHTML(@PathParam("prenom") String prenom){
		return "<h1>Bonjour </h1> "+ prenom;
	}
	
	@GET
	@Path("/format/json")
	@Produces(MediaType.APPLICATION_JSON)	
	public Pojo displayJSON(){		
		return new Pojo(1L, "Exemple");
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Pojo displayJsonPost(@FormParam("identifiant") Long id, @FormParam("nom") String name){
		return new Pojo(id, name);
	}
}
