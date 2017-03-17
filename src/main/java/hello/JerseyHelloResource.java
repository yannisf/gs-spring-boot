package hello;

import hello.xml.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Component
@Path("/")
public class JerseyHelloResource {

    @Autowired
    HelloService helloService;

    @GET
    public String index(@QueryParam("name") String name) {
        return "[Jersey] " + helloService.hello(name);
    }

    @POST
    @Path("/{type}/endpoint")
    @Produces(value = {MediaType.APPLICATION_XML})
    public MessageType process(@PathParam("type") String type) {
        System.out.println(String.format("[POST] %s/endpoint: ", type));
        MessageType messageType = new MessageType();
        messageType.setType(type);
        messageType.setContent("stub");

        return messageType;
    }

}
