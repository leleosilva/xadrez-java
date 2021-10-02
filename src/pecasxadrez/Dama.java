package pecasxadrez;

import jogoxadrez.Posicao;

public class Dama {
    private String cor;
    private Posicao posicao;
    private boolean capturada;
    
    public Dama(String cor, Posicao posicao){
        this.cor = cor;
        this.posicao = posicao;
        this.capturada = false;
    }
    
    public void desenho(){ // método para impressão da peça no tabuleiro
        if("branco".equals(this.getCor())){
            System.out.print("D");
        }
        else if("preto".equals(this.getCor())){
            System.out.print("d");
        }
    }
    
    public boolean checaMovimento(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino){
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
    
    public String getCor() {
        return cor;
    }
}
