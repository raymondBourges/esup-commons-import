<%@include file="_include.jsp"%>
<e:page stringsVar="msgs" menuItem="departments" locale="#{sessionController.locale}" authorized="#{departmentsController.currentUserCanDeleteDepartmentManager}" >
	<%@include file="_navigation.jsp"%>

	<e:form>
		<e:section value="#{msgs['DEPARTMENT_MANAGER_DELETE.TITLE']}">
			<f:param
				value="#{departmentsController.departmentManagerToUpdate.user.displayName} (#{departmentsController.departmentManagerToUpdate.user.id})" />
			<f:param value="#{departmentsController.department.label}" />
		</e:section>

		<e:messages />

		<e:paragraph value="#{msgs['DEPARTMENT_MANAGER_DELETE.TEXT.TOP']}">
				<f:param
					value="#{departmentsController.departmentManagerToUpdate.user.displayName} (#{departmentsController.departmentManagerToUpdate.user.id})" />
				<f:param value="#{departmentsController.department.label}" />
			</e:paragraph>

		<e:commandButton
			value="#{msgs['_.BUTTON.CONFIRM']}"
			action="#{departmentsController.confirmDeleteDepartmentManager}" />

		<e:commandButton
			value="#{msgs['_.BUTTON.CANCEL']}"
			action="cancel" />

	</e:form>
</e:page>
