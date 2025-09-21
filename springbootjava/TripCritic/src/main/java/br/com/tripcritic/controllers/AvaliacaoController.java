package br.com.tripcritic.controllers;

import br.com.tripcritic.models.avaliacao.AvaliacaoRequest;
import br.com.tripcritic.models.avaliacao.AvaliacaoResponse;
import br.com.tripcritic.services.AvaliacaoService;
import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController {

    private final AvaliacaoService avaliacaoService;

    public AvaliacaoController(AvaliacaoService avaliacaoService) {
        this.avaliacaoService = avaliacaoService;
    }

    @PostMapping
    public ResponseEntity<AvaliacaoResponse> save(@RequestBody @Valid AvaliacaoRequest avaliacaoRequest, @NotNull UriComponentsBuilder uriBuilder) {
        var avaliacao = avaliacaoService.save(avaliacaoRequest);
        var uri = uriBuilder.path("/avaliacao/{id}").buildAndExpand(avaliacao.id()).toUri();
        return ResponseEntity.created(uri).body(avaliacao);
    }

    @GetMapping
    public ResponseEntity<Page<AvaliacaoResponse>> findAll(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(avaliacaoService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(avaliacaoService.findById(id));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<Page<AvaliacaoResponse>> findByUsuarioId(@PathVariable Long usuarioId, @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(avaliacaoService.findByUsuarioId(usuarioId, pageable));
    }

    @GetMapping("/ponto-turistico/{pontoTuristicoId}")
    public ResponseEntity<Page<AvaliacaoResponse>> findByPontoTuristicoId(@PathVariable Long pontoTuristicoId, @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(avaliacaoService.findByPontoTuristicoId(pontoTuristicoId, pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        avaliacaoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

