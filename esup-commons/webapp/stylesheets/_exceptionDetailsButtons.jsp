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
<h:panelGroup id="hideExceptionDetails" 
	style="cursor: pointer" onclick="hideExceptionDetails(); return false;" >
	<e:bold value="#{msgs['EXCEPTION.TEXT.HIDE_DETAILS']} " />
	<t:graphicImage value="/media/images/hide.png"
		alt="#{msgs['EXCEPTION.TEXT.HIDE_DETAILS']}" 
		title="#{msgs['EXCEPTION.TEXT.HIDE_DETAILS']}" />
</h:panelGroup>
<h:panelGroup id="showExceptionDetails" 
	style="cursor: pointer" onclick="showExceptionDetails(); return false;" >
	<e:bold value="#{msgs['EXCEPTION.TEXT.SHOW_DETAILS']} " />
	<t:graphicImage value="/media/images/show.png"
		alt="#{msgs['EXCEPTION.TEXT.SHOW_DETAILS']}" 
		title="#{msgs['EXCEPTION.TEXT.SHOW_DETAILS']}" />
</h:panelGroup>
</ui:composition>
