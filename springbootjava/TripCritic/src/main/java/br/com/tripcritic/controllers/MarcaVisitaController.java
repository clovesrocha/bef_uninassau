package br.com.tripcritic.controllers;

import br.com.tripcritic.models.marcaVisita.MarcaVisitaRequest;
import br.com.tripcritic.models.marcaVisita.MarcarVisitaResponse;
import br.com.tripcritic.services.MarcaVisitaService;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/marca-visita")
public class MarcaVisitaController {

    private final MarcaVisitaService marcaVisitaService;

    public MarcaVisitaController(MarcaVisitaService marcaVisitaService) {
        this.marcaVisitaService = marcaVisitaService;
    }

    @PostMapping
    public ResponseEntity<MarcarVisitaResponse> save(@RequestBody @Valid MarcaVisitaRequest marcaVisitaRequest, UriComponentsBuilder uriBuilder) {
        var marcarVisitaResponse = marcaVisitaService.save(marcaVisitaRequest);
        var uri = uriBuilder.path("/marca-visita/{id}").buildAndExpand(marcarVisitaResponse.id()).toUri();
        return ResponseEntity.created(uri).body(marcarVisitaResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarcarVisitaResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(marcaVisitaService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        marcaVisitaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<Page<MarcarVisitaResponse>> findByUsuarioId(@PathVariable Long usuarioId, Pageable pageable) {
        return ResponseEntity.ok(marcaVisitaService.findByUsuarioId(usuarioId, pageable));
    }

    @GetMapping("/ponto-turistico/{pontoTuristicoId}")
    public ResponseEntity<Page<MarcarVisitaResponse>> findByPontoTuristicoId(@PathVariable Long pontoTuristicoId, Pageable pageable) {
        return ResponseEntity.ok(marcaVisitaService.findByPontoTuristicoId(pontoTuristicoId, pageable));
    }

    @GetMapping
    public ResponseEntity<Page<MarcarVisitaResponse>> findAll(Pageable pageable) {
        return ResponseEntity.ok(marcaVisitaService.findAll(pageable));
    }

}
