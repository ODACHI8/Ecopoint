class Residuo {
    private String nome;
    private double pontosPorKg;

    public Residuo(String nome, double pontosPorKg) {
        this.nome = nome;
        this.pontosPorKg = pontosPorKg;
    }

    public String getNome() {
        return nome;
    }

    public double calcularPontuacao(double quantidadeKg) {
        return quantidadeKg * pontosPorKg; // Pontos por kg reciclado
    }
}
