package br.com.tripcritic.controllers;

import br.com.tripcritic.models.Usuario.UsuarioRequest;
import br.com.tripcritic.models.Usuario.UsuarioResponse;
import br.com.tripcritic.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> save(@RequestBody @Valid UsuarioRequest usuarioRequest, UriComponentsBuilder uriComponentsBuilder) {
        var usuarioResponse = usuarioService.save(usuarioRequest);
        var uri = uriComponentsBuilder.path("/usuario/{id}").buildAndExpand(usuarioResponse.id()).toUri();
        return ResponseEntity.created(uri).body(usuarioResponse);
    }

    @GetMapping
    public ResponseEntity<Page<UsuarioResponse>> findAll(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(usuarioService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
