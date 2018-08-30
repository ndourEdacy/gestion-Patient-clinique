package sn.gestion.clinique.entites;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;


@Entity
public class Visite implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String motif;
	private String date;
	@ManyToOne
	@JoinColumn(name="idmedecin")
	private Medecin medecin;
	@ManyToOne
	@JoinColumn(name="idpatient")
	private Patient patient;
	@OneToMany(mappedBy="visite")
	private Collection<LigneVisiteChambre> ligneVisite;
	private Double recette;
	private Double paiement;
	private String type;
	
	public Double getPaiement() {
		return paiement;
	}

	public void setPaiement(Double paiement) {
		this.paiement = paiement;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Medecin getMedecin() {
		return medecin;
	}

	public void setMedecin(Medecin medecin) {
		this.medecin = medecin;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Double getRecette() {
		return recette;
	}

	public void setRecette(Double recette) {
		this.recette = recette;
	}

	public Collection<LigneVisiteChambre> getLigneVisite() {
		return ligneVisite;
	}

	public void setLigneVisite(Collection<LigneVisiteChambre> ligneVisite) {
		this.ligneVisite = ligneVisite;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Visite() {
		super();
		recette=0.0;
		paiement=0.0;
	}
	public Visite(String motif, String date) {
		super();
		this.motif = motif;
		this.date = date;
	}
	public String getMotif() {
		return motif;
	}
	public void setMotif(String motif) {
		this.motif = motif;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}