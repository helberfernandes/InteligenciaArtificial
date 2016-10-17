package br.com.estudo.busca.map;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import br.com.estudo.busca.Node;

public class MapBaseApp extends MapaBase {
	private Canvas canvas;
	private List<Node> add = new ArrayList<Node>();// armazena os nos ja
													// adicionados, para eviar
													// estourod e pilha

	public MapBaseApp() throws HeadlessException {

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		canvas = new Canvas();
		add("Center", canvas);
		pack();
	}

	public static void main(String[] args) {

		MapBaseApp m = new MapBaseApp();
		m.setVisible(true);
	}
	
	

	public Canvas getCanvas() {
		return canvas;
	}

	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}



	public class Canvas extends JPanel {
		private Graphics g;
		private List<Node> explorado = new ArrayList<Node>();
		
		public Canvas() {
			setSize(getPreferredSize());
			iniciaJogo();
		}

		public Dimension getPreferredSize() {
			return new Dimension(800, 600);
		}

		@Override
		public void paint(Graphics g) {
			
			for(Node node :explorado){
				if(add.contains(node)){
					node.setExplorado(true);
				}
			}
				Node n = grafo.getRoot();
				
				
				

				mostraAdjacente(g, n);
		
			
			
		}

		
		
		private void mostraAdjacente(Graphics g, Node n) {
		
//			if(explorado.contains(n)){
//				g.setColor(Color.RED);
//				System.out.println(n.getNome());
//			}else{
//				g.setColor(Color.BLACK);
//			}
			
		
			for (Node u : grafo.adj(n)) {
				
				if(explorado.contains(u)){
					g.setColor(Color.RED);					
				}else{
					g.setColor(Color.BLACK);
				}
				
				g.fillOval(u.getPosicaoX(), u.getPosicaoY(), 10, 10);
				g.drawString(u.getNome(), u.getPosicaoX() + 20, u.getPosicaoY() + 10);
				g.drawLine(n.getPosicaoX() + 5, n.getPosicaoY() + 5, u.getPosicaoX() + 5, u.getPosicaoY() + 5);
				if(!n.isInicioObjetivo()){
					g.drawString(String.valueOf(u.getCusto()), u.getPosicaoX() - 40, u.getPosicaoY() + 20);
				}
				
				if (!add.contains(u)) {
					add.add(u);
					mostraAdjacente(g, u);
				}
			}

		}

		public List<Node> getExplorado() {
			return explorado;
		}

		public void setExplorado(List<Node> explorado) {
			this.explorado = explorado;
		}

		public Graphics getG() {
			return g;
		}

		public void setG(Graphics g) {
			this.g = g;
		}
		
		

	}

}
