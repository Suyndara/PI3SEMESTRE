package com.quiz.quiz_sistema_solar.controller;

import com.quiz.quiz_sistema_solar.model.Pergunta;
import com.quiz.quiz_sistema_solar.service.PerguntaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final PerguntaService perguntaService;

    public AdminController(PerguntaService perguntaService) {
        this.perguntaService = perguntaService;
    }

    @GetMapping
    public String listarPerguntas(Model model) {
        model.addAttribute("perguntas", perguntaService.getPerguntas());
        return "admin";
    }

    @GetMapping("/editar/{id}")
    public String editarPergunta(@PathVariable Integer id, Model model) {
        Pergunta pergunta = perguntaService.getPerguntaById(id.longValue());
        model.addAttribute("pergunta", pergunta);
        return "editar-pergunta";
    }

    @PostMapping("/salvar")
    public String salvarPergunta(@ModelAttribute Pergunta pergunta) {
        perguntaService.salvarPergunta(pergunta);
        return "redirect:/admin";
    }

    @GetMapping("/excluir/{id}")
    public String excluirPergunta(@PathVariable Integer id) {
        perguntaService.excluirPergunta(id.longValue());
        return "redirect:/admin";
    }

    @GetMapping("/nova")
    public String novaPergunta(Model model) {
        model.addAttribute("pergunta", new Pergunta());
        return "editar-pergunta";
    }
}
