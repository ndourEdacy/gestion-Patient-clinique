 package sn.gestion.clinique;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import sn.gestion.clinique.entites.Users;
import sn.gestion.clinique.metier.IMedecinMetier;


@Controller
@SessionAttributes("user")
public class LoginController {
   @Autowired
   private IMedecinMetier metier;
   private Users user=null;
   @RequestMapping("/login")
   public String setUpUserForm(Model model,HttpSession session) {
	   user=(Users) session.getAttribute("user");
	   model.addAttribute("user",new Users());
	   	return "login";
   }
   @RequestMapping("/dologin")
   public String doLogin(@Valid Users user, Model model,HttpSession session) {
	   List<Users> u=metier.VerifUsers();
	   Users us=null;
	   if(!user.getLogin().equals("") || !user.getPassword().equals("")){
	   for(int i = 0; i < u.size(); i++){
		  if(u.get(i).getLogin().equals(user.getLogin()) && u.get(i).getPassword().equals(user.getPassword())){
			  us=u.get(i);
		  	  session.setAttribute("user", us);
		  }
	   }
	   if (us!=null) {
		   model.addAttribute("user",us);
		   return "redirect:/info";
      } else {
          model.addAttribute("message","  Login ou Password incorrect.Veillez reessayer !!");
          return "login";
      }
	  }else{
		  model.addAttribute("message","  Remplissez tous les champs s'il vous plait !!");
          return "login";
	  }
   }
   
   @RequestMapping("/info")
   public String userInfo(HttpSession session) {
	   Users user=(Users) session.getAttribute("user");
	   if(user.getTitre().equalsIgnoreCase("Secretaire"))
	   		return "redirect:secretaire/addPatient"; 
	   else if(user.getTitre().equalsIgnoreCase("Medecin"))
	   		return "redirect:medecin/listePatients"; 
	   else if(user.getTitre().equalsIgnoreCase("Administrateur"))
	   		return "redirect:adminPersonnel/addMedecin";
	   return "login"; 		
   }
   
   
   @RequestMapping("deconnexion")
   public String page4(@ModelAttribute Users user, HttpSession session,Model model, SessionStatus status) {
       status.setComplete();
       user.setLogin("rien");
       session.setAttribute("user",user);
       return "redirect:/login";
   }
}
