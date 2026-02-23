import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private int id;
    private String nome;

    //2º Modificação: Adicionando uma lista de Posts ao usuário
    private List<Post> posts;

    private int contadorPosts;

    public Usuario(int id) {
        this.id = id;

        //1º Modificação: Criando um padrão para evitar repetição
        this.nome = "Usuário" + this.id;

        this.contadorPosts = 0;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    //Modificação: Retornar o total de posts do usuário.
    public int getTotalPosts(){
        return contadorPosts;
    }

    public int getTotalPosts(Usuario u){
        return u.contadorPosts;
    }

    //Modificação: Retornar a lista dos posts de cada usuário
    public List<Post> getListPosts(){
        return posts;
    }

    //3º modificação: Criação do método pra adicionar posts
    public void adicionarPost(Post p){
        posts.add(p);
        contadorPosts++;
    }

}