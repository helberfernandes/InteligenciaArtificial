package br.com.estudo.busca;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Grafo {

	private int numVertices;
	private int numArestas;
	private HashMap<Node, ArrayDeque<Node>> adj;
	private Node root;

	private List<Node> nodes = new ArrayList<Node>();

	public Grafo(int numVertices) {
		super();
		this.numVertices = numVertices;
		this.numArestas = 0;
		this.adj = new HashMap<>();
	}

	public void add(Node u, Node v) {
		ArrayDeque<Node> d = new ArrayDeque<Node>();

		d.add(v);
		if (!u.isInicioObjetivo()) {
			u.setPai(v);
		}
		adj.put(u, d);

	}

	public void adicionaAresta(Node u, Node v) {
		numArestas++;
		if (root == null) {
			root = u;
		}
		if (adj.get(u) != null) {
			adj.get(u).add(v);
		} else {
			add(u, v);
		}

		if (adj.get(v) != null) {
			adj.get(v).add(u);
		} else {
			add(v, u);
		}

		if (!nodes.contains(u)) {
			nodes.add(u);
		}

		if (!nodes.contains(v)) {
			nodes.add(v);
		}

	}

	public Iterable<Node> adj(Node v) {
		return adj.get(v);
	}

	public void updateNode(Node a, Node b) {

		nodes.get(nodes.indexOf(a)).setPosicaoX(a.getPosicaoX());
		nodes.get(nodes.indexOf(a)).setPosicaoY(a.getPosicaoY());

		nodes.get(nodes.indexOf(b)).setPosicaoX(b.getPosicaoX());
		nodes.get(nodes.indexOf(b)).setPosicaoY(b.getPosicaoY());

		// atualizando todos os nos, depois da mudanca
		for (Node n : nodes) {
			updateAdjacente(n);
		}

	}

	/**
	 * Atualiza os adjacentes, quando um no trocar de posicao
	 * 
	 * @param v
	 */
	public void updateAdjacente(Node v) {
		ArrayDeque<Node> tmp = new ArrayDeque<Node>();
		int x;
		int y;

		for (Node n : nodes) {
			// obtendo os adjacentes de cima e a baixo
			x = v.getPosicaoX() - 1;
			if (x != -1) {
				if (n.getPosicaoX() == x && n.getPosicaoY() == v.getPosicaoY()) {
					tmp.add(n);
				}

			}
			x = v.getPosicaoX() + 1;
			if (x <= 2) {
				if (n.getPosicaoX() == x && n.getPosicaoY() == v.getPosicaoY()) {
					tmp.add(n);
				}

			}

			// obtendo os adjacentes de deireito e esquerdo
			y = v.getPosicaoY() - 1;
			if (y != -1) {
				if (n.getPosicaoY() == y && n.getPosicaoX() == v.getPosicaoX()) {
					tmp.add(n);
				}

			}
			y = v.getPosicaoY() + 1;
			if (y <= 2) {
				if (n.getPosicaoY() == y && n.getPosicaoX() == v.getPosicaoX()) {
					tmp.add(n);
				}

			}

		}
		adj.replace(v, tmp);
	}

	public Node getNode(Node v) {
		// return adj.get(v).element();

		return nodes.get(nodes.indexOf(v));
	}

	public List<Node> getNodes() {
		return nodes;
	}

	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}

	public int getNumVertices() {
		return numVertices;
	}

	public void setNumVertices(int numVertices) {
		this.numVertices = numVertices;
	}

	public int getNumArestas() {
		return numArestas;
	}

	public void setNumArestas(int numArestas) {
		this.numArestas = numArestas;
	}

	public HashMap<Node, ArrayDeque<Node>> getAdj() {
		return adj;
	}

	public void setAdj(HashMap<Node, ArrayDeque<Node>> adj) {
		this.adj = adj;
	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		String SL = System.getProperty("line.separator");

		for (Object o : adj.keySet().toArray()) {
			Node n = (Node) o;

			s.append(n.getNome()).append(" -->");

			for (Node u : adj(n)) {
				s.append(u.getNome() + " Custo " + u.getCusto()).append(",");
				//s.append(u.getNome() + "  --> " ).append(",");
			}
			s.append(SL);
		}

		s.append("----------------------------\n");

		// for (int x = 0; x < 4; x++) {
		// for (int y = 0; y < 4; y++) {
		// for (Node n : nodes) {
		// if (n.getPosicaoX() == x && n.getPosicaoY() == y) {
		// // System.out.print(list.get(i).getNome());
		// s.append(n.getNome());
		//
		// }
		// }
		// s.append(" ");
		// // System.out.print(" ");
		// }
		s.append("\n");
		// System.out.println("");
		// }

		return s.toString();
	}

	// @Override
	// public String toString() {
	// StringBuilder s = new StringBuilder();
	//
	// //List<Node> nodes = adj.keySet().iterator();
	//
	//// for(int v=0; v<9;v++){
	//// for(Node u:adj(new Node(String.valueOf(v), 0, 0, 0))){
	////
	//// if(!list.contains(u)){
	//// list.add(u);
	//// }
	//// }
	//// }
	//
	// for(int x =0;x<4;x++){
	// for(int y =0;y<4;y++){
	// for(Node n : nodes){
	// if(n.getPosicaoX()==x&& n.getPosicaoY()==y){
	// //System.out.print(list.get(i).getNome());
	// s.append(n.getNome());
	//
	// }
	// }
	// s.append(" ");
	// //System.out.print(" ");
	// }
	// s.append("\n");
	// //System.out.println("");
	// }
	//
	//
	// return s.toString();
	// }

}
