package Xadrez;

import Xadrez.pecas.Rei;
import Xadrez.pecas.Torre;
import boardGame.Peca;
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
	
	private Peca fazerMovimento(Position fonte, Position alvo){
		Peca p = tabuleiro.removerPeca(fonte);
		Peca pecaCapturada = tabuleiro.removerPeca(alvo);
		tabuleiro.posicionarPeca(p, alvo);
		return pecaCapturada;
	}
	public PecaDeXadrez realizarMovimentoDeXadrez(XadrezPosition posicaoFonte, XadrezPosition posicaoAlvo){
		Position fonte = posicaoFonte.ParaPosicao();
		Position alvo = posicaoAlvo.ParaPosicao();
		validarPosicaoDeOrigem(fonte);
		Peca pecaCapturada = fazerMovimento(fonte , alvo);
		return (PecaDeXadrez)pecaCapturada;
	}
	
	private void validarPosicaoDeOrigem(Position position) {
		if(!tabuleiro.temUmaPeca(position)) {
			throw new XadrezException("não existe peça na posição de origem !");
		}
		if(!tabuleiro.peca(position).temAlgumMovimentoPossivel()) {
			throw new XadrezException("não existem movimentos possíveis para a peça escolhida !");
		}
	}
	private void ColocarNovaPeca(char coluna, int linha, PecaDeXadrez peca) {
		//paraposição converte em matriz
		tabuleiro.posicionarPeca(peca, new XadrezPosition(coluna, linha).ParaPosicao());
	}
	
	private void setupInicial() {
		ColocarNovaPeca('c', 1, new Torre(tabuleiro, Cor.BRANCO));
		ColocarNovaPeca('c', 2, new Torre(tabuleiro, Cor.BRANCO));
		ColocarNovaPeca('d', 2, new Torre(tabuleiro, Cor.BRANCO));
		ColocarNovaPeca('e', 2, new Torre(tabuleiro, Cor.BRANCO));
		ColocarNovaPeca('e', 1, new Torre(tabuleiro, Cor.BRANCO));
        ColocarNovaPeca('d', 1, new Rei(tabuleiro, Cor.BRANCO));

        ColocarNovaPeca('c', 7, new Torre(tabuleiro, Cor.PRETO));
        ColocarNovaPeca('c', 8, new Torre(tabuleiro, Cor.PRETO));
        ColocarNovaPeca('d', 7, new Torre(tabuleiro, Cor.PRETO));
        ColocarNovaPeca('e', 7, new Torre(tabuleiro, Cor.PRETO));
        ColocarNovaPeca('e', 8, new Torre(tabuleiro, Cor.PRETO));
        ColocarNovaPeca('d', 8, new Rei(tabuleiro, Cor.PRETO));
	}
	
	
}
