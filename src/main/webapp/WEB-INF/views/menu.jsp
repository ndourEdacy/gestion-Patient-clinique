
<body>
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
<div id="menu">
	<ul class="nav nav-pills nav-stacked">
	<hr/>
		<li ><a href="addPatient"><span class="glyphicon glyphicon-plus"></span> Nouveau Patient</a></li>
		<br/><br>
		<li><a  href="listePatientSecr"><span class="glyphicon glyphicon-list"></span> Liste Patients</a></li>
		<br/><br>
		<li><a href="listeRdv"><span class="glyphicon glyphicon-list"></span> Rendez_vous</a></li>
		<br/><br>
		<li><a href="modifProfil"><span class="glyphicon glyphicon-edit"></span> Modifier profil</a></li>
		<hr/>
	</ul>
</div>
	
</body>
</html>