package Xadrez;

import boardGame.Peca;
import boardGame.Tabuleiro;

public class PecaDeXadrez extends Peca {
	
	public PecaDeXadrez(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	private Cor cor;

	public Cor getCor() {
		return cor;
	}
	
}
