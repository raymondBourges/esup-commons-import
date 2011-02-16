<%@ include file="/WEB-INF/jsp/include.jsp" %>

<div class="portlet-title">
  <h2>
    <spring:message code="edit.title"/>
  </h2>
</div>



<portlet:actionURL var="setUsername">
  <portlet:param name="action" value="setUsername"/>
</portlet:actionURL>

<form id="${n}setUsername" class="setUsername" action="${setUsername}" method="post">
  <div>
    <p>
      <label class="portlet-form-label">
        <spring:message code="edit.username"/>
        :
      </label>
    </p>
    <input name="username" class="portlet-form-input-field" value="${username}"/>
    <spring:message var="setUsernameMessage" code="edit.set.username.button"/>
  </div>
  <input type="submit" value="${setUsernameMessage}" class="portlet-form-button"/>
</form>


<portlet:renderURL var="formDoneAction" portletMode="VIEW"/>
<p>
  <button onclick="window.location='${formDoneAction}'" class="portlet-form-button">
    <spring:message code="edit.done.button"/>
  </button>
</p>
