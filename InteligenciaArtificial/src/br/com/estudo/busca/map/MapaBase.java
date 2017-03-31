package br.com.estudo.busca.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JFrame;

import br.com.estudo.busca.Grafo;
import br.com.estudo.busca.Node;
import br.com.estudo.map.util.Cidade;
import br.com.estudo.util.MapImport;

public class MapaBase extends JFrame {
	protected Grafo grafo = new Grafo(9);
	private List<Node> cidades = new ArrayList<Node>();
	
	
	
	/**
	 * cria o estado inicial do jogo 458 016 723
	 */
	public void importaMapa() {
		
		 cidades =MapImport.getMap();
		
		for(Node c: cidades){
			/*
			 * primeira cidade importada, para que sirva como inicio da montagem do mapa
			 * pois apartir dela obtenho todos os adjacentes.
			 */
			if(grafo.getRoot()==null){
				grafo.setRoot(c);
			}
			for(Node n:c.getFilhos()){
				grafo.adicionaAresta(c, n);
			}
			
		}
		
//		System.out.println("Inicio ");
//		System.out.println(grafo.toString());
//		System.out.println("-------------------------------------------");
		
	}

	public Grafo getGrafo() {
		return grafo;
	}

	public void setGrafo(Grafo grafo) {
		this.grafo = grafo;
	}
	
	

	

	public List<Node> getCidades() {
		return cidades;
	}

	public void setCidades(List<Node> cidades) {
		this.cidades = cidades;
	}

	public static void main(String[] args) {
		List<Node> nosOrdenados = new ArrayList<Node>();
		MapaBase base = new MapaBase();
		base.importaMapa();
		base.setVisible(true);
		for (Node n : base.getGrafo().getNodes()) {
			nosOrdenados.add(n);
			System.out.println("Ordem " + n.getNome() + " custo " + n.getCusto());
		}
		System.out.println("--------------------------------------------------");

		Collections.sort(nosOrdenados);

		for (Node n : nosOrdenados) {
			if (n.getPai() != null) {
				System.out.println("Ordem " + n.getNome() + " custo " + n.getPai().getNome());
			}
		}
		System.exit(0);

	}

}
