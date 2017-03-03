package br.com.estudo.redeneural.feedforward.classificacao;

public class FeedForward {

	public static double XOR_INPUT[][] = {
			{0.0,0.0,-1},
			{1.0,0.0,1},
			{0.0,1.0,1},
			{1.0,1.0,-1}
	};
	
	
	public static double XOR_IDEAL[][] = {
			{0.0},
			{1.0},
			{1.0},
			{0.0}
	};

	public static void main(String[] args) {

		final int QTD_ENTRADAS = 2;
		final int QTD_NEURONIOS_OCULTOS = 3;
		final int QTD_NEURONIOS_SAIDA =4;

		RedeNeural rna = new RedeNeural(QTD_ENTRADAS, QTD_NEURONIOS_OCULTOS, QTD_NEURONIOS_SAIDA);

		mostrarDados(XOR_INPUT);

		double[] yS = rna.treinar(XOR_INPUT);
////
////		
		double[] entrada = {1.0, 0.0};
//		
////		
		//showVector(yS, 1, 4, true);
//		
//		
		 yS = rna.classifica(entrada);
		 
		 showVector(yS, 1, 4, true);
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
	
	private static void mostrarDados(double dados[][]) {
		for (int x = 0; x < dados.length; x++) {
			System.out.print("{");
			for (int y = 0; y < dados[0].length; y++) {
				System.out.printf("%s ", " " + dados[x][y]);
			}
			System.out.println("}");
		}
	}

}
