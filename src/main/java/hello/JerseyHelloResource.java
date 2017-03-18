package hello;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.dto.MyDto;
import hello.xml.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

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

    @POST
    @Path("/{type}/{subtype}/constant/")
    @Consumes(value = {MediaType.APPLICATION_FORM_URLENCODED})
    @Produces(value = {MediaType.APPLICATION_XML})
    public MessageType formProcess(
            @PathParam("type") String type,
            @PathParam("subtype") String subtype,
            @FormParam("my_param") String myParamValue) throws IOException {
        System.out.println(String.format("[POST] %s/%s/constant: %s", type, subtype, myParamValue));

        ObjectMapper objectMapper = new ObjectMapper();
        MyDto myDto = objectMapper.readValue(myParamValue, MyDto.class);

        MessageType outMessageType = new MessageType();
        outMessageType.setType(String.format("%s/%s", type, subtype));
        outMessageType.setContent(myDto.getValue());
        return outMessageType;
    }


}
