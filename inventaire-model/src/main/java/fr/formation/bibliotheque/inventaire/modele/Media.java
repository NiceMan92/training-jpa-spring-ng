/**
 * 
 */
package fr.formation.bibliotheque.inventaire.modele;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * Les médias de la bibliothèque
 * 
 * @author Administrateur
 *
 */
@Entity
@Table(name="t_media")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type",discriminatorType=DiscriminatorType.CHAR)
@DiscriminatorValue("M")
public class Media {
	//Déclaration des attributs
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ident")
	private long identifiant;
	@Column(unique=true,length=50,nullable=false)
	private String titre;
	private Date datePublication;
	@Column(length=50, nullable=true)
	private String auteur;
	//Déclaration des méthodes
	//Constructeurs
	
	public Media() {
	}

	/**
	 * @param identifiant
	 * @param titre
	 * @param datePublication
	 * @param auteur
	 */
	public Media(long identifiant, String titre, Date datePublication, String auteur) {
		this.identifiant = identifiant;
		this.titre = titre;
		this.datePublication = datePublication;
		this.auteur = auteur;
	}
	//les accesseurs : getters, setters

	/**
	 * @return the identifiant
	 */
	public Long getIdentifiant() {
		return identifiant;
	}

	/**
	 * @param identifiant the identifiant to set
	 */
	public void setIdentifiant(Long identifiant) {
		this.identifiant = identifiant;
	}

	/**
	 * @return the titre
	 */
	public String getTitre() {
		return titre;
	}

	/**
	 * @param titre the titre to set
	 */
	public void setTitre(String titre) {
		this.titre = titre;
	}

	/**
	 * @return the datePublication
	 */
	public Date getDatePublication() {
		return datePublication;
	}

	/**
	 * @param datePublication the datePublication to set
	 */
	public void setDatePublication(Date datePublication) {
		this.datePublication = datePublication;
	}

	/**
	 * @return the auteur
	 */
	public String getAuteur() {
		return auteur;
	}

	/**
	 * @param auteur the auteur to set
	 */
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (identifiant ^ (identifiant >>> 32));
		result = prime * result + ((titre == null) ? 0 : titre.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Media))
			return false;
		Media other = (Media) obj;
		if (identifiant != other.identifiant)
			return false;
		if (titre == null) {
			if (other.titre != null)
				return false;
		} else if (!titre.equals(other.titre))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Media [identifiant=" + identifiant + ", " + (titre != null ? "titre=" + titre : "") + "]";
	}	
	
	
	

}
