package sn.gestion.clinique;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import sn.gestion.clinique.entites.Administrateur;
import sn.gestion.clinique.entites.Infirmier;
import sn.gestion.clinique.entites.Medecin;
import sn.gestion.clinique.entites.Message;
import sn.gestion.clinique.entites.Rdv;
import sn.gestion.clinique.entites.Secretaire;
import sn.gestion.clinique.entites.Users;
import sn.gestion.clinique.metier.IAdminMetier;

@Controller
@RequestMapping(value="/adminPersonnel")
@SessionAttributes("user")
public class AdminControllerAdministrateur {
	@Autowired
	private IAdminMetier metier;
	
	@RequestMapping(value="/indexAdmin")
	public String index( HttpSession session,Model model) throws IOException{
		Users user=(Users) session.getAttribute("user");
		if(user.getLogin()=="rien"){
			return "redirect:../login";
		}
		model.addAttribute("message",new Message());
		model.addAttribute("liste", metier.listeUser(user.getId()));
		session.removeAttribute("page");
		session.setAttribute("page", "adminPersonnel/indexAdmin");
		return "medecin";
	}
	@RequestMapping(value="/saveModifProfil")
	public String saveModifProfil(@Valid MultipartFile file,Administrateur admin, BindingResult bindingResult, Model model,HttpSession session) throws IOException{
		Users user=(Users) session.getAttribute("user");
		Administrateur ad=(Administrateur)session.getAttribute("adm");
		if(user.getLogin()=="rien"){
			return "redirect:../login";
		}
		String nomphoto=ad.getNomPhoto();
		byte[] photo=ad.getPhoto();
		if(!file.isEmpty()){
			admin.setPhoto(file.getBytes());
			admin.setNomPhoto(file.getOriginalFilename());
			metier.ModifierAdministrateur(admin);
		}else{
			admin.setPhoto(photo);
			admin.setNomPhoto(nomphoto);
			if(admin.getId()>0){
				metier.ModifierAdministrateur(admin);
			}
		}
		return "medecin";
	}
	@RequestMapping(value="/modifProfilAdmin")
	public String modifProfil(Model model,HttpSession session){
		Users user=(Users) session.getAttribute("user");
		if(user.getLogin()=="rien")
			return "redirect:../login";
		
		Administrateur m=metier.getAdministrateur(user.getId());
		model.addAttribute("administrateur",m);
		model.addAttribute("user",m);
		session.setAttribute("adm",m);
		return "modifProfilAdmin";
	}
	
	@RequestMapping(value="/addAdministrateur")
	public String indexAdministrateur(Model model,HttpSession session){
		Users user=(Users) session.getAttribute("user");
		if(user.getLogin()=="rien"){
			return "redirect:../login";
		}
		model.addAttribute("message",new Message());
		model.addAttribute("liste", metier.listeUser(user.getId()));
		session.removeAttribute("page");
		session.setAttribute("page", "adminPersonnel/addAdministrateur");
		model.addAttribute("administrateur",new Administrateur());
		model.addAttribute("administrateurs",metier.ListAdministrateur());
		model.addAttribute("user",user);
		return "administrateur";
	}
	
	@RequestMapping(value="/saveAdministrateur")
	public String saveAdministrateur(@Valid MultipartFile file, Administrateur admin, BindingResult bindingResult, Model model,HttpSession session) throws IOException{
		Users user=(Users) session.getAttribute("user");
		Administrateur adm=(Administrateur)session.getAttribute("adm");
		byte [] photo=null;
		String nomPhoto=null;
		if(admin.getId()>0 && adm.getPhoto()!=null){
			photo=adm.getPhoto();
			nomPhoto=adm.getNomPhoto();
		}
		if(user.getLogin()=="rien"){
			return "redirect:../login";
		}
		if(bindingResult.hasErrors()){
			return "administrateur";
		}
		if(!file.isEmpty()){
			admin.setPhoto(file.getBytes());
			admin.setNomPhoto(file.getOriginalFilename());
			if(admin.getId()>0){
				metier.ModifierAdministrateur(admin);	
			}
			else{
				metier.ajouterAdministrateur(admin);
			}
		}
		if(file.isEmpty()){
			if(admin.getId()>0){
				admin.setPhoto(photo);
				admin.setNomPhoto(nomPhoto);
				metier.ModifierAdministrateur(admin);	
			}
			else{
				metier.ajouterAdministrateur(admin);
			}
		}
		model.addAttribute("administrateurs",metier.ListAdministrateur());
		model.addAttribute("message",new Message());
		model.addAttribute("liste", metier.listeUser(user.getId()));
		 return "redirect:../adminPersonnel/addAdministrateur";
	}
	
	@RequestMapping(value="/modifAdministrateur")
	public String editAdministrateur(int idAdmin,Model model,HttpSession session){
		Users user=(Users) session.getAttribute("user");
		if(user.getLogin()=="rien"){
			return "redirect:../login";
		}
		Administrateur m=metier.getAdministrateur(idAdmin);
		model.addAttribute("administrateur",m);
		model.addAttribute("administrateurs",metier.ListAdministrateur());
		model.addAttribute("services",metier.ListService());
		session.removeAttribute("adm");
		model.addAttribute("message",new Message());
		model.addAttribute("liste", metier.listeUser(user.getId()));
		session.setAttribute("adm",m);
		return "administrateur";

	}
	@RequestMapping(value="photoAdmin",produces=MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] photoProfil(int idAdmin) throws IOException{
		Administrateur m=metier.getAdministrateur(idAdmin);
			return IOUtils.toByteArray(new ByteArrayInputStream(m.getPhoto()));
	}
	@RequestMapping(value="/suppAdministrateur")
	public String delMedecin(int idAdmin,Model model,HttpSession session){
		Users user=(Users) session.getAttribute("user");
		if(user.getLogin()==null){
			return "redirect:../login";
		}
		metier.SupprimerAdministrateur(idAdmin);
		model.addAttribute("administrateurs",metier.ListAdministrateur());
		model.addAttribute("services",metier.ListService());
		 return "redirect:../adminPersonnel/addAdministrateur";
	}
}
