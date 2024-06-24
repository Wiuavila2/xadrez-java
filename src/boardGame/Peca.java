package boardGame;

public class Peca {
//será protected pois e de matriz não deve ser visivel no xadrez
	protected Position position;
	private Tabuleiro tabuleiro;
	
	public Peca(Tabuleiro tabileiro) {
		this.tabuleiro = tabileiro;
		position = null;
	}
//só a classe/subclasses poderão acessar o tabuleiro , só as peças/ tabuleiro podem acessar
	protected Tabuleiro getTabuleiro() {
		return tabuleiro;
	}
	
}
