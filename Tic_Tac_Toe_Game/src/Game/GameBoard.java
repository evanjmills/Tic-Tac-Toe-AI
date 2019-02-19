package Game;

import java.util.ArrayList;

/**
 * 
 * @author Evan Mills
 *
 */
public class GameBoard {

	/**
	 * Array of tiles to represent the game board
	 */
	private Tile[][] board = {{new Tile(0,1), new Tile(0,2), new Tile(0,3)}, {new Tile(0,4), new Tile(0,5), new Tile(0,6)}, {new Tile(0,7), new Tile(0,8), new Tile(0,9)}};
	
	public GameBoard() {
		
	}
	
	public GameBoard(Tile[][] temp) {
		board = temp;
	}
	
	/**
	 * gets the state of the tile in the given spot
	 * @param tile
	 * 		the tile that we want to know the state of
	 * @return
	 * 		the state of the tile
	 */
	public int status(int tile) {
		if(tile == 1) {
			return board[0][0].state();
		}
		else if(tile == 2) {
			return board[0][1].state();
		}
		else if(tile == 3) {
			return board[0][2].state();
		}
		else if(tile == 4) {
			return board[1][0].state();
		}
		else if(tile == 5) {
			return board[1][1].state();
		}
		else if(tile == 6) {
			return board[1][2].state();
		}
		else if(tile == 7) {
			return board[2][0].state();
		}
		else if(tile == 8) {
			return board[2][1].state();
		}
		else {
			return board[2][2].state();
		}
	}
	
	/**
	 * Sets the given tile to the given state
	 * @param tile
	 * 		the tile to be set
	 * @param state
	 * 		the state that the tile will be set to
	 * @return
	 * 		weather a tile was set
	 */
	public boolean setTile(int tile, int state) {
		if(tile == 1 && board[0][0].state() == 0) {
			board[0][0].setState(state);
			return true;
		}
		else if(tile == 2 && board[0][1].state() == 0) {
			board[0][1].setState(state);
			return true;
		}
		else if(tile == 3 && board[0][2].state() == 0) {
			board[0][2].setState(state);
			return true;
		}
		else if(tile == 4 && board[1][0].state() == 0) {
			board[1][0].setState(state);
			return true;
		}
		else if(tile == 5 && board[1][1].state() == 0) {
			board[1][1].setState(state);
			return true;
		}
		else if(tile == 6 && board[1][2].state() == 0) {
			board[1][2].setState(state);
			return true;
		}
		else if(tile == 7 && board[2][0].state() == 0) {
			board[2][0].setState(state);
			return true;
		}
		else if(tile == 8 && board[2][1].state() == 0) {
			board[2][1].setState(state);
			return true;
		}
		else if(tile == 9 && board[2][2].state() == 0) {
			board[2][2].setState(state);
			return true;
		}
		return false;
	}
	
	/**
	 * Deep copies the game board
	 * @return
	 * 		new copy of the current game board
	 */
	public GameBoard copy() {
		Tile[][] tempBoard = new Tile[board.length][board.length];
		for(int row = 0; row < board.length; row++) {
			for(int col = 0; col < board[row].length; col++) {
				tempBoard[row][col] = new Tile(board[row][col].state(), board[row][col].index());
			}
		}
		return new GameBoard(tempBoard);
	}
	
	/**
	 * Gets a list of the empty tiles in the board
	 * @return
	 * 		list of empty tiles
	 */
	public ArrayList<Integer> getEmpty() {
		ArrayList<Integer> ret = new ArrayList<Integer>();
		
		for(int row = 0; row < board.length; row++) {
			for(int col = 0; col < board[row].length; col++) {
				if(board[row][col].state() == 0) {
					ret.add(board[row][col].index());
				}
			}
		}
		
		return ret;
	}
	
	/**
	 * Prints the current board
	 */
	public void printBoard() {
		int count = 1;
		for(int row = 0; row < board.length; row++) {
			for(int col = 0; col < board[row].length; col++) {
				if(board[row][col].state() == -1) {
					System.out.print(" O ");
				}
				else if(board[row][col].state() == 1) {
					System.out.print(" X ");
				}
				else {
					System.out.print(" " + count + " ");
				}
				count ++;
			}
			System.out.println();
		}
	}
	
	/**
	 * Checks to see if the game is over
	 * @return
	 * 		-1 if red won
	 * 		1 if blue won
	 * 		0 if no one won
	 * 		2 if the board is filled
	 */
	public int gameOver() {
		
		for(int col = 0; col < board.length; col++) {
			if(vWin(col) != 2) {
				return vWin(col);
			}
		}
		
		for(int row = 0; row < board.length; row++) {
			if(hWin(row) != 2) {
				return hWin(row);
			}
		}
		
		if(dWin() != 2) {
			return dWin();
		}
		
		boolean filled = true;
		for(int row = 0; row < board.length; row++) {
			for(int col = 0; col < board[row].length; col++) {
				if(board[row][col].state() == 0) {
					filled = false;
				}
			}
		}
		
		if(filled) {
			return 0;
		}
		
		return 2;
	}
	
	/**
	 * checks to see if a player won in the vertical direction
	 * @param col
	 * 		the column to check
	 * @return
	 * 		-1 if red won in the vertical direction
	 * 		1 if blue won in the vertical direction
	 * 		0 if no one won in the vertical direction
	 */
	private int vWin(int col) {
		boolean bool =  board[0][col].state() == board[1][col].state() && board[0][col].state() == board[2][col].state();
		if(bool && board[0][col].state() != 0) {
			return board[0][col].state();
		}
		return 2;
	}
	
	/**
	 * Checks to see if a player won in the horizontal direction
	 * @param row
	 * 	the row to check
	 * @return
	 * 		-1 if red won in the horizontal direction
	 * 		1 if blue won in the horizontal direction
	 * 		0 if no one won in the horizontal direction
	 */
	private int hWin(int row) {
		boolean bool = board[row][0].state() == board[row][1].state() && board[row][0].state() == board[row][2].state();
		
		if(bool && board[row][0].state() != 0) {
			return board[row][0].state();
		}
		
		return 2;
	}
	
	/**
	 * Checks to see if a player won in the diagonal direction
	 * @return
	 * 		-1 if red won in the diagonal direction
	 * 		1 if blue won in the diagonal direction
	 * 		0 if no one won in the diagonal direction
	 */
	private int dWin() {
		boolean bool1 = board[0][0].state() == board[1][1].state() && board[0][0].state() == board[2][2].state();
		boolean bool2 = board[0][2].state() == board[1][1].state() && board[0][2].state() == board[2][0].state();
		
		if((bool1 || bool2) && board[1][1].state() != 0 ) {
			return board[1][1].state();
		}
		return 2;
	}
}
