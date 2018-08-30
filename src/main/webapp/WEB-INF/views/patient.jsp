<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html" charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="../resources/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="../resources/css/style1.css" />
	<title>Insert title here</title>
</head>
<body>	
<div id="contenu">
<div style="margin-bottom:0.5%;margin-left:1.6%;margin-top:1%;font-size: 23px;" ><span class="glyphicon glyphicon-user"></span> Secretaire</div>

	<div id="deconnexion"> <a href="../deconnexion"><span class="glyphicon glyphicon-off"></span> Deconnexion</a>
</div>
<div id="profil">
<h4><img src="../resources/img/logoClinique.jpg" width="80px" height="40px" style="margin-left:5%;border-radius:40px 40px 40px 40px;"/>EASY-CLINIQUE</h4>
<hr>
	<c:if test="${user.id>0}">
		<img style="margin-left:5%;border-radius:40px 40px 40px 40px;" width="80px" height="80px" src="photoPSecr?idSecr=${user.id}"/>
		<div style="padding-top:20px;padding-bottom:35px;padding-left:5%;font-size:22px;font-family:italic;color:white;">${user.prenom} ${user.nom}</div>
	</c:if>
	
</div>
	<%@ include file="message.jsp" %>

<div id="menu">
	<ul class="nav nav-pills nav-stacked">
	<hr/>
		<li class="activation" ><a href="addPatient"><span class="glyphicon glyphicon-plus"></span> Nouveau Patient</a></li>
		<br/><br/>
		<li><a  href="listePatientSecr"><span class="glyphicon glyphicon-list"></span> Liste Patients</a></li>
		<br/><br/>
		<li ><a href="listeRdv"><span class="glyphicon glyphicon-list"></span> Rendez_vous</a></li>
		<br/><br/>
		<li ><a href="modifProfil"><span class="glyphicon glyphicon-edit"></span> Modifier profil</a></li>
		<hr/>
	</ul>
</div>
	<div id="chemin">
		<a href="addPatient"><span class="glyphicon glyphicon-home"></span>Accueil</a><span class="glyphicon glyphicon-menu-right"></span>
		<a  href="listePatientSecr"><span class="glyphicon glyphicon-list"></span> Patients</a><span class="glyphicon glyphicon-menu-right"></span>
		<span class="glyphicon glyphicon-file"></span>Nouveau
	</div>
		<div style="overflow-y:scroll; height:800px;" >
	
<div id="milieu">
	<f:form modelAttribute="patient" action="savePatient" 
	        method="post" enctype="multipart/form-data">
	     <div style="margin-left:15%;width: 70%;margin-top: 20px;border:2px solid;border-bottom-color: black;border-right-color: #f1f1f1;border-left-color: #f3f3f3;border-top-color: black" class="form-group">
	        
				<f:input type="hidden" path="id"/>
				<f:input type="hidden" path="secretaire.id" value="${user.id}"/>
				
				<f:input type="hidden" path="id"/>
				<f:input type="hidden" path="dossier.id" />
				
				
				
				<label style="margin-left:15px; margin-top:-5px" for="nompatient">Nom</label>
				<f:input  id="nompatient" class="form-control" path="nom"/>
				<f:errors path="nom" cssClass="errors"></f:errors><br>
				
				<label style="margin-left:15px;margin-top:-5px" for="prenompatient">Prénom</label>
				<f:input id="prenompatient" class="form-control" path="prenom"/>
				<f:errors path="prenom" cssClass="errors"></f:errors><br>
				
				
				<label style="margin-left:15px;margin-top:-5px" for="sexepatient">Sexe</label>
				<f:select class="form-control" id="sexepatient" path="sexe">
					<f:option value="Masculin"></f:option>
					<f:option value="Féminin"></f:option>
				</f:select>
				<f:errors path="sexe" cssClass="errors"></f:errors><br>
				
				
				<label style="margin-left:15px;margin-top:-5px" for="datenaispatient">Date de naissance</label>
				<f:input id="datenaispatient" class="form-control" path="datenais" type="date"/>
				<f:errors path="datenais" cssClass="errors"></f:errors><br>
				
				<label style="margin-left:15px;margin-top:-5px" for="lieunaispatient">Lieu de naissance</label>
				<f:input id="lieunaispatient" class="form-control" path="lieu"/>
				<f:errors path="lieu" cssClass="errors"></f:errors><br>
				
				<label style="margin-left:15px;margin-top:-5px" for="adressepatient">Adresse</label>
				<f:input id="adressepatient" class="form-control" path="adresse"/>
				<f:errors path="adresse" cssClass="errors"></f:errors><br>
				
				<label style="margin-left:15px;margin-top:-5px" for="telpatient">Téléphone</label>
				<f:input id="telpatient" class="form-control" path="tel"/>
				<f:errors path="tel" cssClass="errors"></f:errors><br>
				
				<label style="margin-left:15px;margin-top:-5px" for="taypatient">Taille</label>
				<f:input id="taypatient" class="form-control" path="taille"/>
				<f:errors path="taille" cssClass="errors"></f:errors><br>
				
				<label style="margin-left:15px;margin-top:-5px" for="poidpatient">Poids</label>
				<f:input id="poidpatient" class="form-control" path="poid"/>
				<f:errors path="taille" cssClass="errors"></f:errors><br>
				
				<label style="margin-left:15px;margin-top:-5px" for="gspatient">Groupe Sanguin</label>
				<f:select class="form-control" id="gspatient" path="groupeS">
					<f:option value="O+"></f:option>
					<f:option value="O-"></f:option>
				</f:select>
				<f:errors path="groupeS" cssClass="errors"></f:errors><br>
				
				<label style="margin-left:15px;margin-top:-5px" for="numsspatient">Numéro Sécurité Sociale :</label>
				<f:input id="numsspatient" class="form-control" path="numSS"/>
				<f:errors path="numSS" cssClass="errors"></f:errors>
				<br>
				<label style="margin-left:15px;margin-top:-5px" for="Photo">Photo </label>
				<c:if test="${patient.photo!=null}">
					<img style="margin-left:55px;"width="150" height="150px" src="photo?idPat=${patient.id}"/>
				</c:if>
				<br/>
				<input style="margin:15px; width:350px;height:30px;" type="file"  name="file" />
				<br><br><br>
				
				<button type="submit" style="min-width:100px;background:#64bc62;font-size:16px;width:8%;height:100%;" class="btn btn-default" ><span   class="glyphicon glyphicon-ok"></span>Valider</button>
			<button onclick="javascript:retour_ListeP()" style="min-width:100px;background:#ff3300;font-size:16px;width:8%;height:100%;" class="btn btn-default" type="reset"><span class="glyphicon glyphicon-remove"></span>Annuler</button>
		</div>
	</f:form>
	</div>
	</div>
	</div>
<script src="<%=request.getContextPath()%>/resources/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/Delete.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/bootstrap.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/cliniqueSupp.js"></script>
