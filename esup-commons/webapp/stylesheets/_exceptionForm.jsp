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
<e:form id="exceptionForm" >
	<h:panelGroup>
		<h:panelGroup style="cursor: pointer" onclick="simulateLinkClick('exceptionForm:restartButton');" >
			<e:bold value="#{msgs['EXCEPTION.BUTTON.RESTART']} " />
			<t:graphicImage value="/media/images/restart.png"
				alt="#{msgs['EXCEPTION.BUTTON.RESTART']}" 
				title="#{msgs['EXCEPTION.BUTTON.RESTART']}" />
		</h:panelGroup>
		<e:commandButton style="display: none" id="restartButton" 
			action="#{exceptionController.restart}"
			value="#{msgs['EXCEPTION.BUTTON.RESTART']}" />
	</h:panelGroup>
</e:form>
</ui:composition>
