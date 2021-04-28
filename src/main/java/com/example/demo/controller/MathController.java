package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MathController {

    @RequestMapping(path = "/add/{x}/and/{y}", method = RequestMethod.GET)
    @ResponseBody
    public String add(@PathVariable int x, @PathVariable int y){
        return x + " + " + y + " is " + (x + y);
    }

    @RequestMapping(path = "/subtract/{x}/from/{y}", method = RequestMethod.GET)
    @ResponseBody
    public String subtract(@PathVariable int x, @PathVariable int y){
        return y + " - " + x + " is " + (y - x);
    }

    @RequestMapping(path = "/multiply/{x}/and/{y}", method = RequestMethod.GET)
    @ResponseBody
    public String multiply(@PathVariable int x, @PathVariable int y){
        return x + " * " + y + " is " + (y * x);
    }

    @RequestMapping(path = "/divide/{x}/by/{y}", method = RequestMethod.GET)
    @ResponseBody
    public String divide(@PathVariable int x, @PathVariable int y){
        return x + " / " + y + " is " + (x / y);
    }
}
