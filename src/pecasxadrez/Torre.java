package pecasxadrez;

public class Torre extends Peca {
    
    public Torre(boolean branco){
        super(branco);
    }
    
    @Override
    public void desenho(){ // método para impressão da peça no tabuleiro
        if(isBranco()){ // peça branca
            System.out.print("T");
        }
        else if(!isBranco()){ // peça preta
            System.out.print("t");
        }
    }
    
    @Override
    public boolean checaMovimento(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino){
        /* Para que a torre se movimente, ela deve se manter na mesma linha inicial
         * (apenas trocando de coluna) ou na mesma coluna inicial (apenas trocando
         * de linha).
        */
        
        // Movimentando-se horizontalmente
        if(linhaOrigem == linhaDestino && colunaOrigem != colunaDestino){
            return true;
        }
        // Movimentando-se verticalmente
        else if(colunaOrigem == colunaDestino && linhaOrigem != linhaDestino){
            return true;
        }
        return false;
    }
}
