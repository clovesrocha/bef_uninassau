package br.com.tripcritic.models.Usuario;

public record UsuarioResponse(Long id, String nome, String email, String dataCriacao) {
    public UsuarioResponse(Usuario usuario){
        this(usuario.getId(), usuario.getNome(), usuario.getEmail(),  usuario.getDataCriacao().toString());
    }
}
