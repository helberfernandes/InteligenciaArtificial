package br.com.estudo.util;

import java.util.Comparator;
import br.com.estudo.busca.Node;

public class NodeComparator<Node> implements Comparator<Node> {

	@Override
	public int compare(Node o1, Node o2) {
		
		return o1.toString().compareTo(o2.toString());
	}

}
