package fr.formation.bibliotheque.inventaire.modele;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *  Exemplaire d'un média
 *  
 * @author Administrateur
 *
 */
@Entity
@Table(name="t_exemplaire")
public class Exemplaire {
	@Id
	private String reference;
	
	@Enumerated(EnumType.STRING)
	private EtatExemplaire etat;
	
	@ManyToOne
	@JoinColumn(name="media_ident")
	private Media media;
	
	public Exemplaire() {
		this.etat = EtatExemplaire.Disponible;
	}

	/**
	 * @param reference
	 * @param etat
	 */
	public Exemplaire(String reference, EtatExemplaire etat) {
		super();
		this.reference = reference;
		this.etat = etat;
	}
	
	

	/**
	 * @param reference
	 * @param etat
	 * @param media
	 */
	public Exemplaire(String reference, EtatExemplaire etat, Media media) {
		super();
		this.reference = reference;
		this.etat = etat;
		this.media = media;
	}

	/**
	 * @return the reference
	 */
	public String getReference() {
		return reference;
	}

	/**
	 * @param reference the reference to set
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}

	/**
	 * @return the etat
	 */
	public EtatExemplaire getEtat() {
		return etat;
	}

	/**
	 * @param etat the etat to set
	 */
	public void setEtat(EtatExemplaire etat) {
		this.etat = etat;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Exemplaire [" + (reference != null ? "reference=" + reference + ", " : "")
				+ (etat != null ? "etat=" + etat : "") + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((etat == null) ? 0 : etat.hashCode());
		result = prime * result + ((reference == null) ? 0 : reference.hashCode());
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
		if (!(obj instanceof Exemplaire))
			return false;
		Exemplaire other = (Exemplaire) obj;
		if (etat != other.etat)
			return false;
		if (reference == null) {
			if (other.reference != null)
				return false;
		} else if (!reference.equals(other.reference))
			return false;
		return true;
	}

	/**
	 * @return the media
	 */
	public Media getMedia() {
		return media;
	}

	/**
	 * @param media the media to set
	 */
	public void setMedia(Media media) {
		this.media = media;
	}

	

}
