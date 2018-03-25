/*
 * Copyright (c) 2002-2018 Manorrock.com. All Rights Reserved.
 */
package com.manorrock.oyena.rest;

import java.lang.annotation.Documented;
import static java.lang.annotation.ElementType.METHOD;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;

/**
 * The REST regular expression mapping.
 * 
 * <p>
 *  This mapping type uses the same syntax as the regular expression API in Java.
 * </p>
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Documented
@Target(value = {METHOD})
@Retention(value = RUNTIME)
public @interface RegexPath {
    
    /**
     * Get the regular expression.
     * 
     * @return the regular expression.
     */
    String value() default "(.*)";
}
