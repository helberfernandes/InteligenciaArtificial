package br.com.estudo.busca.minmax;
/**
 * Classe responsavel por guardar uma posicao 
 * referente a Y e X se gundo o plano cartesiano.
 * ex:
 * y ^
 * 4 |
 * 3 |-----o
 * 2 |     |
 * 1 |     |
 * 0  --------------->
 *   0 1 2 3 4 5 6 7 x
 *   
 *  Neste caso y=3 e x=3, marca a posicao.
 *   
 * @author Helber
 *
 */
public class Point {
	private int y;
	private int x;

	public Point(int y, int x) {
		super();
		this.y = y;
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	@Override
	public String toString() {
		return "Point [y=" + y + ", x=" + x + "]";
	}
}
