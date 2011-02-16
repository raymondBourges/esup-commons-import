<%@include file="_include.jsp"%>
<e:page stringsVar="msgs">

	<h:form id="myform">

		<h:panelGrid columns="2">
			<h:outputText value="Si name = valeur du parametre de l'url test ok  : "></h:outputText>
			<t:htmlTag value="br"></t:htmlTag>
			<h:outputText id="outtext" value="value de name est : #{welcomeController.name}" />
		</h:panelGrid>
	</h:form>

</e:page>
