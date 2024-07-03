package boardGame;

public class Position {
	private int Linha;
	private int coluna;
	public Position(int linha, int coluna) {
		super();
		Linha = linha;
		this.coluna = coluna;
	}
	public int getLinha() {
		return Linha;
	}
	public void setLinha(int linha) {
		Linha = linha;
	}
	public int getColuna() {
		return coluna;
	}
	public void setColuna(int coluna) {
		this.coluna = coluna;
	}
	
	public void setValores(int linha, int coluna) {
		this.Linha = linha;
		this.coluna = coluna;
	}
	@Override
	public String toString() {
		return Linha+ ", " + coluna;
	}
	
	
}
