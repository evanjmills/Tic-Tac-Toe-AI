package Game;

/**
 * 
 * @author Evan Mills
 *
 */
public class Tile {
	/**
	 * Stores the state of the cell
	 */
	private int state;
	
	public int index;
	
	/**
	 * creates a new tile with an initial state of the given value
	 * @param state
	 * 		initial state of the cell
	 */
	public Tile(int state, int index) {
		this.index = index;
		this.state = state;
	}
	
	/**
	 * sets the state of the tile to the given state
	 * @param state 
	 * 		the state that the cell will be set to
	 */
	public void setState(int state) {
		this.state = state;
	}
	
	/**
	 * returns the state of the cell
	 * @return
	 * 		-1 for red
	 * 		1 for blue
	 * 		0 for empty
	 */
	public int state() {
		return state;
	}
	
	public int index() {
		return index;
	}
}
