package com.quiz.quiz_sistema_solar.service;

import com.quiz.quiz_sistema_solar.model.Pergunta;
import com.quiz.quiz_sistema_solar.repository.PerguntaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PerguntaService {
    private final PerguntaRepository perguntaRepository;

    public PerguntaService(PerguntaRepository perguntaRepository) {
        this.perguntaRepository = perguntaRepository;
    }

    public List<Pergunta> getPerguntas() {
        return perguntaRepository.findAll();
    }

    public Pergunta getPerguntaById(Long id) {
        return perguntaRepository.findById(id).orElse(null);
    }
}