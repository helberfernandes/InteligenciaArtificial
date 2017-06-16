package br.com.estudo.busca.algoritimogenetico.teste;

import br.com.estudo.util.EstudoUtil;
/**
 * 
 * Baseado no Livro Genetic Algorithms in Java Basics
 * Lee Jacobson
 * Burak Kanber
 *
 */
public class Individuo {
	private char[] cromossomo;
	private int fitness = -1;
	//private String alfa = "abcdefghijlmnopqrstuvxzkyw ABCDEFGHIJLMNOPQRSTUVXZKYW";
	private String alfa = "abcdefghijlmnopqrstuvxzkyw ABCDEFGHIJLMNOPQRSTUVXZKYW";

	public Individuo(char[] cromossomo) {
		super();
		this.cromossomo = cromossomo;
	}
	/**
	 * Cria um cromossomo baseado no tamanho do cromossomo, este cromossomo
	 * e composto pelos characteres de alpa de forma randomica
	 * @param tamanhoCromossomo geralmente e o tamanho da solucao
	 */
	public Individuo(int tamanhoCromossomo) {
		this.cromossomo = new char[tamanhoCromossomo];
		for (int gene = 0; gene < tamanhoCromossomo; gene++) {
			this.setGene(gene, alfa.charAt(EstudoUtil.randInt(0, alfa.length() - 1)));
		}
	}

	public char[] getCromosomo() {
		return cromossomo;
	}

	public void setCromosomo(char[] cromossomo) {
		this.cromossomo = cromossomo;
	}

	public int getFitness() {
		return fitness;
	}

	public void setFitness(int fitness) {
		this.fitness = fitness;
	}

	public void setGene(int offset, char gene) {
		this.cromossomo[offset] = gene;
	}

	public char getGene(int offset) {
		return this.cromossomo[offset];
	}

	public int getTamanhoCromossomo() {
		return cromossomo.length;
	}
	
	/**
	 *  retorna uma string no formato 1111111111 que e a concatenacao de todos os genes
	 */
	public String toString() {
		String output = "";
		for (int gene = 0; gene < this.cromossomo.length; gene++) {
			output += this.cromossomo[gene];
		}
		return output;
	}
}
