package com.jojoldu.book.springboot.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //컨트롤러를 JSON을 반환하는 컨트롤러로 만들어줌.
                //예전에는 ResponseBody를 각 메소드맏다 선언햇던 것을 한번에 사용할수있게해준다고 생각하면됨
public class HelloController {
    @GetMapping("/hello")//HTTP Method인 Get의 요청을 받을 수 있는 API를 만들어줌
                         //예전에는 RequestMapping(method=RequestMethod.GET)으로 사용되었음 이제 이 프로젝트는
    // /                   hello 요청이 오면 문자열 hello를 반환하는 기능을 가짐ㅁ
    public String hello() {
        return "hello";
    }
}
