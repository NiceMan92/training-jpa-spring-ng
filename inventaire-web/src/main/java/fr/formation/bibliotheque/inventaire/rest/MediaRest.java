package fr.formation.bibliotheque.inventaire.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.formation.bibliotheque.inventaire.dao.MediaDao;
import fr.formation.bibliotheque.inventaire.modele.Media;

@Path("/mediars")
public class MediaRest {
	
	private MediaDao mediaDao;
	private static Logger logger= Logger.getLogger(MediaRest.class); 
	
	public MediaRest() {
		logger.info("instanciation");
		logger.info("initialisation de mediaDao à partir de spring");
		ApplicationContext contextSpring = new  ClassPathXmlApplicationContext("classpath:inventaire-beans-v2.xml");
		mediaDao = (MediaDao) contextSpring.getBean("mediaDao");
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Media> listerMedias(){
		logger.info("lister les medias");
		return mediaDao.lister(0, 10);
	}
	
	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Media listerMedia(@PathParam("id")Long id){
		logger.info("listerle media "+id);
		return mediaDao.chercherParId(id);
	}
	
	
	@Path("/{id}")
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	public String supprimerMedia(@PathParam("id")Long id){
		logger.info("listerle media "+id);
		 mediaDao.supprimer(id);
		 return "OK";
	}
	
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String ajouterMedia(Media media){
		logger.info("ajouter un media "+media);
		mediaDao.ajouter(media);
		return "OK";
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String moodifierMedia(Media media){
		logger.info("ajouter un media "+media);
		mediaDao.ajouter(media);
		return "OK";
	}

}
