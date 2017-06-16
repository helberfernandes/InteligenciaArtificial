package br.com.estudo.redeneural.funcaoativacao;

public interface FuncaoAtivacao {
	public double funcaoAtivacao(double n);
	public double funcaoDerivada(double n);
	
	public abstract double[] funcaoAtivacao(double[] oSums);
}