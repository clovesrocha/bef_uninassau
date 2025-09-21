package br.com.tripcritic.models.marcaVisita;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record MarcaVisitaRequest(
        @NotNull(message = "usuarioId é obrigatório")
        Long usuarioId,
        @NotNull(message = "pontoTuristicoId é obrigatório")
        Long pontoTuristicoId,
        @NotBlank(message = "dataVisita é obrigatória")
        @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "dataVisita deve estar no formato ISO yyyy-MM-dd")
        String dataVisita
) {
}
