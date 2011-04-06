<%@include file="_include.jsp"%>
<e:page stringsVar="msgs" menuItem="about" locale="#{sessionController.locale}" authorized="#{aboutController.pageAuthorized}">
	<%@include file="_navigation.jsp"%>

	<e:section value="#{msgs['ABOUT.TITLE']}">
		<f:param value="#{applicationService.name}" />
		<f:param value="#{applicationService.version}" />
	</e:section>

	<e:paragraph value="#{msgs['ABOUT.TEXT.SUMMARY']}">
		<f:param value="#{applicationService.name}" />
	</e:paragraph>

	<e:paragraph value="#{msgs['ABOUT.TEXT.AUTHORS']}">
		<f:param value="#{applicationService.name}" />
	</e:paragraph>
	<e:ul>
		<e:li value="#{msgs['ABOUT.TEXT.AUTHORS.PA']}" />
		<e:li value="#{msgs['ABOUT.TEXT.AUTHORS.LLH']}" />
		<e:li value="#{msgs['ABOUT.TEXT.AUTHORS.RB']}" />
	</e:ul>

	<e:subSection value="#{msgs['ABOUT.SUBTITLE.COPYRIGHT']}" />
	<e:paragraph value="#{msgs['ABOUT.TEXT.COPYRIGHT']}">
		<f:param value="#{applicationService.name}" />
		<f:param value="#{applicationService.version}" />
		<f:param value="#{applicationService.copyright}" />
	</e:paragraph>

	<e:subSection value="#{msgs['ABOUT.SUBTITLE.LICENCE']}" />
	<e:paragraph escape="false" value="#{msgs['ABOUT.TEXT.LICENCE']}" />

	<e:subSection value="#{msgs['ABOUT.SUBTITLE.MORE_INFORMATION']}">
		<f:param value="#{applicationService.name}" />
	</e:subSection>
	<e:ul>
		<e:li escape="false" value="#{msgs['ABOUT.TEXT.MORE_INFORMATION.ESUP_BLANK']}" />
		<e:li escape="false" value="#{msgs['ABOUT.TEXT.MORE_INFORMATION.ESUP_COMMONS']}" />
		<e:li escape="false" value="#{msgs['ABOUT.TEXT.MORE_INFORMATION.ESUP_PORTAIL']}" />
	</e:ul>
	<e:subSection value="#{msgs['ABOUT.SUBTITLE.EXCEPTION']}" />
	<e:form>
		<e:commandButton immediate="true" action="#{aboutController.throwException}" value="#{msgs['ABOUT.BUTTON.EXCEPTION']}" />
	</e:form>
	<e:subSection value="#{msgs['ABOUT.SUBTITLE.URLS']}" />
	<e:ul>
		<e:li value="#{aboutController.url}" />
		<e:li value="#{aboutController.urlViaCas}" />
	</e:ul>
	<e:subSection value="#{msgs['ABOUT.SUBTITLE.WS_TEST']}" />
	<e:ul>
		<e:li value="#{aboutController.remoteVersion}" />
		<e:li value="#{aboutController.remoteCopyright}" />
		<e:li value="#{aboutController.remoteDepartmentCount}" />
	</e:ul>
<% /* @include file="_debug.jsp" */ %>
</e:page>
