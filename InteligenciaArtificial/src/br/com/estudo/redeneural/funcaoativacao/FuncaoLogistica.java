package br.com.estudo.redeneural.funcaoativacao;

import br.com.estudo.redeneural.exception.NeuralException;

public class FuncaoLogistica implements FuncaoAtivacao {

	
	@Override
	public double funcaoAtivacao(double n) {
			return 1/(1+Math.exp(-n));
	}

	@Override
	public double funcaoDerivada(double n) {
		return 1*Math.pow(n, 2)/2;
	}

	@Override
	public double[] funcaoAtivacao(double[] oSums) {
		throw new NeuralException("Funcao nao implementada");
	}

}
