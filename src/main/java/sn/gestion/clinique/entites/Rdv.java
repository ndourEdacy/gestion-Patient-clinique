package sn.gestion.clinique.entites;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.springframework.transaction.annotation.Transactional;

@Entity
@Transactional
public class Rdv {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String start;
	private String end;
	private String title;
	@ManyToOne
	@JoinColumn(name="idsecretaire")
	private Secretaire secretaire;
	@ManyToOne
	@JoinColumn(name="idpatient")
	private Patient patient;
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Secretaire getSecretaire() {
		return secretaire;
	}
	public void setSecretaire(Secretaire secretaire) {
		this.secretaire = secretaire;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Rdv(String start, String end, String title) {
		super();
		this.start = start;
		this.end = end;
		this.title = title;
	}
	public Rdv() {
		super();
	}
	
}
