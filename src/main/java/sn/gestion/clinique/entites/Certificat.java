package sn.gestion.clinique.entites;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Certificat implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String date;
	private String datedebut;
	private int nbjour;
	@ManyToOne
	@JoinColumn(name="idmedecin")
	private Medecin medecin;
	@ManyToOne
	@JoinColumn(name="idpatient")
	private Patient patient;
	 
	public Certificat() {
		super();
	}
	public Certificat(String date, String datedebut, int nbjour) {
		super();
		this.date = date;
		this.datedebut = datedebut;
		this.nbjour = nbjour;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDatedebut() {
		return datedebut;
	}
	public void setDatedebut(String datedebut) {
		this.datedebut = datedebut;
	}
	
	public int getNbjour() {
		return nbjour;
	}
	public void setNbjour(int nbjour) {
		this.nbjour = nbjour;
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
	
}
