package com.example.demo;

//TODO: Function 으로 대체 가능
@Deprecated
@FunctionalInterface
public interface CustomFunction<T, R> {
    R apply(T t);
}