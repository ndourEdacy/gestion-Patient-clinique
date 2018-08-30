function delete_Visite(id) 
	        	{
	            if(confirm("Voulez vous vraiment supprimer ce contenu ?")) 
	            { 
	                alert('Suppression effectuée'); 
	                location.href= 'suppVisite?idVisite='+id; 
	            } 
	            return false;
	        	} 
	        	
	        	function delete_Antecedant(id) 
	        	{
	            if(confirm("Voulez vous vraiment supprimer ce contenu ?")) 
	            { 
	                alert('Suppression effectuée'); 
	                location.href= 'suppAnt?idAnt='+id; 
	            } 
	            return false;
	        	} 
	        	
	        	function delete_Bilan(id) 
	        	{
	            if(confirm("Voulez vous vraiment supprimer ce contenu ?")) 
	            { 
	                alert('Suppression effectuée'); 
	                location.href= 'suppBilan?idBil='+id; 
	            } 
	            return false;
	        	} 
	        	
	        	function delete_Certificat(id) 
	        	{
	            if(confirm("Voulez vous vraiment supprimer ce contenu ?")) 
	            { 
	                alert('Suppression effectuée'); 
	                location.href= 'suppCertificat?idCert='+id; 
	            } 
	            return false;
	        	}
	        	
	        	function delete_Rdv(id) 
	        	{
	            if(confirm("Voulez vous vraiment supprimer ce contenu ?")) 
	            { 
	                alert('Suppression effectuée'); 
	                location.href= 'suppRdv?idRdv='+id; 
	            } 
	            return false;
	        	} 
	        	
	        	function delete_Ordonnance(id) 
	        	{
	            if(confirm("Voulez vous vraiment supprimer ce contenu ?")) 
	            { 
	                alert('Suppression effectuée'); 
	                location.href= 'suppOrdonnance?idOrd='+id; 
	            } 
	            return false;
	        	} 
	        	function retour_Dossier(id){
	        		location.href= 'afficherDossier?idPat='+id;
	        	}
	        	function retour_ListeP(){
	        		location.href= 'listePatientSecr';
	        	}
	        	function retour_Admin(id){
	        		location.href= 'indexAdmin';
	        	}
	        	function delete_Secr(id){
	        	    if(confirm("Voulez vous vraiment supprimer ce contenu ?")) 
		            { 
		                alert('Suppression effectuée'); 
		                location.href= 'suppSecretaire?idSec='+id; 
		            } 
		            return false;
	        	}
	        	function delete_Med(id){
	        	    if(confirm("Voulez vous vraiment supprimer ce contenu ?")) 
		            { 
		                alert('Suppression effectuée'); 
		                location.href= 'suppMedecin?idMed='+id; 
		            } 
		            return false;
	        	}
	        	
	        	function delete_Admin(id){
	        	    if(confirm("Voulez vous vraiment supprimer ce contenu ?")) 
		            { 
		                alert('Suppression effectuée'); 
		                location.href= 'suppAdministrateur?idAdmin='+id; 
		            } 
		            return false;
	        	}
	        	