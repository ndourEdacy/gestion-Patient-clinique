package sn.gestion.clinique.entites;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Antecedant implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id; 
	private String date;
	private String description;
	@ManyToOne
	@JoinColumn(name="idpatient")
	private Patient patient;
	
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public Antecedant() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Antecedant(String date, String datefin, String description) {
		super();
		this.date = date;
		this.description = description;
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
	public void setDate(String datedebut) {
		this.date = datedebut;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
