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
package com.manorrock.oyena.quill;

import java.io.IOException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;
import javax.faces.render.Renderer;

/**
 * The Quill renderer.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
@FacesRenderer(
        componentFamily = "com.manorrock.oyena.quill",
        rendererType = "com.manorrock.oyena.quill.QuillRenderer")
@Deprecated
public class QuillRenderer extends Renderer {
    
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
            QuillComponent quill = (QuillComponent) component;
            ResponseWriter writer = context.getResponseWriter();
            writer.startElement("link", null);
            writer.writeURIAttribute("href", "https://cdn.quilljs.com/1.3.6/quill.snow.css", "href");
            writer.writeURIAttribute("rel", "stylesheet", "rel");
            writer.endElement("link");
            writer.startElement("div", component);
            writer.writeAttribute("id", getScriptSafeClientId(context, component), null);
            if (quill.getValue() != null) {
                writer.writeText(quill.getValue().toString(), null);
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
            ResponseWriter writer = context.getResponseWriter();
            writer.endElement("div");
            writer.startElement("script", null);
            writer.writeURIAttribute("src", "https://cdn.quilljs.com/1.3.6/quill.js", null);
            writer.endElement("script");
            writer.startElement("script", component);
            StringBuilder script = new StringBuilder();
            script.append("var quill_").append(getScriptSafeClientId(context, component)).
                    append(" = new Quill('#").append(getScriptSafeClientId(context, component)).
                    append("', { theme: 'snow' });");
            writer.writeText(script.toString(), null);
            writer.endElement("script");
        }
    }
    
    /**
     * Get a script-safe client id.
     * 
     * @param context the Faces context.
     * @param component the UI component.
     * @return the script-safe client id.
     */
    public String getScriptSafeClientId(FacesContext context, UIComponent component) {
        String result = component.getClientId(context);
        return result.replaceAll(":", "__");
    }
}
