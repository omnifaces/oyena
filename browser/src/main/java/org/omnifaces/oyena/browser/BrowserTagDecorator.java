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

import javax.faces.view.facelets.Tag;
import javax.faces.view.facelets.TagDecorator;

/**
 * The tag decorator that allows for standard HTML to be recognized as JSF tags.
 *
 * <p>
 * Specifically this tag decorator will look for an attribute named
 * 'data-faces-class-name'. If it finds it, it will decorate the tag so the
 * BrowserTagHandler can appropriately react on it.
 * </p>
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
public class BrowserTagDecorator implements TagDecorator {

    /**
     * Decorate the tag.
     *
     * @param tag the tag.
     * @return the decorated tag, or null if we ignore it.
     */
    @Override
    public Tag decorate(Tag tag) {
        Tag decoratedTag = null;
        if (tag != null && tag.getAttributes().get("data-faces-class-name") != null) {
            BrowserTagAttributes attributes = new BrowserTagAttributes();
            attributes.addTagAttributes(tag.getAttributes());
            decoratedTag = new Tag(tag.getLocation(), 
                    "http://omnifaces.org/faces/browser", "browser", 
                    "browser", attributes);
        }
        return decoratedTag;
    }
}
