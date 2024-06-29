package application;

import java.util.Scanner;

import Xadrez.PartidaDeXadrez;
import Xadrez.PecaDeXadrez;
import Xadrez.XadrezPosition;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez();
		
		while (true) {
			UI.printTabuleiro(partidaDeXadrez.getPecas());
			System.out.println();
			System.out.print("Fonte: ");
			XadrezPosition fonte = UI.lerXadrezPosition(sc);
			
			System.out.println();
			System.out.print("alvo: ");
			XadrezPosition alvo = UI.lerXadrezPosition(sc);
			
			PecaDeXadrez pecaCapturada = partidaDeXadrez.realizarMovimentoDeXadrez(fonte, alvo);
		}
	}

}
