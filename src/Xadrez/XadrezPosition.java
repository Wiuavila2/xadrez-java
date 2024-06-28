package Xadrez;

import boardGame.Position;

public class XadrezPosition {

	private char coluna;
	private int linha;
	public XadrezPosition(char coluna, int linha) {
		if(coluna < 'a' || coluna > 'h' || linha < 1 || linha > 8 ) {
			throw new XadrezException("Erro ao instanciar a posição : valores válidos vão de a1 a h8"); 
		}
		this.coluna = coluna;
		this.linha = linha;
	}
	public char getColuna() {
		return coluna;
	}
	public int getLinha() {
		return linha;
	}
	
	protected Position ParaPosicao() {
		return new Position(8 - linha, coluna - 'a');
	}
	
	protected static XadrezPosition DaPosicao(Position position) {
		return new XadrezPosition((char)('a' - position.getColuna()),8 - position.getLinha());
	}
	
	@Override
	public String toString() {
		return " " + coluna + linha; 
	}
}
