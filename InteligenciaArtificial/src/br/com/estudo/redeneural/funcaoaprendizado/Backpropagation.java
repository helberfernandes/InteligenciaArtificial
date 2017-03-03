package br.com.estudo.redeneural.funcaoaprendizado;

import br.com.estudo.redeneural.funcaoativacao.FuncaoAtivacao;

public class Backpropagation implements Treino {

	double erro;
	double erroDelta;
	private FuncaoAtivacao funcaoAtivacao;

	public Backpropagation() {
		super();
	}

	@Override
	public void calcError(double yS, double desejado) {
		// calculando o erro
		erro = desejado - yS;
		// calculando o erro Delta usando a derivada da funcao de ativacao
		erroDelta = funcaoAtivacao.funcaoDerivada(yS) * erro;

	}

	@Override
	public void calcErrorOculto(double yS, double soma) {
		// calculando o erro Delta
		// erroDelta = yS * (1 - yS) * delta * peso;// exemplo de um video no
		// youtube
		
		double derivada = funcaoAtivacao.funcaoDerivada(yS);
		
		erroDelta += derivada * soma;

	}

	public double getErro() {
		return erro;
	}

	public void setErro(double erro) {
		this.erro = erro;
	}

	public double getErroDelta() {
		return erroDelta;
	}

	public void setErroDelta(double erroDelta) {
		this.erroDelta = erroDelta;
	}

	@Override
	public void setFuncaoAtivacao(FuncaoAtivacao funcaoAtivacao) {
		this.funcaoAtivacao = funcaoAtivacao;

	}

}
