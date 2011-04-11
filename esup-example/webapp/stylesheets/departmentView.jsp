<%@include file="_include.jsp"%>
<e:page stringsVar="msgs" menuItem="departments" locale="#{sessionController.locale}" authorized="#{departmentsController.currentUserCanViewDepartment}" >
	<%@include file="_navigation.jsp"%>

	<script type="text/javascript">
	var managerSelected = false;
	function selectManager(linkId) {
		if (!managerSelected) {
		  	managerSelected = true;
			simulateLinkClick(linkId);
	  	}
	}
</script>

	<e:form id="departmentViewForm">
		<e:panelGrid columns="2" columnClasses="colLeft,colRight" width="100%"
			cellspacing="0" cellpadding="0" id="topPanelGrid">
			<e:section value="#{msgs['DEPARTMENT_VIEW.TITLE']}">
				<f:param value="#{departmentsController.department.label}" />
			</e:section>
			<e:commandButton action="back"
				value="#{msgs['DEPARTMENT_VIEW.BUTTON.BACK']}" />
		</e:panelGrid>

		<e:messages />

		<e:panelGrid columns="2" columnClasses="colLeft,colRight" width="100%"
			cellspacing="0" cellpadding="0">
			<h:column>
				<e:subSection value="#{msgs['DEPARTMENT_VIEW.HEADER.MANAGERS']}" />
			</h:column>
			<e:commandButton
				rendered="#{departmentsController.currentUserCanAddDepartmentManager}"
				value="#{msgs['DEPARTMENT_VIEW.BUTTON.ADD_MANAGER']}"
				action="addManager" immediate="true" />
		</e:panelGrid>

		<h:panelGroup
			rendered="#{not empty departmentsController.departmentManagerPaginator.visibleItems}">
			<e:dataTable id="data" rowIndexVar="variable" width="100%"
				rowOnClick="javascript:{selectManager('hiddenLink[#{variable}]');return false;}"
				value="#{departmentsController.departmentManagerPaginator.visibleItems}"
				var="departmentManager" border="0" cellspacing="0" cellpadding="0">
				<f:facet name="header">
					<h:panelGroup>
						<h:panelGrid columns="3" columnClasses="colLeft,colRight" width="100%" >
							<h:panelGroup>
								<e:text value="#{msgs['DEPARTMENT_VIEW.TEXT.MANAGERS.PAGINATION']}">
									<f:param
										value="#{departmentsController.departmentManagerPaginator.firstVisibleNumber + 1}" />
									<f:param
										value="#{departmentsController.departmentManagerPaginator.lastVisibleNumber + 1}" />
									<f:param
										value="#{departmentsController.departmentManagerPaginator.totalItemsCount}" />
								</e:text>
							</h:panelGroup>
							<h:panelGroup rendered="#{departmentsController.departmentManagerPaginator.lastPageNumber != 0}" >
								<h:panelGroup rendered="#{not departmentsController.departmentManagerPaginator.firstPage}" >
									<e:commandButton value="#{msgs['PAGINATION.BUTTON.FIRST']}"
										action="#{departmentsController.departmentManagerPaginator.gotoFirstPage}" />
									<e:text value=" " />
									<e:commandButton value="#{msgs['PAGINATION.BUTTON.PREVIOUS']}"
										action="#{departmentsController.departmentManagerPaginator.gotoPreviousPage}" />
								</h:panelGroup>
								<e:text value=" #{msgs['PAGINATION.TEXT.PAGES']} " />
								<t:dataList value="#{departmentsController.departmentManagerPaginator.nearPages}" var="page">
									<e:text value=" " />
									<e:italic value="#{page + 1}" rendered="#{page == departmentsController.departmentManagerPaginator.currentPage}" />
									<h:commandLink value="#{page + 1}" rendered="#{page != departmentsController.departmentManagerPaginator.currentPage}" >
										<t:updateActionListener
											value="#{page}"
											property="#{departmentsController.departmentManagerPaginator.currentPage}" />
									</h:commandLink>
									<e:text value=" " />
								</t:dataList>
								<h:panelGroup rendered="#{not departmentsController.departmentManagerPaginator.lastPage}" >
									<e:commandButton 
										value="#{msgs['PAGINATION.BUTTON.NEXT']}" 
										action="#{departmentsController.departmentManagerPaginator.gotoNextPage}" />
									<e:text value=" " />
									<e:commandButton 
										value="#{msgs['PAGINATION.BUTTON.LAST']}"
											action="#{departmentsController.departmentManagerPaginator.gotoLastPage}" />
								</h:panelGroup>
							</h:panelGroup>
							<h:panelGroup>
								<e:text value="#{msgs['DEPARTMENT_VIEW.TEXT.MANAGERS.PER_PAGE']} " />
								<e:selectOneMenu onchange="javascript:{simulateLinkClick('departmentViewForm:data:changeButton');}"
									value="#{departmentsController.departmentManagerPaginator.pageSize}" >
									<f:selectItems value="#{departmentsController.departmentManagerPaginator.pageSizeItems}" />
								</e:selectOneMenu>
								<e:commandButton value="#{msgs['_.BUTTON.CHANGE']}" id="changeButton" 
									action="#{departmentsController.departmentManagerPaginator.forceReload}" />
							</h:panelGroup>
						</h:panelGrid>
						<t:htmlTag value="hr" />
					</h:panelGroup>
				</f:facet>
				<t:column>
					<e:text
						value="#{departmentManager.user.displayName} (#{departmentManager.user.id})" />
					<t:commandLink id="hiddenLink" forceId="true"
						action="editDepartmentManager" immediate="true">
						<t:updateActionListener value="#{departmentManager}"
							property="#{departmentsController.departmentManagerToUpdate}" />
					</t:commandLink>
				</t:column>
				<t:column style="text-align: right;">
					<e:commandButton value="#{msgs['_.BUTTON.VIEW_EDIT']}" id="editButton"
						action="editDepartmentManager">
						<t:updateActionListener value="#{departmentManager}"
							property="#{departmentsController.departmentManagerToUpdate}" />
					</e:commandButton>
				</t:column>
				<f:facet name="footer">
					<t:htmlTag value="hr" />
				</f:facet>
			</e:dataTable>
			<e:paragraph value="#{msgs['DEPARTMENT_VIEW.TEXT.MANAGERS.NOTE']}" />
		</h:panelGroup>
		<e:paragraph value="#{msgs['DEPARTMENT_VIEW.TEXT.MANAGERS.NONE']}"
			rendered="#{empty departmentsController.departmentManagerPaginator.visibleItems}" />

		<e:subSection value="#{msgs['DEPARTMENT_VIEW.HEADER.PROPERTIES']}" />

		<e:panelGrid columns="2">
			<e:outputLabel for="id"
				value="#{msgs['DEPARTMENT_VIEW.TEXT.PROPERTIES.ID']}" />
			<e:text value=" #{departmentsController.department.id}" id="id" />
			<e:outputLabel for="label"
				value="#{msgs['DEPARTMENT_VIEW.TEXT.PROPERTIES.LABEL']}" />
			<e:text value=" #{departmentsController.department.label}" id="label" />
			<e:outputLabel for="xlabel"
				value="#{msgs['DEPARTMENT_VIEW.TEXT.PROPERTIES.XLABEL']}" />
			<e:text value=" #{departmentsController.department.xlabel}" id="xlabel" />
			<e:outputLabel for="ldapFilter"
				value="#{msgs['DEPARTMENT_VIEW.TEXT.PROPERTIES.LDAP_FILTER']}" />
			<h:panelGroup id="ldapFilter">
				<e:text value="#{departmentsController.department.ldapFilter}"
					rendered="#{departmentsController.department.ldapFilter != null}" />
				<e:italic value="#{msgs['DEPARTMENT_VIEW.TEXT.PROPERTIES.NO_LDAP_FILTER']}"
					rendered="#{departmentsController.department.ldapFilter == null}" />
			</h:panelGroup>
		</e:panelGrid>
		<e:commandButton
			rendered="#{departmentsController.currentUserCanEditDepartmentProperties}"
			value="#{msgs['DEPARTMENT_VIEW.BUTTON.EDIT_PROPERTIES']}"
			action="editProperties" immediate="true">
			<t:updateActionListener value="#{departmentsController.department}"
				property="#{departmentsController.departmentToUpdate}" />
		</e:commandButton>

		<e:commandButton value="#{msgs['DEPARTMENT_VIEW.BUTTON.DELETE_DEPARTMENT']}"
			rendered="#{departmentsController.currentUserCanDeleteDepartment}"
			action="deleteDepartment" immediate="true"/>
	</e:form>
	<e:subSection value="#{msgs['DEPARTMENT_VIEW.HEADER.URLS']}" />
	<e:ul>
		<e:li value="#{departmentsController.viewUrl}" />
		<e:li value="#{departmentsController.viewUrlViaCas}" />
	</e:ul>
	<script type="text/javascript">
		highlightTableRows("departmentViewForm:data");
		hideTableButtons("departmentViewForm:data","editButton");
		hideButton("departmentViewForm:data:changeButton");
</script>
</e:page>
