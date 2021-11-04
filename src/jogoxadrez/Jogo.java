package jogoxadrez;

import java.util.Scanner;

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
        this.tabuleiro = new Tabuleiro();
        this.jogador1 = new Jogador(nome1, true);
        this.jogador2 = new Jogador(nome2, false);
        this.estado = 1;
        this.vezRodada = true;
        System.out.println("O objeto Jogo foi instanciado com sucesso!");
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
