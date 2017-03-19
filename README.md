## Spring Boot with Jersey
This is a sample project to showcase the use of Spring Boot with Jersey. A resource is deployed with 3 methods.

### Endpoints
All endpoints are deployed under `/api/message`

### Filters
Sample request filters are registered. One of them concatenates the properties of the `message` and creates an HMAC that is then set as attribute.

### Build and run

    $ mvn clean package -Dmaven.test.skip
    $ java -jar target\gs-spring-boot-0.1.0.jar

or simply,

    $ mvn spring-boot:run

### Test
The application can be tested with the following ways:

- Using the included _postman_ collection, `external/postman_collection.json`
- Using the `ClientTest` unit test.
- Using the included `index.html`

#### The `MessageBodyWriter not found for media type=application/xml` issue
Annotating the XML entities with `@XmlRootElement` solves the `MessageBodyWriter not found for media type=application/xml`. If this is not an option there are a couple of steps that must be followed so that a Spring Boot application can work around this limitation:

1. Add the following dependency to `pom.xml` (version corresponds  to jackson bundled with the respective Spring Boot versison):

        <dependency>
            <groupId>com.fasterxml.jackson.jaxrs</groupId>
            <artifactId>jackson-jaxrs-xml-provider</artifactId>
            <version>2.8.7</version>
        </dependency>

2. Add the following line to `JerseyConfig`:

        register(JacksonJaxbXMLProvider.class);
