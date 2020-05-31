package fantasy.Annotation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/test")
    @Log(operationType="add操作:",operationName="添加用户")
    public String testAOP(){
        return "测试自定义注解访法执行。。。";
    }
}