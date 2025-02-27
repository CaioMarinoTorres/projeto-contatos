package com.projetocontatos;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller

public class ContatosControle {

    private static final ArrayList<Contato> LISTA_CONTATOS = new ArrayList<Contato>();

    static {
        LISTA_CONTATOS.add(new Contato("01", "Caio Marino",     "+55 (66) 9 9659-9478"));
        LISTA_CONTATOS.add(new Contato("02", "Higor Marino",    "+55 (65) 9 9803-0224"));
        LISTA_CONTATOS.add(new Contato("03", "Roseli Marino",   "+55 (66) 9 9961-1134"));
        LISTA_CONTATOS.add(new Contato("04", "Julia Marino",    "+55 (19) 9 9987-7210"));
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/contatos")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("listar");
        mv.addObject("contatos", LISTA_CONTATOS);
        return mv;
    }

}
