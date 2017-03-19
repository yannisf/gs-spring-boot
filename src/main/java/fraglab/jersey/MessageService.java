package fraglab.jersey;

import org.springframework.stereotype.Service;

@Service
public class MessageService {

    public String process(String value) {
        return String.format("[process]: %s", value);
    }

}
