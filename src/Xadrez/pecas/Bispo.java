package Xadrez.pecas;

import Xadrez.Cor;
import Xadrez.PecaDeXadrez;
import boardGame.Position;
import boardGame.Tabuleiro;

public class Bispo extends PecaDeXadrez{

	public Bispo(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString() {
		//letra que será impressa no tabuleiro
		return "B";
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		//para facilitar crio uma posição auxiliar 
		Position p = new Position(0, 0);
		
		//noroeste
		p.setValores(position.getLinha() - 1, position.getColuna() -1);
		while(getTabuleiro().positionExiste(p) && !getTabuleiro().temUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() - 1, p.getColuna() - 1);
		}
		if(getTabuleiro().positionExiste(p) && temAlgumaPecaInimiga(p)){
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//nordeste
		p.setValores(position.getLinha() -1, position.getColuna() + 1);
		while(getTabuleiro().positionExiste(p) && !getTabuleiro().temUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() - 1, p.getColuna() + 1);
		}
		if(getTabuleiro().positionExiste(p) && temAlgumaPecaInimiga(p)){
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//sudeste
		p.setValores(position.getLinha() +1, position.getColuna() + 1);
		while(getTabuleiro().positionExiste(p) && !getTabuleiro().temUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() + 1, p.getColuna()+ 1);
		}
		if(getTabuleiro().positionExiste(p) && temAlgumaPecaInimiga(p)){
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//sudoeste
		p.setValores(position.getLinha() + 1, position.getColuna() -1);
		while(getTabuleiro().positionExiste(p) && !getTabuleiro().temUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() + 1, p.getColuna() - 1);
		}
		if(getTabuleiro().positionExiste(p) && temAlgumaPecaInimiga(p)){
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		return mat;
	}

}
