package sn.gestion.clinique.metier;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import sn.gestion.clinique.dao.ICliniqueDao;
import sn.gestion.clinique.entites.*;
@Transactional
public class MedecinMetierImpl implements IMedecinMetier{
	private ICliniqueDao dao;
	@Override
	public Patient getPatient(int idpatient) {
		// TODO Auto-generated method stub
		return dao.getPatient(idpatient);
	}

	@Override
	public Dossier AjouterDossier(Dossier dossier) {
		return dao.AjouterDossier(dossier);
	}

	@Override
	public Dossier ChercherDossier(int iddossier) {
		return dao.ChercherDossier(iddossier);
	}

	@Override
	public void ModifierDossier(Dossier d) {
		dao.ModifierDossier(d);
		
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
	public void ModifierOrdonnance(Ordonnance o) {
		dao.ModifierOrdonnance(o);
		
	}


	@Override
	public List<Users> VerifUsers() {
		return dao.VerifUsers();
	}

	@Override
	public void ModifierPatient(Patient patient) {
		dao.ModifierPatient(patient);
		
	}

	public Ordonnance getOrdonnance(int idord){
		return dao.getOrdonnance(idord);
	}
	@Override
	public List<Ordonnance> ListOrdonnance() {
		return dao.ListOrdonnance();
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
		// TODO Auto-generated method stub
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
	public void SupprimerVisite(int idvisite) {
		dao.SupprimerVisite(idvisite);
		
	}

	@Override
	public Visite getVisite(int idVisite) {
		// TODO Auto-generated method stub
		return dao.getVisite(idVisite);
	}
	@Override
	public Secretaire chercherSecretaire(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Service> ListService() {
		// TODO Auto-generated method stub
		return dao.ListService();
	}
	@Override
	@Transactional
	public List<Rdv> ListRdv() {
		// TODO Auto-generated method stub
		return dao.ListRdv();
	}
	@Override
	public List<Patient> getPatient() {
		// TODO Auto-generated method stub
		return dao.ListPatient()  ;
	}
	@Override
	public List<Visite> ListVisite(int idPat) {
		// TODO Auto-generated method stub
		return dao.ListVisite(idPat);
	}
	@Override
	public List<Rdv> ListRdv(int idPat) {
		// TODO Auto-generated method stub
		return dao.ListRdv(idPat);
	}
	@Override
	public void ModifierRdv(Rdv rdv) {
		// TODO Auto-generated method stub
		dao.ModifierRdv(rdv);
	}
	@Override
	public Rdv AjouterRdv(Rdv rdv) {
		// TODO Auto-generated method stub
		return dao.AjouterRdv(rdv);
	}
	@Override
	public Patient AjouterPatient(Patient pat) {
		// TODO Auto-generated method stub
		return dao.AjouterPatient(pat);
	}
	@Override
	public void ModifierVisite(Visite visite) {
		// TODO Auto-generated method stub
		dao.ModifierVisite(visite);
	}

	@Override
	public Paiement AjouterPaiement(Paiement paiement) {
		// TODO Auto-generated method stub
		return dao.AjouterPaiement(paiement);
	}
	@Override
	public Visite AjouterVisite(Visite visite) {
		// TODO Auto-generated method stub
		return  dao.AjouterVisite(visite);
	}
	@Override
	public Patient getPatient(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void ModifierPaiement(Paiement paiement) {
		dao.ModifierPaiement(paiement);
		
	}
	@Override
	public Antecedant ajouterAntecedant(Antecedant ant) {
		// TODO Auto-generated method stub
		return dao.ajouterAntecdant(ant);
	}
	@Override
	public void ModifierAntecdant(Antecedant ant) {
		// TODO Auto-generated method stub
		dao.ModifierAntecedant(ant);
	}
	@Override
	public Antecedant getAntecedant(int idAnt) {
		// TODO Auto-generated method stub
		return dao.getAntecedant(idAnt);
	}
	@Override
	public void SupprimerAntecedant(int ant) {
		dao.SupprimerAntecedant(ant);
		
	}

	@Override
	public List<Ordonnance> ListOrdonnance(int idpatient) {
		// TODO Auto-generated method stub
		return dao.ListOrdonnance(idpatient);
	}

	@Override
	public void ajouterOrdonnance(Ordonnance ord) {
		dao.AjouterOrdonnance(ord);
		
	}

	@Override
	public void SupprimerOrdonnance(int idOrd) {
		dao.SupprimerOrdonnance(idOrd);
		
	}
	@Override
	public Certificat AjouterCertificat(Certificat cert) {
		return dao.AjouterCertificat(cert);
	}
	@Override
	public void ModifierCertificat(Certificat certificat) {
		dao.ModifierCertificat(certificat);
	}

	@Override
	public Certificat getCertificat(int idCert) {
		return dao.getCertificat(idCert);
	}

	@Override
	public List<Patient> chercherPatient(String nom) {
		return dao.ChercherPatient(nom);
	}

	public List<Rdv> ListRdvpat(String name) {
		return dao.ListRdvpat(name);
	}

	@Override
	public List<Specialite> ListSpecialite() {
		return dao.ListSpecialite();
	}

	@Override
	public Medecin chercherMedecin(int idmedecin) {
		// TODO Auto-generated method stub
		return dao.chercherMedecin(idmedecin);
	}

	@Override
	public void ModifierMedecin(Medecin medecin) {
		dao.ModifierMedecin(medecin);
		
	}

	@Override
	public void ModifierBilan(Bilan bilan) {
		dao.ModifierBilan(bilan);
		
	}

	@Override
	public List<Bilan> ListBilan(int idPat) {
		return dao.ListBilan(idPat);
	}

	@Override
	public Bilan ajouterBilan(Bilan bilan) {
		return dao.AjouterBilan(bilan);
	}

	@Override
	public List<Patient> ListPatient(int idMed) {
		return dao.ListPatient(idMed);
	}

	@Override
	public List<Patient> searchPatient(String string,int idMed) {
		return dao.searchPatient(string, idMed);
	}

	@Override
	public List<Rdv> searchRdv(String string, int idMed) {
		return dao.searchRdv(string, idMed);
	}

	@Override
	public List<Rdv> ListRdvMed(int idMed) {
		// TODO Auto-generated method stub
		return dao.ListRdvMed(idMed);
	}

	@Override
	public void SupprimerBilan(int idBilan) {
		dao.SupprimerBilan(idBilan);
		
	}
	@Override
	public Bilan getBilan(int idBilan) {
		// TODO Auto-generated method stub
		return dao.getBilan(idBilan);
	}

	@Override
	public List<Secretaire> chercherSecretaire(String chaine) {
		return dao.chercherSecretaire(chaine);
	}

	@Override
	public List<Medecin> chercherMedecin(String chaine) {
		return dao.chercherMedecin(chaine);
	}

	@Override
	public List<Patient> ListPatientSecr(int idsecr) {
		return dao.ListPatientSecr(idsecr);
	}

	@Override
	public void SupprimerCertificat(int idCert) {
		dao.SupprimerCertificat(idCert);
		
	}

	@Override
	public List<Users> verifCompte(String login, String question, String reponse) {
		// TODO Auto-generated method stub
		return dao.verifCompte(login, question, reponse);
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

	@Override
	public int notifMessage(int exp) {
		// TODO Auto-generated method stub
		return dao.notifMessage(exp);
	}
}
