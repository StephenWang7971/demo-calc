package com.khkt.demo.request.input;

import com.khkt.demo.request.output.Result;
import com.khkt.demo.service.Calculable;
import com.khkt.demo.service.CalculatorFactory;
import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class CalcForm {
    //计算因子a
    @Pattern(regexp = "\\d", message = "请输入数字")
    public String a;

    //计算因子b
    @Pattern(regexp = "\\d", message = "请输入数字")
    public String b;

    //符号
    public String symbol;

//    public Result calc() {
//        switch (symbol) {
//            case "+":
//                return new Result(a + b);
//            case "-":
//                return new Result(a - b);
//            case "*":
//                return new Result(a * b);
//                //这不利于扩展
//        }
//
//        return new Result(0);       //这是一个bug
//    }

    public Result calc() {
        Calculable calculable = CalculatorFactory.create(symbol);
        return calculable.calc(Integer.parseInt(a), Integer.parseInt(b));
    }
}
