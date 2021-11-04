package pecasxadrez;

public class Bispo {
    private boolean branco;
    private boolean capturada;
    
    public Bispo(boolean branco){
        this.branco = branco;
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
        return Math.abs(distanciaLinha) == Math.abs(distanciaColuna) && Math.abs(distanciaLinha) != 0;
    }
    
    public boolean isBranco() {
        return branco;
    }
    
    
}
