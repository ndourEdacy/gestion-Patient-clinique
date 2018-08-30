package sn.gestion.clinique;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.print.attribute.standard.Media;
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
import org.springframework.web.servlet.ModelAndView;

import com.itextpdf.text.pdf.qrcode.ByteArray;

import sn.gestion.clinique.entites.*;
import sn.gestion.clinique.metier.IAdminMetier;

@Controller
@RequestMapping(value="/adminPersonnel")
public class AdminControllerMedecin {
		@Autowired
		private IAdminMetier metier;
		
		@RequestMapping(value="/saveMedecin")
		public String saveMedecin(@Valid MultipartFile file, Medecin u, BindingResult bindingResult, Model model,HttpSession session) throws IOException{
			Users user=(Users) session.getAttribute("user");
			Medecin med=(Medecin)session.getAttribute("med");
			byte [] photo=null;
			String nomPhoto=null;
			if(u.getId()>0 && med.getPhoto()!=null){
				photo=med.getPhoto();
				nomPhoto=med.getNomPhoto();
			}
			if(user.getLogin()==null){
				return "redirect:../login";
			}
			if(bindingResult.hasErrors()){
				model.addAttribute("specialites",metier.ListSpecialite());
				return "medecin";
			}
			if(!file.isEmpty()){
				u.setPhoto(file.getBytes());
				u.setNomPhoto(file.getOriginalFilename());
				if(u.getId()>0){
					metier.ModifierMedecin(u);	
				}
				else{
					metier.AjouterMedecin(u);
				}
			}
			if(file.isEmpty()){
				if(u.getId()>0){
					u.setPhoto(photo);
					u.setNomPhoto(nomPhoto);
					metier.ModifierMedecin(u);	
				}
				else{
					metier.AjouterMedecin(u);
				}
			}
			model.addAttribute("medecin",new Medecin());
			model.addAttribute("medecins",metier.ListMedecin());
			model.addAttribute("specialites",metier.ListSpecialite());
			return "redirect:../adminPersonnel/addMedecin";
		}
		
		@RequestMapping(value="/addMedecin")
		public String indexMedecin(Model model,HttpSession session){
			Users user=(Users) session.getAttribute("user");
			if(user.getLogin()==null){
				return "redirect:../login";
			}
			model.addAttribute("medecin",new Medecin());
			model.addAttribute("medecins",metier.ListMedecin());
			model.addAttribute("user",user);
			model.addAttribute("specialites",metier.ListSpecialite());
			model.addAttribute("message",new Message());
			model.addAttribute("liste", metier.listeUser(user.getId()));
			session.removeAttribute("page");
			session.setAttribute("page", "adminPersonnel/addMedecin");
			return "medecin";
		}
		@RequestMapping(value="/ListeMedecin")
		public String listeMedecin(Model model,HttpSession session,ModelMap modelmap, HttpServletRequest request){
			Users user=(Users) session.getAttribute("user");
			if(user.getLogin()==null){
				return "redirect:../login";
			}
			PagedListHolder<Medecin> pagedListHolder=new PagedListHolder<Medecin>(metier.ListMedecin());
			model.addAttribute("medecins",pagedListHolder);
			int page=ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			pagedListHolder.setPageSize(10);
			modelmap.put("pagedListHolder", pagedListHolder);
			model.addAttribute("message",new Message());
			model.addAttribute("liste", metier.listeUser(user.getId()));
			return "medecin";
		}
		@RequestMapping(value="/modifMedecin")
		public String editMedecin(int idMed,Model model,HttpSession session){
			Users user=(Users) session.getAttribute("user");
			if(user.getLogin()==null){
				return "redirect:../login";
			}
			Medecin m=metier.chercherMedecin(idMed);
			model.addAttribute("medecin",m);
			model.addAttribute("medecins",metier.ListMedecin());
			model.addAttribute("specialites",metier.ListSpecialite());
			model.addAttribute("message",new Message());
			session.removeAttribute("med");

			session.setAttribute("med", m);
			model.addAttribute("liste", metier.listeUser(user.getId()));
			return "medecin";
		}
		@RequestMapping(value="/suppMedecin")
		public String delMedecin(int idMed,Model model,HttpSession session){
			Users user=(Users) session.getAttribute("user");
			if(user.getLogin()==null){
				return "redirect:../login";
			}
			metier.SupprimerMedecin(idMed);
			model.addAttribute("medecin",new Medecin());
			model.addAttribute("medecins",metier.ListMedecin());
			model.addAttribute("specialites",metier.ListSpecialite());
			 return "redirect:../adminPersonnel/addMedecin";
		}
		
		@RequestMapping(value="/rechMedecin")
		public String rechMedecin(String name, HttpServletRequest request,ModelMap modelmap,HttpSession session){
			Users user=(Users) session.getAttribute("user");
			if(user.getLogin()==null){
				return "redirect:../login";
			}
			PagedListHolder<Medecin> pagedListHolder=new PagedListHolder<Medecin>(metier.chercherMedecin(name));
			int page=ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			pagedListHolder.setPageSize(10);
			modelmap.put("pagedListHolder", pagedListHolder);
			modelmap.addAttribute("message",new Message());
			modelmap.addAttribute("liste", metier.listeUser(user.getId()));
			return "medecin";
		}
		@RequestMapping(value="photo",produces=MediaType.IMAGE_JPEG_VALUE)
		@ResponseBody
		public byte[] photoProfil(int idMed) throws IOException{
			Medecin m=metier.chercherMedecin(idMed);
				return IOUtils.toByteArray(new ByteArrayInputStream(m.getPhoto()));
		}
	}
