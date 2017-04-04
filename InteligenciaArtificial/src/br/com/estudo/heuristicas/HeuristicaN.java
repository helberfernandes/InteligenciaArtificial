package br.com.estudo.heuristicas;

import java.util.Comparator;

import br.com.estudo.busca.Node;
/**
 * Heuristica H(n)
 * 
 * @author Programacao1
 *
 */
public class HeuristicaN  implements Comparator<Node> {
	
	public HeuristicaN() {
		super();

	}

	@Override
	public int compare(Node o1, Node o2) {
		return  Integer.compare(o1.gethCusto(), o2.gethCusto());
	}
}
