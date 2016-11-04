package br.com.estudo.busca.algoritimogenetico.teste;
/**
 * 
 * Baseado no Livro Genetic Algorithms in Java Basics
 * Lee Jacobson
 * Burak Kanber
 *
 */
public class AllOnesGA {

	public static void main(String[] args) {
		int tamanhoPopulacao=5000;
		double taxaMutacao=0.01;
		double taxaCrossover=0.95;
		int contagemElitismo=0;
		 
		AlgoritimoGenetico ga = new AlgoritimoGenetico(tamanhoPopulacao, taxaMutacao, taxaCrossover, contagemElitismo);
		
		ga.setSolucao("que viva cristo rei");
		
		
		
		Populacao  populacao = ga.initPopulation(ga.getSolucao().length());
		ga.evalPopulacao(populacao);
		int geracao =1;
		
		while(ga.isTerminationConditionMet(populacao)==false){
			// Imprimir apto indivíduo da população
			System.out.println("Melhor Individuo: " + populacao.
					getFittest(0).toString()+" aptidao "+populacao.
					getFittest(0).getFitness());
			
			// Apply crossover
			populacao=ga.crossoverPopulacao(populacao);
//			// Apply mutation
			populacao = ga.mutacao(populacao);
			
			ga.evalPopulacao(populacao);
			geracao++;
		}
		
		System.out.println("Solucao encontrada na  " + geracao + " geracoes");
				System.out.println("Melhor Solução: " + populacao.getFittest(0).toString());
	}

}
