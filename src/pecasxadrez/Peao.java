/* ALUNOS
 * Cinthia Souza Costa - 792173
 * Leonardo Cavalcante da Silva - 792190
 */

package pecasxadrez;

public class Peao extends Peca {
    
    public Peao(boolean branco){
        super(branco);
    }
    
    @Override
    public String desenho(){ // método para retorno da representação da peça no tabuleiro
        if(isBranco()){ // peça branca
            return "P";
        }
        return "p"; // peça preta
    }
    
    @Override
    public boolean checaMovimento(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino){
        
        int distanciaLinhaBranco =  linhaOrigem - linhaDestino;
        int distanciaLinhaPreto = linhaDestino - linhaOrigem;
        /*
         * O peão pode se movimentar duas posições quando se encontra em sua posição
         * inicial. Caso contrário, ele apenas poderá se movimentar uma posição.
         * Em ambos os casos, a coluna de origem e de destino devem ser iguais.
         * O peão não pode se movimentar para trás!
        */
        
        // Peão branco, inicia na linha 7 e se movimenta apenas para cima
        if(this.isBranco()){
            // O peão não pode trocar de coluna
            if(colunaOrigem == colunaDestino){
                if((distanciaLinhaBranco == 1) || linhaOrigem == 7 && distanciaLinhaBranco == 2){
                    return true;
                }
            }
        }
        // Peão preto, inicia na linha 2 e se movimenta apenas para baixo
        else{
            // O peão não pode trocar de coluna
            if(colunaOrigem == colunaDestino){
                if((distanciaLinhaPreto == 1) || linhaOrigem == 2 && distanciaLinhaPreto == 2){
                    return true;
                }
            }
        }
        return false;
    }
}
