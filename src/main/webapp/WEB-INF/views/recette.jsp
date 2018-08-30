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
	<%@ include file="message.jsp" %>

<%@ include file="menu.jsp" %>

	<div id="chemin">
		<a href="addPatient"><span class="glyphicon glyphicon-home"></span>Accueil</a><span class="glyphicon glyphicon-menu-right"></span>
		<a  href="listePatientSecr"><span class="glyphicon glyphicon-list"></span> Patients</a><span class="glyphicon glyphicon-menu-right"></span>
		<a  href="afficherDossier?idPat=${idPat}"><span class="glyphicon glyphicon-folder-open"></span>Dossier</a>
		<span class="glyphicon glyphicon-menu-right"></span><span class="glyphicon glyphicon-file"></span> Formulaire Visite
	</div>
	<div id="milieu">
		<f:form modelAttribute="visite" action="saveVisite" 
	        method="post" enctype="multiprat/form-data">
	     <div style="margin-left:15%;width: 70%;margin-top: 20px;border:2px solid;border-bottom-color: black;border-right-color: #f1f1f1;border-left-color: #f3f3f3;border-top-color: black" class="form-group">

			
			<f:input type="hidden" path="id" value="${visite.id }"/>
			<f:input type="hidden" path="date" value="${visite.date }"/>
			<f:input type="hidden" path="motif" value="${visite.motif}"/>
			<f:input type="hidden" path="medecin.id" value="${visite.medecin.id }"/>
			<f:input type="hidden" path="patient.id" value="${visite.patient.id }"/>
			<f:input type="hidden" path="paiement" value="${visite.paiement }"/>
			<f:input type="hidden" path="type" value="${visite.type}"/>
			
			
			
			<br><br>
			<label style="margin-left:15px; margin-top:-5px" for="recettecons">Recette Consultation</label>
			<f:input id="recettecons" class="form-control" path="recette"/>
			<f:errors path="recette" cssClass="errors"></f:errors>
			<br><br>
			<button type="submit" style="min-width:100px;background:#64bc62;font-size:16px;width:8%;height:100%;" class="btn btn-default" ><span   class="glyphicon glyphicon-ok"></span>Valider</button>
			<button style="min-width:100px;background:#ff3300;font-size:16px;width:8%;height:100%;" class="btn btn-default" type="reset"><span class="glyphicon glyphicon-remove"></span>Annuler</button>
		</div>
	</f:form>
</div>
</div>
	<script src="<%=request.getContextPath()%>/resources/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/Delete.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/bootstrap.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/cliniqueSupp.js"></script>
