package hello;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/jersey")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(JerseyHelloResource.class);
    }

}