package sn.gestion.clinique.entites;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;

@Entity
public class Infirmier extends Users implements Serializable{
	private String nom;
	private String prenom;
	private String adresse;
	private String datenais;
	private String lieu;
	private String sexe;
	private String tel;

	@ManyToOne
	@JoinColumn(name="idservice")
	private Service service;
	
	public Infirmier() {
		super();
		super.setTitre("Infirmier");
	}
	
	
	public Infirmier(String username, String password, int actived, String nom,
			String prenom, String adresse, String datenais, String lieu,
			String sexe, String tel) {
		super(username, password);
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.datenais = datenais;
		this.lieu = lieu;
		this.sexe = sexe;
		this.tel = tel;
	}


	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getDatenais() {
		return datenais;
	}
	public void setDatenais(String datenais) {
		this.datenais = datenais;
	}
	public String getLieu() {
		return lieu;
	}
	public void setLieu(String lieu) {
		this.lieu = lieu;
	}
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Service getService() {
		return service;
	}
	public void setService(Service service) {
		this.service = service;
	}
}
