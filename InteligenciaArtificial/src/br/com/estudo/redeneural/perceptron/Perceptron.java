package br.com.estudo.redeneural.perceptron;

import java.util.Arrays;

public class Perceptron {
	private double[] x;// entradas
	private double[] w;// pesos
	private int numEntradas;
	private double bias = -1;
	private static final double taxaAprendizado = 0.05;

	public Perceptron(int numEntradas) {
		this.numEntradas = numEntradas;
		x = new double[numEntradas];
		w = new double[numEntradas];
		inicializaPesos();
	}

	private void inicializaPesos() {
		for (int i = 0; i < numEntradas; i++) {
			w[i] = 0;
		}
	}

	public int funcaoDeAtivacao(double u) {
		return (u >= 0.0) ? 1 : -1;
	}

	public double[] treinar(double[][] dadosDeTreinamento, int maxEpocas) {
		int desejado;
		int epoca = 0;
		double u;
		while (epoca < maxEpocas) {
			for (int i = 0; i < dadosDeTreinamento.length; i++) {
				x = Arrays.copyOf(dadosDeTreinamento[i], numEntradas);
				// cada amostra ja vem com o valor esperado posicionado no fim do
				// vetor
				desejado = (int) dadosDeTreinamento[i][numEntradas];
				u = classificar(x);
				
				if (desejado != u) {
						aprender(u, taxaAprendizado, desejado);
				}
			}
			epoca++;
		}
		return w;
	}

	public int classificar(double[] x) {
		double soma = 0.0;
		for (int i = 0; i < numEntradas; i++) {
			soma += x[i] * w[i];
		}
		soma+=bias;
		return funcaoDeAtivacao(soma);
	}

	public void aprender(double saida, double taxaAprendizado,  double esperados) {
		Double fatorAprendizado = taxaAprendizado * (esperados - saida);
		for (int y = 0; y < w.length; y++) {
			w[y]=w[y] + fatorAprendizado * x[y];
		}
	}

}
