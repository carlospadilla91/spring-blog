package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@Controller
public class RollDiceController {

    @GetMapping("/roll-dice")
    public String showDicePage(){
        return "roll-dice";
    }

    @GetMapping("/roll-dice/{n}")
    public String diceGuess(@PathVariable int n, Model model){
        int randomNumber = (int)(Math.random() * (7 - 1) + 1);
        model.addAttribute("randomNum", "The dice roll is " + randomNumber);
        model.addAttribute("userGuess", "Your guess was " + n);
        boolean correctGuess = randomNumber == n;
        boolean wrongGuess = randomNumber != n;
        model.addAttribute("correctGuess",  correctGuess);
        model.addAttribute("wrongGuess",  wrongGuess);
        return "/roll-dice";
    }
}
