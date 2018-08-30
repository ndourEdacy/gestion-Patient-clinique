<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<head>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/style1.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css" />
</head>
<div id="contenu">
	<div id="menu">
	<ul>
		<li><a href="addMedecin">Gérer Medecin</a></li>
		<li><a href="addInfirmier">Gérer Infirmier</a></li>
		<li><a href="addSecretaire">Gérer Secretaire</a></li>
		<li><a href="addAdministrateur">Gérer Administrateur</a></li>
		<li><a href="modifProfilAdmin"><span class="glyphicon glyphicon-user"></span> Gérer Profil</a></li>
		<li><a href="../deconnexion"><span class="glyphicon glyphicon-off"></span> Deconnexion</a></li>
		
	</ul>
</div>
	<div id="chemin">
		<a href="index"><span class="glyphicon glyphicon-home"></span>Accueil</a><span class="glyphicon glyphicon-menu-right"></span>
		<span class="glyphicon glyphicon-edit"></span> Infirmier<span class="glyphicon glyphicon-menu-right"></span>
	</div>
	<div id="milieu">
	<f:form modelAttribute="infirmier" action="saveInfirmier" 
	        method="post" enctype="multiprat/form-data">
		<f:input type="hidden" path="id"/>
			<f:errors path="id" cssClass="errors"></f:errors>
	     <div style="margin-left:15%;width: 70%;margin-top: 20px;margin-bottom: 20px;border:1px solid ;" class="form-group">
				
				<label style="margin-left:15px;margin-top:-5px" for="nominf">Nom </label>
				<f:input id="nominf" class="form-control" path="nom"/>
				<f:errors path="nom" cssClass="errors"></f:errors>
				
				<label style="margin-left:15px;margin-top:-5px" for="prenominf">Prénom </label>
				<f:input id="prenominf" class="form-control" path="prenom"/>
				<f:errors path="prenom" cssClass="errors"></f:errors>
				
				<label style="margin-left:15px;margin-top:-5px" for="serviceinf">Service </label>
				<f:select id="serviceinf" class="form-control" path="service.id" items="${services}" itemValue="id" itemLabel="libelle"></f:select>
				<f:errors path="service.id" cssClass="errors"></f:errors>
				
				<label style="margin-left:15px;margin-top:-5px" for="logininf">Login </label>
				<f:input id="logininf" class="form-control" path="login"/>
				<f:errors path="login" cssClass="errors"></f:errors>
				
				<label style="margin-left:15px;margin-top:-5px" for="passinf">Mot de passe </label>
				<f:input id="passinf" class="form-control" path="password"/>
				<f:errors path="password" cssClass="errors"></f:errors>
				
				<button type="submit" class="btn btn-default" ><span class="glyphicon glyphicon-ok"></span>Valider</button>
				<button type="reset"class="btn btn-default" ><span class="glyphicon glyphicon-remove"></span>Annuler</button>
			</div>
	</f:form>
</div>
<div id="milieu">
	<table class="table table-striped table-bordered">
		<tr class="success">
			<th>ID</th><th>SERVICE</th><th>NOM</th><th>PRENOM</th><th>TELEPHONE</th><th>ADRESSE</th><th>OPERATION</th>
		</tr>
		<c:forEach items="${infirmiers}" var="inf">
			<tr>
				<td>${inf.id}</td>
				<td>${inf.service.libelle}</td>
				<td>${inf.nom}</td>
				<td>${inf.prenom}</td>
				<td>${inf.tel}</td>
				<td>${inf.adresse}</td>
				<td><a href="suppInfirmier?idInf=${inf.id}">Supp</a>  <a href="modifInfirmier?idInf=${inf.id}">Modif</a></td>
			</tr>
		</c:forEach>
	</table>
</div>
</div>