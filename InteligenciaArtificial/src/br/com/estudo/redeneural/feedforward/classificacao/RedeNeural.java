package br.com.estudo.redeneural.feedforward.classificacao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.estudo.redeneural.base.Neuronio;
import br.com.estudo.redeneural.funcaoaprendizado.Backpropagation;
import br.com.estudo.redeneural.funcaoativacao.FuncaoBipolar;
import br.com.estudo.redeneural.funcaoativacao.FuncaoLogistica;
import br.com.estudo.redeneural.funcaoativacao.FuncaoTangenteHiperbolica;

public class RedeNeural {
	private int numInput;// numero de entradas
	private int numHidden;// neuronios da camadas oculta
	private int numOutput;// neuronios da camadas de saida
	private static final double taxaAprendizado = 0.05;
	private static final double maxEpocas = 900000;

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

		oculta = new Camada(numInput, numHidden, taxaAprendizado, new FuncaoLogistica(),
				new Backpropagation(taxaAprendizado));
		saida = new Camada(numHidden, numOutput, taxaAprendizado, new FuncaoBipolar(),
				new Backpropagation(taxaAprendizado));

//		double[][] w = { { 0.15, 0.2 }, // neuronio 1
//				{ 0.25, 0.3 } // neuronio 2 o mesmo se aplica em baixo
//		};
//
//		oculta.setPesos(w);
//		for (Neuronio n : oculta.getNeuronios()) {
//			n.setBias(0.35);
//		}
//
//		double[][] w2 = { { 0.4, 0.45 }, { 0.5, 0.55 } };
//
//		saida.setPesos(w2);
//		for (Neuronio n : saida.getNeuronios()) {
//			n.setBias(0.6);
//		}

		oculta.setProxima(saida);

	}

	public double[] treinar(double[][] dadosDeTreinamento) {

		double[] yS = null;
		// verificando se o treinamento deu certo, ou seja se a saida e a mesma
		// esperada
		boolean erro = false;
		double desejado;
		int epoca = 0;
		while (epoca <= 1) {

			for (int a = 0; a < dadosDeTreinamento.length; a++) {

				yS = oculta.classifica(Arrays.copyOf(dadosDeTreinamento[a], numInput));

				// cada amostra ja vem com o valor esperado posicionado no fim
				// do
				// vetor
				desejado = (int) dadosDeTreinamento[a][numInput];

				

				for (int i = 0; i < numOutput; i++) {					
					if (yS[i] != desejado) {
						erro = true;
					}
				}
				if (erro) {
					/*
					 * Calculando o erro na camada de saida
					 */
					for (int i = 0; i < saida.getNeuronios().size(); i++) {
						saida.getNeuronios().get(i).getTreino().calcError(yS[i], desejado);
					}

					/*
					 * atualizando o peso da camada de saida. Para isso basta
					 * miltiplicar a taxa de aprendizado pela saida do neuronio
					 * respectivo ao peso multiplicando também oerrodelta
					 */
					for (int i = 0; i < saida.getNeuronios().size(); i++) {
						
						for (int j = 0; j < saida.getNeuronios().get(i).getW().length; ++j) {
						saida.getNeuronios().get(i).getTreino().deltaWheigts(j,
								oculta.getNeuronios().get(i).getSaida());
						}
					}

					/*
					 * Calculadno o erro na camada oculta para isso deve, somar
					 * o produto da multiplicacao dos pesos desta camada com o
					 * delta da camada de saida multiplicado pela derivada da
					 * funcao de ativacao desta camda oculta
					 */
					List<Double> pesos = new ArrayList<>();
					List<Double> delta = new ArrayList<>();
					for (int n = 0; n < oculta.getNeuronios().size(); n++) {
						for (int ns = 0; ns < saida.getNeuronios().size(); ns++) {
							for (int i = 0; i < saida.getNeuronios().get(ns).getW().length; i++) {
								pesos.add(saida.getNeuronios().get(ns).getW()[i]);
							}
							delta.add(saida.getNeuronios().get(ns).getTreino().getErroDelta());
						}
						oculta.getNeuronios().get(n).getTreino().calcErrorOculto(pesos, delta, oculta.getNeuronios().get(n).getSaida());
					}

					// calculando o delta dos pesos da camada oculta
					for (int i = 0; i < oculta.getNeuronios().size(); i++) {
						for (int j = 0; j < oculta.getNeuronios().get(i).getW().length; ++j) {
						oculta.getNeuronios().get(i).getTreino().deltaWheigts(j,
								oculta.getNeuronios().get(i).getX()[i]);
						}
					}

					for (int i = 0; i < saida.getNeuronios().size(); i++) {
						saida.getNeuronios().get(i).updateWheigts();
					}

					for (int i = 0; i < oculta.getNeuronios().size(); i++) {
						oculta.getNeuronios().get(i).updateWheigts();
					}
					
					erro=false;
				}
			}
			epoca++;
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
