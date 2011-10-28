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
	  <c:forEach var="task" items="${taskList}" >
		<li>${task.id} - ${task.title} : ${task.description} (<a href="${urlTask}${task.id}">D�tail</a>)</li>
	  </c:forEach>
  </ul>
  </div>
  

</div>

