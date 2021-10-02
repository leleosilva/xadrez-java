package jogoxadrez;

public class Posicao {
    private int linha;
    private int coluna;
    private String cor;
    private boolean ocupada;
    
    public Posicao(int linha, int coluna){
        this.linha = linha;
        this.coluna = coluna;
        this.ocupada = false;
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

    public String getCor() {
        return cor;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }
}
