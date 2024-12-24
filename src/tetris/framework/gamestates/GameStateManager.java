package tetris.framework.gamestates;

import java.awt.Graphics;
import java.util.Stack;

public class GameStateManager {

	private Stack<GameState> gamestatesStack;
	
	/**Init manager*/
	public GameStateManager() {
		this.gamestatesStack = new Stack<>();
		System.out.println("[Framework][GameStates]: Created game state manager");
	}
	
	/**Call to swith to a new state<br>
	 * Adds the state to the stack
	 * @param state - The new state
	 */
	public void changeState(GameState state) {
		this.gamestatesStack.add(state);
	}
	
	/**Call to go back to previous state<br>
	 * Removes current state from the stack
	 */
	public void backToPrevious() {
		this.gamestatesStack.pop();
	}
	
	/**Clears the stack by removing all game states<br>
	 * Remember to add a new game state immediatly after
	 */
	public void clearStack() {
		this.gamestatesStack.clear();
	}
	
	/**Calls GameState#init on the first state of the stack*/
	public void init() {
		this.gamestatesStack.peek().init();
	}

	/**Calls GameState#tick on the first state of the stack*/
	public void tick() {
		this.gamestatesStack.peek().tick();
	}

	/**Calls GameState#render on the first state of the stack*/
	public void render(Graphics graphics) {
		this.gamestatesStack.peek().render(graphics);
	}

	/**Calls GameState#keyPressed on the first state of the stack*/
	public void keyPressed(int key) {
		this.gamestatesStack.peek().keyPressed(key);
	}

	/**Calls GameState#keyReleased on the first state of the stack*/
	public void keyReleased(int key) {
		this.gamestatesStack.peek().keyReleased(key);
	}
}
