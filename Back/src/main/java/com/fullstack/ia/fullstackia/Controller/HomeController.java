package com.fullstack.ia.fullstackia.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HomeController {

    @GetMapping
    public String home() {
        return "Bienvenue sur pistes mortelles, Click sur lancer pour générer votre enquète.";
    }

    @PostMapping(path = "/launch-story")
    public void launchStory() {

        // Ollama doit générer les personnages 4 suspect, 4 témoins et une victime
        // Cela doit etre retourner en format Json
        // le Json represente une table "personnage" qui doit renseigner le nom, prenom,
        // age, status 1:suspect, 2:témoin, 3:victime.

        // Ollam doit générer un lieu de crime


    }

}
