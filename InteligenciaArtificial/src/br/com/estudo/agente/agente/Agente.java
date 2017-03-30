package br.com.estudo.agente.agente;

import br.com.estudo.agente.ambiente.Ambiente;
import br.com.estudo.agente.estado.Estado;

public class Agente {
	private int x;
	private int y;
	private Acao acao = Acao.CIMA;
	// numero de vezes que foi limpo e igual ao numero de salas
	private int memoria = 0;

	enum Acao {
		LIMPAR, DIREITA, ESQUERDA, CIMA, BAIXO, PARAR
	}

	/**
	 * Sensor de percepcoes do agente;
	 * 
	 * @param ambiente
	 */
	public void percepcao(Ambiente ambiente) {

		/*
		 * caso a acao retornada nao seja NADA ou seja: nao tem o que se fazer,
		 * esta tudo limpo
		 */
		Estado estado;
		int memoria = 0;
		while (acao != Acao.PARAR) {

			
			
			estado = getPercepcao(ambiente);

			System.out.println("estado "+estado+" Acao " + memoria + " " + acao + " xy " + y + " px " + x);
			
			if (estado == Estado.SUJO) {
				aspirar(ambiente, Estado.LIMPO);
				memoria++;
			} else if (memoria >= (ambiente.getAmbiente().length * ambiente.getAmbiente().length)) {
				acao = Acao.PARAR;
			}

			
			executaAcao(acao, ambiente, estado);
			ambiente.mostraAmbiente();

		}
	}

	/**
	 * Executa uma acao no ambiente;
	 * 
	 * @param acao
	 *            que deve ser tomada
	 * @param ambiente
	 *            ambiente no qual deva ser executada a acao
	 */
	private void executaAcao(Acao acao, Ambiente ambiente, Estado estado) {

		switch (acao) {
		case CIMA:
			if ((y - 1) < 0 || isAmbienteLimpo(ambiente,y-1,x)) {
				this.acao = Acao.ESQUERDA;	
			
			} else {
				ambiente.addAgente(this, y - 1, x);
			}
			break;

		case BAIXO:
			if ((y + 1) >= ambiente.getAmbiente().length || isAmbienteLimpo(ambiente,y+1,x)) {
				this.acao = Acao.DIREITA;
			} else {
				ambiente.addAgente(this, y + 1, x);
			}
			break;

		case DIREITA:
			if ((x + 1) >= ambiente.getAmbiente().length  || isAmbienteLimpo(ambiente,y,x+1)) {
				this.acao = Acao.CIMA;
			} else {
				ambiente.addAgente(this, y, x + 1);
			}
			break;

		case ESQUERDA:
			if ((x - 1) < 0 || isAmbienteLimpo(ambiente,y,x-1)) {
				this.acao = Acao.BAIXO;
			
			} else {
				ambiente.addAgente(this, y, x - 1);
			}
			break;

		case LIMPAR:
			aspirar(ambiente, Estado.LIMPO);
			// this.acao = Acao.CIMA;
			break;

		default:
			break;
		}

	}

	private boolean isAmbienteLimpo(Ambiente ambiente, int y, int x) {
		if(ambiente.getAmbiente()[y][x].equals(Estado.LIMPO.toString())){
			return true;
		}
		return false;
	}

	public void aspirar(Ambiente ambiente, Estado estado) {
		ambiente.getAmbiente()[y][x] = estado.toString();

	}

	/**
	 * Retorna uma percepcao da posicao atual do agente.
	 * 
	 * @param ambiente,
	 *            local onde se encontra o agente
	 * @return retorna o estado atual do ambiente baseado na percepca do agente
	 */
	public Estado getPercepcao(Ambiente ambiente) {
		if (ambiente.getAmbiente()[y][x] == Estado.SUJO.toString()) {
			return Estado.SUJO;
		} else {
			return Estado.LIMPO;
		}
	}

	@Override
	public String toString() {
		return "Agente";
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
