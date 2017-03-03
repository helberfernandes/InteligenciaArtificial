package br.com.estudo.redeneural.funcaoativacao;

import br.com.estudo.redeneural.exception.NeuralException;

public class FuncaoLinear implements FuncaoAtivacao {

	@Override
	public double funcaoAtivacao(double n) {
		return n;
	}

	@Override
	public double funcaoDerivada(double n) {
		throw new NeuralException("Nao existe derivada");
	}

}
