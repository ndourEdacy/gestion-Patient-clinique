package sn.gestion.clinique.entites;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;

import org.springframework.transaction.annotation.Transactional;


@Entity
@Transactional
public class Medecin  extends Users implements Serializable{
	private String nom;
	private String prenom;
	private String adresse;
	private String datenais;
	private String lieu;
	private String sexe;
	private String tel;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "idspecialite")
	private Specialite specialite;
	@OneToMany(mappedBy = "medecin",fetch=FetchType.EAGER)
	private Collection<Patient> patient;

	public Collection<Patient> getPatient() {
		return patient;
	}

	public void setPatient(Collection<Patient> patient) {
		this.patient = patient;
	}
	@OneToMany(mappedBy = "medecin")
	private Collection<Visite> visite;
	@OneToMany(mappedBy="medecin")
	private Collection<Certificat> certificats;
	@OneToMany(mappedBy="medecin")
	private Collection<Bilan> bilans;
	
	
	public Collection<Bilan> getBilans() {
		return bilans;
	}

	public void setBilans(Collection<Bilan> bilans) {
		this.bilans = bilans;
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

	public Collection<Visite> getVisite() {
		return visite;
	}

	public void setVisite(Collection<Visite> visite) {
		this.visite = visite;
	}

	public Medecin() {
		super();
		super.setTitre("Medecin");
	}

	

	public Medecin(String nom, String prenom, String adresse, String datenais,
			String lieu, String sexe, String tel,
			String login,String password) {
		super(login,password);
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.datenais = datenais;
		this.lieu = lieu;
		this.sexe = sexe;
		this.tel = tel;
	}

	public Specialite getSpecialite() {
		return specialite;
	}

	public void setSpecialite(Specialite specialite) {
		this.specialite = specialite;
	}

	

	public Collection<Certificat> getCertificats() {
		return certificats;
	}

	public void setCertificats(Collection<Certificat> certificats) {
		this.certificats = certificats;
	}
}
