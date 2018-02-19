/*
 * Copyright (c) 2002-2018 Manorrock.com. All Rights Reserved.
 */
package com.manorrock.oyena;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;

/**
 * Activator for JSF 2.3 and beyond.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@ApplicationScoped
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
public class Jsf23Activator {
    /* 
     * FIXME - Note this class should not be necessary. An issue should be filed 
     *         to make JSF 2.3 mode the default mode of operation.
     */
}
