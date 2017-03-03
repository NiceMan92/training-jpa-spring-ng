package fr.formation.bibliotheque.inventaire.dao;

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

import fr.formation.bibliotheque.inventaire.dao.jpa.MediaDaoImpl;
import fr.formation.bibliotheque.inventaire.modele.Media;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MediaDaoTestCaseBis {
	private static Logger logger = Logger.getLogger(MediaDaoTestCaseBis.class);
	private static EntityManagerFactory factory;
	private static EntityManager entityManager;
	private static MediaDao mediaDao;
	private static Media media1;
	private static Media media2;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		logger.info("Initialisation des ressources");
		logger.info("Initialiser l'unite de persistance");
		factory = Persistence.createEntityManagerFactory("bibliotheque_pu");
		entityManager = factory.createEntityManager();
		//Injection lors de l'instanciation
		//mediaDao = new MediaDaoImpl(entityManager);
		//Injection par setter
		MediaDaoImpl mediaDaoImpl = new MediaDaoImpl();
		mediaDaoImpl.setEntityManager(entityManager);
		mediaDao = mediaDaoImpl;
		
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
			mediaDao.ajouter(media1);
			mediaDao.ajouter(media2);			
		}catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
			
		}
	}

}
