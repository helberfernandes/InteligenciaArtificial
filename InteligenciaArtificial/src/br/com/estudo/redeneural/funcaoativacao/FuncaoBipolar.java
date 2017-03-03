package br.com.estudo.redeneural.funcaoativacao;

import br.com.estudo.redeneural.exception.NeuralException;

public class FuncaoBipolar implements FuncaoAtivacao {

	@Override
	public double funcaoAtivacao(double n) {
		return (n >= 0.0) ? 1 : -1;
	}

	@Override
	public double funcaoDerivada(double n) {
		return n;
	}

}
