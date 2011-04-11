<%@include file="_include.jsp"%>
<e:page stringsVar="msgs" menuItem="tree" locale="#{sessionController.locale}"
	authorized="#{treeController.pageAuthorized}">
	<%@include file="_navigation.jsp"%>

	<e:section value="#{msgs['TREE.TITLE']}" />

	<e:form id="treeForm">

		<e:messages />
		
		<e:commandButton id="refresh"
			action="#{treeController.refreshTree}"
			value="#{msgs['TREE.BUTTON.REFRESH']}" />

		<h:panelGroup rendered="#{treeController.treeModel != null}">
			<t:tree2 id="tree" value="#{treeController.treeModel}"
				var="node" varNodeToggler="t" clientSideToggle="false"
				showRootNode="false" >
				<f:facet name="root">
					<h:panelGroup>
						<t:graphicImage value="/media/images/openFolder.png" />
					</h:panelGroup>
				</f:facet>
				<f:facet name="department">
					<h:panelGroup>
						<t:graphicImage value="/media/images/openFolder.png" />
						<e:text value="#{node.department.label}" />
					</h:panelGroup>
				</f:facet>
				<f:facet name="thing">
					<h:panelGroup>
						<t:graphicImage value="/media/images/document.png" />
						<e:text value="#{node.thing.value}" />
					</h:panelGroup>
				</f:facet>
			</t:tree2>
		</h:panelGroup>
		<e:paragraph value="#{msgs['TREE.TEXT.NO_THING']}"
			rendered="#{treeController.treeModel == null}" />

	</e:form>

</e:page>
