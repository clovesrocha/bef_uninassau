package br.com.tripcritic.models.marcaVisita;

public record MarcarVisitaResponse(Long id, String nomeUsuario, String nomePontoTuristico, String dataVisita) {
    public MarcarVisitaResponse(MarcaVisita marcarVisita) {
        this(marcarVisita.getId(), marcarVisita.getUsuario().getNome(), marcarVisita.getPontoTuristico().getNome(), marcarVisita.getDataVisita().toString());
    }
}
