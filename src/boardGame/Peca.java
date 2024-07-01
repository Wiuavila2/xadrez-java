package boardGame;

public abstract class Peca {
//será protected pois e de matriz não deve ser visivel no xadrez
	protected Position position;
	private Tabuleiro tabuleiro;
	
	public Peca(Tabuleiro tabileiro) {
		this.tabuleiro = tabileiro;
		position = null;
	}
//só a classe/subclasses poderão acessar o tabuleiro , só as peças/ tabuleiro podem acessar
	protected Tabuleiro getTabuleiro() {
		return tabuleiro;
	}
	
	public abstract boolean[][] movimentosPossiveis();
	
	public boolean movimentoPossivel(Position position) {
		return movimentosPossiveis()[position.getLinha()][position.getColuna()];
	}
	//matriz terá verdadeiro onde for possivel
	public boolean temAlgumMovimentoPossivel() {
		boolean[][] mat = movimentosPossiveis();
				for(int i=0; i < mat.length; i++) {
					for(int j=0; j < mat.length; j++) {
						if(mat[i][j]) {
							return true;
						}
					}
				}
				return false;
	}
	
}
