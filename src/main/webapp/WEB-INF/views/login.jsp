<!DOCTYPE html>
<html>
<head>
       <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

		<link rel="stylesheet" type="text/css" href="/clinique/resources/css/style1.css" />
	<link rel="stylesheet" type="text/css" href="/clinique/resources/css/bootstrap.min.css" />
	<style>
	  body{
	    background: #f1f8e9;
	  }
	  
	 
	  #login-submit{
	  background: #c1e2b3;
	  
	  }
	
	 
	  
	  
	</style>
</head>
<body >
  
  	
        
          <div class="login">
          	<div style="width:100%;height:22%; background: #c1e2b3;"><br><h4>Entrer votre Pseudo et votre mot de passe pour vous connecter<h4></h3> <h1 style="margin-top:-80px;margin-left:90%;font-size:45px;" ><span class="glyphicon glyphicon-lock"></span></h1></div>
          		<br>
                <form id="login-form" role="form" style="display: block;" action="dologin" method="post">
                   	 <f:errors style="margin-left:30%;color:red;font-family: italic;font-size: 18px;" path="password" cssClass="errors">${message }</f:errors>
                   	 <br>
                    <label style="margin-left:15px;font-family: italic;font-size: 22px;" for="Pseudo">Pseudo</label>
                    <input id="username" name="login" tabindex="1" style="height:50px;font-size: 22px;" placeholder="Username" type="text" class="form-control" value=""/>
                  
					<br>
                    <label style="margin-left:15px;font-family: italic;font-size: 22px;" for="Mot de passe">Mot de passe</label>
                    <input id="password" name="password" tabindex="2" name="password" style="height:50px;font-size: 22px;" placeholder="Password" type="password" class="form-control" value=""/>
            		
                   
                	<br>
                    <button style="height:50px;font-family: italic;font-size: 22px;" type="submit" name="login-submit" id="login-submit" tabindex="4" class="form-control btn btn-login" /><span class="glyphicon glyphicon-log-in"></span> Se connecter</button>
                    
                </form>
      				<h5 style="margin-left:15px;font-family: italic;font-size: 18px;"><a href="recupCompte">Mot de passe oublié ?</a></h5>
          </div>
		

  
</body>
</html>