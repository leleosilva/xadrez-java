// TESTADOOOOOOOOO

package jogoxadrez;

public class Tabuleiro {
    
    private Posicao posicoes[][];
    
    /* construtor */
    public Tabuleiro () {
        this.posicoes = new Posicao[8][8]; 
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if(i+j % 2 == 0){
                    // cria posição preta
                    this.posicoes[i][j] = new Posicao(i+1, (char)(j + 97), false);
                }
                else{
                    // cria posição branca
                    this.posicoes[i][j] = new Posicao(i+1, (char)(j + 97), true);
                }
            }
        }
        System.out.println("O objeto Tabuleiro foi instanciado com sucesso!");
    }
    
    /* checar se a peça dos limites do tabuleiro */
    public boolean verificaLimitesTabuleiro (int linha, char coluna) {
        linha = linha - 48;
        // verifica se linha desejada está dentro dos limites do tabuleiro
        if (linha >= 1 && linha <= 8) {
            
            // verifica se coluna desejada está dentro dos limites do tabuleiro
            if (coluna >= 'a' && coluna <= 'h') {
                return true;
            }    
        }
        return false;
    }
    /* impressão do tabuleiro */
    public void imprimirTabuleiro () {
        System.out.println();
        System.out.println();
        for (int i = 0; i < 8; i++){
            System.out.print((i+1) + " ");
            for (int j = 0; j < 8; j++){
                System.out.print(" -" + " ");
            }
            System.out.println();
        }
        System.out.print(" ");
        for (int i = 0; i < 8; i++){
            System.out.print("  " + (char)(i + 'a'));
        }
        System.out.println();
    }
}
