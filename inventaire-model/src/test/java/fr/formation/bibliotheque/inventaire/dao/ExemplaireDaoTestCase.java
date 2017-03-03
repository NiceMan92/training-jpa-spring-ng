package fr.formation.bibliotheque.inventaire.dao;

import static org.junit.Assert.*;

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

import fr.formation.bibliotheque.inventaire.modele.EtatExemplaire;
import fr.formation.bibliotheque.inventaire.modele.Exemplaire;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ExemplaireDaoTestCase {
	private static Logger logger = Logger.getLogger(ExemplaireDaoTestCase.class);
	private static EntityManagerFactory factory;
	private static EntityManager entityManager;
	private static Exemplaire exemplaire1;
	private static Exemplaire exemplaire2;
	
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
			logger.debug("creer une nouvelle instance de exemplaire");
			exemplaire1 = new Exemplaire("ref1",EtatExemplaire.Disponible);
			exemplaire2 =new Exemplaire("ref2",EtatExemplaire.Reserve);
			logger.info("Sauver l'exemplaire");
			entityManager.getTransaction().begin();
			entityManager.persist(exemplaire1);
			entityManager.persist(exemplaire2);
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
			logger.info("modifier l'exemplaire");
			exemplaire1.setEtat(EtatExemplaire.Reserve);
			entityManager.getTransaction().begin();
			entityManager.merge(exemplaire1);
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
			logger.debug("récuperer un exemplaire existant");			
			Exemplaire exemplaireOld = entityManager.find(Exemplaire.class, "ref1");
			logger.debug(exemplaireOld);
			assertEquals(exemplaire1,exemplaireOld);
			
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
			Query requeteJQL = entityManager.createQuery("select e from Exemplaire e where e.etat = :etat ");
			requeteJQL.setParameter("etat",EtatExemplaire.Reserve);
			List<Exemplaire> liste = requeteJQL.getResultList();
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
			logger.info("Supprimer la catégorie");
			entityManager.getTransaction().begin();
			entityManager.remove(exemplaire1);
			entityManager.getTransaction().commit();
			
		}catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
			
		}
	}
	

}
