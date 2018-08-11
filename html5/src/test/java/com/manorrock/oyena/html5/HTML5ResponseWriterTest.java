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
import javax.faces.context.ResponseWriter;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;

/**
 * The JUnit tests for the HTML5ResponseWriter class.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
public class HTML5ResponseWriterTest {

    /**
     * Test cloneWithWriter method.
     */
    @Test
    public void testCloneWithWriter() {
        HTML5ResponseWriter responseWriter = new HTML5ResponseWriter();
        ResponseWriter result = responseWriter.cloneWithWriter(new StringWriter());
        assertNotNull(result);
    }

    /**
     * Test close method.
     *
     * @throws Exception when a serious error occurs.
     */
    @Test
    public void testClose() throws Exception {
        HTML5ResponseWriter responseWriter = new HTML5ResponseWriter(new StringWriter(), null, null);
        responseWriter.close();
    }

    /**
     * Test endDocument method.
     *
     * @throws Exception when a serious error occurs.
     */
    @Test
    public void testEndDocument() throws Exception {
        HTML5ResponseWriter responseWriter = new HTML5ResponseWriter(new StringWriter(), null, null);
        responseWriter.endDocument();
    }

    /**
     * Test endElement method.
     *
     * @throws Exception when a serious error occurs.
     */
    @Test
    public void testEndElement() throws Exception {
        StringWriter stringWriter = new StringWriter();
        HTML5ResponseWriter responseWriter = new HTML5ResponseWriter(stringWriter, null, null);
        responseWriter.startElement("html", null);
        responseWriter.endElement("html");
        assertEquals("<html></html>", stringWriter.toString());
    }

    /**
     * Test flush method.
     *
     * @throws Exception when a serious error occurs.
     */
    @Test
    public void testFlush() throws Exception {
        StringWriter stringWriter = new StringWriter();
        HTML5ResponseWriter responseWriter = new HTML5ResponseWriter(stringWriter, null, null);
        responseWriter.flush();
    }

    /**
     * Test getCharacterEncoding method.
     */
    @Test
    public void testGetCharacterEncoding() {
        StringWriter stringWriter = new StringWriter();
        HTML5ResponseWriter responseWriter = new HTML5ResponseWriter(stringWriter, null, null);
        assertNull(responseWriter.getCharacterEncoding());
    }

    /**
     * Test getContentType method.
     */
    @Test
    public void testGetContentType() {
        StringWriter stringWriter = new StringWriter();
        HTML5ResponseWriter responseWriter = new HTML5ResponseWriter(stringWriter, null, null);
        assertNull(responseWriter.getContentType());
    }

    /**
     * Test startDocument method.
     *
     * @throws Exception when a serious error occurs.
     */
    @Test
    public void testStartDocument() throws Exception {
        StringWriter stringWriter = new StringWriter();
        HTML5ResponseWriter responseWriter = new HTML5ResponseWriter(stringWriter, null, null);
        responseWriter.startDocument();
    }

    /**
     * Test writeAttribute method.
     *
     * @throws Exception when a serious error occurs.
     */
    @Test
    public void testWriteAttribute() throws Exception {
        StringWriter stringWriter = new StringWriter();
        HTML5ResponseWriter responseWriter = new HTML5ResponseWriter(stringWriter, null, null);
        responseWriter.startElement("html", null);
        responseWriter.writeAttribute("name", "value", null);
        responseWriter.endElement("html");
        assertEquals("<html name=\"value\"></html>", stringWriter.toString());
    }

    /**
     * Test writeComment method.
     *
     * @throws Exception when a serious error occurs.
     */
    @Test
    public void testWriteComment() throws Exception {
        StringWriter stringWriter = new StringWriter();
        HTML5ResponseWriter responseWriter = new HTML5ResponseWriter(stringWriter, null, null);
        responseWriter.startElement("html", null);
        responseWriter.writeComment("my comment");
        responseWriter.endElement("html");
        assertEquals("<html><!-- my comment --></html>", stringWriter.toString());
    }

    /**
     * Test writeURIAttribute method.
     *
     * @throws Exception when a serious error occurs.
     */
    @Test
    public void testWriteURIAttribute() throws Exception {
        StringWriter stringWriter = new StringWriter();
        HTML5ResponseWriter responseWriter = new HTML5ResponseWriter(stringWriter, null, null);
        responseWriter.startElement("html", null);
        responseWriter.writeURIAttribute("name", "value", null);
        responseWriter.endElement("html");
        assertEquals("<html name=\"value\"></html>", stringWriter.toString());
    }

    /**
     * Test writeText method.
     *
     * @throws Exception when a serious error occurs.
     */
    @Test
    public void testWriteText() throws Exception {
        StringWriter stringWriter = new StringWriter();
        HTML5ResponseWriter responseWriter = new HTML5ResponseWriter(stringWriter, null, null);
        responseWriter.startElement("html", null);
        responseWriter.writeText("name", null);
        responseWriter.endElement("html");
        assertEquals("<html>name</html>", stringWriter.toString());
    }

    /**
     * Test writeText method.
     *
     * @throws Exception when a serious error occurs.
     */
    @Test
    public void testWriteText2() throws Exception {
        StringWriter stringWriter = new StringWriter();
        HTML5ResponseWriter responseWriter = new HTML5ResponseWriter(stringWriter, null, null);
        responseWriter.startElement("html", null);
        responseWriter.writeText("name".toCharArray(), 0, 4);
        responseWriter.endElement("html");
        assertEquals("<html>name</html>", stringWriter.toString());
    }

    /**
     * Test write method.
     *
     * @throws Exception when a serious error occurs.
     */
    @Test
    public void testWrite() throws Exception {
        StringWriter stringWriter = new StringWriter();
        HTML5ResponseWriter responseWriter = new HTML5ResponseWriter(stringWriter, null, null);
        responseWriter.startElement("html", null);
        responseWriter.write("name".toCharArray(), 0, 4);
        responseWriter.endElement("html");
        assertEquals("<html>name</html>", stringWriter.toString());
    }

}
