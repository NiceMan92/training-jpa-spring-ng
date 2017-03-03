package fr.formation.bibliotheque.inventaire.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

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

import fr.formation.bibliotheque.inventaire.modele.Categorie;
import fr.formation.bibliotheque.inventaire.modele.Media;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CategorieDaoTestCase {
	private static Logger logger = Logger.getLogger(CategorieDaoTestCase.class);
	private static EntityManagerFactory factory;
	private static EntityManager entityManager;
	private static Categorie categorie1;
	private static Categorie categorie2;
	
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
			logger.debug("creer une nouvelle instance de categorie");
			categorie1 = new Categorie(0, "Sciences");
			categorie2 = new Categorie(0, "Artistes");
			logger.info("ajouter un media à la catégorie 1");
			Media media = new Media(0,"XML",null,null);
			//categorie1.getMedias().add(media);
			categorie1.addMedia(media);
			logger.info("Sauver la catégorie");
			entityManager.getTransaction().begin();
			entityManager.persist(categorie1);
			entityManager.persist(categorie2);
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
			logger.info("modifier la catégorie");
			categorie1.setLibelle("Informatique");
			entityManager.getTransaction().begin();
			entityManager.merge(categorie1);
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
			categorie1 = new Categorie(1, "Informatique");
			logger.debug("récuperer une catégorie existante");			
			Categorie categorieOld = entityManager.find(Categorie.class, 1L);
			logger.debug(categorieOld);
			assertEquals(categorie1,categorieOld);
			logger.info("<<< lazy loading");
			logger.info("Liste des medias :" + categorieOld.getMedias().size());
			for(Media m : categorieOld.getMedias()){
				logger.info(m);
			}
			
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
			logger.debug("récuperer une liste de catégories");
			//Utilisation du langage de requete JQL/JPA Query Language
			//Equivalent a l'SQL mais en modele objet
			Query requeteJQL = entityManager.createQuery(" select c from Categorie c where c.libelle like '%e%' ");
			List<Categorie> liste = requeteJQL.getResultList();
			assertNotNull(liste);
			logger.debug("Taille de liste :"+liste.size());
			assertEquals(2,liste.size());			
		}catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());			
		}
	}
	
	
	@Test
	public final void testLinkCategories() {		
		try{
			
			logger.info("testLinkCategories");
			logger.debug("Ajouter la categorie2 en tant que sous catégorie de la categorie1");
			
			//categorie1.getSousCategories().add(categorie2);
			categorie1.addSousCategorie(categorie2);
			entityManager.getTransaction().begin();
			entityManager.merge(categorie1);
			entityManager.getTransaction().commit();
			
		}catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());			
		}
	}
	
	
	@Test
	public final void testLinkVerify() {		
		try{
			
			logger.info("testLinkVerify");
			logger.debug("verifier l'association");
			Categorie categorieExistante = entityManager.find(Categorie.class, 1L);
			logger.info(categorieExistante);
			assertNotNull(categorieExistante);
			logger.info(">>> chargement tardif des sous categories");			
			assertNotNull(categorieExistante.getSousCategories());			
			assertEquals(1, categorieExistante.getSousCategories().size());
			logger.info("<<< chargement tardif des sous categories");
			
			
		}catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());			
		}
	}
	
	
	//@Test
	public final void testRemove() {
		
		try{
			logger.info("testRemove");
			logger.info("Supprimer la catégorie");
			entityManager.getTransaction().begin();
			entityManager.remove(categorie1);
			entityManager.getTransaction().commit();
			
		}catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
			
		}
	}
	

}
