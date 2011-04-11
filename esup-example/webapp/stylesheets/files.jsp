<%@include file="_include.jsp"%>
<e:page stringsVar="msgs" menuItem="files" locale="#{sessionController.locale}"
	authorized="#{filesController.pageAuthorized}" downloadId="#{filesController.downloadId}" >
	<%@include file="_navigation.jsp"%>

	<e:section value="#{msgs['FILES.TITLE']}" />

	<e:messages />

    <e:form id="uploadForm" enctype="multipart/form-data" >
        <t:inputFileUpload id="file"
            value="#{filesController.uploadedFile}"
            storage="memory"
            required="true"/>
        <h:commandButton value="#{msgs['FILES.BUTTON.ADD']}"
            action="#{filesController.uploadFile}"/>
		<e:message for="file" />
    </e:form>

	<e:form id="filesForm" rendered="#{not empty filesController.paginator.visibleItems}">
		<e:dataTable id="data" rowIndexVar="variable"
			value="#{filesController.paginator.visibleItems}" var="file" border="0"
			style="width:100%" cellspacing="0" cellpadding="0">
			<f:facet name="header">
				<h:panelGroup>
					<h:panelGrid columns="3" columnClasses="colLeft,colRight" width="100%" >
						<h:panelGroup>
							<e:text value="#{msgs['FILES.TEXT.FILES']}">
								<f:param
									value="#{filesController.paginator.firstVisibleNumber + 1}" />
								<f:param
									value="#{filesController.paginator.lastVisibleNumber + 1}" />
								<f:param
									value="#{filesController.paginator.totalItemsCount}" />
							</e:text>
						</h:panelGroup>
						<h:panelGroup rendered="#{filesController.paginator.lastPageNumber != 0}" >
							<h:panelGroup rendered="#{not filesController.paginator.firstPage}" >
								<e:commandButton value="#{msgs['PAGINATION.BUTTON.FIRST']}"
									action="#{filesController.paginator.gotoFirstPage}" />
								<e:text value=" " />
								<e:commandButton value="#{msgs['PAGINATION.BUTTON.PREVIOUS']}"
									action="#{filesController.paginator.gotoPreviousPage}" />
							</h:panelGroup>
							<e:text value=" #{msgs['PAGINATION.TEXT.PAGES']} " />
							<t:dataList value="#{filesController.paginator.nearPages}" var="page">
								<e:text value=" " />
								<e:italic value="#{page + 1}" rendered="#{page == filesController.paginator.currentPage}" />
								<h:commandLink value="#{page + 1}" rendered="#{page != filesController.paginator.currentPage}"  >
									<t:updateActionListener
										value="#{page}"
										property="#{filesController.paginator.currentPage}" />
								</h:commandLink>
								<e:text value=" " />
							</t:dataList>
							<h:panelGroup rendered="#{not filesController.paginator.lastPage}" >
								<e:commandButton 
									value="#{msgs['PAGINATION.BUTTON.NEXT']}" 
									action="#{filesController.paginator.gotoNextPage}" />
								<e:text value=" " />
								<e:commandButton 
									value="#{msgs['PAGINATION.BUTTON.LAST']}"
										action="#{filesController.paginator.gotoLastPage}" />
							</h:panelGroup>
						</h:panelGroup>
						<h:panelGroup>
							<e:text value="#{msgs['FILES.TEXT.FILES_PER_PAGE']} " />
							<e:selectOneMenu onchange="javascript:{simulateLinkClick('filesForm:data:changeButton');}"
								value="#{filesController.paginator.pageSize}" >
								<f:selectItems value="#{filesController.paginator.pageSizeItems}" />
							</e:selectOneMenu>
							<e:commandButton value="#{msgs['_.BUTTON.CHANGE']}" id="changeButton" 
								action="#{filesController.paginator.forceReload}" />
						</h:panelGroup>
					</h:panelGrid>
					<t:htmlTag value="hr" />
				</h:panelGroup>
			</f:facet>

			<t:column>
				<f:facet name="header">
					<e:text value="#{msgs['FILES.TEXT.HEADER.NAME']}" />
				</f:facet>
				<e:text value="#{file.name}" />
			</t:column>

			<t:column>
				<f:facet name="header">
					<e:text value="#{msgs['FILES.TEXT.HEADER.TYPE']}" />
				</f:facet>
				<e:text value="#{file.type}" />
			</t:column>

			<t:column>
				<f:facet name="header">
					<e:text value="#{msgs['FILES.TEXT.HEADER.SIZE']}" />
				</f:facet>
				<e:text value="#{file.size}" />
			</t:column>

			<t:column>
				<e:commandButton value="#{msgs['FILES.BUTTON.DOWNLOAD']}"
					action="#{filesController.downloadFile}" >
					<t:updateActionListener value="#{file.name}"
						property="#{filesController.filenameToDownload}" />
				</e:commandButton>
			</t:column>

			<f:facet name="footer">
				<t:htmlTag value="hr" />
			</f:facet>
		</e:dataTable>
	</e:form>
	<e:paragraph value="#{msgs['FILES.TEXT.NO_FILE']}"
		rendered="#{empty filesController.paginator.visibleItems}" >
		<f:param value="#{filesController.directory}" />
	</e:paragraph>
	<script type="text/javascript">	
		hideButton("filesForm:data:changeButton");
	</script>
</e:page>
