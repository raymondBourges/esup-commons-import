<%@include file="_include.jsp"%>
<e:page stringsVar="msgs" menuItem="departments" locale="#{sessionController.locale}"
	authorized="#{departmentsController.pageAuthorized}">
	<%@include file="_navigation.jsp"%>

	<script type="text/javascript">
	var departmentSelected = false;
	function selectDepartment(linkId) {
		if (!departmentSelected) {
		  	departmentSelected = true;
			simulateLinkClick(linkId);
	  	}
	}
</script>

	<e:form id="departmentsForm">
		<e:panelGrid columns="2" columnClasses="colLeft,colRight" width="100%"
			cellspacing="0" cellpadding="0">
			<e:section value="#{msgs['DEPARTMENTS.TITLE']}" />
			<e:commandButton action="addDepartment"
				rendered="#{departmentsController.currentUserCanAddDepartment}"
				value="#{msgs['DEPARTMENTS.BUTTON.ADD_DEPARTMENT']}" />
		</e:panelGrid>

		<e:messages />

		<e:dataTable id="data" value="#{departmentsController.departmentPaginator.visibleItems}"
			var="department" rowIndexVar="variable" border="0"
			style="width: 100%" cellspacing="0" cellpadding="0"
			rowId="#{department.id}"
			rowOnClick="javascript:{selectDepartment('hiddenLink[#{variable}]');return false;}"
			rendered="#{not empty departmentsController.departmentPaginator.visibleItems}">
			<f:facet name="header">
				<h:panelGroup>
					<h:panelGrid columns="3" columnClasses="colLeft,colRight" width="100%" >
						<h:panelGroup>
							<e:text value="#{msgs['DEPARTMENTS.TEXT.DEPARTMENTS']}">
								<f:param
									value="#{departmentsController.departmentPaginator.firstVisibleNumber + 1}" />
								<f:param
									value="#{departmentsController.departmentPaginator.lastVisibleNumber + 1}" />
								<f:param
									value="#{departmentsController.departmentPaginator.totalItemsCount}" />
							</e:text>
						</h:panelGroup>
						<h:panelGroup rendered="#{departmentsController.departmentPaginator.lastPageNumber != 0}" >
							<h:panelGroup rendered="#{not departmentsController.departmentPaginator.firstPage}" >
								<e:commandButton value="#{msgs['PAGINATION.BUTTON.FIRST']}"
									action="#{departmentsController.departmentPaginator.gotoFirstPage}" />
								<e:text value=" " />
								<e:commandButton value="#{msgs['PAGINATION.BUTTON.PREVIOUS']}"
									action="#{departmentsController.departmentPaginator.gotoPreviousPage}" />
							</h:panelGroup>
							<e:text value=" #{msgs['PAGINATION.TEXT.PAGES']} " />
							<t:dataList value="#{departmentsController.departmentPaginator.nearPages}" var="page">
								<e:text value=" " />
								<e:italic value="#{page + 1}" rendered="#{page == departmentsController.departmentPaginator.currentPage}" />
								<h:commandLink value="#{page + 1}" rendered="#{page != departmentsController.departmentPaginator.currentPage}" >
									<t:updateActionListener
										value="#{page}"
										property="#{departmentsController.departmentPaginator.currentPage}" />
								</h:commandLink>
								<e:text value=" " />
							</t:dataList>
							<h:panelGroup rendered="#{not departmentsController.departmentPaginator.lastPage}" >
								<e:commandButton 
									value="#{msgs['PAGINATION.BUTTON.NEXT']}" 
									action="#{departmentsController.departmentPaginator.gotoNextPage}" />
								<e:text value=" " />
								<e:commandButton 
									value="#{msgs['PAGINATION.BUTTON.LAST']}"
										action="#{departmentsController.departmentPaginator.gotoLastPage}" />
							</h:panelGroup>
						</h:panelGroup>
						<h:panelGroup>
							<e:text value="#{msgs['DEPARTMENTS.TEXT.DEPARTMENTS_PER_PAGE']} " />
							<e:selectOneMenu onchange="javascript:{simulateLinkClick('departmentsForm:data:changeButton');}"
								value="#{departmentsController.departmentPaginator.pageSize}" >
								<f:selectItems value="#{departmentsController.departmentPaginator.pageSizeItems}" />
							</e:selectOneMenu>
							<e:commandButton value="#{msgs['_.BUTTON.CHANGE']}" id="changeButton" 
								action="#{departmentsController.departmentPaginator.forceReload}" />
						</h:panelGroup>
					</h:panelGrid>
					<t:htmlTag value="hr" />
				</h:panelGroup>
			</f:facet>
			<h:column>
				<f:facet name="header">
					<e:text value="#{msgs['DEPARTMENTS.TEXT.HEADER.LABEL']}" />
				</f:facet>
				<t:commandLink id="hiddenLink" forceId="true"
					action="viewDepartment" immediate="true">
					<e:text value="#{department.label}" />
					<t:updateActionListener value="#{department}"
						property="#{departmentsController.department}" />
				</t:commandLink>
			</h:column>
			<h:column>
				<f:facet name="header">
					<e:text value="#{msgs['DEPARTMENTS.TEXT.HEADER.XLABEL']}" />
				</f:facet>
				<e:text value="#{department.xlabel}" />
			</h:column>
			<h:column>
				<f:facet name="header">
					<e:text value="#{msgs['DEPARTMENTS.TEXT.HEADER.LDAP_FILTER']}" />
				</f:facet>
				<e:text value="#{department.ldapFilter}" />
			</h:column>
			<t:column style="text-align: right;">
				<e:commandButton value="#{msgs['_.BUTTON.VIEW_EDIT']}" id="editButton"
					action="viewDepartment" immediate="true">
					<t:updateActionListener value="#{department}"
						property="#{departmentsController.department}" />
				</e:commandButton>
			</t:column>
			<f:facet name="footer">
				<t:htmlTag value="hr" />
			</f:facet>
		</e:dataTable>
		<e:text value="#{msgs['DEPARTMENTS.TEXT.NO_DEPARTMENT']}"
			rendered="#{empty departmentsController.departmentPaginator.visibleItems}" />
	</e:form>
	<script type="text/javascript">
highlightTableRows("departmentsForm:data");
hideTableButtons("departmentsForm:data","editButton");
hideButton("departmentsForm:data:changeButton");
</script>
</e:page>
