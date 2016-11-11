package br.com.estudo.redeneural.feedforward;

public class RedeNeural {
	private int numInput;// numero de entradas
	private int numHidden;// neuronios da camada oculta
	private int numOutput;// neuronios da camada de saida

	private Neuronio[] oculto;
	private Neuronio[] saida;

	public RedeNeural(int numInput, int numHidden, int numOutput) {
		super();
		this.numInput = numInput;
		this.numHidden = numHidden;
		this.numOutput = numOutput;

		oculto = new Neuronio[numHidden];
		inicializaCamada(oculto, numHidden, numInput);

		saida = new Neuronio[numOutput];
		inicializaCamada(saida, numOutput, numHidden);
	}

	private void inicializaCamada(Neuronio[] camada, int tamanho, int entradas) {
		for (int i = 0; i < tamanho; i++) {
			camada[i] = new Neuronio(entradas);
		}
	}

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

	public double[] classifica(double[] x) {
		double[] yO = new double[numHidden];
		double[] yS = new double[numOutput];
		
		for (int j = 0; j < numHidden; ++j) {
			yO[j]=oculto[j].classificar(x, true);
		}
		
		for (int j = 0; j < numOutput; ++j) {
			yS[j]=saida[j].classificar(yO, false);			
		}
		return Neuronio.funcaoDeAtivacaoSoftmax(yS);
	}

}
