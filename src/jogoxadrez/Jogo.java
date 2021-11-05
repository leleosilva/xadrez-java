package jogoxadrez;

import java.util.Scanner;
import pecasxadrez.*;

public class Jogo {
    private Tabuleiro tabuleiro;
    private Jogador jogador1;
    private Jogador jogador2;
    // estado 1 = início; estado 2 = xeque; estado 3 = xeque-mate
    private int estado;
    // vezRodada = true (branco joga); vezRodada = false (preto joga)
    private boolean vezRodada;
    
    /* construtor */
    public Jogo (String nome1, String nome2){
        this.jogador1 = new Jogador(nome1, true, criarPecas(true));
        this.jogador2 = new Jogador(nome2, false, criarPecas(false));
        this.tabuleiro = new Tabuleiro(jogador1.getPecas(),  jogador2.getPecas());
        this.estado = 1;
        this.vezRodada = true;
    }
    
    public void partida(){
        System.out.println(" / **** BEM-VINDOS AO JOGO DE XADREZ **** /");

        Scanner input = new Scanner(System.in);
        
        char[] coordenadasMovimento;
        String movimento, jogadorRodada;
        boolean movimentoValido = false;
        
        this.tabuleiro.imprimirTabuleiro();
        System.out.println();
        
        System.out.println(this.jogador1.getNome() + " começa o jogo!\n");
        
        while(estado == 1){
           this.isVezRodada(); // true = branco joga; false = preto joga

           // recebe o nome do jogador branco ou do jogador preto
           jogadorRodada = this.isVezRodada() ? this.jogador1.getNome() : this.jogador2.getNome();
           if(movimentoValido){
               System.out.println();
               System.out.println("É a sua vez, " + jogadorRodada + "!\n");
           }
           System.out.print("Digite a posição para onde deseja se mover, " + jogadorRodada + "! (-1 para sair): ");
           movimento = input.next();

           if("-1".equals(movimento)){
               break;
           }
           if(movimento.length() != 2){
               movimentoValido = false;
               System.out.println("Posição inválida!");
               System.out.println();
           }
           else{
               coordenadasMovimento = movimento.toCharArray();

               if(tabuleiro.verificaLimitesTabuleiro(coordenadasMovimento[1], coordenadasMovimento[0])){
                   this.setVezRodada(this.isVezRodada());
                   movimentoValido = true;
               }
               else{
                   movimentoValido = false;
                   System.out.println("Posição inválida!\n");
               } 
           }
       }
        input.close();
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
    
    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        if(estado < 1 && estado > 3){
            System.out.println("Estado inválido!");
        }
        else{
            this.estado = estado;
        }
    }

    public boolean isVezRodada() {
        return vezRodada;
    }

    public void setVezRodada(boolean vezRodada) {
        this.vezRodada = !this.vezRodada;
    }
}
