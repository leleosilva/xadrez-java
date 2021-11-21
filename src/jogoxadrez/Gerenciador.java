/* ALUNOS
 * Cinthia Souza Costa - 792173
 * Leonardo Cavalcante da Silva - 792190
 */

package jogoxadrez;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Gerenciador {
    
    public static void main(String args[]) {
        
        Scanner in = new Scanner(System.in);
        
        int opcao, opcaoCor;
        String nome1, nome2, nomeArquivo;
        boolean corEscolhida = false;
        boolean jogador1ComPecasBrancas = true;
        
        while(true){
            
            System.out.println("\nSejam bem-vindos ao gerenciador de partidas! Escolha uma opção abaixo:");
            System.out.println();
            System.out.println("0 - Encerrar o gerenciador de partidas");
            System.out.println("1 - Iniciar nova partida");
            System.out.println("2 - Retomar o progresso de uma partida salva em um arquivo");
            
            try{
                System.out.print("Opção desejada: ");
                opcao = in.nextInt();
                in.nextLine();
            }
            catch(InputMismatchException ex){
                System.out.println("\nOpção inválida!\n");
                in.nextLine();
                continue;
            }

            switch(opcao){
                case 0:
                    in.close();
                    System.exit(0);
                    break;
                    
                case 1:
                    System.out.println("\nPor favor, informe o nome do primeiro jogador!");
                    System.out.print("Nome do primeiro jogador: ");
                    nome1 = in.nextLine();
                    
                    while(!corEscolhida){
                        System.out.println("\nEscolha a cor de suas peças, " + nome1 + "!");
                        System.out.println();
                        System.out.println("1 - Peças brancas");
                        System.out.println("2 - Peças pretas");
                        try{
                            System.out.print("Cor desejada: ");
                            opcaoCor = in.nextInt();
                            in.nextLine();
                        }
                        catch(InputMismatchException ex){
                            System.out.println("\nOpção inválida!\n");
                            in.nextLine();
                            continue;
                        }
                        
                        switch(opcaoCor){
                            case 1:
                                corEscolhida = true;
                                jogador1ComPecasBrancas = true;
                                break;
                            
                            case 2:
                                corEscolhida = true;
                                jogador1ComPecasBrancas = false;
                                break;
                            
                            default:
                                System.out.println("\nOpção inválida!\n");
                                break;
                        }
                    }
                    
                    System.out.println("\nAgora informe o nome do segundo jogador!");
                    System.out.print("Nome do segundo jogador: ");
                    nome2 = in.nextLine();
                    
                    if(jogador1ComPecasBrancas){
                        Jogo novoJogo = new Jogo(nome1, nome2);
                        novoJogo.partida();
                    }
                    else{
                        Jogo novoJogo = new Jogo(nome2, nome1);
                        novoJogo.partida();
                    }
                    break;
                    
                case 2:
                    System.out.println("\nInsira o nome do arquivo texto de onde você deseja carregar"
                            + " o progresso de sua partida! (-1 para voltar para o gerenciador de partidas)");
                    while(true){
                        System.out.print("Nome do arquivo: ");
                        nomeArquivo = in.nextLine() + ".txt";
                        
                        if(nomeArquivo.equals("-1.txt")){
                            break;
                        }
                        
                        File save = new File(nomeArquivo);

                        if(save.exists()){
                            Jogo jogoExistente = new Jogo(save);
                            jogoExistente.partida();
                            break;
                        }
                        else{
                            System.out.println("\nArquivo não encontrado! Insira o nome de um arquivo existente ou digite -1 para voltar para o gerenciador de partidas!");
                        }
                    }
                    break;
                    
                default:
                    System.out.println("\nOpção inválida!\n");
                    break;      
            }
        }
    }
}
