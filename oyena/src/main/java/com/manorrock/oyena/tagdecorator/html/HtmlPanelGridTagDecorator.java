/*
 * Copyright (c) 2002-2018 Manorrock.com. All Rights Reserved.
 */
package com.manorrock.oyena.tagdecorator.html;

import javax.faces.view.facelets.Tag;
import javax.faces.view.facelets.TagDecorator;

/**
 * The tag decorator that changes h:panelGrid to o:panelGrid.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
public class HtmlPanelGridTagDecorator implements TagDecorator {

    /**
     * Decorate the tag.
     * 
     * @param tag the tag to decorate.
     * @return the tag.
     */
    @Override
    public Tag decorate(Tag tag) {
        Tag result = null;
        if ("panelGrid".equals(tag.getLocalName()) && 
                "http://xmlns.jcp.org/jsf/html".equals(tag.getNamespace())) {
            result = new Tag(tag.getLocation(), "http://www.manorrock.com/oyena/html", 
                    tag.getLocalName(), tag.getQName(), tag.getAttributes());
        }
        return result;
    }
}
