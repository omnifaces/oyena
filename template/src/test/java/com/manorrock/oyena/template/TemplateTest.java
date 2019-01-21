/*
 * Copyright (c) 2002-2019 Manorrock.com. All Rights Reserved.
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
package com.manorrock.oyena.template;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * The template tests.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
public class TemplateTest {

    /**
     * Test using of template 1.
     *
     * @throws Exception when a serious error occurs.
     */
    @Test
    public void testTemplate1() throws Exception {
        TemplateWebApplication webApp = new TemplateWebApplication("src/main/template1");
        webApp.initialize();
        webApp.start();

        TemplateHttpServletRequest request = new TemplateHttpServletRequest();
        request.setWebApplication(webApp);
        request.setContextPath("");
        request.setServletPath("/faces");
        request.setPathInfo("/notfound.html");

        TemplateHttpServletResponse response = new TemplateHttpServletResponse();
        TemplateServletOutputStream outputStream = new TemplateServletOutputStream();
        response.setOutputStream(outputStream);
        outputStream.setResponse(response);

        webApp.service(request, response);

        assertEquals(404, response.getStatus());
    }

    /**
     * Test using of template 2.
     *
     * @throws Exception when a serious error occurs.
     */
    @Test
    public void testTemplate2() throws Exception {
        TemplateWebApplication webApp = new TemplateWebApplication("src/main/template2");
        webApp.initialize();
        webApp.start();

        TemplateHttpServletRequest request = new TemplateHttpServletRequest();
        request.setWebApplication(webApp);
        request.setContextPath("");
        request.setServletPath("/index.html");
        request.setPathInfo(null);

        TemplateHttpServletResponse response = new TemplateHttpServletResponse();
        TemplateServletOutputStream outputStream = new TemplateServletOutputStream();
        response.setOutputStream(outputStream);
        outputStream.setResponse(response);

        webApp.service(request, response);

        assertEquals(200, response.getStatus());
        String responseString = new String(response.getResponseBody());
        assertTrue(responseString.contains("Hello Mojarra"));
    }
}
