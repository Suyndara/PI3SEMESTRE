package com.quiz.quiz_sistema_solar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        // Lógica para lidar com diferentes tipos de erros
        return "redirect:/"; // Redireciona para a página inicial em caso de erro
    }
}