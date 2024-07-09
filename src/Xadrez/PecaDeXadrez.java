package Xadrez;

import boardGame.Peca;
import boardGame.Position;
import boardGame.Tabuleiro;

public abstract class PecaDeXadrez extends Peca {
	
	private Cor cor;
	private int moveCount;

	public PecaDeXadrez(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}


	public Cor getCor() {
		return cor;
	}
	
	public int getMoveCount() {
		return moveCount;
	}


	public void aumentarMoveCount() {
		moveCount ++;
	}
	
	public void diminuirMoveCount() {
		moveCount --;
	}
	
	public XadrezPosition getXadrezPosition() {
		return XadrezPosition.DaPosicao(position);
	}
	
	protected boolean temAlgumaPecaInimiga(Position position ) {
		PecaDeXadrez p = (PecaDeXadrez) getTabuleiro().peca(position);
		return p != null && p.getCor() != cor;
	}
}
