package Xadrez.pecas;

import Xadrez.Cor;
import Xadrez.PecaDeXadrez;
import boardGame.Position;
import boardGame.Tabuleiro;

public class Peao extends PecaDeXadrez {

	public Peao(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		//para facilitar crio uma posição auxiliar 
		Position p = new Position(0, 0);
		
		if(getCor() == Cor.BRANCO) {
			p.setValores(position.getLinha() - 1, position.getColuna());
			if(getTabuleiro().positionExiste(p) && !getTabuleiro().temUmaPeca(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
				p.setValores(position.getLinha() - 2, position.getColuna());
				Position p2= new Position(position.getLinha() - 1, position.getColuna());
				if(getTabuleiro().positionExiste(p) && !getTabuleiro().temUmaPeca(p) && getTabuleiro().positionExiste(p2) && !getTabuleiro().temUmaPeca(p2) && getMoveCount() == 0) {
					mat[p.getLinha()][p.getColuna()] = true;
				}
				p.setValores(position.getLinha() - 1, position.getColuna() -1);
				if(getTabuleiro().positionExiste(p) && temAlgumaPecaInimiga(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
				}
				p.setValores(position.getLinha() - 1, position.getColuna() +1);
				if(getTabuleiro().positionExiste(p) && temAlgumaPecaInimiga(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
				}
		}else {
			p.setValores(position.getLinha() + 1, position.getColuna());
			if(getTabuleiro().positionExiste(p) && !getTabuleiro().temUmaPeca(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
				p.setValores(position.getLinha() + 2, position.getColuna());
				Position p2= new Position(position.getLinha() + 1, position.getColuna());
				if(getTabuleiro().positionExiste(p) && !getTabuleiro().temUmaPeca(p) && getTabuleiro().positionExiste(p2) && !getTabuleiro().temUmaPeca(p2) && getMoveCount() == 0) {
					mat[p.getLinha()][p.getColuna()] = true;
				}
				p.setValores(position.getLinha() + 1, position.getColuna() -1);
				if(getTabuleiro().positionExiste(p) && temAlgumaPecaInimiga(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
				}
				p.setValores(position.getLinha() + 1, position.getColuna() +1);
				if(getTabuleiro().positionExiste(p) && temAlgumaPecaInimiga(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
				}
		}
		return mat;
	}
	
	@Override
	public String toString() {
		return "P";
	}

}
