package com.example.demo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class DemoApplication implements CommandLineRunner {

    private final CoffeeRepository coffeeRepository;

    private String prefix = "cafe:";

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        log.info("prefix는 " + this.prefix + "입니다.");

        /*
            익명클래스
        */
        List<String> list = coffeeRepository.findAllOrderByPrice().stream()
                .map(new Function<Coffee, String>() {
                    @Override
                    public String apply(Coffee c) {
                        //this.prefix 를 사용하면 에러
                        return DemoApplication.this.prefix + c.getName();
                    }
                })
                .collect(Collectors.toList());

        list.forEach(log::info);


        /*
            Lambda 의 Lexical Scope 예시
        */
        list = coffeeRepository.findAllOrderByPrice().stream()
                .map(c -> this.prefix + c.getName())
                .collect(Collectors.toList());

        list.forEach(log::info);


    }

}