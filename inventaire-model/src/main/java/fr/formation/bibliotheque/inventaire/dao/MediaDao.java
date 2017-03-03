/**
 * 
 */
package fr.formation.bibliotheque.inventaire.dao;

import java.util.List;

import fr.formation.bibliotheque.inventaire.modele.Media;

/**
 * @author Administrateur
 *
 */
public interface MediaDao {	
	void ajouter(Media m);
	void modifier(Media m);
	void supprimer(long id);
	Media chercherParId(long id);
	List<Media> lister(int indice, int nombre);
}
