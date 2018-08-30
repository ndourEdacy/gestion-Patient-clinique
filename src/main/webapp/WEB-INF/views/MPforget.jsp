<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html" charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="/clinique/resources/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="/clinique/resources/css/style1.css" />
	<link rel="stylesheet" type="text/css" href="/clinique/resources/css/jquery-ui-1.10.4.css" />
	<style>
	  body{
	    background: #f1f8e9;
	  }
	
	</style>
	<title>Insert title here</title>
</head>
<body>	
<div class="login">
	<f:form modelAttribute="user" action="verifRecupCompte" 
	        method="get" enctype="multipart/form-data">
	        
	        <div style="width:100%;height:22%; background: #c1e2b3;"><br><h3>Recuperation de compte<h3></div>
	        
		<label style="margin-left:15px;margin-top:-5px" for="login">Speudo</label>
		<input  class="form-control" id="login" name="login"/>
		<f:errors path="login" cssClass="errors"></f:errors><br>
	
		<label style="margin-left:15px;margin-top:-5px" for="question">Question secrète </label>
				<select class="form-control"  id="question" name="question">
				<option value="1">Quel est nom de jeune fille de votre mere?</option>
				<option value="2">Quel est nom de votre premier/e copain/copine?</option>
				<option value="3">Quel est votre plat préféré?</option>
				<option value="4">Quel est votre plat préféré?</option>
				
				</select>
				<br>
		<label style="margin-left:15px;margin-top:-5px" for="reponse">Votre réponse</label>
		<input  class="form-control" id="reponse" name="reponse"/>
		<br>
				
		
				<button type="submit" style="min-width:100px;background:#64bc62;font-size:16px;width:8%;height:100%;" class="btn btn-default" ><span   class="glyphicon glyphicon-ok"></span>Valider</button>
			    <p>${message}</p><p>${password}</p>
		
	</f:form>
	</div>
	</body></html>