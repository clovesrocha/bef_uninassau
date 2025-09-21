package br.com.tripcritic.models.avaliacao;

import org.jetbrains.annotations.NotNull;

public record AvaliacaoResponse(Long id, String nomeUsuario, String nomePontoTuristico, Integer nota, String comentario, String dataAvaliacao) {
    public AvaliacaoResponse(@NotNull Avaliacao avaliacao) {
        this(avaliacao.getId(), avaliacao.getUsuario().getNome(), avaliacao.getPontoTuristico().getNome(), avaliacao.getNota(), avaliacao.getComentario(), avaliacao.getDataAvaliacao().toString());
    }
}
