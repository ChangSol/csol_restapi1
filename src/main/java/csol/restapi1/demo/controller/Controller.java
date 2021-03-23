package csol.restapi1.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/")
    public String index(){
        return "Csol Rest api 테스트 홈페이지 입니다.";
    }
}
