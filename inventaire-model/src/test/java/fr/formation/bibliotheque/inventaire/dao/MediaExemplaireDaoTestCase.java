package fr.formation.bibliotheque.inventaire.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import fr.formation.bibliotheque.inventaire.modele.EtatExemplaire;
import fr.formation.bibliotheque.inventaire.modele.Exemplaire;
import fr.formation.bibliotheque.inventaire.modele.Livre;
import fr.formation.bibliotheque.inventaire.modele.Media;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MediaExemplaireDaoTestCase {
	private static Logger logger = Logger.getLogger(MediaExemplaireDaoTestCase.class);
	private static EntityManagerFactory factory;
	private static EntityManager entityManager;
	private static Exemplaire exemplaire;
	private static Media media;
	
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
	public final void test1Create() {
		
		try{
			logger.info("testCreate");
			logger.debug("creer une nouvelle instance de exemplaire");
			exemplaire = new Exemplaire("ref1",EtatExemplaire.Disponible);
			media = new Livre(0,"XML",new Date(),"Orsys","3434-343",230);
			logger.info("Sauver l'exemplaire");
			entityManager.getTransaction().begin();
			entityManager.persist(exemplaire);
			entityManager.persist(media);
			entityManager.getTransaction().commit();
			
		}catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
		
	@Test
	public final void test2Association() {
		
		try{
			logger.info("testAssociation");
			logger.info("Associer l'exemplaire au media");
			exemplaire.setMedia(media);
			entityManager.getTransaction().begin();
			entityManager.merge(exemplaire);
			
			entityManager.getTransaction().commit();
			
		}catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public final void test3VerifierAssociation() {
		try{
			logger.info("testVerifierAssociation");
			Exemplaire exemplaireExistant = entityManager.find(Exemplaire.class,"ref1");
			assertNotNull(exemplaireExistant);
			logger.debug("L'exemplaire :"+ exemplaireExistant); 
			assertNotNull(exemplaireExistant.getMedia());
			logger.debug("Media de l'exemplaire :"+ exemplaireExistant.getMedia());
			assertEquals(exemplaireExistant.getMedia(), media);
			
		}catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
			
		}
	}
	
}
