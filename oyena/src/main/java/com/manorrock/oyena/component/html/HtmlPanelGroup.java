/*
 * Copyright (c) 2002-2018 Manorrock.com. All Rights Reserved.
 */
package com.manorrock.oyena.component.html;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIPanel;

/**
 * The HTML component for h:panelGroup.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@FacesComponent(
        createTag = true, 
        namespace = "http://xmlns.jcp.org/jsf/html",
        tagName = "panelGroup",
        value = "javax.faces.HtmlPanelGroup")
public class HtmlPanelGroup extends UIPanel {

    /*
     * FIXME - the UI component should be able to be @RequestScoped. An issue 
     *         should be filed for this.
     */
 
    /**
     * Stores the component type.
     */
    public static final String COMPONENT_TYPE = "javax.faces.HtmlPanelGroup";

    /**
     * Constructor.
     */
    public HtmlPanelGroup() {
        setRendererType("javax.faces.Group");
    }
}
