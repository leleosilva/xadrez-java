package jogoxadrez;

import pecasxadrez.Peca;

public class Posicao {
    private int linha;
    private char coluna;
    private boolean branco; // cor?
    private boolean ocupada;
    private Peca peca;
    
    // Construtor da classe Posicao
    public Posicao(int linha, char coluna, boolean branco){
        // ???????????????????
        if(linha < 1 || linha > 8){
        }
        if(coluna < 'a' || coluna > 'h'){
        }
        
        else if(linha >= 1 && linha <= 8){
            if(coluna >= 'a' && coluna <= 'h'){
                this.linha = linha;
                this.coluna = coluna;
                this.branco = branco;
                this.ocupada = false;
            }
        }
    }
    
    // Método getter que retorna a linha de uma posição
    public int getLinha() {
        return linha;
    }
    
    // Método getter que retorna a coluna de uma posição
    public char getColuna() {
        return coluna;
    }
    
    // Método getter que retorna a cor 
    public boolean isBranco() {
        return branco;
    }
    
    /* Método getter que retorna o estado de ocupação de uma posição
     * (ocupada = true; não ocupada = false) */
    public boolean isOcupada() {
        return ocupada;
    }
    
    // Método getter que determina o estado de ocupação de uma posição
    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }
    
    // Método getter que retorna a peça que está em uma posição (ou null, caso esteja vazia)
    public Peca getPeca() {
        return peca;
    }
    
    // Método setter que insere uma peça em uma posição
    public void setPeca(Peca peca) {
        this.peca = peca;
    }
}
