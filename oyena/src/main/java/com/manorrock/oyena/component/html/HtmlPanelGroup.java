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

    /**
     * Get the layout.
     *
     * @return the layout.
     */
    public String getLayout() {
        /*
         * FIXME - the FacesContext should be injectable. An issue should be filed for this.
         */
        FacesContext context = FacesContext.getCurrentInstance();
        return (String) getValueExpression("layout").getValue(context.getELContext());
    }

    /**
     * Get the style.
     *
     * @return the style.
     */
    public String getStyle() {
        /*
         * FIXME - the FacesContext should be injectable. An issue should be filed for this.
         */
        FacesContext context = FacesContext.getCurrentInstance();
        return (String) getValueExpression("style").getValue(context.getELContext());
    }

    /**
     * Get the style class.
     *
     * @return the style class.
     */
    public String getStyleClass() {
        /*
         * FIXME - the FacesContext should be injectable. An issue should be filed for this.
         */
        FacesContext context = FacesContext.getCurrentInstance();
        return (String) getValueExpression("styleClass").getValue(context.getELContext());
    }

    /**
     * Set the layout.
     *
     * @param layout the layout.
     */
    public void setLayout(String layout) {
        /*
         * FIXME - the FacesContext should be injectable. An issue should be filed for this.
         */
        FacesContext context = FacesContext.getCurrentInstance();
        ExpressionFactory ef = context.getApplication().getExpressionFactory();
        ValueExpression ve = ef.createValueExpression(layout, String.class);
        setValueExpression("layout", ve);
    }

    /**
     * Set the style.
     *
     * @param style the style.
     */
    public void setStyle(String style) {
        /*
         * FIXME - the FacesContext should be injectable. An issue should be filed for this.
         */
        FacesContext context = FacesContext.getCurrentInstance();
        ExpressionFactory ef = context.getApplication().getExpressionFactory();
        ValueExpression ve = ef.createValueExpression(style, String.class);
        setValueExpression("style", ve);
    }

    /**
     * Set the style class.
     *
     * @param styleClass the style class.
     */
    public void setStyleClass(String styleClass) {
        /*
         * FIXME - the FacesContext should be injectable. An issue should be filed for this.
         */
        FacesContext context = FacesContext.getCurrentInstance();
        ExpressionFactory ef = context.getApplication().getExpressionFactory();
        ValueExpression ve = ef.createValueExpression(styleClass, String.class);
        setValueExpression("styleClass", ve);
    }
}
