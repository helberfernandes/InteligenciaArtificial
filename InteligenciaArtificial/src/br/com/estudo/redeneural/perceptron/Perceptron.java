package br.com.estudo.redeneural.perceptron;

import java.util.Arrays;

import br.com.estudo.redeneural.funcaoativacao.FuncaoAtivacao;
import br.com.estudo.redeneural.funcaoativacao.FuncaoBipolar;

public class Perceptron {
	private double[] x;// entradas
	private double[] w;// pesos
	private int numEntradas;
	private double bias = -1;
	private static final double taxaAprendizado = 0.05;
	private FuncaoAtivacao funcaoAtivacao;

	public Perceptron(int numEntradas) {
		this.numEntradas = numEntradas;
		x = new double[numEntradas];
		w = new double[numEntradas];
		inicializaPesos();
		funcaoAtivacao = new FuncaoBipolar();
	}

	private void inicializaPesos() {
		for (int i = 0; i < numEntradas; i++) {
			w[i] = 0;
		}
	}

	public double[] treinar(double[][] dadosDeTreinamento, int maxEpocas) {
		int desejado;
		int epoca = 0;
		double u;
		while (epoca < maxEpocas) {
			for (int i = 0; i < dadosDeTreinamento.length; i++) {
				x = Arrays.copyOf(dadosDeTreinamento[i], numEntradas);
				
				// cada amostra ja vem com o valor esperado posicionado no fim
				// do
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
		soma += bias;
		return (int) funcaoAtivacao.funcaoAtivacao(soma);
	}

	/**
	 * O Metodo aprender nada mais e do que a atualizacao dos pesos
	 * 
	 * @param saida
	 *            saida Y do neuronio
	 * @param taxaAprendizado
	 * @param esperados
	 */
	public void aprender(double saida, double taxaAprendizado, double esperados) {
		Double fatorAprendizado = taxaAprendizado * (esperados - saida);
		for (int y = 0; y < w.length; y++) {
			w[y] = w[y] + fatorAprendizado * x[y];
		}
	}

}
