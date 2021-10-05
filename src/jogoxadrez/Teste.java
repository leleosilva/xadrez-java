package jogoxadrez;

import java.util.Scanner;
import pecasxadrez.*;

public class Teste {

    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        String movimento, nome1, nome2, cor, ocupacao, jogadorRodada;
        boolean branco, ocupada, vez, movimentoValido = true;
        int estadoJogo;
        
        
        // PARTE 1: TESTES DAS CLASSES GERAIS
        
        
        /* Classe Tabuleiro */
        
        
        // TESTE 1
        System.out.println("Teste 1: Instanciando objeto da classe Tabuleiro");
        Tabuleiro tabuleiro = new Tabuleiro();
        System.out.println();
        
        
        // TESTE 2
        System.out.println("Teste 2: Imprimindo tabuleiro");
        tabuleiro.imprimirTabuleiro();
        System.out.println();
        
        
        // TESTE 3
        System.out.println("Teste 3: Verificando se movimento está dentro dos"
                + " limites do tabuleiro");
        while(true){
            System.out.print("Digite a posição para onde deseja se mover! (-1 para sair): ");
            movimento = in.next();
            
            if("-1".equals(movimento)){
                break;
            }
            if(movimento.length() != 2){
                System.out.println("Posição inválida!");
                System.out.println();
            }
            else{
                /* Divide a String movimento em dois caracteres distintos,
                * coordenadasMovimento[0] = coluna
                * coordenadasMovimento[1] = linha */

                char[] coordenadasMovimento = movimento.toCharArray();

                /* coordenadasMovimento[1] é uma String representando 1 a 8,
                * então subtraímos 48 (caractere 0 na tabela ASCII) para
                * transformar em um inteiro representando a linha da posição.
                */
                if(tabuleiro.verificaLimitesTabuleiro(coordenadasMovimento[1], coordenadasMovimento[0])){
                    System.out.println("Um movimento para a posição " + movimento +
                            " é válido!\n");
                }
                else{
                    System.out.println("Posição inválida!\n");
                } 
            }
        }
        System.out.println();
        
        
        /* Classe Jogador */
        
        
        // TESTE 4
        System.out.println("Teste 4: Criando jogadores e determinando seus nomes e cores");
        
        System.out.print("Digite o nome de um jogador: ");
        nome1 = in.next();
        // Criando jogador branco
        Jogador jogadorTeste1 = new Jogador(nome1, true);
        System.out.println();
        
        System.out.print("Digite o nome de outro jogador: ");
        nome2 = in.next();
        // Criando jogador preto
        Jogador jogadorTeste2 = new Jogador(nome2, false);
        System.out.println();
        
        
        // TESTE 5
        System.out.println("Teste 5: verificando nome e cor dos jogadores através"
                + " dos métodos getters");
        
        // Jogador 1
        branco = jogadorTeste1.isBranco();
        cor = branco ? "brancas" : "pretas";
        
        System.out.println("O jogador " + jogadorTeste1.getNome() +
                " possui peças " + cor);
        System.out.println();
        
        // Jogador 2
        branco = jogadorTeste2.isBranco();
        cor = branco ? "brancas" : "pretas";
        
        System.out.println("O jogador " + jogadorTeste2.getNome() +
                " possui peças " + cor);
        System.out.println();
        
        
        /* Classe Posição */
        
        
        // TESTE 6
        System.out.println("Teste 6: Testando instâncias da classe Posição através"
                + " do método construtor");
        
        Posicao p1 = new Posicao(0, 'g', true); // linha inválida
        System.out.println();
        Posicao p2 = new Posicao(2, 'j', false); // coluna inválida
        System.out.println();
        Posicao p3 = new Posicao(9, 'z', false); // linha e coluna inválidas
        System.out.println();
        Posicao p4 = new Posicao(5, 'd', true); // posição válida
        Posicao p5 = new Posicao(8, 'a', false); // posição válida
        
        
        // TESTE 7
        System.out.println("Teste 7: Verificando atributos de posições válidas através"
                + " dos métodos getters");
        
        // Utilizaremos os objetos p4 e p5
        
        // Testando posição p4
        branco = p4.isBranco();
        cor = branco ? "branca" : "preta";
        
        ocupada = p4.isOcupada();
        ocupacao = ocupada ? "ocupada" : "desocupada";
        
        System.out.println("O objeto p4 é uma posição que possui linha " +
                p4.getLinha() + " e coluna " + p4.getColuna() + ", além de ser "
                        + cor + " e estar " + ocupacao);
        System.out.println();
        
        // Testando posição p5
        branco = p5.isBranco();
        cor = branco ? "branca" : "preta";
        
        ocupada = p5.isOcupada();
        ocupacao = ocupada ? "ocupada" : "desocupada";
        
        System.out.println("O objeto p5 é uma posição que possui linha " +
                p5.getLinha() + " e coluna " + p5.getColuna() + ", além de ser "
                        + cor + " e estar " + ocupacao);
        System.out.println();
        
        
        // TESTE 8
        System.out.println("Teste 8: Alterando estado de ocupação de uma posição válida"
                + " através do método setOcupada()");
        
        // Posição p4
        p4.setOcupada(true);
        ocupada = p4.isOcupada();
        ocupacao = ocupada ? "ocupada" : "desocupada";
        
        System.out.println("Agora o objeto p4 é uma posição " + ocupacao);
        
        // Posição p5
        p5.setOcupada(true);
        ocupada = p5.isOcupada();
        ocupacao = ocupada ? "ocupada" : "desocupada";
        
        System.out.println("Agora o objeto p5 é uma posição " + ocupacao);
        
        
        /* Classe Jogo */
        
        
        // TESTE 9
        System.out.println("Teste 9: Instanciando objeto da classe Jogo\n");
        
        /* Para instanciar um objeto da classe Jogo, utilizaremos os nomes dos
         * jogadores utilizados no teste 3
        */
        Jogo jogo = new Jogo(nome1, nome2);
        System.out.println();
        
        // TESTE 10
        System.out.println("Teste 10: Definindo e verificando o estado do jogo"
                + " através dos métodos setEstado() e getEstado()");
        
        for(int i = 0; i < 4; i++){
            jogo.setEstado(i);
            estadoJogo = jogo.getEstado();
            switch (estadoJogo) {
                case 1:
                    System.out.println("Início do jogo\n");
                    break;
                case 2:
                    System.out.println("Xeque\n");
                    break;
                case 3:
                    System.out.println("Xeque-mate\n");
                    break;
                default:
                    System.out.println("O estado " + i + " não existe!\n");
                    break;
            }
        }
        
        
        // TESTE 11
        System.out.println("Teste 11: Definindo e verificando a vez do jogador,"
                + " através dos métodos setVezRodada() e isVezRodada()");
        
        // Para cada movimento válido inserido, a vez é trocada
        while(true){
            vez = jogo.isVezRodada(); // true = branco joga; false = preto joga
            
            // recebe o nome do jogador branco ou do jogador preto
            jogadorRodada = vez ? "branco" : "preto";
            if(movimentoValido){
                System.out.println();
                System.out.println("É a sua vez, jogador " + jogadorRodada + "!");
            }
            System.out.print("Digite a posição para onde deseja se mover! (-1 para sair): ");
            movimento = in.next();
            
            if("-1".equals(movimento)){
                break;
            }
            if(movimento.length() != 2){
                movimentoValido = false;
                System.out.println("Posição inválida!");
                System.out.println();
            }
            else{
                char[] coordenadasMovimento = movimento.toCharArray();

                if(tabuleiro.verificaLimitesTabuleiro(coordenadasMovimento[1], coordenadasMovimento[0])){
                    jogo.setVezRodada(vez);
                    movimentoValido = true;
                }
                else{
                    movimentoValido = false;
                    System.out.println("Posição inválida!\n");
                } 
            }
        }
        
        // PARTE 2: TESTES DAS CLASSES DE PEÇAS ESPECÍFICAS
        
        
        /* Classe Bispo */
        
        
        // TESTE 12
        System.out.println("Teste 12: Instanciação de peças do tipo Bispo,"
                + " verificando sua cor e testando seu método desenho()");
        
        Bispo b1 = new Bispo(true, p4); // Instanciando bispo branco
        Bispo b2 = new Bispo(false, p5); // Instanciando bispo preto
        
        branco = b1.isBranco();
        cor = branco ? "branca" : "preta";
        System.out.println("O bispo p1 é de cor " + cor);
        
        branco = b2.isBranco();
        cor = branco ? "branca" : "preta";
        System.out.println("O bispo p2 é de cor " + cor);
        
        b1.desenho();
        System.out.println();
        b2.desenho();
        System.out.println("\n");
        
        
        // TESTE 13
        System.out.println("Teste 13: Checando movimento do bispo através da"
                + " função checaMovimento()");
        System.out.println();
        
        
        /* Classe Cavalo */
        
        
        // TESTE 14
        System.out.println("Teste 14: Instanciação de peças do tipo Cavalo,"
                + " verificando sua cor e testando seu método desenho()");
        
        Cavalo c1 = new Cavalo(true, p4); // Instanciando cavalo branco
        Cavalo c2 = new Cavalo(false, p5); // Instanciando cavalo preto
        
        branco = c1.isBranco();
        cor = branco ? "branca" : "preta";
        System.out.println("O cavalo c1 é de cor " + cor);
        
        branco = c2.isBranco();
        cor = branco ? "branca" : "preta";
        System.out.println("O cavalo c2 é de cor " + cor);
        
        c1.desenho();
        System.out.println();
        c2.desenho();
        System.out.println("\n");
        
        
        // TESTE 15
        System.out.println("Teste 15: Checando movimento do cavalo através da"
                + " função checaMovimento()");
        System.out.println();
        
        
        /* Classe Dama */
        
        
        // TESTE 16
        System.out.println("Teste 16: Instanciação de peças do tipo Dama,"
                + " verificando sua cor e testando seu método desenho()");
        
        Dama d1 = new Dama(true, p4); // Instanciando dama branca
        Dama d2 = new Dama(false, p5); // Instanciando dama preta
        
        branco = d1.isBranco();
        cor = branco ? "branca" : "preta";
        System.out.println("A dama d1 é de cor " + cor);
        
        branco = d2.isBranco();
        cor = branco ? "branca" : "preta";
        System.out.println("A dama d2 é de cor " + cor);
        
        d1.desenho();
        System.out.println();
        d2.desenho();
        System.out.println("\n");
        
        
        // TESTE 17
        System.out.println("Teste 17: Checando movimento da dama através da"
                + " função checaMovimento()");
        System.out.println();
        
        
        /* Classe Peão */
        
        
        // TESTE 18
        System.out.println("Teste 18: Instanciação de peças do tipo Peão,"
                + " verificando sua cor e testando seu método desenho()");
        
        Peao pe1 = new Peao(true, p4); // Instanciando peão branco
        Peao pe2 = new Peao(false, p5); // Instanciando peão preto
        
        branco = pe1.isBranco();
        cor = branco ? "branca" : "preta";
        System.out.println("O peão pe1 é de cor " + cor);
        
        branco = pe2.isBranco();
        cor = branco ? "branca" : "preta";
        System.out.println("O peão pe2 é de cor " + cor);
        
        pe1.desenho();
        System.out.println();
        pe2.desenho();
        System.out.println("\n");
        
        
        // TESTE 19
        System.out.println("Teste 19: Checando movimento do peão através da"
                + " função checaMovimento()");
        System.out.println();
        
        
        /* Classe Rei */
        
        
        // TESTE 20
        System.out.println("Teste 20: Instanciação de peças do tipo Rei,"
                + " verificando sua cor e testando seu método desenho()");
        
        Rei r1 = new Rei(true, p4); // Instanciando rei branco
        Rei r2 = new Rei(false, p5); // Instanciando rei preto
        
        branco = r1.isBranco();
        cor = branco ? "branca" : "preta";
        System.out.println("O rei r1 é de cor " + cor);
        
        branco = r2.isBranco();
        cor = branco ? "branca" : "preta";
        System.out.println("O rei r2 é de cor " + cor);
        
        r1.desenho();
        System.out.println();
        r2.desenho();
        System.out.println("\n");
        
        
        // TESTE 21
        System.out.println("Teste 21: Checando movimento do rei através da"
                + " função checaMovimento()");
        System.out.println();
        
        
        /* Classe Torre */
        
        
        // TESTE 22
        System.out.println("Teste 12: Instanciação de peças do tipo Torre,"
                + " verificando sua cor e testando seu método desenho()");
        
        Torre t1 = new Torre(true, p4); // Instanciando torre branca
        Torre t2 = new Torre(false, p5); // Instanciando torre preta
        
        branco = t1.isBranco();
        cor = branco ? "branca" : "preta";
        System.out.println("A torre t1 é de cor " + cor);
        
        branco = t2.isBranco();
        cor = branco ? "branca" : "preta";
        System.out.println("A torre t2 é de cor " + cor);
        
        t1.desenho();
        System.out.println();
        t2.desenho();
        System.out.println("\n");
        
        
        // TESTE 23
        System.out.println("Teste 23: Checando movimento da torre através da"
                + " função checaMovimento()");
        System.out.println();
    }
}
