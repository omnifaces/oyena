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

import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.spi.CDI;
import javax.faces.FacesException;
import javax.faces.FactoryFinder;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseListener;
import javax.faces.lifecycle.Lifecycle;
import javax.faces.lifecycle.LifecycleFactory;

/**
 * The Action lifecycle.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 * @status Beta
 */
public class ActionLifecycle extends Lifecycle {

    /**
     * Stores the action mapping matcher.
     */
    private ActionMappingMatcher actionMappingMatcher;

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
     * Perform the execute part of Action lifecycle.
     *
     * @param facesContext the Faces context.
     * @throws FacesException when a serious error occurs.
     */
    @Override
    public void execute(FacesContext facesContext) throws FacesException {
        ActionMappingMatch match = getActionMappingMatcher().match(facesContext);
        if (match != null) {
            Instance instance = CDI.current().select(
                    match.getBean().getBeanClass(), Any.Literal.INSTANCE);
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
     * Get the action mapping matcher.
     *
     * @return the action mapping matcher.
     */
    private synchronized ActionMappingMatcher getActionMappingMatcher() {
        if (actionMappingMatcher == null) {
            actionMappingMatcher = CDI.current().select(
                    ActionMappingMatcher.class, Any.Literal.INSTANCE).get();
        }
        return actionMappingMatcher;
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
     * @return the empty array of phase listeners.
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
