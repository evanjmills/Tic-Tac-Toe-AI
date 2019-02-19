package AI;

import java.util.ArrayList;

import Game.GameBoard;
/**
 * 
 * @author Evan Mills
 *
 */
public class Node {
	/**
	 * The current game board
	 */
	private GameBoard state;
	
	/**
	 * All the possible moves that can be made from this board
	 */
	private ArrayList<Node> children;
	
	/**
	 * The last move that was made to get this board
	 */
	private int previousMove;
	
	/**
	 * Whether or not it is red's turn to play
	 */
	private boolean redPlay;
	
	/**
	 * Creates a new node with the given state, whether or not it is red's turn, and the last move made
	 * @param state
	 * 		current game board
	 * @param redPlay
	 * 		true if red's turn to play
	 * @param move
	 * 		the last move to get this game board
	 */
	public Node(GameBoard state, boolean redPlay, int move) {
		this.state = state;
		this.redPlay = redPlay;
		previousMove = move;
		children = new ArrayList<Node>();
	}
	
	/**
	 * Returns the move that was made to get this game board
	 * @return
	 * 		previous move
	 */
	public int previousMove() {
		return previousMove;
	}
	
	/**
	 * Finds the best outcome that can be made with the current game board
	 * @return
	 * 		the best outcome
	 */
	public int minimax() {
		if(state.gameOver() != 2) {
			return state.gameOver();
		}
		setChildren();
		int ext = children.get(0).minimax();
		for(int x = 1; x < children.size(); x++) {
			int val = children.get(x).minimax();
			if(redPlay && val < ext) {
				ext = val;
			}
			else if(!redPlay && val > ext) {
				ext = val;
			}
		}
		return ext;
	}
	
	/**
	 * Theoretically makes a move and places it in the children list
	 */
	private void setChildren() {
		ArrayList<Integer> emp = state.getEmpty();
		
		for(Integer temp : emp) {
			GameBoard tempBoard = state.copy();
			if(redPlay) {
				tempBoard.setTile(temp, -1);
			}
			else {
				tempBoard.setTile(temp, 1);
			}
			children.add(new Node(tempBoard, !redPlay, temp));
		}
	}
}
