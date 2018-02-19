/*
 * Copyright (c) 2002-2018 Manorrock.com. All Rights Reserved.
 */
package com.manorrock.oyena;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;

/**
 * Activator for JSF 2.3 and beyond.
 * 
 * <p>
 *  Note this should not be necessary. An issue should be filed to make it the
 *  default mode of operation.
 * </p>
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@ApplicationScoped
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
public class Jsf23Activator {
}
