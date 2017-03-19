package fraglab.jersey;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fraglab.jersey.dto.RequestDto;
import fraglab.jersey.xml.Message;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

import static org.junit.Assert.assertThat;

public class ClientTest {

    @Test
    public void myTest() throws JsonProcessingException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/api").path("message");

        RequestDto requestDto = new RequestDto("Some value & is > 5");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonObject = objectMapper.writeValueAsString(requestDto);
        Form form = new Form();
        form.param("p", jsonObject);

        Message message = target
                .path("custom_type")
                .path("custom_subtype")
                .path("endpoint")
                .request(MediaType.APPLICATION_XML)
                .post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE), Message.class);

        System.out.println(message);
    }

}
