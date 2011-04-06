<%@include file="_include.jsp"%>
<e:page stringsVar="msgs" menuItem="departments" locale="#{sessionController.locale}" authorized="#{departmentsController.currentUserCanDeleteDepartment}" >
	<%@include file="_navigation.jsp"%>

	<e:form>
		<e:section value="#{msgs['DEPARTMENT_DELETE.TITLE']}">
			<f:param value="#{departmentsController.department.label}" />
		</e:section>
		<e:paragraph value="#{msgs['DEPARTMENT_DELETE.TEXT.TOP']}" />
		<e:commandButton value="#{msgs['_.BUTTON.CONFIRM']}"
			action="#{departmentsController.confirmDeleteDepartment}" />
		<e:commandButton immediate="true"
			value="#{msgs['_.BUTTON.CANCEL']}" action="cancel" />
	</e:form>
</e:page>
