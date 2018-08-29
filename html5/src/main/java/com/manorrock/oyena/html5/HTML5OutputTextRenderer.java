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
 * The HTML5 renderer for h:outputText.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
@FacesRenderer(renderKitId = "HTML5", componentFamily = "javax.faces.Output", rendererType = "javax.faces.Text")
public class HTML5OutputTextRenderer extends HTML5BaseRenderer {

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
            Map<String, Object> attributes = component.getAttributes();
            String styleClass = (String) attributes.get("styleClass");
            if (attributes.containsKey("styleClass") || attributes.containsKey("style")
                    || attributes.containsKey("dir") || attributes.containsKey("lang")) {
                responseWriter.startElement("span", component);
            }
            encodeId(context, component);
            encodeAttribute(context, component, "styleClass", "class");
            encodeAttribute(context, component, "dir", "dir");
            encodeAttribute(context, component, "lang", "lang");
            encodeAttribute(context, component, "role", "role");
            encodeAttribute(context, component, "style", "style");
            encodeAttribute(context, component, "title", "title");
            if (attributes.get("value") != null)  {
                responseWriter.writeText(attributes.get("value"), null);
            }
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
            Map<String, Object> attributes = component.getAttributes();
            if (attributes.containsKey("styleClass") || attributes.containsKey("style")
                    || attributes.containsKey("dir") || attributes.containsKey("lang")) {
                responseWriter.endElement("span");
            }
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