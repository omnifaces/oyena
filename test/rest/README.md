
# OmniFaces Oyena REST

The OmniFaces Oyena REST module delivers you with an REST framework that you
can use with Eclipse Mojarra.

## Prerequisites

Your project should already be configured to use Eclipse Mojarra.

## Configuration

To use it in your web application you will need to do the following:

1. Add the Maven dependency
2. Add a faces-config.xml with the CdiLifecycleFactory
3. Add a beans.xml
4. Add a Servlet mapping for the Oyena REST Servlet

### Add the Maven dependency

Add the following Maven dependency:

    <dependency>
      <groupId>org.omnifaces.oyena</groupId>
      <artifactId>oyena-rest</artifactId>
      <version>x.y.z</version>
    </dependency>

Where you need to replace x.y.z with the version you want to use.

### Add a faces-config.xml with the CdiLifecycleFactory

And you need to have a faces-config.xml in the WEB-INF directory with at minimum the following:

    <faces-config version="2.3"
              xmlns="http://xmlns.jcp.org/xml/ns/javaee"
              xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
              xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee 
                                  http://xmlns.jcp.org/xml/ns/javaee/web-facesconfig_2_3.xsd">
      <factory>
        <lifecycle-factory>org.omnifaces.oyena.cdi.CdiLifecycleFactory</lifecycle-factory>
      </factory>
    </faces-config>

### Add a beans.xml

As the framework requires CDI you need to activate CDI.

Add an placeholder beans.xml to the WEB-INF directory and it will take care of that:

    <beans
        xmlns="http://xmlns.jcp.org/xml/ns/javaee"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee 
                            http://xmlns.jcp.org/xml/ns/javaee/beans_2_0.xsd"
        bean-discovery-mode="all">
    </beans>

### Add a Servlet mapping for the Oyena Action Servlet

Add a servlet mapping to the web.xml file:

    <web-app xmlns="http://java.sun.com/xml/ns/javaee"
	 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	 xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
                             http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
	 version="4.0">
      <servlet-mapping>
        <servlet-name>Oyena REST Servlet</servlet-name>
        <url-pattern>/rest/*</url-pattern>
      </servlet-mapping>
    </web-app>

## Using it

To actually use it you wil need to create a CDI bean.

1. Create a CDI bean

### Create a CDI bean

Create a CDI bean with the following content and use your IDE to resolve the
necessary imports:

    @FacesConfig(version = FacesConfig.Version.JSF_2_3)
    @RequestScoped
    public class HelloWorldJsonBean implements Serializable {
 
      /**
       * Perform helloWorld call.
       * 
       * @return the JSON representation of "Hello World"
       */
      @RestPath("/helloWorld")
      public String helloWorld() {
        return "Hello World";
      }
    }


## Try it

Deploy the web application to the server of your choice.

Assuming the web application is deployed at /myrest on your localhost server
listening on port 8080 you would browse to http://localhost:8080/myrest/helloWorld
to try it.

Enjoy!
