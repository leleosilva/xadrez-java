package pecasxadrez;

public class Rei extends Peca {
    
    public Rei(boolean branco){
        super(branco);
    }
    
    @Override
    public void desenho(){ // método para impressão da peça no tabuleiro
        if(this.isBranco()){ // peça branca
            System.out.print("R");
        }
        else if(!this.isBranco()){ // peça preta
            System.out.print("r");
        }
    }
    
    @Override
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
}
