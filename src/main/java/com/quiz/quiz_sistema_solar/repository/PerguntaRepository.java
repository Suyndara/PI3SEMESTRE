package com.quiz.quiz_sistema_solar.repository;

import com.quiz.quiz_sistema_solar.model.Pergunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerguntaRepository extends JpaRepository<Pergunta, Long> {
}