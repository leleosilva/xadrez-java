/* ALUNOS
 * Cinthia Souza Costa - 792173
 * Leonardo Cavalcante da Silva - 792190
 */

package jogoxadrez;

import java.io.Serializable;
import pecasxadrez.*;

public class Tabuleiro implements Serializable {
    
    private Posicao posicoes[][];
    
    // Construtor da classe Tabuleiro
    public Tabuleiro (Peca[] pecasBrancas, Peca[] pecasPretas) throws IntervaloInvalido{
        this.posicoes = new Posicao[8][8]; 
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if((i+j) % 2 == 0){
                    // Cria posição preta
                    this.posicoes[i][j] = new Posicao(i+1, (char)(j + 97), false);
                }
                else{
                    // Cria posição branca
                    this.posicoes[i][j] = new Posicao(i+1, (char)(j + 97), true);
                }
            }
        }
        inserePecasTabuleiro(pecasBrancas); // Insere peças brancas no tabuleiro
        inserePecasTabuleiro(pecasPretas); // Insere peças pretas no tabuleiro
    }
    
    // Impressão do tabuleiro atualizado de acordo com o último turno concluído
    public void imprimirTabuleiro () {
        System.out.println();
        for (int i = 0; i < 8; i++){ // Imprime índices das linhas
            System.out.print((i+1) + "  ");
            for (int j = 0; j < 8; j++){
                if(posicoes[i][j].isOcupada()){ // Verifica se posição está ocupada
                    System.out.print(" ");
                    System.out.print(posicoes[i][j].getPeca().desenho()); // Imprime representação da peça
                    System.out.print(" ");
                }
                else{ // Posição vazia
                    if(posicoes[i][j].isBranco()){
                        System.out.print(" *" + " "); // Posição branca
                    }
                    else{
                        System.out.print(" -" + " "); // Posição preta
                    }
                }
            }
            System.out.println();
        }
        System.out.println();
        System.out.print("  ");
        for (int i = 0; i < 8; i++){ // Imprime índices das colunas
            System.out.print("  " + (char)(i + 'a'));
        }
        System.out.println("\n");
    }
    
    /* Método público utilizado para verificar se um determinado movimento é válido.
     * Esse método reúne métodos privados que verificam diferentes aspectos de um movimento. */
    public boolean checaMovimento(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino){
        
        // Convertendo numerações da tabela ASCII para índices de 0 a 7
        int lOrigem = linhaOrigem - 49;
        int cOrigem = colunaOrigem - 97;
        int lDestino = linhaDestino - 49;
        int cDestino = colunaDestino - 97;
        
        try{
            // Uma posição de origem desocupada é inválida!
            if(!posicoes[lOrigem][cOrigem].isOcupada()){
                return false;
            }
        }
        catch(IndexOutOfBoundsException ex){
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
            // Verifica o movimento de captura do Peão
            if(posicoes[lOrigem][cOrigem].getPeca() instanceof Peao){
                if(verificaMovimentoPeao(lOrigem, cOrigem, lDestino, cDestino)){
                    return true;
                }
            }
        }
        return false;
    }
    
    // Verifica se uma determinada linha e coluna estão dentro dos limites do tabuleiro
    public boolean verificaLimitesTabuleiro (int linha, char coluna) {
        linha = linha - 48;
        // Verifica se linha desejada está dentro dos limites do tabuleiro
        if (linha >= 1 && linha <= 8) {
            
            // Verifica se coluna desejada está dentro dos limites do tabuleiro
            if (coluna >= 'a' && coluna <= 'h') {
                return true;
            }    
        }
        return false;
    }
    
    /* Verifica se um caminho de uma posição de origem até uma posição de destino
     * está livre, de acordo com a peça e com o tipo de movimento. */
    private boolean verificaCaminho(int lOrigem, int cOrigem, int lDestino, int cDestino){

        Posicao pOrigem;
        try{
            pOrigem = posicoes[lOrigem][cOrigem];
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
    
    /* Método que verifica se a posição de destino está desocupada, ocupada por
     * uma peça de mesma cor ou ocupada por uma peça adversária. */
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
            
            /* Mesmo que uma posição esteja ocupada por uma peça de cor diferente em
             * relação a um certo Peão, este Peão não poderá ocupar essa posição, já
             * que ele só pode ocupar posições que possuem peças adversárias na diagonal!
             *
             * O comportamento de captura do Peão não é implementado por esse método!
             */
            if(pOrigem.getPeca() instanceof Peao){
                return false;
            }
            
            /* Caso esteja ocupada, o movimento só pode ocorrer se a cor da peça
             * ocupante for diferente da peça que está se movimentando */
            return pOrigem.getPeca().isBranco() == !pDestino.getPeca().isBranco();
        }
        return true;
    }
    
    // Método que verifica a movimentação de captura diagonal do Peão
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
    
    // Método que move uma peça de uma posição de origem até uma posição de destino 
    public void moverPeca(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino){
        
        // Retorna false se o movimento for inválido
        if(!checaMovimento(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino)){
            return;
        }
        
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
    
    // Método que verifica e retorna a posição de um Rei da cor desejada
    private Posicao verificaPosicaoRei(boolean branca){
        
        // Verifica a posição do Rei branco
        if(branca == true){
            for(int i = 0; i < 8; i++){
                for(int j = 0; j < 8; j++){
                    if(posicoes[i][j].isOcupada()){
                        if(posicoes[i][j].getPeca() instanceof Rei && posicoes[i][j].getPeca().isBranco() == true){
                            return posicoes[i][j];
                        }
                    }
                }
            }
        }
        // Verifica a posição do Rei preto
        else{
            for(int i = 0; i < 8; i++){
                for(int j = 0; j < 8; j++){
                    if(posicoes[i][j].isOcupada()){
                        if(posicoes[i][j].getPeca() instanceof Rei && posicoes[i][j].getPeca().isBranco() == false){
                            return posicoes[i][j];
                        }
                    }
                }
            }
        }
        return null;
    }
    
    /* Método que verifica se o Rei está em xeque.
     * Caso o Rei seja capturado após o aviso de xeque, lança uma exceção do tipo
     * NullPointerException, indicando fim de jogo.
     */
    public boolean verificaReiEmXeque(boolean branca) throws NullPointerException {
        
        // Retorna posição do Rei; caso ele tenha sido capturado, retorna nulo
        Posicao pRei = verificaPosicaoRei(branca);
        
        // Percorre todo o tabuleiro, verificando se peças adversárias podem capturar o Rei
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(posicoes[i][j].isOcupada() && posicoes[i][j].getPeca().isBranco() != branca){
                    if(checaMovimento(i + '1', (char)(j + 'a'), pRei.getLinha() + '0', pRei.getColuna())){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    /* Método que verifica se o Rei está em xeque-mate
     * Caso o Rei seja capturado após o aviso de xeque, lança uma exceção do tipo
     * NullPointerException, indicando fim de jogo.
     */
    public boolean verificaReiEmXequeMate(boolean branca) throws NullPointerException {
        
        // Se o Rei não está em xeque, então ele certamente não está em xeque-mate
        if(!verificaReiEmXeque(branca)){
            return false;
        }
        
        boolean posicaoInicialmenteOcupada;
        
        /* Percorreremos as posições do tabuleiro, procurando por peças da mesma
         * cor do Rei em xeque */
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){

                if(posicoes[i][j].isOcupada() && posicoes[i][j].getPeca().isBranco() == branca){
                    
                    for(int k = 0; k < 8; k++){
                        for(int l = 0; l < 8; l++){
                            
                           /* Ao testar os movimentos, algumas peças podem acabar sendo removidas.
                            *
                            * Por isso, utilizamos um objeto auxiliar da classe Peca para armazenar
                            * as peças removidas e retorná-las ao desfazer o movimento.
                            */
                            Peca pAuxiliar = posicoes[k][l].getPeca();
                                    
                            // Para cada peça encontrada, testaremos todos os seus movimentos válidos
                            if(checaMovimento(i + '1', (char)(j + 'a'), k + '1', (char)(l + 'a'))){
                                
                                /* Verificamos se a posição de destino está inicialmente ocupada,
                                 * pois se estiver, devemos utilizar pAuxiliar para inserir a peça
                                 * removida de volta nessa posição, ao desfazer o movimento. 
                                 */
                                posicaoInicialmenteOcupada = posicoes[k][l].isOcupada();
                                
                                // Movimentando peça
                                inserirPecaEmPosicao(k, l, posicoes[i][j].getPeca());
                                removerPecaDePosicao(i, j);

                                /* Caso o movimento feito tire o Rei do xeque, então não é xeque-mate!
                                 * Nesse caso, desfazemos o movimento feito. */
                                if(!verificaReiEmXeque(branca)){
                                    
                                    inserirPecaEmPosicao(i, j, posicoes[k][l].getPeca());
                                    removerPecaDePosicao(k, l);
                                    
                                    /* Se a posição era inicialmente ocupada, devemos
                                     * retornar a peça que ocupava a posição. */
                                    if(posicaoInicialmenteOcupada){
                                        inserirPecaEmPosicao(k, l, pAuxiliar);
                                    }
                                    return false;    
                                }
                                // Devemos desfazer o movimento, mesmo que o Rei continue em xeque!
                                inserirPecaEmPosicao(i, j, posicoes[k][l].getPeca());
                                removerPecaDePosicao(k, l);
                                
                                /* Se a posição era inicialmente ocupada, devemos
                                 * retornar a peça que ocupava a posição. */
                                if(posicaoInicialmenteOcupada){
                                    inserirPecaEmPosicao(k, l, pAuxiliar);
                                }
                            }
                        }
                    }
                }            
            }
        }
        return true;
    }
    
    // Método auxiliar, utilizado para inserir uma peça em uma posição do tabuleiro
    public void inserirPecaEmPosicao(int linha, int coluna, Peca peca){
        posicoes[linha][coluna].setPeca(peca);
        posicoes[linha][coluna].setOcupada(true);
    }
    
    // Método auxiliar, utilizado para retirar uma peça de uma posição do tabuleiro
    public void removerPecaDePosicao(int linha, int coluna){
        posicoes[linha][coluna].setOcupada(false);
        posicoes[linha][coluna].setPeca(null);
    }
    
    // Método que inicialmente insere as peças no tabuleiro, criando sua configuração inicial
    private void inserePecasTabuleiro(Peca[] pecas){
        
        int linha;
        int posPeao, posTorre = 0, posCavalo = 1, posBispo = 2, posDama = 3, posRei = 4;
        
        
        if(pecas[0].isBranco()){
            linha = 7; // Peças brancas (exceto peões) são inseridas na linha 8 (índice 7)
            posPeao = 6; // Peões brancos são inseridos na linha 7 (índice 6)
        }
        else{
            linha = 0; // Peças pretas (exceto peões) são inseridas na linha 1 (índice 0)
            posPeao = 1; // Peões pretos são inseridos na linha 2 (índice 1)
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
    
    // Método getter que retorna uma posição do tabuleiro
    public Posicao getPosicao(int linha, int coluna) {
        try{
            return posicoes[linha][coluna];
        }
        catch(IndexOutOfBoundsException ex){
            return null;
        }
    }
    
    
}
