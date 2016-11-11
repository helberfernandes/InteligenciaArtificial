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
	 * Embora haja excecoes, em geral, a funcaoo tangente hiperbolica � a melhor
	 * op��o para a ativa��o da camada oculta.
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
	 * Para a ativa��o da camada de sa�da, se sua rede neural est� executando a
	 * classifica��o onde a vari�vel dependente a ser predita tem tr�s ou mais
	 * valores (por exemplo, predizer a inclina��o pol�tica de uma pessoa que
	 * pode ser "liberal", "moderada" ou "conservadora"), Softmax ativa��o � a
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
