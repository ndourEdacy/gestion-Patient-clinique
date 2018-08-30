<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html" charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="../resources/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="../resources/css/style1.css" />
	<script src="../resources/js/jquery.min.js"></script>
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
<%@ include file="message.jsp" %>
  <div id="menu">
	 <ul  class="nav nav-pills nav-stacked">
	 <hr>
		<li><a href="addMedecin"><span class="glyphicon glyphicon-edit"></span> Gérer Medecin</a></li>
		<br><br>
		<li><a href="addSecretaire"><span class="glyphicon glyphicon-edit"></span> Gérer Secretaire</a></li>
		<br><br>
		<li class="activation" ><a href="addAdministrateur"><span class="glyphicon glyphicon-edit"></span> Gérer Administrateur</a></li>
		<br><br>
		<li><a href="modifProfilAdmin"><span class="glyphicon glyphicon-edit"></span> Gérer Profil</a></li>
		<hr/>
	 </ul>
  </div>
 
	<div id="chemin">
		<a href="addMedecin"><span class="glyphicon glyphicon-home"></span> Accueil</a><span class="glyphicon glyphicon-menu-right"></span>
		<span class="glyphicon glyphicon-edit"></span> Administrateur
	</div>
		<div style="overflow-y:scroll; height:800px;" >
	
	<div id="milieu">
		<f:form modelAttribute="administrateur" action="saveAdministrateur" 
	        method="post" enctype="multipart/form-data">
		  <f:input type="hidden" path="id"/>
			<f:errors path="id" cssClass="errors"></f:errors>
	     <div style="margin-left:15%;width: 70%;margin-top: 20px;border:2px solid;border-bottom-color: black;border-right-color: #f1f1f1;border-left-color: #f3f3f3;border-top-color: black" class="form-group">
				
				<label style="margin-left:15px;margin-top:-5px" for="nomadmin">Nom </label>
				<f:input id="nomadmin" class="form-control" path="nom"/>
				<f:errors path="nom" cssClass="errors"></f:errors>
				
				<label style="margin-left:15px;margin-top:-5px" for="prenomadmin">Prénom </label>
				<f:input id="prenomadmin" class="form-control" path="prenom"/>
				<f:errors path="prenom" cssClass="errors"></f:errors>
				
				
				<label style="margin-left:15px;margin-top:-5px" for="loginadmin">Login </label>
				<f:input id="loginadmin" class="form-control" path="login"/>
				<f:errors path="login" cssClass="errors"></f:errors>
				
				<label style="margin-left:15px;margin-top:-5px" for="passsadmin">Mot de passe </label>
				<f:input id="passsadmin" class="form-control" path="password"/>
				<f:errors path="password" cssClass="errors"></f:errors>
				<label style="margin-left:15px;margin-top:-5px" for="Photo">Photo </label>
				<c:if test="${administrateur.id>0}">
					<img style="margin-left:55px;"width="150" height="150px" src="photoAdmin?idAdmin=${administrateur.id}"/>
				</c:if>
				<br/>
				<input style="margin:15px; width:350px;height:30px;" type="file"  name="file" />
				<br><br><br>
				<button type="submit" style="min-width:100px;background:#64bc62;font-size:16px;width:8%;height:100%;" class="btn btn-default" ><span   class="glyphicon glyphicon-ok"></span>Valider</button>
			<button style="min-width:100px;background:#ff3300;font-size:16px;width:8%;height:100%;" class="btn btn-default" type="reset"><span class="glyphicon glyphicon-remove"></span>Annuler</button>
			</div>	
		</f:form>
	</div>
<div id="milieu">
	<table class="table table-striped table-bordered">
		<tr>
			<th style="width:100px">PHOTO</th><th>ID</th><th>NOM</th><th>PRENOM</th><th>TELEPHONE</th><th>ADRESSE</th><th style="height:90%;width:200px;min-width:100px;">ACTION</th>
		</tr>
		<c:forEach items="${administrateurs}" var="admin">
			<tr>
			   <td>
				<c:if test="${admin.photo!=null}">
				<img width="100px" height="100px" src="photoAdmin?idAdmin=${admin.id}"/>
				</c:if> 
				<c:if test="${admin.photo==null}">
				<p>Pas de photo</p>
				</c:if>
				</td>
				
				<td>${admin.id}</td>
				<td>${admin.nom}</td>
				<td>${admin.prenom}</td>
				<td>${admin.tel}</td>
				<td>${admin.adresse}</td>
				<td >
				 <a style="background:#0080ff;font-size:18px;width:30%;height:100%;min-width:40px;" type="button" class="btn btn-default" href="modifAdministrateur?idAdmin=${admin.id}"><span class="glyphicon glyphicon-edit"></span></a>
				<a onclick="javascript:delete_Admin(${admin.id })" style="background:#ff3300;font-size:18px;width:30%;height:100%;min-width:40px;" type="button" class="btn btn-default"><span class="glyphicon glyphicon-remove"></span></a></td> 
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