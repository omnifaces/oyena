
# Manorrock Oyena Template

The Manorrock Oyena Template module delivers you with an easy way to use Facelets
templating in a standalone environment.

## Configuration

To use it in your web application you will need to do the following:

1. Add the Maven dependency
2. Create a web.xml where you register the Faces servlet
3. Create a Facelet file
4. Use the template module to generate the content

### Add the Maven dependency

Add the following Maven dependency:

    <dependency>
      <groupId>com.manorrock.oyena</groupId>
      <artifactId>oyena-template</artifactId>
      <version>x.y.z</version>
    </dependency>

Where you need to replace x.y.z with the version you want to use.

### Create a web.xml where you register the Faces servlet

    <?xml version="1.0" encoding="UTF-8"?>

    <web-app version="3.0" xmlns="http://java.sun.com/xml/ns/javaee" 
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
        xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd">
        <servlet>
            <servlet-name>Faces Servlet</servlet-name>
            <servlet-class>javax.faces.webapp.FacesServlet</servlet-class>
            <load-on-startup>1</load-on-startup>
        </servlet>
        <servlet-mapping>
            <servlet-name>Faces Servlet</servlet-name>
            <url-pattern>*.html</url-pattern>
        </servlet-mapping>
    </web-app>

### Create a Facelet file

    <!DOCTYPE html>

    <html xmlns:h="http://java.sun.com/jsf/html">
        <h:head>
            <title>Hello Mojarra</title>
        </h:head>
        <h:body>
            Hello Mojarra, this is the Faces Context instance: <h:outputText value="#{facesContext}"/>
        </h:body>
    </html>

### Use the template module to generate the content

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

