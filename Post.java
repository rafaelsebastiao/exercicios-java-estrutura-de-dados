public class Post {
    //Remoção do atributo de id, visto que cada usuário já possui sua própria lista de posts.
    private int usuarioId;
    private String conteudo;
    private long timestamp;

    public Post(int usuarioId, String conteudo, long timestamp) {
        this.usuarioId = usuarioId;
        this.conteudo = conteudo;
        this.timestamp = timestamp;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public String getConteudo() {
        return conteudo;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
