package tetris.game.pieces;

public class Square {

	private String color;
	private boolean fixed;
	
	private boolean stopping;
	private int stopTime;
	
	public Square(String color) {
		this.color = color;
		this.fixed = false;
		this.stopping = false;
		this.stopTime = 0;
	}
	
	/**Getter method for 'fixed' value
	 * @return True if square is fixed (appears darker on the screen), false if it's not
	 */
	public boolean isFixed() {
		return fixed;
	}
	
	/**First checks if this square is 'stopping', if so increase stop time<br>
	 * When stop times is 75 (after this method is called 75 times in PlayingState#stopPieces()) this square is set to 'fixed'
	 */
	public void setFixed() {
		if(this.stopping)
			this.stopTime++;
			
		if(stopTime == 40) {
			this.fixed = true;
			this.stopTime = 0;
		}
	}
	
	/**Set method<br>
	 * Sets this square to 'stopping'
	 */
	public void setStopping() {
		this.stopping = true;
	}
	
	public void resetStopTime() {
		this.stopTime = 0;
		this.stopping = false;
	}
	
	/**Getter method for 'stopping' value
	 * @return True if the square is stopping (is about to be set to 'fixed'), false if it's not
	 */
	public boolean isStopping() {
		return stopping;
	}
	
	public void setNotFixed() {
		this.fixed = false;
	}
	
	/**Getter method<br>
	 * Gets the color of this square
	 * @return A string representing the color
	 */
	public String getColor() {
		return color;
	}
	
	public void forceFix() {
		this.fixed = true;
	}
}
