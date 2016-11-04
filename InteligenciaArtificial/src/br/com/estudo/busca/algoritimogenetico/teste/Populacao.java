package br.com.estudo.busca.algoritimogenetico.teste;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;


/**
 * 
 * Baseado no Livro Genetic Algorithms in Java Basics
 * Lee Jacobson
 * Burak Kanber
 *
 */
public class Populacao {
	private Individuo [] populacao;
	private double populacaoFitness = -1;
	
	public Populacao(int tamanhoPopulacao) {
		populacao = new Individuo[tamanhoPopulacao];
	}

	public Populacao(int tamanhoPopulacao, int tamanhoCromossomo) {
		populacao = new Individuo[tamanhoPopulacao];
		//Cada individuo sera criado com genes aleatorios de 0 e 1
		for (int contagemIndividuo = 0; contagemIndividuo < tamanhoPopulacao; contagemIndividuo++) {
			this.populacao[contagemIndividuo] = new Individuo(tamanhoCromossomo);
		}
	}

	public Individuo[] getPopulacao() {
		return populacao;
	}
	/**
	 * Ordena e retorna  individuo com maior fitness.
	 * 
	 * @param offset
	 * @return
	 */
	public Individuo getFittest(int offset) {
		Arrays.sort(this.populacao, new Comparator<Individuo>() {

			@Override
			public int compare(Individuo o1, Individuo o2) {
				if (o1.getFitness() > o2.getFitness()) {
					return -1;
				} else if (o1.getFitness() < o2.getFitness()) {
					return 1;
				}
				return 0;
			}

		});

		return this.populacao[offset];
	}

	public double getPopulacaoFitness() {
		return populacaoFitness;
	}

	public void setPopulacaoFitness(double populacaoFitness) {
		this.populacaoFitness = populacaoFitness;
	}

	public int size() {
		return this.populacao.length;
	}

	public Individuo setIndividual(int offset, Individuo individual) {
		return populacao[offset] = individual;
	}

	public Individuo getIndividual(int offset) {
		return populacao[offset];
	}

	public void shuffle() {
		Random rnd = new Random();
		for (int i = populacao.length - 1; i > 0; i--) {
			int index = rnd.nextInt(i+1);
			Individuo a = populacao[index];
			populacao[index] = populacao[i];
			populacao[i]=a;
		}
	}
}
