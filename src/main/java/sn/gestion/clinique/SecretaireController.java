package sn.gestion.clinique;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.http.MediaType;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import sn.gestion.clinique.entites.*;
import sn.gestion.clinique.metier.*;


@Controller
@SessionAttributes("user")
@RequestMapping(value="/secretaire")
public class SecretaireController {
	@Autowired
	private ISecretaireMetier metier;
	
	@RequestMapping(value="/modifProfil")
	public String modifProfil(Model model,HttpSession session){
		Users user=(Users) session.getAttribute("user");
		if(user.getLogin()=="rien"){
			return "redirect:../login";
		}
		Secretaire secretaire=metier.chercherSecretaire(user.getId());
		model.addAttribute("secretaire",secretaire);
		model.addAttribute("editedSec",secretaire);
		model.addAttribute("user",secretaire);
		session.removeAttribute("page");
		session.setAttribute("page", "secretaire/modifProfil");
		model.addAttribute("message",new Message());
		model.addAttribute("liste", metier.listeUser(user.getId()));
		model.addAttribute("services",metier.ListService());
		return "modifProfilSecretaire";
	}
	
	@RequestMapping(value="/saveModifProfil")
	public String saveModifProfil(@Valid MultipartFile file,Secretaire secretaire, BindingResult bindingResult, Model model,HttpSession session) throws IOException{
		Users user=(Users) session.getAttribute("user");
		if(user.getLogin()=="rien"){
			return "redirect:../login";
		}
		String nomphoto=user.getNomPhoto();
		byte[] photo=user.getPhoto();
		if(!file.isEmpty()){
			secretaire.setPhoto(file.getBytes());
			secretaire.setNomPhoto(file.getOriginalFilename());
			metier.ModifierSecretaire(secretaire);
		}else{
			secretaire.setPhoto(photo);
			secretaire.setNomPhoto(nomphoto);
			if(secretaire.getId()>0){
				metier.ModifierSecretaire(secretaire);
			}
		}
		model.addAttribute("liste", metier.listeUser(user.getId()));
		model.addAttribute("message",new Message());
		session.removeAttribute("page");
		session.setAttribute("page", "secretaire/saveModifProfil");
		return "indexSecretaire";
	}
	
	@RequestMapping(value="/rechRdv")
	public String rechRdv(String name, HttpServletRequest request,ModelMap modelmap,HttpSession session){
		Users user=(Users) session.getAttribute("user");
		if(user.getLogin()==null){
			return "redirect:../login";
		}
		PagedListHolder<Rdv> pagedListHolder=new PagedListHolder<Rdv>(metier.ListRdvpat(name));
		int page=ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setPageSize(10);
		session.removeAttribute("page");
		session.setAttribute("page", "secretaire/rechRdv");
		modelmap.addAttribute("message",new Message());
		modelmap.addAttribute("liste", metier.listeUser(user.getId()));
		modelmap.put("pagedListHolder", pagedListHolder);
		return "listerdv";
	}
	
	@RequestMapping(value="/rechRdv1")
	@ResponseBody
	public String rechRdv1(String name, HttpServletRequest request,ModelMap modelmap,HttpSession session){
        Users user=(Users) session.getAttribute("user");
		
		if(user.getLogin()==null){
			return "redirect:../login";
		}
		List<Rdv> rdvs=metier.ListRdvpat(name);
		String ch="<table class='table table-striped table-bordered'>"
				+ "<tr><th>Photo</th><th>Debut</th><th>Fin</th><th>Motif"
				+ "</th><th>Patient</th><th> Medecin</th>"
			    + "<th>ACTION</th></tr>";
	
		
		for (Iterator iterator = rdvs.iterator(); iterator.hasNext();) {
			Rdv rdv = (Rdv) iterator.next();
			String tof="<p>Pas de photo</p>";
			String med=" ";
			if(rdv.getPatient().getMedecin()!=null)
				med="Dr "+rdv.getPatient().getMedecin().getPrenom()+" "+rdv.getPatient().getMedecin().getNom();

			if(rdv.getPatient().getPhoto()!=null)
				tof="<img width='100px' height='90px' src='photo?idPat="+rdv.getPatient().getId()+"'/>";
			ch+="<tr><td>"+tof+

				"</td><td>"+rdv.getStart()+
				"</td><td>"+rdv.getEnd()+
				"</td><td>"+rdv.getTitle()+
				"</td><td>"+rdv.getPatient().getPrenom()+" "+rdv.getPatient().getNom()+
				"</td><td>"+med+"</td>"+
				"<td id='action'><a style='font-size:18px;width:40%;height:100%;min-width:50px;' type='button' class='btn btn-default' href='afficherDossier?idPat="+rdv.getPatient().getId()+"'><span class='glyphicon glyphicon-eye-open'></span></a>"+ 
				"<a style='background:#0080ff;font-size:18px;width:40%;height:100%;min-width:50px;' type='button' class='btn btn-default' href='modifRdv?idRdv="+rdv.getId()+"'><span class='glyphicon glyphicon-edit'></span></a></td></tr>";		
					
		}
		
		return ch+"</table>";
	}
	
	@RequestMapping(value="/rechPatient")
	public String rechPatient(String name, HttpServletRequest request,ModelMap modelmap,HttpSession session){
		Users user=(Users) session.getAttribute("user");
		if(user.getLogin()==null){
			return "redirect:../login";
		}
		PagedListHolder<Patient> pagedListHolder=new PagedListHolder<Patient>(metier.ListPatient(name));
		int page=ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setPageSize(10);
		modelmap.put("pagedListHolder", pagedListHolder);
		session.removeAttribute("page");
		session.setAttribute("page", "secretaire/rechPatient");
		modelmap.addAttribute("message",new Message());
		modelmap.addAttribute("liste", metier.listeUser(user.getId()));
		return "listePatientSecr";
	}
	
	@RequestMapping(value="/rechPatient1")
	@ResponseBody
	public String rechPatient1(String name, HttpServletRequest request,ModelMap modelmap,HttpSession session){
        Users user=(Users) session.getAttribute("user");
		
		if(user.getLogin()==null){
			return "redirect:../login";
		}
		List<Patient> patient=metier.ListPatient(name);
		String ch="<table class='table table-striped table-bordered'>"
				+ "<tr>"
				+ "<th>Photo</th><th>Numero dossier</th><th>Nom</th><th>Prenom"
				+ "</th><th>Sexe</th><th>Date de Niassance</th>"
				+ "<th>Numero SS</th><th>Adresse</th><th>Telephone</th><th>Medecin</th>"
			    + "<th>ACTION</th>"
				+ "</tr>";
	
		
		for (Iterator iterator = patient.iterator(); iterator.hasNext();) {
			Patient patient2 = (Patient) iterator.next();
			String tof="<p>Pas de photo</p>";
			String med=" ";
			if(patient2.getMedecin()!=null)
				med="Dr "+patient2.getMedecin().getPrenom()+" "+patient2.getMedecin().getNom();

				
			if(patient2.getPhoto()!=null)
				tof="<img width='100px' height='90px' src='photo?idPat="+patient2.getId()+"'/>";
			ch+="<tr><td>"+tof+
				"</td><td>"+patient2.getDossier().getId()+
				"</td><td>"+patient2.getNom()+
				"</td><td>"+patient2.getPrenom()+
				"</td><td>"+patient2.getSexe()+
				"</td><td>"+patient2.getDatenais()+
				"</td><td>"+patient2.getNumSS()+
				"</td><td>"+patient2.getAdresse()+
				"</td><td>"+patient2.getTel()+"</td>"+
				"<td>"+med+"</td>"+
				"<td id='action'><a style='font-size:18px;width:40%;height:100%;min-width:50px;' type='button' class='btn btn-default' href='afficherDossier?idPat="+patient2.getId()+"'><span class='glyphicon glyphicon-eye-open'></span></a>"+ 
				"<a style='background:#0080ff;font-size:18px;width:40%;height:100%;min-width:50px;' type='button' class='btn btn-default' href='modifPatient?idPat="+patient2.getId()+"'><span class='glyphicon glyphicon-edit'></span></a></td></tr>";		
					
		}
		
		return ch+"</table>";
	}
	
	@RequestMapping(value="/listeRdv")
	public String listeRdv(HttpServletRequest request,ModelMap modelmap,HttpSession session){
		Users user=(Users) session.getAttribute("user");
		if(user.getLogin()==null){
			return "redirect:../login";
		}
		PagedListHolder<Rdv> pagedListHolder=new PagedListHolder<Rdv>(metier.ListRdv());

		int page=ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setPageSize(10);
		modelmap.put("pagedListHolder", pagedListHolder);
		modelmap.addAttribute("message",new Message());
		session.removeAttribute("page");
		session.setAttribute("page", "secretaire/listeRdv");
		modelmap.addAttribute("liste", metier.listeUser(user.getId()));
		modelmap.put("patients",metier.ListPatient());
		return "listerdv";
	}
	
	@RequestMapping(value="/indexSecretaire")
	public String indexSecretaire(Model model,HttpSession session){
		Users user=(Users) session.getAttribute("user");
		if(user.getLogin()==null){
			return "redirect:../login";
		}
		model.addAttribute("user",user);
		model.addAttribute("message",new Message());
		model.addAttribute("liste", metier.listeUser(user.getId()));
		session.removeAttribute("page");
		session.setAttribute("page", "secretaire/listeSecretaire");
		return "indexSecretaire";
	}
	
	
	@RequestMapping(value="/listePatientSecr")
	public String index(HttpServletRequest request,ModelMap modelmap,Model m,HttpSession session){
		Users user=(Users) session.getAttribute("user");
		if(user.getLogin()==null){
			return "redirect:../login";
		}
		PagedListHolder<Patient> pagedListHolder=new PagedListHolder<Patient>(metier.ListPatient());
		int page=ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setPageSize(10);
		modelmap.put("pagedListHolder", pagedListHolder);
		m.addAttribute("liste", metier.listeUser(user.getId()));
		modelmap.put("message", new Message());
		session.removeAttribute("page");
		session.setAttribute("page", "secretaire/listePatientSecr");
		modelmap.addAttribute("liste", metier.listeUser(user.getId()));
		return "listePatientSecr";
	}
	
	
	@RequestMapping(value="/afficherDossier")
	public String afficherDossier(int idPat,Model model,HttpSession session){
		Users user=(Users) session.getAttribute("user");
		if(user.getLogin()==null){
			return "redirect:../login";
		}
		model.addAttribute("user",user);
		model.addAttribute("visites",metier.ListVisite(idPat));
		model.addAttribute("rdvs",metier.ListRdv(idPat));
		model.addAttribute("idPat",idPat);
		model.addAttribute("idPat",idPat);
		model.addAttribute("patient",metier.getPatient(idPat));
		model.addAttribute("antecedants",metier.listAntecedants(idPat));
		model.addAttribute("certificats",metier.listCertificat(idPat));
    	model.addAttribute("ordonnances",metier.ListOrdonnance(idPat));
		model.addAttribute("message",new Message());
		model.addAttribute("liste", metier.listeUser(user.getId()));
		session.removeAttribute("page");
		session.setAttribute("page", "secretaire/afficherDossier");
		return "afficherdossier";
	}
	
	@RequestMapping(value="/saveRdv")
	public String saveRdv(@Valid Rdv rdv, BindingResult bindingResult, Model model,HttpSession session) throws IOException{ 
		Users user=(Users) session.getAttribute("user");
		if(user.getLogin()==null){
			return "redirect:../login";
		}
		model.addAttribute("user",user);	
		if(rdv.getId()>0){
				metier.ModifierRdv(rdv);
				return "redirect:/secretaire/afficherDossier?idPat="+rdv.getPatient().getId();
			}else{
				metier.AjouterRdv(rdv);
				return "redirect:/secretaire/afficherDossier?idPat="+rdv.getPatient().getId();
			}
			
	}
	
	@RequestMapping(value="/addRdv")
	public String addRdv(int idPat,Model model,HttpSession session) throws IOException{
		Users user=(Users) session.getAttribute("user");
		if(user.getLogin()==null){
			return "redirect:../login";
		}
		model.addAttribute("user",user);
		model.addAttribute("rdv",new Rdv());
		model.addAttribute("idPat",idPat);
		model.addAttribute("message",new Message());
		model.addAttribute("liste", metier.listeUser(user.getId()));
		session.removeAttribute("page");
		session.setAttribute("page", "secretaire/addRdv");
		return "rdv";
	}
	
	@RequestMapping(value="/modifRdv")
	public String editMedecin(int idRdv,Model model,HttpSession session){
		Rdv rdv=metier.ChercherRdv(idRdv);
		Users user=(Users) session.getAttribute("user");
		if(user.getLogin()==null){
			return "redirect:../login";
		}
		model.addAttribute("idPat",metier.ChercherRdv(idRdv).getPatient().getId());
		model.addAttribute("user",user);
		model.addAttribute("rdv",rdv);
		model.addAttribute("message",new Message());
		model.addAttribute("liste", metier.listeUser(user.getId()));
		return "rdv";
	}
	
	@RequestMapping(value="/suppRdv")
	public String suppRdv(int idRdv,Model model,HttpSession session){
		Users user=(Users) session.getAttribute("user");
		if(user.getLogin()==null){
			return "redirect:../login";
		}
		model.addAttribute("user",user);
		Patient p=metier.ChercherRdv(idRdv).getPatient();
		metier.SupprimerRdv(idRdv);
		model.addAttribute("visites",metier.ListVisite(idRdv));
		model.addAttribute("rdvs",metier.ListRdv(idRdv));
		model.addAttribute("message",new Message());
		model.addAttribute("liste", metier.listeUser(user.getId()));
		return "redirect:/secretaire/afficherDossier?idPat="+p.getId();
	}
	
	@RequestMapping(value="/addPatient")
	public String addPatient(Model model,HttpSession session) throws IOException{
		Users user=(Users) session.getAttribute("user");
		if(user.getLogin()==null){
			return "redirect:../login";
		}
		model.addAttribute("patient",new Patient());
		model.addAttribute("user",user);
		model.addAttribute("message",new Message());
		session.removeAttribute("page");
		session.setAttribute("page", "secretaire/addPatient");
		model.addAttribute("liste", metier.listeUser(user.getId()));
		return "patient";
	
	}
	
	@RequestMapping(value="/savePatient")
	public String savePatient(@Valid MultipartFile file,Dossier dos,Patient pat, BindingResult bindingResult, Model model,HttpSession session) throws IOException{ 
		Users user=(Users) session.getAttribute("user");
		Patient p=(Patient) session.getAttribute("p");
		if(user.getLogin()==null){
			return "redirect:../login";
		}
		model.addAttribute("user",user);
		if(!file.isEmpty()){
			pat.setPhoto(file.getBytes());
			pat.setNomPhoto(file.getOriginalFilename());
		}
		
		if(pat.getId()>0){
			if(p.getMedecin()!=null)
				pat.setMedecin(p.getMedecin());
			if(file.isEmpty() && p.getPhoto()!=null){
				pat.setPhoto(p.getPhoto());
				pat.setNomPhoto(p.getNomPhoto());
				session.removeAttribute("p");
			}
			metier.ModifierPatient(pat);
			return "redirect:listePatientSecr?idSecr="+user.getId();
		}else{
			dos=new Dossier();
			dos.setDatecreation(new Date());
			pat.setDossier(dos);
			metier.AjouterDossier(dos);
			metier.AjouterPatient(pat);
			return "redirect:listePatientSecr?idSecr="+user.getId();
		}		
	}
	
	@RequestMapping(value="/modifPatient")
	public String modifPatient(int idPat,Model model,HttpSession session){
		Users user=(Users) session.getAttribute("user");
		if(user.getLogin()==null){
			return "redirect:../login";
		}
		model.addAttribute("user",user);
		Patient patient=metier.getPatient(idPat);
		model.addAttribute("patient",patient);
		model.addAttribute("message",new Message());
		model.addAttribute("liste", metier.listeUser(user.getId()));
		session.removeAttribute("page");
		session.setAttribute("page", "secretaire/modifPatient");
		session.removeAttribute("page");
		session.setAttribute("p", metier.getPatient(idPat));
		return "patient";
	}
	
	@RequestMapping(value="/modifVisite")
	public String modifVisite(int idvisite,Model model,HttpSession session){
		Users user=(Users) session.getAttribute("user");
		if(user.getLogin()==null){
			return "redirect:../login";
		}
		model.addAttribute("user",user);
		Visite visite=metier.getVisite(idvisite);
		model.addAttribute("visite",visite);
		model.addAttribute("idPat",visite.getPatient().getId());
		model.addAttribute("message",new Message());
		model.addAttribute("liste", metier.listeUser(user.getId()));
		session.removeAttribute("page");
		session.setAttribute("page", "secretaire/modifVisite");
		return "recette";
	}
	
	@RequestMapping(value="/saveVisite")
	public String saveVisite(@Valid Visite visite, BindingResult bindingResult, Model model,HttpSession session) throws IOException{ 
		Users user=(Users) session.getAttribute("user");
		if(user.getLogin()==null){
			return "redirect:../login";
		}
		model.addAttribute("user",user);
		metier.ModifierVisite(visite);
		return "redirect:afficherDossier?idPat="+visite.getPatient().getId();			
	}
	
	@RequestMapping(value="/savePaiement")
	public String savePaiement(@Valid Visite visite, BindingResult bindingResult, Model model,HttpSession session) throws IOException{ 
		Users user=(Users) session.getAttribute("user");
		if(user.getLogin()==null){
			return "redirect:../login";
		}
		model.addAttribute("user",user);	
		if(visite.getId()>0)
			metier.ModifierVisite(visite);	
		
		return "redirect:afficherDossier?idPat="+visite.getPatient().getId();
			
	}
	
	@RequestMapping(value="/addPaiement")
	public String addPaiement(int idVisite,Model model,HttpSession session) throws IOException{
		Users user=(Users) session.getAttribute("user");
		if(user.getLogin()==null){
			return "redirect:../login";
		}
		model.addAttribute("user",user);
		model.addAttribute("visite",metier.getVisite(idVisite));
		model.addAttribute("idVisite",idVisite);
		model.addAttribute("idPat",metier.getVisite(idVisite).getPatient().getId());
		model.addAttribute("message",new Message());
		model.addAttribute("liste", metier.listeUser(user.getId()));
		session.removeAttribute("page");
		session.setAttribute("page", "secretaire/addPaiement");
		return "paiement";
	
	}
	
	@RequestMapping(value="/affecterMedecin")
	public String affecterMedecin(int idPat,Model model,HttpSession session) throws IOException{
		Users user=(Users) session.getAttribute("user");
		if(user.getLogin()==null){
			return "redirect:../login";
		}
		model.addAttribute("user",user);
		model.addAttribute("patient",metier.getPatient(idPat));
		model.addAttribute("medecin",metier.ListMedecin());
		model.addAttribute("idPat",idPat);
		model.addAttribute("message",new Message());
		model.addAttribute("liste", metier.listeUser(user.getId()));
		session.removeAttribute("page");
		session.setAttribute("page", "secretaire/affecterMedecin");
		session.setAttribute("patPhoto",metier.getPatient(idPat));
		return "affectation";	
	}
	
	@RequestMapping(value="/savePatientMedecin")
	public String savePatientMedecin(@Valid Patient pat, BindingResult bindingResult, Model model,HttpSession session) throws IOException{ 
		Users user=(Users) session.getAttribute("user");
		Patient patPhoto=(Patient)session.getAttribute("patPhoto");
		pat.setPhoto(patPhoto.getPhoto());
		pat.setNomPhoto(patPhoto.getNomPhoto());
		if(user.getLogin()==null){
			return "redirect:../login";
		}
		model.addAttribute("user",user);
		if(pat.getId()>0){
			metier.ModifierPatient(pat);
			return "redirect:afficherDossier?idPat="+pat.getId();
		}
		return "redirect:afficherDossier?idPat="+pat.getId();
	}
	@RequestMapping(value="photoPSecr",produces=MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] photoProfil(int idSecr) throws IOException{
		Secretaire m=metier.chercherSecretaire(idSecr);
			return IOUtils.toByteArray(new ByteArrayInputStream(m.getPhoto()));
	}
	@RequestMapping(value="photo",produces=MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] photoPatient(int idPat) throws IOException{
		Patient p=metier.getPatient(idPat);
			return IOUtils.toByteArray(new ByteArrayInputStream(p.getPhoto()));
	}
}
