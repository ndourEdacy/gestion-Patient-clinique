package sn.gestion.clinique.entites;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;

import org.springframework.transaction.annotation.Transactional;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@Transactional
public class Patient implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String nom;
	private String prenom;
	private String adresse;
	private String datenais;
	private String lieu;
	private String sexe;
	private String tel;
	private String numSS;
	private Double poid;
	private Double taille;
	private String groupeS;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idmedecin")
	private Medecin medecin;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idsecretaire")
	private Secretaire secretaire;
	@OneToOne
	@JoinColumn(name="iddossier")
	private Dossier dossier;
	@OneToMany(mappedBy="patient")
	private Collection<Rdv> rdvs;
	@OneToMany(mappedBy="patient")
	private Collection<Visite> visites;
	@OneToMany(mappedBy="patient")
	private Collection<Antecedant> antecedants;
	@OneToMany(mappedBy="patient")
	private Collection<Certificat> certificats;
	@OneToMany(mappedBy="patient")
	private Collection<Bilan> bilan;
	private byte[] photo;
	private String nomPhoto;
	
	
	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public String getNomPhoto() {
		return nomPhoto;
	}

	public void setNomPhoto(String nomPhoto) {
		this.nomPhoto = nomPhoto;
	}

	public Medecin getMedecin() {
		return medecin;
	}

	public void setMedecin(Medecin medecin) {
		this.medecin = medecin;
	}

	public Collection<Bilan> getBilan() {
		return bilan;
	}

	public void setBilan(Collection<Bilan> bilan) {
		this.bilan = bilan;
	}

	public Double getPoid() {
		return poid;
	}

	public void setPoid(Double poid) {
		this.poid = poid;
	}

	public Double getTaille() {
		return taille;
	}

	public void setTaille(Double taille) {
		this.taille = taille;
	}

	public String getGroupeS() {
		return groupeS;
	}

	public void setGroupeS(String groupeS) {
		this.groupeS = groupeS;
	}

	public Secretaire getSecretaire() {
		return secretaire;
	}

	public void setSecretaire(Secretaire secretaire) {
		this.secretaire = secretaire;
	}

	public Patient() {
		super();

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Patient(String nom, String prenom, String adresse, String datenais,
			String lieu, String sexe, String tel,String numSS) {
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.datenais = datenais;
		this.lieu = lieu;
		this.sexe = sexe;
		this.tel = tel;
		this.numSS=numSS;
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

	public Dossier getDossier() {
		return dossier;
	}

	public void setDossier(Dossier dossier) {
		this.dossier = dossier;
	}

	public Collection<Visite> getVisites() {
		return visites;
	}

	public void setVisites(Collection<Visite> visites) {
		this.visites = visites;
	}

	public String getNumSS() {
		return numSS;
	}

	public void setNumSS(String numSS) {
		this.numSS = numSS;
	}

	public Collection<Rdv> getRdvs() {
		return rdvs;
	}

	public void setRdvs(Collection<Rdv> rdvs) {
		this.rdvs = rdvs;
	}

	public String getSunSS() {
		return numSS;
	}

	public void setMunSS(String numSS) {
		this.numSS = numSS;
	}

	public Collection<Antecedant> getAntecedants() {
		return antecedants;
	}

	public void setAntecedants(Collection<Antecedant> antecedants) {
		this.antecedants = antecedants;
	}

	public Collection<Certificat> getCertificats() {
		return certificats;
	}

	public void setCertificats(Collection<Certificat> certificats) {
		this.certificats = certificats;
	}
	
}
