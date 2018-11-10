package com.example.basicAuth.controllers;

import com.example.basicAuth.model.Player;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ToniController {

    @RequestMapping("/toni")
    @ResponseBody
    public Player player(){
        Player toni = new Player();
        toni.setName("toni");
        toni.setAlter(26);
        return toni;
    }
}
