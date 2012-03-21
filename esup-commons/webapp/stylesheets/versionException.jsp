<!DOCTYPE html
PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<!--e:page stringsVar="msgs" -->
<ui:composition  xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
 	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:t="http://myfaces.apache.org/tomahawk"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:e="http://commons.esup-portail.org"
	xmlns:fck="http://www.fck-faces.org/fck-faces"
	template="_portletPageTemplate.jsp">
	<e:emptyMenu />
	<e:panelGrid columns="2" columnClasses="colLeft,colRight" width="100%">
		<e:section value="#{msgs['VERSION_EXCEPTION.TITLE']}" />
		<h:panelGroup />
	</e:panelGrid>
	<e:messages />
	<e:paragraph value="#{msgs['VERSION_EXCEPTION.TEXT.1']}" />
	<e:paragraph value="#{msgs['VERSION_EXCEPTION.TEXT.2']}" />
	<ui:include src="_exceptionForm.jsp"/>
</ui:composition>
