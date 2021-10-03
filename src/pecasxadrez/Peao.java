package pecasxadrez;

import jogoxadrez.Posicao;

public class Peao {
    private boolean cor;
    private Posicao posicao;
    private boolean capturada;
    
    public Peao(boolean cor, Posicao posicao){
        this.cor = cor;
        this.posicao = posicao;
        this.capturada = false;
    }
    
    public void desenho(){ // método para impressão da peça no tabuleiro
        if(!this.getCor()){ // false; peça branca
            System.out.print("P");
        }
        else if(this.getCor()){ // true; peça preta
            System.out.print("p");
        }
    }
    
    public boolean checaMovimento(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino){
        int distanciaLinha = linhaDestino - linhaOrigem;
        
        /*
         * O peão pode se movimentar duas casas quando se encontra em sua posição
         * inicial. Caso contrário, ele apenas poderá se movimentar uma casa.
         * Em ambos os casos, a coluna de origem e de destino devem ser iguais.
         * O peão não pode se movimentar para trás!
        */
        
        // Se o peão voltar para trás, o movimento é inválido!
        if(!this.getCor() && linhaOrigem > linhaDestino
                || this.getCor() && linhaOrigem < linhaDestino){
            return false;
        }
        
        // Posição inicial, o peão pode se mover duas posições
        if((!this.getCor() && linhaOrigem == 7)
                || this.getCor() && linhaOrigem == 2){
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
    
    public boolean getCor() {
        return cor;
    }
}
