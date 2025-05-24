
package com.quiz.quiz_sistema_solar.controller;

import com.quiz.quiz_sistema_solar.model.Pergunta;
import com.quiz.quiz_sistema_solar.service.PerguntaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private PerguntaService perguntaService;

    @GetMapping("/perguntas")
    public String listarPerguntas(Model model) {
        model.addAttribute("perguntas", perguntaService.getPerguntas());
        return "admin/perguntas";
    }

    @GetMapping("/perguntas/novo")
    public String novaPerguntaForm(Model model) {
        model.addAttribute("pergunta", new Pergunta());
        return "admin/formPergunta";
    }

    @PostMapping("/perguntas")
    public String salvarPergunta(@ModelAttribute Pergunta pergunta) {
        perguntaService.salvarPergunta(pergunta);
        return "redirect:/admin/perguntas";
    }

    @GetMapping("/perguntas/editar/{id}")
    public String editarPerguntaForm(@PathVariable Long id, Model model) {
        Pergunta pergunta = perguntaService.getPerguntaById(id);
        if (pergunta == null) {
            return "redirect:/admin/perguntas";
        }
        model.addAttribute("pergunta", pergunta);
        return "admin/formPergunta";
    }

    @PostMapping("/perguntas/editar/{id}")
    public String atualizarPergunta(@PathVariable Long id, @ModelAttribute Pergunta pergunta) {
        pergunta.setId(id);
        perguntaService.salvarPergunta(pergunta);
        return "redirect:/admin/perguntas";
    }

    @GetMapping("/perguntas/excluir/{id}")
    public String excluirPergunta(@PathVariable Long id) {
        perguntaService.excluirPergunta(id);
        return "redirect:/admin/perguntas";
    }
}


/*package com.quiz.quiz_sistema_solar.controller;

import com.quiz.quiz_sistema_solar.model.Pergunta;
import com.quiz.quiz_sistema_solar.service.PerguntaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
private PerguntaService perguntaService;

@GetMapping("/perguntas")
public String listarPerguntas(Model model) {
    model.addAttribute("perguntas", perguntaService.getPerguntas());
    return "admin/perguntas";
}

@GetMapping("/perguntas/novo")
public String novaPerguntaForm(Model model) {
    model.addAttribute("pergunta", new Pergunta());
    return "admin/formPergunta";
}

@PostMapping("/perguntas")
public String salvarPergunta(@ModelAttribute Pergunta pergunta) {
    perguntaService.salvarPergunta(pergunta);
    return "redirect:/admin/perguntas";
}

@GetMapping("/perguntas/editar/{id}")
public String editarPerguntaForm(@PathVariable Long id, Model model) {
    Pergunta pergunta = perguntaService.getPerguntaById(id);
    if (pergunta == null) {
        return "redirect:/admin/perguntas";
    }
    model.addAttribute("pergunta", pergunta);
    return "admin/formPergunta";
}

@PostMapping("/perguntas/editar/{id}")
public String atualizarPergunta(@PathVariable Long id, @ModelAttribute Pergunta pergunta) {
    pergunta.setId(id);
    perguntaService.salvarPergunta(pergunta);
    return "redirect:/admin/perguntas";
}

@GetMapping("/perguntas/excluir/{id}")
public String excluirPergunta(@PathVariable Long id) {
    perguntaService.excluirPergunta(id);
    return "redirect:/admin/perguntas";
}}*/