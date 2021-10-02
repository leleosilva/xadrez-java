package pecasxadrez;

import jogoxadrez.Posicao;

public class Peao {
    private String cor;
    private Posicao posicao;
    private boolean capturada;
    
    public Peao(String cor, Posicao posicao){
        this.cor = cor;
        this.posicao = posicao;
        this.capturada = false;
    }
    
    public void desenho(){ // método para impressão da peça no tabuleiro
        if("branco".equals(this.getCor())){
            System.out.print("P");
        }
        else if("preto".equals(this.getCor())){
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
        if("branco".equals(this.getCor()) && linhaOrigem > linhaDestino
                || "preto".equals(this.getCor()) && linhaOrigem < linhaDestino){
            return false;
        }
        
        // Posição inicial, o peão pode se mover duas posições
        if(("branco".equals(this.getCor()) && linhaOrigem == 6)
                || "preto".equals(this.getCor()) && linhaOrigem == 1){
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
    
    public String getCor() {
        return cor;
    }
}
