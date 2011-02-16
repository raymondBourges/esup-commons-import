<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/trinidad" prefix="tr"%>

<f:view locale="#{sessionController.locale}" >
    <tr:document title="#{msgs['EXAMPLE.TEXT']}" >
        <f:facet name="metaContainer">
             <link rel="stylesheet" type="text/css" href="media/print/alice.css" media="print">
             <meta http-equiv="Content-Type" content="application/pdf"/>             
        </f:facet>
        <div id="header" style=""><tr:outputText value="#{msgs['EXAMPLE.TEXT']}" /></div>
        <div id="footer" style="">Page <span id="pagenumber" /> / <span id="pagecount" /></div>
        <div style="margin-top: 20px; margin-bottom:10px;">
        <tr:outputFormatted styleUsage="instruction"
            value="<b>#{msgs['HELP.TEXT']}</b>" />        </p>
        <tr:spacer height="20px" />
        <tr:outputFormatted styleUsage="instruction" value="<b>#{msgs['HELP.TEXT.SUMMARY']}</b>" />
    </tr:document>
</f:view>