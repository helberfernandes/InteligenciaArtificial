package br.com.estudo.arvorebinaria;

public class ArvoreBinaria {
	 No root;

	public void addNo(int key, String nome){
		No novoNo = new No(key, nome);
		
		if(root == null){
			root= novoNo;
		} else {
			No noFocado = root;
			 No parent;
			 
			 while(true){
				 parent = noFocado;
				 if(key<noFocado.getKey()){
					 noFocado = noFocado.getNoEsquerda();
					  if(noFocado== null){
						  parent.setNoEsquerda(novoNo);
						  return;
					  }
				 } else {
					 noFocado = noFocado.getNoDireita();
					  if(noFocado== null){
						  parent.setNoDireita(novoNo);
						  return;
					  }
				 }
			 }
		}
	}
	
	public void inOrderTraverseTree( No noFoco){
		if(noFoco!=null){
			inOrderTraverseTree( noFoco.getNoEsquerda());
			System.out.println(noFoco);
			inOrderTraverseTree( noFoco.getNoDireita());
		}
	}
	
	
	public void posOrderTraverseTree( No noFoco){
		if(noFoco!=null){
			inOrderTraverseTree( noFoco.getNoEsquerda());			
			inOrderTraverseTree( noFoco.getNoDireita());
			System.out.println(noFoco);
		}
	}
	public void preOrderTraverseTree( No noFoco){
		if(noFoco!=null){
			System.out.println(noFoco);
			inOrderTraverseTree( noFoco.getNoEsquerda());			
			inOrderTraverseTree( noFoco.getNoDireita());
		}
	}
	
	
	
	public No pesquisaNo(int key){
		No noFoco = root;
		while (noFoco.getKey()!=key) {
			
			if(key < noFoco.getKey()){
				noFoco = noFoco.getNoEsquerda();
			} else {
				noFoco = noFoco.getNoDireita();
			}
			if(noFoco == null){
				return null;
			}
		}
		
		return noFoco;
	}
	int profundedade=0;
	public int profundedade(No no){
		profundedade++;
		if(no.getNoEsquerda()!=null){
			profundedade(no.getNoEsquerda());
		}
		if(no.getNoDireita()!=null){
			profundedade(no.getNoEsquerda());
		}
		
		return profundedade;
	}
	
}
