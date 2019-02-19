package AI;

import java.util.ArrayList;

import Game.GameBoard;

/**
 * 
 * @author Evan Mills
 *
 */
public class AI {
	/**
	 * The current board
	 */
	private GameBoard state;
	
	/**
	 * All of the possible moves that can be made
	 */
	private ArrayList<Node> children;
	
	/**
	 * Creates a new AI with the current state of the board
	 * @param state
	 * 		the current state of the board
	 */
	public AI(GameBoard state) {
		this.state = state;
		children = new ArrayList<Node>();
	}
	
	/**
	 * Looks through all of the children to find the best move to be made
	 * @return
	 * 		the best move that can be made
	 */
	public int minimax() {
		if(state.gameOver() != 2) {
			return state.gameOver();
		}
		
		setChildren();
		
		int ext = children.get(0).minimax();
		int index = 0;
		
		for(int x = 1; x < children.size(); x++) {
			int val = children.get(x).minimax();
			if(val < ext) {
				ext = val;
				index = x;
			}
		}
		return children.get(index).previousMove();
	}
	
	/**
	 * Theoretically makes a move and places it in the children list
	 */
	private void setChildren() {
		ArrayList<Integer> emp = state.getEmpty();
		
		for(Integer temp : emp) {
			GameBoard tempBoard = state.copy();
			tempBoard.setTile(temp, -1);
			children.add(new Node(tempBoard, false, temp));
		}
	}
}
