package br.com.estudo.busca.map;

import java.awt.BorderLayout;
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
import br.com.estudo.util.EstudoUtil;

public class MapBaseApp extends MapaBase {
	private Canvas canvas;
	private Queue<Node> fronteira = new LinkedList<Node>();
	
	
	
	public MapBaseApp() throws HeadlessException {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setExtendedState(JFrame.MAXIMIZED_BOTH);
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
		private List<Node> cidades = new ArrayList<Node>();

		public Canvas() {
			setSize(getPreferredSize());
			importaMapa();
		}

		public Dimension getPreferredSize() {
			return new Dimension(800, 600);
		}

		@Override
		public void paint(Graphics g) {
			Node n = grafo.getRoot();
			bfs(n, g);
		}

		/**
		 * Busca em largura para montar o mapa
		 * 
		 * @param node
		 */
		public void bfs(Node node, Graphics g) {
			node.setVisitado(true);
			fronteira.offer(node);

			node.setPosicaoX(100);
			node.setPosicaoY(400);

			if (cidades.contains(node)) {
				g.setColor(Color.RED);
			} else {
				g.setColor(Color.BLACK);
			}
			
			g.fillOval(node.getPosicaoX(), node.getPosicaoY(), 10, 10);
			g.drawString(node.getNome(), node.getPosicaoX() - 10, node.getPosicaoY() - 10);

			int px = 80;
			int py = 200;
			int i=0;
			while (!fronteira.isEmpty()) {
				// System.out.println("Fronteira "+fronteira);
				Node estado = fronteira.poll();// estado atual
				explorado.add(estado);
				py+=20;
				px+=5;
				// obtem todos os nos adjacentes do no atualmente explorado
				List<Node> adj = grafo.adj(estado, Grafo.ORDER_NAME);
				i=0;
				for (Node u : adj) {
					
					if (!fronteira.contains(u) && !explorado.contains(u)) {
						
					
						if(i%2==0){
							u.setPosicaoX(estado.getPosicaoX() +px);
							u.setPosicaoY(estado.getPosicaoY()+(py*i));							
						}else{
							u.setPosicaoX(estado.getPosicaoX() +px-30);
							u.setPosicaoY(estado.getPosicaoY() +(-py*i)+50);
						}
						
						py+=-30;
						px+=2;
						i++;
						
						
						

						if (cidades.contains(u)) {
							g.setColor(Color.RED);
						} else {
							g.setColor(Color.BLACK);
						}

						g.fillOval(u.getPosicaoX(), u.getPosicaoY(), 10, 10);
						g.drawString(u.getNome(), u.getPosicaoX() - 10, u.getPosicaoY() );
					

						fronteira.offer(u);
						
					}
					if (cidades.contains(u)) {
						g.setColor(Color.RED);
					} else {
						g.setColor(Color.BLACK);
					}
					g.drawLine(estado.getPosicaoX() + 5, estado.getPosicaoY() + 5, u.getPosicaoX() + 5,
							u.getPosicaoY() + 5);
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

		public List<Node> getCidades() {
			return cidades;
		}

		public void setCidades(List<Node> cidades) {
			this.cidades = cidades;
		}
		
		

	}

}
