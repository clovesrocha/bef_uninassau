package br.com.tripcritic.services;

import br.com.tripcritic.models.pontoTuristico.Categoria;
import br.com.tripcritic.models.pontoTuristico.PontoTuristico;
import br.com.tripcritic.models.pontoTuristico.PontoTuristicoRequest;
import br.com.tripcritic.models.pontoTuristico.PontoTuristicoResponse;
import br.com.tripcritic.repositories.PontoTuristicoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PontoTuristicoService {
    private final PontoTuristicoRepository pontoTuristicoRepository;

    public PontoTuristicoService(PontoTuristicoRepository pontoTuristicoRepository) {
        this.pontoTuristicoRepository = pontoTuristicoRepository;
    }

    public PontoTuristicoResponse save(PontoTuristicoRequest pontoTuristicoRequest) {
        PontoTuristico pontoTuristico = new PontoTuristico(null, pontoTuristicoRequest.nome(), pontoTuristicoRequest.descricao(), pontoTuristicoRequest.latitude(), pontoTuristicoRequest.longitude(), pontoTuristicoRequest.categoria());
        pontoTuristicoRepository.save(pontoTuristico);
        return new PontoTuristicoResponse(pontoTuristico);
    }

    public PontoTuristicoResponse findById(Long id) {
        PontoTuristico pontoTuristico = pontoTuristicoRepository.findById(id).orElseThrow();
        return new PontoTuristicoResponse(pontoTuristico);
    }

    public void delete(Long id) {
        pontoTuristicoRepository.findById(id).ifPresent(pontoTuristicoRepository::delete);
    }

    public Page<PontoTuristicoResponse> findAll(Pageable pageable) {
        return pontoTuristicoRepository.findAll(pageable).map(PontoTuristicoResponse::new);
    }

    public Page<PontoTuristicoResponse> findByCategoria(Categoria categoria, Pageable pageable) {
        return pontoTuristicoRepository.findByCategoria(categoria, pageable).map(PontoTuristicoResponse::new);
    }
}
