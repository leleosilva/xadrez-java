package jogoxadrez;

import pecasxadrez.Peca;

public class Posicao {
    private int linha;
    private char coluna;
    private boolean branco;
    private boolean ocupada;
    private Peca peca;
    
    public Posicao(int linha, char coluna, boolean branco){
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

    public int getLinha() {
        return linha;
    }

    public char getColuna() {
        return coluna;
    }

    public boolean isBranco() {
        return branco;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    public Peca getPeca() {
        return peca;
    }
    
    public void setPeca(Peca peca) {
        this.peca = peca;
    }
}
