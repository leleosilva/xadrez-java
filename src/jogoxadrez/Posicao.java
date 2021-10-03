package jogoxadrez;

public class Posicao {
    private int linha;
    private char coluna;
    private boolean cor;
    private boolean ocupada;
    
    public Posicao(int linha, char coluna, boolean cor){
        if(linha < 1 || linha > 8) {
            System.out.println("A linha escolhida é inválida!");
        }
        if(coluna < 'a' || coluna > 'h'){
            System.out.println("A coluna escolhida é inválida!");
        }
        else if(linha >= 1 && linha <= 8){
            if(coluna >= 'a' && coluna <= 'h'){
                this.linha = linha;
                this.coluna = coluna;
                this.cor = cor;
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

    public boolean getCor() {
        return cor;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }
}
