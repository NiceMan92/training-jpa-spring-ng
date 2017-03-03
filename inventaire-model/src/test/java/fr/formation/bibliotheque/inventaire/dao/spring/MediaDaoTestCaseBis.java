package fr.formation.bibliotheque.inventaire.dao.spring;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.formation.bibliotheque.inventaire.dao.MediaDao;
import fr.formation.bibliotheque.inventaire.modele.Exemplaire;
import fr.formation.bibliotheque.inventaire.modele.Media;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(locations = {"classpath:inventaire-beans-v2.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class MediaDaoTestCaseBis {
	private static Logger logger = Logger.getLogger(MediaDaoTestCaseBis.class);
	@Resource(name="media1")
	private  Media media1;
	@Resource(name="media2")
	private  Media media2;
	@Resource(name="exemplaire1")
	private  Exemplaire exemplaire1;
	@Resource(name="mediaDao")
	private  MediaDao mediaDao;
	
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
