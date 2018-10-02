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
package com.manorrock.oyena.borderpane;

import java.io.IOException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;
import javax.faces.render.Renderer;

/**
 * The BorderPane renderer.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
@FacesRenderer(
        componentFamily = "com.manorrock.oyena.borderpane",
        rendererType = "com.manorrock.oyena.borderpane.BorderPaneRenderer")
@Deprecated
public class BorderPaneRenderer extends Renderer {

    /**
     * Encode the begin.
     *
     * @param context the Faces context.
     * @param component the UI component.
     * @throws IOException when an I/O error occurs.
     */
    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        writer.startElement("oyena-border-pane", component);
        writer.startElement("top", null);
        UIComponent top = component.getFacet("top");
        if (top != null) {
            top.encodeAll(context);
        }
        writer.endElement("top");
        writer.startElement("bottom", null);
        UIComponent bottom = component.getFacet("bottom");
        if (bottom != null) {
            bottom.encodeAll(context);
        }
        writer.endElement("bottom");
        writer.startElement("left", null);
        UIComponent left = component.getFacet("left");
        if (left != null) {
            left.encodeAll(context);
        }
        writer.endElement("left");
        writer.startElement("right", null);
        UIComponent right = component.getFacet("right");
        if (right != null) {
            right.encodeAll(context);
        }
        writer.endElement("right");
        writer.startElement("center", null);
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
        ResponseWriter writer = context.getResponseWriter();
        writer.endElement("center");
        writer.endElement("oyena-border-pane");
    }

    /**
     * Do we render our children?
     * 
     * @return false.
     */
    @Override
    public boolean getRendersChildren() {
        return false;
    }
}
