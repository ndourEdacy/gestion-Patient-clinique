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
	<span class="glyphicon glyphicon-folder-open"></span>Dossier
	
	</div>
		<div style="overflow-y:scroll; height:800px;" >
	
	<div id="milieu">
		<h4>Dossier N° ${patient.dossier.id}</h4>
			<table class="table table-bordered">
				<tr active="active" ><th style="width:100px">Photo</th><th>Nom</th><th>Prenom</th><th>Date de Niassance</th><th>Sexe</th><th>Numéro SS</th><th>Téléphone</th><th>Adresse</th><th>Medecin</th></tr>
					<tr>
					<c:if test="${patient.photo!=null}">
						<td>
						<img  width="100px" height="90px" src="photo?idPat=${patient.id}"/>
						</td>
					</c:if>	
					<c:if test="${patient.photo==null}">
						<td>
						<p   >Pas de Photo</p>
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
						<td>
							${patient.numSS}
						</td>
						<td>
							${patient.tel}
						</td>
						<td>
							${patient.adresse}
						</td>
						<c:if test="${!empty patient.medecin.id }">
							<td> Dr ${patient.medecin.prenom } ${ patient.medecin.nom }</td>
						</c:if>
						<c:if test="${empty patient.medecin.id }">
							<td><a style="font-size:18px;width:40%;height:100%;min-width:170px;" type="button" class="btn btn-default" href="affecterMedecin?idPat=${patient.id}">Affecter Medecin</a></td>
						</c:if>
					</tr> 
			</table>
		</div>
		<div id="milieu">
		<h4>Visites</h4>
			<table class="table table-bordered">
				<tr active="active" ><th>Date</th><th>Motif</th><th>Recette Consultation</th><th>Recette Actes/Seances</th></tr>
				<c:forEach items="${visites}" var="vis">
					<tr>
						<td>${vis.date}</td>
						<td >${vis.motif}</td>
						<c:if test="${vis.recette>0}" >
							<td>${vis.recette} FCFA</td>
						</c:if>
						<c:if test="${vis.recette==0}" >
							<td><a href="modifVisite?idvisite=${vis.id}">Ajouter</a></td>
						</c:if>
						<td>
						<table class="table table-bordered">
							<tr><th>Montant</th><th>Type Paiement</th></tr>
								<c:if test="${vis.paiement>0}">
									<tr><td>${vis.paiement}</td><td>${vis.type}</td></tr>
								</c:if>
								<c:if test="${vis.paiement==0}">
									<tr><td id="action"><a href="addPaiement?idVisite=${vis.id}">Ajouter Facture</a></td></tr>
								</c:if>
						</table>
						</td>
					</tr>
					
				</c:forEach>
			</table>
			
			</div>
			<div id="milieu">
		    <h4>Rendez-vous</h4><a style=" margin-left:0%;min-width:100px;background:#64bc62;font-size:16px;width:8%;height:100%;" type="button" class="btn btn-default" href="addRdv?idPat=${idPat}" ><span class="glyphicon glyphicon-plus"></span>Ajouter</a>
			<table class="table table-bordered">
				<tr active="active"><th>Debut</th><th>Fin</th><th>Motif</th><th>ACTION</th></tr>
				<c:forEach items="${rdvs}" var="rdv">
					<tr>
						<td>${rdv.start}</td>
						<td>${rdv.end}</td>
						<td >${rdv.title}</td>
						 <td id="action"><a style="background:#0080ff;font-size:18px;width:40%;min-width:50px;height:100%;" type="button" class="btn btn-default" href="modifRdv?idRdv=${rdv.id}"><span class="glyphicon glyphicon-edit"></span></a>
						 <a  onclick="javascript:delete_Rdv(${rdv.id })" style="background:#ff3300;font-size:18px;min-width:50px;width:40%;height:100%;" type="button" class="btn btn-default"  ><span class="glyphicon glyphicon-remove"></span></a> </td> 
						 
					</tr>
				</c:forEach>
			 </table>
		</div>
		<div id="milieu">
			<h4>ATC/Traitements en cours</h4>
			<table class="table table-bordered">
				<tr active="active"><th>Date</th><th>Description</th></tr>
				<c:forEach items="${antecedants}" var="ant">
					<tr>
						<td>${ant.date}</td>
						<td>${ant.description}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div id="milieu">
			<h4>Ordonnances</h4>
				<table class="table table-bordered">
					<tr active="active"><th>Date</th><th>Medicament</th></tr>
					<c:forEach items="${ordonnances}" var="ord">
						<tr>
							<td>
								${ord.date}
							</td>
							<td>
								${ord.medicament}
							</td>
							
						</tr> 
					</c:forEach>
			</table>
		</div>
		<div id="milieu">
			<h4>Certficats</h4>
			<table class="table table-bordered" >
				<tr active="active" ><th>Date</th><th>Date Debut</th><th>Nombre Jours</th></tr>
				<c:forEach items="${certificats}" var="cert">
					<tr>
						<td>${cert.date}</td>
						<td>${cert.datedebut}</td>
						<td>${cert.nbjour}</td>
					</tr>
				</c:forEach>
			</table>
			</div>
		</div>
		</div>
		<script src="<%=request.getContextPath()%>/resources/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/Delete.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/bootstrap.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/cliniqueSupp.js"></script>
    	

	</body>
</html>