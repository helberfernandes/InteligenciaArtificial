package br.com.estudo.agente;

import br.com.estudo.agente.agente.Agente;
import br.com.estudo.agente.ambiente.Ambiente;

public class Principal {

	public static void main(String[] args) {
		Agente agente = new Agente();
		Ambiente ambiente = new Ambiente(10, agente);

		ambiente.mostraAmbiente();
		agente.percepcao(ambiente);
	}
}
