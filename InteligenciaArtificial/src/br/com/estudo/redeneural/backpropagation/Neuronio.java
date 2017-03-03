package br.com.estudo.redeneural.backpropagation;

import java.util.Arrays;
/**
 * Um neuronio recebe um conjunto de entradas e responde a estimolos
 * 
 * @author Helber Fernandes
 *
 */
public class Neuronio {
	private double[] x;// entradas
	private double[] w;// pesos
	private int numEntradas;
	private double bias = -1;
	//y e a saida do neuronio depois da funcao de ativacao
	private double y;
	
	/**
	 * 
	 * @param numEntradas, total de entradas para o neuronio
	 */
	public Neuronio(int numEntradas) {
		this.numEntradas = numEntradas;
		x = new double[numEntradas];
		w = new double[numEntradas];
	}

	/**
	 * Funcao que responsavel por classificar determinado problema
	 * 
	 * @param x Percepcoes do agente enviadas para a rede neural
	 * @param usarFuncao para este probleam especifico, a camada de saida usa uma funcao que e ativada pela soma de todas as camadas de saida
	 * para este caso passar false.
	 * @return resultado do processamenteo do neuronio
	 */
	public double classificar(double[] x, boolean usarFuncao) {
		double soma = 0.0;
		for (int i = 0; i < numEntradas; i++) {
			soma += x[i] * w[i];
		}
		soma += bias;
		if (usarFuncao) {
			return y=funcaoDeAtivacaoHyperTan(soma);
		} else {
			return y=soma;
		}
	}
	/**
	 * 
	 * @return pessos do neuronio
	 */
	public double[] getW() {
		return w;
	}
	
	public void setBias(double bias) {
		this.bias = bias;
	}
	

	public double getBias() {
		return bias;
	}

	public void setW(double[] w) {
		this.w = w;
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
	
	public double getDerivada(double desejado){
		double derivative = (1 - y) * y; // Derivative of softmax is y(1y).        
       // oGrads[i] = derivative * (desejado - y); // oGrad = (1 - O)(O) * (T-O) 
		return derivative;
	}
	
	public void atualizaPesos(double [] hGrads){
		 for (int j = 0; j < w.length; ++j)
         {
//             double delta = learnRate * hGrads[j] * x[i];
//             x[j] += delta; // Update.          
//             x[j] += momentum * ihPrevWeightsDelta[i][j]; // Add momentum factor.          
//             ihPrevWeightsDelta[i][j] = delta; // Save the delta for next time.       
         }
	}

}
