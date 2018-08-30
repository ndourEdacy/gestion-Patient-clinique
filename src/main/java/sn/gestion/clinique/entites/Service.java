package sn.gestion.clinique.entites;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;
@Entity
public class Service {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String libelle;
	@OneToMany(mappedBy="service")
	private Collection<Secretaire> secretaires;
	@OneToMany(mappedBy="service")
	private Collection<Infirmier> infirmiers;
	
	public Service() {
		// TODO Auto-generated constructor stub
	}
	public Service(int numero, String libelle) {
		super();
		this.id = numero;
		this.libelle = libelle;
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
