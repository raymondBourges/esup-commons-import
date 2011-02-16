<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/trinidad" prefix="tr"%>

<f:view locale="#{sessionController.locale}">
    <tr:document title="#{msgs['EXAMPLE.TEXT']}">
        <jsp:include page="inc_facet.jsp" />
        <tr:form>
            <jsp:include page="inc_header.jsp" />
            <div class="panelBase"><tr:panelCaptionGroup
                rendered="#{not empty sessionController.currentUser}">
                <div class="row"><tr:outputText styleClass="labelText"
                    value="#{msgs['USER.TEXT']}: " /> <tr:outputText styleClass="messageText"
                    value="#{sessionController.currentUser.displayName}"
                    rendered="#{not empty sessionController.currentUser}" /></div>
            </tr:panelCaptionGroup> <tr:panelCaptionGroup>
                <h:panelGroup rendered="#{not empty sessionController.currentUser and sessionController.currentUser.admin}">
                <div class="row"><tr:commandLink text="#{msgs['ADMINISTRATION.TEXT']}"
                    action="administration" styleClass="centerMessageLink">
                    <f:setPropertyActionListener value="administration"
                        target="#{sessionController.action}" />
                </tr:commandLink></div></h:panelGroup>
                <div class="row"><tr:commandLink text="#{msgs['PREFERENCES.TEXT']}"
                    action="preferences" styleClass="centerMessageLink">
                    <f:setPropertyActionListener value="preferences"
                        target="#{sessionController.action}" />
                </tr:commandLink></div>
                <div class="row"><tr:commandLink text="#{msgs['ABOUT.TEXT']}" action="about"
                    styleClass="centerMessageLink">
                    <f:setPropertyActionListener value="about" target="#{sessionController.action}" />
                </tr:commandLink></div>
                <div class="row"><tr:commandLink text="#{msgs['HELP.TEXT']}" action="help"
                    styleClass="centerMessageLink">
                    <f:setPropertyActionListener value="help" target="#{sessionController.action}" />
                </tr:commandLink></div>
                <h:panelGroup rendered="#{sessionController.loginEnable}">
                    <div class="row"><tr:goLink text="#{msgs['LOGIN.TEXT']}"
                        destination="/stylesheets/protected/m_login.jsf"
                        styleClass="centerMessageLink" /></div>
                </h:panelGroup>
                <h:panelGroup rendered="#{sessionController.logoutEnable}">
                    <div class="row"><tr:commandLink text="#{msgs['LOGOUT.TEXT']}"
                        action="#{sessionController.logoutAction}" immediate="true"
                        styleClass="centerMessageLink" /></div>
                </h:panelGroup>
                <h:panelGroup rendered="#{not sessionController.portletMode}">
                    <div class="row"><tr:commandLink text="#{msgs['DESKTOP.TEXT']}"
                        action="desktopWelcome" immediate="true" styleClass="centerMessageLink" /></div>
                </h:panelGroup>
                <h:panelGroup rendered="#{sessionController.portletMode}">
                    <div class="row" style="border-top-style: solid; border-top-width: 1px;"><h:outputLink
                        value="#{sessionController.servletUrl}" target="_popup"
                        title="#{msgs['SERVLET.TEXT']}">
                        <tr:outputText value="#{msgs['SERVLET.TEXT']}"
                            styleClass="centerMessageLink" />
                    </h:outputLink></div>
                </h:panelGroup>
            </tr:panelCaptionGroup></div>
        </tr:form>
    </tr:document>
</f:view>
