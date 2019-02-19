package Game;

import java.util.ArrayList;

public class Game {
	
	private GameBoard board;
	
	private boolean redPlay;
	
	private ArrayList<Integer> redTiles;
	
	private ArrayList<Integer> blueTiles;
	
	public Game() {
		board = new GameBoard();
		redPlay = true;
		redTiles = new ArrayList<Integer>();
		blueTiles = new ArrayList<Integer>();
	}
	
	public void makePlay(int input) {
		System.out.println("What space would you like to place a marker in?");
		if(redPlay) {
			if(board.setTile(input, -1)) {
				redPlay = false;
				redTiles.add(input);
			}
		}
		else {
			if(board.setTile(input, 1)) {
				redPlay = true;
				blueTiles.add(input);
			}
		}
	}
	
	
}
