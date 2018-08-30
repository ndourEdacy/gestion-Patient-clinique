package sn.gestion.clinique.entites;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class LigneVisiteChambre implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String date;
	private String heure;
	@ManyToOne
	@JoinColumn(name="idchambre")
	private Chambre chambre;
	@ManyToOne
	@JoinColumn(name="idvisite")
	private Visite visite;
	
	public LigneVisiteChambre() {
		super();
	}
	public LigneVisiteChambre(String date, String heure) {
		super();
		this.date = date;
		this.heure = heure;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getHeure() {
		return heure;
	}
	public void setHeure(String heure) {
		this.heure = heure;
	}
	
}
