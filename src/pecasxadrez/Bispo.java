package pecasxadrez;

import jogoxadrez.Posicao;

public class Bispo {
    private boolean branco;
    private Posicao posicao;
    private boolean capturada;
    
    public Bispo(boolean branco, Posicao posicao){
        this.branco = branco;
        this.posicao = posicao;
        this.capturada = false;
    }
    
    public void desenho(){ // método para impressão da peça no tabuleiro
        if(this.isBranco()){ // peça branca
            System.out.print("B");
        }
        else if(!this.isBranco()){ // peça preta
            System.out.print("b");
        }
    }
    
    public boolean checaMovimento(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino){
        int distanciaLinha = linhaDestino - linhaOrigem;
        int distanciaColuna = colunaDestino - colunaOrigem;
        
        /*
         * Para que o bispo possa se movimentar, o módulo da diferença entre
         * linhas de destino e origem e o módulo da diferença entre colunas de
         * destino e origem devem ser iguais.
        */
        return Math.abs(distanciaLinha) == Math.abs(distanciaColuna);
    }
    
    public Posicao getPosicao() {
        return posicao;
    }
    
    public boolean isBranco() {
        return branco;
    }
    
    
}
