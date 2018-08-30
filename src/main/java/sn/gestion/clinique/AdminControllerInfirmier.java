package sn.gestion.clinique;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.validation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import sn.gestion.clinique.entites.*;
import sn.gestion.clinique.metier.*;

@Controller
@RequestMapping(value="/adminPersonnel")
public class AdminControllerInfirmier {
	@Autowired
	private IAdminMetier metier;
	
	@RequestMapping(value="/index")
	public String index(Model model,HttpSession session){
		Users user=(Users) session.getAttribute("user");
		if(user.getLogin()=="rien"){
			return "redirect:../login";
		}
		return "indexAdministrateur";
	}
	
	@RequestMapping(value="/saveInfirmier")
	public String saveInfirmier(@Valid Infirmier u, BindingResult bindingResult, Model model) throws IOException{
		if(bindingResult.hasErrors()){
			model.addAttribute("infirmiers",metier.ListInfirmier());
			model.addAttribute("services",metier.ListService());
			return "infirmier";
		}
		if(u.getId()>0){
			metier.Modifiernfirmier(u);
		}else
			metier.AjouterInfirmier(u);
		model.addAttribute("infirmier",new Infirmier());
		model.addAttribute("infirmiers",metier.ListInfirmier());
		model.addAttribute("services",metier.ListService());
		return "infirmier";
	}
	
	@RequestMapping(value="/addInfirmier")
	public String indexInfirmier(Model model){
		model.addAttribute("infirmier",new Infirmier());
		model.addAttribute("infirmiers",metier.ListInfirmier());
		model.addAttribute("services",metier.ListService());

		return "infirmier";
	}
	
	@RequestMapping(value="/modifInfirmier")
	public String editInfirmier(int idInf,Model model){
		Infirmier i=metier.chercherInfirmier(idInf);
		model.addAttribute("infirmier",i);
		model.addAttribute("service",metier.ListService());
		model.addAttribute("infirmiers",metier.ListInfirmier());
		model.addAttribute("services",metier.ListService());
		return "infirmier";
	}
	@RequestMapping(value="/suppInfirmier")
	public String delInfirmier(int idInf,Model model){
		//metier.SupprimerInfirmier(idInf);
		model.addAttribute("infirmier",new Infirmier());
		model.addAttribute("service",metier.ListService());
		model.addAttribute("infirmiers",metier.ListInfirmier());
		model.addAttribute("services",metier.ListService());

		return "infirmier";
	}
	
}
