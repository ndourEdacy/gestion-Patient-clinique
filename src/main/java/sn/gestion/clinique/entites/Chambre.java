package sn.gestion.clinique.entites;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;

@Entity
public class Chambre implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int numetage;
	private int nblit;
	private String etat;
	@OneToMany(mappedBy="chambre")
	private Collection<LigneVisiteChambre> ligneVisite;
	@OneToMany(mappedBy="chambre")
	
	public Collection<LigneVisiteChambre> getLigneViste() {
		return ligneVisite;
	}

	public void setLigneViste(Collection<LigneVisiteChambre> ligneViste) {
		this.ligneVisite = ligneViste;
	}

	public Chambre() {
		super();
	}
	
	public Chambre(int id, int numetage, int nblit, String etat) {
		super();
		this.id = id;
		this.numetage = numetage;
		this.nblit = nblit;
		this.etat = etat;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumetage() {
		return numetage;
	}
	public void setNumetage(int numetage) {
		this.numetage = numetage;
	}
	public int getNblit() {
		return nblit;
	}
	public void setNblit(int nblit) {
		this.nblit = nblit;
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	
	
}
