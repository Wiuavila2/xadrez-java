package Xadrez.pecas;

import Xadrez.Cor;
import Xadrez.PecaDeXadrez;
import boardGame.Tabuleiro;

public class Torre extends PecaDeXadrez {

	public Torre(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString() {
		//letra que será impressa no tabuleiro
		return "T";
	}

}
