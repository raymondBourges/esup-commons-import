<%@include file="_include.jsp"%>
<e:menuItem id="welcome" value="#{msgs['NAVIGATION.TEXT.WELCOME']}"
	action="#{welcomeController.enter}"
	accesskey="#{msgs['NAVIGATION.ACCESSKEY.WELCOME']}" />
<e:menuItem id="things" 
	action="#{thingsController.enter}"
	value="#{msgs['NAVIGATION.TEXT.THINGS']}"
	accesskey="#{msgs['NAVIGATION.ACCESSKEY.THINGS']}"
	rendered="#{thingsController.pageAuthorized}" />
<e:menuItem id="departments"
	value="#{msgs['NAVIGATION.TEXT.DEPARTMENTS']}"
	action="#{departmentsController.enter}"
	accesskey="#{msgs['NAVIGATION.ACCESSKEY.DEPARTMENTS']}"
	rendered="#{departmentsController.pageAuthorized}" />
<e:menuItem id="administrators"
	value="#{msgs['NAVIGATION.TEXT.ADMINISTRATION']}"
	action="#{administratorsController.enter}"
	accesskey="#{msgs['NAVIGATION.ACCESSKEY.ADMINISTRATION']}"
	rendered="#{administratorsController.pageAuthorized}" />
<e:menuItem id="preferences"
	value="#{msgs['NAVIGATION.TEXT.PREFERENCES']}"
	accesskey="#{msgs['NAVIGATION.ACCESSKEY.PREFERENCES']}"
	action="#{preferencesController.enter}"
	rendered="#{preferencesController.pageAuthorized}" />
<e:menuItem id="files" 
	value="#{msgs['NAVIGATION.TEXT.FILES']}"
	action="#{filesController.enter}"
	accesskey="#{msgs['NAVIGATION.ACCESSKEY.FILES']}"
	rendered="#{filesController.pageAuthorized}" />
<e:menuItem id="groups" 
	value="#{msgs['NAVIGATION.TEXT.GROUPS']}"
	action="#{groupsController.enter}"
	accesskey="#{msgs['NAVIGATION.ACCESSKEY.GROUPS']}"
	rendered="#{groupsController.pageAuthorized}" />
<e:menuItem id="edition" action="#{editionController.enter}"
	value="#{msgs['NAVIGATION.TEXT.EDITION']}"
	action="#{editionController.enter}"
	accesskey="#{msgs['NAVIGATION.ACCESSKEY.EDITION']}"
	rendered="#{editionController.pageAuthorized}" />
<e:menuItem id="tree" action="#{treeController.enter}"
	value="#{msgs['NAVIGATION.TEXT.TREE']}"
	action="#{treeController.enter}"
	accesskey="#{msgs['NAVIGATION.ACCESSKEY.TREE']}"
	rendered="#{treeController.pageAuthorized}" />
<e:menuItem id="about" value="#{msgs['NAVIGATION.TEXT.ABOUT']}"
	accesskey="#{msgs['NAVIGATION.ACCESSKEY.ABOUT']}"
	action="#{aboutController.enter}" />
<e:menuItem id="login" action="casLogin"
	value="#{msgs['NAVIGATION.TEXT.LOGIN']}"
	accesskey="#{msgs['NAVIGATION.ACCESSKEY.LOGIN']}"
	rendered="#{sessionController.printLogin}" />
<e:menuItem id="logout" action="#{sessionController.logout}"
	value="#{msgs['NAVIGATION.TEXT.LOGOUT']}"
	accesskey="#{msgs['NAVIGATION.ACCESSKEY.LOGOUT']}"
	rendered="#{sessionController.printLogout}" />
