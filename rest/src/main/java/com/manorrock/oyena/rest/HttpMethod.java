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
 * The REST HTTP method mapping.
 * 
 * <p>
 *  This mapping type is used to limit the REST mapping to a specific HTTP method
 *  (eg. GET / POST / HEAD / DELETE).
 * </p>
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Documented
@Target(value = {METHOD})
@Retention(value = RUNTIME)
public @interface HttpMethod {
    
    /**
     * Get the HTTP method.
     * 
     * @return the HTTP method.
     */
    String value() default "GET";
}
