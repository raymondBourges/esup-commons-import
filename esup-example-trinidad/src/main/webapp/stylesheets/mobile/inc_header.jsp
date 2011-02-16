<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/trinidad" prefix="tr"%>

<tr:panelHeader styleClass="toolbar" text="#{msgs['M_EXAMPLE.TEXT']}">
    <tr:commandLink styleClass="backButton" text="#{msgs['BACK.TEXT']}" action="welcome"
         rendered="#{not empty sessionController.action 
            and sessionController.action!='welcome' and not (sessionController.action=='administration' and administrationController.newAdministratorFormInPlace)}">
        <f:setPropertyActionListener value="welcome" target="#{sessionController.action}" />
    </tr:commandLink>
    <tr:commandLink styleClass="cancelButton" text="#{msgs['CANCEL.TEXT']}"
        action="#{administrationController.resetNewAdministratorAction}" immediate="true"
        rendered="#{sessionController.action=='administration' and administrationController.newAdministratorFormInPlace}">
    </tr:commandLink>
    <tr:commandLink styleClass="acceptButton" text="#{msgs['SUBMIT.TEXT']}"
        action="#{administrationController.searchAdministratorAction}"
        rendered="#{empty administrationController.personItems and sessionController.action=='administration' and administrationController.newAdministratorFormInPlace}">
    </tr:commandLink>
    <tr:commandLink styleClass="button" text="#{msgs['WELCOME.TEXT']}" action="welcome"
        rendered="#{not empty sessionController.action 
            and sessionController.action!='welcome' 
            and sessionController.action!='administration' 
            and sessionController.action!='preferences' 
            and sessionController.action!='about' 
            and sessionController.action!='help'}">
        <f:setPropertyActionListener value="welcome" target="#{sessionController.action}" />
    </tr:commandLink>
</tr:panelHeader>
