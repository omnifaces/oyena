/*
 * Copyright (c) 2002-2020 Manorrock.com. All Rights Reserved.
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
package com.manorrock.oyena.action.test.webapp;

import com.manorrock.oyena.action.ActionMapping;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 * The action bean.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@RequestScoped
public class ActionBean implements Serializable {
 
    /**
     * Execute the index action.
     * 
     * @return /index.xhtml
     */
    @ActionMapping("/index")
    public String execute() {
        return "/index.xhtml";
    }
    
    /**
     * Execute the page 2 action.
     * 
     * @param request the HTTP servlet request.
     * @return /index.xhtml
     */
    @ActionMapping("/page2") 
    public String executePage2(HttpServletRequest request) {
        System.out.println(request);
        return "/index.xhtml";
    }
    
    /**
     * Execute the page 3 action.
     * 
     * @param request the HTTP servlet request.
     * @param facesContext the Faces context.
     * @return /index.xhtml
     */
    @ActionMapping("/page3") 
    public String executePage3(HttpServletRequest request, FacesContext facesContext) {
        System.out.println(request);
        System.out.println(facesContext);
        return "/index.xhtml";
    }
}
