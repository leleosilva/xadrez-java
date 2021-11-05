package pecasxadrez;

public abstract class Peca {
    protected boolean branco;
    protected boolean capturada;
    
    protected Peca(boolean branco){
        this.branco = branco;
        this.capturada = false;
    }
    
    abstract public void desenho();
    
    abstract public boolean checaMovimento(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino);
    
    public boolean isBranco() {
        return branco;
    }

    public boolean isCapturada() {
        return capturada;
    }

    public void setCapturada(boolean capturada) {
        this.capturada = capturada;
    }
}
