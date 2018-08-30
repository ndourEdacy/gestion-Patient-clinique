package sn.gestion.clinique;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sn.gestion.clinique.entites.Administrateur;
import sn.gestion.clinique.metier.IAdminMetier;

public class TestJPA {
	ClassPathXmlApplicationContext context;
	@Before
	public void setUp() throws Exception {
	}
   
	@Test
	public void test1(){
		 context=new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
		 

	}
}
