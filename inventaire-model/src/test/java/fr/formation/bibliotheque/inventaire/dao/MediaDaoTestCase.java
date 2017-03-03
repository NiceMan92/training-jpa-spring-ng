package fr.formation.bibliotheque.inventaire.dao;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import fr.formation.bibliotheque.inventaire.modele.Media;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MediaDaoTestCase {
	private static Logger logger = Logger.getLogger(MediaDaoTestCase.class);
	private static EntityManagerFactory factory;
	private static EntityManager entityManager;
	private static Media media1;
	private static Media media2;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		logger.info("Initialisation des ressources");
		logger.info("Initialiser l'unite de persistance");
		factory = Persistence.createEntityManagerFactory("bibliotheque_pu");
		entityManager = factory.createEntityManager();		
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		logger.info("Liberation des ressources");
		entityManager.close();
		entityManager = null;
		factory.close();
		factory = null;
	}

	
	
	
	@Test
	public final void testCreate() {
		
		try{
			logger.info("testCreate");
			logger.debug("creer une nouvelle instance de media");
			media1 = new Media(0,"Hibernate", new Date(),"Orsys");
			media2 = new Media(0,"Maven", new Date(),"Orsys");
			logger.info("Sauver les medias");
			entityManager.getTransaction().begin();
			entityManager.persist(media1);
			entityManager.persist(media2);
			entityManager.getTransaction().commit();
			
		}catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
			
		}
	}
	
	
	
	@Test
	public final void testMerge() {
		
		try{
			logger.info("testMerge");
			logger.info("modifier le media");
			media1.setTitre("Jee");
			entityManager.getTransaction().begin();
			entityManager.merge(media1);
			entityManager.getTransaction().commit();
			
		}catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
			
		}
	}
	
	@Test
	public final void testRead() {
		
		try{
			logger.info("testRead");
			logger.debug("récuperer un media existante");			
			Media mediaOld = entityManager.find(Media.class, 1L);
			logger.debug(mediaOld);
			assertEquals(media1,mediaOld);
			
		}catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
			
		}
	}	
	
	@SuppressWarnings("unchecked")
	@Test
	public final void testReadList() {		
		try{
			logger.info("testReadList");
			logger.debug("récuperer une liste de medias");
			//Utilisation du langage de requete JQL/JPA Query Language
			//Equivalent a l'SQL mais en modele objet
			Query requeteJQL = entityManager.createQuery("select m from Media m where m.titre like '%e%' ");
			List<Media> liste = requeteJQL.getResultList();
			assertNotNull(liste);
			logger.debug("Taille de liste :"+liste.size());
			assertEquals(2,liste.size());			
		}catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());			
		}
	}
	
	@Test
	public final void testRemove() {
		
		try{
			logger.info("testRemove");
			logger.info("Supprimer le media");
			entityManager.getTransaction().begin();
			entityManager.remove(media1);
			entityManager.getTransaction().commit();
			
		}catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
			
		}
	}
	

}
