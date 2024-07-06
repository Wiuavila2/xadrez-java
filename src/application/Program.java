package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import Xadrez.PartidaDeXadrez;
import Xadrez.PecaDeXadrez;
import Xadrez.XadrezException;
import Xadrez.XadrezPosition;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez();
		
		while (true) {
			try {
				UI.limparTela();
				UI.printPartida(partidaDeXadrez);
				System.out.println();
				System.out.print("Fonte: ");
				XadrezPosition fonte = UI.lerXadrezPosition(sc);
				
				boolean[][] movimentosPossiveis = partidaDeXadrez.movimentosPossiveis(fonte);
				UI.limparTela();
				UI.printTabuleiro(partidaDeXadrez.getPecas(),movimentosPossiveis);
				System.out.println();
				System.out.print("alvo: ");
				XadrezPosition alvo = UI.lerXadrezPosition(sc);
				
				PecaDeXadrez pecaCapturada = partidaDeXadrez.realizarMovimentoDeXadrez(fonte, alvo);
			} catch(XadrezException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}catch(InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
	}

}
