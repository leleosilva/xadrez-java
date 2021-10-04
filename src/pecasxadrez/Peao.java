package pecasxadrez;

import jogoxadrez.Posicao;

public class Peao {
    private boolean branco;
    private Posicao posicao;
    private boolean capturada;
    
    public Peao(boolean branco, Posicao posicao){
        this.branco = branco;
        this.posicao = posicao;
        this.capturada = false;
    }
    
    public void desenho(){ // método para impressão da peça no tabuleiro
        if(this.isBranco()){ // peça branca
            System.out.print("P");
        }
        else if(!this.isBranco()){ // peça preta
            System.out.print("p");
        }
    }
    
    public boolean checaMovimento(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino){
        int distanciaLinha = linhaDestino - linhaOrigem;
        
        /*
         * O peão pode se movimentar duas casas quando se encontra em sua posição
         * inicial. Caso contrário, ele apenas poderá se movimentar uma casa.
         * Em ambos os casos, a coluna de origem e de destino devem ser iguais.
         * O peão não pode se movimentar para trás!
        */
        
        // Se o peão voltar para trás, o movimento é inválido!
        if(this.isBranco() && linhaOrigem > linhaDestino
                || !this.isBranco() && linhaOrigem < linhaDestino){
            return false;
        }
        
        // Posição inicial, o peão pode se mover duas posições
        if((this.isBranco() && linhaOrigem == 7)
                || !this.isBranco() && linhaOrigem == 2){
            if(Math.abs(distanciaLinha) <= 2 && colunaOrigem == colunaDestino){
                return true;
            }
        }
        else if(Math.abs(distanciaLinha) == 1 && colunaOrigem == colunaDestino){
            return true;
        }
        return false;
    }
    
    public Posicao getPosicao() {
        return posicao;
    }
    
    public boolean isBranco() {
        return branco;
    }
}
