package sn.gestion.clinique.dao;

import java.util.List;

import javax.persistence.*;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import sn.gestion.clinique.entites.*;
import sn.gestion.clinique.dao.*;


public class CliniqueDaoImplemente implements ICliniqueDao{
	@PersistenceContext
	private EntityManager em;
	@Override
	public Users getUser(int id){
		return em.find(Users.class, id);

	}
	public Patient getPatient(int idpatient) {
		return em.find(Patient.class, idpatient);
	}
	public List<Paiement> ListPaiement() {
		Query req=em.createQuery("select p from Paiement p ");
		return req.getResultList();
	}
	public List<Administrateur> ListAdministrateur() {
		Query req=em.createQuery("select a from Administrateur a ");
		return req.getResultList();
	}
	public List<Patient> ListPatientSecr(int idsecr){
		Query req=em.createQuery("select p from Patient p where p.secretaire.id like :x");
		req.setParameter("x", idsecr);
		return req.getResultList();
	}

	public Medecin getMedecin(int idmedecin) {
		return em.find(Medecin.class, idmedecin);
	}
	@Override
	public List<Ordonnance> ListOrdonnance(int idpatient){
		Query req=em.createQuery("select o from Ordonnance o where o.patient.id like :x");
		req.setParameter("x", idpatient);
		return req.getResultList();
	}
	public List<Antecedant> listAntecedants(int idpatient){
		Query req=em.createQuery("select a from Antecedant a where a.patient.id like :x");
		req.setParameter("x", idpatient);
		return req.getResultList();
	}
	public Visite getVisite(int idvisite) {
		return em.find(Visite.class, idvisite);
	}
	@Override
	public List<Service> ListService(){
		Query req=em.createQuery("select s from Service s");
		return req.getResultList();
	}

	@Override
	public List<Ordonnance> ListOrdonnance(){
		Query req=em.createQuery("select l from Ordonnance l ");
		return req.getResultList();
	}
	@Override
	public Ordonnance getOrdonnance(int idord){
		return em.find(Ordonnance.class, idord);
	}
	public List<Users> VerifUsers(){
		Query req=em.createQuery("select u from Users u");
		return req.getResultList();
	}
	@Override
	public List<Patient> ListPatient() {
		Query req=em.createQuery("select p from Patient p");
		return req.getResultList();
	}

	@Override
	public List<Patient> ListPatient(String nom) {
		Query req=em.createQuery("select p from Patient p where p.nom like :x or p.prenom like :x");
		req.setParameter("x", "%"+nom+"%");
		return req.getResultList();
	}

	@Override
	public List<Medecin> ListMedecin() {
		Query req=em.createQuery("select m from Medecin m");
		return req.getResultList();
	}
	@Override
	public List<Infirmier> ListInfirmier() {
		Query req=em.createQuery("select i from Infirmier i");
		return req.getResultList();
	}
	@Override
	public List<Secretaire> ListSecretaire() {
		Query req=em.createQuery("select s from Secretaire s");
		return req.getResultList();
	}

	@Override
	public List<Rdv> ListRdv(String date) {
		Query req=em.createQuery("select r from Rdv r where r.date like :x");
		req.setParameter("x", date);
		return req.getResultList();
	}

	@Override
	public List<Rdv> ListRdv(int idpatient) {
		Query req=em.createQuery("select r from Rdv r where r.patient.id like :x");
		req.setParameter("x", idpatient);
		return req.getResultList();
	}

	@Override
	public List<Dossier> ListDossier() {
		Query req=em.createQuery("select d from Dossier d");
		return req.getResultList();
	}

	@Override
	public List<Dossier> ListDossier(int idmedecin) {
		Query req=em.createQuery("select d from Dossier d where d.medecin.id like :x");
		req.setParameter("x", idmedecin);
		return req.getResultList();
	}

	@Override
	public List<Rdv> ListRdvMedecin(Medecin medecin, String jour) {
		return null;
	}

	@Override
	public Rdv AjouterRdv(Rdv rdv) {
		em.persist(rdv);
		return rdv;
	}

	@Override
	public Visite AjouterVisite(Visite visite) {
		em.persist(visite);
		return visite;
	}

	@Override
	public Dossier AjouterDossier(Dossier dossier) {
		em.persist(dossier);
		return dossier;
	}

	@Override
	public Patient AjouterPatient(Patient patient) {
		em.persist(patient);
		return patient;
	}

	@Override
	public Ordonnance AjouterOrdonnance(Ordonnance ordonnance) {
		em.persist(ordonnance);
		return ordonnance;
	}

	@Override
	public Dossier ChercherDossier(int iddossier) {
		return em.find(Dossier.class, iddossier);
	}
	
	@Override
	public List<Patient> ChercherPatient(String string) {
		Query req=em.createQuery("select p from Patient p where p.nom like :x or p.prenom like :x");
		req.setParameter("x", "%"+string+"%");
		return req.getResultList();
	}


	@Override
	public void ModifierDossier(Dossier d) {
		em.merge(d);
		
	}
	@Override
	public void ModifierPatient(Patient patient) {
		em.merge(patient);
		
	}
	@Override
	public void ModifierRdv(Rdv rdv) {
		em.merge(rdv);	
	}
	
	@Override
	public void ModifierOrdonnance(Ordonnance o) {
		em.merge(o);
		
	}

	@Override
	public void AnnulerRdv(Long id) {
		Rdv rdv=em.find(Rdv.class, id);
		em.remove(rdv);
		
	}

	@Override
	public void AffecterPatient(int idpatient, int idmedecin) {
		Patient p=getPatient(idpatient);
		
	}
	
	@Override
	public void ModifierVisite(Visite v) {
		em.merge(v);
	}

	@Override
	public void ModifierInformationPatient(Patient p) {
		em.merge(p);
	}
	@Override
	@Transactional
	public List<Rdv> ListRdv() {
		Query req=em.createQuery("select r from Rdv r");
		return req.getResultList();
	}
	@Override
	public Paiement AjouterPaiement(Paiement paiement) {
		em.persist(paiement);
		return paiement;
	}
	@Override
	public Users AjouterMedecin(Users user) {
		em.persist(user);
		return  user;
	}
	@Override
	public Users AjouterInfirmier(Users user) {
		em.persist(user);
		return user;
	}
	@Override
	public Users AjouterSecretaire(Users user) {
		em.persist(user);
		return  user;
	}
	@Override
	public void SupprimerAdministrateur(int idAdmin) {
		Administrateur ad=getAdministrateur(idAdmin);
		em.remove(ad);	
	}
	@Override
	public void SupprimerSecretaire(int idsecretaire) {
		Secretaire secretaire=chercherSecretaire(idsecretaire);
		em.remove(secretaire);
	}
	@Override
	public void SupprimerMedecin(int idmedecin) {
		Medecin medecin=chercherMedecin(idmedecin);
		em.remove(medecin);
		
	}
	public void SupprimerCertificat(int idCert) {
		Certificat cert=getCertificat(idCert);
		em.remove(cert);
	}
	@Override
	public Medecin chercherMedecin(int idmedecin) {
		return em.find(Medecin.class,idmedecin);
	}
	@Override
	public Infirmier chercherInfirmier(int idinfirmier) {
		return em.find(Infirmier.class,idinfirmier);
	}
	@Override
	public Secretaire chercherSecretaire(int idsecretaire) {
		return em.find(Secretaire.class,idsecretaire);
	}
	@Override
	public void Modifiernfirmier(Infirmier infirmier) {
		em.merge(infirmier);	
	}
	@Override
	public void ModifierMedecin(Medecin medecin) {
		em.merge(medecin);	
	}
	@Override
	public void ModifierSecretaire(Secretaire secretaire) {
		em.merge(secretaire);	
	}
	@Override
	public List<Specialite> ListSpecialite() {
		Query req=em.createQuery("select s from Specialite s");
		return req.getResultList();
	}
	@Override
	public List<Visite> ListVisite(int idpatient) {
		Query req=em.createQuery("select v from Visite v where v.patient.id like :x");
		req.setParameter("x", idpatient);
		return req.getResultList();
	}
	@Override
	public Rdv ChercherRdv(int idrdv) {
		return em.find(Rdv.class,idrdv);
	}
	@Override
	public void SupprimerRdv(int idrdv) {

		Rdv rdv=ChercherRdv(idrdv);
		em.remove(rdv);
	}
	@Override
	public void ModifierPaiement(Paiement paiement) {
		em.merge(paiement);
	}
	@Override
	public List<Certificat> listCertificat(int idpatient) {
		Query req=em.createQuery("select c from Certificat c where c.patient.id like :x");
		req.setParameter("x", idpatient);
		return req.getResultList();
	}
	@Override
	public Administrateur ajouterAdministrateur(Administrateur admin) {
		 em.persist(admin);
		 return admin;
	}
	@Override
	public void ModifierAdministrateur(Administrateur admin) {
		em.merge(admin);
		
	}
	@Override
	public Administrateur getAdministrateur(int idAdmin) {

		return em.find(Administrateur.class, idAdmin);
	}
	@Override
	public void SupprimerVisite(int idvisite) {
		Visite v=em.find(Visite.class, idvisite);
		em.remove(v);
		
	}
	@Override
	public Antecedant getAntecedant(int idAnt) {
		return em.find(Antecedant.class, idAnt);
	}
	@Override
	public Antecedant ajouterAntecdant(Antecedant ant) {
		em.persist(ant);
		return ant;
	}
	@Override
	public void ModifierAntecedant(Antecedant ant) {
		em.merge(ant);
		
	}
	@Override
	public void SupprimerAntecedant(int idAnt) {
		em.remove(em.find(Antecedant.class, idAnt));	
	}
	@Override
	public void SupprimerOrdonnance(int idOrd) {
		em.remove(em.find(Ordonnance.class, idOrd));	
		
	}
	@Override
	public Certificat AjouterCertificat(Certificat cert) {
		em.persist(cert);
		return cert;
	}
	@Override
	public void ModifierCertificat(Certificat cert) {
		em.merge(cert);
	}
	@Override
	public Certificat getCertificat(int idCert) {
		return em.find(Certificat.class, idCert);
	}
	@Override
	public List<Rdv> ListRdvpat(String name) {
		Query req=em.createQuery("select r from Rdv r where r.patient.prenom like :x or r.patient.nom like :x");
		req.setParameter("x", "%"+name+"%");
		return req.getResultList();
	}
	public List<Bilan> ListBilan(int idPat) {
		Query req=em.createQuery("select r from Bilan r where r.patient.id like :x ");
		req.setParameter("x", idPat);
		return req.getResultList();
	}
	@Override
	public Bilan AjouterBilan(Bilan bilan) {
		em.persist(bilan);
		return bilan;
	}
	@Override
	public void ModifierBilan(Bilan bilan) {
		em.merge(bilan);
		
	}
	@Override
	public List<Patient> ListPatient(int idMed) {
		Query req=em.createQuery("select p from Patient p where p.medecin.id like :x");
		req.setParameter("x", idMed);
		return req.getResultList();
	}
	@Override
	public List<Patient> searchPatient(String string,int idMed) {
		Query req=em.createQuery("select p from Patient p where p.medecin.id like :x and (p.nom like :y or p.prenom like :y)");
		req.setParameter("x", idMed);
		req.setParameter("y", "%"+string+"%");
		return req.getResultList();
	}
	@Override
	public List<Rdv> searchRdv(String string, int idMed) {
		Query req=em.createQuery("select r from Rdv r where r.patient.medecin.id like :y and (r.patient.prenom like :x or r.patient.nom like :x)");
		req.setParameter("x", "%"+string+"%");
		req.setParameter("y", idMed);
		return req.getResultList();
	}
	@Override
	public List<Rdv> ListRdvMed(int idMed) {
		Query req=em.createQuery("select r from Rdv r where r.patient.medecin.id like :y ");
		req.setParameter("y", idMed);
		return req.getResultList();
	}
	@Override
	public Bilan getBilan(int idBilan) {
		return em.find(Bilan.class, idBilan);
	}
	@Override
	public void SupprimerBilan(int idBilan) {
		em.remove(em.find(Bilan.class, idBilan));
		
	}
	@Override
	public List<Secretaire> chercherSecretaire(String chaine) {
		Query req=em.createQuery("select s from Secretaire s where s.nom like :x or s.prenom like :x");
		req.setParameter("x", "%"+chaine+"%");
		return req.getResultList();
	}
	@Override
	public List<Medecin> chercherMedecin(String chaine) {
		Query req=em.createQuery("select m from Medecin m where m.nom like :x or m.prenom like :x");
		req.setParameter("x", "%"+chaine+"%");
		return req.getResultList();
	}
	@Override
	public List<Users> verifCompte(String login, String question, String reponse) {
		Query req=em.createQuery("select u from Users u where u.login like :x and u.question like :y and u.reponse like :z");
		req.setParameter("x", login);
		req.setParameter("y", question);
		req.setParameter("z", reponse);
		return req.getResultList();
	}
	public List<Message> listeMessages(int exp , int des){
		
		
		Query req= em.createQuery("select m from Message m where (m.idexpediteur like :x and m.idrecepteur like :y) or (m.idexpediteur like :y and m.idrecepteur like :x) ORDER BY date");
		req.setParameter("x", exp);
		req.setParameter("y", des);
		
		return req.getResultList();
	}
	@Override
	public Message envoyerMessage(Message message) {
		// TODO Auto-generated method stub
		em.persist(message);
		return message;
	}
	@Override
	public List<Users> listeUser(int id) {
		Query req=em.createQuery("select u from Users u where u.id not like :y ");
		req.setParameter("y", id);
		return req.getResultList();
	}
	public int notifMessage(int id){
		int n=0;
		Query re = em.createQuery("select Count(m) from Message m where m.idrecepteur like :x");
		re.setParameter("x", id);
		//re.setParameter("Y", n);
		return re.getMaxResults();
	}
}
