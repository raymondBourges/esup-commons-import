<html 
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:p="http://primefaces.prime.com.tr/ui">

<ui:composition template="template.jsp">

	<ui:define name="body">
		<p:accordionPanel>
			<p:tab title="FirstTabTitle">
				<h:outputText value="Lorem" />
			</p:tab>
			<p:tab title="SecondTabTitle">
				<h:outputText value="Ipsum" />
			</p:tab>
			<p:tab title="ThirdTabTitle">
			</p:tab>
		</p:accordionPanel>
	</ui:define>
</ui:composition>
</html>

