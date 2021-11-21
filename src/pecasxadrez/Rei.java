/* ALUNOS
 * Cinthia Souza Costa - 792173
 * Leonardo Cavalcante da Silva - 792190
 */

package pecasxadrez;

public class Rei extends Peca {
    
    public Rei(boolean branco){
        super(branco);
    }
    
    @Override
    public String desenho(){ // método para retorno da representação da peça no tabuleiro
        if(isBranco()){ // peça branca
            return "R";
        }
        return "r"; // peça preta
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
