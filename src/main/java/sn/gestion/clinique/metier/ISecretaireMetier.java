package sn.gestion.clinique.metier;

import java.util.List;

import sn.gestion.clinique.entites.*;

public interface ISecretaireMetier {
	public Message envoyerMessage(Message message);
	public List<Users> listeUser(int id);
	public List<Message> listeMessages(int exp,int des);
	public List<Rdv> ListRdvpat(String name);
	public Administrateur ajouterAdministrateur(Administrateur admin);
	public void ModifierAdministrateur(Administrateur admin);
	public List<Paiement> ListPaiement();
	public List<Service> ListService();
	public List<Certificat> listCertificat(int idpatient);
	public Patient getPatient(int idpatient);
	public Visite getVisite(int idvisite);
	public List<Patient> ListPatient();
	public List<Visite> ListVisite(int idpatient);
	public List<Patient> ListPatient(String nompatient);
	public List<Rdv> ListRdv();
	public List<Medecin> ListMedecin();
	public void ModifierSecretaire(Secretaire secretaire);
	public Secretaire chercherSecretaire(int idsecretaire);
	public List<Rdv> ListRdv(String date);
	public List<Rdv> ListRdv(int idpatient);
	public List<Dossier> ListDossier();
	public List<Dossier> ListDossier(int idmedecin);
	public List<Rdv> ListRdvMedecin(Medecin medecin, String jour);
	public Dossier ChercherDossier(int iddossier);
	public List<Patient> ChercherPatient(String string);
	public void ModifierPaiement(Paiement paiement);
	public List<Users> VerifUsers();
	public Paiement AjouterPaiement(Paiement paiement);
	public Rdv AjouterRdv(Rdv rdv);
	public Visite AjouterVisite(Visite visite);
	public Dossier AjouterDossier(Dossier dossier);
	public Patient AjouterPatient(Patient patient);
	public Ordonnance getOrdonnance(int idord);
	
	public void ModifierPatient(Patient patient);
	public void ModifierInformationPatient(Patient p);
	public void ModifierRdv(Rdv rdv);
	public void ModifierDossier(Dossier d);
	public void ModifierVisite(Visite v);

	public void AnnulerRdv(Long id);
	public void AffecterPatient(int idpatient, int idmedecin);
	public Rdv ChercherRdv(int idrdv);
	public void SupprimerRdv(int idrdv);
	public List<Ordonnance> ListOrdonnance();
	public List<Ordonnance> ListOrdonnance(int idpatient);
	public List<Antecedant> listAntecedants(int idpatient);

}
