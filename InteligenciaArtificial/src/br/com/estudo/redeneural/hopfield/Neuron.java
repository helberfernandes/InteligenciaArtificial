package br.com.estudo.redeneural.hopfield;

public class Neuron {
	/**
	 * * The weights between this neuron and the other neurons on * the layer.
	 */
	public int weightv[];

	/** * Activation results for this neuron. */
	public int activation;

	/**
	 * * The constructor. The weights between this neuron and * every other
	 * neuron(including itself) is passed in as * an array. Usually the weight
	 * between this neuron and * itself is zero. * * @param in The weight
	 * vector.
	 */
	public Neuron(int in[]) {
		weightv = in;
	}

	/**
	 * * This method is called to determine if the neuron would * activate, or
	 * fire. * * @param x Neuron input * @return If the neuron would activate,
	 * or fire
	 */
	public int act(boolean x[]) {
		int i;
		int a = 0;

		for (i = 0; i < x.length; i++)
			if (x[i])
				a += weightv[i];
		return a;
	}

}
