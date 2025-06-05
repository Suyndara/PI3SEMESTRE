package com.quiz.quiz_sistema_solar.controller;

import com.quiz.quiz_sistema_solar.model.Pergunta;
import com.quiz.quiz_sistema_solar.model.RespostaUsuario;
import com.quiz.quiz_sistema_solar.service.PerguntaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/quiz")
@SessionAttributes({"pontuacao", "perguntaAtual", "perguntas", "respostas"})
public class QuizController {

    private final PerguntaService perguntaService;
    private int perguntaIndex = 0;

    public QuizController(PerguntaService perguntaService) {
        this.perguntaService = perguntaService;
    }

    @ModelAttribute("pontuacao")
    public Integer inicializarPontuacao() {
        return 0;
    }

    @ModelAttribute("respostas")
    public List<RespostaUsuario> inicializarRespostas() {
        return new ArrayList<>();
    }

    @GetMapping
    public String comecarQuiz(Model model) {
        List<Pergunta> todasPerguntas = perguntaService.getPerguntas();

        Collections.shuffle(todasPerguntas);

        List<Pergunta> perguntas;
        if (todasPerguntas.size() > 10) {
            perguntas = todasPerguntas.subList(0, 10);
        } else {
            perguntas = todasPerguntas;
        }

        perguntaIndex = 0;  
        model.addAttribute("pontuacao", 0);
        model.addAttribute("perguntas", perguntas);
        model.addAttribute("perguntaAtual", perguntas.get(perguntaIndex));
        model.addAttribute("posicaoAtual", perguntaIndex + 1); 

        return "quiz";
    }

    @PostMapping
    public String proximaPergunta(
            @ModelAttribute("respostas") List<RespostaUsuario> respostas,
            @RequestParam String resposta,
            @RequestParam Integer perguntaId,
            @ModelAttribute("pontuacao") Integer pontuacao,
            @ModelAttribute("perguntas") List<Pergunta> perguntas,
            Model model) {

        Pergunta perguntaAtual = perguntas.stream()
                .filter(p -> p.getId().equals(perguntaId))
                .findFirst()
                .orElse(null);

        if (perguntaAtual != null && perguntaAtual.getRespostaCorreta().equals(resposta)) {
            pontuacao++;
            model.addAttribute("pontuacao", pontuacao);
        }

        RespostaUsuario respostaUsuario = new RespostaUsuario();
        respostaUsuario.setPerguntaId(perguntaId);
        respostaUsuario.setResposta(resposta);
        respostas.add(respostaUsuario);

        perguntaIndex++;
        if (perguntaIndex < perguntas.size()) {
            model.addAttribute("perguntaAtual", perguntas.get(perguntaIndex));
            model.addAttribute("posicaoAtual", perguntaIndex + 1);
            return "quiz";
        } else {
            return "redirect:/resultado";
        }
    }
}

