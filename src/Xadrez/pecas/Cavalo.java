package Xadrez.pecas;

import Xadrez.Cor;
import Xadrez.PecaDeXadrez;
import boardGame.Position;
import boardGame.Tabuleiro;

public class Cavalo extends PecaDeXadrez {

	public Cavalo(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString() {
		//letra que será impressa no tabuleiro
		return "C";
	}
	
	//metodo auxiliar para ajudar na implantação
	private boolean podeMover(Position position) {
		PecaDeXadrez p = (PecaDeXadrez)getTabuleiro().peca(position);
		return p == null || p.getCor() != getCor();
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Position p = new Position(0, 0);

		// acima
		p.setValores(position.getLinha() - 1, position.getColuna() -2);
		if (getTabuleiro().positionExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// abaixo
		p.setValores(position.getLinha() - 2, position.getColuna() - 1);
		if (getTabuleiro().positionExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// esquerda
		p.setValores(position.getLinha() -2, position.getColuna() + 1);
		if (getTabuleiro().positionExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// direita
		p.setValores(position.getLinha() -1, position.getColuna() + 2);
		if (getTabuleiro().positionExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// noroeste
		p.setValores(position.getLinha() + 1, position.getColuna() + 2);
		if (getTabuleiro().positionExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// nordeste
		p.setValores(position.getLinha() + 2, position.getColuna() + 1);
		if (getTabuleiro().positionExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// sudoeste
		p.setValores(position.getLinha() + 2, position.getColuna() - 1);
		if (getTabuleiro().positionExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// sudeste
		p.setValores(position.getLinha() + 1, position.getColuna() - 2);
		if (getTabuleiro().positionExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		return mat;
	}

}
