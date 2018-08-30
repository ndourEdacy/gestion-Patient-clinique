<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html" charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="../resources/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="../resources/css/style1.css" />
	<link rel="stylesheet" type="text/css" href="../tags/paging.tag" />

	<title>Insert title here</title>
</head>
<body>	
<div id="contenu">
<div style="margin-bottom:0.5%;margin-left:1.6%;margin-top:1%;font-size: 23px;" ><span class="glyphicon glyphicon-user"></span> Secretaire</div>

		<%@ include file="message.jsp" %>
	
	<%@ include file="menu.jsp" %>
	<div id="chemin">
		<a href="indexSecretaire"><span class="glyphicon glyphicon-home"></span>Accueil</a><span class="glyphicon glyphicon-menu-right"></span>
		<a  href="listePatientSecr"><span class="glyphicon glyphicon-list"></span> Patients</a><span class="glyphicon glyphicon-menu-right"></span>
		<a  href="afficherDossier?idPat=${idPat}"><span class="glyphicon glyphicon-folder-open"></span>Dossier</a>
		<span class="glyphicon glyphicon-menu-right"></span><span class="glyphicon glyphicon-file"></span> Formulaire Affectation	</div>
<div id="milieu">
	<f:form modelAttribute="patient" action="savePatientMedecin" 
	        method="post" enctype="multiprat/form-data">
	     <div style="margin-left:15%;width: 70%;margin-top: 20px;border:2px solid;border-bottom-color: black;border-right-color: #f1f1f1;border-left-color: #f3f3f3;border-top-color: black" class="form-group">
	        
				<f:input type="hidden" path="id"/>
				<f:input type="hidden" path="secretaire.id" value="${user.id }"/>
				
				<f:input type="hidden"  class="form-control" path="dossier.id" value="${patient.dossier.id}"/>
				<f:errors path="nom" cssClass="errors"></f:errors><br>
				<f:input type="hidden"  class="form-control" path="nom" value="${patient.nom}"/>
				<f:input type="hidden"  class="form-control" value="${patient.prenom}" path="prenom"/>
				<f:errors path="prenom" cssClass="errors"></f:errors><br>
				<f:input type="hidden"  class="form-control" value="${patient.sexe}" path="sexe"/>
				
				
				<label style="margin-left:15px; margin-top:-5px" for="medecin">Medecin</label>
				<f:select class="form-control" path="medecin.id" >
					<c:forEach items="${medecin}" var="med"> 
						<f:option value="${med.id}">Dr. ${med.prenom} ${med.nom}</f:option>
					</c:forEach>	 
				</f:select>
				<f:errors path="medecin.id" cssClass="errors"></f:errors><br>
				
				<f:input type="hidden"  class="form-control" value="${patient.datenais}" path="datenais"/>
				<f:errors path="datenais" cssClass="errors"></f:errors><br>
				
				<f:input type="hidden" class="form-control" value="${patient.lieu}" path="lieu"/>
				<f:errors path="lieu" cssClass="errors"></f:errors><br>
				
				<f:input type="hidden"  class="form-control" value="${patient.adresse}" path="adresse"/>
				<f:errors path="adresse" cssClass="errors"></f:errors><br>
				
				<f:input type="hidden"  class="form-control" value="${patient.tel}" path="tel"/>
				<f:errors path="tel" cssClass="errors"></f:errors><br>
				
				<f:input type="hidden"  class="form-control" value="${patient.taille}" path="taille"/>
				<f:errors path="taille" cssClass="errors"></f:errors><br>
				
				<f:input type="hidden"  class="form-control" value="${patient.groupeS}" path="groupeS"/>
				<f:errors path="groupeS" cssClass="errors"></f:errors><br>
				
				<f:input type="hidden"  class="form-control" value="${patient.poid}" path="poid"/>
				<f:errors path="poid" cssClass="errors"></f:errors><br>
				
				<f:input type="hidden" class="form-control" value="${patient.numSS}" path="numSS"/>
				<f:errors path="numSS" cssClass="errors"></f:errors>
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
