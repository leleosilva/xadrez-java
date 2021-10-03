package jogoxadrez;

public class Tabuleiro {
    
    private Posicao posicoes[][];
    
    /* construtor */
    public Tabuleiro () {
        this.posicoes = new Posicao[8][8]; 
        this.inicializacaoTabuleiro();
    }
    
    /* inicialização de tabuleiro */
    public void inicializacaoTabuleiro () {
        boolean cor;
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                
                /* Verifica se a posição é branca ou preta
                 * branco = false; preto = true.
                 * Se a soma dos índices da posição for par, a posição é preta.
                 * Caso contrário, é branca.
                */
                if(i+j % 2 == 0){
                    cor = true;
                }
                else{
                    cor = false;
                }
                
                /* A linha de cada posição corresponde ao índice i+1
                 * (variando de 1 a 8), enquanto a coluna corresponde ao índice
                 * j + 97 (ou seja, j + 'a', variando de 'a' até 'h').
                
                * Ocorre o cast de int para char para armazenar a coluna corretamente.
                */
                this.posicoes[i][j] = new Posicao(i+1, (char)(j + 97), cor);
            }
        }
    }
    
    /* checar se a peça dos limites do tabuleiro */
    public boolean verificaLimitesTabuleiro (int linha, char coluna) {
        
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
