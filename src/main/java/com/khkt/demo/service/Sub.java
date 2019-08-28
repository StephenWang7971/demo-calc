package com.khkt.demo.service;

import com.khkt.demo.request.output.Result;

public class Sub implements Calculable {
    @Override
    public Result calc(Integer a, Integer b) {
        return new Result(a - b);
    }
}
