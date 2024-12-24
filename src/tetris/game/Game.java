package tetris.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import tetris.framework.display.Window;
import tetris.framework.gamestates.GameStateManager;
import tetris.framework.resources.ResourceManager;
import tetris.game.states.Login;

public class Game {
	
	public static final GameStateManager STATE_MANAGER = new GameStateManager();
	
	private static Timer timer;
	private static boolean running = false;
	
	public static void main(String[] args) {
		System.out.println("[Game][Main]: Starting...");
		ResourceManager.readImageFiles();
		Window.create();
		startGame();
		System.out.println("[Game][Main]: Started!");
	}
	
	/**Changes state to main menu<br>
	 * Starts timer (game loop)<br>
	 * Sets game to "running"
	 */
	private static void startGame() {
		STATE_MANAGER.changeState(new Login());
		timer = new Timer(20, new GameLoop());
		running = true;
		timer.start();
	}
	
	/**Checks if the game is running*/
	public static boolean isRunning() {
		return running;
	}
	
	private static class GameLoop implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Game.STATE_MANAGER.tick();
		}
		
	}
}
