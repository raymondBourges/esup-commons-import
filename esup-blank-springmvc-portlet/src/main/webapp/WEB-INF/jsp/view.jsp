<%@ include file="/WEB-INF/jsp/include.jsp"%>

<portlet:renderURL var="renderRefreshUrl" />

<div class="portlet-title">
  <h2>
    ${user.username} !
  </h2>
</div>

<div class="portlet-section">

  <div class="portlet-section-body">

    <span><spring:message code="view.helloString" arguments="${user.username}"/></span>

  </div>

</div>

