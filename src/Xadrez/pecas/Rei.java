package Xadrez.pecas;

import Xadrez.Cor;
import Xadrez.PartidaDeXadrez;
import Xadrez.PecaDeXadrez;
import boardGame.Position;
import boardGame.Tabuleiro;

public class Rei extends PecaDeXadrez {

	private PartidaDeXadrez partidaDeXadrez;

	public Rei(Tabuleiro tabuleiro, Cor cor, PartidaDeXadrez partidaDeXadrez) {

		super(tabuleiro, cor);
		this.partidaDeXadrez = partidaDeXadrez;
	}

	@Override
	public String toString() {
		//letra que será impressa no tabuleiro
		return "R";
	}
	
	//metodo auxiliar para ajudar na implantação
	private boolean podeMover(Position position) {
		PecaDeXadrez p = (PecaDeXadrez)getTabuleiro().peca(position);
		return p == null || p.getCor() != getCor();
	}

	private boolean testHookCastling(Position position){
		PecaDeXadrez p = (PecaDeXadrez)getTabuleiro().peca(position);
		return p != null && p instanceof Torre && p.getCor() == getCor() && p.getMoveCount() == 0;
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Position p = new Position(0, 0);

		// acima
		p.setValores(position.getLinha() - 1, position.getColuna());
		if (getTabuleiro().positionExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// abaixo
		p.setValores(position.getLinha() + 1, position.getColuna());
		if (getTabuleiro().positionExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// esquerda
		p.setValores(position.getLinha(), position.getColuna() - 1);
		if (getTabuleiro().positionExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// direita
		p.setValores(position.getLinha(), position.getColuna() + 1);
		if (getTabuleiro().positionExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// noroeste
		p.setValores(position.getLinha() - 1, position.getColuna() - 1);
		if (getTabuleiro().positionExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// nordeste
		p.setValores(position.getLinha() - 1, position.getColuna() + 1);
		if (getTabuleiro().positionExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// sudoeste
		p.setValores(position.getLinha() + 1, position.getColuna() - 1);
		if (getTabuleiro().positionExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// sudeste
		p.setValores(position.getLinha() + 1, position.getColuna() + 1);
		if (getTabuleiro().positionExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		//movimento especial castling
		if(getMoveCount() == 0 && !partidaDeXadrez.getCheck()) {
			//lado do rei
			Position posT1 = new Position(position.getLinha(), position.getColuna() + 3);
			if(testHookCastling(posT1)) {
				Position p1 = new Position(position.getLinha(), position.getColuna() + 1);
				Position p2 = new Position(position.getLinha(), position.getColuna() + 2);
					if(getTabuleiro().peca(p1) == null && getTabuleiro().peca(p2) == null) {
						mat[position.getLinha()][position.getColuna() + 2] = true;
					}
			}
			//rook do lado da rainha
			Position posT2 = new Position(position.getLinha(), position.getColuna() - 4);
			if(testHookCastling(posT2)) {
				Position p1 = new Position(position.getLinha(), position.getColuna() - 1);
				Position p2 = new Position(position.getLinha(), position.getColuna() - 2);
				Position p3 = new Position(position.getLinha(), position.getColuna() - 3);
				if (getTabuleiro().peca(p1) == null && getTabuleiro().peca(p2) == null && getTabuleiro().peca(p3) == null) {
					mat[position.getLinha()][position.getColuna() - 2] = true;
				}
			}
		}
		return mat;
	}

}
