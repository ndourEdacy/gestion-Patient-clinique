package sn.gestion.clinique.metier;


import java.util.List;

import org.apache.taglibs.standard.lang.jstl.OrOperator;
import org.springframework.transaction.annotation.Transactional;

import sn.gestion.clinique.dao.ICliniqueDao;
import sn.gestion.clinique.entites.*;

@Transactional
public class CliniqueMetierImplemente implements IAdminMetier,ISecretaireMetier,IMedecinMetier{
	private ICliniqueDao dao;
	
	@Override
	public List<Users> verifCompte(String login, String question, String reponse) {
		// TODO Auto-generated method stub
		return dao.verifCompte(login, question, reponse);
	}
	@Override
	public void SupprimerCertificat(int idCert) {
		dao.SupprimerCertificat(idCert);
		
	}
	@Override
	public void ModifierBilan(Bilan bilan) {
		dao.ModifierBilan(bilan);
		
	}
	@Override
	public List<Patient> ListPatientSecr(int idsecr) {
		return dao.ListPatientSecr(idsecr);
	}
	@Override
	public List<Bilan> ListBilan(int idPat) {
		// TODO Auto-generated method stub
		return dao.ListBilan(idPat);
	}

	@Override
	public Bilan ajouterBilan(Bilan bilan) {
		// TODO Auto-generated method stub
		return dao.AjouterBilan(bilan);
	}
	@Override
	public Certificat getCertificat(int idCert) {
		return dao.getCertificat(idCert);
	}
	@Override
	public void SupprimerAntecedant(int ant) {
		dao.SupprimerAntecedant(ant);
		
	}
	public void setDao(ICliniqueDao dao) {
		this.dao = dao;
	}
	@Override
	public List<Rdv> ListRdvpat(String name) {
		// TODO Auto-generated method stub
		return dao.ListRdvpat(name);
	}
	@Override
	public List<Ordonnance> ListOrdonnance(int idpatient) {
		return dao.ListOrdonnance(idpatient);
	}
	@Override
	public void Modifiernfirmier(Infirmier infirmier) {
		dao.Modifiernfirmier(infirmier);
		
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
	public void ModifierMedecin(Medecin medecin) {
		dao.ModifierMedecin(medecin);
		
	}
	@Override
	public List<Antecedant> listAntecedants(int idpatient) {
		return dao.listAntecedants(idpatient);
	}
	@Override
	public List<Ordonnance> ListOrdonnance() {
		return dao.ListOrdonnance();
	}
	@Override
	public void ModifierSecretaire(Secretaire secretaire) {
		dao.ModifierSecretaire(secretaire);
		
	}

	@Override
	public Medecin chercherMedecin(int idmedecin) {
		return dao.chercherMedecin(idmedecin);
	}

	@Override
	public Infirmier chercherInfirmier(int idinfimier) {
		return dao.chercherInfirmier(idinfimier);
	}

	@Override
	public Secretaire chercherSecretaire(int idsecretaire) {
		return dao.chercherSecretaire(idsecretaire);
	}

	@Override
	public Users AjouterMedecin(Users user) {
		return dao.AjouterMedecin(user);
	}

	@Override
	public Users AjouterInfirmier(Users user) {
		return dao.AjouterInfirmier(user);
	}

	@Override
	public Users AjouterSecretaire(Users user) {
		return dao.AjouterSecretaire(user);
	}

	@Override
	public void SupprimerSecretaire(int idsecretaire) {
		dao.SupprimerSecretaire(idsecretaire);
		
	}

	@Override
	public void SupprimerMedecin(int idmedecin) {
		dao.SupprimerMedecin(idmedecin);
		
	}

	@Override
	public List<Specialite> ListSpecialite() {
		return dao.ListSpecialite();
	}
	@Override
	public List<Service> ListService() {
		return dao.ListService();
	}

	@Override
	public List<Infirmier> ListInfirmier() {
		return dao.ListInfirmier();
	}

	@Override
	public List<Secretaire> ListSecretaire() {
		return dao.ListSecretaire();
	}

	@Override
	public List<Medecin> ListMedecin() {
		return dao.ListMedecin();
	}
	@Override
	public List<Users> VerifUsers() {
		return dao.VerifUsers();
	}

	@Override
	public List<Patient> ListPatient() {
		return dao.ListPatient();
	}

	@Override
	public void ModifierOrdonnance(Ordonnance o) {
		dao.ModifierOrdonnance(o);
		
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
	public List<Patient> ListPatient(String nompatient) {
		return dao.ListPatient(nompatient);
	}

	@Override
	@Transactional
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
	public Dossier AjouterDossier(Dossier dossier) {
		return dao.AjouterDossier(dossier);
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

	@Override
	public Ordonnance getOrdonnance(int idord){
		return dao.getOrdonnance(idord);
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
	public Administrateur getAdministrateur(int idAdmin) {
		return dao.getAdministrateur(idAdmin);
	}
	@Override
	public void SupprimerVisite(int idvisite) {
		dao.SupprimerVisite(idvisite);
		
	}
	@Override
	public Patient getPatient(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Patient> getPatient() {
		// TODO Auto-generated method stub
		return dao.ListPatient();
	}
	@Override
	public Paiement AjouterPaiement(Paiement paiement) {
		// TODO Auto-generated method stub
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
	public Patient AjouterPatient(Patient patient) {
		return dao.AjouterPatient(patient);
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
	public List<Patient> chercherPatient(String nom) {
		return dao.ChercherPatient(nom);
	}

	@Override
	public List<Patient> ListPatient(int idMed) {
		return dao.ListPatient(idMed);
	}
	public List<Patient> searchPatient(String string,int idMed) {
		return dao.searchPatient(string, idMed);
	}
	@Override
	public List<Rdv> searchRdv(String string, int idMed) {
		return dao.searchRdv(string, idMed);
	}

	@Override
	public List<Rdv> ListRdvMed(int idMed) {
		return dao.ListRdvMed(idMed);
	}
	@Override
	public void SupprimerBilan(int idBilan) {
		dao.SupprimerBilan(idBilan);
		
	}
	@Override
	public Bilan getBilan(int idBilan) {
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
	public List<Administrateur> ListAdministrateur() {
		// TODO Auto-generated method stub
		return dao.ListAdministrateur();
	}
	@Override
	public void SupprimerAdministrateur(int idAdmin) {
		dao.SupprimerAdministrateur(idAdmin);
		
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
		return notifMessage(exp);
	}
	@Override
	public Users getUser(int id) {
		// TODO Auto-generated method stub
		return dao.getUser(id);
	}
}
