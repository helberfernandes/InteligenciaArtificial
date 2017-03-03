package br.com.estudo.redeneural.backpropagation;
/**
 * Este exemplo foi retirado do livro Neural Networks Using C# Succinctly
 * de James McCaffrey e tem tão somente o intuito de estudo.
 * Realizei algumas modificações do exemplo afim de criar uma 
 * ideia mais orientada a objeto, e simplificar um pouco, em algusn casos.
 */
public class Backprogation {

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
         System.out.println("Pesos iniciais:");
         showVector(weights, 8, 2, true);
         rna.setPesos(weights);
         
         
         double[] xValues = new double []{ 1.0, 2.0, 3.0 }; // Inputs. 
         double[] tValues = new double []{ 0.2500, 0.7500 }; // Target outputs. 
         
         System.out.println("Entradas:");
         showVector(xValues, 3, 1, true);
         
         System.out.println("Esperados:");
         showVector(tValues, 2, 4, true);
         double learnRate = 0.05;
         /**
          * Momentum é um fator adicional adicionado (ou essencialmente subtraído se o fator é negativo)
          *  para cada peso e valor de polarização. O uso do momentum é projetado primeiramente para acelerar
          *   o treinamento quando a taxa de aprendizagem é pequena, como é geralmente. Observe que ao definir 
          *   o valor do termo de momentum para 0.0, você essencialmente omitirá o fator de momentum
          */
         double momentum = 0.01;
         int maxEpochs = 1000;
         
         System.out.println("Taxa de aprendizado: "+learnRate);
         System.out.println("Momentum: "+momentum);
         System.out.println("Max Epocas: "+maxEpochs);
         rna.encontrarPesos(tValues, xValues, learnRate, momentum, maxEpochs);
      
         double[] bestWeights = rna.getPesos();
         System.out.println("Melhores pesos encontrados");
         showVector(bestWeights, 8, 4, true);
         
	}
	
	public static void showVector(double[] vector, int valsPerRow, int decimals, boolean newLine) {
        for (int i = 0; i < vector.length; ++i) {
            if (i>0 && i % valsPerRow == 0)
               System.out.println(" ");
           System.out.print( String.format(" %."+decimals+"f", vector[i]));;
           
        }
        if (newLine == true)  
        	System.out.println(" ");
    }

}
