package com.pfc.clinique;

import static org.junit.Assert.*;

import javax.validation.constraints.AssertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.ui.Model;


public class TestJPA {
	ClassPathXmlApplicationContext context;
	@Before
	public void setUp() throws Exception {
	}
   
	@Test
	public void test1(){
	   try{
			 context=new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
	   }
	   catch(Exception e){
		   assertTrue(e.getMessage(),false);
	   }

	}
}
