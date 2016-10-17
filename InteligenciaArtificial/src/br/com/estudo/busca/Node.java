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
		return custo;
	}

	public void setCusto(int custo) {
		this.custo = custo;
	}
	
	

	public Node getPrevious() {
		return pai;
	}

	public void setPrevious(Node pai) {
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

	/**
     * calculates - but does not set - g costs.
     * <p>
     * It will assume <code>BASICMOVEMENTCOST</code> as the cost from
     * <code>paiAbstractNode</code> to itself if the movement is not diagonally,
     * otherwise it will assume <code>DIAGONALMOVEMENTCOST</code>.
     * Weather or not it is diagonally is set in the Map class method which
     * finds the adjacent AbstractNodes.
     *
     * @param paiAbstractNode
     * @return gCosts
     */
    public int calculategCosts(Node paiAbstractNode) {
        
            return (paiAbstractNode.getgCusto()
                    + BASICMOVEMENTCOST );
        
    }
	/**
     * returns <code>gCosts</code> + <code>hCosts</code>.
     * <p>
     *
     *
     * @return the fCosts
     */
    public int getfCosts() {
        return gCusto + hCusto;
    }
	
	
	 public void sethCosts(Node endNode) {
         this.sethCusto((absolute(this.getPosicaoX() - endNode.getPosicaoX())
                 + absolute(this.getPosicaoY() - endNode.getPosicaoY()))
                 * BASICMOVEMENTCOST);
     }
	 
	 /**
	     * sets gCosts to <code>gCosts</code> plus <code>movementPanelty</code>
	     * for this AbstractNode.
	     *
	     * @param gCosts the gCosts to set
	     */
	    private void setgCosts(int gCosts) {
	        this.gCusto = gCosts ;
	    }
	 /**
	     * sets gCosts to <code>gCosts</code> plus <code>movementPanelty</code>
	     * for this AbstractNode given the pai AbstractNode as well as the basic cost
	     * from it to this AbstractNode.
	     *
	     * @param paiAbstractNode
	     * @param basicCost
	     */
	    public void setgCosts(Node paiAbstractNode, int basicCost) {
	        setgCosts(paiAbstractNode.getgCusto() + basicCost);
	    }

	    /**
	     * sets gCosts to <code>gCosts</code> plus <code>movementPanelty</code>
	     * for this AbstractNode given the pai AbstractNode.
	     * <p>
	     * It will assume <code>BASICMOVEMENTCOST</code> as the cost from
	     * <code>paiAbstractNode</code> to itself if the movement is not diagonally,
	     * otherwise it will assume <code>DIAGONALMOVEMENTCOST</code>.
	     * Weather or not it is diagonally is set in the Map class method which
	     * finds the adjacent AbstractNodes.
	     *
	     * @param paiAbstractNode
	     */
	    public void setgCosts(Node paiAbstractNode) {
	      
	            setgCosts(paiAbstractNode, BASICMOVEMENTCOST);
	       
	    }

     private int absolute(int a) {
         return a > 0 ? a : -a;
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

	@Override
	public int compareTo(Node node) {
		if (this.custo< node.custo) {
            return -1;
        }
        if (this.custo> node.custo) {
            return 1;
        }
        return 0;
	}
	
}
