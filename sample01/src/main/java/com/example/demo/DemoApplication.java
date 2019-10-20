package com.example.demo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class DemoApplication implements CommandLineRunner {

    private final CoffeeRepository coffeeRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {


        /*
            [AS-IS] 명령형 프로그래밍
        */

        List<Coffee> coffees = new ArrayList<>();

        for (Coffee coffee : coffeeRepository.findAllOrderByPrice()) {
            if(coffee.getMilk()){
                coffees.add(coffee);
                //break;
            }
        }

        if(coffees.isEmpty()){
            log.info("밀크가 들어있는 가장 저렴한 메뉴는 없다.");
        }
        else{
            log.info("밀크가 들어있는 가장 저렴한 메뉴는? " + coffees.get(0).getName());
        }




        /*
            [TO-BE] 함수형 프로그래밍으로 개선
        */

        Optional<Coffee> coffeeOptional = coffeeRepository.findAllOrderByPrice().stream()
                .filter(Coffee::getMilk)
                .findFirst();

        if(coffeeOptional.isPresent()){
            log.info("밀크가 들어있는 가장 저렴한 메뉴는? " + coffeeOptional.get().getName());
        }
        else{
            log.info("밀크가 들어있는 가장 저렴한 메뉴는 없다.");
        }

    }
}