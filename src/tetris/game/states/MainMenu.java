package tetris.game.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import tetris.framework.display.Window;
import tetris.framework.gamestates.GameState;
import tetris.framework.resources.ResourceManager;
import tetris.game.Game;

public class MainMenu extends GameState {

	protected String[] options;
	protected int selected;
	
	@Override
	protected void init() {
		this.options = new String[] {"Start Game", "Highscores", "  Log Out", "Quit Game"};
		this.selected = 0;
		System.out.println("[Game][States]: Created main menu");
	}

	@Override
	public void tick() {}

	@Override
	public void render(Graphics graphics) {
		this.drawBackground(graphics);
		this.drawButtons(graphics);
		this.drawOptions(graphics);
	}

	@Override
	public void keyPressed(int key) {
		if(key == KeyEvent.VK_UP) {
			if(this.selected > 0) this.selected--;
		}
		else if(key == KeyEvent.VK_DOWN) {
			if(this.selected < this.options.length-1) this.selected++;
		}
		else if(key == KeyEvent.VK_ENTER) {
			if(this.selected == 0) {
				Game.STATE_MANAGER.changeState(new PlayingState());
			} else if(this.selected == 1) {
				Game.STATE_MANAGER.changeState(new HighscoresMenu());
			} else if(this.selected == 2) {
				Game.STATE_MANAGER.changeState(new Login());
			} else if(this.selected == 3) {
				System.exit(0);
			}
		}
	}

	@Override
	public void keyReleased(int key) {}

	/**Fills the background with a color*/
	private void drawBackground(Graphics graphics) {
		graphics.setColor(new Color(10, 10, 30));
		graphics.fillRect(0, 0, Window.WIDTH, Window.HEIGHT);
	}
	
	/**Draws the menu options*/
	private void drawOptions(Graphics graphics) {
		graphics.setColor(Color.WHITE);
		graphics.setFont(new Font("Arial", Font.PLAIN, 20));
		for(int i=0;i<options.length;i++) {
			if(selected == i) 
				graphics.setColor(Color.GREEN);
			else
				graphics.setColor(Color.WHITE);
			
			graphics.drawString(options[i], Window.WIDTH/3, 190 + 60 * i);
		}
	}
	
	/**Draws the buttons and the logo*/
	private void drawButtons(Graphics graphics) {
		graphics.drawImage(ResourceManager.texture("logo.png"), Window.WIDTH / 3 - 70, 10, 220, 140, null);
		for(int i=0;i<options.length;i++) {
			graphics.drawImage(ResourceManager.texture("menu_button.png"), Window.WIDTH/3-40, 160 + 60 * i, 160, 50, null);
		}
	}
}
