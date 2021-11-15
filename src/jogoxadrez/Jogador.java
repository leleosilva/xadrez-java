package jogoxadrez;

import pecasxadrez.Peca;

public class Jogador {
    private String nome;
    private boolean branco;
    private Peca[] pecas;
    
    public Jogador(String nome, boolean branco, Peca[] pecas){
        this.pecas = new Peca[16];
        this.nome = nome;
        this.branco = branco;
        this.pecas = pecas;
    }
    
    public void pecasCapturadas(){
        System.out.println();
        System.out.print("Peças capturadas:");
        for(int i = 0; i < 16; i++){
            if(pecas[i].isCapturada()){
                System.out.print(" ");
                pecas[i].desenho();
            }
        }
        System.out.println("\n");
    }
    
    public String getNome() {
        return nome;
    }

    public boolean isBranco() {
        return branco;
    }

    public Peca[] getPecas() {
        return pecas;
    }
}
