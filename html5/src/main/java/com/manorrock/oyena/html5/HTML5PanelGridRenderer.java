/*
 * Copyright (c) 2002-2018 Manorrock.com. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without 
 * modification, are permitted provided that the following conditions are met:
 *
 *   1. Redistributions of source code must retain the above copyright notice, 
 *      this list of conditions and the following disclaimer.
 *   2. Redistributions in binary form must reproduce the above copyright notice,
 *      this list of conditions and the following disclaimer in the documentation
 *      and/or other materials provided with the distribution.
 *   3. Neither the name of the copyright holder nor the names of its 
 *      contributors may be used to endorse or promote products derived from this
 *      software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.manorrock.oyena.html5;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

/**
 * The HTML5 renderer for h:panelGrid.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
@FacesRenderer(renderKitId = "HTML5", componentFamily = "javax.faces.Panel", rendererType = "javax.faces.Grid")
public class HTML5PanelGridRenderer extends HTML5BaseRenderer {

    /**
     * Encode the begin.
     *
     * @param context the Faces context.
     * @param component the UI component.
     * @throws IOException when an I/O error occurs.
     */
    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        if (component.isRendered()) {
            ResponseWriter responseWriter = context.getResponseWriter();
            responseWriter.startElement("table", component);
            encodeId(context, component);
            encodeAttribute(context, component, "styleClass", "class");
            encodeAttribute(context, component, "bgcolor", "bgcolor");
            encodeAttribute(context, component, "border", "border");
            encodeAttribute(context, component, "cellpadding", "cellpadding");
            encodeAttribute(context, component, "cellspacing", "cellspacing");
            encodeAttribute(context, component, "dir", "dir");
            encodeAttribute(context, component, "frame", "frame");
            encodeAttribute(context, component, "lang", "lang");
            // TODO encode onXXX attributes
            encodeAttribute(context, component, "role", "role");
            encodeAttribute(context, component, "rules", "rules");
            encodeAttribute(context, component, "style", "style");
            encodeAttribute(context, component, "summary", "summary");
            encodeAttribute(context, component, "title", "title");
            encodeAttribute(context, component, "width", "width");
            encodeCaption(context, component);
            encodeHeader(context, component);
            encodeFooter(context, component);
        }
    }

    /**
     * Encode the caption.
     *
     * @param context the Faces context.
     * @param component the UI component.
     * @throws IOException when an I/O error occurs.
     */
    private void encodeCaption(FacesContext context, UIComponent component) throws IOException {
        ResponseWriter responseWriter = context.getResponseWriter();
        if (component.getFacet("caption") != null) {
            UIComponent caption = component.getFacet("caption");
            responseWriter.startElement("caption", caption);
            encodeAttribute(context, component, "captionClass", "class");
            encodeAttribute(context, component, "captionStyle", "style");
            caption.encodeAll(context);
            responseWriter.endElement("caption");
        }
    }

    /**
     * Encode the children.
     *
     * @param context the Faces context.
     * @param component the UI component.
     * @throws IOException when an I/O error occurs.
     */
    @Override
    public void encodeChildren(FacesContext context, UIComponent component) throws IOException {
        Map<String, Object> attributes = component.getAttributes();
        if (attributes.get("columns") == null) {
            if (component.isRendered()) {
                Iterator<UIComponent> children = component.getChildren().iterator();
                while (children.hasNext()) {
                    UIComponent child = children.next();
                    child.encodeAll(context);
                }
            }
        } else {
            int columns = (int) attributes.get("columns");
            // TODO encode using the specified number of columns.
        }
    }

    /**
     * Encode the end.
     *
     * @param context the Faces context.
     * @param component the UI component.
     * @throws IOException when an I/O error occurs.
     */
    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        if (component.isRendered()) {
            ResponseWriter responseWriter = context.getResponseWriter();
            responseWriter.endElement("table");
        }
    }

    /**
     * Encode the footer.
     *
     * @param context the Faces context.
     * @param component the UI component.
     * @throws IOException when an I/O error occurs.
     */
    private void encodeFooter(FacesContext context, UIComponent component) throws IOException {
        ResponseWriter responseWriter = context.getResponseWriter();
        if (component.getFacet("footer") != null) {
            UIComponent header = component.getFacet("footer");
            responseWriter.startElement("tfoot", header);
            responseWriter.startElement("tr", null);
            responseWriter.startElement("td", null);
            encodeAttribute(context, component, "footerClass", "class");
            encodeAttribute(context, component, "colgroup", "scope");
            encodeAttribute(context, component, "columns", "colspan");
            header.encodeAll(context);
            responseWriter.endElement("td");
            responseWriter.endElement("tr");
            responseWriter.endElement("tfoot");
        }
    }

    /**
     * Encode the header.
     *
     * @param context the Faces context.
     * @param component the UI component.
     * @throws IOException when an I/O error occurs.
     */
    private void encodeHeader(FacesContext context, UIComponent component) throws IOException {
        ResponseWriter responseWriter = context.getResponseWriter();
        if (component.getFacet("header") != null) {
            UIComponent header = component.getFacet("header");
            responseWriter.startElement("thead", header);
            responseWriter.startElement("tr", null);
            responseWriter.startElement("th", null);
            encodeAttribute(context, component, "headerClass", "class");
            encodeAttribute(context, component, "colgroup", "scope");
            encodeAttribute(context, component, "columns", "colspan");
            header.encodeAll(context);
            responseWriter.endElement("th");
            responseWriter.endElement("tr");
            responseWriter.endElement("thead");
        }
    }

    /**
     * Do we render our children?
     *
     * @return true
     */
    @Override
    public boolean getRendersChildren() {
        return true;
    }
}
