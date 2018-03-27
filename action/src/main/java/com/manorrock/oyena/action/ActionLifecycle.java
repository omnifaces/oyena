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
package com.manorrock.oyena.action;

import java.util.Iterator;
import java.util.Set;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.spi.AnnotatedMethod;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.CDI;
import javax.enterprise.util.AnnotationLiteral;
import javax.faces.FacesException;
import javax.faces.FactoryFinder;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseListener;
import javax.faces.lifecycle.Lifecycle;
import javax.faces.lifecycle.LifecycleFactory;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * The Action lifecycle.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 * @status Beta
 */
public class ActionLifecycle extends Lifecycle {

    /**
     * Stores the bean manager.
     */
    private BeanManager beanManager;

    /**
     * Stores the default lifecycle.
     */
    private Lifecycle defaultLifecycle;

    /**
     * Constructor.
     */
    public ActionLifecycle() {
    }

    /**
     * Add a phase listener.
     *
     * <p>
     * This is ignored by the Action lifecycle.
     * </p>
     *
     * @param phaseListener the phase listener.
     */
    @Override
    public void addPhaseListener(PhaseListener phaseListener) {
    }

    /**
     * Find the action mapping for the given bean.
     *
     * @param facesContext the Faces context.
     * @param bean the bean.
     * @return the action mapping match, or null if not found.
     */
    private ActionMappingMatch determineActionMappingMatch(FacesContext facesContext, Bean<?> bean) {
        ActionMappingMatch result = null;
        Class clazz = bean.getBeanClass();
        AnnotatedType annotatedType = beanManager.createAnnotatedType(clazz);
        Set<AnnotatedMethod> annotatedMethodSet = annotatedType.getMethods();
        for (AnnotatedMethod method : annotatedMethodSet) {
            if (method.isAnnotationPresent(ActionMapping.class)) {
                ActionMapping requestMapping = method.getAnnotation(ActionMapping.class);
                String mapping = requestMapping.value();
                String pathInfo = facesContext.getExternalContext().getRequestPathInfo();
                if (pathInfo.equals(mapping)) {
                    result = new ActionMappingMatch();
                    result.setBean(bean);
                    result.setMethod(method.getJavaMember());
                    result.setRequestMapping(mapping);
                    result.setMappingType(ActionMappingMatch.MappingType.EXACT);
                    break;
                } else if (mapping.endsWith("*")) {
                    mapping = mapping.substring(0, mapping.length() - 1);
                    if (pathInfo.startsWith(mapping)) {
                        if (result == null) {
                            result = new ActionMappingMatch();
                            result.setBean(bean);
                            result.setMethod(method.getJavaMember());
                            result.setRequestMapping(mapping);
                            result.setMappingType(ActionMappingMatch.MappingType.PREFIX);
                        } else if (mapping.length() > result.getLength()) {
                            result.setBean(bean);
                            result.setMethod(method.getJavaMember());
                            result.setRequestMapping(mapping);
                            result.setMappingType(ActionMappingMatch.MappingType.PREFIX);
                        }
                    }
                } else if (mapping.startsWith("*")) {
                    mapping = mapping.substring(1);
                    if (pathInfo.endsWith(mapping)) {
                        result = new ActionMappingMatch();
                        result.setBean(bean);
                        result.setMethod(method.getJavaMember());
                        result.setRequestMapping(mapping);
                        result.setMappingType(ActionMappingMatch.MappingType.EXTENSION);
                        break;
                    }
                }
            }
            if (result != null
                    && (result.getMappingType().equals(ActionMappingMatch.MappingType.EXACT)
                    || (result.getMappingType().equals(ActionMappingMatch.MappingType.EXTENSION)))) {
                break;
            }
        }
        return result;
    }

    /**
     * Perform the execute part of Action lifecycle.
     *
     * @param facesContext the Faces context.
     * @throws FacesException when a serious error occurs.
     */
    @Override
    public void execute(FacesContext facesContext) throws FacesException {
        Iterator<Bean<?>> beans = getBeans();
        ActionMappingMatch match = null;
        while (beans.hasNext()) {
            Bean<?> bean = beans.next();
            ActionMappingMatch candidate = determineActionMappingMatch(facesContext, bean);
            if (match == null) {
                match = candidate;
            } else if (candidate != null && candidate.getLength() > match.getLength()) {
                match = candidate;
            }
        }
        if (match != null) {
            Instance instance = CDI.current().select(
                    match.getBean().getBeanClass(), new AnnotationLiteral<Any>() {
            });
            String viewId;
            try {
                viewId = (String) match.getMethod().invoke(instance.get(), new Object[0]);
            } catch (Throwable throwable) {
                throw new FacesException(throwable);
            }
            if (facesContext.getViewRoot() == null) {
                UIViewRoot viewRoot = new UIViewRoot();
                viewRoot.setRenderKitId("HTML_BASIC");
                viewRoot.setViewId(viewId);
                facesContext.setViewRoot(viewRoot);
            }
        }
    }

    /**
     * Get the beans.
     *
     * @return the beans.
     */
    private Iterator<Bean<?>> getBeans() {
        Set<Bean<?>> beans = getBeanManager().getBeans(
                Object.class, new AnnotationLiteral<Any>() {
        });
        return beans.iterator();
    }

    /**
     * Get the BeanManager.
     *
     * @return the bean manager.
     */
    private BeanManager getBeanManager() {
        if (beanManager == null) {
            Object result = null;
            try {
                InitialContext initialContext = new InitialContext();
                result = initialContext.lookup("java:comp/BeanManager");
            } catch (NamingException exception) {
                try {
                    InitialContext initialContext = new InitialContext();
                    result = initialContext.lookup("java:comp/env/BeanManager");
                } catch (NamingException exception2) {
                }
            }
            if (result != null) {
                beanManager = (BeanManager) result;
            } else {
                beanManager = null;
            }
        }
        return beanManager;
    }

    /**
     * Get the default lifecycle.
     *
     * <p>
     * FIXME - This method lazily gets the default lifecycle as FactoryFinder is
     * not properly re-entrant. We should be able to initialize the
     * defaultLifecycle variable in the constructor of this class. An issue
     * should be filed for this.
     * </p>
     *
     * @return the default lifecycle.
     */
    private synchronized Lifecycle getDefaultLifecycle() {
        if (defaultLifecycle == null) {
            LifecycleFactory lifecycleFactory = (LifecycleFactory) FactoryFinder.getFactory(FactoryFinder.LIFECYCLE_FACTORY);
            defaultLifecycle = lifecycleFactory.getLifecycle(LifecycleFactory.DEFAULT_LIFECYCLE);
        }
        return defaultLifecycle;
    }

    /**
     * Get the phase listeners.
     *
     * <p>
     * As phase listeners are ignored by the Action lifecycle this will always
     * return a zero length array.
     * </p>
     *
     * @return
     */
    @Override
    public PhaseListener[] getPhaseListeners() {
        return new PhaseListener[0];
    }

    /**
     * Remove a phase listener.
     *
     * <p>
     * This is ignored by the Action lifecycle.
     * </p>
     *
     * @param phaseListener the phase listener.
     */
    @Override
    public void removePhaseListener(PhaseListener phaseListener) {
    }

    /**
     * Perform the render part of the Action lifecycle.
     *
     * @param facesContext the Faces context.
     * @throws FacesException when a serious error occurs.
     */
    @Override
    public void render(FacesContext facesContext) throws FacesException {
        getDefaultLifecycle().render(facesContext);
    }
}
