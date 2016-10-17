package br.com.estudo.busca.custouniforme;

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
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import br.com.estudo.busca.Grafo;
import br.com.estudo.busca.Node;
import br.com.estudo.busca.map.MapBaseApp;
/**
 * BrFS - Breadth-first search
 * @author Helber
 *
 */
public class BuscaEmCustoUniforme extends MapBaseApp {
	
	private Node objetivo;

	boolean finalizar = false;
	private Queue<Node> queue;
	private List<Node> explorado = new ArrayList<Node>();
	private List<Node> nosOrdenados = new ArrayList<Node>();

	public BuscaEmCustoUniforme(Node objetivo) {
		queue = new LinkedList<Node>();
		this.objetivo = objetivo;
	}


	/**
	 * Busca em largura
	 * 
	 * @param node
	 */
	public void bfs(Node node) {
		node.setVisitado(true);
		queue.add(node);
		
		while (!queue.isEmpty()) {
			
			
			nosOrdenados.clear();
			
			for (Node n : queue) {
				nosOrdenados.add(n);
			}
			
			
			Collections.sort(nosOrdenados);
			
			queue.clear();
			for (Node n : nosOrdenados) {
				queue.add(n);
			}
			Node element = queue.remove();
			
			// verifica objetivo
			if(verificaObjetivo(element)){
				System.out.println("Elemento objetivo : " + element);
				explorado.add(element);
				getCanvas().setExplorado(explorado);
				getCanvas().repaint();
				
				return;
			}
			
			explorado.add(element);
			
			Iterable<Node> adj = grafo.adj(element);
			
			
			for (Node n : adj) {
				if (n != null && !explorado.contains(n) && !queue.contains(n)) {
					n.setVisitado(true);
					queue.add(n);
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
		Node objetivo =new Node(BUCHAREST, 1, 2, 0);
		BuscaEmCustoUniforme largura = new BuscaEmCustoUniforme(objetivo);
		largura.setVisible(true);

		Node node4 = new Node(ARAD, 50, 250, 140);;
		
		largura.bfs(node4);
		//System.out.println(largura.getG().toString());

		System.out.println(largura.getExplorado());

	}

}