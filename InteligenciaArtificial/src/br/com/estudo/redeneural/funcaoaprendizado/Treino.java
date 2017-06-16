package br.com.estudo.redeneural.funcaoaprendizado;

import java.util.List;

import br.com.estudo.redeneural.funcaoativacao.FuncaoAtivacao;

public interface Treino {
	public void setFuncaoAtivacao(FuncaoAtivacao funcaoAtivacao);
	/**
	 * funcao do erro quadratico
	 * @param yS - saida do neuronio n
	 * @param desejado
	 * @return
	 */
	public double calcError(double yS,double desejado);
	public double calcErrorOculto(List<Double> pesos, List<Double> delta, double saida);
	public double getErroDelta();
	public double getErro();
	
	public void deltaWheigts(int index,double y);
	public double updateWheigts(int index,double w);
	
}
