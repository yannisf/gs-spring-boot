package fraglab.jersey;

import fraglab.jersey.xml.Message;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class SignEntityResponseFilter implements ContainerResponseFilter {

    public static final String HMAC_ALGO = "HmacSHA256";

    @Override
    public void filter(
            ContainerRequestContext requestContext,
            ContainerResponseContext responseContext)
            throws IOException {

        Message entity = (Message) responseContext.getEntity();

        String secret = "secret";
        String message = entity.getDate().getTime() + ":" + entity.getType() + ":" + entity.getContent();

        Mac hmac = null;
        try {
            hmac = Mac.getInstance(HMAC_ALGO);
            SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), HMAC_ALGO);
            hmac.init(secret_key);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

        String hash = Base64.encodeBase64String(hmac.doFinal(message.getBytes()));
        entity.setHash(hash);
    }

}
