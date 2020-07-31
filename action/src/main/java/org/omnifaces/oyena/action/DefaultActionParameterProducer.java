/*
 * Copyright (c) 2002-2020 OmniFaces. All Rights Reserved.
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
package org.omnifaces.oyena.action;

import java.lang.annotation.Annotation;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.spi.CDI;
import javax.faces.FacesException;

/**
 * The default action parameter producer.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
@ApplicationScoped
public class DefaultActionParameterProducer implements ActionParameterProducer {

    /**
     * Produce an instance for the given type.
     *
     * @param actionMappingMatch the Action mapping match.
     * @param parameterType the parameter type.
     * @param parameterAnnotations the parameter annotations.
     * @return the instance.
     */
    @Override
    public Object produce(ActionMappingMatch actionMappingMatch, Class<?> parameterType,
            Annotation[] parameterAnnotations) {
        
        ActionPathParameter path = getActionPathParameterAnnotation(parameterAnnotations);
        if (path != null) {
            Pattern pattern = Pattern.compile(actionMappingMatch.getActionMapping());
            Matcher matcher = pattern.matcher(actionMappingMatch.getPathInfo());
            if (matcher.matches()) {
                return matcher.group(path.value());
            } else {
                throw new FacesException("Unable to match @RestPathParameter: " + path.value());
            }
        }

        return CDI.current().select(parameterType, Any.Literal.INSTANCE).get();
    }
    
    /**
     * Get the @ActionPathParameter annotation (if present).
     *
     * @return the @RestPathParameter annotation, or null if not present.
     */
    private ActionPathParameter getActionPathParameterAnnotation(Annotation[] annotations) {
        ActionPathParameter result = null;
        if (annotations != null && annotations.length > 0) {
            for (Annotation annotation : annotations) {
                if (annotation instanceof ActionPathParameter) {
                    result = (ActionPathParameter) annotation;
                    break;
                }
            }
        }
        return result;
    }
}
