<%@include file="_include.jsp"%>
<e:page stringsVar="msgs" menuItem="groups" locale="#{sessionController.locale}"
	authorized="#{groupsController.pageAuthorized}">
	<%@include file="_navigation.jsp"%>

	<e:section value="#{msgs['GROUPS.TITLE']}" />

	<e:messages />

	<e:form id="groupsForm" >
		<e:panelGrid columns="2">
			<e:outputLabel for="uid"
				value="#{msgs['GROUPS.TEXT.PROMPT.UID']}" />
			<h:panelGroup>
				<e:inputText id="uid" value="#{groupsController.uid}" />
				<e:message for="uid" />
			</h:panelGroup>
			<e:outputLabel for="groupId"
				value="#{msgs['GROUPS.TEXT.PROMPT.GROUP_ID']}" />
			<h:panelGroup>
				<e:inputText id="groupId" value="#{groupsController.groupId}" />
				<e:message for="groupId" />
			</h:panelGroup>
			<e:commandButton value="#{msgs['GROUPS.BUTTON.TEST']}" action="#{groupsController.test}" />
		</e:panelGrid>
	</e:form>
	<h:panelGroup rendered="#{groupsController.uid != null and groupsController.portalUser.attributes != null}" >
		<e:subSection value="#{msgs['GROUPS.TEXT.TEST.ATTRIBUTES']}" >
			<f:param value="#{groupsController.uid}" />
		</e:subSection>
		<t:dataList var="attributeName"
			rendered="#{not empty groupsController.portalUser.attributeNames}"
			value="#{groupsController.portalUser.attributeNames}" 
			rowCountVar="attrRowCount" rowIndexVar="attrRowIndex" >
			<e:text value="#{attributeName} -> " />
			<t:dataList var="value" value="#{groupsController.portalUser.attributes[attributeName]}"
				rowCountVar="rowCount" rowIndexVar="rowIndex" >
				<e:text value="("
					rendered="#{(rowIndex == 0) and (rowCount > 1)}" />
				<e:bold value="#{value}" />
				<e:text value=", "
					rendered="#{(rowIndex + 1 < rowCount) and (rowCount > 1)}" />
				<e:text
					value=")"
					rendered="#{(rowIndex + 1 == rowCount) and (rowCount > 1)}" />
			</t:dataList>
			<t:htmlTag value="br" rendered="#{attrRowIndex + 1 != attrRowCount}" />
		</t:dataList>
		<e:paragraph
			rendered="#{empty groupsController.portalUser.attributes}"
			value="#{msgs['GROUPS.TEXT.TEST.NO_ATTRIBUTE']}" />
	</h:panelGroup>
	<h:panelGroup rendered="#{groupsController.uid != null and groupsController.portalUsers != null}" >
		<e:subSection value="#{msgs['GROUPS.TEXT.TEST.SEARCH_USERS']}" >
			<f:param value="#{groupsController.uid}" />
		</e:subSection>
		<t:dataList var="portalUser"
			rendered="#{not empty groupsController.portalUsers}"
			value="#{groupsController.portalUsers}" 
			rowCountVar="rowCount" rowIndexVar="rowIndex" >
			<e:li value="#{portalUser.id}" />
		</t:dataList>
		<e:paragraph
			rendered="#{empty groupsController.portalUsers}"
			value="#{msgs['GROUPS.TEXT.TEST.NO_SEARCH_USER']}" />
	</h:panelGroup>
	<h:panelGroup rendered="#{groupsController.portalUser != null}" >
		<e:subSection value="#{msgs['GROUPS.TEXT.TEST.USER_GROUPS']}" >
			<f:param value="#{groupsController.uid}" />
		</e:subSection>
		<t:dataList var="group"
			rendered="#{not empty groupsController.portalUserGroups}"
			value="#{groupsController.portalUserGroups}" 
			rowCountVar="groupCount" rowIndexVar="groupIndex" >
			<e:text value="#{group.id} / #{group.name}" />
			<t:htmlTag value="br" rendered="#{groupIndex + 1 != groupCount}" />
		</t:dataList>
		<e:paragraph
			rendered="#{empty groupsController.portalUserGroups}"
			value="#{msgs['GROUPS.TEXT.TEST.NO_USER_GROUP']}" />
	</h:panelGroup>
	<h:panelGroup rendered="#{groupsController.portalGroup != null}" >
		<e:subSection value="#{msgs['GROUPS.TEXT.TEST.SUB_GROUPS']}" >
			<f:param value="#{groupsController.groupId}" />
		</e:subSection>
		<t:dataList var="group"
			rendered="#{not empty groupsController.portalSubGroups}"
			value="#{groupsController.portalSubGroups}" >
			<e:li value="#{group.id} / #{group.name}" />
		</t:dataList>
		<e:paragraph
			rendered="#{empty groupsController.portalSubGroups}"
			value="#{msgs['GROUPS.TEXT.TEST.NO_SUB_GROUP']}" />
	</h:panelGroup>
	<h:panelGroup rendered="#{groupsController.groupId != null}" >
		<e:subSection value="#{msgs['GROUPS.TEXT.TEST.SEARCH_GROUPS']}" >
			<f:param value="#{groupsController.groupId}" />
		</e:subSection>
		<t:dataList var="group"
			rendered="#{not empty groupsController.portalSearchGroups}"
			value="#{groupsController.portalSearchGroups}" >
			<e:li value="#{group.id} / #{group.name}" />
		</t:dataList>
		<e:paragraph
			rendered="#{empty groupsController.portalSearchGroups}"
			value="#{msgs['GROUPS.TEXT.TEST.NO_SEARCH_GROUP']}" />
	</h:panelGroup>
	<h:panelGroup
		rendered="#{groupsController.portalUser != null and groupsController.portalGroup != null}" >
		<e:subSection value="#{msgs['GROUPS.TEXT.TEST.MEMBERSHIP']}" >
			<f:param value="#{groupsController.uid}" />
			<f:param value="#{groupsController.groupId}" />
		</e:subSection>
		<e:paragraph
			value="#{groupsController.member}" />
	</h:panelGroup>
	<e:subSection value="#{msgs['GROUPS.TEXT.PREFS']}" />
	<t:dataList var="name"
		rendered="#{not empty groupsController.prefs}"
		value="#{groupsController.prefNames}" >
		<e:li value="#{name} => #{groupsController.prefs[name]}" />
	</t:dataList>
	<e:paragraph
		rendered="#{empty groupsController.prefs}"
		value="#{msgs['GROUPS.TEXT.NO_PREF']}" />
	<e:form id="ptForm" >
		<e:outputLabel for="casTargetService"
			value="#{msgs['GROUPS.TEXT.PROMPT.SERVICE']}" />
		<e:inputText id="casTargetService" value="#{groupsController.casTargetService}" />
		<e:commandButton value="#{msgs['GROUPS.BUTTON.PT']}" action="#{groupsController.retrievePt}" />
	</e:form>

</e:page>
