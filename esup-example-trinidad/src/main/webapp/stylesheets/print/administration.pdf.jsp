<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/trinidad" prefix="tr"%>

<f:view locale="#{sessionController.locale}">
    <tr:document title="#{msgs['EXAMPLE.TEXT']}">
        <f:facet name="metaContainer">
            <link rel="stylesheet" type="text/css" href="media/print/alice.css" media="print">
        </f:facet>
        <div id="header" style=""><tr:outputText value="#{msgs['EXAMPLE.TEXT']}" /></div>
        <div id="footer" style="">Page <span id="pagenumber" /> / <span id="pagecount" /></div>
        <div style="margin-top: 20px; margin-bottom: 10px;"><tr:outputFormatted
            styleUsage="instruction" value="<b>#{msgs['ADMINISTRATION.ADMINISTRATORS.TEXT']}</b>" /></div>
        <tr:table value="#{administrationController.administrators}" var="administrator"
            emptyText="#{msgs['ADMINISTRATION.ADMINISTRATORS.NOBODY']}"
            shortDesc="#{msgs['ADMINISTRATION.ADMINISTRATORS.TEXT']}"
            summary="#{msgs['ADMINISTRATION.ADMINISTRATORS.TEXT']}" rowBandingInterval="1">
            <tr:column>
                <tr:outputFormatted value="#{administrator.displayName}" />
            </tr:column>
        </tr:table>
    </tr:document>
</f:view>
