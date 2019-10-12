package com.example.demo;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

@Repository
public class CoffeeRepository {

    private final HashMap<String, Coffee> coffeeHashMap = new HashMap<>();

    @PostConstruct
    private void init(){
        coffeeHashMap.put("mocha", Coffee.builder().name("mocha").milk(true).price(1700).build());
        coffeeHashMap.put("latte", Coffee.builder().name("latte").milk(true).price(1500).build());
        coffeeHashMap.put("americano", Coffee.builder().name("americano").milk(false).price(900).build());
    }

    public List<Coffee> findAllOrderByPrice(){
        ArrayList<Coffee> coffeeArrayList = new ArrayList<>(coffeeHashMap.values());

        /*
        coffeeArrayList.sort(new Comparator<Coffee>() {
            @Override
            public int compare(Coffee o1, Coffee o2) {
                if(o1.getPrice() > o2.getPrice()){
                    return 1;
                }
                else if(o1.getPrice() < o2.getPrice()){
                    return -1;
                }
                else{
                    return 0;
                }
            }
        });
        */

        /*
        coffeeArrayList.sort((o1, o2) -> {
            if(o1.getPrice() > o2.getPrice()){
                return 1;
            }
            else if(o1.getPrice() < o2.getPrice()){
                return -1;
            }
            else{
                return 0;
            }
        });
        */

        coffeeArrayList.sort(Comparator.comparing(Coffee::getPrice));

        return coffeeArrayList;
    }

    public Coffee findByName(String name){
        return coffeeHashMap.get(name);
    }
}
