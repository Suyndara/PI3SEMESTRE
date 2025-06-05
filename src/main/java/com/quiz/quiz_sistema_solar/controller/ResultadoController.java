package com.quiz.quiz_sistema_solar.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.quiz.quiz_sistema_solar.model.Pergunta;

@Controller
@RequestMapping("/resultado")
public class ResultadoController {

    @GetMapping
    public String mostrarResultado(
            @SessionAttribute("pontuacao") Integer pontuacao,
            @SessionAttribute(value = "perguntas", required = false) List<Pergunta> perguntas,
            Model model) {
        
        if (perguntas == null) {
            
            return "redirect:/";
        }
        
        model.addAttribute("pontuacao", pontuacao);
        model.addAttribute("perguntas", perguntas);
        return "resultado";
    }
}