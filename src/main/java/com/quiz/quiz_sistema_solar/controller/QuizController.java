package com.quiz.quiz_sistema_solar.controller;

import com.quiz.quiz_sistema_solar.model.Pergunta;
import com.quiz.quiz_sistema_solar.model.RespostaUsuario;
import com.quiz.quiz_sistema_solar.service.PerguntaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
        List<Pergunta> perguntas = perguntaService.getPerguntas();
        perguntaIndex = 0;
        model.addAttribute("pontuacao", 0); // Reinicia a pontuação
        model.addAttribute("perguntaAtual", perguntas.get(perguntaIndex));
        model.addAttribute("perguntas", perguntas);
        return "quiz";
    }

    @PostMapping
    public String proximaPergunta(
        @ModelAttribute("respostas") List<RespostaUsuario> respostas,
        @RequestParam String resposta,
        @RequestParam Integer perguntaId,  // Alterado para Integer
        @ModelAttribute("pontuacao") Integer pontuacao,
        @ModelAttribute("perguntas") List<Pergunta> perguntas,
        Model model) {

        // Correção da filtragem
        Pergunta perguntaAtual = perguntas.stream()
                .filter(p -> p.getId().equals(perguntaId))  // Agora ambos são Integer
                .findFirst()
                .orElse(null);

        // LOGS DE DEPURAÇÃO - ADICIONE AQUI
        System.out.println("=== DEBUG ===");
        System.out.println("Resposta do usuário: " + resposta);
        System.out.println("Resposta correta: " + perguntaAtual.getRespostaCorreta());
        System.out.println("Comparação: " + perguntaAtual.getRespostaCorreta().equals(resposta));
        System.out.println("Tipo da resposta do usuário: " + resposta.getClass());
        System.out.println("Tipo da resposta correta: " + perguntaAtual.getRespostaCorreta().getClass());
        System.out.println("=============");        
        System.out.println("Pergunta ID: " + perguntaId);
        System.out.println("Texto da pergunta: " + perguntaAtual.getTexto());
        System.out.println("Alternativas: A) " + perguntaAtual.getAlternativaA() 
            + " B) " + perguntaAtual.getAlternativaB()
            + " C) " + perguntaAtual.getAlternativaC()
            + " D) " + perguntaAtual.getAlternativaD());
        System.out.println("=============");               

        if (perguntaAtual != null && perguntaAtual.getRespostaCorreta().equals(resposta)) {
            pontuacao++;
            model.addAttribute("pontuacao", pontuacao);
        }

        // Armazena a resposta
        RespostaUsuario respostaUsuario = new RespostaUsuario();
        respostaUsuario.setPerguntaId(perguntaId);
        respostaUsuario.setResposta(resposta);
        respostas.add(respostaUsuario);

        // Próxima pergunta ou resultado final
        perguntaIndex++;
        if (perguntaIndex < perguntas.size()) {
            model.addAttribute("perguntaAtual", perguntas.get(perguntaIndex));
            return "quiz";
        } else {
            return "redirect:/resultado";
        }
    }
}