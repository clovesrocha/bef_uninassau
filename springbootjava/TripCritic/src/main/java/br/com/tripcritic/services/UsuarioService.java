package br.com.tripcritic.services;

import br.com.tripcritic.models.Usuario.Usuario;
import br.com.tripcritic.models.Usuario.UsuarioRequest;
import br.com.tripcritic.models.Usuario.UsuarioResponse;
import br.com.tripcritic.repositories.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioResponse save(UsuarioRequest usuarioRequest) {
        Usuario usuario = new Usuario(null, usuarioRequest.nome(), usuarioRequest.email(), usuarioRequest.senha(), LocalDateTime.now());
        usuarioRepository.save(usuario);
        return new UsuarioResponse(usuario);
    }

    public UsuarioResponse findById(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return new UsuarioResponse(usuario);
    }

    public Page<UsuarioResponse> findAll(Pageable pageable) {
        return usuarioRepository.findAll(pageable).map(UsuarioResponse::new);
    }
    public void delete(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
        } else {
            throw new RuntimeException("Usuário não encontrado");
        }
    }
}
