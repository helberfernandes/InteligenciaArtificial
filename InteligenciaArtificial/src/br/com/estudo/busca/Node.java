package br.com.estudo.busca;

public class Node implements Comparable<Node>{
	protected static final int BASICMOVEMENTCOST = 1;
	private String nome;
	private int posicaoX;
	private int posicaoY;
	private boolean visitado;
	private boolean explorado;
	private boolean inicioObjetivo;
	private int custo;
	private Node pai;
	private boolean diagonally;
	
	  /** Custo para alcancar o no. */
    private int gCusto;

    /** estimated costs to get from this AbstractNode to end AbstractNode. */
    private int hCusto;
	

	public Node(String nome, int posicaoX, int posicaoY, int custo) {
		super();
		this.nome = nome;
		this.posicaoX = posicaoX;
		this.posicaoY = posicaoY;
		this.custo = custo;
	}

	public Node(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getPosicaoX() {
		return posicaoX;
	}

	public void setPosicaoX(int posicaoX) {
		this.posicaoX = posicaoX;
	}

	public int getPosicaoY() {
		return posicaoY;
	}

	public void setPosicaoY(int posicaoY) {
		this.posicaoY = posicaoY;
	}

	public boolean isVisitado() {
		return visitado;
	}

	public void setVisitado(boolean visitado) {
		this.visitado = visitado;
	}

	
	public int getCusto() {
		return custo+calculaCusto();
	}
	/**
	 * Obtendo o custo dos pais
	 * @return
	 */
	private int calculaCusto() {
		Node no = this;
		int total=0;
		
		while (no.getPai() != null) {
			no = no.getPai();
			total+=no.getCusto();
		}
		return total;
	}

	public void setCusto(int custo) {
		this.custo = custo;
	}
	
	

	

	public Node getPai() {
		return pai;
	}

	public void setPai(Node pai) {
		this.pai = pai;
	}

	@Override
	public String toString() {
		return "Node [nome=" + nome + "]";
	}
	
	
	
	  public boolean isExplorado() {
		return explorado;
	}

	public void setExplorado(boolean explorado) {
		this.explorado = explorado;
	}

     
     

	public int getgCusto() {
		return gCusto;
	}

	public void setgCusto(int gCusto) {
		this.gCusto = gCusto;
	}

	public int gethCusto() {
		return hCusto;
	}

	public void sethCusto(int hCusto) {
		this.hCusto = hCusto;
	}
	
	

	public boolean isInicioObjetivo() {
		return inicioObjetivo;
	}

	public void setInicioObjetivo(boolean inicioObjetivo) {
		this.inicioObjetivo = inicioObjetivo;
	}
		
	
	
	@Override
	public int compareTo(Node node) {
		if (this.getCusto()< node.getCusto()) {
            return -1;
        }
        if (this.getCusto()> node.getCusto()) {
            return 1;
        }
        return 0;
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
		Node other = (Node) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	
	
}
