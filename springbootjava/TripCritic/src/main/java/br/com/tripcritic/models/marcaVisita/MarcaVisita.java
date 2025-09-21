package br.com.tripcritic.models.marcaVisita;

import br.com.tripcritic.models.pontoTuristico.PontoTuristico;
import br.com.tripcritic.models.Usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class MarcaVisita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "ponto_turistico_id")
    private PontoTuristico pontoTuristico;
    @Column(name = "data_visita")
    private LocalDateTime dataVisita = LocalDateTime.now();
}
