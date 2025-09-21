package br.com.tripcritic.repositories;

import br.com.tripcritic.models.marcaVisita.MarcaVisita;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.nio.channels.FileChannel;

public interface MarcaVisitaRepository extends JpaRepository<MarcaVisita, Long> {
    Page<MarcaVisita> findByUsuarioId(Long usuarioId, Pageable pageable);
    Page<MarcaVisita> findByPontoTuristicoId(Long pontoTuristicoId, Pageable pageable);
}
