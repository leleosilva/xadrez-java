package jogoxadrez;

public class Posicao {
    private int linha;
    private char coluna;
    private boolean branco;
    private boolean ocupada;
    
    public Posicao(int linha, char coluna, boolean branco){
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
                this.branco = branco;
                this.ocupada = false;
                System.out.println("O objeto Posição foi instanciado com sucesso!");
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
}
