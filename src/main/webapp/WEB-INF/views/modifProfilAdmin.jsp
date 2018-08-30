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
<div style="margin-bottom:0.5%;margin-left:1.6%;margin-top:1%;font-size: 23px;" ><span class="glyphicon glyphicon-user"></span>  Administrateur</div>

	<div id="deconnexion"> <a href="../deconnexion"><span class="glyphicon glyphicon-off"></span> Deconnexion</a>
</div>
<div id="profil">
<h4><img src="../resources/img/logoClinique.jpg" width="80px" height="40px" style="margin-left:5%;border-radius:40px 40px 40px 40px;"/>EASY-CLINIQUE</h4>
<hr>
	<c:if test="${user.id>0}">
		<img style="margin-left:5%;border-radius:40px 40px 40px 40px;"  width="80px" height="80px" src="photoAdmin?idAdmin=${user.id}"/>
		<div style="padding-top:20px;padding-bottom:35px;padding-left:5%;color:white;font-size:22px;font-family:italic;">${user.prenom} ${user.nom}</div>
	</c:if>
	
</div>
  <div id="menu">
	 <ul  class="nav nav-pills nav-stacked">
	 <hr>
		<li><a href="addMedecin"><span class="glyphicon glyphicon-edit"></span> Gérer Medecin</a></li>
		<br><br>
		<li><a href="addSecretaire"><span class="glyphicon glyphicon-edit"></span> Gérer Secretaire</a></li>
		<br><br>
		<li><a href="addAdministrateur"><span class="glyphicon glyphicon-edit"></span> Gérer Administrateur</a></li>
		<br><br>
		<li class="activation"><a href="modifProfilAdmin"><span class="glyphicon glyphicon-edit"></span> Gérer Profil</a></li>
		<hr/>
	 </ul>
  </div>
  <div id="chemin">
		<a href="addMedecin"><span class="glyphicon glyphicon-home"></span>Accueil</a><span class="glyphicon glyphicon-menu-right"></span>
		<span class="glyphicon glyphicon-edit"></span> Profil<span class="glyphicon glyphicon-menu-right"></span>
  </div>
  	<div style="overflow-y:scroll; height:800px;" >
  
  <div id="milieu">
	  <f:form modelAttribute="administrateur" action="saveModifProfil" 
	        method="post" enctype="multipart/form-data">
	     	     <div style="margin-left:15%;width: 70%;margin-top: 20px;border:2px solid;border-bottom-color: black;border-right-color: #f1f1f1;border-left-color: #f3f3f3;border-top-color: black" class="form-group">

			<f:input type="hidden" path="id"/>
			<f:errors path="id" cssClass="errors"></f:errors>
			
			<label style="margin-left:15px;margin-top:-5px" for="nomadmin">Nom</label> 
			<f:input class="form-control" id="nomadmin" path="nom"/>
			<f:errors path="nom" cssClass="errors"></f:errors><br>
			
			<label style="margin-left:15px;margin-top:-5px" for="prenoadmin">Prenom</label> 
			<f:input class="form-control" id="prenoadmin" path="prenom"/>
			<f:errors path="prenom" cssClass="errors"></f:errors><br>
			
			<label style="margin-left:15px;margin-top:-5px" for="adresseadmin">Adresse</label> 
			<f:input class="form-control" id="adresseadmin" path="adresse"/>
			<f:errors path="adresse" cssClass="errors"></f:errors>
			
			<label style="margin-left:15px;margin-top:-5px" for="teladmin">Téléphone</label> 
			<f:input class="form-control" id="teladmin" path="tel"/>
			<f:errors path="tel" cssClass="errors"></f:errors><br>
			
			<label style="margin-left:15px;margin-top:-5px" for="datenaisadmin">Date de naissance</label> 
			<f:input type="date" class="form-control hasDatepicker" id="datepicker " path="datenais"/>
			<f:errors path="datenais" cssClass="errors"></f:errors><br>
			
			<label style="margin-left:15px;margin-top:-5px" for="lieunaisadmin">Lieu de naissance</label> 
			<f:input class="form-control" id="loginpat" path="lieu"/>
			<f:errors path="lieu" cssClass="errors"></f:errors><br>
			
			<label style="margin-left:15px;margin-top:-5px" for="sexeadmin">Sexe</label> 
			<f:select class="form-control" id="lieunaisadmin" path="sexe" >
				<f:option value="Masculin">Masculin</f:option>
				<f:option value="Feminin">Feminin</f:option>
			</f:select>
			<f:errors path="sexe" cssClass="errors"></f:errors><br>
			
			<label style="margin-left:15px;margin-top:-5px" for="loginadmin">Login</label> 
			<f:input class="form-control" id="loginadmin" path="login"/>
			<f:errors path="login" cssClass="errors"></f:errors><br>
			
			<label style="margin-left:15px;margin-top:-5px" for="passadmin">Mot de passe</label> 
			<f:input class="form-control" id="passadmin" path="password"/>
			<f:errors path="password" cssClass="errors"></f:errors><br>
			
			<label style="margin-left:15px;margin-top:-5px" for="question">Question secrète </label>
				<f:select class="form-control"  id="question" path="question">
				<f:option value="1">Quel est nom de jeune fille de votre mère?</f:option>
				<f:option value="2">Quel est nom de votre premier/e copain/copine?</f:option>
				<f:option value="3">Quel est votre plat préféré?</f:option>
				<f:option value="4">Quel est votre plat préféré?</f:option>
				
				</f:select><br>
		<label style="margin-left:15px;margin-top:-5px" for="reponse">Votre réponse</label>
		<f:input  class="form-control" id="reponse" path="reponse"/>
			<br>
				<label style="margin-left:15px;margin-top:-5px" for="Photo">Photo </label>
				<c:if test="${administrateur.photo!=null}">
					<img style="margin-left:55px;"width="150" height="150px" src="photoAdmin?idAdmin=${administrateur.id}"/>
				</c:if>
				<br/>
				<input style="margin:15px; width:350px;height:30px;" type="file"  name="file" />
				<br><br><br>
			<button type="submit" style="min-width:100px;background:#64bc62;font-size:16px;width:8%;height:100%;" class="btn btn-default" ><span   class="glyphicon glyphicon-ok"></span>Valider</button>
			<button onclick="javascript:retour_Admin()" style="min-width:100px;background:#ff3300;font-size:16px;width:8%;height:100%;" class="btn btn-default" type="reset"><span class="glyphicon glyphicon-remove"></span>Annuler</button> </div>
	 </f:form>
  </div>
</div>
</div>
    <script src="<%=request.getContextPath()%>/resources/js/Delete.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/calendrier.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/cliniqueSupp.js"></script>
