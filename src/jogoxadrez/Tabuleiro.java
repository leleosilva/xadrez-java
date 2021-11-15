package jogoxadrez;

import pecasxadrez.*;

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
    
    /* impressão do tabuleiro */
    public void imprimirTabuleiro () {
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
        System.out.println("\n");
    }
    
    public boolean checaMovimento(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino){
        
        // Convertendo numerações da tabela ASCII para índices de 0 a 7
        int lOrigem = linhaOrigem - 49;
        int cOrigem = colunaOrigem - 97;
        int lDestino = linhaDestino - 49;
        int cDestino = colunaDestino - 97;
        
        // Uma posição de origem desocupada é inválida!
        if(!posicoes[lOrigem][cOrigem].isOcupada()){
            return false;
        }
        // Verifica se as posições de origem e destino estão dentro dos limites do tabuleiro
        if(verificaLimitesTabuleiro(linhaOrigem, colunaOrigem) && verificaLimitesTabuleiro(linhaDestino, colunaDestino)){
            // Verifica se o movimento da peça na posição de origem é válido
            if(posicoes[lOrigem][cOrigem].getPeca().checaMovimento(lOrigem + 1, colunaOrigem, lDestino + 1, colunaDestino)){
                // Verifica se o caminho entre a origem e o destino está ocupado por peças
                if(verificaCaminho(lOrigem, cOrigem, lDestino, cDestino)){
                    // Verifica se a posição de destino está vazia ou ocupada por uma peça de cor oposta / mesma cor
                    if(verificaOcupacaoDestino(lOrigem, cOrigem, lDestino, cDestino)){
                        return true;
                    }
                }
            }
            if(posicoes[lOrigem][cOrigem].getPeca() instanceof Peao){
                if(verificaMovimentoPeao(lOrigem, cOrigem, lDestino, cDestino)){
                    return true;
                }
            }
        }
        return false;
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
    
    private boolean verificaCaminho(int lOrigem, int cOrigem, int lDestino, int cDestino){

        Posicao pOrigem, pDestino;
        try{
            pOrigem = posicoes[lOrigem][cOrigem];
            pDestino = posicoes[lDestino][cDestino];
        }
        catch(IndexOutOfBoundsException ex){
            return false;
        }
        
        int avancaLinha = lOrigem;
        int avancaColuna = cOrigem;
        
        /* O Cavalo se movimenta "pulando" posições do tabuleiro, e o Rei
         * se move apenas uma posição por vez.
         * Portanto, não é necessário verificar o caminho até o destino para essas peças! */
        if(pOrigem.getPeca() instanceof Cavalo || pOrigem.getPeca() instanceof Rei){
            return true;
        }
        // Movimento na diagonal
        if(Math.abs(lDestino-lOrigem) == (Math.abs(cDestino-cOrigem))){
            while(avancaLinha != lDestino || avancaColuna != cDestino){
                // Aumenta o índice da linha se o índice de origem for menor do que o de destino
                if(lOrigem < lDestino){
                    avancaLinha++;
                }
                else if(lOrigem > lDestino){ // Caso contrário, diminui o índice da linha
                    avancaLinha--;
                }
                // Aumenta o índice da coluna se o índice de origem for menor do que o de destino
                if(cOrigem < cDestino){
                    avancaColuna++;
                }
                else if(cOrigem > cDestino){ // Caso contrário, diminui o índice da coluna
                    avancaColuna--;
                }
                // Se alguma posição no caminho estiver ocupada, o movimento é inválido!
                if(posicoes[avancaLinha][avancaColuna].isOcupada() && (avancaColuna != cDestino && avancaLinha != lDestino)){
                    return false;
                }
            }
        }
        // Movimento na horizontal
        else if(lDestino == lOrigem){
            while(avancaColuna != cDestino){
                // Aumenta o índice da coluna se o índice de origem for menor do que o de destino
                if(cOrigem < cDestino){
                    avancaColuna++;
                }
                else if(cOrigem > cDestino){ // Caso contrário, diminui o índice da coluna
                    avancaColuna--;
                }
                // Se alguma posição no caminho estiver ocupada, o movimento é inválido!
                if(posicoes[lDestino][avancaColuna].isOcupada() && avancaColuna != cDestino){
                    return false;
                }
            }
        }
        // Movimento na vertical
        else if(cDestino == cOrigem){
            while(avancaLinha != lDestino){
                // Aumenta o índice da linha se o índice de origem for menor do que o de destino
                if(lOrigem < lDestino){
                    avancaLinha++;
                }
                else if(lOrigem > lDestino){ // Caso contrário, diminui o índice da linha
                    avancaLinha--;
                }
                // Se alguma posição no caminho estiver ocupada, o movimento é inválido!
                if(posicoes[avancaLinha][cDestino].isOcupada() && avancaLinha != lDestino){
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean verificaOcupacaoDestino(int lOrigem, int cOrigem, int lDestino, int cDestino){
        
        Posicao pOrigem, pDestino;
        try{
            pOrigem = posicoes[lOrigem][cOrigem];
            pDestino = posicoes[lDestino][cDestino];
        }
        catch(IndexOutOfBoundsException ex){
            return false;
        }
        
        // Verifica se a posição de destino está ocupada
        if(pDestino.isOcupada()){
            /* Caso esteja ocupada, o movimento só pode ocorrer se a cor da peça
             * ocupante for diferente da peça que está se movimentando */
            return pOrigem.getPeca().isBranco() == !pDestino.getPeca().isBranco();
        }
        return true;
    }
    
    // Método que verifica a movimentação diagonal do Peão
    private boolean verificaMovimentoPeao(int lOrigem, int cOrigem, int lDestino, int cDestino){
        Posicao pOrigem, pDestino;
        try{
            pOrigem = posicoes[lOrigem][cOrigem];
            pDestino = posicoes[lDestino][cDestino];
        }
        catch(IndexOutOfBoundsException ex){
            return false;
        }
        
        // O peão só pode se movimentar na diagonal em colunas adjacentes à sua posição atual
        if(cDestino == cOrigem + 1 || cDestino == cOrigem - 1){
            // Verifica se a movimentação do peão está correta de acordo com sua cor
            if(pOrigem.getPeca().isBranco() && lDestino - lOrigem == -1
                    || !pOrigem.getPeca().isBranco() && lDestino - lOrigem == 1){
                // O peão só pode se movimentar na diagonal se a posição de destino contiver peças adversárias
                if(pDestino.isOcupada()){
                    return pOrigem.getPeca().isBranco() == !pDestino.getPeca().isBranco();
                }
            }
        }
        return false;
    }
    
    public void moverPeca(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino){
        
        // Convertendo numerações da tabela ASCII para índices de 0 a 7
        int lOrigem = linhaOrigem - 49;
        int cOrigem = colunaOrigem - 97;
        int lDestino = linhaDestino - 49;
        int cDestino = colunaDestino - 97;
        
        /* Se a posição de destino estiver ocupada, então a peça presente nessa
         * posição será capturada */
        if(posicoes[lDestino][cDestino].isOcupada()){
            posicoes[lDestino][cDestino].getPeca().setCapturada(true);
        }
        // Movendo a peça da posição de origem para a posição de destino
        posicoes[lDestino][cDestino].setPeca(posicoes[lOrigem][cOrigem].getPeca());
        posicoes[lDestino][cDestino].setOcupada(true);
        posicoes[lOrigem][cOrigem].setOcupada(false);
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
    
    public Peca getPosicaoDaPeca(int linha, char coluna){
        int lOrigem = linha - 49;
        int cOrigem = coluna - 97;
        
        try{
            return posicoes[lOrigem][cOrigem].getPeca();
        }
        catch(IndexOutOfBoundsException ex){
            return null;
        }
    }
}
