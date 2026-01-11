package com.company.company.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class welc {

    @GetMapping
    public String display(){
        return "this is the landging page";
    }

}
