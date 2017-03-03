package fr.formation.bibliotheque.inventaire.modele;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LivreTestCase {
	private Media media1;
	private Media media2;

	@Before
	public void setUp() throws Exception {
		System.out.println("Initialisation de jeu de test");
		media1 = new Media(1, "Junit", null, "Orsys");
		media2 = new Media(1, "Junit", null, "Orsys");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("Libération de jeu de test");
		media1 = null;
		media2 = null;
	}

	@Test
	public final void testEqualsObject() {
		System.out.println("Test testEqualsObject");		
		assertNotNull(media1);
		assertNotNull(media2);
		assertNotEquals(media1, media2);
	}

}
