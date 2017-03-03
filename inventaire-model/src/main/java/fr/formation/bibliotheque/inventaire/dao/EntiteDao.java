/**
 * 
 */
package fr.formation.bibliotheque.inventaire.dao;

import java.util.List;

import fr.formation.bibliotheque.inventaire.modele.Entite;

/**
 * @author Administrateur
 *
 */
public interface EntiteDao<T extends Entite<I>,I > {	
	void ajouter(T entite);
	void modifier(T entite);
	void supprimer(Class<T> classe,I id);
	T chercherParId(Class<T> classe,I id);
	List<T> lister(Class<T> classe,int indice, int nombre);
}
