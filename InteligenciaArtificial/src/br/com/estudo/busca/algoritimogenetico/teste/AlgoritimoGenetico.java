package br.com.estudo.busca.algoritimogenetico.teste;

import org.omg.CORBA.portable.IndirectionException;
/**
 * 
 * Baseado no Livro Genetic Algorithms in Java Basics
 * Lee Jacobson
 * Burak Kanber
 *
 */
public class AlgoritimoGenetico {
	private int tamanhoPopulacao;
	private double taxaMutacao;
	private double taxaCrossover;
	private int contagemElitismo;
	private static String solucao;

	public AlgoritimoGenetico(int tamanhoPopulacao, double taxaMutacao, double taxaCrossover, int contagemElitismo) {
		super();
		this.tamanhoPopulacao = tamanhoPopulacao;
		this.taxaMutacao = taxaMutacao;
		this.taxaCrossover = taxaCrossover;
		this.contagemElitismo = contagemElitismo;
	}

	public Populacao initPopulation(int TamanhoCromosomo) {
		Populacao populacao = new Populacao(this.tamanhoPopulacao, TamanhoCromosomo);
		return populacao;
	}
	/**
	 * Calculamos a aptidao diacordo com as letras certas no lugares certos
	 * ex. dado o objetivo : flamengo.
	 * Caso apareca a seguinte palavra  flaeznjt, sua aptidao sera 3, pois existem 3 letras nolugares certos
	 * alem de serem as letras que procuramos
	 * 
	 * @param individuo
	 * @return
	 */
	public int calcFitness(Individuo individuo) {
		int correctGenes = 0;

		for (int geneIndex = 0; geneIndex < individuo.getTamanhoCromossomo(); geneIndex++) {
			if (individuo.getGene(geneIndex) == solucao.charAt(geneIndex)) {
				correctGenes ++;
			}
		}
		
		

//		double fitness = (double) correctGenes / individuo.getTamanhoCromossomo();
//
		individuo.setFitness(correctGenes);
		return correctGenes;
	}
	/**
	 * Calcula a fitness de toda a populacao, inicialmente se calcula a fitness de cada
	 * individuo, e posteriormente soma esta fitness com o restante da populacao, uma vez calculado
	 * adiciona na fitness total da populacao.
	 * @param populacao
	 */
	public void evalPopulacao(Populacao populacao) {
		double populacaoFitness = 0;
		for (Individuo individuo : populacao.getPopulacao()) {
			populacaoFitness += calcFitness(individuo);
		}
		populacao.setPopulacaoFitness(populacaoFitness);
	}
	
	/**
	 * A solucao termina somente quando e encontrado algum individuao igual ao
	 * objetivo.
	 * @param populacao
	 * @return
	 */
	public boolean isTerminationConditionMet(Populacao populacao) {
		for (Individuo individuo : populacao.getPopulacao()) {
			
			if (individuo.toString().equals(solucao)) {
				return true;
			}
			
			
		}
		return false;
	}

	public Individuo selecionaPai(Populacao populacao) {
		// obtento os individuos
		Individuo individuos[] = populacao.getPopulacao();

		// Roleta spin
		double populacaoFitness = populacao.getPopulacaoFitness();
		double posicaoRoleta = Math.random() * populacaoFitness;

		// encontrando o pai
		double roleta = 0;

		for (Individuo individuo : individuos) {
			roleta += individuo.getFitness();
			if (roleta >= posicaoRoleta) {
				return individuo;
			}
		}
		return individuos[populacao.size() - 1];
	}

	public Populacao crossoverPopulacao(Populacao populacao) {
		// cria uma nova populacao
		Populacao novaPopulacao = new Populacao(populacao.size());

		// Loop sobre aptidão da população atual
		for (int populacaoIndex = 0; populacaoIndex < populacao.size(); populacaoIndex++) {

			Individuo mae = populacao.getFittest(populacaoIndex);

			// Aplicar cruzado para este indivíduo?
			if (taxaCrossover > Math.random() && populacaoIndex > contagemElitismo) {
				// Inicializa descendentes
				Individuo filho = new Individuo(mae.getTamanhoCromossomo());
				// encontrando a pai
				Individuo pai = selecionaPai(populacao);

				// Loop sobre genoma
				for (int geneIndex = 0; geneIndex < mae.getTamanhoCromossomo(); geneIndex++) {
					// Use metade dos genes do pai
					//obtem o ponto unico para o cressover, para habilitar deve trocar a linha if (swapPoint< Math.random()) {
					int swapPoint = (int) (Math.random() * (mae.getTamanhoCromossomo() + 1));
					/*
					 * toda vez que o random for menor que 0.5 o filho recebera
					 * um gene da mae do contrario sera do pai, teoricamente
					 * neste casao 50 para cada
					 */
					
					//if (swapPoint< Math.random()) {// aqui implementa o crossover de ponto unico basta trocar esta linha pela a baixo
					
					if (0.5 > Math.random()) {
						filho.setGene(geneIndex, mae.getGene(geneIndex));
					} else {
						filho.setGene(geneIndex, pai.getGene(geneIndex));
					}
				}
				// adiciona o filho a nova populacao
				novaPopulacao.setIndividual(populacaoIndex, filho);
			} else {
				/*
				 * Adicionar indivíduo a nova população sem aplicar Crossover
				 * adiciona o filho a nova populacao, este individuo no caso
				 * sera o que tiver maior fitness na populacao antiga
				 */
				novaPopulacao.setIndividual(populacaoIndex, mae);
			}
		}
		return novaPopulacao;
	}

	public Populacao mutacao(Populacao populacao) {
		// inicializa nova Populacao
		Populacao novaPopulacao = new Populacao(tamanhoPopulacao);

		// Loop sobre aptidão da população atual
		for (int populacaoIndex = 0; populacaoIndex < populacao.size(); populacaoIndex++) {
			Individuo mae = populacao.getFittest(populacaoIndex);
			// Loop over individual's genes
			for (int geneIndex = 0; geneIndex < mae.getTamanhoCromossomo(); geneIndex++) {
				// Ir mutação se este é um indivíduo elite
				if(populacaoIndex>=this.contagemElitismo){
					// Será que este gene precisa mutação?
					if(taxaMutacao>Math.random()){
						// Get new gene
						int newGene = 1;
						if(mae.getGene(geneIndex)==1){
							newGene=0;
						}
						//muda gene
						//mae.setGene(geneIndex, newGene);
					}
				}
			}
			novaPopulacao.setIndividual(populacaoIndex, mae);
		}
		return novaPopulacao;
	}

	public static String getSolucao() {
		return solucao;
	}

	public static void setSolucao(String solucao) {
		AlgoritimoGenetico.solucao = solucao;
	}
	
	

}
