package fr.formation.bibliotheque.inventaire.dao.spring;

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
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import fr.formation.bibliotheque.inventaire.dao.MediaDao;
import fr.formation.bibliotheque.inventaire.modele.Exemplaire;
import fr.formation.bibliotheque.inventaire.modele.Media;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MediaDaoTestCase {
	private static Logger logger = Logger.getLogger(MediaDaoTestCase.class);
	private static Media media1;
	private static Media media2;
	private static Exemplaire exemplaire1;
	private static MediaDao mediaDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		logger.info("Initialisation des ressources");
		
		logger.debug("recuperer les medias à partir de Spring");
		//Déclaration du fichier XML spring : inventaire-beans.xml
		Resource inventaireBeans = new ClassPathResource("inventaire-beans-v2.xml");
		BeanFactory contextSpring = new  XmlBeanFactory(inventaireBeans);
		//media1 = new Media(0,"Hibernate", new Date(),"Orsys");
		media1 = (Media) contextSpring.getBean("media1");
		//media2 = new Media(0,"Maven", new Date(),"Orsys");
		media2 = (Media) contextSpring.getBean("media2");			
		exemplaire1 =  (Exemplaire) contextSpring.getBean("exemplaire1");		
		logger.info("Récupérer le dao mediaDao");		
		//entityManager = (EntityManager) contextSpring.getBean("entityManager");
		mediaDao = (MediaDao) contextSpring.getBean("mediaDao");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		logger.info("Liberation des ressources");	
	}	
	@Test
	public final void testCreate() {
		
		try{
			logger.info("testCreate");			
			logger.info(exemplaire1.getEtat().toString());
			assertEquals(media1,exemplaire1.getMedia());
			logger.info("Sauver les medias");			
			mediaDao.ajouter(media1);
			mediaDao.ajouter(media2);						
		}catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
			
		}
	}
	
	
	

}
