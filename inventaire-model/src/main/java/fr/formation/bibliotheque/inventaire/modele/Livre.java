/**
 * 
 */
package fr.formation.bibliotheque.inventaire.modele;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author Administrateur
 *
 */
@Entity
@DiscriminatorValue("L")
public class Livre extends Media {
	private String ISBN;
	private int nombrePages;

	/**
	 * 
	 */
	public Livre() {
	}

	/**
	 * @param identifiant
	 * @param titre
	 * @param datePublication
	 * @param auteur
	 */
	public Livre(long identifiant, String titre, Date datePublication, String auteur) {
		super(identifiant, titre, datePublication, auteur);
	}
	
	/**
	 * @param identifiant
	 * @param titre
	 * @param datePublication
	 * @param auteur
	 * @param iSBN
	 * @param nombrePages
	 */
	public Livre(long identifiant, String titre, Date datePublication, String auteur, String iSBN, int nombrePages) {
		super(identifiant, titre, datePublication, auteur);
		ISBN = iSBN;
		this.nombrePages = nombrePages;
	}

	/**
	 * @return the iSBN
	 */
	public String getISBN() {
		return ISBN;
	}

	/**
	 * @param iSBN the iSBN to set
	 */
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	/**
	 * @return the nombrePages
	 */
	public int getNombrePages() {
		return nombrePages;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Livre [" + (ISBN != null ? "ISBN=" + ISBN + ", " : "") + "identifiant=" + getIdentifiant() + ", "
				+ (getTitre() != null ? "titre=" + getTitre() : "") + "]";
	}

	/**
	 * @param nombrePages the nombrePages to set
	 */
	public void setNombrePages(int nombrePages) {
		this.nombrePages = nombrePages;
	}
	
	
	

}
