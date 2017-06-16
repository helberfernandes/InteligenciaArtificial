package br.com.estudo.redeneural.backpropagation;

import br.com.estudo.redeneural.base.Neuronio;

public class RedeNeural {
	private int numInput;// numero de entradas
	private int numHidden;// neuronios da camada oculta
	private int numOutput;// neuronios da camada de saida

	private Neuronio[] oculto;
	private Neuronio[] saida;

	private double[] oGrads; // Output gradients for back-propagation.
	private double[] hGrads; // Hidden gradients for back-propagation.
	
	double[] yO;//saida da camada oculta
	double[] yS; // camada de saida

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
		yO = new double[numHidden];
		yS = new double[numOutput];
		
		oGrads = new double[numHidden];
		hGrads = new double[numOutput];
		
		oculto = new Neuronio[numHidden];
		inicializaCamada(oculto, numHidden, numInput);

		saida = new Neuronio[numOutput];
		inicializaCamada(saida, numOutput, numHidden);
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
	private void inicializaCamada(Neuronio[] camada, int tamanho, int entradas) {
		for (int i = 0; i < tamanho; i++) {
			camada[i] = new Neuronio(entradas);
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

	public double[] getPesos() {

		int numWeights = (numInput * numHidden) + numHidden + (numHidden * numOutput) + numOutput;
		double[] result = new double[numWeights];
		int k = 0;
		for (int i = 0; i < numInput; ++i)
			for (int j = 0; j < numHidden; ++j)
				result[k++] = oculto[j].getW()[i];

		for (int i = 0; i < numHidden; ++i)
			result[k++] = oculto[i].getBias();

		for (int i = 0; i < numHidden; ++i)
			for (int j = 0; j < numOutput; ++j)
				result[k++] = saida[j].getW()[i];

		for (int i = 0; i < numOutput; ++i)
			result[k++] = saida[i].getBias();

		return result;
	}

	public void encontrarPesos(double[] esperados, double[] xValues, double taxaAprendizado, double momentum,
			int maxEpocas) {
		int epoca = 0;
		while (epoca <= maxEpocas) {
			double[] y = classifica(xValues);
			updatePesos(esperados, taxaAprendizado, momentum, y);
			if (epoca % 100 == 0) {
				System.out.println("Epoca =" + epoca + " saida atual =");
				Backprogation.showVector(y, 2, 4, true);
			}
			++epoca;
		}
	}

	private void updatePesos(double[] esperados, double taxaAprendizado, double momentum, double[] outputs) {
		// 1. Compute output gradients. Assumes softmax.
		/*
		 * O gradiente de um nó de saída é a diferença entre o valor de saída
		 * calculado eo valor desejado, multiplicado pela derivada de cálculo da
		 * função de ativação usada pela camada de saída.
		 * 
		 * A primeira parte da computação é o termo derivativo. A segunda parte
		 * é a diferença entre valores de saída calculados e desejados. A ordem
		 * em que a diferença entre valores calculados e desejados é realizada é
		 * muito importante, mas varia de implementação para implementação.
		 * Aqui, a diferença é calculada como (desejada - calculada) em vez de
		 * (calculada - desejada).
		 */
		for (int i = 0; i < oGrads.length; ++i) {
			// Derivative of softmax is y(1y).
			double derivative = (1 - outputs[i]) * outputs[i];
			// oGrad = (1 - O)(O) * (T-O)
			oGrads[i] = derivative * (esperados[i] - outputs[i]);
		}
		
		
		 // 2. Compute hidden gradients. Assumes tanh!      
        for (int i = 0; i < hGrads.length; ++i)
        {
            double derivative = (1 - yO[i]) * (1 + yO[i]); // f' of tanh is (1y)(1+y).        
            double sum = 0.0;
            for (int j = 0; j < numOutput; ++j) // Each hidden delta is the sum of numOutput terms.          
                sum += oGrads[j] * oculto[j].getW()[i]; // Each downstream gradient * outgoing weight.         
            hGrads[i] = derivative * sum; // hGrad = (1-O)(1+O) * Sum(oGrads*oWts)      
        }
        
        // 3. Update input to hidden weights.      
//        for (int i = 0; i < ihWeights.Length; ++i)
//        {
//           
//        }

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
		

		for (int j = 0; j < numHidden; ++j) {			
			yO[j] = oculto[j].classificar(x);
		}

		for (int j = 0; j < numOutput; ++j) {
			yS[j] = saida[j].classificar(yO);
		}
		return Neuronio.funcaoDeAtivacaoSoftmax(yS);
	}

}
