package com.quiz.quiz_sistema_solar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quiz.quiz_sistema_solar.model.Pergunta;
import com.quiz.quiz_sistema_solar.service.PerguntaService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin/perguntas")
public class AdminPerguntaController {

    private final PerguntaService perguntaService;

    public AdminPerguntaController(PerguntaService perguntaService) {
        this.perguntaService = perguntaService;
    }

    @GetMapping("/nova")
    public String novaPerguntaForm(Model model, HttpSession session) {
        if (session.getAttribute("adminLogado") == null) {
            return "redirect:/login";
        }
        model.addAttribute("pergunta", new Pergunta());
        return "admin/nova-pergunta";
    }

    @PostMapping("/nova")
    public String salvarNovaPergunta(@ModelAttribute Pergunta pergunta, HttpSession session) {
        if (session.getAttribute("adminLogado") == null) {
            return "redirect:/login";
        }
        perguntaService.salvarPergunta(pergunta);
        return "redirect:/admin/perguntas";
    }

    @GetMapping
    public String listarPerguntas(Model model, HttpSession session) {
        if (session.getAttribute("adminLogado") == null) {
            return "redirect:/login";
        }
        model.addAttribute("perguntas", perguntaService.getPerguntas());
        return "admin/lista-perguntas";
    }

    @GetMapping("/editar/{id}")
    public String editarPerguntaForm(@PathVariable Integer id, Model model, HttpSession session) {
        if (session.getAttribute("adminLogado") == null) {
            return "redirect:/login";
        }
        Pergunta pergunta = perguntaService.getPerguntaById(id);
        if (pergunta == null) {
            return "redirect:/admin/perguntas";
        }
        model.addAttribute("pergunta", pergunta);
        return "admin/editar-pergunta";
    }

    @PostMapping("/editar/{id}")
    public String salvarEdicao(@PathVariable Integer id, @ModelAttribute Pergunta pergunta, HttpSession session) {
        if (session.getAttribute("adminLogado") == null) {
            return "redirect:/login";
        }
        pergunta.setId(id);
        perguntaService.atualizarPergunta(pergunta);
        return "redirect:/admin/perguntas";
    }

    @GetMapping("/remover/{id}")
    public String excluirPergunta(@PathVariable Integer id, HttpSession session) {
        if (session.getAttribute("adminLogado") == null) {
            return "redirect:/login";
        }
        perguntaService.excluirPergunta(id);
        return "redirect:/admin/perguntas";
    }
}