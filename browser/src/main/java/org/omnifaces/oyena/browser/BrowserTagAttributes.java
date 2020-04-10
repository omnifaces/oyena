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

import java.util.ArrayList;
import java.util.Arrays;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagAttributes;

/**
 * An implementation of TagAttributes.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
public class BrowserTagAttributes extends TagAttributes {

    /**
     * Stores the attributes.
     */
    private final ArrayList<TagAttribute> attributes = new ArrayList<>();

    /**
     * Constructor.
     */
    public BrowserTagAttributes() {
    }

    /**
     * Add the tag attribute.
     *
     * @param attribute the tag attribute.
     */
    public void addTagAttribute(TagAttribute attribute) {
        this.attributes.add(attribute);
    }

    /**
     * Add the tag attributes.
     *
     * @param attributes the tag attributes.
     */
    public void addTagAttributes(TagAttributes attributes) {
        this.attributes.addAll(Arrays.asList(attributes.getAll()));
    }

    /**
     * Get all the tag attributes.
     *
     * @return the tag attributes.
     */
    @Override
    public TagAttribute[] getAll() {
        return attributes.toArray(new TagAttribute[0]);
    }

    /**
     * Get the tag attribute with the specified local name.
     *
     * @param localName the local name.
     * @return the tag attribute, or null otherwise.
     */
    @Override
    public TagAttribute get(String localName) {
        TagAttribute attribute = null;
        for (TagAttribute current : attributes) {
            if (current.getLocalName().equals(localName)) {
                attribute = current;
                break;
            }
        }
        return attribute;
    }

    /**
     * Get the tag attribute with the specified namespace and local name.
     *
     * @param namespace the namespace.
     * @param localName the local name.
     * @return the tag attribute, or null otherwise.
     */
    @Override
    public TagAttribute get(String namespace, String localName) {
        TagAttribute attribute = null;
        for (TagAttribute current : attributes) {
            if (current.getLocalName().equals(localName)
                    && current.getNamespace().equals(namespace)) {
                attribute = current;
                break;
            }
        }
        return attribute;
    }

    /**
     * Get the tag attributes for the given namespace.
     *
     * @param namespace the namespace.
     * @return the array of tag attributes.
     */
    @Override
    public TagAttribute[] getAll(String namespace) {
        ArrayList<TagAttribute> all = new ArrayList<>();
        for (TagAttribute attribute : all) {
            if (attribute.getNamespace().equals(namespace)) {
                all.add(attribute);
            }
        }
        return all.toArray(new TagAttribute[0]);
    }

    /**
     * Get the namespaces.
     *
     * @return the namespaces.
     */
    @Override
    public String[] getNamespaces() {
        ArrayList<String> namespaces = new ArrayList<>();
        for (TagAttribute attribute : attributes) {
            if (!namespaces.contains(attribute.getNamespace())) {
                namespaces.add(attribute.getNamespace());
            }
        }
        return namespaces.toArray(new String[0]);
    }
}
