package br.com.estudo.redeneural.funcaoaprendizado;

import java.util.ArrayList;
import java.util.List;

import br.com.estudo.redeneural.funcaoativacao.FuncaoAtivacao;

public class Backpropagation implements Treino {
	private final double taxaAprendizado;
	private double erro;
	private double erroDelta;
	private FuncaoAtivacao funcaoAtivacao;
	private List<Double> pesosDelta = new ArrayList<>();
	List<Double> pesos = new ArrayList<>();
	List<Double> delta = new ArrayList<>();
	
	public Backpropagation(final double taxaAprendizado) {
		this.taxaAprendizado = taxaAprendizado;
	}
	
	/**
	 * o Delta da camada de saida e a diferenca entre o resultado desejado e a
	 * saida do neuronio, muiltiplicado pela derivada da funcao de ativacao
	 * utilizada na camada de saida.
	 */
	@Override
	public double calcError(double yS, double desejado) {
		// calculando o erro
		erro = desejado - yS;				
		return erroDelta = (erro *= funcaoAtivacao.funcaoDerivada(yS));
	}

	@Override
	public double calcErrorOculto(List<Double> pesos, List<Double> delta, double saida) {
		this.pesos.addAll(pesos);
		this.delta.addAll(delta);
		double soma=0.0;
		
		for(Double p: pesos){
			for(Double d: delta){
				soma+=p*d;
			}
		}
		
		return erroDelta =soma* funcaoAtivacao.funcaoDerivada(saida);		
	}
	
	public void deltaWheigts(int index, double y){
		pesosDelta.add(index, taxaAprendizado * y *erroDelta);
	}
	
	public double updateWheigts(int index,double w){
		return pesosDelta.get(index)+w;
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
