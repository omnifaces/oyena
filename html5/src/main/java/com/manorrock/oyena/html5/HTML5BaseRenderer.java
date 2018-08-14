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
import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.component.behavior.ClientBehaviorHolder;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;

/**
 * Our HTML5 base renderer.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
public abstract class HTML5BaseRenderer extends Renderer {

    /**
     * Encode the attribute.
     *
     * @param context the Faces context.
     * @param component the component.
     * @param name the name.
     * @param encodedName the encoded name.
     * @throws IOException when an I/O error occurs.
     */
    protected void encodeAttribute(FacesContext context, UIComponent component, String name, String encodedName) throws IOException {
        ResponseWriter responseWriter = context.getResponseWriter();
        Map<String, Object> attributes = component.getAttributes();
        String value = (String) attributes.get(name);
        if (value != null) {
            responseWriter.writeAttribute(encodedName, value, null);
        }
    }

    /**
     * Encode the id.
     *
     * @param context the Faces context.
     * @param component the component.
     * @throws IOException when an I/O error occurs.
     */
    protected void encodeId(FacesContext context, UIComponent component) throws IOException {
        boolean renderId = false;
        String id = component.getId();
        if (id != null && !id.startsWith(UIViewRoot.UNIQUE_ID_PREFIX)) {
            renderId = true;
        }
        if (!renderId && component instanceof ClientBehaviorHolder) {
            ClientBehaviorHolder cbh = (ClientBehaviorHolder) component;
            if (!cbh.getClientBehaviors().isEmpty()) {
                renderId = true;
            }
        }
        if (renderId) {
            ResponseWriter responseWriter = context.getResponseWriter();
            responseWriter.writeAttribute("id", component.getClientId(context), null);
        }
    }
}
