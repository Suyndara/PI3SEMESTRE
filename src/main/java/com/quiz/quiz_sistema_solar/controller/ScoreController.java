package com.quiz.quiz_sistema_solar.controller;

import com.quiz.quiz_sistema_solar.model.Usuario;
import com.quiz.quiz_sistema_solar.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ScoreController {
    private final UsuarioService usuarioService;

    public ScoreController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/salvar-pontuacao")
    public String salvarPontuacao(@RequestParam String nome, @RequestParam Integer pontuacao) {
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setPontuacao(pontuacao);
        usuarioService.setUsuario(usuario);
        System.out.println("Enviou ao banco: " + nome + ", e pontuação: " + pontuacao);
        return "redirect:/pontuacao";  

    }

    @GetMapping("/pontuacao")
    public String mostrarScoreboard(Model model) {
        model.addAttribute("usuarios", usuarioService.getRanking());
        return "pontuacao";
    }

    @GetMapping("/score")
    public String redirecionarScoreboard() {
        return "redirect:/pontuacao";
    }
}