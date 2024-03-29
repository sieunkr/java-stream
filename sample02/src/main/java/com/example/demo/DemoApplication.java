package com.example.demo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.function.Predicate;

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

        int sumByMilk = coffeeRepository.findAllOrderByPrice().stream()
                .filter(Coffee::getMilk)
                .mapToInt(Coffee::getPrice)
                .sum();

        int sumByUnder1600 = coffeeRepository.findAllOrderByPrice().stream()
                .filter(c -> c.getPrice() < 1600)
                .mapToInt(Coffee::getPrice)
                .sum();

        log.info("밀크가 들어있는 메뉴의 총합은? " + sumByMilk + "원");
        log.info("1600원 이하 메뉴의 총합은? " + sumByUnder1600 + "원");



        /*
            [TO-BE] 방법 1
        */

        sumByMilk = sumByPredicate(coffeeRepository.findAllOrderByPrice(), Coffee::getMilk);
        sumByUnder1600 = sumByPredicate(coffeeRepository.findAllOrderByPrice(), coffee -> coffee.getPrice() < 1600);

        log.info("밀크가 들어있는 메뉴의 총합은? " + sumByMilk + "원");
        log.info("1600원 이하 메뉴의 총합은? " + sumByUnder1600 + "원");


        /*
            참고
            https://www.baeldung.com/java-predicate-chain
        */

        Predicate<Coffee> predicate1 = Coffee::getMilk;
        Predicate<Coffee> predicate2 =  coffee -> coffee.getPrice() < 1600;

        int sumByMilkAndUnder1600 = coffeeRepository.findAllOrderByPrice().stream()
                .filter(predicate1.and(predicate2))
                .mapToInt(Coffee::getPrice)
                .sum();

        log.info("밀크가 들어있고, 1600원 이하인 메뉴의 총합은? " + sumByMilkAndUnder1600 + "원");

    }

    private static int sumByPredicate(final List<Coffee> coffeeList, final Predicate<Coffee> predicate){
        return coffeeList.stream()
                .filter(predicate)
                .mapToInt(Coffee::getPrice)
                .sum();
    }
}
