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

import java.io.IOException;
import java.io.Writer;
import java.util.Stack;
import javax.faces.component.UIComponent;
import javax.faces.context.ResponseWriter;

/**
 * The HTML 5 response writer.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
public class HTML5ResponseWriter extends ResponseWriter {

    /**
     * Stores the character encoding.
     */
    private String characterEncoding;

    /**
     * Stores the content type.
     */
    private String contentType;

    /**
     * Stores our stack.
     */
    private Stack<Snippet> stack = new Stack<>();

    /**
     * Stores the writer.
     */
    private Writer writer;

    /**
     * Constructor.
     */
    public HTML5ResponseWriter() {
    }

    /**
     * Constructor.
     *
     * @param writer the writer to use.
     * @param contentType the content type.
     * @param characterEncoding the character encoding.
     */
    public HTML5ResponseWriter(Writer writer, String contentType, String characterEncoding) {
        this.writer = writer;
        this.contentType = contentType;
        this.characterEncoding = characterEncoding;
    }

    /**
     * Clone with the given writer.
     *
     * @param writer the writer.
     * @return the response writer.
     */
    @Override
    public ResponseWriter cloneWithWriter(Writer writer) {
        return new HTML5ResponseWriter(writer, getContentType(), getCharacterEncoding());
    }

    /**
     * Close the writer.
     *
     * @throws IOException when an I/O error occurs.
     */
    @Override
    public void close() throws IOException {
        writer.close();
    }

    /**
     * End the document.
     *
     * @throws IOException when an I/O error occurs.
     */
    @Override
    public void endDocument() throws IOException {
    }

    /**
     * End the element.
     *
     * @param name the element name.
     * @throws IOException when an I/O error occurs.
     */
    @Override
    public void endElement(String name) throws IOException {
        writeStartElement();
        writer.write("</" + name + ">");
        if (!stack.isEmpty()) {
            Snippet element = stack.peek();
            if (element.type.equals("startElementWritten")) {
                stack.pop();
            }
        }
    }

    /**
     * Flush writer.
     *
     * @throws IOException when an I/O error occurs.
     */
    @Override
    public void flush() throws IOException {
        writer.flush();
    }

    /**
     * Get the character encoding.
     *
     * @return the character encoding.
     */
    @Override
    public String getCharacterEncoding() {
        return characterEncoding;
    }

    /**
     * Get the content type.
     *
     * @return the content type.
     */
    @Override
    public String getContentType() {
        return contentType;
    }

    /**
     * Start the document.
     *
     * @throws IOException when an I/O error occurs.
     */
    @Override
    public void startDocument() throws IOException {
    }

    /**
     * Start the element.
     *
     * @param name the element name.
     * @param component the UI component.
     * @throws IOException when an I/O error occurs.
     */
    @Override
    public void startElement(String name, UIComponent component) throws IOException {
        writeStartElement();
        stack.push(new Snippet("element", "<" + name));
    }

    /**
     * Write an attribute.
     *
     * @param name the name.
     * @param value the value.
     * @param property the property.
     * @throws IOException when an I/O error occurs.
     */
    @Override
    public void writeAttribute(String name, Object value, String property) throws IOException {
        stack.push(new Snippet("attribute", name + "=\"" + value.toString() + "\""));
    }

    /**
     * Write the comment.
     *
     * <p>
     * Even though the specification says we need to escape the comment the
     * HTML5 specification does not describe how to escape in comments. It only
     * describes what are valid comments. So erring on the side of caution here
     * and not trying to do any escaping. See
     * https://www.w3.org/TR/html5/syntax.html#comments
     * </p>
     *
     * @param comment the comment.
     * @throws IOException when an I/O error occurs.
     */
    @Override
    public void writeComment(Object comment) throws IOException {
        writeStartElement();
        writer.write("<!-- ");
        writer.write(comment.toString());
        writer.write(" -->");
    }

    /**
     * Write a preamble.
     * 
     * <p>
     *  Since we are generating HTML5 we are NOT going to write anything if
     *  a call to writePreamble happens as there is no preamble in HTML5.
     * </p>
     * 
     * @param preamble the preamble.
     * @throws IOException when an I/O error occurs.
     */
    @Override
    public void writePreamble(String preamble) throws IOException {
    }

    /**
     * Write a URI attribute.
     *
     * @param name the name.
     * @param value the value.
     * @param property the property.
     * @throws IOException when an I/O error occurs.
     */
    @Override
    public void writeURIAttribute(String name, Object value, String property) throws IOException {
        // TODO make sure it is actually properly escaped.
        stack.push(new Snippet("attribute", name + "=\"" + value.toString() + "\""));
    }

    /**
     * Write the text.
     *
     * @param text the text.
     * @param property the property.
     * @throws IOException when an I/O error occurs.
     */
    @Override
    public void writeText(Object text, String property) throws IOException {
        writeStartElement();
        // TODO make sure it is actually properly escaped.
        writer.write(text.toString());
    }

    /**
     * Write the text.
     *
     * @param text the text.
     * @param offset the offset.
     * @param length the length.
     * @throws IOException when an I/O error occurs.
     */
    @Override
    public void writeText(char[] text, int offset, int length) throws IOException {
        writeStartElement();
        writer.write(text, offset, length);
    }

    /**
     * Write the text.
     *
     * @param buffer the buffer.
     * @param offset the offset.
     * @param length the length.
     * @throws IOException when an I/O error occurs.
     */
    @Override
    public void write(char[] buffer, int offset, int length) throws IOException {
        writeStartElement();
        writer.write(buffer, offset, length);
    }

    /**
     * Write the start element (and its attributes).
     *
     * <p>
     * Peek at the top of the stack to see if startElementWritten is at the top
     * of the stack, if so do nothing. Otherwise get the start element start any
     * of its attributes from the stack and then write out the element start.
     * Then push startElementWritten onto the stack so we know we have already
     * written the start.
     * </p>
     */
    private void writeStartElement() throws IOException {
        if (!stack.isEmpty()) {
            Snippet element = stack.peek();
            if (!element.type.equals("startElementWritten")) {
                StringBuilder content = new StringBuilder();
                Stack<Snippet> snippets = new Stack<>();
                Snippet current = stack.pop();
                if (!stack.isEmpty()) {
                    while (!current.type.equals("element")) {
                        snippets.push(current);
                        current = stack.pop();
                    }
                }
                content.append(current.content);
                while (!snippets.isEmpty()) {
                    current = snippets.pop();
                    content.append(" ").append(current.content);
                }
                content.append(">");
                writer.write(content.toString());
                stack.push(new Snippet("startElementWritten"));
            }
        }
    }

    /**
     * Inner class that is used in our stack.
     */
    private static class Snippet {

        /**
         * Stores the content.
         */
        public String content;

        /**
         * Stores the type.
         */
        public String type;

        /**
         * Constructor.
         *
         * @param type the type of response element.
         */
        public Snippet(String type) {
            this.content = "";
            this.type = type;
        }

        /**
         * Constructor.
         *
         * @param type the type of response element.
         * @param content the content to write.
         */
        public Snippet(String type, String content) {
            this.content = content;
            this.type = type;
        }
    }
}
