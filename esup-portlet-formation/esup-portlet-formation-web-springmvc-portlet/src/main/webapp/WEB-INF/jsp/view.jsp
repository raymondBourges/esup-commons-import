<%@ include file="/WEB-INF/jsp/include.jsp"%>

<portlet:renderURL var="renderRefreshUrl" />

<div class="portlet-title">
  <h2>
    ${userFromEC2.login} !
  </h2>
</div>

<div class="portlet-section">

  <div class="portlet-section-body">

	<ul>
    	<li><spring:message code="view.helloString" arguments="${userFromEC2.login}"/></li>
    	<li><spring:message code="view.helloString" arguments="${usernamePref}"/></li>
    	<li><spring:message code="view.helloString" arguments="${remoteUser}"/></li>
	</ul>
<!--   <ul> -->
<%-- 	  <c:forEach var="task" items="${taskList}" > --%>
<%-- 		<li>${task.id} - ${task.title} : ${task.description}</li> --%>
<%-- 	  </c:forEach> --%>
<!--   </ul> -->
  </div>
  

</div>

