package br.com.tripcritic.models.pontoTuristico;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PontoTuristicoRequest(
        @NotBlank(message = "Nome é obrigatório")
        @Size(max = 120, message = "Nome deve ter no máximo 120 caracteres")
        String nome,
        @NotBlank(message = "Descrição é obrigatória")
        @Size(max = 1000, message = "Descrição deve ter no máximo 1000 caracteres")
        String descricao,
        @NotNull(message = "Latitude é obrigatória")
        @Min(value = -90, message = "Latitude mínima é -90")
        @Max(value = 90, message = "Latitude máxima é 90")
        Double latitude,
        @NotNull(message = "Longitude é obrigatória")
        @Min(value = -180, message = "Longitude mínima é -180")
        @Max(value = 180, message = "Longitude máxima é 180")
        Double longitude,
        @NotNull(message = "Categoria é obrigatória")
        Categoria categoria
) {
}
