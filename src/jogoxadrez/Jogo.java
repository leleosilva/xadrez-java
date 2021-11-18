package jogoxadrez;

import java.util.Scanner;
import pecasxadrez.*;

public class Jogo {
    private Tabuleiro tabuleiro;
    private Jogador jogador1;
    private Jogador jogador2;
    private boolean inicio;
    private boolean xeque;
    private boolean xequeMate;
    // turno = true (branco joga); turno = false (preto joga)
    private boolean turno;
    
    // Construtor da classe Jogo
    public Jogo (String nome1, String nome2){
        
        this.jogador1 = new Jogador(nome1, true, criarPecas(true));
        this.jogador2 = new Jogador(nome2, false, criarPecas(false));
        
        this.tabuleiro = new Tabuleiro(jogador1.getPecas(),  jogador2.getPecas());
        
        inicio = true;
        xeque = false;
        xequeMate = false;
        
        turno = true; // branco começa jogando!
    }
    
    /* Método principal da classe Jogo, reúne outros métodos e se comunica com
     * o usuário para realizar uma partida de xadrez! */
    public void partida(){
        System.out.println(" / **** BEM-VINDOS AO JOGO DE XADREZ **** /");
        
        System.out.println("\n" + jogador1.getNome() + " possui peças brancas e começa o jogo!\n");
        
        while(inicio && !xequeMate){
            
            movimentarPeca();
            
            // Verifica xeque-mate do Rei de acordo com o turno do jogador atual

            reiEmXequeMate(false);
            // Caso haja xeque-mate, a partida se encerra!
            if(xequeMate){
                break;
            } 
            
            reiEmXequeMate(true);
            // Caso haja xeque-mate, a partida se encerra!
            if(xequeMate){
                break;
            }   
            
            // Verifica xeque dos Reis
            reiEmXeque(false);
            reiEmXeque(true);
        }
    }
    
    /* Método que se comunica com o usuário para solicitar os movimentos das peças
    
    */
    private void movimentarPeca(){
        
        Scanner input = new Scanner(System.in);
        
        char[] coordenadasMovimentoOrigem = new char[2];
        char[] coordenadasMovimentoDestino = new char[2];
        
        String movimentoOrigem, movimentoDestino;
        boolean pOrigemValida = false, pDestinoValida = false;
        
        Jogador jogadorTurno;
        
        while(true){
            
            // Imprime tabuleiro e lista de peças capturadas pelos jogadores
            imprimirInterface();
            
            // recebe o jogador branco ou jogador preto
            jogadorTurno = verificaJogadorDoTurno();
            
            // Loop enquanto a entrada da posição de origem for inválida
            while(!pOrigemValida){
                System.out.println("\nInsira a posição da peça que deseja mover, " + jogadorTurno.getNome() + "! (-1 para sair)");
                System.out.print("Posição de origem: ");
                
                movimentoOrigem = input.nextLine();
                
                /* -1 encerra o jogo, perguntando aos jogadores se eles querem salvar
                 * o progresso de sua partida atual em um arquivo ou não. */
                if("-1".equals(movimentoOrigem)){
                    System.exit(0);
                }
                
                /* Uma posição de origem desejada é inserida como uma String de dois caracteres (coluna e linha).
                 * Se a String tiver tamanho diferente de 2, a posição inserida é inválida! */
                if(movimentoOrigem.length() != 2){
                    System.out.println("Posição inválida!");
                    System.out.println();
                }
                else{
                    coordenadasMovimentoOrigem = movimentoOrigem.toCharArray();
                    if(!tabuleiro.verificaLimitesTabuleiro(coordenadasMovimentoOrigem[1], coordenadasMovimentoOrigem[0])){
                        System.out.println("A posição escolhida é inválida!");
                        continue;
                    }
                    pOrigemValida = true;
                }
            }
            while(!pDestinoValida){
                System.out.println("\nDigite a posição para onde deseja se mover, " + jogadorTurno.getNome() + "! (-1 para sair)");
                System.out.print("Posição de destino: ");
                
                movimentoDestino = input.nextLine();
                
                /* -1 encerra o jogo, perguntando aos jogadores se eles querem salvar
                 * o progresso de sua partida atual em um arquivo ou não. */
                if("-1".equals(movimentoDestino)){
                    System.exit(0);
                }
                
                /* Uma posição de origem desejada é inserida como uma String de dois caracteres (coluna e linha).
                 * Se a String tiver tamanho diferente de 2, a posição inserida é inválida! */
                if(movimentoDestino.length() != 2){
                    System.out.println("Posição inválida!");
                    System.out.println();
                }
                else{
                    coordenadasMovimentoDestino = movimentoDestino.toCharArray();
                    if(!tabuleiro.verificaLimitesTabuleiro(coordenadasMovimentoDestino[1], coordenadasMovimentoDestino[0])){
                        System.out.println("A posição escolhida é inválida!");
                        continue;
                    }
                    pDestinoValida = true;
                }
            }
            try{
                // Verifica se o jogador atual está movendo apenas suas peças
                if(!jogadorTurno.isBranco() == tabuleiro.getPosicao(coordenadasMovimentoOrigem[1]-49,
                        coordenadasMovimentoOrigem[0]-97).getPeca().isBranco()){
                    System.out.println("Você não pode mover as peças do jogador adversário!\n");
                    pOrigemValida = false;
                    pDestinoValida = false;
                    continue;
                }
            }
            catch(NullPointerException ex){
                System.out.println("Posições desocupadas são inválidas!");
                pOrigemValida = false;
                pDestinoValida = false;
                continue;
            }
            // Um movimento só é válido caso satisfaça a todas as condições impostas pela classe Tabuleiro
            if(tabuleiro.checaMovimento(coordenadasMovimentoOrigem[1], coordenadasMovimentoOrigem[0], coordenadasMovimentoDestino[1], coordenadasMovimentoDestino[0])){
                
                if(checaSeMovimentoColocaReiEmXeque(coordenadasMovimentoOrigem[1], coordenadasMovimentoOrigem[0], coordenadasMovimentoDestino[1], coordenadasMovimentoDestino[0])){
                    System.out.println("O movimento escolhido coloca seu rei em xeque!");
                    pOrigemValida = false;
                    pDestinoValida = false;
                }
                
                else{
                    // Um movimento só é válido caso o Rei não esteja em xeque ou o movimento tire o Rei do xeque
                    if(checaSeMovimentoTiraReiDoXeque(coordenadasMovimentoOrigem[1], coordenadasMovimentoOrigem[0], coordenadasMovimentoDestino[1], coordenadasMovimentoDestino[0])){
                        tabuleiro.moverPeca(coordenadasMovimentoOrigem[1], coordenadasMovimentoOrigem[0], coordenadasMovimentoDestino[1], coordenadasMovimentoDestino[0]);
                        alteraTurno();
                        return;
                    }
                    else{
                        System.out.println("O movimento escolhido não tira o rei do xeque!");
                        pOrigemValida = false;
                        pDestinoValida = false;
                    }
                }
            }
            else{
                System.out.println("Movimento inválido!");
                pOrigemValida = false;
                pDestinoValida = false;
            }
        }
    }
    
    // Método que imprime o tabuleiro e uma lista com as peças que cada jogador capturou
    private void imprimirInterface(){
        jogador1.pecasCapturadas();
        tabuleiro.imprimirTabuleiro();
        jogador2.pecasCapturadas();
    }
    
    /* Método que verifica se o Rei está em xeque, avisando os jogadores e
     * encerrando a partida caso o Rei seja capturado após o xeque */
    private void reiEmXeque(boolean branca){
        
        String cor;
        String nomeJogador;
        
        if(branca){
            cor = "branco";
            nomeJogador = jogador2.getNome();
        }
        else{
            cor = "preto";
            nomeJogador = jogador1.getNome();
        }
        try{
            if(tabuleiro.verificaReiEmXeque(branca)){
                xeque = true;
                System.out.println("\nO rei " + cor + " está em xeque!");
            }
        }
        catch(NullPointerException ex){
            System.out.println("Fim de jogo! " + nomeJogador + " ganhou a partida!");
            inicio = false;
        }
    }
    
    // Método que verifica se o Rei está em xeque-mate, encerrando a partida e avisando os jogadores
    private void reiEmXequeMate(boolean branca){

        String nomeJogador;
        
        if(branca){
            nomeJogador = jogador2.getNome();
        }
        else{
            nomeJogador = jogador1.getNome();
        }
        try{
            if(tabuleiro.verificaReiEmXequeMate(branca)){
                xequeMate = true;
                imprimirInterface();
                System.out.println("Xeque-mate! " + nomeJogador + " ganhou a partida!");
            }
        }
        catch(NullPointerException ex){
            System.out.println("Fim de jogo! " + nomeJogador + " ganhou a partida!");
            inicio = false;
        }
    }
    
    /* Um jogador não pode fazer um movimento que coloque seu próprio Rei em xeque;
     * caso contrário, o movimento é considerado inválido!
    
     * Esse método verifica se o movimento escolhido pelo usuário coloca seu próprio
     * rei em xeque. */
    private boolean checaSeMovimentoColocaReiEmXeque(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino){
        
        // Convertendo numerações da tabela ASCII para índices de 0 a 7
        int lOrigem = linhaOrigem - 49;
        int cOrigem = colunaOrigem - 97;
        int lDestino = linhaDestino - 49;
        int cDestino = colunaDestino - 97;
        
        boolean posicaoInicialmenteOcupada = tabuleiro.getPosicao(lDestino, cDestino).isOcupada();
        
        Peca pecaDestino = tabuleiro.getPosicao(lDestino, cDestino).getPeca();
        
        // Só verifica se o movimento coloca o Rei em xeque caso ele ainda não esteja em xeque
        if(!xeque){
            
            // Movimenta peça da origem até o destino
            tabuleiro.inserirPecaEmPosicao(lDestino, cDestino, tabuleiro.getPosicao(lOrigem, cOrigem).getPeca());
            tabuleiro.removerPecaDePosicao(lOrigem, cOrigem);
            
            /* Verifica o xeque do Rei de cor oposta ao jogador que joga no turno atual.
             * Se o Rei estiver em xeque após o movimento, esse movimento é inválido! */
            if(tabuleiro.verificaReiEmXeque(isTurno())){
                xeque = true;
                tabuleiro.inserirPecaEmPosicao(lOrigem, cOrigem, tabuleiro.getPosicao(lDestino, cDestino).getPeca());
                tabuleiro.removerPecaDePosicao(lDestino, cDestino);
                
                if(posicaoInicialmenteOcupada){
                    tabuleiro.inserirPecaEmPosicao(lDestino, cDestino, pecaDestino);
                }
                return true;
            }
            xeque = false;
            // Desfazemos o movimento, mesmo que o Rei ainda não esteja em xeque!
            tabuleiro.inserirPecaEmPosicao(lOrigem, cOrigem, tabuleiro.getPosicao(lDestino, cDestino).getPeca());
            tabuleiro.removerPecaDePosicao(lDestino, cDestino);

            if(posicaoInicialmenteOcupada){
                tabuleiro.inserirPecaEmPosicao(lDestino, cDestino, pecaDestino);
            }
            return false;
        }
        return false;
    }
    
    /* Caso o Rei de um jogador esteja em xeque, esse jogador necessariamente deve
     * fazer um movimento que tire seu Rei do estado de xeque; caso contrário, o
     * movimento é considerado inválido!
    
     * Esse método verifica se, caso o Rei de um jogador esteja em xeque, o movimento
     * escolhido por ele o tira do xeque.
    */
    private boolean checaSeMovimentoTiraReiDoXeque(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino){
        
        // Convertendo numerações da tabela ASCII para índices de 0 a 7
        int lOrigem = linhaOrigem - 49;
        int cOrigem = colunaOrigem - 97;
        int lDestino = linhaDestino - 49;
        int cDestino = colunaDestino - 97;
        
        boolean posicaoInicialmenteOcupada = tabuleiro.getPosicao(lDestino, cDestino).isOcupada();
        
        Peca pecaDestino = tabuleiro.getPosicao(lDestino, cDestino).getPeca();
        
        // Só verifica se o movimento tira o Rei do xeque caso ele já esteja em xeque
        if(xeque){
            
            // Movimenta peça da origem até o destino
            tabuleiro.inserirPecaEmPosicao(lDestino, cDestino, tabuleiro.getPosicao(lOrigem, cOrigem).getPeca());
            tabuleiro.removerPecaDePosicao(lOrigem, cOrigem);
            
            /* Verifica o xeque do Rei de cor oposta ao jogador que joga no turno atual.
             * Se o Rei não estiver em xeque após o movimento, esse movimento é válido! */
            if(!tabuleiro.verificaReiEmXeque(isTurno())){
                xeque = false;
                tabuleiro.inserirPecaEmPosicao(lOrigem, cOrigem, tabuleiro.getPosicao(lDestino, cDestino).getPeca());
                tabuleiro.removerPecaDePosicao(lDestino, cDestino);
                
                if(posicaoInicialmenteOcupada){
                    tabuleiro.inserirPecaEmPosicao(lDestino, cDestino, pecaDestino);
                }
                return true;
            }
            xeque = true;
            // Desfazemos o movimento, mesmo que o Rei ainda esteja em xeque!
            tabuleiro.inserirPecaEmPosicao(lOrigem, cOrigem, tabuleiro.getPosicao(lDestino, cDestino).getPeca());
            tabuleiro.removerPecaDePosicao(lDestino, cDestino);

            if(posicaoInicialmenteOcupada){
                tabuleiro.inserirPecaEmPosicao(lDestino, cDestino, pecaDestino);
            }
            return false;
        }
        return true;
    }
    
    // Retorna o jogador que joga no turno atual
    private Jogador verificaJogadorDoTurno(){
        if(isTurno()){
            return jogador1;
        }
        return jogador2;
    }
    
    /* Método que cria um vetor de 16 peças em ordem, para facilitar a visualização
     * das peças capturadas de um jogador */
    private Peca[] criarPecas(boolean branco){
        Peca[] pecas = new Peca[16];
        for(int i = 0; i < 16; i++){
            if(i < 8){
                pecas[i] = new Peao(branco);
            }
            else if(i < 10){
                pecas[i] = new Torre(branco);
            }
            else if(i < 12){
                pecas[i] = new Cavalo(branco);
            }
            else if(i < 14){
                pecas[i] = new Bispo(branco);
            }
            else if(i == 14){
                pecas[i] = new Dama(branco);
            }
            else if(i == 15){
                pecas[i] = new Rei(branco);
            }
        }
        return pecas;
    }
    
    public void salvarPartida(){
        
    }
    
    public void carregarPartida(){
        
    }
    
    /* Método que retorna a cor do jogador a jogar o turno atual
     * true = jogador branco joga; false = jogador preto joga */
    private boolean isTurno() {
        return turno;
    }
    
    // Método que altera o turno de acordo com o jogador que jogou o último turno
    private void alteraTurno() {
        this.turno = !this.turno;
    }
}
