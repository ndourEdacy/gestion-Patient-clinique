package sn.gestion.clinique.entites;

import java.io.Serializable;
import java.sql.Date;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Message implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int idmessage;
	
	int idexpediteur;
	int idrecepteur;
	int vu;
 
	public int getVu() {
		return vu;
	}
	public void setVu(int vu) {
		this.vu = vu;
	}
	String contenu;
	private String date;
	
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getIdmessage() {
		return idmessage;
	}
	public void setIdmessage(int idmessage) {
		this.idmessage = idmessage;
	}
	
	public int getIdexpediteur() {
		return idexpediteur;
	}
	public void setIdexpediteur(int idexpediteur) {
		this.idexpediteur = idexpediteur;
	}
	public int getIdrecepteur() {
		return idrecepteur;
	}
	public void setIdrecepteur(int idrecepteur) {
		this.idrecepteur = idrecepteur;
	}
	public String getContenu() {
		return contenu;
	}
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

}
