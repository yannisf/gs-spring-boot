package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

@Component
@Path("/")
public class JerseyHelloResource {

    @Autowired
    HelloService helloService;

    @GET
    public String index(@QueryParam("name") String name) {
        return "[Jersey] " + helloService.hello(name);
    }

}
