
# OmniFaces Oyena CDI

The OmniFaces Oyena CDI module delivers you CDI-based implementations that can
be used to extend the behavior of Eclipse Mojarra.

## Prerequisites

Your project should already be configured to use Eclipse Mojarra.

## Configuration

To use it in your web application you will need to do the following:

1. Add the Maven dependency
2. Add a faces-config.xml with the factory of choice.
3. Add a beans.xml

### Add the Maven dependency

Add the following Maven dependency:

    <dependency>
      <groupId>org.omnifaces.oyena</groupId>
      <artifactId>oyena-cdi</artifactId>
      <version>x.y.z</version>
    </dependency>

Where you need to replace x.y.z with the version you want to use.

### Add a faces-config.xml with the factory of choice.

In the example below we use the CDI-based LifecycleFactory:

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

As the module requires CDI you need to activate CDI.

Add an placeholder beans.xml to the WEB-INF directory and it will take care of that:

    <beans
        xmlns="http://xmlns.jcp.org/xml/ns/javaee"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee 
                            http://xmlns.jcp.org/xml/ns/javaee/beans_2_0.xsd"
        bean-discovery-mode="all">
    </beans>
