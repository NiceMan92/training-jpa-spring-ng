/**
 * 
 */
package fr.formation.bibliotheque.inventaire.modele;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Administrateur
 *
 */
@Entity
@Table(name="t_categorie")
public class Categorie {
	@Id
	//Strategie de génération de la clé : par défaut : l'app
	// Identity : gérée par la BdD : mysql
	//Sequence : gérée par la BdD : oracle
	//Auto : le framework
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ident")
	private long identifiant;
	@Column(name="libelle",unique=true,length=50,nullable=false)
	private String libelle;
	
	//Stratégies de chargement : lazy ou eager
	//Stratégie de cascade : Persist, Merge, Remove, Detach
	@OneToMany(fetch=FetchType.LAZY,cascade={CascadeType.REMOVE,CascadeType.PERSIST})
	@JoinColumn(name="id_categorie_parent")
	private List<Categorie> sousCategories;
	
	
	//Association ManyToMany
	@ManyToMany(cascade={CascadeType.PERSIST})
	@JoinTable(name="r_media_categorie", 
				joinColumns={@JoinColumn(name="categorie_id")},
				inverseJoinColumns={@JoinColumn(name="media_id")}
			)
	
	private List<Media> medias;
	
	
	public Categorie() {
		sousCategories = new ArrayList<Categorie>();
		medias = new ArrayList<Media>();
	}

	/**
	 * @param identifiant
	 * @param libelle
	 */
	public Categorie(long identifiant, String libelle) {
		this();
		this.identifiant = identifiant;
		this.libelle = libelle;
	}

	/**
	 * @return the identifiant
	 */
	public long getIdentifiant() {
		return identifiant;
	}

	/**
	 * @param identifiant the identifiant to set
	 */
	public void setIdentifiant(long identifiant) {
		this.identifiant = identifiant;
	}

	/**
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (identifiant ^ (identifiant >>> 32));
		result = prime * result + ((libelle == null) ? 0 : libelle.hashCode());
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
		if (!(obj instanceof Categorie))
			return false;
		Categorie other = (Categorie) obj;
		if (identifiant != other.identifiant)
			return false;
		if (libelle == null) {
			if (other.libelle != null)
				return false;
		} else if (!libelle.equals(other.libelle))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Categorie [identifiant=" + identifiant + ", " + (libelle != null ? "libelle=" + libelle : "") + "]";
	}

	/**
	 * @return the sousCategories
	 */
	public List<Categorie> getSousCategories() {
		return sousCategories;
	}

	/**
	 * @param sousCategories the sousCategories to set
	 */
	public void setSousCategories(List<Categorie> sousCategories) {
		this.sousCategories = sousCategories;
	}

	/**
	 * @return the medias
	 */
	public List<Media> getMedias() {
		return medias;
	}

	/**
	 * @param medias the medias to set
	 */
	public void setMedias(List<Media> medias) {
		this.medias = medias;
	}
	
	
	public void addSousCategorie(Categorie categorie){
		if(! this.sousCategories.contains(categorie)){
			this.sousCategories.add(categorie);
		}
	}
	
	public void removeSousCategorie(Categorie categorie){
		if( this.sousCategories.contains(categorie)){
			this.sousCategories.remove(categorie);
		}
	}
	
	public void addMedia(Media media){
		if(! this.medias.contains(media)){
			this.medias.add(media);
		}
	}
	public void removeMedia(Media media){
		if( this.medias.contains(media)){
			this.medias.remove(media);
		}
	}


	

}
