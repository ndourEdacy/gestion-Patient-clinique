<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<head>
<meta http-equiv="Content-Type" content="text/html" charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="../resources/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="../resources/css/style1.css" />
	<title>Insert title here</title>
	
</head>
<body>	
<div id="contenu">
<div style="margin-bottom:0.5%;margin-left:1.6%;margin-top:1%;font-size: 23px;" ><span class="glyphicon glyphicon-user"></span> Medecin</div>
		<%@ include file="message.jsp" %>
	
	<%@ include file="menuMed.jsp" %>

	<div id="chemin">
	<a href="indexMedecin"><span class="glyphicon glyphicon-home"></span>Accueil</a><span class="glyphicon glyphicon-menu-right"></span>
	<a  href="listePatients"><span class="glyphicon glyphicon-list"></span> Patients</a><span class="glyphicon glyphicon-menu-right"></span>
	<span class="glyphicon glyphicon-folder-open"></span>Dossier
	</div>
	<div style="overflow-y:scroll; height:800px;" >
	 <div id="milieu" >
			<h4>Dossier N° ${patient.dossier.id}</h4>
			<table class="table table-bordered ">
				<tr class="active" ><th style="width:100px">Photo</th><th>Nom</th><th>Prenom</th><th>Date de Niassance</th><th>Sexe</th></tr>
					<tr>
					<c:if test="${patient.photo!=null}">
						<td>
						<img   width="100px" height="90px" src="photoP?idPat=${patient.id}"/>
						</td>
					</c:if>	
					<c:if test="${patient.photo==null}">
						<td>
						<p style="margin-left:25%;border-radius:40px 40px 40px 40px;"  >Pas de Photo</p>
						</td>
					</c:if>
						<td>
							${patient.nom}
						</td>
						<td>
							${patient.prenom}
						</td>
						<td>
							${patient.datenais}
						</td>
						<td>
							${patient.sexe}
						</td>
					</tr> 
			</table>
		</div>
	
	<div id="milieu">
	<h4>Visites</h4><a style=" margin-left:0%;min-width:100px;background:#00ff80;font-size:16px;width:8%;height:100%;" type="button" class="btn btn-default" href="addVisite?idPat=${idPat}"><span class="glyphicon glyphicon-plus"></span>Ajouter</a>
		<table class="table table-bordered ">
			<tr class="active"><th>Date</th><th>Motif</th><th>Action</th></tr>
			<c:forEach items="${visites}" var="vis">
			 <c:set var="idVis" value="${vis.id}" scope="page"/>
				<tr>
					<td>${vis.date}</td>
					<td >${vis.motif}</td>
					<td id="action"><a style="background:#0080ff;min-width:40px;font-size:18px;width:30%;height:100%;" type="button" class="btn btn-default" href="modifVisite?idVisite=${vis.id}"><span class="glyphicon glyphicon-edit" ></span></a>
					<a onclick="javascript:delete_Visite(${vis.id })" style="background:#ff3300;font-size:18px;min-width:40px;width:30%;height:100%;" type="button" class="btn btn-default"  ><span class="glyphicon glyphicon-remove" id="suppV" ></span></a></td>
				</tr>	
			</c:forEach>
		</table>
	</div>
	
	<div id="milieu">
		<h4>ATC/Traitements en cours</h4><a style=" margin-left:0%;min-width:100px; background:#00ff80;font-size:16px;width:8%;height:100%;" type="button" class="btn btn-default" href="addAntecedant?idPat=${idPat}"><span class="glyphicon glyphicon-plus"></span>Ajouter</a>
		<table class="table table-bordered ">
			<tr class="active"><th>Date</th><th>Description</th>
			<th>Action</th></tr>
			<c:forEach items="${antecedants}" var="ant">
				<tr>
					<td>${ant.date}</td>
					<td>${ant.description}</td>
					<td id="action"><a style="background:#0080ff;min-width:40px;font-size:18px;width:30%;height:100%;" type="button" class="btn btn-default" href="modifAnt?idAnt=${ant.id}"><span class="glyphicon glyphicon-edit"></span></a>
					<a onclick="javascript:delete_Antecedant(${ant.id })" style="background:#ff3300;min-width:40px;font-size:18px;width:30%;height:100%;" type="button" class="btn btn-default" ><span class="glyphicon glyphicon-remove"></span></a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	
	<div id="milieu">
		<h4>Bilans</h4><a style=" margin-left:0%;min-width:100px; background:#00ff80;font-size:16px;width:8%;height:100%;" type="button" class="btn btn-default" href="addBilan?idPat=${idPat}"><span class="glyphicon glyphicon-plus"></span>Ajouter</a>
		<table class="table table-bordered">
			<tr class="active" ><th>Date</th><th>Libellé</th>
			<th>Action</th></tr>
			<c:forEach items="${bilan}" var="bil">
				<tr>
					<td>${bil.date}</td>
					<td>${bil.libelle}</td>
					<td id="action"><a style="background:#0080ff;min-width:40px;font-size:18px;width:30%;height:100%;" type="button" class="btn btn-default" href="modifBilan?idBil=${bil.id}" title="modifier"><span class="glyphicon glyphicon-edit"></span></a>
					<a onclick="javascript:delete_Bilan(${bil.id })" style="background:#ff3300;min-width:40px;font-size:18px;width:30%;height:100%;" type="button" class="btn btn-default"  title="supprimer"><span class="glyphicon glyphicon-remove"></span></a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	
	<div id="milieu">
			<h4>Ordonnances</h4><a style=" margin-left:0%;min-width:100px; background:#00ff80;font-size:16px;width:8%;height:100%;" type="button" class="btn btn-default" href="addOrdonnance?idPat=${idPat}"><span class="glyphicon glyphicon-plus"></span>Ajouter</a>
			<table class="table table-bordered">
				<tr class="active"><th>Date</th><th>Medicament</th><th>Action</th></tr>
				<c:forEach items="${ordonnances}" var="ord">
					<tr>
						<td>
							${ord.date}
						</td>
						<td>
							${ord.medicament}
						</td>
						<td id="action">
							<a style="background:#0080ff;font-size:18px;width:30%;min-width:40px;height:100%;" type="button" class="btn btn-default" href="modifOrdonnance?idOrd=${ord.id}" title="modifier"><span class="glyphicon glyphicon-edit"></span></a>
							<a onclick="javascript:delete_Ordonnance(${ord.id })" style="background:#ff3300;font-size:18px;min-width:40px;width:30%;height:100%;" type="button" class="btn btn-default" title="supprimer" class="suppOrd"><span class="glyphicon glyphicon-remove"></span></a>
							<a style="font-size:18px;width:70%;height:100%;min-width:80px;" type="button" class="btn btn-default" href="generatepdfOrd/?idOrd=${ord.id}" title="imprimer"> <span class="glyphicon glyphicon-print"></span></a>
						</td>
					</tr> 
				</c:forEach>
			</table>
		</div>
		<div id="milieu">	
			<h4>Certificats</h4><a style=" margin-left:0%;min-width:100px;background:#00ff80;font-size:16px;width:8%;height:100%;" type="button" class="btn btn-default" href="addCertificat?idPat=${idPat}"><span class="glyphicon glyphicon-plus"></span>Ajouter</a>
			<table class="table table-bordered">
				<tr class="active"><th>Date</th><th>Date Debut</th><th>Nombre jours </th><th>Action</th></tr>
				<c:forEach items="${certificat}" var="cert">
					<tr>
						<td>
							${cert.date}
						</td>
						<td>
							${cert.datedebut}
						</td>
						<td>
							${cert.nbjour}
						</td>
						<td id="action" ><a style="background:#0080ff;width:30%;min-width:40px;font-size:18px;height:100%;" type="button" class="btn btn-default" href="modifCertificat?idCert=${cert.id}" title="modifier"><span class="glyphicon glyphicon-edit"></span></a>
						<a onclick="javascript:delete_Certificat(${cert.id })" style="background:#ff3300;width:30%;min-width:40px;font-size:18px;height:100%;" type="button" class="btn btn-default"  title="supprimer"><span class="glyphicon glyphicon-remove"></span></a>
						<a style="font-size:18px;width:70%;min-width:80px;height:100%;" type="button" class="btn btn-default" href="generatepdfCert?idCert=${cert.id}" title="imprimer"><span class="glyphicon glyphicon-print"></span></a></td>
					</tr> 
				</c:forEach>
			</table>
		</div>
	
	</div>
	</div>
	
	
<script src="<%=request.getContextPath()%>/resources/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/cliniqueSupp.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/Delete.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/bootstrap.js"></script>
 
	 
</body>
</html>