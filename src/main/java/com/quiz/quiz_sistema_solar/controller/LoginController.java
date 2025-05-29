package com.quiz.quiz_sistema_solar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    private final String ADMIN_EMAIL = "sistemasolar@quiz.com";
    private final String ADMIN_SENHA = "1234";

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String processarLogin(@RequestParam String email, @RequestParam String senha, HttpSession session) {
        if (ADMIN_EMAIL.equals(email) && ADMIN_SENHA.equals(senha)) {
            session.setAttribute("adminLogado", true);
            System.out.println("Usuário logado com sucesso!!");
            return "redirect:/admin/perguntas";

        } else {
            return "redirect:/login?erro=true";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}