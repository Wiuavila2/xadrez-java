package application;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import Xadrez.Cor;
import Xadrez.PartidaDeXadrez;
import Xadrez.PecaDeXadrez;
import Xadrez.XadrezPosition;

public class UI {
	
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
	
	
	// https://stackoverflow.com/questions/2979383/java-clear-the-console
	public static void limparTela() {
	 System.out.print("\033[H\033[2J");
	 System.out.flush(); 
	}
	
	public static XadrezPosition lerXadrezPosition(Scanner sc) {
		try {
			String s = sc.nextLine();
			char coluna = s.charAt(0);
			int linha = Integer.parseInt(s.substring(1));
			return new XadrezPosition(coluna, linha);	
		}
		catch(RuntimeException e) {
			throw new InputMismatchException("Erro ao ler a posicão de xadrez : valores válidos são de a1 a h8 ");
		}
	}
	
	public static void printPartida(PartidaDeXadrez partidaDeXadrez, List<PecaDeXadrez>capturada) {
		printTabuleiro(partidaDeXadrez.getPecas());
		System.out.println();
		printPecaCapturada(capturada);
		System.out.println();
		System.out.println("Turno: " + partidaDeXadrez.getTurno());
		
		if(!partidaDeXadrez.getCheckMate()) {
			System.out.println("Esperando o jogador : " + partidaDeXadrez.getJogadorAtual());		
			if(partidaDeXadrez.getCheck()) {
				System.out.println("CHECK!");
			
			}
		}
		else {
			System.out.println("CHECK MATE!");
			System.out.println("vencedor: " + partidaDeXadrez.getJogadorAtual());
		}
		
	}
	public static void printTabuleiro(PecaDeXadrez[][] pecas) {
		for(int i=0; i < pecas.length;i++) {
			System.out.print((8-i) + " ");
			for(int j=0; j < pecas.length;j++) {
				printPeca(pecas[i][j],false);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}
	//metodo para imprimir uma peca
	
	public static void printTabuleiro(PecaDeXadrez[][] pecas,boolean[][] movimentosPossiveis) {
		for(int i=0; i < pecas.length;i++) {
			System.out.print((8-i) + " ");
			for(int j=0; j < pecas.length;j++) {
				printPeca(pecas[i][j], movimentosPossiveis[i][j]);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}
	
	
	private static void printPeca(PecaDeXadrez peca, boolean background) {
		if(background) {
			System.out.print(ANSI_BLUE_BACKGROUND);
		}
		if (peca == null) {
            System.out.print("-" + ANSI_RESET);
        }
        else {
            if (peca.getCor() == Cor.BRANCO) {
                System.out.print(ANSI_WHITE + peca + ANSI_RESET);
            }
            else {
                System.out.print(ANSI_YELLOW + peca + ANSI_RESET);
            }
        }
        System.out.print(" ");
	}
	
	private static void printPecaCapturada(List<PecaDeXadrez> capturada) {
		List<PecaDeXadrez> brancas= capturada.stream().filter(a -> a.getCor() == Cor.BRANCO).collect(Collectors.toList());
		List<PecaDeXadrez> pretas= capturada.stream().filter(a -> a.getCor() == Cor.PRETO).collect(Collectors.toList());
		System.out.println("Pecas capturadas");
		System.out.print(" BRANCAS : ");
		System.out.print(ANSI_WHITE);
		//jeito padrão d eimprimir arrays em java 
		System.out.println(Arrays.toString(brancas.toArray()));
		System.out.println(ANSI_RESET);
		
		System.out.print(" PRETAS : ");
		System.out.print(ANSI_YELLOW);
		//jeito padrão d eimprimir arrays em java 
		System.out.println(Arrays.toString(pretas.toArray()));
		System.out.println(ANSI_RESET);
	}
}
	

