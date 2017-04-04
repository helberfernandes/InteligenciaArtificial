package br.com.estudo.heuristicas;

import java.util.Comparator;

import br.com.estudo.busca.Node;

public class CostComparator2 implements Comparator<Node> {

	private Node pai = null;

	public CostComparator2(Node pai) {
		super();
		this.pai = pai;
	}

	public CostComparator2() {
		super();

	}

	@Override
	public int compare(Node o1, Node o2) {
		Integer total1 = 0;
		Integer total2 = 0;
		if (pai != null) {
			int totalPai = calcCost(pai);
			
			total1 =totalPai +  o1.getCusto().get(pai)+o1.gethCusto();
			if(o1.getCustoTotal()>total1 || o1.getCustoTotal()==0){
				o1.setCustoTotal(total1);
			}
			total2 =  totalPai + o2.getCusto().get(pai)+o2.gethCusto();
			if(o2.getCustoTotal()>total2 || o2.getCustoTotal()==0){
				o2.setCustoTotal(total2);
			}		
			
			if(o1.getNome().equals("Calgary")){
				o1.setCustoTotal(0);
			}
			
			if(o2.getNome().equals("Calgary")){
				o2.setCustoTotal(0);
			}
			
		}
		return Integer.compare(o1.getCustoTotal(), o2.getCustoTotal());
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
		return total;
	}
}
