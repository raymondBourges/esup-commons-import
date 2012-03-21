package org.esupportail.commons.jsf;

/*
 * Copyright 2002-2005 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.springframework.context.MessageSource;

import java.util.Locale;

import javax.faces.context.FacesContext;
import javax.faces.el.PropertyResolver;


/**
 * This class is a JSF <code>PropertyResolver</code> for Spring's <code>MessageSource</code>.
 * It allows you to extract message names as properties from the <code>MessageSource</code>.
 * For example, you could get a message using the following syntax:
 *    &lt;h:outputText value="#{messageSource.firstName}" /&gt;.
 * @author Rick Hightower
 */
public class MessageSourcePropertyResolver extends PropertyResolver {
    /**
     * No Arguments to message source.
     */
    private static final Object[] NO_ARGS = new Object[] {};

    // ------------------------------------------------------ Instance Variables

    /**
     * <p>The original <code>VariableResolver</code>.</p>
     */
    private PropertyResolver original = null;

    /**
     * <p>Construct a new {@link MessageSourcePropertyResolver} instance.</p>
     *
     *
     * @param aOriginal Original resolver.
     */
    public MessageSourcePropertyResolver(final PropertyResolver aOriginal) {
        this.original = aOriginal;
    }

    // ------------------------------------------------ PropertyResolver Methods

    /**
     * <p>Looks up and returns the "property" of the object. If the base
     * object is a <code>MessageSource</code> class return a message from the
     * <code>MessageSource</code>.</p>
     *
     * @param base object that contains the property.
     * @param property Property to be returned.
     * @return the value in the app context.
     *  object in this context
     */
    @Override
    public Object getValue(final Object base, final Object property) {
        if (base instanceof MessageSource) {
            MessageSource messageSource = (MessageSource) base;
            Locale locale = FacesContext.getCurrentInstance().getViewRoot()
                                        .getLocale();

            return messageSource.getMessage((String) property, NO_ARGS, locale);
        } else {
            return original.getValue(base, property);
        }
    }

    /**
     * If the base object is a <code>MessageSource</code> class do nothing as
     * <code>MessageSource</code> is read only.
     *
     * @param base Base object in which to store a property
     * @param property Property to be stored
     * @param value Value to be stored
     *
     *  object in this context
     */
    @Override
    public void setValue(final Object base, final Object property,
        final Object value) {
        if (base instanceof MessageSource) {
            return; //read only for now
        } else {
            original.setValue(base, property, value);
        }
    }

    /**
     * If the base object is a <code>MessageSource</code> class return true as
     * <code>MessageSource</code> is read only.
     *
     * @param base Base object from which to return read only state
     * @param property Property to be checked
     * @return always true if MessageSource
     */
    @Override
    public boolean isReadOnly(final Object base, final Object property) {
        if (base instanceof MessageSource) {
            return true;
        } else {
            return original.isReadOnly(base, property);
        }
    }

    /**
     * If the base object is a <code>MessageSource</code> class return String.class
     * as the <code>MessageSource</code> returns Strings.
     *
     * @param base Base object from which to return a property type
     * @param property Property whose type is to be returned
     * @return type
     */
    @Override
    public Class<?> getType(final Object base, final Object property) {
        if (base instanceof MessageSource) {
            return String.class;
        } else {
            return original.getType(base, property);
        }
    }

    /**
     * <p>Convert an index into a corresponding string, and delegate
     * to <code>getValue(base, property)</code>.</p>
     *
     * @param base Base object from which to return a property
     * @param index Index to be returned
     *
     * @return object in this context
     */
    @Override
    public Object getValue(final Object base, final int index) {
        if (base instanceof MessageSource) {
            return getValue(base, "" + index);
        } else {
            return original.getValue(base, index);
        }
    }

    /**
     * If the base object is a <code>MessageSource</code> class do nothing as
     * <code>MessageSource</code> is read only.
     *
     * @param base Base object into which to store a property
     * @param index Index to be stored
     * @param value Value to be stored
     *
     *  object in this context
     */
    @Override
    public void setValue(final Object base, final int index, final Object value) {
        if (base instanceof MessageSource) {
            //AppContext read only
            return;
        } else {
            original.setValue(base, index, value);
        }
    }

    /**
      * If the base object is a <code>MessageSource</code> class return true as
      * <code>MessageSource</code> is read only.
      *
      * @param base Base object from which to check a property
      * @param index Index to be checked
      * @return true always
      */
    @Override
    public boolean isReadOnly(final Object base, final int index) {
        if (base instanceof MessageSource) {
            return true;
        } else {
            return original.isReadOnly(base, index);
        }
    }

    /**
     * <p>Convert an index into a corresponding string, and delegate to
     * <code>getType(base,property)</code>.</p>
     *
     * @param base Base object from which to return a property type
     * @param index Index whose type is to be returned
     * @return type
     */
    @Override
    public Class<?> getType(final Object base, final int index) {
        if (base instanceof MessageSource) {
            return getType(base, "" + index);
        } else {
            return original.getType(base, index);
        }
    }
}
