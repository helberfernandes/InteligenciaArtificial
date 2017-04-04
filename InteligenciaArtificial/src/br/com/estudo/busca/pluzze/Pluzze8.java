package br.com.estudo.busca.pluzze;

import java.util.List;

import br.com.estudo.busca.Grafo;
import br.com.estudo.busca.Node;
import br.com.estudo.busca.largura.BuscaEmLargura;

public class Pluzze8 {
	protected Grafo grafo = new Grafo(9);
	
	
	public void initGrafo(){
		Node node0 = new Node("0");
		Node node1 = new Node("1");
		Node node2 = new Node("2");
		Node node3 = new Node("3");
		Node node4 = new Node("4");
		Node node5 = new Node("5");
		Node node6 = new Node("6");
		Node node7 = new Node("7");
		Node node8 = new Node("8");
		
		grafo.adicionaAresta(node0, node1);
		grafo.adicionaAresta(node0, node7);
		
		grafo.adicionaAresta(node1, node2);
		grafo.adicionaAresta(node1, node8);
		
		
		grafo.adicionaAresta(node2, node3);
		
		
		grafo.adicionaAresta(node3, node8);
		grafo.adicionaAresta(node3, node4);
		
		
		grafo.adicionaAresta(node4, node5);
		
		grafo.adicionaAresta(node5, node6);
		grafo.adicionaAresta(node5, node8);
		
		
		grafo.adicionaAresta(node6, node7);
		
		grafo.adicionaAresta(node7, node8);
		
		//printGrafo();
	}
	
	
	
	private void printGrafo() {
		Node node0 = new Node("0");
		System.out.print(node0.getNome()+" ");
		
		List<Node> adj = grafo.adj(node0, Grafo.ORDER_NAME);
		for (Node n : adj) {
			System.out.print(n.getNome());
		}
		
	}


	public static void main(String[] args) {
		
		Pluzze8 pluzze8 = new Pluzze8();
		pluzze8.initGrafo();
		
		
		

	}

}
