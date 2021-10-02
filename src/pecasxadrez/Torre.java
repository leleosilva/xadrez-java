package pecasxadrez;

import jogoxadrez.Posicao;

public class Torre {
    private String cor;
    private Posicao posicao;
    private boolean capturada;
    
    public Torre(String cor, Posicao posicao){
        this.cor = cor;
        this.posicao = posicao;
        this.capturada = false;
    }
    
    public void desenho(){ // método para impressão da peça no tabuleiro
        if("branco".equals(this.getCor())){
            System.out.print("T");
        }
        else if("preto".equals(this.getCor())){
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
    
    public String getCor() {
        return cor;
    }
}
