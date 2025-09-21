package br.com.tripcritic.controllers;

import br.com.tripcritic.models.pontoTuristico.Categoria;
import br.com.tripcritic.models.pontoTuristico.PontoTuristicoRequest;
import br.com.tripcritic.models.pontoTuristico.PontoTuristicoResponse;
import br.com.tripcritic.services.PontoTuristicoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/ponto-turistico")
public class PontoTuristicoController {

    private final PontoTuristicoService pontoTuristicoService;

    public PontoTuristicoController(PontoTuristicoService pontoTuristicoService) {
        this.pontoTuristicoService = pontoTuristicoService;
    }

    @PostMapping
    public ResponseEntity<PontoTuristicoResponse> save(@RequestBody @Valid PontoTuristicoRequest pontoTuristicoRequest, UriComponentsBuilder uriBuilder) {
        var pontoTuristicoResponse = pontoTuristicoService.save(pontoTuristicoRequest);
        var uri = uriBuilder.path("/ponto-turistico/{id}").buildAndExpand(pontoTuristicoResponse.id()).toUri();
        return ResponseEntity.created(uri).body(pontoTuristicoResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PontoTuristicoResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(pontoTuristicoService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pontoTuristicoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<PontoTuristicoResponse>> findAll(Pageable pageable) {
        return ResponseEntity.ok(pontoTuristicoService.findAll(pageable));
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<Page<PontoTuristicoResponse>> findByCategoria(@PathVariable Categoria categoria, Pageable pageable) {
        return ResponseEntity.ok(pontoTuristicoService.findByCategoria(categoria, pageable));
    }
}
