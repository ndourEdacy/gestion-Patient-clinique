<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<head>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/style1.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css" />
</head>
<div id="contenu">
	<%@ include file="menuMed.jsp" %>
	
	<div id="chemin">
	<a href="listePatients"><span class="glyphicon glyphicon-home"></span>Accueil</a><span class="glyphicon glyphicon-menu-right"></span>
	<a  href="listePatients"><span class="glyphicon glyphicon-list"></span> Patients</a><span class="glyphicon glyphicon-menu-right"></span>
	<a  href="DossierPatient?idPat=${idPat}"><span class="glyphicon glyphicon-folder-open"></span>Dossier</a>
	<span class="glyphicon glyphicon-file"></span> Formulaire Bilan
	
	</div>
	<div id="milieu">
	<f:form modelAttribute="bilan" action="saveBilan" 
	        method="post" enctype="multiprat/form-data">	
	     <div style="margin-left:15%;width: 70%;margin-top: 20px;margin-bottom: 20px;border:1px solid ;" class="form-group">
			
			<f:input type="hidden" path="medecin.id"  value="${user.id}"/>
			<f:input type="hidden" path="patient.id"  value="${idPat}"/>
			<f:input type="hidden" path="id"/>
			
		
			
			<label style="margin-left:15px; margin-top:-5px" for="libbilan">Libellé</label>
			<f:input id="libbilan" class="form-control"  path="libelle"/>
			<f:errors path="libelle" cssClass="errors"></f:errors>
			
			<label style="margin-left:15px; margin-top:-5px" for="descbilan">Description</label>
			<f:textarea id="descbilan" class="form-control" rows="8" path="description"/>
			<f:errors path="description" cssClass="errors"></f:errors>
			
			<input class="btn btn-default" type="submit" value="Ajouter"/>
			<input class="btn btn-default" type="reset" value="Annuler"/>
			</div>
	</f:form>
	</div>
</div>