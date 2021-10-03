package jogoxadrez;

import java.util.Scanner;

public class Jogo {
    private Tabuleiro tabuleiro;
    private Jogador jogador1;
    private Jogador jogador2;
    private String estado;
    
    /* construtor */
    public Jogo (){
        this.tabuleiro = new Tabuleiro();
        tabuleiro.inicializacaoTabuleiro();
        this.estado = "jogando";       
    }
    
    public void partida (){
        
        System.out.println(" / **** BEM-VINDOS AO JOGO DE XADREZ **** /");
        
        /* definição dos jogadores */
        Scanner in = new Scanner(System.in);
        String nome1, nome2;
        
        System.out.print("Digite o nome do primeiro jogador: ");
        nome1 = in.next();
        System.out.print("Digite o nome do segundo jogador: ");
        nome2 = in.next();
        
        this.jogador1 = new Jogador (nome1, false);
        this.jogador2 = new Jogador (nome2, true);
        
        // Imprime o tabuleiro
        this.tabuleiro.imprimirTabuleiro();
        
        /* 
               Definição de intercalação de jogadas

               false = branco joga
               true = preto joga
            */
        boolean vez = false;
        
        // Flag para verificar se o movimento é válido
        boolean movimentoValido = true;
        
        // Partida ocorre enquanto o estado é "jogando"
        while("jogando".equals(this.estado)){
            String movimento;
            
            // Muda a vez do jogador para preto caso um movimento válido tenha sido inserido
            if(!vez && movimentoValido){
                System.out.println("É a sua vez, " + this.jogador1.getNome() + "!");
                vez = true;
                movimentoValido = false;
            }
            // Muda a vez do jogador para branco caso um movimento válido tenha sido inserido
            else if(vez && movimentoValido){
                System.out.println("É a sua vez, " + this.jogador2.getNome() + "!");
                vez = false;
                movimentoValido = false;
            }
            
            /* Os movimentos são escolhidos utilizando a convenção CL, em que
             * C = coluna e L = linha. Exemplos: a4, h3, g7, b1...
            */
            System.out.print("Digite as coordenadas da posição para onde deseja se mover! "
                    + "Ex: c7 (Digite -1 para sair da partida): ");
            movimento = in.next();
            if("-1".equals(movimento)){
                break;
            }
            System.out.println();
            
            /* movimento é uma String com dois caracteres, com coluna e linha.
             * Caso o tamanho da String seja diferente de 2, oo movimento digitado
             * é inválido.
            */
            if(movimento.length() != 2){
                System.out.println("Posição inválida!");
                System.out.println();
            }
            else{
                /* Divide a String movimento em dois caracteres distintos,
                 * coordenadasMovimento[0] = coluna
                 * coordenadasMovimento[1] = linha */
                char[] coordenadasMovimento = movimento.toCharArray();
                
                // Posição dentro do tabuleiro, movimento pode ocorrer
                
                /* coordenadasMovimento[1] é uma String representando 1 a 8,
                 * então subtraímos 48 (caractere 0 na tabela ASCII) para
                 * transformar em um inteiro representando a linha da posição.
                */
                if(this.tabuleiro.verificaLimitesTabuleiro(coordenadasMovimento[1]-48, coordenadasMovimento[0])){
                    System.out.println("Um movimento para a posição " + movimento + " é válido!");
                    System.out.println();
                    movimentoValido = true; // movimento é válido
                }
                // Posição fora dos limites do tabuleiro 
                else{
                    System.out.println("Posição inválida!");
                    System.out.println();
                }
            }
        }
    }
}
