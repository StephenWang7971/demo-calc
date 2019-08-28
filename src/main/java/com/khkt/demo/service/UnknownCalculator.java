package com.khkt.demo.service;

import com.khkt.demo.exception.UnknownSymbolException;
import com.khkt.demo.request.output.Result;

public class UnknownCalculator implements Calculable {
    @Override
    public Result calc(Integer a, Integer b) {
        throw new UnknownSymbolException();
    }
}
