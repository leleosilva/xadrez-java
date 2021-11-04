package pecasxadrez;

public class Dama {
    private boolean branco;
    private boolean capturada;
    
    public Dama(boolean branco){
        this.branco = branco;
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
        if(Math.abs(distanciaLinha) == Math.abs(distanciaColuna) && Math.abs(distanciaLinha) != 0){
            return true;
        }
        
        // Movimentando-se como uma torre
        else if(linhaOrigem == linhaDestino && colunaOrigem != colunaDestino){
            return true;
        }
        // Movimentando-se verticalmente
        else if(colunaOrigem == colunaDestino && linhaOrigem != linhaDestino){
            return true;
        }
        return false;
    }
    
    public boolean isBranco() {
        return branco;
    }
}
