package br.com.estudo.busca;

import br.com.estudo.busca.custouniforme.BuscaEmCustoUniforme;
import br.com.estudo.busca.largura.BuscaEmLargura;
import br.com.estudo.busca.profundidade.BuscaEmProfundidade;

public class BuscaMain {

	public static void main(String[] args) {
		Node inicio = new Node("Montreal", 1, 1);
		Node objetivo = new Node("Duluth", 1, 2);
		
		BuscaEmCustoUniforme custo = new BuscaEmCustoUniforme(objetivo);
		custo.setVisible(true);
		custo.ucs(inicio);
		
		BuscaEmLargura largura = new BuscaEmLargura(objetivo);
		largura.setVisible(true);
		largura.bfs(inicio);
		
		
		BuscaEmProfundidade profundidade = new BuscaEmProfundidade(objetivo);
		profundidade.setVisible(true);
		profundidade.dfs(inicio);
		

	}

}
