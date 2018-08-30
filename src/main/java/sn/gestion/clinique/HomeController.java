package sn.gestion.clinique;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import sn.gestion.clinique.entites.Users;
import sn.gestion.clinique.metier.IMedecinMetier;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	  @Autowired
	   private IMedecinMetier metier;
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "redirect:/login";

	}
	
	@RequestMapping(value = "/recupCompte")
	public String recupCompte(Model model) {
		return "MPforget";

	}
	@RequestMapping(value = "/verifRecupCompte")
	public String verifRecupCompte(String login,String question,String reponse, Model model) {
		List<Users> users=metier.verifCompte(login,question,reponse);
		if(users.size()!=0){
			for (int i = 0; i < users.size(); i++) {
				model.addAttribute("message", "Voici votre mot de passe: ");
				model.addAttribute("password", users.get(i).getPassword());
			}
		}
		else{
			model.addAttribute("message", "Les informations entrées sont incorrectes!");
		}
		return "MPforget";

	}
	
	
}
