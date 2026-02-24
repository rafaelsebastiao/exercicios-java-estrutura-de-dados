import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private int id;
    private String nome;

    //2º Modificação: Adicionando uma lista de Posts ao usuário
    private List<Post> posts = new ArrayList<>();

    //Modificação: Adicionando uma lista de posts extremos (Primeiro e ultimo) ao usuário
    private PostsExtremos postsExtremos;


    private int contadorPosts;

    private int primeiraData;

    //Modificação: Variáveis para controle da data do primeiro post e da data do último post
    private long menorTimestamp;
    private long maiorTimestamp;

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
        if (contadorPosts == 0){
            //primeiro e último
            this.postsExtremos = new PostsExtremos(p, p);

            maiorTimestamp = p.getTimestamp();
            menorTimestamp = p.getTimestamp();

        }else if (p.getTimestamp() > maiorTimestamp){

            maiorTimestamp = p.getTimestamp();
            this.postsExtremos.setUltimo(p);        

        }

        else if (p.getTimestamp() < menorTimestamp){
            menorTimestamp = p.getTimestamp();
            this.postsExtremos = new PostsExtremos(p, p);
        }
        

        posts.add(p);

        contadorPosts++;
    }


    //Modificação: criação de get para retorno dos posts extremos (primeiro e último)
    public PostsExtremos getPostsExtremos(){
        return this.postsExtremos;
    }

}