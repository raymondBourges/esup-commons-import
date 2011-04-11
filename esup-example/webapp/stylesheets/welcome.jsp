<%@include file="_include.jsp"%>
<e:page stringsVar="msgs" menuItem="welcome" locale="#{sessionController.locale}" >
	<%@include file="_navigation.jsp"%>
	<e:section value="#{msgs['WELCOME.TITLE']}" />
	<e:paragraph value="#{msgs['WELCOME.TEXT.TOP']}" />
	
	<e:messages/>

	<h:panelGroup rendered="#{sessionController.currentUser != null}">
		<h:panelGroup rendered="#{not empty welcomeController.printableThingSets}">
			<e:subSection value="#{msgs['WELCOME.THINGS.TEXT']}" />
			<e:dataTable value="#{welcomeController.printableThingSets}" var="printableThingSet"
				border="0" style="width:100%" cellspacing="0" cellpadding="0" alternateColors="false">
				<f:facet name="header">
					<t:htmlTag value="hr" />
				</f:facet>
				<t:column>
					<e:dataTable value="#{printableThingSet.things}" var="thing"
						border="0" style="width:100%" cellspacing="0" cellpadding="0" alternateColors="true"
						>
						<f:facet name="header">
							<h:panelGroup>
								<e:text value="#{msgs['WELCOME.THINGS.HEADER']}" 
									rendered="#{not empty printableThingSet.things}">
									<f:param value="#{printableThingSet.department.label}" />
								</e:text>
								<e:text value="#{msgs['WELCOME.THINGS.NONE']}" 
									rendered="#{empty printableThingSet.things}">
									<f:param value="#{printableThingSet.department.label}" />
								</e:text>
							</h:panelGroup>
						</f:facet>
						<t:column>
							<e:text value="#{thing.value}" />
						</t:column>
					</e:dataTable>
				</t:column>
				<f:facet name="footer">
					<t:htmlTag value="hr" />
				</f:facet>
			</e:dataTable>
		</h:panelGroup>
		<e:paragraph rendered="#{empty welcomeController.printableThingSets}" value="#{msgs['WELCOME.TEXT.NO_THING']}" />
	</h:panelGroup>
	<e:form id="welcomeForm" rendered="#{sessionController.currentUser == null}">
		<e:paragraph value="#{msgs['WELCOME.TEXT.UNAUTHENTICATED']}" />
		<e:panelGrid columns="2" >
			<e:outputLabel for="locale" 
				value="#{msgs['PREFERENCES.TEXT.LANGUAGE']}" />
			<h:panelGroup>
				<e:selectOneMenu id="locale" onchange="submit();"
					value="#{preferencesController.locale}" converter="#{localeConverter}" >
					<f:selectItems value="#{preferencesController.localeItems}" />
				</e:selectOneMenu>
				<e:commandButton value="#{msgs['_.BUTTON.CHANGE']}" id="localeChangeButton" />
			</h:panelGroup>
		</e:panelGrid>
	</e:form>
<script type="text/javascript">	
	hideButton("welcomeForm:localeChangeButton");		
</script>
<% /* @include file="_debug.jsp" */ %>
</e:page>
