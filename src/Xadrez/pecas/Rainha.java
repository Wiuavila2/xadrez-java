package Xadrez.pecas;

import Xadrez.Cor;
import Xadrez.PecaDeXadrez;
import boardGame.Position;
import boardGame.Tabuleiro;

public class Rainha extends PecaDeXadrez {
	public Rainha(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString() {
		//letra que será impressa no tabuleiro
		return "Q";
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		// para facilitar crio uma posição auxiliar
		Position p = new Position(0, 0);

		// acima(mantem coluna e reduz a linha)(-1 porque é acima)
		p.setValores(position.getLinha() - 1, position.getColuna());
		while (getTabuleiro().positionExiste(p) && !getTabuleiro().temUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() - 1);
		}
		if (getTabuleiro().positionExiste(p) && temAlgumaPecaInimiga(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// esquerda
		p.setValores(position.getLinha(), position.getColuna() - 1);
		while (getTabuleiro().positionExiste(p) && !getTabuleiro().temUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() - 1);
		}
		if (getTabuleiro().positionExiste(p) && temAlgumaPecaInimiga(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// direita
		p.setValores(position.getLinha(), position.getColuna() + 1);
		while (getTabuleiro().positionExiste(p) && !getTabuleiro().temUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() + 1);
		}
		if (getTabuleiro().positionExiste(p) && temAlgumaPecaInimiga(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// abaixo
		p.setValores(position.getLinha() + 1, position.getColuna());
		while (getTabuleiro().positionExiste(p) && !getTabuleiro().temUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() + 1);
		}
		if (getTabuleiro().positionExiste(p) && temAlgumaPecaInimiga(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// noroeste
		p.setValores(position.getLinha() - 1, position.getColuna() - 1);
		while (getTabuleiro().positionExiste(p) && !getTabuleiro().temUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() - 1, p.getColuna() - 1);
		}
		if (getTabuleiro().positionExiste(p) && temAlgumaPecaInimiga(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// nordeste
		p.setValores(position.getLinha() - 1, position.getColuna() + 1);
		while (getTabuleiro().positionExiste(p) && !getTabuleiro().temUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() - 1, p.getColuna() + 1);
		}
		if (getTabuleiro().positionExiste(p) && temAlgumaPecaInimiga(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// sudeste
		p.setValores(position.getLinha() + 1, position.getColuna() + 1);
		while (getTabuleiro().positionExiste(p) && !getTabuleiro().temUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() + 1, p.getColuna() + 1);
		}
		if (getTabuleiro().positionExiste(p) && temAlgumaPecaInimiga(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// sudoeste
		p.setValores(position.getLinha() + 1, position.getColuna() - 1);
		while (getTabuleiro().positionExiste(p) && !getTabuleiro().temUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() + 1, p.getColuna() - 1);
		}
		if (getTabuleiro().positionExiste(p) && temAlgumaPecaInimiga(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		return mat;
	}

}
