package br.com.estudo.redeneural.feedforward;

import br.com.estudo.redeneural.base.Neuronio;
import br.com.estudo.redeneural.funcaoativacao.FuncaoAtivacao;
import br.com.estudo.redeneural.funcaoativacao.FuncaoLinear;
import br.com.estudo.redeneural.funcaoativacao.FuncaoTangenteHiperbolica;

public class RedeNeural {
	private int numInput;// numero de entradas
	private int numHidden;// neuronios da camada oculta
	private int numOutput;// neuronios da camada de saida

	private Neuronio[] oculto;
	private Neuronio[] saida;

	/**
	 * Rede Neural FeeForward
	 * 
	 * @param numInput
	 *            numero de entradas
	 * @param numHidden
	 *            numero de neuronios na camada oculta
	 * @param numOutput
	 *            numero de neuronios na camada de saida
	 */
	public RedeNeural(int numInput, int numHidden, int numOutput) {
		super();
		this.numInput = numInput;
		this.numHidden = numHidden;
		this.numOutput = numOutput;

		oculto = new Neuronio[numHidden];
		inicializaCamada(oculto, numHidden, numInput, new FuncaoTangenteHiperbolica());

		saida = new Neuronio[numOutput];
		inicializaCamada(saida, numOutput, numHidden, new FuncaoLinear());
	}

	/**
	 * Metodo responsavel por instanciar os neuronios de uma camada X
	 * 
	 * @param camada
	 *            camada que se deseja instanciar
	 * @param tamanho
	 *            total de neuronios da camada
	 * @param entradas
	 *            total de entradas da camada
	 */
	private void inicializaCamada(Neuronio[] camada, int tamanho, int entradas, FuncaoAtivacao funcaoAtivacao) {
		for (int i = 0; i < tamanho; i++) {
			camada[i] = new Neuronio(entradas,funcaoAtivacao);
		}
	}

	/**
	 * Para este exemplo os pesos sao definidos externamente
	 * 
	 * @param weights
	 *            todos os pesos e bias de cada camada
	 */
	public void setPesos(double[] weights) {
		int k = 0; // Pointer into weights.

		for (int i = 0; i < numInput; ++i)
			for (int j = 0; j < numHidden; ++j)
				oculto[j].getW()[i] = weights[k++];

		for (int i = 0; i < numHidden; ++i)
			oculto[i].setBias(weights[k++]);

		for (int i = 0; i < numHidden; ++i)
			for (int j = 0; j < numOutput; ++j)
				saida[j].getW()[i] = weights[k++];

		for (int i = 0; i < numOutput; ++i)
			saida[i].setBias(weights[k++]);
	}

	/**
	 * Metodo responsavel por classificar, ou estimular a rede
	 * 
	 * @param x
	 *            Percepcoes do agente enviadas para a rede neural
	 * 
	 * @return um valor de ativacao
	 */
	public double[] classifica(double[] x) {
		double[] yO = new double[numHidden];
		double[] yS = new double[numOutput];

		for (int j = 0; j < numHidden; ++j) {
			yO[j] = oculto[j].classificar(x);
		}

		for (int j = 0; j < numOutput; ++j) {
			yS[j] = saida[j].classificar(yO);
		}
		return Neuronio.funcaoDeAtivacaoSoftmax(yS);
	}

	
	
}
