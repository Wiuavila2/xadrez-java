package Xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Xadrez.pecas.Peao;
import Xadrez.pecas.Rei;
import Xadrez.pecas.Torre;
import boardGame.Peca;
import boardGame.Position;
import boardGame.Tabuleiro;

public class PartidaDeXadrez {

	private int turno;
	private Cor jogadorAtual;
	private Tabuleiro tabuleiro;
	private boolean check;
	private boolean checkMate;
	
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
		PecaDeXadrez p = (PecaDeXadrez)tabuleiro.removerPeca(fonte);
		p.aumentarMoveCount();
		Peca pecaCapturada = tabuleiro.removerPeca(alvo);
		tabuleiro.posicionarPeca(p, alvo);
		
		if(pecaCapturada != null) {
			pecasNoTabuleiro.remove(pecaCapturada);
			pecasCapturadas.add(pecaCapturada);
		}
		return pecaCapturada;
	}
	
	public void desfazerMovimento(Position fonte,Position destino, Peca pecaCapturada) {
		PecaDeXadrez p = (PecaDeXadrez)tabuleiro.removerPeca(destino);
		p.diminuirMoveCount();
		tabuleiro.posicionarPeca(p, fonte);
		if(pecaCapturada != null) {
			tabuleiro.posicionarPeca(pecaCapturada, destino);
			pecasCapturadas.remove(pecaCapturada);
			pecasNoTabuleiro.add(pecaCapturada);
		}
	}
	public PecaDeXadrez realizarMovimentoDeXadrez(XadrezPosition posicaoFonte, XadrezPosition posicaoAlvo){
		Position fonte = posicaoFonte.ParaPosicao();
		Position alvo = posicaoAlvo.ParaPosicao();
		validarPosicaoDeOrigem(fonte);
		validarPosicaoDeDestino(fonte, alvo);
		Peca pecaCapturada = fazerMovimento(fonte , alvo);
		
		if(testarCheck(jogadorAtual)) {
			desfazerMovimento(fonte, alvo, pecaCapturada);
			throw new XadrezException("Este movimento te coloca em check ");
		}
		
		check = (testarCheck(oponente(jogadorAtual))) ? true : false;
		
		if(testarCheckMate(oponente(jogadorAtual))) {
			checkMate = true;
		}else {
			proximoTurno();			
		}
		return (PecaDeXadrez)pecaCapturada;
	}
	
	public boolean getCheck() {
		return check;
	}
	
	public boolean getCheckMate() {
		return checkMate;
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
	
	private Cor oponente(Cor cor) {
		return (cor == Cor.BRANCO)? Cor.PRETO : Cor.BRANCO;
	}
	
	private PecaDeXadrez rei(Cor cor) {
		List<Peca> list = pecasNoTabuleiro.stream().filter(x -> ((PecaDeXadrez)x).getCor() == cor).collect(Collectors.toList());
		for(Peca p: list) {
			if(p instanceof Rei) {
				return (PecaDeXadrez)p;
			}
		}
		throw new IllegalStateException("Não existe o rei da cor :" + cor + " no tabuleiro");
	}
	
	private boolean testarCheck(Cor cor) {
		Position reiPosition = rei(cor).getXadrezPosition().ParaPosicao();
		List <Peca> pecasDoOponete = pecasNoTabuleiro.stream().filter(x -> ((PecaDeXadrez)x).getCor() == oponente(cor)).collect(Collectors.toList());
		for(Peca p : pecasDoOponete) {
			boolean[][] mat = p.movimentosPossiveis();
			if(mat[reiPosition.getLinha()][reiPosition.getColuna()]) {
				return true;
			}
		}
		return false;
	}
	
	private boolean testarCheckMate(Cor cor) {
		if(!testarCheck(cor)) {
			return false;
		}
		List<Peca> list = pecasNoTabuleiro.stream().filter(x -> ((PecaDeXadrez)x).getCor() == cor).collect(Collectors.toList());
		for(Peca p : list) {
			boolean[][] mat = p.movimentosPossiveis();
			for(int i = 0; i<tabuleiro.getLinhas(); i ++) {
				for(int j = 0; j<tabuleiro.getColunas(); j ++) {
					if(mat[i][j]) {
						//não posso usar o .position pois ele é protected
						Position fonte = ((PecaDeXadrez)p).getXadrezPosition().ParaPosicao();
						Position alvo = new Position(i, j); 
						Peca pecaCapturada = fazerMovimento(fonte, alvo);
						boolean testCheck = testarCheck(cor);
						desfazerMovimento(fonte, alvo, pecaCapturada);
						if(!testCheck) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	private void ColocarNovaPeca(char coluna, int linha, PecaDeXadrez peca) {
		//paraposição converte em matriz
		tabuleiro.posicionarPeca(peca, new XadrezPosition(coluna, linha).ParaPosicao());
		pecasNoTabuleiro.add(peca);
	}
	
	private void setupInicial() {
		ColocarNovaPeca('a', 1, new Torre(tabuleiro, Cor.BRANCO));
        ColocarNovaPeca('e', 1, new Rei(tabuleiro, Cor.BRANCO));
        ColocarNovaPeca('h', 1, new Torre(tabuleiro, Cor.BRANCO));
        ColocarNovaPeca('a', 2, new Peao(tabuleiro, Cor.BRANCO));
        ColocarNovaPeca('b', 2, new Peao(tabuleiro, Cor.BRANCO));
        ColocarNovaPeca('c', 2, new Peao(tabuleiro, Cor.BRANCO));
        ColocarNovaPeca('d', 2, new Peao(tabuleiro, Cor.BRANCO));
        ColocarNovaPeca('e', 2, new Peao(tabuleiro, Cor.BRANCO));
        ColocarNovaPeca('f', 2, new Peao(tabuleiro, Cor.BRANCO));
        ColocarNovaPeca('g', 2, new Peao(tabuleiro, Cor.BRANCO));
        ColocarNovaPeca('h', 2, new Peao(tabuleiro, Cor.BRANCO));

		// Peças Pretas
       ColocarNovaPeca('a', 8, new Torre(tabuleiro, Cor.PRETO));
       ColocarNovaPeca('e', 8, new Rei(tabuleiro, Cor.PRETO));
       ColocarNovaPeca('h', 8, new Torre(tabuleiro, Cor.PRETO));
       ColocarNovaPeca('a', 7, new Peao(tabuleiro, Cor.PRETO));
       ColocarNovaPeca('b', 7, new Peao(tabuleiro, Cor.PRETO));
       ColocarNovaPeca('c', 7, new Peao(tabuleiro, Cor.PRETO));
       ColocarNovaPeca('d', 7, new Peao(tabuleiro, Cor.PRETO));
       ColocarNovaPeca('e', 7, new Peao(tabuleiro, Cor.PRETO));
       ColocarNovaPeca('f', 7, new Peao(tabuleiro, Cor.PRETO));
       ColocarNovaPeca('g', 7, new Peao(tabuleiro, Cor.PRETO));
       ColocarNovaPeca('h', 7, new Peao(tabuleiro, Cor.PRETO));
	}
	
	public int getTurno() {
		return turno;
	}

	public Cor getJogadorAtual() {
		return jogadorAtual;
	}
	
}
