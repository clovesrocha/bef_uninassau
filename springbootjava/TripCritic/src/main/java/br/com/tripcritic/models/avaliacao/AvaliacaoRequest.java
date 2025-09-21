package br.com.tripcritic.models.avaliacao;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AvaliacaoRequest(
        @NotNull(message = "usuarioId é obrigatório")
        Long usuarioId,
        @NotNull(message = "pontoTuristicoId é obrigatório")
        Long pontoTuristicoId,
        @NotNull(message = "nota é obrigatória")
        @Min(value = 1, message = "nota mínima é 1")
        @Max(value = 5, message = "nota máxima é 5")
        Integer nota,
        @Size(max = 500, message = "comentário deve ter no máximo 500 caracteres")
        String comentario
) {
}
