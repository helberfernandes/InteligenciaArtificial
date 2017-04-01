package br.com.estudo.busca.aestrela;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import br.com.estudo.busca.Grafo;
import br.com.estudo.busca.Node;
import br.com.estudo.busca.map.MapBaseApp;
import br.com.estudo.util.CostComparator2;

/**
 * UCS - Uniform-Cost Search
 * 
 * @author Helber
 *
 */
public class BuscaAEstrela extends MapBaseApp {
	private Node objetivo;

	private Queue<Node> fronteira;// itens para ser explorados
	private Queue<Node> fronteiraTemp;
	private List<Node> explorado = new ArrayList<Node>();// lista com nos ja
															// explorados

	public BuscaAEstrela(Node objetivo) {
		fronteira = new PriorityQueue<Node>(500, new CostComparator2());
		fronteiraTemp= new PriorityQueue<Node>(500, new CostComparator2());
		this.objetivo = objetivo;
	}

	/**
	 * UCS - Uniform-Cost Search
	 * 
	 * @param node
	 */
	public void ucs(Node node) {
		
		
		
		node.setVisitado(true);
		fronteira.offer(node);

		while (!fronteira.isEmpty()) {			
			System.out.println("Fronteira " + fronteira);
			Node estado = fronteira.poll();// estado atual
			explorado.add(estado);
			getCanvas().getCidades().add(estado);
			getCanvas().repaint();
			// verifica objetivo
			if (verificaObjetivo(estado)) {
				return;
			}

			boolean remover = false;
			// obtem todos os nos adjacentes do no atualmente explorado
			List<Node> adj = grafo.adj(estado, Grafo.ORDER_COST);
			for (Node n : adj) {
				if (!fronteira.contains(n) && !explorado.contains(n)) {
					n.setPai(estado);					
					fronteira.offer(n);
				} else if (fronteira.contains(n)) {
					for (Node tmp : fronteira) {
						if (n.getCustoTotal() < tmp.getCustoTotal()) {
							remover = false;
						}
					}
					if (remover) {
						fronteira.remove(n);
						fronteira.add(n);
					}
					remover = false;
				}
			}

			fronteiraTemp.clear();
			for(Node n: fronteira){
				fronteiraTemp.add(n);
			}
			
			fronteira.clear();
			for(Node n: fronteiraTemp){
				fronteira.add(n);
			}
			
		}
	}

	private boolean verificaObjetivo(Node element) {
		if (objetivo.equals(element)) {
			return true;
		}
		return false;
	}

	public Grafo getG() {
		return grafo;
	}

	public void setG(Grafo g) {
		this.grafo = grafo;
	}

	public List<Node> getExplorado() {
		return explorado;
	}

	public void setExplorado(List<Node> explorado) {
		this.explorado = explorado;
	}

	public static void main(String[] args) {
		Node objetivo = new Node("Duluth", 1, 2);
		BuscaAEstrela largura = new BuscaAEstrela(objetivo);
		largura.setVisible(true);

		Node node4 = new Node("Montreal", 1, 1);

		largura.ucs(node4);
		// System.out.println(largura.getG().toString());

		System.out.println(largura.getExplorado());

	}

}
