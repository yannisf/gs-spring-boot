package fraglab.jersey;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(MessageResource.class);
        register(PoweredByResponseFilter.class);
        register(SignEntityResponseFilter.class);
    }

}