package br.com.estudo.arvorebinaria;

public class Main {

	public static void main(String[] args) {
		
		ArvoreBinaria arvoreBinaria = new ArvoreBinaria();
		arvoreBinaria.addNo(50,"A");
		arvoreBinaria.addNo(25,"B");
		arvoreBinaria.addNo(15,"C");
		arvoreBinaria.addNo(30,"D");
		arvoreBinaria.addNo(75,"E");
		arvoreBinaria.addNo(85,"F");
		arvoreBinaria.preOrderTraverseTree(arvoreBinaria.root);
		
		//System.out.println("Encontrado o "+arvoreBinaria.pesquisaNo(15));

		
		
//		System.out.println("          A");
//		System.out.println("        /  \\");
//		System.out.println("       /    \\");		
//		System.out.println("      B     C");
//		System.out.println("     /  \\");
//		System.out.println("    /    \\");		
//		System.out.println("   B     C");
//		System.out.println(" /  \\");
//		System.out.println("/    \\");		
//		System.out.println("B     C");
		
		
		
//		System.out.println("   B");
//		System.out.println(" /  \\");
//		System.out.println("/    \\");		
//		System.out.println("B     C");
//		
//		System.out.println("      B");
//		System.out.println("    /  \\");
//		System.out.println("   /    \\");		
//		System.out.println("   B     C");		
//		System.out.println(" /  \\");
//		System.out.println("/    \\");		
//		System.out.println("B     C");
//		
//		System.out.println("         B");
//		System.out.println("       /  \\");
//		System.out.println("      /    \\");		
//		System.out.println("      B     C");		
//		System.out.println("    /  \\");
//		System.out.println("   /    \\");		
//		System.out.println("   B     C");
//		System.out.println(" /  \\");
//		System.out.println("/    \\");		
//		System.out.println("B     C");
		
		montaArvore(arvoreBinaria);
	}

	private static void montaArvore(ArvoreBinaria arvoreBinaria) {
		String [] s = {"A","B","C","D","E","F"}; 
		
		
		
//		for(int i=0;i<4;i++){			
//			
//			System.out.print("   ");
//		}
//		
	}

}
