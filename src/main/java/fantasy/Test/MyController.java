package fantasy.Test;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    private String getData(String user) {
        String a = user;
        String s = JSON.toJSONString(user);
        return null;
    }
}
