package Game;

import java.util.Scanner;

import AI.AI;

/**
 * 
 * @author Evan Mills
 *
 */
public class Game {
	
	/**
	 * The game board
	 */
	private GameBoard board;
	
	/**
	 * Keeps track of whose turn it is
	 */
	private boolean redPlay;
	
	/**
	 * Creates and runs a new game
	 */
	public Game() {
		board = new GameBoard();
		redPlay = false;
		int temp = 2;
		Scanner s = new Scanner(System.in);
		board.printBoard();
		while(temp == 2) {
			if(redPlay) {
				AI ai = new AI(board);	
				temp = makePlay(ai.minimax());
			}
			else {
				System.out.println("What space would you like to place a marker in?");
				int input = s.nextInt();
				temp = makePlay(input);
			}
			System.out.println();
		}
		if(temp== -1) {
			System.out.println("Red wins");
		}
		else if(temp==1) {
			System.out.println("blue wins");
		}
		else {
			System.out.println("Cats game");
		}
		
		s.close();
	}
	
	/**
	 * Makes a valid move
	 * @param input
	 * 		the tile you want to cover
	 * @return
	 * 		if someone one and who won.
	 */
	public int makePlay(int input) {
		if(redPlay) {
			if(board.setTile(input, -1)) {
				redPlay = false;
			}
		}
		else {
			if(board.setTile(input, 1)) {
				redPlay = true;
			}
		}
		
		board.printBoard();
		
		return board.gameOver();
	}
	
	/**
	 * Gets the current game board. ONLY USED FOR AI
	 * @return
	 * 		board
	 */
	public GameBoard board(){
		return board;
	}
}
