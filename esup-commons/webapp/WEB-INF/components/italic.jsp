<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
 
<html xmlns="http://www.w3.org/1999/xhtml" 
	  xmlns:h="http://java.sun.com/jsf/html"
      xmlns:ui="http://java.sun.com/jsf/facelets"> 
 
<ui:component> 
 
   <ui:fragment rendered="#{empty rendered or rendered}">
	  <h:outputFormat value="#{value}" style="font-style: italic;">
	  	<ui:insert/>
	  </h:outputFormat>
  </ui:fragment>
  

 
</ui:component> 
 
</html>