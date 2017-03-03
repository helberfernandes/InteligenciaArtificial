package br.com.estudo.redeneural.funcaoaprendizado;

import br.com.estudo.redeneural.feedforward.teste.Camada;
import br.com.estudo.redeneural.funcaoativacao.FuncaoAtivacao;

public interface Treino {
	public void setFuncaoAtivacao(FuncaoAtivacao funcaoAtivacao);
	public void calcError(double yS,double desejado);
	public void calcErrorOculto(double yS,double soma );
	public double getErroDelta();
	
}
