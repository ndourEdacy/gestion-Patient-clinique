package sn.gestion.clinique.metier;


import java.util.List;

import sn.gestion.clinique.entites.*;

public interface IAdminMetier {
	public Users getUser(int id);
	public Message envoyerMessage(Message message);
	public List<Users> listeUser(int id);
	public List<Message> listeMessages(int exp,int des);
	public List<Administrateur> ListAdministrateur();
	public void SupprimerVisite(int idvisite);
	public Administrateur getAdministrateur(int idAdmin);
	public Administrateur ajouterAdministrateur(Administrateur admin);
	public void ModifierAdministrateur(Administrateur admin);
	public List<Paiement> ListPaiement();
	public List<Certificat> listCertificat(int idpatient);
	public List<Specialite> ListSpecialite();
	public List<Patient> ListPatient();
	public List<Service> ListService();
	public List<Infirmier> ListInfirmier();
	public List<Secretaire> ListSecretaire();
	public List<Medecin> ListMedecin();
	public void Modifiernfirmier(Infirmier infirmier);
	public void ModifierPaiement(Paiement paiement);
	public void ModifierPatient(Patient patient);
	public void ModifierMedecin(Medecin medecin);
	public void ModifierSecretaire(Secretaire secretaire);
	public Medecin chercherMedecin(int idmedecin);
	public Infirmier chercherInfirmier(int idinfimier);
	public Secretaire chercherSecretaire(int idsecretaire);
	public Users AjouterMedecin(Users user);
	public Users AjouterInfirmier(Users user);
	public Users AjouterSecretaire(Users user);
	public void SupprimerAdministrateur(int idAdmin);
	public void SupprimerSecretaire(int idsecretaire);
	public void SupprimerMedecin(int idmedecin);
	public List<Users> VerifUsers();
	public  Rdv ChercherRdv(int idrdv);
	public void SupprimerRdv(int idrdv);
	public Ordonnance getOrdonnance(int idord);
	public List<Ordonnance> ListOrdonnance();
	public List<Ordonnance> ListOrdonnance(int idpatient);
	public List<Antecedant> listAntecedants(int idpatient);
	public List<Secretaire> chercherSecretaire(String chaine);
	public List<Medecin> chercherMedecin(String chaine);
}
