/*
 * Copyright (c) 2002-2018 Manorrock.com. All Rights Reserved.
 */
package com.manorrock.oyena.action.test.webapp;

import com.manorrock.oyena.action.ActionMapping;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;

/**
 * The index page bean.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@RequestScoped
public class IndexPageBean implements Serializable {
 
    /**
     * Execute the page.
     * 
     * @return /index.xhtml
     */
    @ActionMapping("/index")
    public String execute() {
        return "/index.xhtml";
    }
}
