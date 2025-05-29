package com.quiz.quiz_sistema_solar.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.quiz.quiz_sistema_solar.model.Pergunta;
import com.quiz.quiz_sistema_solar.repository.PerguntaRepository;

@Service
public class PerguntaService {
    private final PerguntaRepository perguntaRepository;

    public PerguntaService(PerguntaRepository perguntaRepository) {
        this.perguntaRepository = perguntaRepository;
    }

    public List<Pergunta> getPerguntas() {
        return perguntaRepository.findAll();
    }

    public Pergunta getPerguntaById(Integer id) {
        return perguntaRepository.findById(id).orElse(null);
    }

    public Pergunta salvarPergunta(Pergunta pergunta) {
        return perguntaRepository.save(pergunta);
    }

    public void atualizarPergunta(Pergunta pergunta) {
        perguntaRepository.save(pergunta);
    }

    public void excluirPergunta(int id) {
        perguntaRepository.deleteById(id);
    }
}