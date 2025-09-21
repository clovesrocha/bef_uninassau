package br.com.tripcritic.services;

import br.com.tripcritic.models.avaliacao.Avaliacao;
import br.com.tripcritic.models.avaliacao.AvaliacaoRequest;
import br.com.tripcritic.models.avaliacao.AvaliacaoResponse;
import br.com.tripcritic.repositories.AvaliacaoRepository;
import br.com.tripcritic.repositories.PontoTuristicoRepository;
import br.com.tripcritic.repositories.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AvaliacaoService {

    private final AvaliacaoRepository avaliacaoRepository;
    private final PontoTuristicoRepository pontoTuristicoRepository;
    private final UsuarioRepository usuarioRepository;

    public AvaliacaoService(AvaliacaoRepository avaliacaoRepository, PontoTuristicoRepository pontoTuristicoRepository, UsuarioRepository usuarioRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
        this.pontoTuristicoRepository = pontoTuristicoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public AvaliacaoResponse save(AvaliacaoRequest avaliacaoRequest) {
        var usuario = usuarioRepository.findById(avaliacaoRequest.usuarioId()).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        var pontoTuristico = pontoTuristicoRepository.findById(avaliacaoRequest.pontoTuristicoId()).orElseThrow(() -> new RuntimeException("Ponto turístico não encontrado"));
        Avaliacao avaliacao = new Avaliacao(null, usuario, pontoTuristico, avaliacaoRequest.nota(), avaliacaoRequest.comentario(), LocalDateTime.now());
        avaliacaoRepository.save(avaliacao);
        return new AvaliacaoResponse(avaliacao);
    }
    public AvaliacaoResponse findById(Long id) {
        Avaliacao avaliacao = avaliacaoRepository.findById(id).orElseThrow(() -> new RuntimeException("Avaliação não encontrada"));
        return new AvaliacaoResponse(avaliacao);
    }

    public void delete(Long id) {
        avaliacaoRepository.deleteById(id);
    }

    public Page<AvaliacaoResponse> findAll(Pageable pageable) {
        return avaliacaoRepository.findAll(pageable).map(AvaliacaoResponse::new);
    }
    public Page<AvaliacaoResponse> findByPontoTuristicoId(Long pontoTuristicoId, Pageable pageable) {
        return avaliacaoRepository.findByPontoTuristicoId(pontoTuristicoId, pageable).map(AvaliacaoResponse::new);
    }

    public Page<AvaliacaoResponse> findByUsuarioId(Long usuarioId, Pageable pageable) {
        return avaliacaoRepository.findByUsuarioId(usuarioId, pageable).map(AvaliacaoResponse::new);
    }


}
