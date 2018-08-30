package sn.gestion.clinique.metier;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import sn.gestion.clinique.dao.ICliniqueDao;
import sn.gestion.clinique.entites.*;

@Transactional
public class SecretaireMetierImpl implements ISecretaireMetier{
	private ICliniqueDao dao;
	
	public void setDao(ICliniqueDao dao) {
		this.dao = dao;
	}
	public List<Service> ListService() {
		return dao.ListService();
	}
	@Override
	public void ModifierSecretaire(Secretaire secretaire) {
		dao.ModifierSecretaire(secretaire);
		
	}
	@Override
	public Secretaire chercherSecretaire(int idsecretaire) {
		return dao.chercherSecretaire(idsecretaire);
	}
	@Override
	public Patient getPatient(int idpatient) {
		return dao.getPatient(idpatient);
	}

	@Override
	public Visite getVisite(int idvisite) {
		return dao.getVisite(idvisite);
	}
	@Override
	public List<Ordonnance> ListOrdonnance() {
		return dao.ListOrdonnance();
	}
	@Override
	public List<Ordonnance> ListOrdonnance(int idpatient) {
		return dao.ListOrdonnance(idpatient);
	}
	@Override
	public List<Patient> ListPatient() {
		return dao.ListPatient();
	}

	@Override
	public List<Patient> ListPatient(String nompatient) {
		return dao.ListPatient(nompatient);
	}

	@Override
	public List<Rdv> ListRdv() {
		return dao.ListRdv();
	}

	@Override
	public List<Rdv> ListRdv(String date) {
		return dao.ListRdv(date);
	}

	@Override
	public List<Rdv> ListRdv(int idpatient) {
		return dao.ListRdv(idpatient);
	}

	@Override
	public List<Dossier> ListDossier() {
		return dao.ListDossier();
	}

	@Override
	public List<Dossier> ListDossier(int idmedecin) {
		return dao.ListDossier(idmedecin);
	}

	@Override
	public List<Rdv> ListRdvMedecin(Medecin medecin, String jour) {
		return dao.ListRdvMedecin(medecin, jour);
	}

	@Override
	public Dossier ChercherDossier(int iddossier) {
		return dao.ChercherDossier(iddossier);
	}

	@Override
	public List<Patient> ChercherPatient(String string) {
		return dao.ChercherPatient(string);
	}

	@Override
	public Paiement AjouterPaiement(Paiement paiement) {
		return dao.AjouterPaiement(paiement);
	}
		

	@Override
	public Rdv AjouterRdv(Rdv rdv) {
		return dao.AjouterRdv(rdv);
	}

	@Override
	public Visite AjouterVisite(Visite visite) {
		return dao.AjouterVisite(visite);
	}

	@Override
	public Dossier AjouterDossier(Dossier dossier) {
		return dao.AjouterDossier(dossier);
	}

	@Override
	public Patient AjouterPatient(Patient patient) {
		return dao.AjouterPatient(patient);
	}

	@Override
	public void ModifierInformationPatient(Patient p) {
		dao.ModifierInformationPatient(p);
		
	}

	@Override
	public void ModifierRdv(Rdv rdv) {
		dao.ModifierRdv(rdv);
	}

	@Override
	public void ModifierDossier(Dossier d) {
		dao.ModifierDossier(d);
		
	}

	@Override
	public void ModifierVisite(Visite v) {
		dao.ModifierVisite(v);
		
	}

	@Override
	public void AnnulerRdv(Long id) {
		dao.AnnulerRdv(id);
		
	}

	@Override
	public void AffecterPatient(int idpatient, int idmedecin) {
		dao.AffecterPatient(idpatient, idmedecin);
		
	}

	@Override
	public List<Visite> ListVisite(int idpatient) {
		return dao.ListVisite(idpatient);
	}

	@Override
	public List<Users> VerifUsers() {
		return dao.VerifUsers();
	}
	@Override
	public Rdv ChercherRdv(int idrdv) {
		return dao.ChercherRdv(idrdv);
	}
	@Override
	public void SupprimerRdv(int idrdv) {

		dao.SupprimerRdv(idrdv);
	}

	@Override
	public void ModifierPatient(Patient patient) {
		dao.ModifierPatient(patient);
		
	}

	@Override
	public void ModifierPaiement(Paiement paiement) {
		dao.ModifierPaiement(paiement);
		
	}

	public Ordonnance getOrdonnance(int idord){
		return dao.getOrdonnance(idord);
	}

	@Override
	public List<Antecedant> listAntecedants(int idpatient) {
		return dao.listAntecedants(idpatient);
	}
	@Override
	public List<Certificat> listCertificat(int idpatient) {
		return dao.listCertificat(idpatient);
	}
	@Override
	public List<Paiement> ListPaiement() {
		return dao.ListPaiement();
	}
	@Override
	public Administrateur ajouterAdministrateur(Administrateur admin) {
		return dao.ajouterAdministrateur(admin);
	}
	@Override
	public void ModifierAdministrateur(Administrateur admin) {
		dao.ModifierAdministrateur(admin);
		
	}
	@Override
	public List<Rdv> ListRdvpat(String name) {
		return dao.ListRdvpat(name);
	}
	@Override
	public List<Medecin> ListMedecin() {
		return dao.ListMedecin();
	}
	@Override
	public Message envoyerMessage(Message message) {
		// TODO Auto-generated method stub
		return dao.envoyerMessage(message);
	}

	@Override
	public List<Users> listeUser(int id) {
		// TODO Auto-generated method stub
		return dao.listeUser(id);
	}

	@Override
	public List<Message> listeMessages(int exp, int des) {
		// TODO Auto-generated method stub
		return dao.listeMessages(exp, des);
	}
}
