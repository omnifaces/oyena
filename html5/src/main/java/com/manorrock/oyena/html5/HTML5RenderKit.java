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

import java.io.OutputStream;
import java.io.Writer;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.FactoryFinder;
import static javax.faces.FactoryFinder.RENDER_KIT_FACTORY;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseStream;
import javax.faces.context.ResponseWriter;
import javax.faces.render.RenderKit;
import javax.faces.render.RenderKitFactory;
import javax.faces.render.Renderer;
import javax.faces.render.ResponseStateManager;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * The HTML5 render-kit.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@ApplicationScoped
@Named("HTML5")
public class HTML5RenderKit extends RenderKit {
    
    /**
     * Stores the Faces context.
     */
    @Inject
    private FacesContext facesContext;
    
    /**
     * Stores the HTML_BASIC_RENDER_KIT render-kit.
     */
    private RenderKit wrapped;

    /**
     * Constructor.
     */
    public HTML5RenderKit() {
    }
    
    /**
     * Add a renderer.
     * 
     * @param family the family.
     * @param rendererType the renderer type.
     * @param renderer the renderer. 
     */
    @Override
    public void addRenderer(String family, String rendererType, Renderer renderer) {
        getWrapped().addRenderer(family, family, renderer);
    }

    /**
     * Get the renderer.
     * 
     * @param family the family.
     * @param rendererType the renderer type.
     * @return the renderer.
     */
    @Override
    public Renderer getRenderer(String family, String rendererType) {
        return getWrapped().getRenderer(family, rendererType);
    }

    /**
     * Get the response state manager.
     * 
     * @return the response state manager.
     */
    @Override
    public ResponseStateManager getResponseStateManager() {
        return getWrapped().getResponseStateManager();
    }

    /**
     * Create the response writer.
     * 
     * @param writer the response writer.
     * @param contentTypeList the content-type list.
     * @param characterEncoding the character encoding.
     * @return the response writer.
     */
    @Override
    public ResponseWriter createResponseWriter(Writer writer, String contentTypeList, String characterEncoding) {
        ResponseWriter result;
        if (contentTypeList == null || contentTypeList.contains("text/html")) {
            result = new HTML5ResponseWriter(writer, contentTypeList, characterEncoding);
        } else {
            result = getWrapped().createResponseWriter(writer, contentTypeList, characterEncoding);
        }
        return result;
    }

    /**
     * Create the response stream.
     * 
     * @param outputStream the output stream.
     * @return the response stream.
     */
    @Override
    public ResponseStream createResponseStream(OutputStream outputStream) {
        return getWrapped().createResponseStream(outputStream);
    }

    /**
     * Get the wrapped BASIC_HTML render-kit.
     * 
     * @return the wrapped BASIC_HTML render-kit.
     */
    private RenderKit getWrapped() {
        if (wrapped == null) {
            RenderKitFactory factory = (RenderKitFactory) FactoryFinder.getFactory(RENDER_KIT_FACTORY);
            wrapped = factory.getRenderKit(facesContext, RenderKitFactory.HTML_BASIC_RENDER_KIT);
        }
        return wrapped;
    }
}
