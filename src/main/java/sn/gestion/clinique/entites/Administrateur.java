package sn.gestion.clinique.entites;

import java.io.Serializable;

import javax.persistence.*;


@Entity
public class Administrateur extends Users implements Serializable{
	private String nom;
	private String prenom;
	private String adresse;
	private String datenais;
	private String lieu;
	private String sexe;
	private String tel;
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
	
	public Administrateur() {
		super();
		super.setTitre("Administrateur");
	}
	public Administrateur(String nom, String prenom, String adresse,
			String datenais, String lieu, String sexe, String tel,String login,String password) {
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.datenais = datenais;
		this.lieu = lieu;
		this.sexe = sexe;
		this.tel = tel;
	}
	
}
