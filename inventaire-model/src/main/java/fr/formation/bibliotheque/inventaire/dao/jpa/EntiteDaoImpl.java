/**
 * 
 */
package fr.formation.bibliotheque.inventaire.dao.jpa;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import fr.formation.bibliotheque.inventaire.dao.EntiteDao;
import fr.formation.bibliotheque.inventaire.modele.Entite;

/**
 * @author Administrateur
 *
 */
public class EntiteDaoImpl<T extends Entite<I>,I > implements EntiteDao<T,I> {
	private EntityManager entityManager;
	private static Logger logger = Logger.getLogger(EntiteDaoImpl.class);

	/**
	 * 
	 */
	public EntiteDaoImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.formation.bibliotheque.inventaire.dao.EntiteDao#ajouter(java.lang.Object)
	 */
	@Override
	public void ajouter(T m) {
		logger.debug("sauver "+m);
		entityManager.getTransaction().begin();
		entityManager.persist( m);
		entityManager.getTransaction().commit();

	}

	/*
	 * (non-Javadoc)
	 * @see fr.formation.bibliotheque.inventaire.dao.EntiteDao#modifier(java.lang.Object)
	 */
	@Override
	public void modifier(T m) {
		logger.debug("modifier "+m);
		entityManager.getTransaction().begin();
		entityManager.merge( m);
		entityManager.getTransaction().commit();

	}

	/*
	 * (non-Javadoc)
	 * @see fr.formation.bibliotheque.inventaire.dao.EntiteDao#supprimer(long)
	 */
	@Override
	public void supprimer(Class<T> classe,I id) {
		logger.debug("supprimer media ayant id: "+id);
		T m = chercherParId(classe, id);
		entityManager.getTransaction().begin();
		entityManager.remove(m);
		entityManager.getTransaction().commit();

	}

	/* (non-Javadoc)
	 * @see fr.formation.bibliotheque.inventaire.dao.MediaDao#chercherParId(long)
	 */
	@Override
	public T chercherParId(Class<T> classe, I id) {
		logger.debug("chercher media ayant id "+id);		
		return (T) entityManager.find(classe , id);
	}

	/* (non-Javadoc)
	 * @see fr.formation.bibliotheque.inventaire.dao.MediaDao#lister(int, int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> lister(Class<T> classe, int indice, int nombre) {
		logger.debug("lister les media de "+indice+" nombre "+nombre);
		Query requeteJQL = entityManager.createQuery("select entite from "+  classe.getName()+" entite");
		requeteJQL.setFirstResult(indice);
		requeteJQL.setMaxResults(nombre);
		return requeteJQL.getResultList();
	}

}
