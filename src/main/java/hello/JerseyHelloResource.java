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
    @Consumes(value = {MediaType.APPLICATION_XML})
    @Produces(value = {MediaType.APPLICATION_XML})
    public MessageType process(@PathParam("type") String type, MessageType inMessageType) {
        System.out.println(String.format("[POST] %s/endpoint: %s", type, inMessageType.getContent()));
        MessageType outMessageType = new MessageType();
        outMessageType.setType(type);
        outMessageType.setContent(inMessageType.getContent());

        return outMessageType;
    }

}
