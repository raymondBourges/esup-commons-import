<html xmlns="http://www.w3c.org/1999/xhtml"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:p="http://primefaces.prime.com.tr/ui">
<jsp:output doctype-root-element="html"
	doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN"
	doctype-system="http://www.w3c.org/TR/xhtml1/DTD/xhtml1-transitional.dtd" />
<jsp:directive.page contentType="text/html" />

<f:view>
	<ui:debug />
	<head>
	<p:resources />
	</head>
	<body>
	<h1>Titre</h1>
	<ui:insert name="body" />
	</body>
</f:view>
</html>