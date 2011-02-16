<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/trinidad" prefix="tr"%>

<%-- Directive pour prendre en compte les caractÃ¨res accentuÃ©s en saisie dans ce formulaire --%>
<jsp:directive.page contentType="text/html;charset=UTF-8" />

<f:view locale="#{sessionController.locale}">
    <tr:document title="#{msgs['EXAMPLE.TEXT']}">
        <jsp:include page="inc_facet.jsp" />
        <tr:form rendered="#{not administrationController.newAdministratorFormInPlace}">
            <jsp:include page="inc_header.jsp" />
            <tr:panelList styleClass="panelList">
                <tr:commandLink text="#{msgs['ADMINISTRATION.BUTTON.NEW_ADMINISTRATOR']}"
                    shortDesc="#{msgs['ADMINISTRATION.BUTTON.NEW_ADMINISTRATOR']}"
                    action="#{administrationController.newAdministratorAction}" />
                <h:panelGroup>
                    <tr:outputFormatted styleUsage="instruction"
                        value="<b>&nbsp;#{msgs['ADMINISTRATION.ADMINISTRATORS.TEXT']}</b>" />
                    <tr:spacer height="20px" />
                    <tr:outputFormatted styleUsage="instruction"
                        value="&nbsp;#{msgs['ADMINISTRATION.ADMINISTRATORS.DELETE']}"
                        rendered="#{administrationController.administratorsSize > 1}" />
                </h:panelGroup>
            </tr:panelList>
            <tr:table value="#{administrationController.administrators}" var="administrator"
                rows="5" width="100%" styleClass="iphoneTable" horizontalGridVisible="false" 
                rendered="#{not administrationController.newAdministratorFormInPlace}">
                <tr:column inlineStyle="padding:0 !important;">
                    <tr:panelGroupLayout layout="vertical" styleClass="listing">
                        <tr:commandLink
                            action="#{administrationController.deleteAdministratorAction}"
                            rendered="#{administrationController.administratorsSize > 1}"
                            inlineStyle="text-decoration:none;color:#000000;">
                            <f:setPropertyActionListener value="#{administrator}"
                                target="#{administrationController.administratorToDelete}" />
                            <h:graphicImage value="/media/images/user_delete.png"
                                style="float:left;border:0;margin-right:10px;"
                                alt="#{msgs['ADMINISTRATION.IMG.ALT.DELETE']}"
                                title="#{msgs['ADMINISTRATION.IMG.ALT.DELETE']}" />
                            <tr:outputText value="#{administrator.displayName}" />
                        </tr:commandLink>
                        <tr:outputFormatted styleUsage="instruction"
                            value="#{administrator.displayName}"
                            rendered="#{administrationController.administratorsSize == 1}" />
                    </tr:panelGroupLayout>
                </tr:column>
            </tr:table>
        </tr:form>
        <tr:form id="searchAdministrator"
            rendered="#{administrationController.newAdministratorFormInPlace}">
            <jsp:include page="inc_header.jsp" />
            <tr:messages globalOnly="true" inlineStyle="background-color:orange;" />
            <tr:panelList styleClass="panelList">
                <tr:outputFormatted styleUsage="instruction"
                    value="<b>&nbsp;#{msgs['ADMINISTRATION.ADMINISTRATORS.ADD.INTRO']}</b>" />
                <h:panelGroup>
                    <tr:outputFormatted styleUsage="instruction"
                        value="<b>#{msgs['ADMINISTRATION.TEXT.LABEL.SEARCH']}</b>" />
                    <tr:inputText id="searchPerson" value="#{administrationController.searchPerson}"
                        label="" maximumLength="10" required="true"
                        disabled="#{not empty administrationController.personItems}">
                        <f:validator validatorId="searchValidator" />
                    </tr:inputText>
                    <tr:spacer height="20px" />
                    <tr:outputFormatted styleUsage="instruction"
                        value="<b>&nbsp;#{msgs['ADMINISTRATION.ADMINISTRATORS.ADD.CHOICE']}</b>"
                        rendered="#{not empty administrationController.personItems}" />
                </h:panelGroup>
            </tr:panelList>
            <tr:table value="#{administrationController.personItems}" var="person" rows="5"
                width="100%" styleClass="iphoneTable" horizontalGridVisible="false"
                rendered="#{not empty administrationController.personItems}">
                <tr:column inlineStyle="padding:0 !important;">
                    <tr:panelGroupLayout layout="vertical" styleClass="listing">
                        <tr:commandLink
                            action="#{administrationController.searchAdministratorAction}"
                            inlineStyle="text-decoration:none;color:#000000;">
                            <f:setPropertyActionListener value="#{person.value}"
                                target="#{administrationController.uidToAdd}" />
                            <h:graphicImage value="/media/images/user_green.png"
                                style="float:left;border:0;margin-right:10px;"
                                alt="#{msgs['ADMINISTRATION.IMG.ALT.ADD']}"
                                title="#{msgs['ADMINISTRATION.IMG.ALT.ADD']}" />
                            <tr:outputText value="#{person.label}" />
                        </tr:commandLink>
                    </tr:panelGroupLayout>
                </tr:column>
            </tr:table>
        </tr:form>
    </tr:document>
</f:view>
