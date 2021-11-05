package jogoxadrez;

public class IntervaloInvalido extends Exception {
    private int linha;
    private char coluna;
    
    public IntervaloInvalido(int linha, char coluna){
        super();
        this.linha = linha;
        this.coluna = coluna;
    }

    @Override
    public String toString() {
        if((linha < 1 || linha > 8) && (coluna < 'a' || coluna > 'h')){
            return "A linha e coluna estão fora do intervalo!";
        }
        else if(linha < 1 || linha > 8){
            return "A linha está fora do intervalo!";
        }
        return "A coluna está fora do intervalo!";
    }
}
