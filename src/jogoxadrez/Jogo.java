package jogoxadrez;

public class Jogo {
    private Tabuleiro tabuleiro;
    private Jogador jogador1;
    private Jogador jogador2;
    // estado 1 = início; estado 2 = xeque; estado 3 = xeque-mate
    private int estado;
    private boolean vezRodada;
    
    /* construtor */
    public Jogo (String nome1, String nome2){
        this.tabuleiro = new Tabuleiro();
        this.jogador1 = new Jogador(nome1, true);
        this.jogador2 = new Jogador(nome2, false);
        this.estado = 1;
        this.vezRodada = true;
        System.out.println("O objeto Jogo foi instanciado com sucesso!");
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        if(estado < 1 && estado > 3){
            System.out.println("Estado inválido!");
        }
        else{
            this.estado = estado;
        }
    }

    public boolean isVezRodada() {
        return vezRodada;
    }

    public void setVezRodada(boolean vezRodada) {
        /* Se vezRodada é true (preto joga), então vezRodada recebe o valor lógico
         * oposto (false, branco joga). Caso vezRodada seja false, o mesmo acontece.
        */
        this.vezRodada = !this.vezRodada;
    }
    
}
