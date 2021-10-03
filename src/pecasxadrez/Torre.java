package pecasxadrez;

import jogoxadrez.Posicao;

public class Torre {
    private boolean cor;
    private Posicao posicao;
    private boolean capturada;
    
    public Torre(boolean cor, Posicao posicao){
        this.cor = cor;
        this.posicao = posicao;
        this.capturada = false;
    }
    
    public void desenho(){ // método para impressão da peça no tabuleiro
        if(!this.getCor()){ // false; peça branca
            System.out.print("T");
        }
        else if(this.getCor()){ // true; peça preta
            System.out.print("t");
        }
    }
    
    public boolean checaMovimento(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino){
        /* Para que a torre se movimente, ela deve se manter na mesma linha inicial
         * (apenas trocando de coluna) ou na mesma coluna inicial (apenas trocando
         * de linha).
        */
        
        if(linhaOrigem == linhaDestino){
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
    
    public boolean getCor() {
        return cor;
    }
}
