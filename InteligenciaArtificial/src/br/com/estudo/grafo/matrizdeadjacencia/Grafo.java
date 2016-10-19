package br.com.estudo.grafo.matrizdeadjacencia;

public class Grafo {
	public static class Aresta {
		private int v1, v2, peso;

		public Aresta(int v1, int v2, int peso) {
			super();
			this.v1 = v1;
			this.v2 = v2;
			this.peso = peso;
		}

		public int getV1() {
			return v1;
		}

		public int getV2() {
			return v2;
		}

		public int getPeso() {
			return peso;
		}
		
	}
	
	private  int  mat[][]; //pesos
	private int numVertices;
	private int pos[];// posicao atual ao se percorrer os adjs de um vertice v
	
	
	
			
	public Grafo(int numVertices) {
		this.mat = new int [numVertices][numVertices];
		this.pos = new int [numVertices];
		this.numVertices=numVertices;
		for(int i =0; i<this.numVertices; i++){
			for(int j= 0;j<this.numVertices;j++){
				this.mat[i][j]=0;
				this.pos[i]=-1;
			}
		}
	}
	
	public  void insereAresta(int v1,int v2, int peso){
		this.mat[v1][v2]=peso;
	}

	public boolean existeArea(int v1,int v2){
		return (this.mat[v1][v2]>0);
	}
	
	
	public boolean listAdjVazia(int v){
		for(int i =0; i<this.numVertices; i++)
			if(this.mat[v][i]>0)return false;
		return true;
		
	}

	
	public Aresta primeiroListaAdj(int v){
		/*
		 * Retorna a primeira aresta que o vertice v participa ou
		 * null se a lista de adjacencia de v for vazia
		 */
		this.pos[v]=-1;
		return this.proxAdj(v);
	}

	private Aresta proxAdj(int v) {
		/*
		 * Retorna a proxima aresta que o vertice participa ou
		 * null se a lista de adjacencia de v estiver no fim
		 */
		this.pos[v]++;
		
		while((this.pos[v]< this.numVertices) &&
				(this.mat[v][this.pos[v]]==0)){
			this.pos[v]++;
		}
		
		if(this.pos[v]==this.numVertices) return null;
		else return new Aresta(v,this.pos[v],this.mat[v][this.pos[v]]);
	}

	public Aresta retiraAresta(int v1, int v2){
		if(this.mat[v1][v2]==0) return null;//aresta nao existe
		else{
			Aresta aresta = new Aresta(v1, v2, this.mat[v1][v2]);
			this.mat[v1][v2]=0;
			return aresta;
		}
	}
	
	public void imprime(){
		System.out.print(" ");
		for(int i =0;i<this.numVertices;i++)
			System.out.print(i+" ");
		System.out.println();
		for(int i =0;i<this.numVertices;i++){
			System.out.print(i+" ");
			for(int j =0;j<this.numVertices;j++){
				System.out.print(this.mat[i][j]+" ");
				
			}
			System.out.println();
		}	
	}
	public int numVertices(){
		return this.numVertices;
	}
	
	
	public static void main(String[] args) {
		Grafo grafo = new Grafo(10);
		grafo.insereAresta(1, 2, 1);
		grafo.insereAresta(3, 4, 1);
		grafo.insereAresta(5, 6, 1);
		grafo.insereAresta(7, 8, 1);

		grafo.imprime();

	}

}
