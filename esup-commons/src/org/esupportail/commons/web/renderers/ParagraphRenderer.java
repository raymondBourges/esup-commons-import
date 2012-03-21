/**
 * ESUP-Portail Commons - Copyright (c) 2006-2009 ESUP-Portail consortium.
 */
package org.esupportail.commons.web.renderers;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlOutputFormat;
import javax.faces.context.FacesContext;

import org.esupportail.commons.web.tags.config.TagsConfigurator;

/**
 * ESUP-Portail renderer for the section tag.
 */
public class ParagraphRenderer extends AbstractTagWrapperRenderer {

	/**
	 * The renderer type.
	 */
	public static final String RENDERER_TYPE = "org.esupportail.ParagraphRenderer";

	/**
	 * Constructor.
	 */
	public ParagraphRenderer() {
		super();
	}
	
	@Override
    public void internalEncodeBegin(FacesContext facesContext, UIComponent uiComponent) throws IOException {
	    ((HtmlOutputFormat) uiComponent).setStyle(TagsConfigurator.getInstance().getParagraphStyleClass());
        super.internalEncodeBegin(facesContext, uiComponent);
    }

    /**
	 * @see org.esupportail.commons.web.renderers.AbstractTagWrapperRenderer#getTag()
	 */
	@Override
	protected String getTag() {
		return TagsConfigurator.getInstance().getParagraphTag();
	}

}
