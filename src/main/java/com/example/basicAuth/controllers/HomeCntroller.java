package com.example.basicAuth.controllers;

import com.example.basicAuth.model.Player;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api")
@RestController
public class HomeCntroller {

    @RequestMapping("/players")
    @ResponseBody
    public List<Player> showPlayers(){
        Player richard = new Player();
        richard.setAlter(27);
        richard.setName("richard");

        Player toni = new Player();
        toni.setName("toni");
        toni.setAlter(26);

        List<Player> spieler = new ArrayList<>();
        spieler.add(richard);
        spieler.add(toni);

        System.out.println("spieler: " + spieler);
        return spieler;
    }
}
