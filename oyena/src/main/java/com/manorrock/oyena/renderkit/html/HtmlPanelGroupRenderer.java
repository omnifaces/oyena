/*
 * Copyright (c) 2002-2018 Manorrock.com. All Rights Reserved.
 */
package com.manorrock.oyena.renderkit.html;

import java.io.IOException;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;
import javax.faces.render.Renderer;

/**
 * The HTML renderer for h:panelGroup.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
@FacesRenderer(
        componentFamily = "javax.faces.Panel",
        renderKitId = "HTML_BASIC",
        rendererType = "javax.faces.Group")
public class HtmlPanelGroupRenderer extends Renderer {

    /**
     * Encode the begin.
     *
     * @param context the context.
     * @param component the UI component.
     * @throws IOException when an I/O error occurs.
     */
    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        if (component.isRendered()) {
            ResponseWriter writer = context.getResponseWriter();
            ValueExpression ve = component.getValueExpression("layout");
            String layout = ve != null ? (String) ve.getValue(context.getELContext()) : "";
            if (layout.equals("block")) {
                writer.startElement("div", component);
            } else {
                writer.startElement("span", component);
            }
            context.getAttributes().put(component.getId() + "_layout", layout);
            for (String attributeName : component.getAttributes().keySet()) {
                switch (attributeName) {
                    case "layout":
                        break;
                    case "styleClass": {
                        ve = component.getValueExpression("styleClass");
                        if (ve != null) {
                            String styleClass = (String) ve.getValue(context.getELContext());
                            writer.writeAttribute("class", styleClass, "styleClass");
                        }
                    }
                    break;
                    default: {
                        ve = component.getValueExpression(attributeName);
                        if (ve != null) {
                            String value = (String) ve.getValue(context.getELContext());
                            writer.writeAttribute(attributeName, value, attributeName);
                        }
                    }
                }
            }
        }
    }

    /**
     * Encode the children.
     *
     * @param context the context.
     * @param component the UI component.
     * @throws IOException when an I/O error occurs.
     */
    @Override
    public void encodeChildren(FacesContext context, UIComponent component) throws IOException {
        if (component.isRendered()) {
            for (UIComponent child : component.getChildren()) {
                child.encodeAll(context);
            }
        }
    }

    /**
     * Encode the end.
     *
     * @param context the context.
     * @param component the UI component.
     * @throws IOException when an I/O error occurs.
     */
    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        if (component.isRendered()) {
            ResponseWriter writer = context.getResponseWriter();
            String layout = (String) context.getAttributes().remove(component.getId() + "_layout");
            if (layout.equals("block")) {
                writer.endElement("div");
            } else {
                writer.endElement("span");
            }
        }
    }

    /**
     * Do we render our children ourselves?
     *
     * @return true as we are.
     */
    @Override
    public boolean getRendersChildren() {
        return true;
    }
}
