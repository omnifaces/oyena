/*
 * Copyright (c) 2002-2018 Manorrock.com. All Rights Reserved.
 */
package com.manorrock.oyena.rest;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.FacesException;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseListener;
import javax.faces.lifecycle.Lifecycle;
import javax.inject.Named;

/**
 * The REST lifecycle.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
@ApplicationScoped
@Named("com.manorrock.oyena.rest.RestLifecycle")
public class RestLifecycle extends Lifecycle {

    /**
     * Add a phase listener.
     *
     * <p>
     * This is ignored by the REST lifecycle.
     * </p>
     *
     * @param phaseListener the phase listener.
     */
    @Override
    public void addPhaseListener(PhaseListener phaseListener) {
    }

    /**
     * Perform the execute part of lifecycle.
     *
     * @param facesContext the Faces context.
     * @throws FacesException when a serious error occurs.
     */
    @Override
    public void execute(FacesContext facesContext) throws FacesException {
    }

    /**
     * Get the phase listeners.
     *
     * <p>
     * As phase listeners are ignored by the REST lifecycle this will always
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
     * This is ignored by the REST lifecycle.
     * </p>
     *
     * @param phaseListener the phase listener.
     */
    @Override
    public void removePhaseListener(PhaseListener phaseListener) {
    }

    /**
     * Perform the render part of the REST lifecycle.
     *
     * @param facesContext the Faces context.
     * @throws FacesException when a serious error occurs.
     */
    @Override
    public void render(FacesContext facesContext) throws FacesException {
    }
}
