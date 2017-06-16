package br.com.estudo.redeneural.base;

import java.util.Arrays;
import java.util.Random;

import br.com.estudo.redeneural.funcaoaprendizado.Treino;
import br.com.estudo.redeneural.funcaoativacao.FuncaoAtivacao;
import br.com.estudo.redeneural.funcaoativacao.FuncaoSigmoid;

/**
 * Um neuronio recebe um conjunto de entradas e responde a estimolos
 * 
 * @author Helber Fernandes
 *
 */
public class Neuronio {
	private double[] x;// entradas
	private double[] w;// pesos
	private int numEntradas;
	private double bias = 0.35;
	private double saida = 0;
	private FuncaoAtivacao funcaoAtivacao;
	private Treino treino;
	/*
	 * soma o erro da camada proxima multiplicado pelo peso associado a saida do neuronio.
	 * exmplo: neuronio A sua saida seria a entrada dos neuronios B e C, logo cada um dos neuronios 
	 * asociaria um peso a saida de A, sendo assim a soma seria o erro de B + pesso que B atribui a saida de A
	 * + erro de C + pesso que C atribui a saida de A
	 */
	private double soma;

	/**
	 * Instancia o enuronio informando qual funcao de ativacao deseja utilizar
	 * 
	 * @param numEntradas,
	 *            total de entradas para o neuronio
	 */
	public Neuronio(int numEntradas, Treino treino, FuncaoAtivacao funcaoAtivacao) {
		this.numEntradas = numEntradas;
		x = new double[numEntradas];
		w = new double[numEntradas];
		this.funcaoAtivacao = funcaoAtivacao;
		this.treino = treino;
		this.treino.setFuncaoAtivacao(this.funcaoAtivacao);// passa a funcao de
															// ativacao para
															// fazer o calculo
															// do erro com a
															// derivada
		inicializaPesos();
	}

	/**
	 * Por padrao cada neuronio recebe a funcao sigmoid
	 * 
	 * @param numEntradas,total
	 *            de entradas para o neuronio
	 */
	public Neuronio(int numEntradas, Treino treino) {
		this(numEntradas, treino, new FuncaoSigmoid());
	}
	
	public Neuronio(int numEntradas) {
		this(numEntradas, null, new FuncaoSigmoid());
	}
	
	
	public Neuronio(int numEntradas, FuncaoAtivacao funcaoAtivacao) {
		super();
		this.numEntradas = numEntradas;
		this.funcaoAtivacao = funcaoAtivacao;
		x = new double[numEntradas];
		w = new double[numEntradas];
		
		inicializaPesos();
	}

	private void inicializaPesos() {
		Random rn = new Random();
		for (int i = 0; i < numEntradas; i++) {
			w[i] =0;//rn.nextInt(10-2+1)+2
		}
	}
	
	
	/**
	 * Funcao que responsavel por classificar determinado problema
	 * 
	 * @param x
	 *            Percepcoes do agente enviadas para a rede neural
	 * @param usarFuncao
	 *            para este probleam especifico, a camada de saida usa uma
	 *            funcao que e ativada pela soma de todas as camadas de saida
	 *            para este caso passar false.
	 * @return resultado do processamenteo do neuronio
	 */
	public double classificar(double[] x) {
		this.x=x;
		
		double net = 0.0;
		for (int i = 0; i < numEntradas; i++) {
			net += this.x[i] * w[i];
		}
		net += bias;
		return saida = this.funcaoAtivacao.funcaoAtivacao(net);
	}

	/**
	 * 
	 * @return pessos do neuronio
	 */
	public double[] getW() {
		return w;
	}

	public void setBias(double bias) {
		this.bias = bias;
	}

	/**
	 * Para a ativação da camada de saída, se sua rede neural está executando a
	 * classificação onde a variável dependente a ser predita tem três ou mais
	 * valores (por exemplo, predizer a inclinação política de uma pessoa que
	 * pode ser "liberal", "moderada" ou "conservadora"), Softmax ativação é a
	 * melhor escolha.
	 */
	public static double[] funcaoDeAtivacaoSoftmax(double[] oSums) {
		// Does all output nodes at once.
		// Determine max oSum.
		double max = oSums[0];
		for (int i = 0; i < oSums.length; ++i)
			if (oSums[i] > max)
				max = oSums[i];

		// Determine scaling factor -- sum of exp(each val - max).
		double scale = 0.0;
		for (int i = 0; i < oSums.length; ++i)
			scale += Math.exp(oSums[i] - max);

		double[] result = new double[oSums.length];
		for (int i = 0; i < oSums.length; ++i)
			result[i] = Math.exp(oSums[i] - max) / scale;

		return result; // Now scaled so that xi sums to 1.0.
	}

	public double getSaida() {
		return saida;
	}

	public void setSaida(double saida) {
		this.saida = saida;
	}

	public void setW(int index, double peso) {
		this.w[index] = peso;
	}

	public void calcError(double desejado) {
		treino.calcError(getSaida(), desejado);
	}

	/**
	 * Atualiza os pesos da camada
	 */
	public void aprender(double taxaAprendizado) {
		for (int j = 0; j < getW().length; ++j) {
			getW()[j] = getW()[j] + taxaAprendizado * x[j] * treino.getErroDelta();
		}
	}

//	/**
//	 * 
//	 * @param delta
//	 *            da camada anterior
//	 * @param peso
//	 *            da camada anterior
//	 */
//	public void calcError() {
//		treino.calcErrorOculto(getSaida(), soma);
//	}
	
	public void updateWheigts(){
		for (int j = 0; j < getW().length; ++j) {
			setW(j, treino.updateWheigts(j, getW()[j]));
		}
	}
	
	

	
	public void soma(double erro, double peso) {
		soma+=erro*peso;
//		System.out.println("Soma "+soma);
	}

	public FuncaoAtivacao getFuncaoAtivacao() {
		return funcaoAtivacao;
	}

	public void setFuncaoAtivacao(FuncaoAtivacao funcaoAtivacao) {
		this.funcaoAtivacao = funcaoAtivacao;
	}

	public Treino getTreino() {
		return treino;
	}

	public void setTreino(Treino treino) {
		this.treino = treino;
	}

	
	
	public double[] getX() {
		return x;
	}

	public void setX(double[] x) {
		this.x = x;
	}

	public int getNumEntradas() {
		return numEntradas;
	}

	public void setNumEntradas(int numEntradas) {
		this.numEntradas = numEntradas;
	}

	public double getBias() {
		return bias;
	}
	
	

	public void setW(double[] w) {
		this.w = w;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(bias);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((funcaoAtivacao == null) ? 0 : funcaoAtivacao.hashCode());
		result = prime * result + numEntradas;
		temp = Double.doubleToLongBits(saida);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((treino == null) ? 0 : treino.hashCode());
		result = prime * result + Arrays.hashCode(w);
		result = prime * result + Arrays.hashCode(x);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Neuronio other = (Neuronio) obj;
		if (Double.doubleToLongBits(bias) != Double.doubleToLongBits(other.bias))
			return false;
		if (funcaoAtivacao == null) {
			if (other.funcaoAtivacao != null)
				return false;
		} else if (!funcaoAtivacao.equals(other.funcaoAtivacao))
			return false;
		if (numEntradas != other.numEntradas)
			return false;
		if (Double.doubleToLongBits(saida) != Double.doubleToLongBits(other.saida))
			return false;
		if (treino == null) {
			if (other.treino != null)
				return false;
		} else if (!treino.equals(other.treino))
			return false;
		if (!Arrays.equals(w, other.w))
			return false;
		if (!Arrays.equals(x, other.x))
			return false;
		return true;
	}
	
	

}
