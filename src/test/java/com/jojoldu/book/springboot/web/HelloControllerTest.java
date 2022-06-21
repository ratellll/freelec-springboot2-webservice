package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.config.auth.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class) //테스트를 진행할때 JUit에 내장된 실행자외에 다른 실행자를 실행시킴.
                             //여기서는 SpringExtension라는 스프링 실행자 사용.
                             //즉 스프링 부트 테스트와 JUnit사이에 연결자 역할을 함.
@WebMvcTest(controllers = HelloController.class,
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
        }
)                                               // 여러 스프링 테스트 언노테이션중 WEB에 집중하는 어노테이션
                                                //선언할경우 Controller , ControllerAdvice등 사용 가능
                                                // Service, Compomemt,Repository등은 사용 불가.
                                                // 여기서는 컨트롤러만 사용하기때문에 선언
public class HelloControllerTest {
    @Autowired // 스프링이 관리하는 Bean을 주입받음
    private MockMvc mvc; //웹 API를 테스트할때 사용, 스프링MVC테스트의 시작점, 이 클래스를 통해 HTTP GET,POST등에 대한 API테스트 를 할수있음

    @WithMockUser(roles="USER")
    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello")) //MockMvc를 통해 /hello 주소로 HTTP GET을 요청
                                              //체인닝이 지원ㄴ되어 아래와 같이 여러 검증 기능을 이어서 선언할수있음
                .andExpect(status().isOk())//mvc.perform 의 결과 , HTTP Header의 Status를 검증
                                            // 200,404,500 오류의 상태를 검증.
                                            // 여기선 OK즉 , 200인지 아닌지를 검증
                .andExpect(content().string(hello));
                                    //mvc.perform의 결과를 검증
                                    //응답본문의 내용을 검증
                                    //Controller에서 "hello"를 리턴하기 때문에 이 값이 맞는지 검증함
    }
    @WithMockUser(roles="USER")
    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;
        mvc.perform(
                        get("/hello/dto")
                                .param("name", name)
                                .param("amount", String.
                                        valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}
