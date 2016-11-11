package br.com.estudo.redeneural.feedforward;

import java.util.Arrays;

public class Neuronio {
	private double[] x;// entradas
	private double[] w;// pesos
	private int numEntradas;
	private double bias = -1;

	public Neuronio(int numEntradas) {
		this.numEntradas = numEntradas;
		x = new double[numEntradas];
		w = new double[numEntradas];
	}

	public double classificar(double[] x, boolean usarFuncao) {
		double soma = 0.0;
		for (int i = 0; i < numEntradas; i++) {
			soma += x[i] * w[i];
		}
		soma += bias;
		if (usarFuncao) {
			return funcaoDeAtivacaoHyperTan(soma);
		} else {
			return soma;
		}
	}

	public double[] getW() {
		return w;
	}

	public void setBias(double bias) {
		this.bias = bias;
	}

	/**
	 * Embora haja excecoes, em geral, a funcaoo tangente hiperbolica é a melhor
	 * opção para a ativação da camada oculta.
	 */
	private static double funcaoDeAtivacaoHyperTan(double v) {
		if (v < -20.0)
			return -1.0;
		else if (v > 20.0)
			return 1.0;
		else
			return Math.tanh(v);
	}

	/**
	 * Para a ativação da camada de saída, se sua rede neural está executando a
	 * classificação onde a variável dependente a ser predita tem três ou mais
	 * valores (por exemplo, predizer a inclinação política de uma pessoa que
	 * pode ser "liberal", "moderada" ou "conservadora"), Softmax ativação é a
	 * melhor escolha.
	 */
	public static double[] funcaoDeAtivacaoSoftmax(double[] oSums) {
		// Does all output nodes at once.
		// Determine max oSum.
		double max = oSums[0];
		for (int i = 0; i < oSums.length; ++i)
			if (oSums[i] > max)
				max = oSums[i];

		// Determine scaling factor -- sum of exp(each val - max).
		double scale = 0.0;
		for (int i = 0; i < oSums.length; ++i)
			scale += Math.exp(oSums[i] - max);

		double[] result = new double[oSums.length];
		for (int i = 0; i < oSums.length; ++i)
			result[i] = Math.exp(oSums[i] - max) / scale;

		return result; // Now scaled so that xi sums to 1.0.
	}

}
