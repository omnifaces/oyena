/*
 * Copyright (c) 2002-2018 Manorrock.com. All Rights Reserved.
 */
package com.manorrock.oyena.taghandler.html;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.view.facelets.ComponentConfig;
import javax.faces.view.facelets.ComponentHandler;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;

/**
 * The Facelets tag handler for h:panelGrid.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
public class HtmlPanelGridTagHandler extends ComponentHandler {

    /**
     * Constructor.
     *
     * @param config the component config.
     */
    public HtmlPanelGridTagHandler(ComponentConfig config) {
        super(config);
    }

    /**
     * Set the attributes.
     * 
     * @param context the context.
     * @param instance the instance.
     */
    @Override
    public void setAttributes(FaceletContext context, Object instance) {
        TagAttribute[] tagAttributes = getTag().getAttributes().getAll();
        if (tagAttributes != null) {
            UIComponent component = (UIComponent) instance;
            for (TagAttribute tagAttribute : tagAttributes) {
                if (tagAttribute.isLiteral()) {
                    ValueExpression ve = context.getExpressionFactory().
                            createValueExpression(context.getFacesContext().getELContext(), 
                                    "#{" + tagAttribute.getValue() + "}", Object.class);
                    component.setValueExpression(tagAttribute.getQName(), ve);
                    /*
                     * FIXME - we do not have access to the names of each 
                     *         ValueExpression, so we work around it by pushing 
                     *         the name into the attributes of the component so we
                     *         can use the attributes keyset to iterator over the
                     *         names. An issue should be filed for this.
                     */
                    component.getAttributes().put(tagAttribute.getQName(), "");
                } else {
                    ValueExpression ve = context.getExpressionFactory().
                            createValueExpression(context.getFacesContext().getELContext(),
                                    tagAttribute.getValue(), Object.class);
                    component.setValueExpression(tagAttribute.getQName(), ve);
                    /*
                     * FIXME - we do not have access to the names of each 
                     *         ValueExpression, so we work around it by pushing 
                     *         the name into the attributes of the component so we
                     *         can use the attributes keyset to iterator over the
                     *         names. An issue should be filed for this.
                     */
                    component.getAttributes().put(tagAttribute.getQName(), "");
                }
            }
        }
    }
}
