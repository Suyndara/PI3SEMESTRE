package com.quiz.quiz_sistema_solar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quiz.quiz_sistema_solar.model.Pergunta;

@Repository
public interface PerguntaRepository extends JpaRepository<Pergunta, Integer> {
}