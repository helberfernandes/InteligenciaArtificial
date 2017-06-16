package br.com.estudo.busca.minmax;

import java.util.ArrayList;
import java.util.List;

public class Board {
	public static final int NO_PLAYER = 0;
	public static final int PLAYER_X = 1;
	public static final int PLAYER_O = 2;
	private int[][] board = new int[3][3];
	public Point conputerMove;

	public boolean isGameOver() {
		return hasPlayerWon(PLAYER_X) || hasPlayerWon(PLAYER_O) || getAvalibableCells().isEmpty();
	}

	/**
	 * 
	 * @return retorna uma lista de posicoes disponiveis para o jogo;
	 */
	private List<Point> getAvalibableCells() {
		List<Point> avaliableCells = new ArrayList<>();
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 3; x++) {
				if (board[y][x] == NO_PLAYER)
					avaliableCells.add(new Point(y, x));
			}
		}
		return avaliableCells;
	}

	private boolean hasPlayerWon(int player) {
		if ((board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] == player)
				|| (board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[0][2] == player)) {
			return true;
		}

		for (int i = 0; i < 3; i++) {
			if ((board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] == player)
					|| (board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] == player)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Adiciona uma jogada caso nas coordenasdas naos exista uma jogada
	 * estabelecida, assim evita o sbescrever de jogadas.
	 * 
	 * @param point
	 *            coordenada que se deseja jogar
	 * @param player
	 *            marca do jogador
	 * @return true ou false, indicando se a jogada pode ser efetivada.
	 */
	public boolean placeAMove(Point point, int player) {
		if (board[point.getX()][point.getY()] != NO_PLAYER)
			return false;

		board[point.getX()][point.getY()] = player;
		return true;
	}

	public void displayBoard() {
		System.out.println();

		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 3; x++) {
				String value = "?";
				
				if (board[y][x] == PLAYER_X) {
					value = "X";
				} else if (board[y][x] == PLAYER_O) {
					value = "O"; 
				}
				System.out.print(value);

			}
			System.out.println();
		}
	}
	
	

}
