<!DOCTYPE html
PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<ui:composition  xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
 	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:t="http://myfaces.apache.org/tomahawk"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:e="http://commons.esup-portail.org"
	xmlns:fck="http://www.fck-faces.org/fck-faces">
<h:panelGroup id="exceptionDetails" rendered="#{printDetails}" >
	<t:htmlTag value="br" />		
	<ui:include src="_exceptionDetails.jsp"/>
</h:panelGroup>

<script>
function hideExceptionDetails() {
	hideElement('hideExceptionDetails');
	showElement('showExceptionDetails');
	hideElement('exceptionDetails');
}
function showExceptionDetails() {
	showElement('hideExceptionDetails');
	hideElement('showExceptionDetails');
	showElement('exceptionDetails');
}
hideExceptionDetails();
</script>
</ui:composition>