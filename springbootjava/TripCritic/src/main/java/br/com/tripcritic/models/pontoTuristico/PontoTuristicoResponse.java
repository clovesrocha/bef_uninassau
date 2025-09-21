package br.com.tripcritic.models.pontoTuristico;

public record PontoTuristicoResponse(Long id, String nome, String descricao, Double latitude, Double longitude, Categoria categoria) {
    public PontoTuristicoResponse(PontoTuristico pontoTuristico) {
        this(pontoTuristico.getId(), pontoTuristico.getNome(), pontoTuristico.getDescricao(), pontoTuristico.getLatitude(), pontoTuristico.getLongitude(), pontoTuristico.getCategoria());
    }
}
