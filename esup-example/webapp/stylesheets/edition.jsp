<%@include file="_include.jsp"%>
<%@ taglib prefix="fck" uri="http://www.fck-faces.org/fck-faces"%>
<e:page stringsVar="msgs" menuItem="edition" locale="#{sessionController.locale}"
	authorized="#{editionController.pageAuthorized}">
	<%@include file="_navigation.jsp"%>

	<e:section value="#{msgs['EDITION.TITLE']}" />

	<e:messages />

	<e:subSection value="#{msgs['EDITION.HEADER.INPUT']}"/>
	<e:form id="editorForm" >
		<fck:editor  value="#{editionController.text}" toolbarSet="Example"/>
		<e:commandButton action="#{editionController.update}"
			value="#{msgs['_.BUTTON.UPDATE']}" />
	</e:form>
	
	<e:subSection value="#{msgs['EDITION.HEADER.RAW_OUTPUT']}"/>
	<e:paragraph value="#{editionController.text}"/>
	
	<e:subSection value="#{msgs['EDITION.HEADER.OUTPUT']}"/>
	<e:paragraph escape="false" value="#{editionController.text}"/>
	
	<e:subSection value="#{msgs['EDITION.HEADER.URL']}"/>
	<e:paragraph value="#{msgs['EDITION.TEXT.URL']}">
		<f:param value="#{editionController.urlViaCas}" />
	</e:paragraph>
</e:page>
