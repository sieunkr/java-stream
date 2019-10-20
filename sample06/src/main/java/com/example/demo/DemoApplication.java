package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        /*
        [AS-IS] 익명 클래스, 매개변수와 리턴값이 없는 함수
        */
        SimpleInterface functionalInterface01 = new SimpleInterface() {
            @Override
            public void action() {
                System.out.println("익명클래스, 안녕하세요!!");
            }
        };

        functionalInterface01.action();


        /*
        [TO-BE] 람다식, 매개변수와 리턴값이 없는 함수
        */
        SimpleInterface functionalInterface02 =
                () -> System.out.println("람다식, 안녕하세요!!");

        functionalInterface02.action();



        /*
        [AS-IS] 익명 클래스, 매개변수 O, 리턴 X
        */
        SimpleConsumerInterface consumerInterface01 = new SimpleConsumerInterface() {
            @Override
            public void accept(String s) {
                System.out.println("익명클래스, " + s + "님, 안녕하세요!!");
            }
        };

        consumerInterface01.accept("Eddy.Kim");


        /*
        [TO-BE] 람다식, 매개변수 O, 리턴 X
        */
        SimpleConsumerInterface consumerInterface02 =
                //(s) -> System.out.println("람다식, " + s + "님, 안녕하세요!!");
                s -> System.out.println("람다식, " + s + "님, 안녕하세요!!");

        consumerInterface02.accept("Eddy.Kim");

        /*
        [TO-BE] 람다식, 매개변수 O, 리턴 X, 중괄호를 생략하지 않은 경우...
        실행문이 하나라면 중괄호 생략 가능
        */
        SimpleConsumerInterface abc = s -> {
            System.out.println("람다식, " + s + "님, 안녕하세요!!");
        };
        
    }
}
