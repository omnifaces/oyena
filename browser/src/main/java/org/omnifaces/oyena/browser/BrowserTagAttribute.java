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
package org.omnifaces.oyena.browser;

import javax.el.MethodExpression;
import javax.el.ValueExpression;
import javax.faces.view.Location;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;

/**
 * An implementation of TagAttribute.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
public class BrowserTagAttribute extends TagAttribute {

    /**
     * Stores the local; name.
     */
    private String localName;
    
    /**
     * Constructor.
     * 
     * @param localName the local name.
     * @param value the value.
     */
    public BrowserTagAttribute(String localName, Object value) {
        this.localName = localName;
    }

    @Override
    public boolean getBoolean(FaceletContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getInt(FaceletContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Get the local name.
     * 
     * @return the local name.
     */
    @Override
    public String getLocalName() {
        return localName;
    }

    @Override
    public Location getLocation() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public MethodExpression getMethodExpression(FaceletContext ctx, Class type, Class[] paramTypes) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getNamespace() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object getObject(FaceletContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getQName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getValue() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getValue(FaceletContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object getObject(FaceletContext ctx, Class type) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ValueExpression getValueExpression(FaceletContext ctx, Class type) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isLiteral() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
