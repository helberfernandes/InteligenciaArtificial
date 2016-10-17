package br.com.estudo.busca.profundidade;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import br.com.estudo.busca.Grafo;
import br.com.estudo.busca.Node;
import br.com.estudo.busca.map.MapBaseApp;

/**
 * DFS - Depth-first search
 * 
 * @author Helber
 *
 */
public class BuscaEmProfundidadeApp extends MapBaseApp {

	private Node objetivo;

	boolean finalizar = false;
	private Queue<Node> queue;
	private List<Node> explorado = new ArrayList<Node>();

	public BuscaEmProfundidadeApp(Node objetivo) {
		queue = new LinkedList<Node>();
		this.objetivo = objetivo;
	}

	/**
	 * Busca em largura
	 * 
	 * @param node
	 */
	public void dfs(Node node) {
		if (verificaObjetivo(node)) {
			System.out.println("Elemento objetivo : " + node);
			explorado.add(node);
			getCanvas().setExplorado(explorado);
			getCanvas().repaint();
			finalizar = true;
			return;
		} else {
			node.setVisitado(true);
			queue.add(node);

			Node element = queue.remove();
			explorado.add(element);
			Iterable<Node> adj = grafo.adj(element);
			for (Node n : adj) {
				if (n != null && !n.isVisitado()) {
					dfs(n);
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
		Node objetivo = new Node(BUCHAREST, 1, 2, 0);
		BuscaEmProfundidadeApp largura = new BuscaEmProfundidadeApp(objetivo);
		largura.setVisible(true);

		Node node4 = new Node(ARAD, 1, 1, 0);

		largura.dfs(node4);
		// System.out.println(largura.getG().toString());

		System.out.println(largura.getExplorado());

	}

}
