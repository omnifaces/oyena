/*
 * Copyright (c) 2002-2018 Manorrock.com. All Rights Reserved.
 */
package com.manorrock.oyena.component.html;

import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIPanel;
import javax.faces.context.FacesContext;

/**
 * The HTML component for h:panelGrid.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
@FacesComponent(
        createTag = true,
        namespace = "http://xmlns.jcp.org/jsf/html",
        tagName = "panelGrid",
        value = "javax.faces.HtmlPanelGrid")
public class HtmlPanelGrid extends UIPanel {

    /**
     * Stores the component type.
     */
    public static final String COMPONENT_TYPE = "javax.faces.HtmlPanelGrid";

    /**
     * Constructor.
     */
    public HtmlPanelGrid() {
        setRendererType("javax.faces.Grid");
    }
}
