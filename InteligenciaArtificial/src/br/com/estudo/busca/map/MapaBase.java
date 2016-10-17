package br.com.estudo.busca.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JFrame;

import br.com.estudo.busca.Grafo;
import br.com.estudo.busca.Node;

public class MapaBase extends JFrame  {
	public static final String ARAD = "Arad";
	public static final String ZERIND = "zerind";
	public static final String ORADEA = "Oradea";
	public static final String TIMISOARA = "Timisoara";
	public static final String LUGOJ = "Lugoj";
	public static final String MEHADIA = "Mehadia";
	public static final String DOBRETA = "Dobreta";
	public static final String CRAIOVA = "craiova";
	public static final String SIBIU = "Sibiu";
	public static final String RIMNICU_VILCEA = "Rimnicu Vilcea";
	public static final String FAGARAS = "Fagaras";
	public static final String PITESTI = "Pitesti";
	public static final String BUCHAREST = "Bucharest";

	protected Grafo grafo = new Grafo(9);
	
	
	/**
	 * cria o estado inicial do jogo 458 016 723
	 */
	public void iniciaJogo() {

		Node arad =new Node(ARAD, 50, 250, 0);
		Node zerind= new Node(ZERIND, 70, 180, 75);
		Node timisoara=new Node(TIMISOARA, 50, 350, 118);
		Node sibiu= new Node(SIBIU, 230, 270, 140);
		Node oradea=new Node(ORADEA, 90, 110, 71);
		Node lugoj=new Node(LUGOJ, 150, 390, 111);
		Node mehadia= new Node(MEHADIA, 155, 430, 70);
		Node dobreta=new Node(DOBRETA, 150, 480, 75);
		Node craiova=new Node(CRAIOVA, 300, 500, 120);
		Node pitesti= new Node(PITESTI, 420, 390, 97);
		Node rimunicuVilcea= new Node(RIMNICU_VILCEA, 290, 350, 80);
		Node fagaras=new Node(FAGARAS, 400, 270, 99);
		Node bucharest =new Node(BUCHAREST, 550, 440, 0);
		
		grafo.adicionaAresta(arad, zerind);
		grafo.adicionaAresta(arad, timisoara);
		grafo.adicionaAresta(arad, sibiu);
		grafo.adicionaAresta(zerind, oradea);
		grafo.adicionaAresta(oradea, sibiu);
		grafo.adicionaAresta(timisoara, lugoj);
		grafo.adicionaAresta(lugoj, mehadia);
		grafo.adicionaAresta(mehadia, dobreta);
		grafo.adicionaAresta(dobreta, craiova);
		grafo.adicionaAresta(craiova, pitesti);
		grafo.adicionaAresta(craiova, rimunicuVilcea);
		grafo.adicionaAresta(rimunicuVilcea, sibiu);
		grafo.adicionaAresta(sibiu, fagaras);
		grafo.adicionaAresta(pitesti, rimunicuVilcea);
		grafo.adicionaAresta(pitesti,bucharest);
		grafo.adicionaAresta(fagaras, bucharest);
		System.out.println("Inicio ");
		System.out.println(grafo.toString());
		System.out.println("-------------------------------------------");
	}
	
	
	public Grafo getGrafo() {
		return grafo;
	}


	public void setGrafo(Grafo grafo) {
		this.grafo = grafo;
	}


	public static void main(String[] args) {
		List<Node> nosOrdenados = new ArrayList<Node>();
		MapaBase  base = new MapaBase();
		 base.iniciaJogo();
		base.setVisible(true);
		for (Node n : base.getGrafo().getNodes()) {
			nosOrdenados.add(n);
			System.out.println("Ordem "+n.getNome()+" custo "+n.getCusto());
		}
		System.out.println("--------------------------------------------------");
		
		Collections.sort(nosOrdenados);
		
		for (Node n : nosOrdenados) {
			System.out.println("Ordem "+n.getNome()+" custo "+n.getCusto());
		}
		
	}

}
