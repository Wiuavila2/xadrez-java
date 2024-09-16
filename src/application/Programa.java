package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Xadrez.PartidaDeXadrez;
import Xadrez.PecaDeXadrez;
import Xadrez.XadrezException;
import Xadrez.XadrezPosition;

public class Programa {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez();
		List<PecaDeXadrez> capturadas = new ArrayList<>();
		
		while (!partidaDeXadrez.getCheckMate()) {
			try {
				UI.limparTela();
				UI.printPartida(partidaDeXadrez, capturadas);
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
				
				if(pecaCapturada != null) {
					capturadas.add(pecaCapturada);
				}

				if(partidaDeXadrez.getPromovida() != null) {
					System.out.print("digite a peça que deseja na promoção (b/c/q/r)");
					String type = sc.nextLine();
					partidaDeXadrez.substituirPecaPromovida(type);
				}
			} catch(XadrezException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}catch(InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
		UI.limparTela();
		UI.printPartida(partidaDeXadrez, capturadas);
	}

}
