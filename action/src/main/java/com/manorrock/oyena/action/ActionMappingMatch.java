/*
 * Copyright (c) 2002-2018 Manorrock.com. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without 
 * modification, are permitted provided that the following conditions are met:
 *
 *   1. Redistributions of source code must retain the above copyright notice, 
 *      this list of conditions and the following disclaimer.
 *   2. Redistributions in binary form must reproduce the above copyright notice,
 *      this list of conditions and the following disclaimer in the documentation
 *      and/or other materials provided with the distribution.
 *   3. Neither the name of the copyright holder nor the names of its 
 *      contributors may be used to endorse or promote products derived from this
 *      software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.manorrock.oyena.action;

import java.lang.reflect.Method;
import javax.enterprise.inject.spi.Bean;

/**
 * The action mapping match class.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 * @status Beta
 */
public class ActionMappingMatch {

    /**
     * Stores the mapping type.
     */
    private MappingType mappingType;

    /**
     * Stores the bean.
     */
    private Bean<?> bean;
    
    /**
     * Stores the method.
     */
    private Method method;
    
    /**
     * Stores the request mapping.
     */
    private String requestMapping;

    /**
     * Get the bean.
     *
     * @return the bean.
     */
    public Bean<?> getBean() {
        return bean;
    }

    /**
     * Get the length.
     *
     * @return the length.
     */
    public int getLength() {
        return requestMapping.length();
    }

    /**
     * Get the mapping type.
     *
     * @return the mapping type.
     */
    public MappingType getMappingType() {
        return mappingType;
    }

    /**
     * Get the method.
     *
     * @return the method.
     */
    public Method getMethod() {
        return method;
    }

    /**
     * Set the mapping type.
     *
     * @param mappingType the mapping type.
     */
    public void setMappingType(MappingType mappingType) {
        this.mappingType = mappingType;
    }

    /**
     * Set the bean.
     *
     * @param bean the bean.
     */
    public void setBean(Bean<?> bean) {
        this.bean = bean;
    }

    /**
     * Set the method.
     *
     * @param method the method.
     */
    public void setMethod(Method method) {
        this.method = method;
    }

    /**
     * Set the request mapping.
     *
     * @param requestMapping the request mapping.
     */
    public void setRequestMapping(String requestMapping) {
        this.requestMapping = requestMapping;
    }

    /**
     * Mapping type enum.
     */
    public enum MappingType {
        /*
         * An exact match.
         */
        EXACT,
        /*
         * A prefix match.
         */
        PREFIX,
        /*
         * An extension match.
         */
        EXTENSION
    }
}
