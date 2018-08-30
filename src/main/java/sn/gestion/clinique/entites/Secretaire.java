package sn.gestion.clinique.entites;

import java.io.Serializable;
import java.util.Collection;

import javax.jws.soap.SOAPBinding.Style;
import javax.persistence.*;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.transaction.annotation.Transactional;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@Transactional
public class Secretaire extends Users{
	private String nom;
	private String prenom;
	private String adresse;
	private String datenais;
	private String lieu;
	private String sexe;
	private String tel;
	@OneToMany(mappedBy = "secretaire",fetch=FetchType.EAGER)
	private Collection<Patient> patient;
	@ManyToOne
	@JoinColumn(name="idservice")
	private Service service;
	@OneToMany(mappedBy = "secretaire")
	private Collection<Rdv> rdvs;
	
	@Transactional
	public Collection<Patient> getPatient() {
		return patient;
	}
	public void setPatient(Collection<Patient> patient) {
		this.patient = patient;
	}
	
	@Transactional
	public Collection<Rdv> getRdvs() {
		return rdvs;
	}
	public void setRdvs(Collection<Rdv> rdvs) {
		this.rdvs = rdvs;
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
	
	public Secretaire() {
		super();
		super.setTitre("Secretaire");
	}
	public Secretaire(String nom, String prenom, String adresse,
			String datenais, String lieu, String sexe, String tel,
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
	
}
