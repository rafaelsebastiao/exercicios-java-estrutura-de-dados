import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Sistema de Blog - Versão com Gargalos de Performance
 * 
 * CONTEXTO: Sua startup de blog está crescendo rapidamente.
 * O sistema precisa mostrar o primeiro e último post de cada usuário
 * na página de perfil. Com 1.000+ usuários, a página está demorando
 * muito para carregar.
 * 
 * DESAFIO: Identifique os gargalos de complexidade e refatore o código
 * para melhorar a performance, justificando cada decisão.
 * 
 * DICAS PARA ANALISE:
 * - Qual a complexidade de tempo de cada método?
 * - Existem operações redundantes?
 * - As estruturas de dados são adequadas?
 * - Como o código se comporta com N usuários e M posts?
 */

public class SistemaPostsBlog {
    
    private List<Usuario> usuarios;
    private List<Post> todosOsPosts;
    
    public SistemaPostsBlog() {
        this.usuarios = new ArrayList<>();
        this.todosOsPosts = new ArrayList<>();
        
    }
    
    public void adicionarPost(int usuarioId, String conteudo) {
        Usuario usuario = getUsuarioByIndex(usuarioId);
        
        if (usuario != null) {
            Post novoPost = new Post(usuarioId, conteudo, System.currentTimeMillis());
            todosOsPosts.add(novoPost); // O(1)

            //Modificação: Adicionar post vinculado ao usuário
            usuario.adicionarPost(novoPost);
            // usuario.incrementarContadorPosts(); // O(1)
        }
    } // => O(N)
    
    //Modificação: Exclusão do método obterPrimeiroEULltimo post pois já possui implementação dentro da própria classe.
    

    //1º modificação: Método para retornar usuário pelo seu index
    public Usuario getUsuarioByIndex(int index){
        try{
            return usuarios.get(index);

        }catch (IndexOutOfBoundsException e){
            return null;
        }
    }

    //2º modificação: Alterar o método de adicionar usuário para desta forma não necessitar percorrer todo o array
    public void adicionarUsuario(Usuario usuario) {
        boolean existe = getUsuarioByIndex(usuario.getId()) != null;

        if(!existe){
            usuarios.add(usuario);
        }

    } 
    
    //Modificação: Exclusão do método contarPosts, já que cada usuário possui um atributo interno para isso.

    public String gerarRelatorioGeral() {
        StringBuilder relatorio = new StringBuilder();
        relatorio.append("=== RELATORIO DO BLOG ===\n\n");
        
        for (Usuario usuario : usuarios) { // O(N)
            relatorio.append("Usuario: ").append(usuario.getNome()).append("\n");
            
            int totalPosts = usuario.getTotalPosts();
            // O(1)
            relatorio.append("Total de posts: ").append(totalPosts).append("\n");
            
            PostsExtremos extremos = usuario.getPostsExtremos(); 

            if (extremos != null) {
                relatorio.append("Primeiro post: ")
                         .append(extremos.getPrimeiro().getConteudo()) // O(1)
                         .append("\n");
                relatorio.append("Ultimo post: ")
                         .append(extremos.getUltimo().getConteudo()) // O(1)
                         .append("\n");
            }
            relatorio.append("\n");
        }
        
        return relatorio.toString();
    } //=> O(N^2)

    public static void main(String[] args) {
        SistemaPostsBlog sistema = new SistemaPostsBlog();
        
        System.out.println("Criando usuarios e posts...");
        long inicio = System.currentTimeMillis();
        
        // Adiciona 1000 usuários
        for (int i = 1; i <= 1000; i++) {
            sistema.adicionarUsuario(new Usuario(i));
        }
        
        // Cada usuário faz 50 posts
        for (int i = 1; i <= 1000; i++) {
            for (int j = 1; j <= 50; j++) {
                sistema.adicionarPost(i, "Post " + j + " do usuário " + i);
                try {
                    Thread.sleep(1); // Simula passagem de tempo
                } catch (InterruptedException e) {}
            }
        }
        
        long fimCarga = System.currentTimeMillis();
        System.out.println("Tempo de carga: " + (fimCarga - inicio) + "ms");
        
        System.out.println("\nTestando operações críticas...");
        long inicioOp = System.currentTimeMillis();
        
        //Modificação: Utilizando o método interno da classe de Usuarios para pegar seus posts extremos.
        PostsExtremos extremos = sistema.getUsuarioByIndex(500).getPostsExtremos();
        
        int total = sistema.getUsuarioByIndex(500).getTotalPosts();

        long fimOp = System.currentTimeMillis();
        System.out.println("Tempo de operações: " + (fimOp - inicioOp) + "ms");
        System.out.println("Total de posts do usuário 500: " + total);
        
        
        System.out.println("\nGerando relatório completo...");
        long inicioRel = System.currentTimeMillis();
        
        String relatorio = sistema.gerarRelatorioGeral();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("relatorio.txt"))) {
            writer.write(relatorio);
            System.out.println("Relatorio criado com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
        }       
        
        long fimRel = System.currentTimeMillis();
        System.out.println("Tempo de geração relatorio: " + (fimRel - inicioRel) + "ms");
        
    }
}
