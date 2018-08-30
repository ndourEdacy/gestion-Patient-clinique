package sn.gestion.clinique.entites;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Paiement implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@OneToOne
	@JoinColumn(name="idvisite")
	private Visite visite;
	private Double montant;
	private String observation;
	private String typePaiement;
	private String date;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Visite getVisite() {
		return visite;
	}
	public void setVisite(Visite visite) {
		this.visite = visite;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Paiement() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Paiement(Double montant, String typePaiement) {
		super();
		this.montant = montant;
		this.typePaiement = typePaiement;
	}
	public Double getMontant() {
		return montant;
	}
	public void setMontant(Double montant) {
		this.montant = montant;
	}
	public String getObservation() {
		return observation;
	}
	public void setObservation(String observation) {
		this.observation = observation;
	}
	public String getTypePaiement() {
		return typePaiement;
	}
	public void setTypePaiement(String typePaiement) {
		this.typePaiement = typePaiement;
	}
	
	
}
