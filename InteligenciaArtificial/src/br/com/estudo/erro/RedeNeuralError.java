
/**
 * Introduction to Neural Networks with Java, 2nd Edition
 * Copyright 2008 by Heaton Research, Inc. 
 * http://www.heatonresearch.com/books/java-neural-2/
 * 
 * ISBN13: 978-1-60439-008-7  	 
 * ISBN:   1-60439-008-5
 *   
 * This class is released under the:
 * GNU Lesser General Public License (LGPL)
 * http://www.gnu.org/copyleft/lesser.html
 */
package br.com.estudo.erro;


public class RedeNeuralError extends RuntimeException {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 689652540497618994L;


	public RedeNeuralError(final String msg) {
		super(msg);
	}

	
	public RedeNeuralError(final Throwable t) {
		super(t);
	}
}
