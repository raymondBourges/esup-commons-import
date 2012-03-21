/**
 * ESUP-Portail Commons - Copyright (c) 2006-2009 ESUP-Portail consortium.
 */
package org.esupportail.commons.web.component;

import java.io.IOException;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;

import org.apache.myfaces.component.html.ext.HtmlGraphicImage;
import org.apache.myfaces.component.html.ext.HtmlOutputText;
import org.apache.myfaces.custom.div.Div;
import org.apache.myfaces.shared_impl.renderkit.html.HTML;


/**
 * This class represents e:form elements.
 */
public class UIForm extends javax.faces.component.UIForm {

	/**
	 * The type Component.
	 */
	public static final String COMPONENT_TYPE = "org.esupportail.commons.component.form";
	
	/**
	 * The id of the submit popup.
	 */
	public static final String SUBMIT_POPUP_ID = "submitPopup";
	
	/**
	 * The submitPopupText attribute.
	 */
	public static final String SUBMIT_POPUP_TEXT = "submitPopupText";
	
	/**
	 * The submitPopupImage attribute.
	 */
	public static final String SUBMIT_POPUP_IMAGE = "submitPopupImage";
	
	 private String _showSubmitPopupText;
	 private String _showSubmitPopupImage;
	 private String _freezeScreenOnSubmit;
	
	/**
	 * Constructor.
	 */
	public UIForm() {
		super();
	}
	
    /**
     * @param localValue
     * @param valueBindingName
     * @return the value
     */
    private Object getLocalOrValueBindingValue(Object localValue,
                    String valueBindingName)
    {
        if (localValue != null)
            return localValue;
        ValueBinding vb = getValueBinding(valueBindingName);
        return vb != null ? vb.getValue(getFacesContext()) : null;
    }

	/**
	 * @return true if the form already has a submit popup.
	 */
	@SuppressWarnings("unchecked")
	protected boolean hasSubmitPopup() {
		for (UIComponent c : (List<UIComponent>) getChildren()) {
			if (SUBMIT_POPUP_ID.equals(c.getId())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Add the submitPopup.
	 * @param context
	 */
	@SuppressWarnings("unchecked")
	protected void addSubmitPopupIfNeeded(final FacesContext context) {
		// add a div
		Div div = new Div();
		div.setId("submitPopup");
		div.setStyleClass("form-submit-popup");
		div.setStyle("visibility: hidden");
		div.setRendered(true);
		getChildren().add(div);
		// add the image to the div
		HtmlGraphicImage img = new HtmlGraphicImage();
		img.setId("submitPopupImage");
		img.setStyle("visibility: hidden");
		img.setRendered(true);
		div.getChildren().add(img);
		// add the text to the div
		HtmlOutputText text = new HtmlOutputText();
		text.setId("submitPopupText");
		text.setStyle("visibility: hidden");
		text.setRendered(true);
		div.getChildren().add(text);
	    String theOnsubmit = "";
	    Object onsubmit = getAttributes().get(HTML.ONSUMBIT_ATTR);
        if (onsubmit != null) {
            theOnsubmit += "{ " + onsubmit + " }; ";
        }
        theOnsubmit += "if (typeof(onFormSubmit2) == 'function') { onFormSubmit2(this, ";
        theOnsubmit += getFreezeScreenOnSubmit();
        theOnsubmit += ", ";
        theOnsubmit += getShowSubmitPopupText();
        theOnsubmit += ", ";
        theOnsubmit += getShowSubmitPopupImage();
        theOnsubmit += "); } else onFormSubmit();";
        getAttributes().put(HTML.ONSUMBIT_ATTR,theOnsubmit);
	}

	/**
	 * @see javax.faces.component.UIComponentBase#encodeBegin(javax.faces.context.FacesContext)
	 */
	@Override
	public void encodeBegin(final FacesContext context) throws IOException {
		if (!hasSubmitPopup()) {
			addSubmitPopupIfNeeded(context);
		}
		super.encodeBegin(context);
	}


	

    public String getShowSubmitPopupText()
    {
        return (String) getLocalOrValueBindingValue(_showSubmitPopupText, "showSubmitPopupText" );
    }


    public void setShowSubmitPopupText(String showSubmitPopupText)
    {
        _showSubmitPopupText = showSubmitPopupText;
    }
    

    public String getShowSubmitPopupImage()
    {
        return (String) getLocalOrValueBindingValue(_showSubmitPopupImage, "showSubmitPopupImage" );
    }


    public void setShowSubmitPopupImage(String showSubmitPopupImage)
    {
        _showSubmitPopupImage = showSubmitPopupImage;
    }
    
    public String getFreezeScreenOnSubmit()
    {
        return (String) getLocalOrValueBindingValue(_freezeScreenOnSubmit, "freezeScreenOnSubmit" );
    }


    public void setFreezeScreenOnSubmit(String freezeScreenOnSubmit)
    {
        _freezeScreenOnSubmit = freezeScreenOnSubmit;
    }
	
}
