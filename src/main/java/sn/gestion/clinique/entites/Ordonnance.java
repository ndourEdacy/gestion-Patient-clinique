package sn.gestion.clinique.entites;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;


@Entity
public class Ordonnance  implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String Date;
	private String medicament;
	@OneToMany(mappedBy="ordonnance")
	@ManyToOne
	@JoinColumn(name="idpatient")
	private Patient patient;
	
	
	
	public String getMedicament() {
		return medicament;
	}
	public void setMedicament(String medicament) {
		this.medicament = medicament;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public Ordonnance() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Ordonnance(String date) {
		super();
		Date = date;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	
}
