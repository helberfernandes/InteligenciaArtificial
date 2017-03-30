package br.com.estudo.agente.ambiente;

import br.com.estudo.agente.agente.Agente;
import br.com.estudo.agente.estado.Estado;

public class Ambiente {
		private String [][] ambiente;
		private Agente agente;
		public Ambiente(int tamanho, Agente agente) {
			ambiente = new  String [tamanho][tamanho];	
			
			definirEstadoInicial();
			this.agente=agente; 
			addAgente(agente, 0, 0 );
		}
		
		private void definirEstadoInicial() {
			for(int x=0; x<ambiente.length;x++){
				for(int y=0; y<ambiente.length;y++){
					ambiente[x][y]=Estado.SUJO.toString();
				}
			}
		}

		public void mostraAmbiente(){
			System.out.println("");
			System.out.println("");
			System.out.println("");
			for(int y=0; y<ambiente.length;y++){
				
				for(int x=0; x<ambiente.length;x++){
					
					if(agente.getX()==x && agente.getY()==y){
						printPos(agente.toString());
					}else{
						printPos(ambiente[y][x]);
					}
				}
				System.out.println("");
			}
		}
		
		private void printPos(String string) {
		
		
			while(string.length()<8){
				if(string.length()%2==0){
					string=string+" ";
				}else{
					string=" "+string;
				}
			}
			System.out.print("|");
			System.out.print(string);
			System.out.print("|");
		}

		public void addAgente(Agente agente, int y, int x ){
			agente.setX(x);
			agente.setY(y);
		}
		
		

		public String[][] getAmbiente() {
			return ambiente;
		}
}
