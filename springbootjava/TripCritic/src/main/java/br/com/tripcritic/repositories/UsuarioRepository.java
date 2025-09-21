package br.com.tripcritic.repositories;

import br.com.tripcritic.models.Usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
