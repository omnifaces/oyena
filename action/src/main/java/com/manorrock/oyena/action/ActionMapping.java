/*
 * Copyright (c) 2002-2018 Manorrock.com. All Rights Reserved.
 */
package com.manorrock.oyena.action;

import java.lang.annotation.Documented;
import static java.lang.annotation.ElementType.METHOD;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;

/**
 * The action mapping.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Documented
@Target(value = {METHOD})
@Retention(value = RUNTIME)
public @interface ActionMapping {
    
    /**
     * Get the mapping (syntax is the same as url-pattern in web.xml)
     * ˆ
     * @return the mapping.
     */
    String value() default "/*";
}
