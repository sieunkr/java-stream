package com.example.demo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.function.Function;

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
            [AS-IS]
        */

        /*
        int discountValue = getDiscountPriceWithCustomFunction(coffeeRepository.findByName("latte"), new CustomFunction<Coffee, Integer>() {
            @Override
            public Integer apply(Coffee c) {
                return c.getPrice() - 100;
            }
        });
        */

        int discountValue = getDiscountPriceWithCustomFunction(coffeeRepository.findByName("latte"), c -> c.getPrice() - 100);

        log.info("할인된 가격은? " + discountValue + "원");


        /*
            [TO-BE]
        */
        discountValue = getDiscountPriceWithDefaultFunction(coffeeRepository.findByName("latte"), c -> c.getPrice() - 100);

        log.info("할인된 가격은? " + discountValue + "원");

    }
    
    private int getDiscountPriceWithCustomFunction(Coffee coffee, CustomFunction<Coffee, Integer> function){
        return function.apply(coffee);
    }

    private int getDiscountPriceWithDefaultFunction(Coffee coffee, Function<Coffee, Integer> function){
        return function.apply(coffee);
    }
}