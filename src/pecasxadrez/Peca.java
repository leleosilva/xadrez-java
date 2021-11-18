package pecasxadrez;

public abstract class Peca {
    protected boolean branco;
    protected boolean capturada;
    
    // Construtor da classe Peca
    protected Peca(boolean branco){
        this.branco = branco;
        this.capturada = false;
    }
    
    /* Método abstrato que retorna a representação visual de uma peça;
     * é implementado pelas subclasses de Peca */
    abstract public String desenho();
    
    /* Método abstrato que verifica a movimentação de um tipo de peça;
     * é implementado pelas subclasses de Peca */
    abstract public boolean checaMovimento(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino);
    
    // Método getter que retorna a cor de uma peça (branca = true; preta = false)
    public boolean isBranco() {
        return branco;
    }
    
    /* Método getter que retorna o estado de captura de uma peça
     * (capturada = true; não capturada = false) */
    public boolean isCapturada() {
        return capturada;
    }
    
    // Método setter que define o estado de captura de uma peça
    public void setCapturada(boolean capturada) {
        this.capturada = capturada;
    }
}
