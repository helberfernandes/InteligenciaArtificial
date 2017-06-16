package br.com.estudo.redeneural.perceptron;

import java.util.Arrays;

public class PerceptronDemo {

	public static void main(String[] args) {
		//a amostra ja tras o valor esperado na ultima posicao
		double AMOSTRAS[][] = {
				{  1,  1,  1,  1,  1 }, // Gato
				{  1, -1,  1,  1,  1 }, // Cao				
				{  1, -1, -1,  1,  1 }, // Cavalo
				{ -1,  1, -1,  1, -1 }, // Galinha
				{  1, -1,  1, -1, -1 }, // Avestruz
				{ -1, -1, -1,  1, -1 }, // Homem
		};
		System.out.println("Apostras");
		mostrarDados(AMOSTRAS);

		Perceptron p = new Perceptron(4);

		double[] pesos = p.treinar(AMOSTRAS, 10000);

		System.out.println("Pesos Encontrados");
		mostrarDados(pesos);

		System.out.println("classificação");

		double[] entrada = { 1, 1, 1, 1 };
		System.out.println(p.classificar(entrada) == 1d ? "Quadrupede" : "Bipede");

		System.out.println("----------------------------------------------------");
		double[] x = null;
		for (int i = 0; i < AMOSTRAS.length; i++) {
			x = Arrays.copyOf(AMOSTRAS[i], AMOSTRAS.length);
			System.out.println(p.classificar(x) == 1d ? "Quadrupede" : "Bipede");
		}

	}

	private static void mostrarDados(double dados[][]) {
		for (int x = 0; x < dados.length; x++) {
			System.out.print("{");
			for (int y = 0; y < dados[0].length; y++) {
				System.out.printf("%s ", "" + dados[x][y]);
			}
			System.out.println("}");
		}
	}

	private static void mostrarDados(double dados[]) {
		for (int x = 0; x < dados.length; x++) {
			System.out.print("{");
			System.out.printf("%s ", "" + dados[x]);
			System.out.println("}");
		}
	}

}
