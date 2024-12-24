package tetris.game.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import tetris.framework.display.Window;
import tetris.framework.gamestates.GameState;
import tetris.framework.resources.ResourceManager;
import tetris.game.Game;

public class HighscoresMenu extends GameState {

	private int[] scores;
	
	@Override
	protected void init() {
		this.scores = ResourceManager.readHighscoresFile();
	}

	@Override
	public void tick() {}

	@Override
	public void render(Graphics graphics) {
		this.drawBackground(graphics);
		this.drawScores(graphics);
	}

	@Override
	public void keyPressed(int key) {
		if(key == KeyEvent.VK_ESCAPE) {
			Game.STATE_MANAGER.backToPrevious();
		}
	}

	@Override
	public void keyReleased(int key) {}

	/**Fills the background with a color*/
	private void drawBackground(Graphics graphics) {
		graphics.setColor(new Color(10, 10, 30));
		graphics.fillRect(0, 0, Window.WIDTH, Window.HEIGHT);
		graphics.setColor(Color.WHITE);
		graphics.setFont(new Font("Arial", Font.PLAIN, 30));
		graphics.drawString("Highscores", 30, 40);
		graphics.setFont(new Font("Arial", Font.PLAIN, 18));
		graphics.drawString("Press ESC to return to main menu", 10, 280);
	}
	
	private void drawScores(Graphics graphics) {
		graphics.setColor(Color.WHITE);
		graphics.setFont(new Font("Arial", Font.PLAIN, 14));
		for(int i=0;i<this.scores.length;i++) {
			graphics.drawString((i+1)+" - "+this.scores[i]+" lines", 60, 80+i*20);
		}
	}
}
