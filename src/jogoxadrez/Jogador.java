package jogoxadrez;

import pecasxadrez.Peca;

public class Jogador {
    private String nome;
    private boolean branco;
    private Peca[] pecas;
    
    // Construtor da classe Jogador
    public Jogador(String nome, boolean branco, Peca[] pecas){
        this.pecas = new Peca[16];
        this.nome = nome;
        this.branco = branco;
        this.pecas = pecas;
    }
    
    // Método que imprime uma lista com as peças capturadas de um jogador
    public void pecasCapturadas(){
        System.out.println();
        System.out.print("Peças capturadas:");
        for(int i = 0; i < 16; i++){
            if(pecas[i].isCapturada()){
                System.out.print(" ");
                System.out.println(pecas[i].desenho());
            }
        }
        System.out.println("\n");
    }
    
    // Método getter que retorna o nome de um jogador
    public String getNome() {
        return nome;
    }
    
    // Método getter que retorna a cor de um jogador (branco = true; preto = false)
    public boolean isBranco() {
        return branco;
    }
    
    // Método getter que retorna o conjunto de peças de um jogador
    public Peca[] getPecas() {
        return pecas;
    }
}
