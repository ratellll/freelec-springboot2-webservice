package com.jojoldu.book.springboot.web.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter // 선언된 모든필드의 get 메소드를 생성해줌
@RequiredArgsConstructor// 선언된 모든 final 필드가 포함된 생성자를 생성해줌
                        //final 이 없는ㄴ 필드는 생성자에 포함되지않음
public class HelloRespronseDto {

    private final String name;
    private final int amount;
}
