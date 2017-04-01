package br.com.estudo.busca.jogo.labirinto;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import br.com.estudo.busca.Grafo;
import br.com.estudo.busca.Node;
import br.com.estudo.util.MapImport;

public class Labirinto extends LabirintoBase {

	public Labirinto() throws HeadlessException {
		super();
	}

	public static void main(String[] args) {
		Labirinto labirinto = new Labirinto();
		labirinto.setVisible(true);

		Node objetivo = new Node("P", 1, 2);
		Node node4 = new Node("A", 1, 1);

		BuscaEmProfundidade profundedade = new BuscaEmProfundidade(objetivo, labirinto.getCanvas(),
				labirinto.getGrafo());

		profundedade.dfs(node4);

//		BuscaEmLargura largura = new BuscaEmLargura(objetivo, labirinto.getCanvas(), labirinto.getGrafo());
//
//		largura.bfs(node4);
		
		
		
//		BuscaEmCustoUniforme custo = new BuscaEmCustoUniforme(objetivo, labirinto.getCanvas(), labirinto.getGrafo());
//
//		custo.ucs(node4);

		

	}

}
