public class Usuario {
    private String nome;
    private String senha;
    private int nivel;
    private double pontos;

    public Usuario(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
        this.nivel = 1; // Nível inicial
        this.pontos = 0; // Pontos iniciais
    }

    public String getNome() {
        return nome;
    }

    public int getNivel() {
        return nivel;
    }

    public double getPontos() {
        return pontos;
    }

    // Método de autenticação
    public boolean autenticar(String senha) {
        return this.senha.equals(senha);
    }

    // Método para reciclar e ganhar pontos
    public void reciclar(Residuo residuo, double quantidadeKg) {
        double pontosGanhados = residuo.calcularPontuacao(quantidadeKg); // Pontos por quantidade reciclada
        this.pontos += pontosGanhados;

        System.out.println("Você reciclou " + quantidadeKg + " kg de " + residuo.getNome() + " e ganhou " + pontosGanhados + " pontos!");

        // Verificar progresso de nível
        verificarNivel();
    }

    private void verificarNivel() {
        int pontosParaProximoNivel = 50;

        // pontos suficientes para subir de nível
        while (pontos >= pontosParaProximoNivel) {
            nivel++;
            pontos -= pontosParaProximoNivel; // Subtrai apenas os pontos necessários para subir de nível
            System.out.println("Parabéns, " + nome + "! Você subiu para o nível " + nivel + "!");
        }
    }
}
