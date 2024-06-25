package Xadrez.pecas;

import Xadrez.Cor;
import Xadrez.PecaDeXadrez;
import boardGame.Tabuleiro;

public class Rei extends PecaDeXadrez {

	public Rei(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString() {
		//letra que será impressa no tabuleiro
		return "R";
	}
	

}
