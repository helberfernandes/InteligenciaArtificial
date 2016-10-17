package br.com.estudo.arvorebinaria;

public class No {
	
	private int key;
	private No noEsquerda;
	private No noDireita;
	private String nome;

	public No() {}

	





	






	

	public No(int key, String nome) {
		super();
		this.key = key;
		this.nome = nome;
	}
















	public No getNoEsquerda() {
		return noEsquerda;
	}

	public void setNoEsquerda(No noEsquerda) {
		this.noEsquerda = noEsquerda;
	}

	public No getNoDireita() {
		return noDireita;
	}

	public void setNoDireita(No noDireita) {
		this.noDireita = noDireita;
	}
	
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}







	public int getKey() {
		return key;
	}
















	public void setKey(int key) {
		this.key = key;
	}
















	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		No other = (No) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}







	@Override
	public String toString() {
		return  nome ;
	}

	

}
