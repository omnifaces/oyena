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
package com.manorrock.oyena.cdi;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import javax.enterprise.inject.literal.NamedLiteral;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.CDI;
import javax.faces.context.FacesContext;
import javax.faces.render.RenderKit;
import javax.faces.render.RenderKitFactory;
import javax.faces.render.RenderKitWrapper;
import javax.inject.Named;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * The CDI render-kit factory.
 *
 * @author Mnfred Riem (mriem@manorrock.com)
 */
public class CdiRenderKitFactory extends RenderKitFactory {

    /**
     * Stores the bean manager.
     */
    public BeanManager beanManager;
    
    /**
     * Constructor.
     */
    public CdiRenderKitFactory() {
    }

    /**
     * Constructor.
     *
     * @param wrapped the wrapped render-kit factory.
     */
    public CdiRenderKitFactory(RenderKitFactory wrapped) {
        super(wrapped);
        try {
            InitialContext initialContext = new InitialContext();
            beanManager = (BeanManager) initialContext.lookup("java:comp/BeanManager");
        } catch (NamingException ne) {
        }
    }

    /**
     * Add the render kit.
     *
     * @param renderKitId the render-kit id.
     * @param renderKit the render-kit.
     */
    @Override
    public void addRenderKit(String renderKitId, RenderKit renderKit) {
        // because we are using CDI to manage our render-kits this is a no-op.
    }

    /**
     * Get the render kit.
     *
     * @param facesContext the Faces context.
     * @param renderKitId the render kit id.
     * @return the render kit.
     */
    @Override
    public RenderKit getRenderKit(FacesContext facesContext, String renderKitId) {
        RenderKit result = null;
        if (renderKitId.equals(RenderKitFactory.HTML_BASIC_RENDER_KIT)) {
            result = getWrapped().getRenderKit(facesContext, renderKitId);
        } else {
            AnnotatedType<RenderKit> type = beanManager.createAnnotatedType(RenderKit.class);
            Set<Bean<?>> beans = beanManager.getBeans(type.getBaseType(), NamedLiteral.of(renderKitId));
            Iterator<Bean<?>> iterator = beans.iterator();
            while (iterator.hasNext()) {
                Bean<?> bean = iterator.next();
                Named named = bean.getBeanClass().getAnnotation(Named.class);
                if (named.value().equals(renderKitId)) {
                    result = (RenderKit) CDI.current().select(named).get();
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Get the render kit ids.
     *
     * @return the render kit ids.
     */
    @Override
    public Iterator<String> getRenderKitIds() {
        ArrayList<String> renderKitIds = new ArrayList<>();
        getWrapped().getRenderKitIds().forEachRemaining(renderKitIds::add);
        AnnotatedType<RenderKit> type = beanManager.createAnnotatedType(RenderKit.class);
        Set<Bean<?>> beans = beanManager.getBeans(type.getBaseType());
        Iterator<Bean<?>> iterator = beans.iterator();
        while (iterator.hasNext()) {
            Bean<?> bean = iterator.next();
            if (bean.getBeanClass().isAnnotationPresent(Named.class)) {
                Named named = bean.getBeanClass().getAnnotation(Named.class);
                renderKitIds.add(named.value());
            }
        }
        return renderKitIds.iterator();
    }
}
