
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
import org.springframework.web.multipart.MultipartFile;

	import sn.gestion.clinique.entites.*;
import sn.gestion.clinique.metier.IAdminMetier;

	@Controller
	@RequestMapping(value="/adminPersonnel")
	public class AdminControllerSecretaire {
			@Autowired
			private IAdminMetier metier;
			@RequestMapping(value="/saveSecretaire")
			public String saveSecretaire(@Valid MultipartFile file,Secretaire u, BindingResult bindingResult, Model model,HttpSession session) throws IOException{
				Users user=(Users) session.getAttribute("user");
				Secretaire sec=(Secretaire)session.getAttribute("sec");
				byte [] photo=null;
				String nomPhoto=null;
				if(u.getId()>0 && sec.getPhoto()!= null){
					photo=sec.getPhoto();
					nomPhoto=sec.getNomPhoto();
				}
				if(user.getLogin()==null){
					return "redirect:../login";
				}
				if(bindingResult.hasErrors()){
					model.addAttribute("services",metier.ListService());
					return "secretaire";
				}
				if(!file.isEmpty()){
					u.setPhoto(file.getBytes());
					u.setNomPhoto(file.getOriginalFilename());
					if(u.getId()>0){
						metier.ModifierSecretaire(u);	
					}
					else{
						metier.AjouterSecretaire(u);
					}
				}
				if(file.isEmpty()){
					if(u.getId()>0){
						u.setPhoto(photo);
						u.setNomPhoto(nomPhoto);
						metier.ModifierSecretaire(u);	
					}
					else{
						metier.AjouterSecretaire(u);
					}
				}
				model.addAttribute("secretaire",new Secretaire());
				model.addAttribute("services",metier.ListService());
				return "redirect:../adminPersonnel/addSecretaire";
			}
			
			@RequestMapping(value="/addSecretaire")
			public String indexSecretaire(Model model,HttpSession session){
				Users user=(Users) session.getAttribute("user");
				if(user.getLogin()==null){
					return "redirect:../login";
				}
				model.addAttribute("secretaire",new Secretaire());
				model.addAttribute("secretaires",metier.ListSecretaire());
				model.addAttribute("services",metier.ListService());
				model.addAttribute("message",new Message());
				model.addAttribute("liste", metier.listeUser(user.getId()));
				session.removeAttribute("page");
				session.setAttribute("page", "adminPersonnel/addSecretaire");
				return "secretaire";
			}
			@RequestMapping(value="/listeSecretaire")
			public String listeSecretaire(HttpServletRequest request,ModelMap modelmap,HttpSession session){
				Users user=(Users) session.getAttribute("user");
				if(user.getLogin()==null){
					return "redirect:../login";
				}
				PagedListHolder<Secretaire> pagedListHolder=new PagedListHolder<Secretaire>(metier.ListSecretaire());
				int page=ServletRequestUtils.getIntParameter(request, "p", 0);
				pagedListHolder.setPage(page);
				pagedListHolder.setPageSize(10);
				modelmap.put("pagedListHolder", pagedListHolder);
				modelmap.addAttribute("message",new Message());
				modelmap.addAttribute("liste", metier.listeUser(user.getId()));
				return "secretaire";
			}
			@RequestMapping(value="/modifSecretaire")
			public String editSecretaire(int idSec,Model model,HttpSession session){
				Users user=(Users) session.getAttribute("user");
				if(user.getLogin()==null){
					return "redirect:../login";
				}
				Secretaire m=metier.chercherSecretaire(idSec);
				model.addAttribute("secretaire",m);
				model.addAttribute("services",metier.ListService());
				model.addAttribute("message",new Message());
				session.removeAttribute("sec");
				session.setAttribute("sec", m);
				model.addAttribute("liste", metier.listeUser(user.getId()));
				return "secretaire";
			}
			@RequestMapping(value="/suppSecretaire")
			public String delSecretaire(int idSec,Model model,HttpSession session){
				Users user=(Users) session.getAttribute("user");
				if(user.getLogin()==null){
					return "redirect:../login";
				}
				metier.SupprimerSecretaire(idSec);
				model.addAttribute("secretaire",new Secretaire());
				model.addAttribute("services",metier.ListService());
				return "redirect:../adminPersonnel/addSecretaire";
			}
			
			@RequestMapping(value="/rechSecretaire")
			public String rechSecretaire(String name, HttpServletRequest request,ModelMap modelmap,HttpSession session){
				Users user=(Users) session.getAttribute("user");
				if(user.getLogin()==null){
					return "redirect:../login";
				}
				PagedListHolder<Secretaire> pagedListHolder=new PagedListHolder<Secretaire>(metier.chercherSecretaire(name));
				int page=ServletRequestUtils.getIntParameter(request, "p", 0);
				pagedListHolder.setPage(page);
				pagedListHolder.setPageSize(10);
				modelmap.put("pagedListHolder", pagedListHolder);
				modelmap.addAttribute("message",new Message());
				modelmap.addAttribute("liste", metier.listeUser(user.getId()));
				return "listeSecretaire";
			}
			@RequestMapping(value="photoSecr",produces=MediaType.IMAGE_JPEG_VALUE)
			@ResponseBody
			public byte[] photoProfil(int idSecr) throws IOException{
				Secretaire m=metier.chercherSecretaire(idSecr);
					return IOUtils.toByteArray(new ByteArrayInputStream(m.getPhoto()));
			}
				
		}
