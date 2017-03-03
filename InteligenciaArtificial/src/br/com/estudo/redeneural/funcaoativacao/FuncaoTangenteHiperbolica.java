package br.com.estudo.redeneural.funcaoativacao;

public class FuncaoTangenteHiperbolica implements FuncaoAtivacao {

	/**
	 * Embora haja excecoes, em geral, a funcaoo tangente hiperbolica é a melhor
	 * opção para a ativação da camada oculta.
	 */
	@Override
	public double funcaoAtivacao(double n) {
//		if (n < -20.0)
//			return -1.0;
//		else if (n > 20.0)
//			return 1.0;
//		else
			return Math.tanh(n);
	}

	@Override
	public double funcaoDerivada(double n) {
		return 1-Math.pow(n, 2);
	}

}
