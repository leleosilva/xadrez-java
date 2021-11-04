package pecasxadrez;

public class Cavalo {
    private boolean branco;
    private boolean capturada;
    
    public Cavalo(boolean branco){
        this.branco = branco;
        this.capturada = false;
    }
    
    public void desenho(){ // método para impressão da peça no tabuleiro
        if(this.isBranco()){ // peça branca
            System.out.print("C");
        }
        else if(!this.isBranco()){ // peça preta
            System.out.print("c");
        }
    }
    
    public boolean checaMovimento(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino){
        int distanciaLinha = linhaDestino - linhaOrigem;
        int distanciaColuna = colunaDestino - colunaOrigem;
        
        /*
         * É possível observar um padrão na movimentação do cavalo. Sempre que
         * ele se move para uma nova posição, o módulo da distância entre as
         * linhas consideradas vale 2, enquanto o módulo da distância entre as
         * colunas vale 1, ou vice-versa!
        */
        if(Math.abs(distanciaLinha) == 2 && Math.abs(distanciaColuna) == 1){
            return true;
        }
        else if(Math.abs(distanciaLinha) == 1 && Math.abs(distanciaColuna) == 2){
            return true;
        }
        return false;
    }
    
    public boolean isBranco() {
        return branco;
    }
}
