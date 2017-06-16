package br.com.estudo.redeneural.feedforward.classificacao;

public class BackPropagation {

	static double AMOSTRAS[][] = {
			{  1,   1,  -1}, // Gato
			{ -1,   1,   1}, // Cao				
			{  1,  -1,   1}, // Cavalo
			{ -1,  -1,  -1}, // Galinha
			
	};
	
	

	public static double INPUT_DATA[] = { -1,   1 };

	

	public static void main(String[] args) {

		final int QTD_ENTRADAS = 2;
		final int QTD_NEURONIOS_OCULTOS = 2;
		final int QTD_NEURONIOS_SAIDA = 1;
		RedeNeural rna = new RedeNeural(QTD_ENTRADAS, QTD_NEURONIOS_OCULTOS, QTD_NEURONIOS_SAIDA);

		double[] yS = rna.treinar(AMOSTRAS);
		// showVector(yS, 1, 4, true);
		yS = rna.classifica(INPUT_DATA);

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
