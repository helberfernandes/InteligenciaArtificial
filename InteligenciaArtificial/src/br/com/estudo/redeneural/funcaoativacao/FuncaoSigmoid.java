package br.com.estudo.redeneural.funcaoativacao;

public class FuncaoSigmoid implements FuncaoAtivacao {

	@Override
	public double funcaoAtivacao(double n) {
		return 1.0/(1+Math.exp(-1.0*n));
	}

	@Override
	public double funcaoDerivada(double n) {
		return n*(1.0-n);
	}

}
