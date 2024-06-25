package boardGame;

public class Tabuleiro {
	
	private int linhas;
	private int colunas;
	private Peca[][] pecas;
	
	
	public Tabuleiro(int linhas, int colunas) {
		this.linhas = linhas;
		this.colunas = colunas;
		pecas = new Peca[linhas][colunas];
		
	}


	public int getLinhas() {
		return linhas;
	}


	public void setLinhas(int linhas) {
		this.linhas = linhas;
	}


	public int getColunas() {
		return colunas;
	}


	public void setColunas(int colunas) {
		this.colunas = colunas;
	}
	
	public Peca peca(int linha, int coluna) {
		return pecas[linha][coluna];
	}
	
	public Peca peca(Position position) {
		return pecas[position.getLinha()][position.getColuna()];
	}
	
	public void posicionarPeca(Peca peca, Position position) {
		pecas[position.getLinha()][position.getColuna()] = peca;
		//posso acessar diretamente pois está no mesmo pacote como protected
		peca.position = position;
	}
	
}
