 jQuery(document).ready(function($) {
	 	//alert(parseInt( $("#id_u").text()));
	
	 $.ajax({
			type:"POST",
			url:"/clinique/adminPersonnel/notifMessage?exp="+parseInt( $("#id_u").text()),
			contentType:"text",
			success:function(data){
				//alert(data);
				$("#notification").html(data);
			},
			error:function(data){
				
			}
			
		
		});
	        	 $(".suppVisite").click(function(){
		        	  $("#opac").toggle();
		        	  var $val = $(this).attr('value');
		        
		        	
		        	  $("#supp_visite").find('a').attr('href','suppVisite?idAnt='+$val);
		        	  $("#supp_visite").toggle(1000);
		        });
	        	 $(".supp_Bilan").click(function(){
		        	  $("#opac").toggle();
		        	  var $val = $(this).attr('value');
		        	  
		        	
		        	  $("#supp_visite").find('a').attr('href','suppBilan?idPat='+$val);
		        	  $("#supp_visite").toggle(1000);
		        });
	        	 $(".suppAnt").click(function(){
		        	  $("#opac").toggle();
		        	  var $val = $(this).attr('value');
		        	 
		        	
		        	  $("#supp_visite").find('a').attr('href','suppAnt?idAnt='+$val);
		        	  $("#supp_visite").toggle(1000);
		        });
	        	 $(".suppCert").click(function(){
		        	  $("#opac").toggle();
		        	  var $val = $(this).attr('value');
		        	 
		        	
		        	  $("#supp_visite").find('a').attr('href', 'suppCertificat?idCert='+$val);
		        	  $("#supp_visite").toggle(1000);
		        });
	        	 $(".suppOrd").click(function(){
		        	  $("#opac").toggle();
		        	  var $val = $(this).attr('value');
		        	  $("#supp_visite").find('a').attr('href','suppOrdonnance?idOrd='+$val);
		        	  $("#supp_visite").toggle(1000);
		        });
	        	 
	        	$("#non").click(function(){
	        		$("#supp_visite").toggle();
	        		$("#opac").toggle();
	        	});
	        	
	        	$("#msg_discution").click(function(){
	 		 	   $("#user_connect").toggle();
	      
	 		    });
	        	//var $idEx = $("#id_u").text();
	        	$(".b_dis").click(function(){
	        		
	        		var $page = $("#formMessage").attr("action"),
	        		   
	        		 $idEx = $("#id_u").text();
	        		
	        		$("#recipient-name").attr("placeholder",$(this).html());
	        		$("#formMessage").attr("action",$page+"&idDes="+$(this).val());
	        		
	        		$.ajax({
	        			type:"POST",
	        			url:"/clinique/adminPersonnel/listeMessage?exp="+parseInt($idEx)+"&des="+parseInt($(this).val()),
	        			contentType:"text",
	        			success:function(data){
	        				$("#mesMessage").html(data);
	        			},
	        			error:function(data){
	        				//alert("errEUR");
	        			}
	        			
	        		
	        		});
	        		
	        	});
	        	$("#modifMed").click(function(){
	        		$.notify({	// options
	        			message: 'modification reussi' 
	        		},{
	        			// settings
	        			type: 'danger'
	        		});
	        	});
	        	$(".addcert").click(function(){
	        		$("#certificat").modal('show');
	        		
	        	});
	        
	        	$('#ajoutCert').click(function(){
	        		$.notify({	// options
	        			message: 'Certificat Ajoutte avec success' 
	        		},{
	        			// settings
	        			type: 'danger'
	        		});
	        	});
	        	
	        	$("#rechPat").keyup(function(){
	        		//document.location.href="/clinique/medecin/rechPatient?"+$(this).val();
	        		//alert($(this).val());
	        		if($(this).val()!=""){
	        			//alert($(this).val());
	        			$.ajax({
		        			type:"POST",
		        			url:"/clinique/medecin/rechPatient1?name="+$(this).val(),
		        			contentType:"text",
		        			success:function(data){
		        				//document.location.href="/clinique/medecin/rechPatient?"+$(this).val();
		        				$("#rech").html(data);
		        			},
		        			error:function(data){
		        				//alert("errEUR");
		        			}
		        			
		        		
		        		});
	        		}
	        		else{
	        			//alert($(this).val());
	        			location.href= 'listePatients'; 
	        		}
	        		
	        	});
	        	
	        	$("#rechP").keyup(function(){
	        		if($(this).val()!=""){
	        			//alert($(this).val());
	        			$.ajax({
		        			type:"POST",
		        			url:"/clinique/secretaire/rechPatient1?name="+$(this).val(),
		        			contentType:"text",
		        			success:function(data){
		        				$("#rechPS").html(data);
		        			},
		        			error:function(data){
		        				//alert("errEUR");
		        			}
		        			
		        		
		        		});
	        		}
	        		else{
	        			//alert($(this).val());
	        			location.href= 'listePatientSecr'; 
	        		}
	        		
	        	});
	        	
	        	$("#rechR").keyup(function(){
	        		if($(this).val()!=""){
	        			//alert($(this).val());
	        			$.ajax({
		        			type:"POST",
		        			url:"/clinique/secretaire/rechRdv1?name="+$(this).val(),
		        			contentType:"text",
		        			success:function(data){
		        				$("#rechRS").html(data);
		        			},
		        			error:function(data){
		        				//alert("errEUR");
		        			}
		        			
		        		
		        		});
	        		}
	        		else{
	        			//alert($(this).val());
	        			location.href= 'listeRdv'; 
	        		}
	        		
	        	});
	        	  	
	        	
	        	$("#rechRdv").keyup(function(){
	        		if($(this).val()!=""){
	        			//alert($(this).val());
	        			$.ajax({
		        			type:"POST",
		        			url:"/clinique/medecin/rechRdv2?name="+$(this).val(),
		        			contentType:"text",
		        			success:function(data){
		        				$("#rechRM").html(data);
		        			},
		        			error:function(data){
		        				//alert("errEUR");
		        			}
		        		});
	        		}
	        		else{
	        			//alert($(this).val());
	        			location.href= 'listeRdv'; 
	        		}
	        		
	        	});
	        	
 });