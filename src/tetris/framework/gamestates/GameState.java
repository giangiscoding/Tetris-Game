package tetris.framework.gamestates;

import java.awt.Graphics;

public abstract class GameState {
	
	/**Creates a game state<br>
	 * Calls this.init()
	 */
	public GameState() {
		this.init();
	}
	
	/**Called when state is created<br>
	 * Should init every class field
	 */
	protected abstract void init();
	
	/**Called at every iteration of the game loop<br>
	 * Should handle game logic
	 */
	public abstract void tick();
	
	/**Called in GameScreen#paintComponent<br>
	 * Renders everything that should be rendered in this state
	 * @param graphics - Graphics object
	 */
	public abstract void render(Graphics graphics);
	
	/**Called in GameScreen.Keyboard#keryPressed<br>
	 * Performs an action when the key is pressed
	 * @param key - The key code KeyEvent#getKeyCode
	 */
	public abstract void keyPressed(int key);

	/**Called in GameScreen.Keyboard#keryReleased<br>
	 * Performs an action when the key is released
	 * @param key - The key code KeyEvent#getKeyCode
	 */
	public abstract void keyReleased(int key);
}
