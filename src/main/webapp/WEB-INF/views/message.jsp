<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<div id="discution">
	<span id="msg_discution"><strong style="font-size:22px;margin-left:55px; text-align:center;font-family:italic;">Discussion</strong></span>
	<div id="user_connect" >
	    <c:forEach items="${liste}" var="user">
	       <span id="id_dis" style="display:none;">${user.id }</span>
	       <button type="button" style="margin:0px;margin-left:-2px;width:105%;font-size:18px;background:#f1f8e9;font-family:italic;color:black;text-align:center;" class="btn btn-primary b_dis" data-toggle="modal" data-target="#exampleModal" data-whatever="@getbootstrap" value="${user.id }">${user.prenom } ${user.nom }</button><hr>
	    </c:forEach>
	</div>
</div>
<div  class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="exampleModalLabel">New message</h4>
      </div>
      <span id="id_u" value="" style="display:none;">${user.id}</span>
      <div class="modal-body">
        <f:form modelAttribute="message" action="../adminPersonnel/envoiMessage?idExp=${user.id }"  method="post" id="formMessage">
          <f:input type="hidden" path="idexpediteur"  />
			<f:errors path="idexpediteur" cssClass="errors"></f:errors>
			<f:input type="hidden" path="idmessage"/>
			<f:errors path="idmessage" cssClass="errors"></f:errors>
			
			<f:input id="datevisite" class="form-control" type="hidden" path="date" />
			<f:errors path="date" cssClass="errors"></f:errors>
          <div class="form-group">
            <label for="recipient-name" class="control-label">A:</label>
            <input type="text" class="form-control" id="recipient-name" placeholder=" " disabled/>
          </div>
          <div class="form-group">
            <label for="message-text" class="control-label">Message:</label>
            <f:textarea class="form-control" id="message-text" path="contenu"/>
          </div>

        <button  type="submit" class="btn btn-primary">Send message</button>
         <button  style='margin-top:10px;' type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </f:form>
      </div>
      <hr>
       <div id="mesMessage">
        	
        </div>
    </div>
       
  </div>
  
</div>