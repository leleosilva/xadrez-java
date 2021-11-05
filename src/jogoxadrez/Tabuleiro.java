package jogoxadrez;

import pecasxadrez.Peca;

public class Tabuleiro {
    
    private Posicao posicoes[][];
    
    /* construtor */
    public Tabuleiro (Peca[] pecasBrancas, Peca[] pecasPretas){
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
        inserePecasTabuleiro(pecasBrancas);
        inserePecasTabuleiro(pecasPretas);
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
            System.out.print((i+1) + "  ");
            for (int j = 0; j < 8; j++){
                if(posicoes[i][j].isOcupada()){ // Verifica se posição está ocupada
                    System.out.print(" ");
                    posicoes[i][j].getPeca().desenho(); // Imprime representação da peça
                    System.out.print(" ");
                }
                else{
                    System.out.print(" -" + " "); // Posição vazia
                }
            }
            System.out.println();
        }
        System.out.println();
        System.out.print("  ");
        for (int i = 0; i < 8; i++){ // Imprimindo caracteres das colunas
            System.out.print("  " + (char)(i + 'a'));
        }
        System.out.println();
    }
    
    private void inserePecasTabuleiro(Peca[] pecas){
        
        int linha;
        int posPeao, posTorre = 0, posCavalo = 1, posBispo = 2, posDama = 3, posRei = 4;
        
        
        if(pecas[0].isBranco()){
            linha = 7; // Peças brancas (exceto peões) são inseridas na linha 7
            posPeao = 6; // Peões brancos são inseridos na linha 6
        }
        else{
            linha = 0; // Peças pretas (exceto peões) são inseridas na linha 0
            posPeao = 1; // Peões pretos são inseridos na linha 1
        }
        for(int i = 0; i < 16; i++){
            if(i < 8){
                posicoes[posPeao][i].setOcupada(true);
                posicoes[posPeao][i].setPeca(pecas[i]);
            }
            else if(i < 10){ // Torre
                posicoes[linha][posTorre].setOcupada(true);
                posicoes[linha][posTorre].setPeca(pecas[i]);
                posTorre = 7;
            }
            else if(i < 12){ // Cavalo
                posicoes[linha][posCavalo].setOcupada(true);
                posicoes[linha][posCavalo].setPeca(pecas[i]);
                posCavalo = 6;
            }
            else if(i < 14){ // Bispo
                posicoes[linha][posBispo].setOcupada(true);
                posicoes[linha][posBispo].setPeca(pecas[i]);
                posBispo = 5;
            }
            else if(i == 14){ // Dama
                posicoes[linha][posDama].setOcupada(true);
                posicoes[linha][posDama].setPeca(pecas[i]);
            }
            else if(i == 15){ // Rei
                posicoes[linha][posRei].setOcupada(true);
                posicoes[linha][posRei].setPeca(pecas[i]);
            }
        }
    }
}
