<html xmlns="http://www.w3c.org/1999/xhtml"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:h="http://java.sun.com/jsf/html">
<jsp:output doctype-root-element="html"
	doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN"
	doctype-system="http://www.w3c.org/TR/xhtml1/DTD/xhtml1-transitional.dtd" />
<jsp:directive.page contentType="text/html" />
<head>
</head>
<body>
<jsp:scriptlet>
String newLocation = "stylesheets/welcome.faces";
response.sendRedirect(newLocation);
</jsp:scriptlet>
</body>
</html>
