<%@include file="_commons-include.jsp"%>
<e:page stringsVar="msgs">
	<e:emptyMenu />
	<e:panelGrid columns="2" columnClasses="colLeft,colRight" width="100%">
		<e:section value="#{msgs['LDAP_EXCEPTION.TITLE']}" />
		<h:panelGroup />
	</e:panelGrid>
	<e:messages />
	<e:paragraph value="#{msgs['LDAP_EXCEPTION.TEXT.TOP']}" />
	<%@include file="_exceptionForm.jsp"%>
</e:page>
