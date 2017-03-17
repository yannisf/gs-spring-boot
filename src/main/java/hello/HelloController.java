package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

    @Autowired
    HelloService helloService;

    @RequestMapping("/")
    public String index(@RequestParam("name") String name) {
        return String.format("[Spring] " + helloService.hello(name));
    }

}
