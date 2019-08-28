package com.khkt.demo.controller;

import com.google.gson.Gson;
import com.khkt.demo.exception.UnknownSymbolException;
import com.khkt.demo.request.input.CalcForm;
import com.khkt.demo.request.output.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
public class CalcController {

    @PostMapping("/calc")
    @ResponseBody
    public ResponseEntity calc(@RequestBody @Valid CalcForm calcForm) {
        Gson gson = new Gson();
        try {
            Result result = calcForm.calc();
            return new ResponseEntity(gson.toJson(result), HttpStatus.OK);
        } catch (UnknownSymbolException e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
