/*
 * Copyright (c) 2002-2018 Manorrock.com. All Rights Reserved.
 */
package com.manorrock.oyena.action;

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
 * @status Experimental
 */
public class ActionLifecycle extends Lifecycle {

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
        if (facesContext.getViewRoot() == null) {
            UIViewRoot viewRoot = new UIViewRoot();
            viewRoot.setRenderKitId("HTML_BASIC");
            viewRoot.setViewId("/index.xhtml");
            facesContext.setViewRoot(viewRoot);
        }
    }

    /**
     * Get the default lifecycle.
     * 
     * <p>
     *  FIXME - This method lazily gets the default lifecycle as FactoryFinder is
     *  not properly re-entrant. We should be able to initialize the 
     *  defaultLifecycle variable in the constructor of this class. An issue
     *  should be filed for this.
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
