/* ALUNOS
 * Cinthia Souza Costa - 792173
 * Leonardo Cavalcante da Silva - 792190
 */

package pecasxadrez;

public class Cavalo extends Peca {
    
    public Cavalo(boolean branco){
        super(branco);
    }
    
    @Override
    public String desenho(){ // método para retorno da representação da peça no tabuleiro
        if(isBranco()){ // peça branca
            return "C";
        }
        return "c"; // peça preta
    }
    
    @Override
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
        return Math.abs(distanciaLinha) == 1 && Math.abs(distanciaColuna) == 2;
    }
}
