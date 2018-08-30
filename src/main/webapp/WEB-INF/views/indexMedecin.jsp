<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<html>
<head>
<link rel="stylesheet" type="text/css" href="../resources/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="../resources/css/style1.css" />
</head>
<body>
<div id="contenu">
	<%@ include file="message.jsp" %>

<div id="menu">
	<ul>
		<li><a href="listePatients"><span class="glyphicon glyphicon-list"></span> Liste Dossiers</a></li>
		<li><a href="listeRdv"><span class="glyphicon glyphicon-list"></span> Liste Rendez_vous</a></li>
		<li><a href="modifProfil"><span class="glyphicon glyphicon-edit"></span> Modifier profil</a></li>
		<li><c:if test="${!empty user}" >
		<a href="../deconnexion"><span class="glyphicon glyphicon-off"></span> Deconnexion</a></c:if></li>
	</ul>
</div>
</div>	
<script src="<%=request.getContextPath()%>/resources/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/Delete.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/bootstrap.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/cliniqueSupp.js"></script>
</body>
</html>