package pecasxadrez;

public class Rei {
    private boolean branco;
    private boolean capturada;
    
    public Rei(boolean branco){
        this.branco = branco;
        this.capturada = false;
    }
    
    public void desenho(){ // método para impressão da peça no tabuleiro
        if(this.isBranco()){ // peça branca
            System.out.print("R");
        }
        else if(!this.isBranco()){ // peça preta
            System.out.print("r");
        }
    }
    
    public boolean checaMovimento(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino){
        int distanciaLinha = linhaDestino - linhaOrigem;
        int distanciaColuna = colunaDestino - colunaOrigem;
        
        /*
         * O rei apenas se movimenta em posições adjacentes à sua posição atual.
         * Portanto, a módulo da diferença entre as linhas e o módulo da diferença
         * entre as colunas devem ser menores ou iguais a 1.
        */
        if(Math.abs(distanciaLinha) == 1 || Math.abs(distanciaColuna) == 1){
            if(Math.abs(distanciaLinha) <= 1 && Math.abs(distanciaColuna) <= 1){
                return true;
            }
        }
        return false;
    }
    
    public boolean isBranco() {
        return branco;
    }
}
