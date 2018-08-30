<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<head>
<meta http-equiv="Content-Type" content="text/html" charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="../resources/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="../resources/css/jquery-ui-1.10.4.css" />
	<link rel="stylesheet" type="text/css" href="../resources/css/style1.css" />
	<script src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/jquery-ui.min.js"></script>
	<title>Insert title here</title>
</head>
<body>	
<div id="contenu">
<div style="margin-bottom:0.5%;margin-left:1.6%;margin-top:1%;font-size: 23px;" ><span class="glyphicon glyphicon-user"></span> Medecin</div>

	<%@ include file="menuMed.jsp" %>
	<div id="chemin">
	<a href="listePatients"><span class="glyphicon glyphicon-home"></span>Accueil</a><span class="glyphicon glyphicon-menu-right"></span>
	<a  href="listePatients"><span class="glyphicon glyphicon-list"></span> Patients</a><span class="glyphicon glyphicon-menu-right"></span>
	<a  href="DossierPatient?idPat=${idPat}"><span class="glyphicon glyphicon-folder-open"></span>Dossier</a>
	<span class="glyphicon glyphicon-menu-right"></span><span class="glyphicon glyphicon-file"></span> Formulaire Visite
	
	</div>
	
	<div id="milieu">
	<f:form modelAttribute="visite" action="saveVisite" 
	        method="post" enctype="multiprat/form-data">	
	     <div style="margin-left:15%;width: 70%;margin-top: 20px;border:2px solid;border-bottom-color: black;border-right-color: #f1f1f1;border-left-color: #f3f3f3;border-top-color: black" class="form-group">
			
			<f:input type="hidden" path="medecin.id"  value="${user.id}"/>
			<f:input type="hidden" path="patient.id"  value="${idPat}"/>
			<f:input type="hidden" path="id"/>
			
			<label style="margin-left:15px; margin-top:-5px" for="datevisite">Date</label>
		     <f:input type="text" id="datepicker" class="form-control" path="date"  />
			<br>
			<label style="margin-left:15px; margin-top:-5px" for="motifvisite">Motif</label>
			<f:textarea id="motifvisite" class="form-control" rows="3" path="motif"/>
			<f:errors path="motif" cssClass="errors"></f:errors>
			<br>
			<button type="submit" style="min-width:100px;background:#64bc62;font-size:16px;width:8%;height:100%;" class="btn btn-default" ><span   class="glyphicon glyphicon-ok"></span>Valider</button>
			<button style="min-width:100px;background:#ff3300;font-size:16px;width:8%;height:100%;" class="btn btn-default" type="reset"><span class="glyphicon glyphicon-remove"></span>Annuler</button>
			</div>
	</f:form>
	</div>
</div>
<script>
jQuery(document).ready(function($){
    $("#datepicker").datepicker();
});
</script>
<script src="<%=request.getContextPath()%>/resources/js/calendrier.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/cliniqueSupp.js"></script>

