package br.com.tripcritic.repositories;

import br.com.tripcritic.models.avaliacao.Avaliacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    Page<Avaliacao> findByPontoTuristicoId(Long pontoTuristicoId, Pageable pageable);

    Page<Avaliacao> findByUsuarioId(Long usuarioId, Pageable pageable);
}
