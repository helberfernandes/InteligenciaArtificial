package br.com.estudo.busca;

public class TestaGrafo {
		private static final String ARAD="Arad";
		private static final String ZERIND="zerind";
		private static final String ORADEA="Oradea";
		private static final String TIMISOARA="Timisoara";
		private static final String LUGOJ="Lugoj";
		private static final String MEHADIA="Mehadia";
		private static final String DOBRETA="Dobreta";
		private static final String CRAIOVA="craiova";
		private static final String SIBIU="Sibiu";
		private static final String RIMNICU_VILCEA="Rimnicu Vilcea";
		private static final String FAGARAS="Fagaras";
		private static final String PITESTI="Pitesti";
		private static final String BUCHAREST="Bucharest";
		
		
	public static void main(String[] args) {
		Grafo g = new Grafo(9);

		g.adicionaAresta(new Node(ARAD, 1, 1, 0), new Node(ZERIND, 1, 0, 75));
		g.adicionaAresta(new Node(ARAD, 1, 1, 0), new Node(TIMISOARA, 1, 2, 118));
		g.adicionaAresta(new Node(ARAD, 1, 1, 0), new Node(SIBIU, 2, 1, 140));
		g.adicionaAresta(new Node(ZERIND, 2, 1, 75), new Node(ORADEA, 2, 2, 0));
		g.adicionaAresta(new Node(ORADEA, 2, 2, 0), new Node(SIBIU, 1, 2, 0));		
		g.adicionaAresta(new Node(TIMISOARA, 2, 2, 118), new Node(LUGOJ, 1, 2, 0));		
		g.adicionaAresta(new Node(LUGOJ, 2, 2, 0), new Node(MEHADIA, 1, 2, 0));
		g.adicionaAresta(new Node(MEHADIA, 2, 2, 0), new Node(DOBRETA, 1, 2, 0));
		g.adicionaAresta(new Node(DOBRETA, 2, 2, 0), new Node(CRAIOVA, 1, 2, 0));
		g.adicionaAresta(new Node(CRAIOVA, 2, 2, 0), new Node(PITESTI, 1, 2, 0));
		g.adicionaAresta(new Node(CRAIOVA, 2, 2, 0), new Node(RIMNICU_VILCEA, 1, 2, 0));		
		g.adicionaAresta(new Node(RIMNICU_VILCEA, 2, 2, 0), new Node(SIBIU, 1, 2, 0));		
		g.adicionaAresta(new Node(SIBIU, 2, 2, 140), new Node(FAGARAS, 1, 2, 0));
		g.adicionaAresta(new Node(PITESTI, 2, 2, 0), new Node(RIMNICU_VILCEA, 1, 2, 0));
		g.adicionaAresta(new Node(PITESTI, 2, 2, 0), new Node(BUCHAREST, 1, 2, 0));		
		g.adicionaAresta(new Node(FAGARAS, 2, 2, 0), new Node(BUCHAREST, 1, 2, 0));
		


		System.out.println(g.toString());

//		g.updateNode(new Node("4", 1, 0, 0), new Node("0", 0, 0, 0));
//
//		System.out.println(g.toString());

		// g.(new Node("0", 0, 0, 0));
	}
}
