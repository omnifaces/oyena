/*
 * Copyright (c) 2002-2018 Manorrock.com. All Rights Reserved.
 */
package com.manorrock.oyena.html;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;
import javax.faces.render.Renderer;

/**
 * The HTML renderer for h:panelGrid.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
@FacesRenderer(
        componentFamily = "javax.faces.Panel",
        renderKitId = "HTML_BASIC",
        rendererType = "javax.faces.Grid")
public class HtmlPanelGridRenderer extends Renderer {

    /*
     * FIXME - the HTML_BASIC renderkit documentation does not specify how to deal
     *         with bodyrows. An issue should be filed.
     *       - the HTML_BASIC renderkit documentation does not specify how to deal
     *         with rowClass. An issue should be filed.
     */
    /**
     * Encode the "caption" facet.
     *
     * @param context the context.
     * @param component the UI component.
     * @throws IOException when an I/O error occurs.
     */
    private void encodeCaptionFacet(FacesContext context, UIComponent component) throws IOException {
        if (component.isRendered() && component.getFacet("caption") != null) {
            ResponseWriter writer = context.getResponseWriter();
            UIComponent caption = component.getFacet("caption");
            writer.startElement("caption", caption);
            if (component.getValueExpression("captionClass") != null) {
                ValueExpression ve = component.getValueExpression("captionClass");
                writer.writeAttribute("class", ve.getValue(context.getELContext()), "captionClass");
            }
            if (component.getValueExpression("captionStyle") != null) {
                ValueExpression ve = component.getValueExpression("captionStyle");
                writer.writeAttribute("style", ve.getValue(context.getELContext()), "captionStyle");
            }
            caption.encodeAll(context);
            writer.endElement("caption");
        }
    }

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
            writer.startElement("table", component);
            for (String attributeName : component.getAttributes().keySet()) {
                switch (attributeName) {
                    case "bodyRows":
                    case "captionClass":
                    case "captionStyle":
                    case "columnClasses":
                    case "columns":
                    case "footerClass":
                    case "headerClass":
                    case "rowClass":
                    case "rowClasses":
                        break;
                    case "styleClass": {
                        ValueExpression ve = component.getValueExpression("styleClass");
                        if (ve != null) {
                            String styleClass = (String) ve.getValue(context.getELContext());
                            writer.writeAttribute("class", styleClass, "styleClass");
                        }
                    }
                    break;
                    default: {
                        ValueExpression ve = component.getValueExpression(attributeName);
                        if (ve != null) {
                            String value = (String) ve.getValue(context.getELContext());
                            writer.writeAttribute(attributeName, value, attributeName);
                        }
                    }
                }
            }
            encodeCaptionFacet(context, component);
            encodeHeaderFacet(context, component);
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
            ResponseWriter writer = context.getResponseWriter();
            writer.startElement("tbody", null);
            if (component.getValueExpression("columns") == null) {
                writer.startElement("tr", null);
                for (UIComponent child : component.getChildren()) {
                    writer.startElement("td", child);
                    child.encodeAll(context);
                    writer.endElement("td");
                }
                writer.endElement("tr");
            } else {
                List<String> columnClasses = null;
                if (component.getValueExpression("columnClasses") != null) {
                    String columnString = (String) component.
                            getValueExpression("columnClasses").getValue(context.getELContext());
                    columnClasses = Arrays.asList(columnString.split(","));
                }
                int columnIndex = 0;
                long columns = (long) component.getValueExpression("columns").getValue(context.getELContext());
                List<String> rowClasses = null;
                if (component.getValueExpression("rowClasses") != null) {
                    String rowString = (String) component.
                            getValueExpression("rowClasses").getValue(context.getELContext());
                    rowClasses = Arrays.asList(rowString.split(","));
                }
                int rowClassIndex = 0;
                Iterator<UIComponent> iterator = component.getChildren().iterator();
                while (iterator.hasNext()) {
                    UIComponent child = iterator.next();
                    if (child.isRendered()) {
                        if (columnIndex == 0) {
                            writer.startElement("tr", null);
                            if (rowClasses != null && rowClasses.get(rowClassIndex) != null) {
                                writer.writeAttribute("class", rowClasses.get(rowClassIndex), null);
                                rowClassIndex++;
                                if (rowClassIndex == rowClasses.size()) {
                                    rowClassIndex = 0;
                                }
                            }
                        }
                        writer.startElement("td", child);
                        if (columnClasses != null && columnClasses.get(columnIndex) != null) {
                            writer.writeAttribute("class", columnClasses.get(columnIndex), null);
                        }
                        child.encodeAll(context);
                        writer.endElement("td");
                        columnIndex++;
                        if (columnIndex == columns) {
                            writer.endElement("tr");
                            columnIndex = 0;
                        }
                    }
                }
            }
            writer.endElement("tbody");
        }
    }

    /**
     * Encode the "footer" facet.
     *
     * @param context the context.
     * @param component the UI component.
     * @throws IOException when an I/O error occurs.
     */
    private void encodeFooterFacet(FacesContext context, UIComponent component) throws IOException {
        if (component.isRendered() && component.getFacet("footer") != null) {
            ResponseWriter writer = context.getResponseWriter();
            UIComponent header = component.getFacet("footer");
            writer.startElement("tfoot", header);
            writer.startElement("tr", null);
            writer.startElement("td", null);
            if (component.getValueExpression("footerClass") != null) {
                ValueExpression ve = component.getValueExpression("footerClass");
                writer.writeAttribute("class", ve.getValue(context.getELContext()), "footerClass");
            }
            writer.writeAttribute("scope", "colgroup", null);
            if (component.getValueExpression("columns") != null) {
                ValueExpression ve = component.getValueExpression("columns");
                writer.writeAttribute("colspan", ve.getValue(context.getELContext()), "columns");
            }
            writer.endElement("th");
            writer.endElement("tr");
            writer.endElement("thead");
        }
    }

    /**
     * Encode the "header" facet.
     *
     * @param context the context.
     * @param component the UI component.
     * @throws IOException when an I/O error occurs.
     */
    private void encodeHeaderFacet(FacesContext context, UIComponent component) throws IOException {
        if (component.isRendered() && component.getFacet("header") != null) {
            ResponseWriter writer = context.getResponseWriter();
            UIComponent header = component.getFacet("header");
            writer.startElement("thead", header);
            writer.startElement("tr", null);
            writer.startElement("th", null);
            if (component.getValueExpression("headerClass") != null) {
                ValueExpression ve = component.getValueExpression("headerClass");
                writer.writeAttribute("class", ve.getValue(context.getELContext()), "headerClass");
            }
            writer.writeAttribute("scope", "colgroup", null);
            if (component.getValueExpression("columns") != null) {
                ValueExpression ve = component.getValueExpression("columns");
                writer.writeAttribute("colspan", ve.getValue(context.getELContext()), "columns");
            }
            writer.endElement("th");
            writer.endElement("tr");
            writer.endElement("thead");
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
            encodeFooterFacet(context, component);
            writer.endElement("table");
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
