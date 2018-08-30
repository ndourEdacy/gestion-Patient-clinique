package sn.gestion.clinique.entites;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.ws.BindingType;


@Entity
public class Bilan implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String libelle;
	private String date;
	@ManyToOne
	@JoinColumn(name="idmedecin")
	private Medecin medecin;
	@ManyToOne
	@JoinColumn(name="idpatient")
	private Patient patient;
	private String description;
	
	public Bilan( String libelle, String date, Medecin medecin,
			Patient patient, String description) {
		super();
		this.libelle = libelle;
		this.date = date;
		this.medecin = medecin;
		this.patient = patient;
		this.description = description;
	}
	public Bilan() {
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
