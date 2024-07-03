package Xadrez;

import boardGame.Peca;
import boardGame.Position;
import boardGame.Tabuleiro;

public abstract class PecaDeXadrez extends Peca {
	
	public PecaDeXadrez(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	private Cor cor;

	public Cor getCor() {
		return cor;
	}
	
	protected boolean temAlgumaPecaInimiga(Position position ) {
		PecaDeXadrez p = (PecaDeXadrez) getTabuleiro().peca(position);
		return p != null && p.getCor() != cor;
	}
}
