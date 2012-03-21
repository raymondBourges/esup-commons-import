package org.esupportail.commons.jsf;

import java.io.IOException;
import java.util.Locale;

import javax.el.ELException;
import javax.faces.FacesException;
import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.el.ValueBinding;
import javax.faces.webapp.UIComponentTag;

import org.apache.myfaces.shared_impl.util.LocaleUtils;
import org.esupportail.commons.exceptions.WebFlowException;
import org.esupportail.commons.utils.ContextUtils;
import org.esupportail.commons.web.renderers.MenuItemRenderer;
import org.esupportail.commons.web.tags.TagUtils;
import org.esupportail.commons.web.tags.config.TagsConfigurator;

import com.sun.facelets.FaceletContext;
import com.sun.facelets.FaceletException;
import com.sun.facelets.tag.TagAttribute;
import com.sun.facelets.tag.TagConfig;
import com.sun.facelets.tag.TagHandler;

public class PageTagHandler extends TagHandler {
    


    public PageTagHandler(TagConfig config) {
        super(config);
    }

    @Override
    public void apply(FaceletContext ctx, UIComponent parent) throws IOException, FacesException, FaceletException, ELException {
        if (!isAuthorized(ctx)) {
            throw new WebFlowException("Page not allowed");
        }
        TagAttribute menuItemAttr = this.getAttribute("menuItem");
        if (menuItemAttr != null) {
            ContextUtils.setRequestAttribute(MenuItemRenderer.CURRENT_MENU_ITEM_ATTRIBUTE, menuItemAttr.getValue());
        }
        Application application = ctx.getFacesContext().getApplication();
        Locale locale;
        TagAttribute localeAttr = this.getAttribute("locale");
        if (localeAttr != null) {
            String localeString = localeAttr.getValue();
            if (UIComponentTag.isValueReference(localeString)) {
                ValueBinding vb = application.createValueBinding(localeString);
                Object localeValue = vb.getValue(ctx.getFacesContext());
                if (localeValue instanceof Locale) {
                    locale = (Locale) localeValue;
                } else if (localeValue instanceof String) {
                    locale = LocaleUtils.toLocale((String) localeValue);
                } else {
                    if (localeValue != null) {
                        throw new IllegalArgumentException(
                                "Locale or String class expected. Expression: " + localeString
                                + ". Class: " + localeValue.getClass().getName());
                    }
                    locale = getDefaultLocale();
                }
            } else {
                locale = LocaleUtils.toLocale(localeString);
            }
        } else {
            locale = getDefaultLocale();
        }
        Long downloadId = null;
        TagAttribute downloadIdAttr = this.getAttribute("downloadId");
        if (downloadIdAttr != null) {
            String downloadIdString = downloadIdAttr.getValue();
            if (UIComponentTag.isValueReference(downloadIdString)) {
                ValueBinding vb = application.createValueBinding(downloadIdString);
                Object downloadIdValue = vb.getValue(ctx.getFacesContext());
                if (downloadIdValue != null) {
                    if (downloadIdValue instanceof Long) {
                        downloadId = (Long) downloadIdValue;
                    } else if (downloadIdValue instanceof String) {
                        downloadId = Long.valueOf(downloadIdValue.toString());
                    } else {
                        throw new IllegalArgumentException(
                                "Long or String class expected. Expression: " + downloadIdString
                                + ". Class: " + downloadIdValue.getClass().getName());
                    }
                    locale = getDefaultLocale();
                }
            }
        }

        ctx.setLocale(locale);

        this.nextHandler.apply(ctx, parent); 
    }
    
    /**
     * @return the default locale to apply.
     */
    private Locale getDefaultLocale() {
        TagsConfigurator tagsConfigurator = TagsConfigurator.getInstance();
        return tagsConfigurator.getLocale();
    }
    
    /**
     * @return true if the page is authorized.
     */
    private boolean isAuthorized(FaceletContext ctx) {
        TagAttribute authorizedAttr = this.getAttribute("authorized");
        if (authorizedAttr == null) {
            return true;
        }
        Boolean authorized = TagUtils.getBooleanAttributeValue("authorized", authorizedAttr.getValue());
        if (authorized == null) {
            return true;
        }
        return authorized.booleanValue();
    }

}
