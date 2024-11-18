import java.util.List;

class PontoDeColeta {
    private String nome;
    private String endereco;
    private List<String> tiposAceitos;

    public PontoDeColeta(String nome, String endereco, List<String> tiposAceitos) {
        this.nome = nome;
        this.endereco = endereco;
        this.tiposAceitos = tiposAceitos;
    }

    public String getNome() {
        return nome;
    }
    public String getEndereco() { return endereco; }

    public boolean aceitaResiduo(Residuo residuo) {
        return tiposAceitos.contains(residuo.getNome().toLowerCase()); // Aceitar resíduos de forma case insensitive
    }
}
