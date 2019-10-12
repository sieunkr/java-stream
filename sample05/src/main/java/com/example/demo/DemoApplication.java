package com.example.demo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.Collectors;

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
            [AS-IS] 순차 처리
        */
        long start = System.currentTimeMillis();

        List<String> coffeeNameList = Arrays.asList("latte", "americano", "cappuccino", "coldbrew");

        List<Coffee> coffees = coffeeNameList.stream()
                .map(coffeeRepository::findByName)
                .collect(Collectors.toList());

        long end = System.currentTimeMillis();
        log.info( "실행 시간 : " + ( end - start )/1000.0 + "초");


        /*
            [TO-BE] 병렬 처리 - ForkJoinPool.commonpool 사용
        */
        start = System.currentTimeMillis();

        List<Coffee> coffeesByParallelStream = coffeeNameList.parallelStream()
                .map(coffeeRepository::findByName)
                .collect(Collectors.toList());

        end = System.currentTimeMillis();
        log.info( "병렬 실행 시간 : " + ( end - start )/1000.0 + "초");


        /*
            [TO-BE] 병렬 처리 - ForkJoinPool.commonpool 사용
        */
        start = System.currentTimeMillis();

        coffeeNameList = Arrays.asList("latte", "americano", "cappuccino", "coldbrew", "affogato");

        coffeesByParallelStream = coffeeNameList.parallelStream()
                .map(coffeeRepository::findByName)
                .collect(Collectors.toList());

        end = System.currentTimeMillis();
        log.info( "병렬 실행 시간 : " + ( end - start )/1000.0 + "초");



        /*
            [TO-BE] 병렬 처리 - CustomPool 사용
        */
        start = System.currentTimeMillis();

        final List<String> list = Arrays.asList("latte", "americano", "cappuccino", "coldbrew", "affogato");

        ForkJoinPool customThreadPool = new ForkJoinPool(10);
        ForkJoinTask<List<Coffee>> threads = customThreadPool.submit(() ->
                list.parallelStream()
                        .map(coffeeRepository::findByName)
                        .collect(Collectors.toList())
        );

        threads.join();

        end = System.currentTimeMillis();
        log.info( "병렬 실행 시간 : " + ( end - start )/1000.0 + "초");



    }
}
