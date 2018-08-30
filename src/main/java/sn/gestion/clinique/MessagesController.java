package sn.gestion.clinique;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import sn.gestion.clinique.dao.ICliniqueDao;
import sn.gestion.clinique.entites.Message;
import sn.gestion.clinique.entites.Users;
import sn.gestion.clinique.metier.CliniqueMetierImplemente;
import sn.gestion.clinique.metier.IAdminMetier;
import sn.gestion.clinique.metier.IMedecinMetier;
import sn.gestion.clinique.metier.ISecretaireMetier;

@Controller
@SessionAttributes("user")
@RequestMapping(value="/adminPersonnel")
public class MessagesController {
	
	@Autowired
	IMedecinMetier metier;
	@Autowired
	IAdminMetier metierAd;
	@Autowired
	ISecretaireMetier secr;
	@RequestMapping(value="/envoiMessage")
	public String envoiMessage(@Valid int idExp,Message message,Model model,HttpSession session,int idDes,Locale locale) throws IOException{
		Users user=(Users) session.getAttribute("user");
		String page = (String)session.getAttribute("page");
		System.out.println(idExp);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		message.setDate(formattedDate);
		message.setIdexpediteur(idExp);
		message.setIdrecepteur(idDes);
		
		if(user.getLogin()==null){
			return "redirect:../login";
		}
		model.addAttribute("user",user);
		model.addAttribute("message",new Message());
		if(user.getTitre().equals("Medecin")){
			metier.envoyerMessage(message);
			model.addAttribute("messages",metier.listeMessages(idExp, idDes));
		}
		else if(user.getTitre().equals("Secretaire")){
			secr.envoyerMessage(message);	
		}
		else{
			metierAd.envoyerMessage(message);
			model.addAttribute("messages",metierAd.listeMessages(idExp, idDes));
		}
		return "redirect:../"+page;
	}
	
	@PostMapping(value="/listeMessage")
	@ResponseBody
	public String  messages(int exp,int des){
		String sms="<table>";
		int ex = exp,de=des;
		System.out.println(ex+" "+de);
		List<Message> mes = metier.listeMessages(exp, des);
		for (Iterator iterator = mes.iterator(); iterator.hasNext();) {
			Message message = (Message) iterator.next();
			if(message.getIdexpediteur()==exp)
				sms+="<tr><td style='font-size:19px;font-family:italic;padding:10px;'><strong><strong>Vous</strong></strong>: "+message.getContenu()+"</td></tr>";
			else{
				sms+="<tr><td style='font-size:18px;padding:10px;'><strong><strong> "+metierAd.getUser(exp).getLogin()+"</strong></strong>: "+message.getContenu()+"</td></tr>";
			}
		} 
		
		return sms+"</table>";
	}
	@PostMapping(value="/notifMessage")
	@ResponseBody
	public String notif(int exp){
		int n= metier.notifMessage(exp);
		return n+"";
	}
	
}
