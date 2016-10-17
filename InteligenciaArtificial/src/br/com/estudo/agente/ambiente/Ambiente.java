package br.com.estudo.agente.ambiente;

import br.com.estudo.agente.estado.Estado;

public class Ambiente {
		private String [][] ambiente;

		public Ambiente(int tamanho) {
			ambiente = new  String [tamanho][tamanho];	
			
			definirEstadoInicial();
		}
		
		private void definirEstadoInicial() {
			for(int x=0; x<ambiente.length;x++){
				for(int y=0; y<ambiente.length;y++){
					ambiente[x][y]=Estado.SUJO.toString();
				}
			}
		}

		public void mostraAmbiente(){
			for(int x=0; x<ambiente.length;x++){
				for(int y=0; y<ambiente.length;y++){
					System.out.print("| "+ambiente[x][y]+" |");
				}
				System.out.println("");
			}
		}
		
		
}
