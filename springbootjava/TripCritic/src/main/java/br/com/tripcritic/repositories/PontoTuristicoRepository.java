package br.com.tripcritic.repositories;

import br.com.tripcritic.models.pontoTuristico.Categoria;
import br.com.tripcritic.models.pontoTuristico.PontoTuristico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.nio.channels.FileChannel;

public interface PontoTuristicoRepository extends JpaRepository<PontoTuristico, Long>{
    Page<PontoTuristico> findByCategoria(Categoria categoria, Pageable pageable);
}
