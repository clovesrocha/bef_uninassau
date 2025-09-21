package br.com.tripcritic.models.pontoTuristico;

import lombok.Getter;

@Getter
public enum Categoria {
    NATUREZA("Natureza"),
    HISTORICO("Histórico"),
    URBANO("Urbano"),
    CULTURAL("Cultural"),
    AVENTURA("Aventura");

    private final String descricao;

    Categoria(String descricao) {
        this.descricao = descricao;
    }

}
