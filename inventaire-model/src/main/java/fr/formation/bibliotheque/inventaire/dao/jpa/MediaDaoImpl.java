/**
 * 
 */
package fr.formation.bibliotheque.inventaire.dao.jpa;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import fr.formation.bibliotheque.inventaire.dao.MediaDao;
import fr.formation.bibliotheque.inventaire.modele.Media;

/**
 * @author Administrateur
 *
 */
public class MediaDaoImpl implements MediaDao {
	private EntityManager entityManager;
	private static Logger logger = Logger.getLogger(MediaDaoImpl.class);

	
	public MediaDaoImpl() {
	}
	
	/**
	 * 
	 */
	public MediaDaoImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/* (non-Javadoc)
	 * @see fr.formation.bibliotheque.inventaire.dao.MediaDao#ajouter(fr.formation.bibliotheque.inventaire.modele.Media)
	 */
	@Override
	public void ajouter(Media m) {
		logger.debug("sauver "+m);
		entityManager.getTransaction().begin();
		entityManager.persist( m);
		entityManager.getTransaction().commit();

	}

	/* (non-Javadoc)
	 * @see fr.formation.bibliotheque.inventaire.dao.MediaDao#modifier(fr.formation.bibliotheque.inventaire.modele.Media)
	 */
	@Override
	public void modifier(Media m) {
		logger.debug("modifier "+m);
		entityManager.getTransaction().begin();
		entityManager.merge( m);
		entityManager.getTransaction().commit();

	}

	/* (non-Javadoc)
	 * @see fr.formation.bibliotheque.inventaire.dao.MediaDao#supprimer(long)
	 */
	@Override
	public void supprimer(long id) {
		logger.debug("supprimer media ayant id: "+id);
		Media m = chercherParId(id);
		entityManager.getTransaction().begin();
		entityManager.remove(m);
		entityManager.getTransaction().commit();

	}

	/* (non-Javadoc)
	 * @see fr.formation.bibliotheque.inventaire.dao.MediaDao#chercherParId(long)
	 */
	@Override
	public Media chercherParId(long id) {
		logger.debug("chercher media ayant id "+id);
		return entityManager.find(Media.class, id);
	}

	/* (non-Javadoc)
	 * @see fr.formation.bibliotheque.inventaire.dao.MediaDao#lister(int, int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Media> lister(int indice, int nombre) {
		logger.debug("lister les media de "+indice+" nombre "+nombre);
		Query requeteJQL = entityManager.createQuery("select m from Media m ");
		requeteJQL.setFirstResult(indice);
		requeteJQL.setMaxResults(nombre);
		return requeteJQL.getResultList();
	}

	/**
	 * @return the entityManager
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * @param entityManager the entityManager to set
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	
	
}
