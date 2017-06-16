package br.com.estudo.redeneural.funcaoativacao;

public class Softmax implements FuncaoAtivacao{
	/**
	 * Para a ativa��o da camada de sa�da, se sua rede neural est� executando a
	 * classifica��o onde a vari�vel dependente a ser predita tem tr�s ou mais
	 * valores (por exemplo, predizer a inclina��o pol�tica de uma pessoa que
	 * pode ser "liberal", "moderada" ou "conservadora"), Softmax ativa��o � a
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
