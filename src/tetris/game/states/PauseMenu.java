package tetris.game.states;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import tetris.game.Game;

public class PauseMenu extends MainMenu {

	@Override
	protected void init() {
		this.options = new String[] {"Resume", "Quit Game"};
	}

	@Override
	public void render(Graphics graphics) {
		super.render(graphics);
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
				Game.STATE_MANAGER.backToPrevious();
			} else if(this.selected == 1) {
				Game.STATE_MANAGER.clearStack();
				Game.STATE_MANAGER.changeState(new MainMenu());
			}
		}
	}
	
}
