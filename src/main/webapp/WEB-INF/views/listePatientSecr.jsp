<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html" charset="ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="../resources/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="../resources/css/style1.css" />
	<link rel="stylesheet" type="text/css" href="../tags/paging.tag" />
	<script src="<%=request.getContextPath()%>/resources/js/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/cliniqueSupp.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/bootstrap.js"></script>	
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
		<li ><a href="addPatient"><span class="glyphicon glyphicon-plus"></span> Nouveau Patient</a></li>
		<br/><br/>
		<li class="activation"><a  href="listePatientSecr"><span class="glyphicon glyphicon-list"></span> Liste Patients</a></li>
		<br/><br/>
		<li ><a href="listeRdv"><span class="glyphicon glyphicon-list"></span> Rendez_vous</a></li>
		<br/><br/>
		<li ><a href="modifProfil"><span class="glyphicon glyphicon-edit"></span> Modifier profil</a></li>
		<hr/>
	</ul>
</div>
	<div id="chemin">
		<a href="addPatient"><span class="glyphicon glyphicon-home"></span>Accueil</a><span class="glyphicon glyphicon-menu-right"></span>
		<span class="glyphicon glyphicon-list"></span> Patients
	</div>
		<div style="overflow-y:scroll; height:800px;" >
	
	<div id="milieu">
	<form action="rechPatient" 
		   enctype="multipart/form-data">
		 <div class="input-group">
		       <input id="rechP" style="width:91%;margin-left:0.1%;" type="text" name="name" class="form-control" placeholder="Rechercher patient">
		       <button style="margin-top:-34px;margin-left: 90.3%;" type="submit"  class="btn btn-default"><span class="glyphicon glyphicon-search"></span>Rechercher</button>
		    </div>
	</form>
	<jsp:useBean id="pagedListHolder" scope="request"
		type="org.springframework.beans.support.PagedListHolder" />
	<c:url value="/secretaire/listePatientSecr" var="pagedLink">
		<c:param name="p" value="~" />
	</c:url>
		<div id="rechPS">
	<table class="table table-bordered">
		<tr class="active" ><th style="width:100px">PHOTO</th><th>Numero dossier</th><th>Nom</th><th>Prénom</th><th>Sexe</th><th>Date de naissance</th><th>Numéro SS</th>
		<th>Adresse</th><th>Téléphone</th><th>Medecin</th><th >ACTION</th>
		</tr>
		<c:forEach items="${pagedListHolder.pageList}" var="pat">
			<tr>
				<c:if test="${pat.photo!=null}">
					<td>
						<img width="100px" height="90px" src="photo?idPat=${pat.id}"/>
					</td>
				</c:if>
				<c:if test="${pat.photo==null}">
					<td>
						<p style="margin-left:25%;"  >Pas de Photo</p>
					</td>
				</c:if>				
				<td>${pat.dossier.id}</td>
				<td>${pat.nom}</td>
				<td>${pat.prenom}</td>
				<td>${pat.sexe}</td>
				<td>${pat.datenais}</td>
				<td>${pat.numSS}</td>
				<td>${pat.adresse}</td>
				<td>${pat.tel}</td>
				<c:if test="${!empty pat.medecin.id }">
					<td> Dr ${pat.medecin.prenom } ${ pat.medecin.nom }</td>
				</c:if>
				<c:if test="${empty pat.medecin.id }">
					<td> </td>
				</c:if>
				<td style="height:90%;min-width:16%;width:16%;" id="action"><a style="font-size:18px;width:40%;height:100%;min-width:50px;" type="button" class="btn btn-default" href="afficherDossier?idPat=${pat.id}"><span class="glyphicon glyphicon-eye-open"></span></a> 
				<a style="background:#0080ff;font-size:18px;width:40%;height:100%;min-width:50px;" type="button" class="btn btn-default" href="modifPatient?idPat=${pat.id}"><span class="glyphicon glyphicon-edit"></span></a>
				</td>
			</tr>
		</c:forEach>
	</table>
	</div>
	<tg:paging  pagedLink="${pagedLink}" pagedListHolder="${pagedListHolder}"></tg:paging>
	</div>
	</div>
	</div>
</body>
</html>