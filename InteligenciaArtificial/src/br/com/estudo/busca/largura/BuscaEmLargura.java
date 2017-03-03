package br.com.estudo.busca.largura;

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
 * BrFS - Breadth-first search
 * @author Helber
 *
 */
public class BuscaEmLargura extends MapBaseApp {
	
	private Node objetivo;

	boolean finalizar = false;
	private Queue<Node> fronteira;// itens para ser explorados
	private List<Node> explorado = new ArrayList<Node>();//lista com nos ja explorados

	public BuscaEmLargura(Node objetivo) {
		fronteira = new LinkedList<Node>();
		this.objetivo = objetivo;
	}


	/**
	 * Busca em largura - BREADTH FRIST SEARCH - BFS
	 * 
	 * @param node
	 */
	public void bfs(Node node) {
		node.setVisitado(true);
		fronteira.offer(node);
		
		while (!fronteira.isEmpty()) {
			//System.out.println("Fronteira "+fronteira);
			Node estado = fronteira.poll();//estado atual
			explorado.add(estado);
			
			// verifica objetivo
			if(verificaObjetivo(estado)){
				//System.out.println("Elemento objetivo : " + nodeActual);
				//solucao(nodeActual);
				finalizar=true;
				return;
			}
			
			
			
			//obtem todos os nos adjacentes do no atualmente explorado
			Iterable<Node> adj = grafo.adj(estado);
			for (Node n : adj) {
				if (!fronteira.contains(n) && !explorado.contains(n)) {
					n.setVisitado(true);
					fronteira.offer(n);
				}

			}
//			try {
//				Thread.sleep(100);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			for(Node n: fronteira){
//				if(!getCanvas().getExplorado().contains(n)){
//					getCanvas().getExplorado().add(n);
//				}
//			}
			
			getCanvas().repaint();
			
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
		Node objetivo =new Node(BUCHAREST, 1, 2, 0);
		BuscaEmLargura largura = new BuscaEmLargura(objetivo);
		largura.setVisible(true);

		Node node4 = new Node(ARAD, 1, 1, 0);

		largura.bfs(node4);
		//System.out.println(largura.getG().toString());

		System.out.println(largura.getExplorado());

	}

}
