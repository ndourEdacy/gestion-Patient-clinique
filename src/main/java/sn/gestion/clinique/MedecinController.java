package sn.gestion.clinique;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.http.MediaType;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.itextpdf.awt.geom.misc.Messages;

import GeneratePdf.CertificatPdf;
import GeneratePdf.OrdonnancePdf;
import sn.gestion.clinique.entites.Antecedant;
import sn.gestion.clinique.entites.Bilan;
import sn.gestion.clinique.entites.Certificat;
import sn.gestion.clinique.entites.Dossier;
import sn.gestion.clinique.entites.Medecin;
import sn.gestion.clinique.entites.Message;
import sn.gestion.clinique.entites.Ordonnance;
import sn.gestion.clinique.entites.Paiement;
import sn.gestion.clinique.entites.Patient;
import sn.gestion.clinique.entites.Rdv;
import sn.gestion.clinique.entites.Users;
import sn.gestion.clinique.entites.Visite;
import sn.gestion.clinique.metier.IMedecinMetier;

@Controller
@SessionAttributes("user")
@RequestMapping(value="medecin")
public class MedecinController {
	@Autowired
	private IMedecinMetier metier;
	
	@RequestMapping(value = "/montaAgenda", method = RequestMethod.GET)
	public ModelAndView MontaAgenda() {
		 
		ModelAndView mv = new ModelAndView("AgendaEventos");
		
		return mv;
	}
	
	@RequestMapping(value = "/getEventos.json", method = RequestMethod.GET)	
	public @ResponseBody List<Rdv> GetEventos(){

		List<Rdv> eventos=metier.ListRdv();	
		return eventos;
	}
		
	@RequestMapping(method=RequestMethod.GET)
	public String indexTest(HttpServletRequest request,ModelMap modelmap){
		PagedListHolder<Patient> pagedListHolder=new PagedListHolder<Patient>(metier.getPatient());
		int page=ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setPageSize(2);
		modelmap.addAttribute("message",new Message());
		modelmap.put("pagedListHolder", pagedListHolder);
		return "indexTest";
	}
	
	@RequestMapping(value="/generatepdfCert")
	public ModelAndView ModelAndViewCertificat(int idCert,HttpServletRequest request,HttpServletResponse response,HttpSession session){		
		Certificat cert=metier.getCertificat(idCert);
		return new ModelAndView(new CertificatPdf(),"certificat",cert);
	}
	
	@RequestMapping(value="/generatepdfOrd")
	public ModelAndView ModelAndView(int idOrd,HttpServletRequest request,HttpServletResponse response,HttpSession session){		
		Ordonnance ord=metier.getOrdonnance(idOrd);
		return new ModelAndView(new OrdonnancePdf(),"ordonnance",ord);
	}
	
	@RequestMapping(value="/modifProfil")
	public String modifProfil(@Valid Message message,Model model,HttpSession session){
		Users user=(Users) session.getAttribute("user");
		if(user.getLogin()=="rien"){
			return "redirect:../login";
		}
		Medecin medecin=metier.chercherMedecin(user.getId());
		model.addAttribute("medecin",medecin);
		model.addAttribute("user",medecin);
		model.addAttribute("specialite",metier.ListSpecialite());
		model.addAttribute("liste", metier.listeUser(user.getId()));
		model.addAttribute("message", new Message());
		return "modifProfilMedecin";
	}
	
	@RequestMapping(value="/saveModifProfil")
	public String saveModifProfil(@Valid MultipartFile file, Medecin medecin, BindingResult bindingResult, Model model,HttpSession session) throws IOException{
		Users user=(Users) session.getAttribute("user");
		if(user.getLogin()=="rien"){
			return "redirect:../login";
		}
		String nomphoto=user.getNomPhoto();
		byte[] photo=user.getPhoto();
		if(!file.isEmpty()){
			medecin.setPhoto(file.getBytes());
			medecin.setNomPhoto(file.getOriginalFilename());
			metier.ModifierMedecin(medecin);
		}else{
			medecin.setPhoto(photo);
			medecin.setNomPhoto(nomphoto);
			if(medecin.getId()>0){
				metier.ModifierMedecin(medecin);
			}
		}
		session.removeAttribute("page");
		session.setAttribute("page", "medecin/saveModifProdfil");
		model.addAttribute("message",new Message());
		model.addAttribute("liste", metier.listeUser(user.getId()));
		return "redirect:listePatients";
	}
	
	@PostMapping(value="/rechRdv2")
	@ResponseBody
	public String rechRdv2(String name,HttpSession session){
		Users user=(Users) session.getAttribute("user");
		
		if(user.getLogin()==null){
			return "redirect:../login";
		}
		List<Rdv> rdvs=metier.searchRdv(name,user.getId());
		String ch="<table class='table table-striped table-bordered'>"
				+ "<tr >"
				+ "<th>Photo</th><th>Debut</th><th>Fin"
				+ "</th><th>Motif</th><th> Patient</th>"
				+ "<th>Action</th>"
				+ "</tr>";
	
		
		for (Iterator iterator = rdvs.iterator(); iterator.hasNext();) {
			Rdv rdv = (Rdv) iterator.next();
			String tof="<p>Pas de photo</p>";
			if(rdv.getPatient().getPhoto()!=null)
				tof="<img width='100px' height='90px' src='photoP?idPat="+rdv.getPatient().getId()+"'/>";
				ch+="<tr><td>"+tof+"</td>"+ 
				"<td>"+rdv.getStart()+
				"</td><td>"+rdv.getEnd()+
				"</td><td>"+rdv.getTitle()+
				"</td><td>"+rdv.getPatient().getPrenom()+" "+rdv.getPatient().getNom()+
				"</td><td id='action'><a style='font-size:20px;width:70%;height:100%;' type='button' class='btn btn-default' href='DossierPatient?idPat="+rdv.getPatient().getId()+"'><span class='glyphicon glyphicon-eye-open'></span></a> </td></tr>";
		}
		
		return ch+"</table>";
	}

	
	@RequestMapping(value="/rechRdv")
	public String rechRdv(String name, HttpServletRequest request,ModelMap modelmap,HttpSession session){
		Users user=(Users) session.getAttribute("user");
		if(user.getLogin()==null){
			return "redirect:../login";
		}
		PagedListHolder<Rdv> pagedListHolder=new PagedListHolder<Rdv>(metier.searchRdv(name,user.getId()));
		int page=ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setPageSize(10);
		modelmap.put("pagedListHolder", pagedListHolder);
		session.removeAttribute("page");
		session.setAttribute("page", "medecin/rechRdv");
		modelmap.addAttribute("message",new Message());
		modelmap.addAttribute("liste", metier.listeUser(user.getId()));
		return "listeRdvMedecin";
	}
	
	@RequestMapping(value="/rechPatient")
	public String rechPatient(String name, HttpServletRequest request,ModelMap modelmap,HttpSession session){
		Users user=(Users) session.getAttribute("user");
		if(user.getLogin()==null){
			return "redirect:../login";
		}
		PagedListHolder<Patient> pagedListHolder=new PagedListHolder<Patient>(metier.searchPatient(name, user.getId()));
		List<Patient> patient=metier.getPatient();
		int page=ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setPageSize(10);
		modelmap.put("pagedListHolder", pagedListHolder);
		session.removeAttribute("page");
		session.setAttribute("page", "medecin/rechPatient");
		modelmap.addAttribute("message",new Message());
		modelmap.addAttribute("liste", metier.listeUser(user.getId()));
		return "listePatientMedecin";
	}
	@PostMapping(value="/rechPatient1")
	@ResponseBody
	public String rechPatient1(String name,HttpSession session){
		Users user=(Users) session.getAttribute("user");
		
		if(user.getLogin()==null){
			return "redirect:../login";
		}
		List<Patient> patient=metier.searchPatient(name,user.getId());
		String ch="<table class='table table-striped table-bordered'>"
				+ "<tr >"
				+ "<th>Photo</th><th>Numero dossier</th><th>Nom</th><th>Prenom"
				+ "</th><th>Sexe</th><th>Date de Niassance</th>"
				+ "<th>Lieu de naissance</th><th>Poid</th>"
			    +"<th>Taille</th><th>Groupe sanguin</th><th>Numero SS</th>"
			    + "<th>Adresse</th><th>Telephone</th><th>ACTION</th>"
				+ "</tr>";
	
		
		for (Iterator iterator = patient.iterator(); iterator.hasNext();) {
			Patient patient2 = (Patient) iterator.next();
			String tof="<p>Pas de photo</p>";
			if(patient2.getPhoto()!=null)
				tof="<img width='100px' height='90px' src='photoP?idPat="+patient2.getId()+"'/>";
			ch+="<tr><td>"+tof+"</td>"+ 
				"<td>"+patient2.getDossier().getId()+
				"</td><td>"+patient2.getNom()+
				"</td><td>"+patient2.getPrenom()+
				"</td><td>"+patient2.getSexe()+
				"</td><td>"+patient2.getDatenais()+
				"</td><td>"+patient2.getLieu()+
				"</td><td>"+patient2.getPoid()+
				"</td><td>"+patient2.getTaille()+
				"</td><td>"+patient2.getGroupeS()+
				"</td><td>"+patient2.getNumSS()+
				"</td><td>"+patient2.getAdresse()+
				"</td><td>"+patient2.getTel()+
				"</td><td id='action'><a  style='font-size:20px;width:70%;height:100%;' type='button' class='btn btn-default' href='DossierPatient?idPat="+patient2.getId()+"'><span class='glyphicon glyphicon-eye-open'></span></a> </td></tr>";
					
					
		}
		
		return ch+"</table>";
	}

	@RequestMapping(value="/listeRdv",method=RequestMethod.GET)
	public String listeRdv(HttpServletRequest request,ModelMap modelmap,HttpSession session){
		Users user=(Users) session.getAttribute("user");
		if(user.getLogin()==null){
			return "redirect:../login";
		}
		PagedListHolder<Rdv> pagedListHolder=new PagedListHolder<Rdv>(metier.ListRdvMed(user.getId()));

		int page=ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setPageSize(10);
		modelmap.put("pagedListHolder", pagedListHolder);
		modelmap.put("patients",metier.getPatient());
		modelmap.addAttribute("message",new Message());
		modelmap.addAttribute("liste", metier.listeUser(user.getId()));
		session.removeAttribute("page");
		session.setAttribute("page", "medecin/listeRdv");
		return "listeRdvMedecin";
	}
	
	@RequestMapping(value="/indexMedecin")
	public String indexSecretaire(Model model,HttpSession session){
		Users user=(Users) session.getAttribute("user");
		if(user.getLogin()==null){
			return "redirect:../login";
		}
		model.addAttribute("user",user); 
		session.removeAttribute("page");
		session.setAttribute("page", "medecin/indexMedecin");
		model.addAttribute("message",new Message());
		model.addAttribute("liste", metier.listeUser(user.getId()));
		return "indexMedecin";
	}
	
	@RequestMapping(value="/listePatients",method=RequestMethod.GET)
	public String index(HttpServletRequest request,Model m,ModelMap modelmap,HttpSession session){
		Users user=(Users) session.getAttribute("user");
		if(user.getLogin()==null){
			return "redirect:../login";
		}
		PagedListHolder<Patient> pagedListHolder=new PagedListHolder<Patient>(metier.ListPatient(user.getId()));
		int page=ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		modelmap.addAttribute("user",user);
		session.removeAttribute("page");
		session.setAttribute("page", "medecin/listePatients");
		modelmap.addAttribute("message",new Message());
		modelmap.addAttribute("liste", metier.listeUser(user.getId()));
		m.addAttribute("user_con",metier.listeUser(19));
		pagedListHolder.setPageSize(10);
		modelmap.put("pagedListHolder", pagedListHolder);
		return "listePatientMedecin";
	}
	
	@RequestMapping(value="/DossierPatient")
	public String afficherDossier(int idPat,Model model,HttpSession session){
		Users user=(Users) session.getAttribute("user");
		if(user.getLogin()==null){
			return "redirect:../login";
		}
		model.addAttribute("user",user);
		model.addAttribute("visites",metier.ListVisite(idPat));
		model.addAttribute("rdvs",metier.ListRdv(idPat));
		model.addAttribute("idPat",idPat);
		model.addAttribute("tmp",0);
		model.addAttribute("paiements",metier.ListPaiement());
		model.addAttribute("antecedants",metier.listAntecedants(idPat));
		model.addAttribute("certificat",metier.listCertificat(idPat));
    	model.addAttribute("ordonnances",metier.ListOrdonnance(idPat));
    	model.addAttribute("patient",metier.getPatient(idPat));
    	model.addAttribute("bilan",metier.ListBilan(idPat));
    	model.addAttribute("message",new Message());
		model.addAttribute("liste", metier.listeUser(user.getId()));
		session.removeAttribute("page");
		session.setAttribute("page", "medecin/DossierPatient?idPat="+idPat);
		return "DossierPatient";
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
		return "patient";
	}
	@RequestMapping(value="/modifRecette")
	public String modifRecette(int idvisite,Model model,HttpSession session){
		Users user=(Users) session.getAttribute("user");
		if(user.getLogin()==null){
			return "redirect:../login";
		}
		model.addAttribute("user",user);
		Visite visite=metier.getVisite(idvisite);
		model.addAttribute("visite",visite);
		session.removeAttribute("page");
		session.setAttribute("page", "medecin/modifRecette");
		model.addAttribute("message",new Message());
		model.addAttribute("liste", metier.listeUser(user.getId()));
		return "recette";
	}
	@RequestMapping(value="/saveRecette")
	public String saveRecette(@Valid Visite visite, BindingResult bindingResult, Model model,HttpSession session) throws IOException{ 
		Users user=(Users) session.getAttribute("user");
		if(user.getLogin()==null){
			return "redirect:../login";
		}
		model.addAttribute("user",user);
		session.removeAttribute("page");
		session.setAttribute("page", "medecin/saveRecette");
		model.addAttribute("message",new Message());
		model.addAttribute("liste", metier.listeUser(user.getId()));
		metier.ModifierVisite(visite);
		return "redirect:afficherDossier?idPat="+visite.getPatient().getId();			
	}
	
	
	@RequestMapping(value="/addVisite")
	public String addVisite(int idPat,Model model,HttpSession session) throws IOException{
		Users user=(Users) session.getAttribute("user");
		if(user.getLogin()==null){
			return "redirect:../login";
		}
		model.addAttribute("user",user);
		model.addAttribute("visite",new Visite());
		model.addAttribute("idPat",idPat);
		return "visite";
	
	}

	@RequestMapping(value="/suppVisite")
	public String suppVisite(int idVisite,Model model,HttpSession session){
		Users user=(Users) session.getAttribute("user");
		if(user.getLogin()==null){
			return "redirect:../login";
		}
		model.addAttribute("user",user);
		Visite v=metier.getVisite(idVisite);
		metier.SupprimerVisite(idVisite);
		return "redirect:/medecin/DossierPatient?idPat="+v.getPatient().getId();
	}
	@RequestMapping(value="/saveVisite")
	public String saveVisite(@Valid Visite visite, BindingResult bindingResult, Model model,HttpSession session) throws IOException{ 
		Users user=(Users) session.getAttribute("user");
		if(user.getLogin()==null){
			return "redirect:../login";
		}
		model.addAttribute("user",user);	
		if(visite.getId()>0){
				metier.ModifierVisite(visite);
				return "redirect:/medecin/DossierPatient?idPat="+visite.getPatient().getId();
			}else{
				metier.AjouterVisite(visite);
				return "redirect:/medecin/DossierPatient?idPat="+visite.getPatient().getId();
			}
			
	}

	@RequestMapping(value="/modifVisite")
	public String editVisite(int idVisite,Model model,HttpSession session){
		Visite visite=metier.getVisite(idVisite);
		Users user=(Users) session.getAttribute("user");
		if(user.getLogin()==null){
			return "redirect:../login";
		}
		model.addAttribute("user",user);
		model.addAttribute("visite",visite);
		model.addAttribute("idPat",visite.getPatient().getId());
		return "visite";
	}
	
	@RequestMapping(value="/addAntecedant")
	public String addAntecedant(int idPat,Model model,HttpSession session) throws IOException{
		Users user=(Users) session.getAttribute("user");
		if(user.getLogin()==null){
			return "redirect:../login";
		}
		model.addAttribute("user",user);
		model.addAttribute("antecedant",new Antecedant());
		model.addAttribute("idPat",idPat);
		return "antecedant";
	
	}

	@RequestMapping(value="/suppAnt")
	public String suppAntecedant(int idAnt,Model model,HttpSession session){
		Users user=(Users) session.getAttribute("user");
		if(user.getLogin()==null){
			return "redirect:../login";
		}
		model.addAttribute("user",user);
		Antecedant ant=metier.getAntecedant(idAnt);
		metier.SupprimerAntecedant(idAnt);
		return "redirect:/medecin/DossierPatient?idPat="+ant.getPatient().getId();
	}
	@RequestMapping(value="/saveAntecedant")
	public String asaveAntecedant(@Valid Antecedant ant, BindingResult bindingResult, Model model,HttpSession session) throws IOException{ 
		Users user=(Users) session.getAttribute("user");
		if(user.getLogin()==null){
			return "redirect:../login";
		}
		model.addAttribute("user",user);	
		if(ant.getId()>0){
				metier.ModifierAntecdant(ant);
				return "redirect:/medecin/DossierPatient?idPat="+ant.getPatient().getId();
			}else{
				metier.ajouterAntecedant(ant);
				return "redirect:/medecin/DossierPatient?idPat="+ant.getPatient().getId();
			}
			
	}

	@RequestMapping(value="/modifAnt")
	public String editAntecedant(int idAnt,Model model,HttpSession session){
		Antecedant antecedant=metier.getAntecedant(idAnt);
		Users user=(Users) session.getAttribute("user");
		if(user.getLogin()==null){
			return "redirect:../login";
		}
		model.addAttribute("user",user);
		model.addAttribute("idPat",antecedant.getPatient().getId());
		model.addAttribute("antecedant",antecedant);
		return "antecedant";
	}
	
	@RequestMapping(value="/addOrdonnance")
	public String addOrdonnance(int idPat,Model model,HttpSession session) throws IOException{
		Users user=(Users) session.getAttribute("user");
		if(user.getLogin()==null){
			return "redirect:../login";
		}
		model.addAttribute("user",user);
		model.addAttribute("ordonnance",new Ordonnance());
		model.addAttribute("idPat",idPat);
		return "ordonnance";
	
	}

	@RequestMapping(value="/suppOrdonnance")
	public String suppOrdonnance(int idOrd,Model model,HttpSession session){
		Users user=(Users) session.getAttribute("user");
		if(user.getLogin()==null){
			return "redirect:../login";
		}
		model.addAttribute("user",user);
		Ordonnance ord=metier.getOrdonnance(idOrd);
		metier.SupprimerOrdonnance(idOrd);
		return "redirect:/medecin/DossierPatient?idPat="+ord.getPatient().getId();
	}
	
	@RequestMapping(value="/saveOrdonnance")
	public String saveOrdonnance(@Valid Ordonnance ord, BindingResult bindingResult, Model model,HttpSession session) throws IOException{ 
		Users user=(Users) session.getAttribute("user");
		if(user.getLogin()==null){
			return "redirect:../login";
		}
		model.addAttribute("user",user);	
		if(ord.getId()>0){
				metier.ModifierOrdonnance(ord);
				return "redirect:/medecin/DossierPatient?idPat="+ord.getPatient().getId();
			}else{
				metier.ajouterOrdonnance(ord);
				return "redirect:/medecin/DossierPatient?idPat="+ord.getPatient().getId();
			}
			
	}

	@RequestMapping(value="/modifOrdonnance")
	public String editOrdonnance(int idOrd,Model model,HttpSession session){
		Ordonnance ord=metier.getOrdonnance(idOrd);
		Users user=(Users) session.getAttribute("user");
		if(user.getLogin()==null){
			return "redirect:../login";
		}
		model.addAttribute("user",user);
		model.addAttribute("ordonnance",ord);
		model.addAttribute("idPat",ord.getPatient().getId());
		return "ordonnance";
	}
	
	@RequestMapping(value="/addCertificat")
	public String addCertificat(int idPat,Model model,HttpSession session) throws IOException{
		Users user=(Users) session.getAttribute("user");
		if(user.getLogin()==null){
			return "redirect:../login";
		}
		model.addAttribute("user",user);
		model.addAttribute("certificat",new Certificat());
		model.addAttribute("idPat",idPat);
		return "certificat";
	
	}

	@RequestMapping(value="/suppCertificat")
	public String suppCertificat(int idCert,Model model,HttpSession session){
		Users user=(Users) session.getAttribute("user");
		if(user.getLogin()==null){
			return "redirect:../login";
		}
		model.addAttribute("user",user);
		Certificat cert=metier.getCertificat(idCert);
		metier.SupprimerCertificat(idCert);
		return "redirect:/medecin/DossierPatient?idPat="+cert.getPatient().getId();
	}
	
	@RequestMapping(value="/saveCertificat")
	public String saveCertificat(@Valid Certificat cert, BindingResult bindingResult, Model model,HttpSession session) throws IOException{ 
		Users user=(Users) session.getAttribute("user");
		if(user.getLogin()==null){
			return "redirect:../login";
		}
		model.addAttribute("user",user);	
		if(cert.getId()>0){
				metier.ModifierCertificat(cert);
				return "redirect:/medecin/DossierPatient?idPat="+cert.getPatient().getId();
			}else{
				metier.AjouterCertificat(cert);
				return "redirect:/medecin/DossierPatient?idPat="+cert.getPatient().getId();
			}
			
	}

	@RequestMapping(value="/modifCertificat")
	public String modifCertificat(int idCert,Model model,HttpSession session){
		Certificat certificat=metier.getCertificat(idCert);
		Users user=(Users) session.getAttribute("user");
		if(user.getLogin()==null){
			return "redirect:../login";
		}
		model.addAttribute("user",user);
		model.addAttribute("certificat",certificat);
		model.addAttribute("idPat",certificat.getPatient().getId());
		return "certificat";
	}
	
	@RequestMapping(value="/saveBilan")
	public String saveBilan(@Valid Bilan bilan, BindingResult bindingResult, Model model,HttpSession session) throws IOException{ 
		Users user=(Users) session.getAttribute("user");
		if(user.getLogin()==null){
			return "redirect:../login";
		}
		model.addAttribute("user",user);	
		if(bilan.getId()>0){
				metier.ModifierBilan(bilan);
				return "redirect:/medecin/DossierPatient?idPat="+bilan.getPatient().getId();
		}else{
				metier.ajouterBilan(bilan);
				return "redirect:/medecin/DossierPatient?idPat="+bilan.getPatient().getId();
		}
			
	}
	
	@RequestMapping(value="/addBilan")
	public String addBilan(int idPat,Model model,HttpSession session) throws IOException{
		Users user=(Users) session.getAttribute("user");
		if(user.getLogin()==null){
			return "redirect:../login";
		}
		model.addAttribute("user",user);
		model.addAttribute("bilan",new Bilan());
		model.addAttribute("idPat",idPat);
		return "bilan";
	}
	
	@RequestMapping(value="/modifBilan")
	public String modifBilan(int idBil,Model model,HttpSession session){
		Bilan bilan=metier.getBilan(idBil);
		Users user=(Users) session.getAttribute("user");
		if(user.getLogin()==null){
			return "redirect:../login";
		}
		model.addAttribute("user",user);
		model.addAttribute("bilan",bilan);
		model.addAttribute("idPat",bilan.getPatient().getId());
		return "bilan";
	}
	
	@RequestMapping(value="/suppBilan")
	public String suppBilan(int idBil,Model model,HttpSession session){
		Users user=(Users) session.getAttribute("user");
		if(user.getLogin()==null){
			return "redirect:../login";
		}
		model.addAttribute("user",user);
		Bilan bil=metier.getBilan(idBil);
		metier.SupprimerBilan(idBil);
		return "redirect:/medecin/DossierPatient?idPat="+bil.getPatient().getId();
	}
	@RequestMapping(value="photoPMed",produces=MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] photoProfil(int idMed) throws IOException{
		Medecin m=metier.chercherMedecin(idMed);
			return IOUtils.toByteArray(new ByteArrayInputStream(m.getPhoto()));
	}
	@RequestMapping(value="photoP",produces=MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] photoProfilPat(int idPat) throws IOException{
		Patient p=metier.getPatient(idPat);
			return IOUtils.toByteArray(new ByteArrayInputStream(p.getPhoto()));
	}
}
