<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html" charset="ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="../resources/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="../resources/css/style1.css" />
	<title>Insert title here</title>
</head>
<body>	
<div id="contenu">
<%@ include file="message.jsp" %>
<div style="margin-bottom:0.5%;margin-left:1.6%;margin-top:1%;font-size: 23px;" ><span class="glyphicon glyphicon-user"></span> Medecin</div>

	<div id="deconnexion"> <a href="../deconnexion"><span class="glyphicon glyphicon-off"></span> Deconnexion</a>
</div>

<div id="profil">
<h4><img src="../resources/img/logoClinique.jpg" width="80px" height="40px" style="margin-left:5%;border-radius:40px 40px 40px 40px;"/>EASY-CLINIQUE</h4>
<hr>
	<c:if test="${user.id>0}">
		<img style="margin-left:5%;border-radius:40px 40px 40px 40px;"  width="80px" height="80px" src="photoPMed?idMed=${user.id}"/>
		<div style="padding-top:20px;font-family:italic;padding-bottom:35px;padding-left:5%">Dr ${user.prenom} ${user.nom}</div>
	</c:if>
	
</div>
	<div id="menu"> 
		
		<ul class="nav nav-pills nav-stacked">
		<hr/>
			<li ><a href="listePatients"><span class="glyphicon glyphicon-list"></span> Liste Dossiers</a></li>
			<br><br>
			<li  ><a href="listeRdv"><span class="glyphicon glyphicon-list"></span> Liste Rendez_vous</a></li>
			<br><br>
			<li class="activation"><a href="modifProfil"><span class="glyphicon glyphicon-edit"></span> Modifier profil</a></li>
			<hr/>
		
		</ul>
	</div>
	<div id="chemin">
		<a href="listePatients"><span class="glyphicon glyphicon-home"></span>Accueil</a><span class="glyphicon glyphicon-menu-right"></span>
		<span class="glyphicon glyphicon-edit"></span> Profil<span class="glyphicon glyphicon-menu-right"></span>
	</div>
	<div style="overflow-y:scroll; height:800px;" >
	
<div id="milieu">
	<f:form modelAttribute="medecin" action="saveModifProfil" 
	        method="post" enctype="multipart/form-data">
	     <div style="margin-left:15%;width: 70%;margin-top: 20px;border:2px solid;border-bottom-color: black;border-right-color: #f1f1f1;border-left-color: #f3f3f3;border-top-color: black" class="form-group">
		<f:input type="hidden" path="id"/>
		<f:errors path="id" cssClass="errors"></f:errors>
				
		<label style="margin-left:15px;margin-top:-5px" for="nompat">Nom</label>
			<f:input  class="form-control" id="nompat" path="nom"/>
			<f:errors path="nom" cssClass="errors"></f:errors><br>
		
		<label style="margin-left:15px;margin-top:-5px" for="prenompat">Prénom</label> 
			<f:input class="form-control" id="prenompat"  path="prenom"/>
			<f:errors path="prenom" cssClass="errors"></f:errors><br>
		
		<label style="margin-left:15px;margin-top:-5px" for="adressepat">Adresse</label>
			<f:input  class="form-control" id="adressepat" path="adresse"/>
			<f:errors path="adresse" cssClass="errors"></f:errors><br>
		
	    <label style="margin-left:15px;margin-top:-5px" for="telpat">Téléphone</label>
	    <f:input  class="form-control" id="telpat" path="tel"/>
	    <f:errors path="tel" cssClass="errors"></f:errors><br>
	    
	    <label style="margin-left:15px;margin-top:-5px" for="datenaispat">Date de naissance</label> 
	    <f:input  class="form-control hasPicker" id="datepicker" type="date" path="datenais"/>
	    <f:errors path="datenais" cssClass="errors"></f:errors><br>
	    
	    <label style="margin-left:15px;margin-top:-5px" for="lieunaispat">Lieu de naissance</label>
	    <f:input  class="form-control" id="lieunaispat" path="lieu"/>
	    <f:errors path="lieu" cssClass="errors"></f:errors><br>
	    
		<label style="margin-left:15px;margin-top:-5px" for="sexepat">Sexe</label>
		<f:select  class="form-control" id="sexepat" path="sexe" >
					<f:option value="Masculin">Masculin</f:option>
					<f:option value="Feminin">Feminin</f:option>
		</f:select>
		<f:errors path="sexe" cssClass="errors"></f:errors><br>
		
		<label style="margin-left:15px;margin-top:-5px" for="specpat">Spécialité</label>
		<f:select  class="form-control" id="specpat" path="specialite.id" items="${specialite}" itemValue="id" itemLabel="libelle"></f:select>
		<f:errors path="specialite.id" cssClass="errors"></f:errors><br>
		
		<label style="margin-left:15px;margin-top:-5px" for="loginpat">Login</label>
		<f:input  class="form-control" id="loginpat" path="login"/>
		<f:errors path="login" cssClass="errors"></f:errors><br>
		
		<label style="margin-left:15px;margin-top:-5px" for="passpat">Mot de passe</label>
		<f:input  class="form-control" id="passpat" path="password"/>
		<f:errors path="password" cssClass="errors"></f:errors><br>
		<label style="margin-left:15px;margin-top:-5px" for="question">Question secrète </label>
				<f:select class="form-control"  id="question" path="question">
				<f:option value="1">Quel est nom de jeune fille de votre mére?</f:option>
				<f:option value="2">Quel est nom de votre premier/e copain/copine?</f:option>
				<f:option value="3">Quel est votre plat préféré?</f:option>
				<f:option value="4">Quel est votre plat préféré?</f:option>
				
				</f:select><br>
		<label style="margin-left:15px;margin-top:-5px" for="reponse">Votre réponse</label>
		<f:input  class="form-control" id="reponse" path="reponse"/>
		<br>
				<label style="margin-left:15px;margin-top:-5px" for="Photo">Photo </label>
				<c:if test="${medecin.photo!=null}">
					<img style="margin-left:55px;"width="150" height="150px" src="photoPMed?idMed=${medecin.id}"/>
				</c:if>
				<br/>
				<input style="margin:15px; width:350px;height:30px;" type="file"  name="file" />
				<br><br><br>
		
				<button type="submit" style="min-width:100px;background:#64bc62;font-size:16px;width:8%;height:100%;" class="btn btn-default" ><span   class="glyphicon glyphicon-ok"></span>Valider</button>
			<button style="min-width:100px;background:#ff3300;font-size:16px;width:8%;height:100%;" class="btn btn-default" type="reset"><span class="glyphicon glyphicon-remove"></span>Annuler</button>	
	</div>
		
	</f:form>
	</div>
	</div>
</div>

<script src="<%=request.getContextPath()%>/resources/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/calendrier.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/cliniqueSupp.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/bootstrap.js"></script>
