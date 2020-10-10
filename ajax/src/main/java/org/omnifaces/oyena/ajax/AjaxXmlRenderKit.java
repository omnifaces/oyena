/*
 * Copyright (c) 2002-2020 OmniFaces. All Rights Reserved.
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
package org.omnifaces.oyena.ajax;

import java.io.OutputStream;
import java.io.Writer;
import javax.faces.context.ResponseStream;
import javax.faces.context.ResponseWriter;
import javax.faces.render.RenderKit;
import javax.faces.render.Renderer;
import javax.faces.render.ResponseStateManager;

/**
 * The AJAX XML RenderKit.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
public class AjaxXmlRenderKit extends RenderKit {

    @Override
    public void addRenderer(String family, String rendererType, Renderer renderer) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ResponseStream createResponseStream(OutputStream out) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ResponseWriter createResponseWriter(Writer writer, 
            String contentTypeList, String characterEncoding) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Renderer getRenderer(String family, String rendererType) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ResponseStateManager getResponseStateManager() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
