    public class PostsExtremos {
        private Post primeiro;
        private Post ultimo;
        
        public PostsExtremos(Post primeiro, Post ultimo) {
            this.primeiro = primeiro;
            this.ultimo = ultimo;
        }
        
        public Post getPrimeiro() { return primeiro; }
        public Post getUltimo() { return ultimo; }
        

        //Modificação: Adição de set para modificação do primeiro e ultimo post
        public void setPrimeiro(Post p){ this.primeiro = p;}
        public void setUltimo(Post p) { this.ultimo = p; }
    }
