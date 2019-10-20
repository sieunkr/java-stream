package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StringUtils;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {


        /*
            Runnable 예시
        */

        Runnable task01 = new Runnable(){
            @Override
            public void run(){
                System.out.println("익명클래스로 구현한 Task01 is running");
            }
        };
        Thread thread01 = new Thread(task01);
        thread01.start();


        Runnable task02 =
                () -> System.out.println("익명클래스로 구현한 Task01 is running");

        Thread thread02 = new Thread(task02);
        thread02.start();


        /*
            Supplier 예시
        */
        Supplier<String> supplier = () -> "Supplier 예시, 반환 데이터 존재";
        System.out.println(supplier.get());

        /*
            Consumer 예시
        */
        Consumer<String> consumer = s -> System.out.println(s);
        consumer.accept("Consumer 예시, 매개변수는 있지만 반환 데이터 없음");

        /*
            Consumer 예시, 메서드 레퍼런스
        */
        Consumer<String> consumer02 = System.out::println;

        /*
            Function 예시, String 타입의 매개변수를 대문자로 변환해주는 기능
        */
        //Function<String, String> function = s -> s.toUpperCase();
        Function<String, String> function = String::toUpperCase;
        System.out.println(function.apply("eddy"));


        /*
            Predicate 예시
        */
        Predicate<String> predicate = s -> s.startsWith("e");
        System.out.println("e로 시작하면 true 리턴 :"
                + predicate.test("eddy"));

    }
}
