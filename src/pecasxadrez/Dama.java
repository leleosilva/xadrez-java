package pecasxadrez;

import jogoxadrez.Posicao;

public class Dama {
    private boolean branco;
    private Posicao posicao;
    private boolean capturada;
    
    public Dama(boolean branco, Posicao posicao){
        this.branco = branco;
        this.posicao = posicao;
        this.capturada = false;
    }
    
    public void desenho(){ // método para impressão da peça no tabuleiro
        if(this.isBranco()){ // peça branca
            System.out.print("D");
        }
        else if(!this.isBranco()){ // peça preta
            System.out.print("d");
        }
    }
    
    public boolean checaMovimento(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino){
        int distanciaLinha = linhaDestino - linhaOrigem;
        int distanciaColuna = colunaDestino - colunaOrigem;
        /*
         * A dama pode se movimentar tanto como um bispo quanto como uma torre
        */
        
        // Movimentando-se como um bispo
        if(Math.abs(distanciaLinha) == Math.abs(distanciaColuna)){
            return true;
        }
        
        // Movimentando-se como uma torre
        else if(linhaOrigem == linhaDestino){
            return true;
        }
        else if(colunaOrigem == colunaDestino){
            return true;
        }
        return false;
    }

    public Posicao getPosicao() {
        return posicao;
    }
    
    public boolean isBranco() {
        return branco;
    }
}
