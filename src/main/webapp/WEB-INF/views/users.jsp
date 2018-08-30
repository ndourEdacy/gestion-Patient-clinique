<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<head>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/bootstrap.js"></script>
	<link rel="stylesheet" type="text/css" href="../resources/css/style1.css" />
	<link rel="stylesheet" type="text/css" href="../resources/css/bootstrap.min.css" />
</head>
<div class="cadre">
	<f:form modelAttribute="infirmier" action="saveInfirmier" 
	        method="post" enctype="multiprat/form-data">
		<table>
			<tr>
				<td>ID Secretaire:</td>
				<td><f:input path="id"/></td>
				<td><f:errors path="id" cssClass="errors"></f:errors></td>
			</tr>
			<tr>
				<td>Nom Secretaire:</td>
				<td><f:input path="nom"/></td>
				<td><f:errors path="nom" cssClass="errors"></f:errors></td>
			</tr>
			<tr>
				<td>Prenom Secretaire:</td>
				<td><f:input path="prenom"/></td>
				<td><f:errors path="prenom" cssClass="errors"></f:errors></td>
			</tr>
			<tr>
				<td>Login:</td>
				<td><f:input path="login"/></td>
				<td><f:errors path="login" cssClass="errors"></f:errors></td>
			</tr>
			<tr>
				<td>Mot de passe :</td>
				<td><f:input path="password"/></td>
				<td><f:errors path="password" cssClass="errors"></f:errors></td>
			</tr>
			<tr>
				<td><input type="submit" value="Valider"/></td>
			</tr>
		</table>
		
	</f:form>
</div>
<div  class="cadre">
	<table class="tabPersonnel">
		<tr>
			<th>ID</th><th>SERVICE</th><th>NOM</th><th>PRENOM</th><th>TELEPHONE</th><th>ADRESSE</th><th>OPERATION</th>
		</tr>
		<c:forEach items="${secretaires}" var="sec">
			<tr>
				<td>${sec.id}</td>
				<td>${sec.service.libelle}</td>
				<td>${sec.nom}</td>
				<td>${sec.prenom}</td>
				<td>${sec.tel}</td>
				<td>${sec.adresse}</td>
				<td><a href="suppSecretaire?idSecr=${sec.id}">Supp</a>  <a href="modifSecretaire?idSecr=${sec.id}">Modif</a></td>
			</tr>
		</c:forEach>
	</table>
</div>