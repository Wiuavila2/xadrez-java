package Xadrez;

import Xadrez.pecas.Rei;
import Xadrez.pecas.Torre;
import boardGame.Position;
import boardGame.Tabuleiro;

public class PartidaDeXadrez {

	private Tabuleiro tabuleiro;

	public PartidaDeXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		setupInicial();
	}
	
	public PecaDeXadrez[][] getPecas(){
		PecaDeXadrez[][] mat = new PecaDeXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for(int i=0; i < tabuleiro.getLinhas();i++) {
			for(int j=0; j < tabuleiro.getColunas();j++) {
				mat[i][j] = (PecaDeXadrez) tabuleiro.peca(i,j);
			}
		}
		return mat;
	}
	
	private void setupInicial() {
		tabuleiro.posicionarPeca(new Torre(tabuleiro, Cor.BRANCO), new Position(2, 1));
		tabuleiro.posicionarPeca(new Rei(tabuleiro, Cor.PRETO), new Position(0, 4));
		tabuleiro.posicionarPeca(new Rei(tabuleiro, Cor.BRANCO), new Position(7, 4));
	}
	
	
}
