package com.quiz.quiz_sistema_solar.service;

import com.quiz.quiz_sistema_solar.model.Pergunta;
import com.quiz.quiz_sistema_solar.repository.PerguntaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class QuizService {

    private final PerguntaRepository perguntaRepository;
    private List<Pergunta> perguntas;
    private int pontuacao;

    public QuizService(PerguntaRepository perguntaRepository) {
        this.perguntaRepository = perguntaRepository;
        this.pontuacao = 0;
        carregarPerguntas();
    }

    // Carrega todas as perguntas do banco
    private void carregarPerguntas() {
        perguntas = perguntaRepository.findAll();
    }

    // Retorna a próxima pergunta aleatória
    public Pergunta getProximaPergunta() {
        if (perguntas == null || perguntas.isEmpty()) {
            throw new IllegalStateException("Não há perguntas disponíveis");
        }

        Random random = new Random();
        int indiceAleatorio = random.nextInt(perguntas.size());
        return perguntas.get(indiceAleatorio);
    }

    // Verifica se a resposta está correta e atualiza a pontuação
    public void verificarResposta(String respostaUsuario, Pergunta perguntaAtual) {
        if (perguntaAtual.getRespostaCorreta().equalsIgnoreCase(respostaUsuario)) {
            pontuacao++;
        }
    }

    public int getPontuacao() {
        return pontuacao;
    }
}