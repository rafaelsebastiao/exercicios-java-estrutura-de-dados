    public class PostsExtremos {
        private Post primeiro;
        private Post ultimo;
        
        public PostsExtremos(Post primeiro, Post ultimo) {
            this.primeiro = primeiro;
            this.ultimo = ultimo;
        }
        
        public Post getPrimeiro() { return primeiro; }
        public Post getUltimo() { return ultimo; }
    }
