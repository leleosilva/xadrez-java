package jogoxadrez;

public class Jogador {
    private String nome;
    private boolean cor;
    
    public Jogador(String nome, boolean cor){
        this.nome = nome;
        this.cor = cor;
    }
    
    public String getNome() {
        return nome;
    }

    public boolean isCor() {
        return cor;
    }
}
