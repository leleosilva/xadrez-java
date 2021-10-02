package pecasxadrez;

import jogoxadrez.Posicao;

public class Cavalo {
    private String cor;
    private Posicao posicao;
    private boolean capturada;
    
    public Cavalo(String cor, Posicao posicao){
        this.cor = cor;
        this.posicao = posicao;
        this.capturada = false;
    }
    
    public void desenho(){ // método para impressão da peça no tabuleiro
        if("branco".equals(this.getCor())){
            System.out.print("C");
        }
        else if("preto".equals(this.getCor())){
            System.out.print("c");
        }
    }
    
    public boolean checaMovimento(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino){
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
        if(Math.abs(distanciaLinha) == 1 && Math.abs(distanciaColuna) == 2){
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
