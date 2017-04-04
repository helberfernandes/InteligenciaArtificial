package br.com.estudo.busca.gulosa;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import br.com.estudo.busca.Grafo;
import br.com.estudo.busca.Node;
import br.com.estudo.busca.map.MapBaseApp;
import br.com.estudo.heuristicas.HeuristicaN;


/**
 * GBFS-Greedy Best First Search
 * 
 * @author Helber
 *
 */
public class GreedyBestFirstSearch extends MapBaseApp {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1163755722547099105L;

	private Node objetivo;

	private Queue<Node> fronteira;// itens para ser explorados
	private Queue<Node> fronteiraTemp;
	private List<Node> explorado = new ArrayList<Node>();// lista com nos ja
															// explorados

	public GreedyBestFirstSearch(Node objetivo) {
		fronteira = new PriorityQueue<Node>(500, new HeuristicaN());
		fronteiraTemp= new PriorityQueue<Node>(500, new HeuristicaN());
		this.objetivo = objetivo;
	}

	/**
	 * GBFS-Greedy Best First Search
	 * 
	 * @param node
	 */
	public void gbfs(Node node) {
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
			List<Node> adj = grafo.adj(estado, Grafo.ORDER_HEURISTIC_N_COST);
			for (Node n : adj) {
				
				if (!fronteira.contains(n) && !explorado.contains(n)) {
					n.setPai(estado);					
					fronteira.offer(n);
				} else if (fronteira.contains(n)) {
					for (Node tmp : fronteira) {
						if (n.gethCusto() < tmp.gethCusto()) {
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
		Node objetivo = new Node("Calgary", 1, 2);
		Node inicio = new Node("Las Vegas", 1, 1);
		GreedyBestFirstSearch largura = new GreedyBestFirstSearch(objetivo);
		largura.setVisible(true);
		largura.gbfs(inicio);
		

		System.out.println(largura.getExplorado());

	}
	
	
	


}
