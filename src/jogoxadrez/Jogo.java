package jogoxadrez;

import java.util.Scanner;
import pecasxadrez.*;

public class Jogo {
    private Tabuleiro tabuleiro;
    private Jogador jogador1;
    private Jogador jogador2;
    // estado 1 = início; estado 2 = xeque; estado 3 = xeque-mate
    private int estado;
    // turno = true (branco joga); turno = false (preto joga)
    private boolean turno;
    
    /* construtor */
    public Jogo (String nome1, String nome2){
        this.jogador1 = new Jogador(nome1, true, criarPecas(true));
        this.jogador2 = new Jogador(nome2, false, criarPecas(false));
        this.tabuleiro = new Tabuleiro(jogador1.getPecas(),  jogador2.getPecas());
        this.estado = 1;
        this.turno = true;
    }
    
    public void partida(){
        System.out.println(" / **** BEM-VINDOS AO JOGO DE XADREZ **** /");
        
        System.out.println("\n" + jogador1.getNome() + " começa o jogo!\n");
        
        while(estado == 1){
            movimentarPeca();
        }
    }
    
    private void movimentarPeca(){
        
        Scanner input = new Scanner(System.in);
        
        char[] coordenadasMovimentoOrigem = new char[2];
        char[] coordenadasMovimentoDestino = new char[2];
        
        String movimentoOrigem, movimentoDestino;
        boolean pOrigemValida = false, pDestinoValida = false;
        
        Jogador jogadorTurno;
        
        while(true){
            
            imprimirInterface();
            
            // recebe o jogador branco ou jogador preto
            jogadorTurno = verificaJogadorDoTurno();
            
            while(!pOrigemValida){
                System.out.println("\nInsira a posição da peça que deseja mover, " + jogadorTurno.getNome() + "! (-1 para sair): ");
                movimentoOrigem = input.next();

                if("-1".equals(movimentoOrigem)){
                    System.exit(0);
                }
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
                System.out.print("\nDigite a posição para onde deseja se mover, " + jogadorTurno.getNome() + "! (-1 para sair): ");
                movimentoDestino = input.next();
                if("-1".equals(movimentoDestino)){
                    System.exit(0);
                }

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
                if(!jogadorTurno.isBranco() == tabuleiro.getPosicaoDaPeca(coordenadasMovimentoOrigem[1],
                        coordenadasMovimentoOrigem[0]).isBranco()){
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
                if(tabuleiro.checaMovimento(coordenadasMovimentoOrigem[1], coordenadasMovimentoOrigem[0], coordenadasMovimentoDestino[1], coordenadasMovimentoDestino[0])){
                    tabuleiro.moverPeca(coordenadasMovimentoOrigem[1], coordenadasMovimentoOrigem[0], coordenadasMovimentoDestino[1], coordenadasMovimentoDestino[0]);
                    alteraTurno(isTurno());
                    break;
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
    
    // Retorna o jogador que joga no turno atual
    private Jogador verificaJogadorDoTurno(){
        if(isTurno()){
            return jogador1;
        }
        return jogador2;
    }
    
    /* Método que cria um vetor de 16 peças em ordem, para facilitar a visualização
     * das peças ativas e capturadas de um jogador */
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
    
    private int getEstado() {
        return estado;
    }

    private void setEstado(int estado) {
        if(estado < 1 && estado > 3){
            System.out.println("Estado inválido!");
        }
        else{
            this.estado = estado;
        }
    }
    
    /* Método que retorna a cor do jogador a jogar o turno atual
     * true = jogador branco joga; false = jogador preto joga */
    private boolean isTurno() {
        return turno;
    }
    
    // Método que altera o turno de acordo com o jogador que jogou o último turno
    private void alteraTurno(boolean turno) {
        this.turno = !this.turno;
    }
}
