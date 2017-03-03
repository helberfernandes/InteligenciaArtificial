package br.com.estudo.redeneural.perceptron;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class RedeNeuralAPP extends JFrame {
	// Define constants
	public static final int CANVAS_WIDTH = 640;
	public static final int CANVAS_HEIGHT = 480;

	// Declare an instance of the drawing canvas,
	// which is an inner class called DrawCanvas extending javax.swing.JPanel.
	private DrawCanvas canvas;

	public RedeNeuralAPP() throws HeadlessException {
		canvas = new DrawCanvas(2,4,2, new double [2],new double [8],new double [2]); // Construct the drawing canvas
		canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));

		// Set the Drawing JPanel as the JFrame's content-pane
		Container cp = getContentPane();
		cp.add(canvas);
		// or "setContentPane(canvas);"

		setDefaultCloseOperation(EXIT_ON_CLOSE); // Handle the CLOSE button
		pack(); // Either pack() the components; or setSize()
		setTitle("......"); // "super" JFrame sets the title
		setVisible(true); // "super" JFrame show
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new RedeNeuralAPP(); // Let the constructor do the job
			}
		});

	}

	

}
