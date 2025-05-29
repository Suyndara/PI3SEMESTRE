package com.quiz.quiz_sistema_solar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

<<<<<<< HEAD
import com.quiz.quiz_sistema_solar.model.Pergunta;

@Repository
public interface PerguntaRepository extends JpaRepository<Pergunta, Integer> {
}
=======
public interface PerguntaRepository extends JpaRepository<Pergunta, Long> {}
>>>>>>> b717b5e85822ab4b8b67dcaf45b12fdb47072cf7
