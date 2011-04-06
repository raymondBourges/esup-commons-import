<%@include file="_include.jsp"%>
<e:page stringsVar="msgs" menuItem="things" locale="#{sessionController.locale}"
	authorized="#{thingsController.pageAuthorized}">
	<%@include file="_navigation.jsp"%>
	<script type="text/javascript">
	var thingSelected = false;
	function selectThing(linkId) {
		if (!thingSelected) {
		  	thingSelected = true;
			simulateLinkClick(linkId);
	  	}
	  	return false;
	}
	</script>

	<e:form id="thingsHeaderForm">

		<h:panelGroup>
			<e:section value="#{msgs['THINGS.TITLE']}"
				rendered="#{thingsController.department != null}">
				<f:param value="#{thingsController.department.label}" />
			</e:section>
			<e:section value="#{msgs['THINGS.TITLE_NO_DEPARTMENT']}"
				rendered="#{thingsController.department == null}" />
		</h:panelGroup>

		<e:messages />

		<e:outputLabel for="department"
			value="#{msgs['THINGS.TEXT.DEPARTMENT_SELECTION.PROMPT']}" />
		<e:selectOneMenu id="department" value="#{thingsController.department}"
			onchange="submit();" converter="#{departmentConverter}">
			<f:selectItems value="#{thingsController.departmentItems}" />
		</e:selectOneMenu>
		<e:message for="department" />
		<e:commandButton value="#{msgs['_.BUTTON.CHANGE']}" id="changeButton" />
		<script type="text/javascript">
			hideButton("thingsHeaderForm:changeButton");
</script>
		<e:commandButton
			rendered="#{thingsController.currentUserCanAddThing}"
			value="#{msgs['THINGS.BUTTON.ADD_THING']}"
			action="#{thingsController.addThing}" immediate="true" />
		<e:commandButton action="navigationDepartmentView"
			rendered="#{thingsController.department != null}"
			value="#{msgs['THINGS.BUTTON.VIEW_DEPARTMENT']}">
			<t:updateActionListener value="#{thingsController.department}" property="#{departmentsController.department}" />
		</e:commandButton>

	</e:form>

	<h:panelGroup rendered="#{thingsController.department != null}"
		id="thingsPanelGroup">
		<e:form id="thingsForm">
			<h:panelGroup rendered="#{not empty thingsController.paginator.visibleItems}">
				<e:dataTable id="data" rowIndexVar="variable"
					value="#{thingsController.paginator.visibleItems}" var="thing" border="0"
					style="width:100%" cellspacing="0" cellpadding="0">
					<f:facet name="header">
						<h:panelGroup>
							<e:paginator id="thingsPaginator" paginator="#{thingsController.paginator}"
								itemsName="#{msgs['THINGS.TITLE_NO_DEPARTMENT']}" 
								onchange="javascript:{simulateLinkClick('thingsForm:data:submitPageSize');}"/>
							<e:commandButton id="submitPageSize" style="display:none;"
								action="#{thingsController.paginator.forceReload}"/>
							<t:htmlTag value="hr" />
						</h:panelGroup>
					</f:facet>

					<t:column
						onclick="javascript:{return selectThing('hiddenLink[#{variable}]');}">
						<f:facet name="header">
							<e:text
								value="#{msgs['THINGS.TEXT.THINGS.HEADER.VALUE']}" />
						</f:facet>
						<e:text value="#{thing.value}" />
						<t:commandLink id="hiddenLink" forceId="true" action="editThing">
							<t:updateActionListener value="#{thing}"
								property="#{thingsController.thingToUpdate}" />
						</t:commandLink>
					</t:column>

					<t:column
						onclick="javascript:{return selectThing('hiddenLink[#{variable}]');}">
						<f:facet name="header">
							<e:text
								value="#{msgs['THINGS.TEXT.THINGS.HEADER.DATE']}" />
						</f:facet>
						<e:text value="#{thing.printableDate}" />
					</t:column>

					<t:column
						onclick="javascript:{return selectThing('hiddenLink[#{variable}]');}">
						<e:commandButton value="#{msgs['_.BUTTON.VIEW_EDIT']}"
							id="editButton" action="editThing">
							<t:updateActionListener value="#{thing}"
								property="#{thingsController.thingToUpdate}" />
						</e:commandButton>
					</t:column>

					<t:column>
						<e:commandButton value="^^" action="#{thingsController.moveFirst}"
							rendered="#{variable != 0 or not thingsController.paginator.firstPage}">
							<t:updateActionListener value="#{thing}"
								property="#{thingsController.thingToUpdate}" />
						</e:commandButton>
					</t:column>

					<t:column>
						<e:commandButton value="^" action="#{thingsController.moveUp}"
							rendered="#{variable != 0 or not thingsController.paginator.firstPage}">
							<t:updateActionListener value="#{thing}"
								property="#{thingsController.thingToUpdate}" />
						</e:commandButton>
					</t:column>

					<t:column>
						<e:commandButton value="v" action="#{thingsController.moveDown}"
							rendered="#{variable != thingsController.paginator.visibleItemsCount - 1 or not thingsController.paginator.lastPage}">
							<t:updateActionListener value="#{thing}"
								property="#{thingsController.thingToUpdate}" />
						</e:commandButton>
					</t:column>

					<t:column>
						<e:commandButton value="vv" action="#{thingsController.moveLast}"
							rendered="#{variable != thingsController.paginator.visibleItemsCount - 1 or not thingsController.paginator.lastPage}">
							<t:updateActionListener value="#{thing}"
								property="#{thingsController.thingToUpdate}" />
						</e:commandButton>
					</t:column>

					<f:facet name="footer">
						<t:htmlTag value="hr" />
					</f:facet>
				</e:dataTable>
				<e:paragraph value="#{msgs['THINGS.TEXT.THINGS.NOTE']}" />
			</h:panelGroup>
			<e:paragraph
				value="#{msgs['THINGS.TEXT.THINGS.NO_THING']}"
				rendered="#{empty thingsController.paginator.visibleItems}" />
		</e:form>
	</h:panelGroup>
	<script type="text/javascript">
		highlightTableRows("thingsForm:data");
		hideTableButtons("thingsForm:data","editButton");
		hideButton("thingsForm:data:submitPageSize");
		highlightChildrenLiTags("thingsForm:data:thingsPaginator");
</script>
</e:page>
