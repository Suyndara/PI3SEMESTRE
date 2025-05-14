package com.quiz.quiz_sistema_solar.service;

import com.quiz.quiz_sistema_solar.model.Usuario;
import com.quiz.quiz_sistema_solar.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario setUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> getRanking() {
        return usuarioRepository.findAllByOrderByPontuacaoDesc();
    }
}