package jogoxadrez;

public class Jogador {
    private String nome;
    private boolean branco;
    
    public Jogador(String nome, boolean branco){
        this.nome = nome;
        this.branco = branco;
        System.out.println("O objeto Jogador foi criado com sucesso!");
    }
    
    public String getNome() {
        return nome;
    }

    public boolean isBranco() {
        return branco;
    }
}
