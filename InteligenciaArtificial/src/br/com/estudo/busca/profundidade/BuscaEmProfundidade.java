package br.com.estudo.busca.profundidade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import br.com.estudo.busca.Grafo;
import br.com.estudo.busca.Node;
import br.com.estudo.busca.map.MapBaseApp;
import br.com.estudo.util.NodeComparator;

/**
 * DFS - Depth-first search
 * 
 * @author Helber
 *
 */
public class BuscaEmProfundidade extends MapBaseApp {

	private Node objetivo;

	boolean finalizar = false;
	private Stack<Node> fronteira;
	private List<Node> explorado = new ArrayList<Node>();

	public BuscaEmProfundidade(Node objetivo) {
		fronteira = new Stack<Node>();
		this.objetivo = objetivo;
	}

	/**
	 * Busca em largura
	 * 
	 * @param node
	 */
	public void dfs(Node node) {
		fronteira.push(node);
		explorado.clear();

		while (!fronteira.isEmpty()) {

			Node estado = fronteira.pop();
			explorado.add(estado);
			if (verificaObjetivo(estado)) {
				getCanvas().setExplorado(explorado);
				getCanvas().repaint();
				return;
			}
			List<Node> adj2 = grafo.adj(estado, Grafo.ORDER_NAME);

			/*
			 * a lista vem ordenada neste caso para funcionar com a pilha devo
			 * colocar em ordem reversa pois dara errado em ordem cescente
			 * exemplo: numa pilha adiciono em ordem A, B,C se desejo explorar A
			 * por primeiro a pilha me retornara C entao eu coloco em ordem
			 * reversa para resolver o problema
			 */
			Collections.reverse(adj2);

			for (Node n : adj2) {
				if (!fronteira.contains(n) && !explorado.contains(n)) {
					fronteira.push(n);
				}
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
		Node objetivo = new Node("Vancouver", 1, 2);
		BuscaEmProfundidade largura = new BuscaEmProfundidade(objetivo);
		largura.setVisible(true);

		Node node4 = new Node("Chicago", 1, 1);

		largura.dfs(node4);

		System.out.println(largura.getExplorado());

	}

}
