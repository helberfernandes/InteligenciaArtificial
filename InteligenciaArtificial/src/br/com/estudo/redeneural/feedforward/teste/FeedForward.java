package br.com.estudo.redeneural.feedforward.teste;


public class FeedForward {

	public static void main(String[] args) {
		
		final int QTD_ENTRADAS = 3;
		final int QTD_NEURONIOS_OCULTOS = 4;
		final int QTD_NEURONIOS_SAIDA = 2;

		RedeNeural rna = new RedeNeural(QTD_ENTRADAS, QTD_NEURONIOS_OCULTOS, QTD_NEURONIOS_SAIDA);

		double XOR_INPUT[] = { 1, 2, 3 };

		double XOR_IDEAL[] = { 0.2500, 0.7300 };

		double[] yS = rna.treinar(XOR_INPUT, XOR_IDEAL);

		showVector(yS, 2, 4, true);
	}

	public static void showVector(double[] vector, int valsPerRow, int decimals, boolean newLine) {
		for (int i = 0; i < vector.length; ++i) {
			if (i % valsPerRow == 0)
				System.out.println(" ");
			System.out.print(String.format(" %.4f", vector[i]));
		}
		if (newLine == true)
			System.out.println(" ");
	}

}
