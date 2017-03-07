package br.com.estudo.util;

import java.util.Comparator;

import br.com.estudo.busca.Node;

public class CostComparator implements Comparator<Node> {

	private Node pai;
	String caminho="";

	public CostComparator(Node pai) {
		super();
		this.pai = pai;
	}

	@Override
	public int compare(Node o1, Node o2) {
		o1.setPai(pai);
		o2.setPai(pai);
		int totalPai = calcCost(pai);
		
		Integer total1 =totalPai+o1.getCusto().get(pai);
		
		
		Integer total2 =totalPai+o2.getCusto().get(pai);
		
		System.out.println("Caminho pai "+caminho);
		return total1.compareTo(total2);
	}

	/**
	 * Busca por todos os custos dos pais, soma e retorna
	 * 
	 * @param pai2
	 */
	private int calcCost(Node pai) {
		int total = 0;
		if (pai.getPai() != null) {
			total = pai.getCusto().get(pai.getPai());
			caminho=" -- "+pai.getPai().getNome();
			calcCost(pai.getPai());
		}

		return total;

	}

}
