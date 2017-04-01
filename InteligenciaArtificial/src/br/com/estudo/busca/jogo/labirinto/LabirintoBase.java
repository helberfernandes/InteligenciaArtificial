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

public class LabirintoBase extends JFrame {
	private Canvas canvas;
	protected Grafo grafo = new Grafo(9);
	private List<Node> cidades = new ArrayList<Node>();
	private Queue<Node> fronteira = new LinkedList<Node>();
	private List<Node> explorado = new ArrayList<Node>();
	
	
	
	

	public LabirintoBase() throws HeadlessException {
		super();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		importaMapa();
		canvas = new Canvas();
		add("Center", canvas);
	}

	public static void main(String[] args) {
		LabirintoBase labirinto = new LabirintoBase();
		labirinto.setVisible(true);

	}

	/**
	 * cria o estado inicial do jogo 458 016 723
	 */
	public void importaMapa() {

		cidades = MapImport.getMap();

		for (Node c : cidades) {
			/*
			 * primeira cidade importada, para que sirva como inicio da montagem
			 * do mapa pois apartir dela obtenho todos os adjacentes.
			 */
			if (grafo.getRoot() == null) {
				grafo.setRoot(c);
			}
			for (Node n : c.getFilhos()) {
				grafo.adicionaAresta(c, n);
			}

		}

		Node n = grafo.getRoot();
		

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
	
	

	public Canvas getCanvas() {
		return canvas;
	}

	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}



	public class Canvas extends JPanel {
		private Graphics g;
		private List<Node> caminho = new ArrayList<Node>();

		public Canvas() {

		}

		public Dimension getPreferredSize() {
			return new Dimension(800, 600);
		}

		@Override
		public void paint(Graphics g) {
			g.fillRect(10, 10, 400, 10);

			g.fillRect(10, 10, 10, 200);
			g.fillRect(50, 10, 10, 200);

			g.fillRect(10, 250, 10, 200);
			g.fillRect(50, 250, 10, 200);

			g.fillRect(100, 150, 10, 300);
			g.fillRect(100, 150, 10, 300);
			g.fillRect(100, 150, 10, 300);
			g.fillRect(100, 150, 10, 300);

			g.fillRect(50, 80, 100, 10);

			g.fillRect(100, 300, 200, 10);

			g.fillRect(200, 10, 10, 200);

			g.fillRect(300, 100, 10, 200);

			g.fillRect(400, 10, 10, 200);
			g.fillRect(400, 250, 10, 200);
			g.fillRect(10, 450, 400, 10);

			Node n = grafo.getRoot();

			bfs(n, g);

		}

		/**
		 * Busca em largura para montar o mapa
		 * 
		 * @param node
		 */
		public void bfs(Node node, Graphics g) {

			fronteira.offer(node);
			

			if (caminho.contains(node)) {
				g.setColor(Color.RED);
			} else {
				g.setColor(Color.BLACK);
			}

			g.fillOval(node.getPosicaoX(), node.getPosicaoY(), 10, 10);
			g.drawString(node.getNome(), node.getPosicaoX() - 10, node.getPosicaoY() - 10);

			int i = 0;
			while (!fronteira.isEmpty()) {
				// System.out.println("Fronteira "+fronteira);
				Node estado = fronteira.poll();// estado atual
				explorado.add(estado);

				// obtem todos os nos adjacentes do no atualmente explorado
				List<Node> adj = grafo.adj(estado, Grafo.ORDER_NAME);
				i = 0;
				for (Node u : adj) {

					if (!fronteira.contains(u) && !explorado.contains(u)) {

						if (caminho.contains(u)) {
							g.setColor(Color.RED);
						} else {
							g.setColor(Color.BLACK);
						}

						g.fillOval(u.getPosicaoX(), u.getPosicaoY(), 10, 10);
						g.drawString(u.getNome(), u.getPosicaoX() - 10, u.getPosicaoY());

						fronteira.offer(u);

					}
					if (caminho.contains(u)) {
						g.setColor(Color.RED);
					} else {
						g.setColor(Color.BLACK);
					}
					g.drawLine(estado.getPosicaoX() + 5, estado.getPosicaoY() + 5, u.getPosicaoX() + 5,
							u.getPosicaoY() + 5);
				}

			}
			
		}

		public Graphics getG() {
			return g;
		}

		public void setG(Graphics g) {
			this.g = g;
		}

		public List<Node> getCaminho() {
			return caminho;
		}

		public void setCaminho(List<Node> caminho) {
			this.caminho = caminho;
		}
		
		
		
	}

}
