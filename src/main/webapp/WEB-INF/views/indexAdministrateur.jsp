<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../resources/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="../resources/css/style1.css" />
<title>Insert title here</title>
</head>
<body>
<div id="contenu">
<%@ include file="message.jsp" %>
<div id="menu">
	<ul>
		<li><a href="addMedecin">Ajouter  Utilusateur</a></li>
		<li><a href="addInfirmier">Gérer Infirmier</a></li>
		<li><a href="addSecretaire">Gérer Secretaire</a></li>
		<li><a href="addAdministrateur">Gérer Administrateur</a></li>
		<li><a href="modifProfilAdmin"><span class="glyphicon glyphicon-user"></span> Gérer Profil</a></li>
		<li><a href="../deconnexion"><span class="glyphicon glyphicon-off"></span> Deconnexion</a></li>
		
	</ul>
</div>
</div>
<script src="<%=request.getContextPath()%>/resources/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/Delete.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/bootstrap.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/cliniqueSupp.js"></script>
</body>
</html>