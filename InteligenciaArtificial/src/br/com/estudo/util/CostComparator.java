package br.com.estudo.util;

import java.util.Comparator;

import br.com.estudo.busca.Node;

public class CostComparator implements Comparator<Node> {

	private Node pai = null;

	public CostComparator(Node pai) {
		super();
		this.pai = pai;
	}

	public CostComparator() {
		super();

	}

	@Override
	public int compare(Node o1, Node o2) {
		Integer total1 = 0;
		Integer total2 = 0;
		if (pai != null) {
			int totalPai = calcCost(pai);

			total1 = totalPai + o1.getCusto().get(pai);
			if(o1.getCustoTotal()>total1 || o1.getCustoTotal()==0){
				o1.setCustoTotal(total1);
			}
			total2 = totalPai + o2.getCusto().get(pai);
			if(o2.getCustoTotal()>total2 || o2.getCustoTotal()==0){
				o2.setCustoTotal(total2);
			}
			// System.out.println("Caminho pai " + totalPai);
			// System.out.println("Filho 1 " + o1.getNome() + " total " +
			// total1);
			// System.out.println("Filho 2 " + o2.getNome() + " total " +
			// total2);
			// System.out.println();
			// System.out.println();
		}

		return pai != null ? total1.compareTo(total2) : Integer.compare(o1.getCustoTotal(), o2.getCustoTotal());
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

			total += calcCost(pai.getPai());
		}

		// while (pai.getPai() != null) {
		// pai = pai.getPai();
		// total+=pai.getCusto().get(pai.getPai());
		//
		// }

		return total;

	}

}
