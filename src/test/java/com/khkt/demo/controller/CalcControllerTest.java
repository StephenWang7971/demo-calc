package com.khkt.demo.controller;


import com.google.gson.Gson;
import com.khkt.demo.DemoApplication;
import com.khkt.demo.request.input.CalcForm;
import net.minidev.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {DemoApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CalcControllerTest {
    public TestRestTemplate testRestTemplate = new TestRestTemplate();

    @LocalServerPort
    private int port;

    private URL base;

    @Before
    public void setUp() {
        String url = String.format("http://localhost:%d/", port);
        try {
            this.base = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldReturnSumWhenAdd() {

        //given
        CalcForm form = new CalcForm();
        form.a = "3";
        form.b = "5";
        form.symbol = "+";

        //when
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Gson gson = new Gson();
        String json = gson.toJson(form);

        HttpEntity<String> entity = new HttpEntity<String>(json, headers);
        ResponseEntity<String> response = testRestTemplate.exchange(
                base.toString() + "/calc", HttpMethod.POST, entity, String.class);

        //then
        assertEquals(HttpStatus.OK, response.getStatusCode());

        String body = response.getBody();

        JSONObject results = gson.fromJson(body, JSONObject.class);

        System.out.println(results);

        int value = ((Double) results.get("value")).intValue();
        Assert.assertEquals(8, value);
    }

    @Test
    public void shouldReturnDiffWhenSub() {

        //given
        CalcForm form = new CalcForm();
        form.a = "9";
        form.b = "5";
        form.symbol = "-";

        //when
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Gson gson = new Gson();
        String json = gson.toJson(form);

        HttpEntity<String> entity = new HttpEntity<String>(json, headers);
        ResponseEntity<String> response = testRestTemplate.exchange(
                base.toString() + "/calc", HttpMethod.POST, entity, String.class);

        //then
        assertEquals(HttpStatus.OK, response.getStatusCode());

        String body = response.getBody();

        JSONObject results = gson.fromJson(body, JSONObject.class);

        System.out.println(results);

        int value = ((Double) results.get("value")).intValue();
        Assert.assertEquals(4, value);
    }


    @Test
    public void shouldReturnAccumulateWhenMutiply() {

        //given
        CalcForm form = new CalcForm();
        form.a = "3";
        form.b = "5";
        form.symbol = "*";

        //when
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Gson gson = new Gson();
        String json = gson.toJson(form);

        HttpEntity<String> entity = new HttpEntity<String>(json, headers);
        ResponseEntity<String> response = testRestTemplate.exchange(
                base.toString() + "/calc", HttpMethod.POST, entity, String.class);

        //then
        assertEquals(HttpStatus.OK, response.getStatusCode());

        String body = response.getBody();

        JSONObject results = gson.fromJson(body, JSONObject.class);

        System.out.println(results);

        int value = ((Double) results.get("value")).intValue();
        Assert.assertEquals(15, value);
    }


    @Test
    public void shouldReturnErrorWhenSymbolIsUnknown() {

        //given
        CalcForm form = new CalcForm();
        form.a = "3";
        form.b = "5";
        form.symbol = "^";

        //when
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Gson gson = new Gson();
        String json = gson.toJson(form);

        HttpEntity<String> entity = new HttpEntity<String>(json, headers);
        ResponseEntity<String> response = testRestTemplate.exchange(
                base.toString() + "/calc", HttpMethod.POST, entity, String.class);

        //then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

//        String body = response.getBody();
//
//        JSONObject results = gson.fromJson(body, JSONObject.class);
//
//        System.out.println(results);
//
//        int value = ((Double) results.get("value")).intValue();
//        Assert.assertEquals(15, value);
    }

}
