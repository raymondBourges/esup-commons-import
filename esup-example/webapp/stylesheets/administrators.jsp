<%@include file="_include.jsp"%>
<e:page stringsVar="msgs" menuItem="administrators"
	locale="#{sessionController.locale}"
	authorized="#{administratorsController.pageAuthorized}">
	<%@include file="_navigation.jsp"%>

	<e:form id="administratorsForm">
		<e:panelGrid columns="2" columnClasses="colLeft,colRight" width="100%"
			cellspacing="0" cellpadding="0">
			<e:section value="#{msgs['ADMINISTRATORS.TITLE']}" />
			<e:commandButton action="addAdmin"
				value="#{msgs['ADMINISTRATORS.BUTTON.ADD_ADMIN']}"
				rendered="#{administratorsController.currentUserCanAddAdmin}" />
		</e:panelGrid>

		<e:messages />

		<e:dataTable
			rendered="#{not empty administratorsController.paginator.visibleItems}"
			id="data" rowIndexVar="variable"
			value="#{administratorsController.paginator.visibleItems}"
			var="admin" border="0" style="width:100%" cellspacing="0"
			cellpadding="0">
			<f:facet name="header">
				<h:panelGroup>
					<e:paginator id="adminPaginator"
						paginator="#{administratorsController.paginator}"
						itemsName="#{msgs['ADMINISTRATORS']}"
						onchange="javascript:{simulateLinkClick('administratorsForm:data:submitPageSize');}" />
					<e:commandButton id="submitPageSize"
						action="#{administratorsController.paginator.forceReload}" />
					<t:htmlTag value="hr" />
				</h:panelGroup>
			</f:facet>
			<t:column>
				<e:bold value="#{admin.displayName} (#{admin.id})" />
			</t:column>
			<t:column style="text-align: right;">
				<e:commandButton action="deleteAdmin"
					rendered="#{sessionController.currentUser.id != admin.id}"
					value="#{msgs['ADMINISTRATORS.BUTTON.DELETE_ADMIN']}">
					<t:updateActionListener value="#{admin}"
						property="#{administratorsController.userToDelete}" />
				</e:commandButton>
			</t:column>
			<f:facet name="footer">
				<t:htmlTag value="hr" />
			</f:facet>
		</e:dataTable>

		<e:subSection value="#{msgs['ADMINISTRATORS.HEADER.LDAP_STATISTICS']}" />
		<h:panelGroup
			rendered="#{not empty administratorsController.ldapStatistics}">
			<e:dataTable value="#{administratorsController.ldapStatistics}"
				var="string" border="0" style="width: 100%" cellspacing="0"
				cellpadding="0">
				<f:facet name="header">
					<h:panelGroup>
						<t:htmlTag value="hr" />
					</h:panelGroup>
				</f:facet>
				<t:column>
					<e:bold value="#{string}" />
				</t:column>
				<f:facet name="footer">
					<t:htmlTag value="hr" />
				</f:facet>
			</e:dataTable>
		</h:panelGroup>
		<e:subSection
			value="#{msgs['ADMINISTRATORS.TEXT.LDAP_STATISTICS.NONE']}"
			rendered="#{empty administratorsController.ldapStatistics}" />
	</e:form>
	<script type="text/javascript">	
		hideButton("administratorsForm:data:submitPageSize");
		highlightChildrenLiTags("administratorsForm:data:adminPaginator");
	</script>
</e:page>
