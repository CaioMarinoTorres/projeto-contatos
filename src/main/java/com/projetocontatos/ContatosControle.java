package com.projetocontatos;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.UUID;

@Controller

public class ContatosControle {

    private static final ArrayList<Contato> LISTA_CONTATOS = new ArrayList<>();

    static {
        LISTA_CONTATOS.add(new Contato("01", "Caio Marino", "+55 (66) 9 9659-9478"));
        LISTA_CONTATOS.add(new Contato("02", "Higor Marino", "+55 (65) 9 9803-0224"));
        LISTA_CONTATOS.add(new Contato("03", "Roseli Marino", "+55 (66) 9 9961-1134"));
        LISTA_CONTATOS.add(new Contato("04", "Julia Marino", "+55 (19) 9 9987-7210"));
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

    @GetMapping("/contatos/novo")
    public ModelAndView novo() {
        ModelAndView mv = new ModelAndView("formulario");

        mv.addObject("contato", new Contato());

        return mv;
    }

    @PostMapping("/contatos")
    public String cadastrar(Contato contato) {
        String id = "0" + (LISTA_CONTATOS.size() + 1);
        contato.setId(id);
        LISTA_CONTATOS.add(contato);
        return "redirect:/contatos";
    }

    @GetMapping("/contatos/{id}/editar")
    public ModelAndView editar(@PathVariable String id) {
        ModelAndView mv = new ModelAndView("formulario");

        Contato contato = procurarContato(id);

        mv.addObject("contato", contato);

        return mv;
    }

    @PutMapping("/contatos/{id}")
    public String atualizar(Contato contato) {
        Integer indice = procurarIndiceContato(contato.getId());

        Contato contatoVelho = LISTA_CONTATOS.get(indice);

        LISTA_CONTATOS.remove(contatoVelho);
        LISTA_CONTATOS.add(indice, contato);

        return "redirect:/contatos";
    }

    @DeleteMapping("/contatos/{id}")
    public String remover (@PathVariable String id){
        Contato contato = procurarContato(id);

        LISTA_CONTATOS.remove(contato);

        return "redirect:/contatos";
    }

//    ----------------------------------------------------------Métodos Auxiliares

    public Contato procurarContato(String id) {
        Integer indice = procurarIndiceContato(id);

        if (indice != null) {
            Contato contato = LISTA_CONTATOS.get(indice);
            return contato;
        }
        return null;
    }

    public Integer procurarIndiceContato(String id) {
        for (int i = 0; i < LISTA_CONTATOS.size(); i++) {
            Contato contato = LISTA_CONTATOS.get(i);

            if (contato.getId().equals(id)) {
                return i;
            }
        }
        return null;
    }

}
