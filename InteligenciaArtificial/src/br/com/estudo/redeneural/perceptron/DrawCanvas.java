package br.com.estudo.redeneural.perceptron;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class DrawCanvas extends JPanel {
	private int numEntradas;
	private int numNeuroNiosCamadaOculta;
	private int numNeuroNiosCamadaSaida;

	private int[] positionEntrada;
	private int[] positionOculto;
	private int[] positionSaida;
	
	
	private double[] x;
	private double[] w;
	private double[] o;



	public DrawCanvas(int numEntradas, int numNeuroNiosCamadaOculta, int numNeuroNiosCamadaSaida, double[] x,
			double[] w, double[] o) {
		super();
		this.numEntradas = numEntradas;
		this.numNeuroNiosCamadaOculta = numNeuroNiosCamadaOculta;
		this.numNeuroNiosCamadaSaida = numNeuroNiosCamadaSaida;
		this.x = x;
		this.w = w;
		this.o = o;
		positionEntrada = new int[numEntradas * numEntradas];
		positionOculto = new int[numNeuroNiosCamadaOculta * numNeuroNiosCamadaOculta];
		positionSaida = new int[numNeuroNiosCamadaSaida * numNeuroNiosCamadaSaida];
	}



	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g); // paint parent's background
		setBackground(Color.WHITE); // set background color for this JPanel

		// Your custom painting codes. For example,
		// Drawing primitive shapes
		g.setColor(Color.BLACK); // set the drawing color

		System.out.println("Tamanho " + ((g.getClipBounds().getHeight() / 2) - 50));
		int p = 0;
		int p2 = 0;
		for (int i = 0; i < numEntradas; i++) {

			positionEntrada[p] = 100;
			p++;
			positionEntrada[p] = 150 + (i * 100);
			p++;
			
		    
			g.fillRect(positionEntrada[p2], positionEntrada[++p2], 20, 30);
			setBackground(Color.WHITE); 
			g.drawString(String.valueOf(x[i]), positionEntrada[p2-1],positionEntrada[p2]);
			//g.setColor(Color.BLACK);
			p2++;
		}

		g.setColor(Color.RED); // set the drawing color
		p = 0;
		p2 = 0;
		for (int x = 0; x < numNeuroNiosCamadaOculta; x++) {
			positionOculto[p] = 250;
			p++;
			positionOculto[p] = 60 + (x * 100);
			p++;

			g.fillOval(positionOculto[p2], positionOculto[++p2], 20, 30);
			g.drawString(String.valueOf(w[x]), positionOculto[p2-1],positionOculto[p2]);
			p2++;
		}

		g.setColor(Color.BLUE); // set the drawing color
		p = 0;
		p2 = 0;
		for (int x = 0; x < numNeuroNiosCamadaSaida; x++) {

			positionSaida[p] = 420;
			p++;
			positionSaida[p] = 160 + (x * 100);
			p++;

			g.fillOval(positionSaida[p2], positionSaida[++p2], 20, 30);
			p2++;
		}
		p = 0;
		p2 = 0;
		for (int i = 0; i < numEntradas; i++) {
			for (int x = 0; x < numNeuroNiosCamadaOculta; x++) {
				g.drawLine(positionEntrada[p], positionEntrada[(p + 1)] + 20, positionOculto[p2],
						positionOculto[++p2] + 20);
				g.drawString(String.valueOf(w[x]), (positionEntrada[p] ),
						positionEntrada[(p)]+100
						);
				
				p2++;
			}
			p += 2;
			p2 = 0;
		}

		p = 0;
		p2 = 0;
		for (int i = 0; i < numNeuroNiosCamadaOculta; i++) {
			for (int x = 0; x < numNeuroNiosCamadaSaida; x++) {
				g.drawLine(positionOculto[p], positionOculto[(p + 1)] + 20, positionSaida[p2],
						positionSaida[++p2] + 20);
				p2++;
			}
			p += 2;
			p2 = 0;
		}

	}
	
	

	public void setX(double[] x) {
		this.x = x;
	}

	public double[] getW() {
		return w;
	}

	public void setW(double[] w) {
		this.w = w;
	}

	public double[] getO() {
		return o;
	}

	public void setO(double[] o) {
		this.o = o;
	}
	
	
}
