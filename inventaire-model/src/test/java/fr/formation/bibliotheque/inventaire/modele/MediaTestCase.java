package fr.formation.bibliotheque.inventaire.modele;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MediaTestCase {
	private static Logger logger = Logger.getLogger(MediaTestCase.class);
	private Media media1;
	private Media media2;

	@Before
	public void setUp() throws Exception {
		//System.out.println("Initialisation de jeu de test");
		logger.info("Initialisation de jeu de test");
		logger.debug("Instanciation de media1");
		media1 = new Livre(1, "Junit", null, "Orsys");
		logger.debug("Instanciation de media2");
		media2 = new Livre(2, "Maven", null, "Orsys");
	}

	@After
	public void tearDown() throws Exception {
		logger.info("Libération de jeu de test");
		media1 = null;
		media2 = null;
	}

	@Test
	public final void testEqualsObject() {
		logger.info("Test testEqualsObject");		
		assertNotNull(media1);
		assertNotNull(media2);
		assertNotEquals(media1, media2);
	}

}
