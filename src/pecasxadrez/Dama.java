/* ALUNOS
 * Cinthia Souza Costa - 792173
 * Leonardo Cavalcante da Silva - 792190
 */

package pecasxadrez;

public class Dama extends Peca {
    
    public Dama(boolean branco){
        super(branco);
    }
    
    @Override
    public String desenho(){ // método para retorno da representação da peça no tabuleiro
        if(isBranco()){ // peça branca
            return "D";
        }
        return "d"; // peça preta

    }
    
    @Override
    public boolean checaMovimento(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino){
        int distanciaLinha = linhaDestino - linhaOrigem;
        int distanciaColuna = colunaDestino - colunaOrigem;
        /*
         * A dama pode se movimentar na diagonal, horizontal ou vertical
         */
        
        // Movimentando-se diagonalmente
        if(Math.abs(distanciaLinha) == Math.abs(distanciaColuna) && Math.abs(distanciaLinha) != 0){
            return true;
        }
        
        // Movimentando-se horizontalmente
        else if(linhaOrigem == linhaDestino && colunaOrigem != colunaDestino){
            return true;
        }
        // Movimentando-se verticalmente
        else if(colunaOrigem == colunaDestino && linhaOrigem != linhaDestino){
            return true;
        }
        return false;
    }
}
