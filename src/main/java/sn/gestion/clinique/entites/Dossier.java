package sn.gestion.clinique.entites;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


@Entity
public class Dossier implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Date datecreation;
	public Dossier() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Dossier(Date datecreation) {
		super();
		this.datecreation = datecreation;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long num) {
		this.id = num;
	}
	public Date getDatecreation() {
		return datecreation;
	}
	public void setDatecreation(Date date) {
		this.datecreation = date;
	}
	
}
