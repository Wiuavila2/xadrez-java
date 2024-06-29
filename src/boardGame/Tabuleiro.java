package boardGame;

public class Tabuleiro {
	
	private int linhas;
	private int colunas;
	private Peca[][] pecas;
	
	
	public Tabuleiro(int linhas, int colunas) {
		if(linhas < 1 || colunas < 1 ) {
			throw new TabuleiroException("Erro ao criar o Tabuleiro : é necessário que haja ao menos 1 linha e 1 coluna");
		}
		this.linhas = linhas;
		this.colunas = colunas;
		pecas = new Peca[linhas][colunas];
		
	}


	public int getLinhas() {
		return linhas;
	}

	public int getColunas() {
		return colunas;
	}
	
	public Peca peca(int linha, int coluna) {
		if(!positionExiste(linha, coluna)) {
			throw new TabuleiroException("Posição não está np tabuleiro!");
		}
		return pecas[linha][coluna];
	}
	
	public Peca peca(Position position) {
		if(!positionExiste(position)) {
			throw new TabuleiroException("Posição não está no tabuleiro!");
		}
		return pecas[position.getLinha()][position.getColuna()];
	}
	
	public void posicionarPeca(Peca peca, Position position) {
		if(temUmaPeca(position)) {
			throw new TabuleiroException("ja existe uma peça na posição" + position);
		}
		pecas[position.getLinha()][position.getColuna()] = peca;
		//posso acessar diretamente pois está no mesmo pacote como protected
		peca.position = position;
	}
	
	public Peca removerPeca(Position position) {
		if(!positionExiste(position)) {
			throw new TabuleiroException("Posição não está no tabuleiro!");
		}
		if(peca(position) == null) {
			return null;
		}
		Peca aux = peca(position);
		aux.position = null;
		pecas[position.getLinha()][position.getColuna()] = null;
		return aux;
	}
	public boolean positionExiste(int linha, int coluna) {
		return linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas;
	}
	
	public boolean positionExiste(Position position) {
		return positionExiste(position.getLinha(), position.getColuna());
	}
	
	public boolean temUmaPeca(Position position)  {
		if(!positionExiste(position)) {
			throw new TabuleiroException("Posição não está no tabuleiro!");
		}
		return peca(position) != null;
	}
}
