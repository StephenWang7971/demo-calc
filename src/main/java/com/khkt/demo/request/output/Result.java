package com.khkt.demo.request.output;

import lombok.Data;

@Data
public class Result {
    public int value;

    public Result(int v) {
        this.value = v;
    }
}
