package fraglab.jersey;

import com.fasterxml.jackson.databind.ObjectMapper;
import fraglab.jersey.dto.RequestDto;
import fraglab.jersey.xml.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Component
@Path("/message")
public class MessageResource {

    @Autowired
    MessageService messageService;

    @GET
    public String index(
            @QueryParam("p") String value) {
        return String.format("[GET] / => %s", messageService.process(value));
    }

    @POST
    @Path("/{type}/endpoint")
    @Consumes(value = {MediaType.APPLICATION_XML})
    @Produces(value = {MediaType.APPLICATION_XML})
    public Message process(@PathParam("type") String type, Message inMessage) {
        System.out.println(String.format("[POST] %s/endpoint: %s", type, inMessage.getContent()));
        Message outMessage = new Message();
        outMessage.setType(type);
        outMessage.setContent(messageService.process(inMessage.getContent()));

        return outMessage;
    }

    @POST
    @Path("/{type}/{subtype}/endpoint")
    @Consumes(value = {MediaType.APPLICATION_FORM_URLENCODED})
    @Produces(value = {MediaType.APPLICATION_XML})
    public Message formProcess(
            @PathParam("type") String type,
            @PathParam("subtype") String subtype,
            @FormParam("p") String myParamValue) throws IOException {
        System.out.println(String.format("[POST] %s/%s/endpoint: %s", type, subtype, myParamValue));

        ObjectMapper objectMapper = new ObjectMapper();
        RequestDto requestDto = objectMapper.readValue(myParamValue, RequestDto.class);

        Message outMessage = new Message();
        outMessage.setType(String.format("%s_extracted/%s_extracted", type, subtype));
        outMessage.setContent(messageService.process(requestDto.getValue()));
        return outMessage;
    }

}
