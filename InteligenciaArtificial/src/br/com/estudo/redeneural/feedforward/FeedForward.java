package br.com.estudo.redeneural.feedforward;
/**
 * Este exemplo foi retirado do livro Neural Networks Using C# Succinctly
 * de James McCaffrey e tem tão somente o intuito de estudo.
 * Realizei algumas modificações do exemplo afim de criar uma 
 * ideia mais orientada a objeto, e simplificar um pouco, em algusn casos.
 */
public class FeedForward {

	public static void main(String[] args) {
		RedeNeural rna = new RedeNeural(3, 4, 2);

         double[] weights = new double[] {
             //pesos da camada de entrada
             0.01, 0.02, 0.03, 0.04,
             0.05, 0.06, 0.07, 0.08,
             0.09, 0.10, 0.11, 0.12,
             //bias da camada oculta
             0.13, 0.14, 0.15, 0.16,
             //pesos para a camada de saida
             0.17, 0.18,
             0.19, 0.20,
             0.21, 0.22,
             0.23, 0.24,

             // bias para a camada de saida
             0.25, 0.26

         };
         
         
         rna.setPesos(weights);
         
         double[] xValues = new double[] { 1.0, 2.0, 3.0 };
         
         double[] yValues = rna.classifica(xValues);
         
         
         showVector(yValues, 2, 4, true);
	}
	
	
	public static void showVector(double[] vector, int valsPerRow, int decimals, boolean newLine) {
        for (int i = 0; i < vector.length; ++i) {
            if (i % valsPerRow == 0)
               System.out.println(" ");
           System.out.print( String.format(" %.4f", vector[i]));;
           
        }
        if (newLine == true)  
        	System.out.println(" ");
    }

}
