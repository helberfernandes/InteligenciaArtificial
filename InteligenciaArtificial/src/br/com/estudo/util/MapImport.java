package br.com.estudo.util;

import java.io.File;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import br.com.estudo.busca.Node;
import br.com.estudo.map.util.Cidade;
import br.com.estudo.map.util.Mapa;
/**
 * Esta Classe e responsavel por importar um mapa 
 * para fins de estudo e transformalo em um grafo
 * para posterior busca em grafos
 * 
 * @author Helber
 * 
 */
public class MapImport {
	private static List<Node> cidades = new ArrayList<Node>();
	// aqui fica todas as cidades para que nao se repita em nenhum momento
	private static List<Node> listaTodascidades = new ArrayList<Node>();
	private static Node node;
	private static Node nodeFilho;
	private static Node temp;
	

	public static void main(String[] args) {

		getMap();

	}

	public static List<Node> getMap() {
		XStream stream = new XStream(new StaxDriver());
		stream.alias("mapa", Mapa.class);
		stream.alias("cidade", Cidade.class);
		stream.processAnnotations(Cidade.class);
		stream.processAnnotations(Mapa.class);
		Mapa mapa = (Mapa) stream.fromXML(new File("c:\\java\\mapa_cidades.xml"));

		return iniciaMapa(mapa);
	}

	private static List<Node> iniciaMapa(Mapa mapa) {

		int i = 0;
		for (Cidade c : mapa.getCidades()) {
			node = new Node(c.getNome(), c.getPx(),c.getPy());
			node.sethCusto(c.getHeuristica());
			
			// evitando duplicidade
			if (listaTodascidades.contains(node)) {
				node = listaTodascidades.get(listaTodascidades.indexOf(node));
			}
			
			
			//System.out.println("Node Pai "+node.getNome());
			
			carregaFilhos(c.getCidades(), c,node);

			if (!cidades.contains(node)) {
				if (listaTodascidades.contains(node)) {
					// se contiver na lista de todas as cidades atualiza para um
					// objeto mais completo
					// pois pode ocorrer de ter sido adicionado sem os filhos
					listaTodascidades.add(listaTodascidades.indexOf(node), node);
				} else {
					listaTodascidades.add(node);
				}
				cidades.add(node);
			}
			// else{
			// node = cidades.get(cidades.indexOf(node));
			// }

			
		}

//		for (Node n : cidades) {
//			System.out.println(n.getNome() + " " + n.gethCusto());
//			for (Node b : n.getFilhos()) {
//				System.out.println("  " + b.getNome() + " " + b.gethCusto());
//			}
//			System.out.println();
//			System.out.println();
//
//		}
		return cidades;
	}
	
	private static void carregaFilhos(List<Cidade> cidades, Cidade c, Node node) {
		int i = 0;
		
		for (Cidade v : c.getCidades()) {
			
			//x2=EstudoUtil.randInt(40, 800)+(2*4);
			//y2=EstudoUtil.randInt(40, 800);
			
			
		
			
//				x2 =  (2*40+x2) +node.getPosicaoX();
//				y2 =  node.getPosicaoY();
//				if(x2>400){
//					x2=200;
//					y2+=100;
//				}
				
				//System.out.println("Filho "+v.getNome()+" x = "+x2+" y = "+y2+" xpai ="+node.getPosicaoX());
				
				
			nodeFilho = new Node(v.getNome(), v.getPx(),v.getPy());
			if (listaTodascidades.contains(nodeFilho)) {
				nodeFilho = listaTodascidades.get(listaTodascidades.indexOf(nodeFilho));
			}

			nodeFilho.getCusto().put(node, v.getCusto());
			
			nodeFilho.sethCusto(v.getHeuristica());
			

			if (!listaTodascidades.contains(nodeFilho)) {
				listaTodascidades.add(nodeFilho);
			} else {
				listaTodascidades.add(listaTodascidades.indexOf(nodeFilho), nodeFilho);
			}
			node.getCusto().put(nodeFilho, v.getCusto());
			node.getFilhos().add(nodeFilho);
			
			
			
		}
		
		
//		Collections.sort(c.getCidades(),new NodeComparator());
//		for (Cidade v : c.getCidades()) {
//			System.out.println(v.getNome()+"  "+v.getHeuristica());
//		}
	}

}
