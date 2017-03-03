package br.com.estudo.redeneural.feedforward.teste;

import br.com.estudo.redeneural.funcaoaprendizado.Backpropagation;
import br.com.estudo.redeneural.funcaoativacao.FuncaoAtivacao;
import br.com.estudo.redeneural.funcaoativacao.FuncaoLinear;
import br.com.estudo.redeneural.funcaoativacao.FuncaoSigmoid;
import br.com.estudo.redeneural.funcaoativacao.FuncaoTangenteHiperbolica;

public class RedeNeural {
	private int numInput;// numero de entradas
	private int numHidden;// neuronios da camadas oculta
	private int numOutput;// neuronios da camadas de saida
	private static final double taxaAprendizado = 0.05;
	private static final double maxEpocas = 10000;

	private Camada oculta;
	private Camada saida;

	/**
	 * Rede Neural FeeForward
	 * 
	 * @param numInput
	 *            numero de entradas
	 * @param numHidden
	 *            numero de neuronios na camadas oculta
	 * @param numOutput
	 *            numero de neuronios na camadas de saida
	 */
	public RedeNeural(int numInput, int numHidden, int numOutput) {
		super();
		this.numInput = numInput;
		this.numHidden = numHidden;
		this.numOutput = numOutput;

		oculta = new Camada(numInput, numHidden, taxaAprendizado, new Backpropagation());
		saida = new Camada(numHidden, numOutput, taxaAprendizado, new Backpropagation());
		oculta.setProxima(saida);

	}

	public double[] treinar(double[] x, double[] desejado) {

		double[] yS = null;
		for (int y = 0; y < maxEpocas; y++) {
			yS = oculta.classifica(x);
			boolean erro = false;
			for (int i = 0; i < yS.length; i++) {
				if (yS[i] != desejado[i]) {
					erro = true;
				}
			}

			if (erro) {
				int i = 0;

				for (Neuronio neuronio : saida.getNeuronios()) {
					neuronio.calcError(desejado[i++]);
					neuronio.aprender(taxaAprendizado);
				}

				i = 0;
				for (Neuronio neuronio : oculta.getNeuronios()) {

					/**
					 * Para calcular o erro da camada oculta, devo pegar o delta
					 * da camada proxima que no caso deste problema como temos
					 * apenas tres camadas, entrada,oculto e saida, é a camada
					 * de saida. O peso e o peso que corresponde a o neuronio
					 * associado na entrada a ele *
					 */
					for (Neuronio n : oculta.getProxima().getNeuronios()) {
						neuronio.calcError(n.getTreino().getErroDelta(), n.getW()[i]);
					}
					neuronio.aprender(taxaAprendizado);
					i++;

				}
				erro = false;
			}
		}

		return yS;
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
		return oculta.classifica(x);
	}

}
