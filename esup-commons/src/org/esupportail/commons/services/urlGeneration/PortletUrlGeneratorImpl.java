package org.esupportail.commons.services.urlGeneration;

import java.lang.reflect.InvocationTargetException;

import javax.faces.context.FacesContext;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;
import javax.portlet.WindowStateException;

public class PortletUrlGeneratorImpl extends UportalUrlGeneratorImpl {

    /**
     * 
     */
    private static final long serialVersionUID = -8469800283864537311L;

    @Override
    protected String getUportalGuestUrl() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (facesContext != null) {
            Object response = facesContext.getExternalContext().getResponse();

            try {
                PortletURL portletURL = (PortletURL) response.getClass().getMethod("createRenderURL",new Class[]{}).invoke(response,new Object[]{});
                try {
                    portletURL.setWindowState(WindowState.MAXIMIZED);
                } catch (WindowStateException e) {
                    throw new RuntimeException(e);
                }
                return portletURL.toString();
            } catch (IllegalArgumentException e) {
                throw new RuntimeException(e);
            } catch (SecurityException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
   
        }
        return super.getUportalGuestUrl();

    }

    @Override
    protected String getUportalUrl() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (facesContext != null) {
            PortletRequest portletRequest = (PortletRequest) facesContext.getExternalContext().getRequest();
            return portletRequest.getScheme() + "://" + portletRequest.getServerName() + ":" + portletRequest.getServerPort()+ portletRequest.getContextPath();
        }
        return super.getUportalUrl();
    }

}
