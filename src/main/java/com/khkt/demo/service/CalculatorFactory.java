package com.khkt.demo.service;

import java.util.HashMap;
import java.util.Map;

public class CalculatorFactory {

    static Map<String, String> REGISTRY = new HashMap<>();

    static {
        REGISTRY.put("+", "Add");
        REGISTRY.put("-", "Sub");
        REGISTRY.put("*", "Multiply");
        REGISTRY.put("/", "Divide");
    }

    public static Calculable create(String symbol) {
//        switch (symbol) {
//            case "+":
//                return new Add();
//            case "-":
//                return new Sub();
//            case "*":
//                return new Multiply();
//            case "/":
//                return new Divide();
//        }
//        return null;

        String className = REGISTRY.get(symbol);
        if (className != null) {
            try {
                Class clazz = Class.forName("com.khkt.demo.service." + className);
                return (Calculable) clazz.newInstance();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
        return new UnknownCalculator();
    }
}
