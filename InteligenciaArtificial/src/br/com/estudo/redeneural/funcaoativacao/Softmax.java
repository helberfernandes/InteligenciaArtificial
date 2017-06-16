package br.com.estudo.redeneural.funcaoativacao;

public class Softmax implements FuncaoAtivacao{
	/**
	 * Para a ativação da camada de saída, se sua rede neural está executando a
	 * classificação onde a variável dependente a ser predita tem três ou mais
	 * valores (por exemplo, predizer a inclinação política de uma pessoa que
	 * pode ser "liberal", "moderada" ou "conservadora"), Softmax ativação é a
	 * melhor escolha.
	 */
	@Override
	public double[] funcaoAtivacao(double[] oSums) {
		// Does all output nodes at once.
				// Determine max oSum.
				double max = oSums[0];
				for (int i = 0; i < oSums.length; ++i)
					if (oSums[i] > max)
						max = oSums[i];

				// Determine scaling factor -- sum of exp(each val - max).
				double scale = 0.0;
				for (int i = 0; i < oSums.length; ++i)
					scale += Math.exp(oSums[i] - max);

				double[] result = new double[oSums.length];
				for (int i = 0; i < oSums.length; ++i)
					result[i] = Math.exp(oSums[i] - max) / scale;

				return result; // Now scaled so that xi sums to 1.0
	}

	@Override
	public double funcaoDerivada(double n) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double funcaoAtivacao(double n) {
		// TODO Auto-generated method stub
		return 0;
	}

}
