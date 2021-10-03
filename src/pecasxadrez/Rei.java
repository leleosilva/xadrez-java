package pecasxadrez;

import jogoxadrez.Posicao;

public class Rei {
    private boolean cor;
    private Posicao posicao;
    private boolean capturada;
    
    public Rei(boolean cor, Posicao posicao){
        this.cor = cor;
        this.posicao = posicao;
        this.capturada = false;
    }
    
    public void desenho(){ // método para impressão da peça no tabuleiro
        if(!this.getCor()){ // false; peça branca
            System.out.print("R");
        }
        else if(this.getCor()){ // true; peça preta
            System.out.print("r");
        }
    }
    
    public boolean checaMovimento(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino){
        int distanciaLinha = linhaDestino - linhaOrigem;
        int distanciaColuna = colunaDestino - colunaOrigem;
        
        /*
         * O rei apenas se movimenta em posições adjacentes à sua posição atual.
         * Portanto, a módulo da diferença entre as linhas e o módulo da diferença
         * entre as colunas devem ser menores ou iguais a 1.
        */
        return Math.abs(distanciaLinha) <= 1 && Math.abs(distanciaColuna) <= 1;
    }
    
    public Posicao getPosicao() {
        return posicao;
    }
    
    public boolean getCor() {
        return cor;
    }
}
