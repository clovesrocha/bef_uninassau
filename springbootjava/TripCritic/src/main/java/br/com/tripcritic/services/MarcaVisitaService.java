package br.com.tripcritic.services;

import br.com.tripcritic.models.marcaVisita.MarcaVisita;
import br.com.tripcritic.models.marcaVisita.MarcaVisitaRequest;
import br.com.tripcritic.models.marcaVisita.MarcarVisitaResponse;
import br.com.tripcritic.repositories.MarcaVisitaRepository;
import br.com.tripcritic.repositories.PontoTuristicoRepository;
import br.com.tripcritic.repositories.UsuarioRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MarcaVisitaService {

    private final MarcaVisitaRepository marcaVisitaRepository;
    private final PontoTuristicoRepository pontoTuristicoRepository;
    private final UsuarioRepository usuarioRepository;

    public MarcaVisitaService(MarcaVisitaRepository marcaVisitaRepository, PontoTuristicoRepository pontoTuristicoRepository, UsuarioRepository usuarioRepository) {
        this.marcaVisitaRepository = marcaVisitaRepository;
        this.pontoTuristicoRepository = pontoTuristicoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public MarcarVisitaResponse save(@NotNull MarcaVisitaRequest marcaVisitaRequest) {
        var usuario = usuarioRepository.findById(marcaVisitaRequest.usuarioId()).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        var pontoTuristico = pontoTuristicoRepository.findById(marcaVisitaRequest.pontoTuristicoId()).orElseThrow(() -> new RuntimeException("Ponto turístico não encontrado"));
        MarcaVisita marcaVisita = new MarcaVisita(null, usuario, pontoTuristico, LocalDateTime.now());
        marcaVisitaRepository.save(marcaVisita);
        return new MarcarVisitaResponse(marcaVisita);
    }

    public void delete(Long id) {
        marcaVisitaRepository.findById(id).ifPresent(marcaVisitaRepository::delete);
    }

    public MarcarVisitaResponse findById(Long id) {
        MarcaVisita marcaVisita = marcaVisitaRepository.findById(id).orElseThrow(() -> new RuntimeException("Marcação de visita não encontrada"));
        return new MarcarVisitaResponse(marcaVisita);
    }

    public Page<MarcarVisitaResponse> findByUsuarioId(Long usuarioId, Pageable pageable) {
        return marcaVisitaRepository.findByUsuarioId(usuarioId, pageable).map(MarcarVisitaResponse::new);
    }

    public Page<MarcarVisitaResponse> findByPontoTuristicoId(Long pontoTuristicoId, Pageable pageable) {
        return marcaVisitaRepository.findByPontoTuristicoId(pontoTuristicoId, pageable).map(MarcarVisitaResponse::new);
    }

    public Page<MarcarVisitaResponse> findAll(Pageable pageable) {
        return marcaVisitaRepository.findAll(pageable).map(MarcarVisitaResponse::new);
    }
}

