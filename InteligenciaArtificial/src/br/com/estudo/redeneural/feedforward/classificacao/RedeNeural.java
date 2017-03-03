package br.com.estudo.redeneural.feedforward.classificacao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.estudo.redeneural.funcaoaprendizado.Backpropagation;
import br.com.estudo.redeneural.funcaoativacao.FuncaoAtivacao;
import br.com.estudo.redeneural.funcaoativacao.FuncaoBipolar;
import br.com.estudo.redeneural.funcaoativacao.FuncaoLinear;
import br.com.estudo.redeneural.funcaoativacao.FuncaoSigmoid;
import br.com.estudo.redeneural.funcaoativacao.FuncaoTangenteHiperbolica;

public class RedeNeural {
	private int numInput;// numero de entradas
	private int numHidden;// neuronios da camadas oculta
	private int numOutput;// neuronios da camadas de saida
	private static final double taxaAprendizado = 0.7;
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

		oculta = new Camada(numInput, numHidden, taxaAprendizado, new FuncaoTangenteHiperbolica(), new Backpropagation());
		saida = new Camada(numHidden, numOutput, taxaAprendizado, new FuncaoBipolar(), new Backpropagation());
		oculta.setProxima(saida);

	}

	public double[] treinar(double[][] x) {
		boolean erro = false;
		double[] yS = null;
		for (int y = 0; y < maxEpocas; y++) {
			for (int j = 0; j < x.length; j++) {
				yS = oculta.classifica(x[j]);
			}
			
//			for (int i = 0; i < x.length; i++) {
//				for (int a = 0; a < yS.length; a++) {
//					if (yS[a] != x[i][numInput]) {
//						erro = true;
//					}
//				}
//			}

		//	if (erro) {
				int i = 0;

				
				List<Neuronio> neuronios = new ArrayList<Neuronio>();
				for (Neuronio neuronio : saida.getNeuronios()) {
					//System.out.println("Desejado "+x[i][numInput]);
//					try {
//						Thread.sleep(500);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
					neuronio.calcError(x[i][numInput]);
					neuronio.aprender(taxaAprendizado);
					neuronios.add(neuronio);
					
					i++;
				}
				
				 saida.setNeuronios(neuronios);
				
				
				
				

				i = 0;
				for (Neuronio neuronio : oculta.getNeuronios()) {

					/**
					 * Para calcular o erro da camada oculta, devo pegar o delta
					 * da camada proxima que no caso deste problema como temos
			bradesc		 * apenas tres camadas, entrada,oculto e saida, é a camada
					 * de saida. O peso e o peso que corresponde a o neuronio
					 * associado na entrada a ele *
					 */
					for (Neuronio n : oculta.getProxima().getNeuronios()) {
						//System.out.println("peso "+n.getTreino().getErroDelta());
						
//						try {
//							Thread.sleep(500);
//						} catch (InterruptedException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
						neuronio.soma(n.getTreino().getErroDelta(), n.getW()[i]);
					}
					neuronio.calcError();
					neuronio.aprender(taxaAprendizado);
					i++;

				}
				
//				for (Neuronio neuronio : oculta.getNeuronios()) {
//					
//					System.out.println("peso "+neuronio.getTreino().getErroDelta());
//				
//				}
				erro = false;
			//}
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
