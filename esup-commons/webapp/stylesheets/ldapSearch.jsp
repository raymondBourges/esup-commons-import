<%@include file="_commons-include.jsp"%>
<e:page stringsVar="msgs" locale="#{ldapSearchController.locale}" >
	<e:emptyMenu />

	<e:form id="ldapSearchForm">
		<e:section value="#{msgs['LDAP_SEARCH.TITLE']}" />

		<e:outputLabel for="searchInput"
			value="#{msgs['LDAP_SEARCH.TEXT.PROMPT']}" />
		<e:inputText id="searchInput" value="#{ldapSearchController.searchInput}"
			required="true" />
		<e:message for="searchInput" />

		<e:panelGrid columns="2" columnClasses="colLeft,colRight" width="100%">
			<e:commandButton value="#{msgs['LDAP_SEARCH.BUTTON.SEARCH']}"
				action="#{ldapSearchController.search}" />
			<e:commandButton value="#{msgs['_.BUTTON.CANCEL']}"
				action="#{ldapSearchController.cancel}" immediate="true" />
		</e:panelGrid>
	</e:form>
	<script type="text/javascript">
		focusElement("ldapSearchForm:searchInput");
	</script>
</e:page>
