/*
 * Copyright (c) 2002-2018 Manorrock.com. All Rights Reserved.
 */
package com.manorrock.oyena.action;

import java.util.ArrayList;
import java.util.Iterator;
import javax.faces.lifecycle.Lifecycle;
import javax.faces.lifecycle.LifecycleFactory;

/**
 * The Action lifecycle factory.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 * @status Experimental
 */
public class ActionLifecycleFactory extends LifecycleFactory {

    /**
     * Stores the action lifecycle.
     */
    private final ActionLifecycle actionLifecycle;
    
    /**
     * Constructor.
     *
     * @param wrapped the wrapped lifecycle factory.
     */
    public ActionLifecycleFactory(LifecycleFactory wrapped) {
        super(wrapped);
        actionLifecycle = new ActionLifecycle();
    }

    /**
     * Add the lifecycle.
     *
     * @param lifecycleId the lifecycle id.
     * @param lifecycle the lifecycle.
     */
    @Override
    public void addLifecycle(String lifecycleId, Lifecycle lifecycle) {
        getWrapped().addLifecycle(lifecycleId, lifecycle);
    }
    
    /**
     * Get the lifecycle.
     *
     * @param lifecycleId the lifecycle id.
     * @return the lifecycle.
     */
    @Override
    public Lifecycle getLifecycle(String lifecycleId) {
        Lifecycle result;
        if (lifecycleId.equals(ActionLifecycle.class.getName())) {
            result = actionLifecycle;
        } else {
            result = getWrapped().getLifecycle(lifecycleId);
        }
        return result;
    }

    /**
     * Get the lifecycle ids.
     *
     * @return the lifecycle ids.
     */
    @Override
    public Iterator<String> getLifecycleIds() {
        ArrayList<String> lifecycleIds = new ArrayList<>();
        getWrapped().getLifecycleIds().forEachRemaining(lifecycleIds::add);
        lifecycleIds.add(ActionLifecycle.class.getName());
        return lifecycleIds.iterator();
    }
}
