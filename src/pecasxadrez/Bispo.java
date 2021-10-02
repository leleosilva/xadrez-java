package pecasxadrez;

import jogoxadrez.Posicao;

public class Bispo {
    private String cor;
    private Posicao posicao;
    private boolean capturada;
    
    public Bispo(String cor, Posicao posicao){
        this.cor = cor;
        this.posicao = posicao;
        this.capturada = false;
    }
    
    public void desenho(){ // método para impressão da peça no tabuleiro
        if("branco".equals(this.getCor())){
            System.out.print("B");
        }
        else if("preto".equals(this.getCor())){
            System.out.print("b");
        }
    }
    
    public boolean checaMovimento(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino){
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
    
    public String getCor() {
        return cor;
    }
    
    
}
