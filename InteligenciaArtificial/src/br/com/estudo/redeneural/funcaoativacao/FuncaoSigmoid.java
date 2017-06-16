package br.com.estudo.redeneural.funcaoativacao;

import br.com.estudo.redeneural.exception.NeuralException;

public class FuncaoSigmoid implements FuncaoAtivacao {

	@Override
	public double funcaoAtivacao(double n) {
		return 1.0/(1+Math.exp(-1.0*n));
	}

	@Override
	public double funcaoDerivada(double n) {
		return n*(1.0-n);
	}

	@Override
	public double[] funcaoAtivacao(double[] oSums) {
		throw new NeuralException("Funcao nao implementada");
	}
}
