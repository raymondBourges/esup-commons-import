<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/trinidad" prefix="tr"%>

<%-- Directive pour prendre en compte les caractères accentués en saisie dans ce formulaire --%>
<jsp:directive.page contentType="text/html;charset=UTF-8" />

<f:view locale="#{sessionController.locale}">
    <tr:document title="#{msgs['EXAMPLE.TEXT']}">
        <jsp:include page="inc_navigation.jsp" />
        <tr:form rendered="#{not administrationController.newAdministratorFormInPlace}">
            <tr:spacer height="20px" />
            <h:outputLink value="#{administrationController.printUrl}" target="_popup" title="PDF">
                <h:graphicImage value="/media/images/page_white_acrobat.png"
                    style="border:0;" alt="PDF"/>
            </h:outputLink>
            <tr:outputFormatted styleUsage="instruction"
                value=" <b>#{msgs['ADMINISTRATION.TEXT']}</b>" />
            <tr:spacer height="20px" />
            <tr:commandLink action="#{administrationController.newAdministratorAction}"
                shortDesc="#{msgs['ADMINISTRATION.BUTTON.NEW_ADMINISTRATOR']}">
                <tr:image source="/media/images/user_green.png" inlineStyle="border:0;" />
            </tr:commandLink>
            <tr:outputFormatted styleUsage="instruction"
                value=" #{msgs['ADMINISTRATION.BUTTON.NEW_ADMINISTRATOR']}" />
            <tr:spacer height="20px" />
            <tr:outputFormatted styleUsage="instruction"
                value="<b>#{msgs['ADMINISTRATION.ADMINISTRATORS.TEXT']}</b>" />
            <tr:spacer height="20px" />
            <tr:table value="#{administrationController.administrators}" var="administrator"
                rows="5" emptyText="#{msgs['ADMINISTRATION.ADMINISTRATORS.NOBODY']}"
                shortDesc="#{msgs['ADMINISTRATION.ADMINISTRATORS.TEXT']}"
                summary="#{msgs['ADMINISTRATION.ADMINISTRATORS.TEXT']}" rowBandingInterval="1">
                <tr:column
                    rendered="#{not administrationController.newAdministratorFormInPlace and administrationController.administratorsSize > 1}">
                    <tr:commandLink id="delete_button"
                        action="#{administrationController.deleteAdministratorAction}"
                        onclick="if (!confirm('#{msgs['ADMINISTRATION.MESSAGE.CONFIRM_DELETE_1']} #{administrator.displayName} #{msgs['ADMINISTRATION.MESSAGE.CONFIRM_DELETE_2']}')) return false;">
                        <f:setPropertyActionListener value="#{administrator}"
                            target="#{administrationController.administratorToDelete}" />
                        <h:graphicImage value="/media/images/user_delete.png"
                            style="float:left;border:0;"
                            alt="#{msgs['ADMINISTRATION.IMG.ALT.DELETE']}"
                            title="#{msgs['ADMINISTRATION.IMG.ALT.DELETE']}" />
                    </tr:commandLink>
                </tr:column>
                <tr:column>
                    <tr:outputFormatted value="#{administrator.displayName}" />
                </tr:column>
            </tr:table>
        </tr:form>
        <tr:form rendered="#{administrationController.newAdministratorFormInPlace}">
            <tr:messages globalOnly="true" inlineStyle="background-color:orange;" />
            <tr:spacer height="20px" />
            <tr:outputFormatted styleUsage="instruction"
                value="<b>#{msgs['ADMINISTRATION.BUTTON.NEW_ADMINISTRATOR']}</b>" />
            <tr:spacer height="20px" />
            <tr:inputText id="searchPerson" value="#{administrationController.searchPerson}"
                label="#{msgs['ADMINISTRATION.TEXT.LABEL.SEARCH']}" maximumLength="10"
                required="true" disabled="#{not empty administrationController.personItems}"
                immediate="true">
                <f:validator validatorId="searchValidator" />
            </tr:inputText>
            <tr:spacer height="20px" />
            <tr:selectOneChoice id="choice" value="#{administrationController.uidToAdd}"
                label="#{msgs['ADMINISTRATION.TEXT.LABEL.CHOICE']}"
                rendered="#{not empty administrationController.personItems}" required="true">
                <f:selectItems value="#{administrationController.personItems}" />
            </tr:selectOneChoice>
            <tr:spacer height="20px" />
            <tr:commandButton text="#{msgs['SUBMIT.TEXT']}"
                action="#{administrationController.searchAdministratorAction}" />
            <tr:commandButton text="#{msgs['CANCEL.TEXT']}" immediate="true"
                action="#{administrationController.resetNewAdministratorAction}" />
        </tr:form>
    </tr:document>
</f:view>
