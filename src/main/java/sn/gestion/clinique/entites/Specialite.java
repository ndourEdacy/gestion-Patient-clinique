package sn.gestion.clinique.entites;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;

import org.springframework.transaction.annotation.Transactional;

@Entity
@Transactional
public class Specialite  implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String libelle;
	@OneToMany(mappedBy="specialite",fetch=FetchType.EAGER)
	private Collection<Medecin> medecins;
	
	public Specialite() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Specialite(String libelle) {
		super();
		this.libelle = libelle;
	}
	
	public Collection<Medecin> getMedecins() {
		return medecins;
	}
	public void setMedecins(Collection<Medecin> medecins) {
		this.medecins = medecins;
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
	
}
