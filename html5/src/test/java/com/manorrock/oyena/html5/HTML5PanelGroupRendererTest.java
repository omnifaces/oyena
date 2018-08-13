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
package com.manorrock.oyena.html5;

import java.io.StringWriter;
import javax.faces.component.html.HtmlPanelGroup;
import javax.faces.context.FacesContext;
import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.powermock.api.easymock.PowerMock;
import static org.powermock.api.easymock.PowerMock.replay;
import static org.powermock.api.easymock.PowerMock.verify;

/**
 * The JUnit tests for HTML5PanelGroupRenderer.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
public class HTML5PanelGroupRendererTest {
    
    /**
     * Stores the Faces context.
     */
    private FacesContext context;
    
    /**
     * Setup before testing.
     */
    @Before
    public void before() {
        context = PowerMock.createPartialMockForAllMethodsExcept(FacesContext.class, "getCurrentInstance");
    }
    
    /**
     * Test encodeBegin method.
     * 
     * @throws Exception when a serious error occurs.
     */
    @Test(expected = NullPointerException.class)
    public void testEncodeBegin() throws Exception {
        HTML5PanelGroupRenderer renderer = new HTML5PanelGroupRenderer();
        renderer.encodeBegin(null, null);
    }
    
    /**
     * Test encodeBegin method.
     * 
     * @throws Exception when a serious error occurs.
     */
    @Test(expected = NullPointerException.class)
    public void testEncodeBegin2() throws Exception {
        HTML5PanelGroupRenderer renderer = new HTML5PanelGroupRenderer();
        HtmlPanelGroup panelGroup = new HtmlPanelGroup();
        renderer.encodeBegin(null, panelGroup);
    }
        
    /**
     * Test encodeBegin method.
     * 
     * @throws Exception when a serious error occurs.
     */
    @Test
    public void testEncodeBegin3() throws Exception {
        StringWriter writer = new StringWriter();
        HTML5ResponseWriter responseWriter = new HTML5ResponseWriter(writer, null, null);
        HTML5PanelGroupRenderer renderer = new HTML5PanelGroupRenderer();
        HtmlPanelGroup panelGroup = new HtmlPanelGroup();
        expect(context.getResponseWriter()).andReturn(responseWriter).anyTimes();
        replay(context);
        renderer.encodeBegin(context, panelGroup);
        renderer.encodeEnd(context, panelGroup);
        verify(context);
        String content = writer.toString();
        assertTrue(content.contains("<span></span>"));
    }
        
    /**
     * Test encodeBegin method.
     * 
     * @throws Exception when a serious error occurs.
     */
    @Test
    public void testEncodeBegin4() throws Exception {
        StringWriter writer = new StringWriter();
        HTML5ResponseWriter responseWriter = new HTML5ResponseWriter(writer, null, null);
        HTML5PanelGroupRenderer renderer = new HTML5PanelGroupRenderer();
        HtmlPanelGroup panelGroup = new HtmlPanelGroup();
        panelGroup.setLayout("block");
        expect(context.getResponseWriter()).andReturn(responseWriter).anyTimes();
        replay(context);
        renderer.encodeBegin(context, panelGroup);
        renderer.encodeEnd(context, panelGroup);
        verify(context);
        String content = writer.toString();
        assertTrue(content.contains("<div></div>"));
    }
        
    /**
     * Test encodeBegin method.
     * 
     * @throws Exception when a serious error occurs.
     */
    @Test
    public void testEncodeBegin5() throws Exception {
        StringWriter writer = new StringWriter();
        HTML5ResponseWriter responseWriter = new HTML5ResponseWriter(writer, null, null);
        HTML5PanelGroupRenderer renderer = new HTML5PanelGroupRenderer();
        HtmlPanelGroup panelGroup = new HtmlPanelGroup();
        panelGroup.setLayout("block");
        panelGroup.setStyle("mystyle");
        expect(context.getResponseWriter()).andReturn(responseWriter).anyTimes();
        replay(context);
        renderer.encodeBegin(context, panelGroup);
        renderer.encodeEnd(context, panelGroup);
        verify(context);
        String content = writer.toString();
        assertEquals("<div style=\"mystyle\"></div>", content);
    }
        
    /**
     * Test encodeBegin method.
     * 
     * @throws Exception when a serious error occurs.
     */
    @Test
    public void testEncodeBegin6() throws Exception {
        StringWriter writer = new StringWriter();
        HTML5ResponseWriter responseWriter = new HTML5ResponseWriter(writer, null, null);
        HTML5PanelGroupRenderer renderer = new HTML5PanelGroupRenderer();
        HtmlPanelGroup panelGroup = new HtmlPanelGroup();
        panelGroup.setLayout("block");
        panelGroup.setStyleClass("myclass");
        expect(context.getResponseWriter()).andReturn(responseWriter).anyTimes();
        replay(context);
        renderer.encodeBegin(context, panelGroup);
        renderer.encodeEnd(context, panelGroup);
        verify(context);
        String content = writer.toString();
        assertEquals("<div class=\"myclass\"></div>", content);
    }
}
