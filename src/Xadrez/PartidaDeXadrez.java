package Xadrez;

import java.util.ArrayList;
import java.util.List;

import Xadrez.pecas.Rei;
import Xadrez.pecas.Torre;
import boardGame.Peca;
import boardGame.Position;
import boardGame.Tabuleiro;

public class PartidaDeXadrez {

	private int turno;
	private Cor jogadorAtual;
	private Tabuleiro tabuleiro;
	
	private List<Peca> pecasNoTabuleiro = new ArrayList<>();
	private List<Peca> pecasCapturadas = new ArrayList<>();

	public PartidaDeXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		turno = 1;
		jogadorAtual = Cor.BRANCO;
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
	
	public boolean[][] movimentosPossiveis(XadrezPosition posicaoFonte){
		Position position = posicaoFonte.ParaPosicao();
		validarPosicaoDeOrigem(position);
		return tabuleiro.peca(position).movimentosPossiveis();
	}
	
	private Peca fazerMovimento(Position fonte, Position alvo){
		Peca p = tabuleiro.removerPeca(fonte);
		Peca pecaCapturada = tabuleiro.removerPeca(alvo);
		tabuleiro.posicionarPeca(p, alvo);
		
		if(pecaCapturada != null) {
			pecasNoTabuleiro.remove(pecaCapturada);
			pecasCapturadas.add(pecaCapturada);
		}
		return pecaCapturada;
	}
	public PecaDeXadrez realizarMovimentoDeXadrez(XadrezPosition posicaoFonte, XadrezPosition posicaoAlvo){
		Position fonte = posicaoFonte.ParaPosicao();
		Position alvo = posicaoAlvo.ParaPosicao();
		validarPosicaoDeOrigem(fonte);
		validarPosicaoDeDestino(fonte, alvo);
		Peca pecaCapturada = fazerMovimento(fonte , alvo);
		proximoTurno();
		return (PecaDeXadrez)pecaCapturada;
	}
	
	private void validarPosicaoDeOrigem(Position position) {
		if(!tabuleiro.temUmaPeca(position)) {
			throw new XadrezException("não existe peça na posição de origem !");
		}
		if(jogadorAtual != ((PecaDeXadrez)tabuleiro.peca(position)).getCor()) {
			throw new XadrezException("A peça escolhida não é sua");
		}
		if(!tabuleiro.peca(position).temAlgumMovimentoPossivel()) {
			throw new XadrezException("não existem movimentos possíveis para a peça escolhida !");
		}
	}
	
	private void validarPosicaoDeDestino(Position fonte, Position alvo) {
		if(!tabuleiro.peca(fonte).movimentoPossivel(alvo)) {
			throw new XadrezException("A peça escolhida não pode se mover para o alvo selecionado!"); 
		}
	}
	
	private void proximoTurno() {
		turno++;
		jogadorAtual = (jogadorAtual == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO; 
	}
	private void ColocarNovaPeca(char coluna, int linha, PecaDeXadrez peca) {
		//paraposição converte em matriz
		tabuleiro.posicionarPeca(peca, new XadrezPosition(coluna, linha).ParaPosicao());
		pecasNoTabuleiro.add(peca);
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
	
	public int getTurno() {
		return turno;
	}

	public Cor getJogadorAtual() {
		return jogadorAtual;
	}
	
}
