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
import java.util.Stack;

import br.com.estudo.busca.Grafo;
import br.com.estudo.busca.Node;
import br.com.estudo.busca.map.MapBaseApp;

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
//	public void dfs(Node node) {
//		if (verificaObjetivo(node)) {
//			System.out.println("Elemento objetivo : " + node);
//			solucao(node);
//			return;
//		} else {
//			node.setVisitado(true);
//			queue.add(node);
//
//			Node element = queue.remove();
//			explorado.add(element);
//			Iterable<Node> adj = grafo.adj(element);
//			for (Node n : adj) {
//				if (n != null && !n.isVisitado()) {
//					dfs(n);
//				}
//
//			}
//		}
//
//	}
	
	
	public void dfs(Node node){
		fronteira.push(node);
		explorado.clear();
		
		while(!fronteira.isEmpty()){
			System.out.println(fronteira);
			Node estado= fronteira.pop();
			explorado.add(estado);
			if (verificaObjetivo(estado)) {
				
				getCanvas().setExplorado(explorado);
				getCanvas().repaint();
				return;
			} 
			Iterable<Node> adj = grafo.adj(estado);
			for (Node n : adj) {
				if (!fronteira.contains(n) && !explorado.contains(n)) {
					fronteira.push(n);
				}

			}
			
		}
	}
	
	private void solucao(Node element) {
		List<Node> solucao = new ArrayList<Node>();
		
		
		
		// Deveria ser comportamento da busca?
				String retorno = "";
				Node no = element;
				solucao.add(no);
				retorno += no.getNome();
				while (no.getPai() != null) {
					no = no.getPai();
					solucao.add(no);
					retorno = no.getNome() + " " + retorno;
				}
				 System.out.println("REsultado "+retorno);
		
		
		getCanvas().setExplorado(solucao);
		getCanvas().repaint();
	
		
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
		BuscaEmProfundidade largura = new BuscaEmProfundidade(objetivo);
		largura.setVisible(true);

		Node node4 = new Node(ARAD, 1, 1, 0);

		largura.dfs(node4);
		// System.out.println(largura.getG().toString());

		System.out.println(largura.getExplorado());

	}

}
